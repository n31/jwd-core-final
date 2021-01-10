package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SpaceshipTest {
    Spaceship spaceship;
    SpaceshipCriteria spaceshipCriteria;

    @Before
    public void setup() {
        Map<Role, Short> crew = new HashMap<>();
        crew.put(Role.COMMANDER, (short)0);
        crew.put(Role.FLIGHT_ENGINEER, (short)1);
        spaceship = new Spaceship(1L, "test", crew, 1000L);
        spaceshipCriteria = new SpaceshipCriteria();
    }

    @Test
    public void testIsMatchesCriteria() {
        spaceshipCriteria.setReadyForNextMissions(true);
        assertTrue(spaceship.isMatchesCriteria(spaceshipCriteria));
        spaceshipCriteria.setFlightDistance(100L);
        assertTrue(spaceship.isMatchesCriteria(spaceshipCriteria));
        spaceshipCriteria.setIsVacant(Role.COMMANDER);
        assertFalse(spaceship.isMatchesCriteria(spaceshipCriteria));
        spaceshipCriteria.setIsVacant(Role.FLIGHT_ENGINEER);
        assertTrue(spaceship.isMatchesCriteria(spaceshipCriteria));
        spaceshipCriteria.setFlightDistance(999999L);
        assertFalse(spaceship.isMatchesCriteria(spaceshipCriteria));
    }
}
