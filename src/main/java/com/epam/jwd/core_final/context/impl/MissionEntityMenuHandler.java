package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.EntityMenu;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.impl.MissionCrud;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MissionEntityMenuHandler implements EntityMenu {
    private static final Logger log = LoggerFactory.getLogger(MissionEntityMenuHandler.class);
    private static final ApplicationProperties props = new ApplicationProperties();

    @Override
    public Object handleInput(Object o) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(props.getDateTimeFormat());
        Scanner scan = new Scanner(System.in);

        String name;
        String startDateString;
        String endDateString;
        LocalDate startDate;
        LocalDate endDate;
        Long distance;
        MissionResult missionResult;

        System.out.print("enter name: ");
        name = scan.nextLine();

        System.out.print("enter start date " + props.getDateTimeFormat() + " : ");
        startDateString = scan.nextLine();
        startDate = LocalDate.parse(startDateString, formatter);

        System.out.print("enter end date " + props.getDateTimeFormat() + " : ");
        endDateString = scan.nextLine();
        endDate = LocalDate.parse(endDateString, formatter);

        System.out.print("enter distance: ");
        distance = Long.parseLong(scan.nextLine());

        List<MissionResult> x = Arrays.asList(MissionResult.values());
        x.forEach(e -> {
            System.out.print((x.indexOf(e) + 1) + " - ");
            System.out.println(e.toString());
        });
        int i = Integer.parseInt(scan.nextLine());
        switch (i) {
            case 1 -> missionResult = MissionResult.CANCELLED;
            case 2 -> missionResult = MissionResult.COMPLETED;
            case 3 -> missionResult = MissionResult.FAILED;
            case 4 -> missionResult = MissionResult.IN_PROGRESS;
            case 5 -> missionResult = MissionResult.PLANNED;
            default -> missionResult = MissionResult.CANCELLED;
        }

        MissionCrud missionCrud = MissionCrud.getInstance();
        return missionCrud.createMission(
                FlightMissionFactory.getInstance().create(name, startDate, endDate, distance, missionResult)
        );
    }
}
