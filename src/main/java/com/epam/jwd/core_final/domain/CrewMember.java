package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.criteria.Criteria;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class CrewMember extends AbstractBaseEntity {
    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMissions;


    public CrewMember(Role role, String name, Rank rank, Long id) {
        super(id, name);
        this.role = role;
        this.rank = rank;
        this.isReadyForNextMissions = true;
    }

    public void setReadyForNextMissions(Boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    @Override
    public String toString() {
        String result = "ID_" + getId().toString() + " " + getRole() + " " + getRank() + " " + getName() + " | Ready = "
                + getReadyForNextMissions();
        return result;
    }

    public boolean isMatchesCriteria(Criteria<? extends CrewMember> criteria) {
        boolean flag = true;
        if (criteria.getId() != null && !this.getId().equals(criteria.getId())) {
            flag = false;
        }
        if (criteria.getName() != null && !this.getName().equals(criteria.getName())) {
            flag = false;
        }
        if (criteria.getRole() != null && !this.getRole().equals(criteria.getRole())) {
            flag = false;
        }
        if (criteria.getRank() != null && !this.getRank().equals(criteria.getRank())) {
            flag = false;
        }
        if (criteria.getReadyForNextMissions() != null
                && !this.getReadyForNextMissions().equals(criteria.getReadyForNextMissions())) {
            flag = false;
        }
        return flag;
    }
}
