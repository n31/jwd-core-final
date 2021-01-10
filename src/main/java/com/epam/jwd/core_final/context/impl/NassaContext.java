package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

// todo
public class NassaContext implements ApplicationContext {
    private static final Logger logger = LoggerFactory.getLogger(NassaContext.class);
    private ApplicationProperties props = new ApplicationProperties();
    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();

    private NassaContext() {}

    private static NassaContext instance;
    public static NassaContext getInstance() {
        if (instance == null) {
            instance = new NassaContext();
        }
        return  instance;
    }

    public ApplicationProperties getProps() {
        return props;
    }

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        if (tClass.getSimpleName().equals("CrewMember")) {
            return (Collection<T>)crewMembers;
        } else if (tClass.getSimpleName().equals("Spaceship")) {
            return (Collection<T>)spaceships;
        }
        return null;
    }

    /**
     * You have to read input files, populate collections
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {
        CrewMemberFactory crewMemberFactory = CrewMemberFactory.getInstance();
        String[] crewRawData = retrieveRawCrewData().split(";");
        for (String crewMemberData : crewRawData) {
            String[] separatedMemberData = crewMemberData.split(",");
            crewMembers.add(crewMemberFactory.create(Long.parseLong(separatedMemberData[0]),
                    separatedMemberData[1], Long.parseLong(separatedMemberData[2])));
        }
        if (crewMembers.size() == 0) throw new InvalidStateException();

        SpaceshipFactory spaceshipFactory = SpaceshipFactory.getInstance();
        List<String> spaceshipRawData = retrieveRawSpaceshipData();
        spaceshipRawData.forEach(e -> {
            String[] spaceshipData = e.split(";");
            spaceships.add(spaceshipFactory.create(spaceshipData[0], Long.parseLong(spaceshipData[1]), spaceshipData[2]));
        });
        if (spaceships.size() == 0) throw new InvalidStateException();
    }

    private String retrieveRawCrewData() {
        String rawCrewData = "";
        try {
            File crewFile = new File(props.getInputRootDir() + File.separator + props.getCrewFileName());
            Scanner crewReader = new Scanner(crewFile);
            while (crewReader.hasNextLine()) {
                String data = crewReader.nextLine();
                if (data.charAt(0) != '#') rawCrewData = data;
            }
            crewReader.close();
        } catch (FileNotFoundException e) {
            logger.error("Failed to load - " + e.getMessage());
            System.exit(1);
        }
        return rawCrewData;
    }

    private List<String> retrieveRawSpaceshipData() {
        List<String> rawSpaceshipData = new ArrayList<>();
        try {
            File spaceshipFile = new File(props.getInputRootDir() + File.separator + props.getSpaceshipsFileName());
            Scanner spaceshipReader = new Scanner(spaceshipFile);
            while (spaceshipReader.hasNextLine()) {
                String data = spaceshipReader.nextLine();
                if (data.charAt(0) != '#') rawSpaceshipData.add(data);
            }
        } catch (FileNotFoundException e) {
            logger.error("Failed to load - " + e.getMessage());
            System.exit(1);
        }
        return rawSpaceshipData;
    }
}
