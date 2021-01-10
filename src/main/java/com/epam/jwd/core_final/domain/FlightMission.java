package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.criteria.Criteria;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDate}
 * end date {@link java.time.LocalDate}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 */
public class FlightMission extends AbstractBaseEntity {
    // todo
    private LocalDate startDate;
    private LocalDate endDate;
    private Long distance;
    private Spaceship assignedSpaceShift;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;

    public FlightMission(Long id, String name, LocalDate startDate, LocalDate endDate,
                         Long distance, MissionResult missionResult) {
        super(id, name);
        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
        this.missionResult = missionResult;
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

    public Spaceship getAssignedSpaceShift() {
        return assignedSpaceShift;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public void setAssignedSpaceShift(Spaceship assignedSpaceShift) {
        this.assignedSpaceShift = assignedSpaceShift;
    }

    public void setAssignedCrew(List<CrewMember> assignedCrew) {
        this.assignedCrew = assignedCrew;
    }

    public boolean isMatchesCriteria(Criteria<? extends FlightMission> criteria) {
        boolean flag = true;
        if (criteria.getId() != null && !this.getId().equals(criteria.getId())) {
            flag = false;
        }
        if (criteria.getName() != null && !this.getName().equals(criteria.getName())) {
            flag = false;
        }
        if (criteria.getStartDate() != null && !(this.getStartDate().isBefore(criteria.getStartDate()))) {
            flag = false;
        }
        if (criteria.getEndDate() != null && !(this.getEndDate().isAfter(criteria.getEndDate()))) {
            flag = false;
        }
        if (criteria.getDistance() != null && !(this.getDistance() <= criteria.getDistance())) {
            flag = false;
        }
        if (criteria.isSpaceshipAssigned() != null
                && !((this.getAssignedSpaceShift() != null) == criteria.isSpaceshipAssigned()) ) {
            flag = false;
        }
        if (criteria.getMissionResult() != null && !(this.getMissionResult().equals(criteria.getMissionResult()))) {
            flag = false;
        }
        if (criteria.getAssignedSpacehip() != null && this.getAssignedSpaceShift() != null &&
                !(this.getAssignedSpaceShift().equals(criteria.getAssignedSpacehip()))) {
            flag = false;
        }
        if (criteria.getAssignedSpacehip() != null && this.getAssignedSpaceShift() == null) {
            flag = false;
        }
        return flag;
    }

    @Override
    public String toString() {
        String result = "ID_" + getId() + " " + getName() + " | Status = " + getMissionResult().toString() + "\n";
        result += startDate.toString() + " - " + endDate.toString() + " | Distance = " + distance + "\n";
        if (assignedSpaceShift != null) result += assignedSpaceShift.toString();
        if (assignedCrew != null) {
            for (CrewMember member : assignedCrew) {
                result += member.toString() + "\n";
            }
        }
        return result;
    }

    public String toJSON() {
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = "";
        try {
            jsonStr = Obj.writeValueAsString(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
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

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }
}
