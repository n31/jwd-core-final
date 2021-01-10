package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDate;
import java.util.Map;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {
    private String name;
    private Long id;

    public abstract Criteria<T> getResult();

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return null;
    }

    public Rank getRank() {
        return null;
    }

    public Boolean getReadyForNextMissions() {
        return null;
    }


    public Map<Role, Short> getCrew() {
        return null;
    }

    public Role isVacant() {
        return null;
    }

    public Long getFlightDistance() {
        return null;
    }

    public LocalDate getStartDate() {
        return null;
    }

    public LocalDate getEndDate() {
        return null;
    }

    public Long getDistance() {
        return null;
    }

    public Boolean isSpaceshipAssigned() {
        return null;
    }

    public MissionResult getMissionResult() {
        return null;
    }

    public Spaceship getAssignedSpacehip() {
        return null;
    }
}
