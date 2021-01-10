package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDate;

public class FlightMissionFactory implements EntityFactory {
    private Long lastId = 1L;

    private FlightMissionFactory() {}

    private static FlightMissionFactory instance;
    public static FlightMissionFactory getInstance() {
        if (instance == null) {
            instance = new FlightMissionFactory();
        }
        return instance;
    }

    @Override
    public FlightMission create(Object... args) {
        String name = (String)args[0];
        LocalDate startDate = (LocalDate)args[1];
        LocalDate endDate = (LocalDate)args[2];
        Long distance = (Long)args[3];
        MissionResult missionResult = (MissionResult)args[4];

        return new FlightMission(lastId++, name, startDate, endDate, distance, missionResult);
    }
}
