package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDate;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {
    private LocalDate startDate;
    private LocalDate endDate;
    private Long distance;
    private Boolean assignedSpaceShift;
    private MissionResult missionResult;
    private Spaceship assignedSpacehip;

    @Override
    public Criteria<FlightMission> getResult() {
        return null;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public void setAssignedSpaceShift(Boolean assignedSpaceShift) {
        this.assignedSpaceShift = assignedSpaceShift;
    }

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }

    public void setAssignedSpacehip(Spaceship assignedSpacehip) {
        this.assignedSpacehip = assignedSpacehip;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Long getDistance() {
        return distance;
    }

    public Boolean isSpaceshipAssigned() {
        return assignedSpaceShift;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public Spaceship getAssignedSpacehip() {
        return assignedSpacehip;
    }
}
