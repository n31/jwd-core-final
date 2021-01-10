package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.EntityMenu;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.impl.MissionCrud;
import com.epam.jwd.core_final.util.InputUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MissionUpdateHandler implements EntityMenu {
    private static final ApplicationProperties props = new ApplicationProperties();

    @Override
    public Object show(Object o) {
        List<FlightMission> missions = MissionCrud.getInstance().findAllMissions();
        missions.forEach(System.out::println);
        System.out.print("Enter mission id: ");
        return InputUtil.handleChoice(1, missions.size());
    }

    @Override
    public Object handleInput(Object o) {
        int id = (int)o;
        FlightMission mission = MissionCrud.getInstance().findAllMissions().get(id - 1);

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

        mission.setName(name);
        mission.setDistance(distance);
        mission.setStartDate(startDate);
        mission.setEndDate(endDate);
        mission.setMissionResult(missionResult);

        MissionCrud.getInstance().updateSpaceshipDetails(mission);

        return mission;
    }

    public static MissionResult extractMissionResult(long result) {
        MissionResult missionResult = null;
        if (result == 1) {
            missionResult = MissionResult.CANCELLED;
        } else if (result == 2) {
            missionResult = MissionResult.FAILED;
        } else if (result == 3) {
            missionResult = MissionResult.PLANNED;
        } else if (result == 4) {
            missionResult = MissionResult.IN_PROGRESS;
        } else if (result == 5) {
            missionResult = MissionResult.COMPLETED;
        }
        return missionResult;
    }
}
