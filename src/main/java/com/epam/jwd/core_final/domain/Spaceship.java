package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.criteria.Criteria;

import java.util.Iterator;
import java.util.Map;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {
    private Map<Role, Short> crew;
    private Long flightDistance;
    private Boolean isReadyForNextMissions;
    //todo
    public Spaceship(Long id, String name, Map<Role, Short> crew, Long flightDistance) {
        super(id, name);
        this.crew = crew;
        this.flightDistance = flightDistance;
        this.isReadyForNextMissions = true;
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public void setReadyForNextMissions(Boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }

    public void setCrew(Map<Role, Short> crew) {
        this.crew = crew;
    }

    public boolean isMatchesCriteria(Criteria<? extends Spaceship> criteria) {
        boolean flag = true;
        if (criteria.getId() != null && !this.getId().equals(criteria.getId())) {
            flag = false;
        }
        if (criteria.getName() != null && !this.getName().equals(criteria.getName())) {
            flag = false;
        }
        if (criteria.getCrew() != null && !this.getCrew().equals(criteria.getCrew())) {
            flag = false;
        }
        if (criteria.getFlightDistance() != null && !(this.getFlightDistance() >= criteria.getFlightDistance())) {
            flag = false;
        }
        if (criteria.getReadyForNextMissions() != null
                && !(this.getReadyForNextMissions() == criteria.getReadyForNextMissions())) {
            flag = false;
        }
        if (criteria.isVacant() != null && !(this.crew.get(criteria.isVacant()) > (short)0)) {
            flag = false;
        }
        return flag;
    }

    @Override
    public String toString() {
        String result = "ID_" + getId().toString() + " " + getName() + " | Distance = " + getFlightDistance() +
                " | Ready = " + getReadyForNextMissions() + '\n';
        Iterator crewIterator = crew.entrySet().iterator();
        while (crewIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)crewIterator.next();
            result += mapElement.getKey() + " " + mapElement.getValue() + '\n';
        }
        return result;
    }
}
