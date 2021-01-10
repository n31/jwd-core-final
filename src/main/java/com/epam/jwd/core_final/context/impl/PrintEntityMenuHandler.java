package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.EntityMenu;
import com.epam.jwd.core_final.service.impl.CrewCrud;
import com.epam.jwd.core_final.service.impl.MissionCrud;
import com.epam.jwd.core_final.service.impl.SpaceshipCrud;

public class PrintEntityMenuHandler implements EntityMenu {
    @Override
    public Object handleInput(Object o) {
        switch ((Integer) o) {
            case 1: {
                CrewCrud.getInstance().findAllCrewMembers()
                        .forEach(System.out::println);
                break;
            }
            case 2: {
                SpaceshipCrud.getInstance().findAllSpaceships()
                        .forEach(System.out::println);
                break;
            }
            case 3: {
                MissionCrud.getInstance().findAllMissions()
                        .forEach(System.out::println);
                break;
            }
        }
        return null;
    }
}
