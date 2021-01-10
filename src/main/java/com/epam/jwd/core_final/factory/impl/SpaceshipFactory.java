package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.util.HashMap;
import java.util.Map;

public class SpaceshipFactory implements EntityFactory {
    private Long lastId = 1L;

    private SpaceshipFactory() {}

    private static SpaceshipFactory instance;
    public static SpaceshipFactory getInstance() {
        if (instance == null) {
            instance = new SpaceshipFactory();
        }
        return instance;
    }

    @Override
    public Spaceship create(Object... args) {
        String name = (String)args[0];
        Long distance = (Long)args[1];
        Map<Role, Short> crew = rawCrewToMap((String)args[2]);
        return new Spaceship(lastId++, name, crew, distance);
    }

    private Map<Role, Short> rawCrewToMap(String raw) {
        Map<Role, Short> spaceshipCrew = new HashMap<>();
        raw = raw.substring(1, raw.length() - 1);
        String[] rawData = raw.split(",");
        for (String data : rawData) {
            Role role = Role.resolveRoleById(Long.parseLong(String.valueOf(data.charAt(0))));
            Short number = Short.parseShort(String.valueOf(data.charAt(2)));
            spaceshipCrew.put(role, number);
        }
        return spaceshipCrew;
    }
}
