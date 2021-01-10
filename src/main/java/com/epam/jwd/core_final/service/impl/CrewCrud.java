package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.NotAssignableEntityException;
import com.epam.jwd.core_final.exception.NotUniqueEntityException;
import com.epam.jwd.core_final.service.CrewService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrewCrud implements CrewService {
    List<CrewMember> crewCrud;

    private CrewCrud() {
        NassaContext nassaContext = NassaContext.getInstance();
        this.crewCrud = (List<CrewMember>)nassaContext.retrieveBaseEntityList(CrewMember.class);
    }
    private static CrewCrud instance;
    public static CrewCrud getInstance() {
        if (instance == null) {
            instance = new CrewCrud();
        }
        return instance;
    }

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return this.crewCrud;
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        return this.crewCrud.stream()
                .filter(v -> v.isMatchesCriteria(criteria)).collect(Collectors.toList());
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        return Optional.of(this.crewCrud.stream()
                .filter(v -> v.isMatchesCriteria(criteria))
                .findFirst()).orElse(null);
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        crewCrud.set(Integer.parseInt(crewMember.getId().toString()) - 1, crewMember);
        return crewMember;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws NotAssignableEntityException {
        if (!crewMember.getReadyForNextMissions()) {
            throw new NotAssignableEntityException();
        }
        SpaceshipCrud spaceshipCrud = SpaceshipCrud.getInstance();
        SpaceshipCriteria spaceshipCriteria = new SpaceshipCriteria();
        spaceshipCriteria.setReadyForNextMissions(false);
        spaceshipCriteria.setIsVacant(crewMember.getRole());
        Optional<Spaceship> optionalSpaceship = spaceshipCrud.findSpaceshipByCriteria(spaceshipCriteria);
        if (optionalSpaceship.isPresent()) {
            Spaceship suitableSpaceship = optionalSpaceship.get();
            Map<Role, Short> spaceshipCrew = suitableSpaceship.getCrew();
            Short crewRoleNumber = (short)(spaceshipCrew.get(crewMember.getRole()) - 1);
            spaceshipCrew.put(crewMember.getRole(), crewRoleNumber);
            spaceshipCrud.updateSpaceshipDetails(suitableSpaceship);

            MissionCrud missionCrud = MissionCrud.getInstance();
            FlightMissionCriteria missionCriteria = new FlightMissionCriteria();
            missionCriteria.setAssignedSpacehip(suitableSpaceship);
            FlightMission currentMission = missionCrud.findMissionByCriteria(missionCriteria).get();
            List<CrewMember> missionCrew = currentMission.getAssignedCrew();
            if (missionCrew == null) missionCrew = new ArrayList<>();
            missionCrew.add(crewMember);
            currentMission.setAssignedCrew(missionCrew);
            missionCrud.updateSpaceshipDetails(currentMission);

            crewMember.setReadyForNextMissions(false);
            this.updateCrewMemberDetails(crewMember);
        } else {
            throw new NotAssignableEntityException();
        }
    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws NotUniqueEntityException {
        CrewMemberCriteria crit = new CrewMemberCriteria();
        crit.setName(crewMember.getName());
        if (findCrewMemberByCriteria(crit).isPresent()) {
            throw new NotUniqueEntityException();
        } else {
            crewCrud.add(crewMember);
        }
        return crewMember;
    }
}
