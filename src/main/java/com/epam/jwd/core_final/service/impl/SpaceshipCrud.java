package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.NotAssignableEntityException;
import com.epam.jwd.core_final.exception.NotUniqueEntityException;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpaceshipCrud implements SpaceshipService {
    List<Spaceship> spaceshipCrud;

    private SpaceshipCrud() {
        NassaContext nassaContext = NassaContext.getInstance();
        this.spaceshipCrud = (List<Spaceship>)nassaContext.retrieveBaseEntityList(Spaceship.class);;
    }
    private static SpaceshipCrud instance;
    public static SpaceshipCrud getInstance() {
        if (instance == null) {
            instance = new SpaceshipCrud();
        }
        return instance;
    }

    @Override
    public List<Spaceship> findAllSpaceships() {
        return spaceshipCrud;
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        return this.spaceshipCrud.stream()
                .filter(v -> v.isMatchesCriteria(criteria)).collect(Collectors.toList());
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        return Optional.of(this.spaceshipCrud.stream()
                .filter(v -> v.isMatchesCriteria(criteria))
                .findFirst()).orElse(null);
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        spaceshipCrud.set(Integer.parseInt(spaceship.getId().toString()) - 1, spaceship);
        return spaceship;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship) throws NotAssignableEntityException {
        if (!spaceship.getReadyForNextMissions()) throw new NotAssignableEntityException();
        MissionCrud missionCrud = MissionCrud.getInstance();
        FlightMissionCriteria flightMissionCriteria = new FlightMissionCriteria();
        flightMissionCriteria.setDistance(spaceship.getFlightDistance());
        flightMissionCriteria.setAssignedSpaceShift(false);
        Optional<FlightMission> optionalMission = missionCrud.findMissionByCriteria(flightMissionCriteria);
        if (optionalMission.isPresent()) {
            FlightMission suitableMission = optionalMission.get();
            suitableMission.setAssignedSpaceShift(spaceship);
            missionCrud.updateSpaceshipDetails(suitableMission);
            spaceship.setReadyForNextMissions(false);
            this.updateSpaceshipDetails(spaceship);
        } else {
            throw new NotAssignableEntityException();
        }
    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws NotUniqueEntityException {
        SpaceshipCriteria crit = new SpaceshipCriteria();
        crit.setName(spaceship.getName());
        if (findSpaceshipByCriteria(crit).isPresent()) {
            throw new NotUniqueEntityException();
        } else {
            spaceshipCrud.add(spaceship);
        }
        return spaceship;
    }
}
