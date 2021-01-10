package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.service.MissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MissionCrud implements MissionService {
    static Logger logger = LoggerFactory.getLogger(MissionCrud.class);
    List<FlightMission> missionCrud = new ArrayList<>();

    private MissionCrud() {}
    private static MissionCrud instance;
    public static MissionCrud getInstance() {
        if (instance == null) {
            instance = new MissionCrud();
        }
        return instance;
    }

    @Override
    public List<FlightMission> findAllMissions() {
        return missionCrud;
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        return this.missionCrud.stream()
                .filter(v -> v.isMatchesCriteria(criteria)).collect(Collectors.toList());
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        return Optional.of(this.missionCrud.stream()
                .filter(v -> v.isMatchesCriteria(criteria))
                .findFirst()).orElse(null);
    }

    @Override
    public FlightMission updateSpaceshipDetails(FlightMission flightMission) {
        missionCrud.set(Integer.parseInt(flightMission.getId().toString()) - 1, flightMission);
        return flightMission;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        missionCrud.add(flightMission);

        SpaceshipCrud spaceshipCrud = SpaceshipCrud.getInstance();
        SpaceshipCriteria spaceshipCriteria = new SpaceshipCriteria();
        spaceshipCriteria.setReadyForNextMissions(true);
        spaceshipCrud.findAllSpaceshipsByCriteria(spaceshipCriteria)
                .forEach(e -> {
                    try {
                        spaceshipCrud.assignSpaceshipOnMission(e);
                    } catch (RuntimeException err) {
                        logger.error(err.getMessage());
                    }
                });


        CrewCrud crewCrud = CrewCrud.getInstance();
        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
        crewMemberCriteria.setReadyForNextMissions(true);
        crewCrud.findAllCrewMembersByCriteria(crewMemberCriteria)
                .forEach(e -> {
                    try {
                        crewCrud.assignCrewMemberOnMission(e);
                    } catch (RuntimeException err) {
                        logger.error(err.getMessage());
                    }
                });


        return flightMission;
    }
}
