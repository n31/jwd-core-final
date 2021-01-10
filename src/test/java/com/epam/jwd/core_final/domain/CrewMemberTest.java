package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CrewMemberTest {
    CrewMember crewMember;
    CrewMemberCriteria crewMemberCriteria;

    @Before
    public void setup() {
        crewMember = new CrewMember(Role.COMMANDER, "test", Rank.CAPTAIN, 1L);
        crewMemberCriteria = new CrewMemberCriteria();
    }

    @Test
    public void testIsMatchesCriteria() {
        crewMemberCriteria.setReadyForNextMissions(true);
        assertTrue(crewMember.isMatchesCriteria(crewMemberCriteria));
        crewMemberCriteria.setRank(Rank.FIRST_OFFICER);
        assertFalse(crewMember.isMatchesCriteria(crewMemberCriteria));
        crewMemberCriteria.setRank(Rank.CAPTAIN);
        assertTrue(crewMember.isMatchesCriteria(crewMemberCriteria));
        crewMemberCriteria.setRole(Role.COMMANDER);
        assertTrue(crewMember.isMatchesCriteria(crewMemberCriteria));
        crewMemberCriteria.setRole(Role.FLIGHT_ENGINEER);
        assertFalse(crewMember.isMatchesCriteria(crewMemberCriteria));
    }
}
