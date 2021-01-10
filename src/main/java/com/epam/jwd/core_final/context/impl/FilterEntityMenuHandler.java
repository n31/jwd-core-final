package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.EntityMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.impl.CrewCrud;
import com.epam.jwd.core_final.service.impl.MissionCrud;
import com.epam.jwd.core_final.service.impl.SpaceshipCrud;

import java.util.List;
import java.util.Map;

public class FilterEntityMenuHandler implements EntityMenu {
    @Override
    public Object handleInput(Object o) {
        Map<String, Object> values = (Map<String, Object>) o;
        if (values.containsKey("crewMemberOptions")) {
            Role role = (Role) values.get("role");
            Rank rank = (Rank) values.get("rank");
            Boolean isReadyForNextMissions = (Boolean) values.get("isReadyForNextMissions");
            CrewMemberCriteria criteria = new CrewMemberCriteria();
            criteria.setRole(role);
            criteria.setRank(rank);
            criteria.setReadyForNextMissions(isReadyForNextMissions);
            List<CrewMember> members = CrewCrud.getInstance().findAllCrewMembersByCriteria(criteria);
            members.forEach(System.out::println);
        } else if (values.containsKey("spaceshipOptions")) {
            Long flightDistance = (Long) values.get("flightDistance");
            Boolean isReadyForNextMissions = (Boolean) values.get("isReadyForNextMissions");
            SpaceshipCriteria criteria = new SpaceshipCriteria();
            criteria.setFlightDistance(flightDistance);
            criteria.setReadyForNextMissions(isReadyForNextMissions);
            List<Spaceship> members = SpaceshipCrud.getInstance().findAllSpaceshipsByCriteria(criteria);
            members.forEach(System.out::println);
        } else if (values.containsKey("missionOptions")) {
            String name = (String) values.get("name");
            Long distance = (Long) values.get("distance");
            MissionResult missionResult = (MissionResult) values.get("missionResult");
            FlightMissionCriteria criteria = new FlightMissionCriteria();
            criteria.setDistance(distance);
            criteria.setMissionResult(missionResult);
            criteria.setName(name);
            List<FlightMission> members = MissionCrud.getInstance().findAllMissionsByCriteria(criteria);
            members.forEach(System.out::println);
        }
        return null;
    }

}
