package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.FilterEntityMenuHandler;
import com.epam.jwd.core_final.context.impl.JSONHandler;
import com.epam.jwd.core_final.context.impl.MissionEntityMenuHandler;
import com.epam.jwd.core_final.context.impl.MissionUpdateHandler;
import com.epam.jwd.core_final.context.impl.PrintEntityMenuHandler;
import com.epam.jwd.core_final.util.CriteriaUtil;
import com.epam.jwd.core_final.util.InputUtil;

import java.util.function.Function;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();

    default Integer printAvailableOptions() {
        System.out.println("1 - Print entities");
        System.out.println("2 - Find by criteria");
        System.out.println("3 - Create a new mission");
        System.out.println("4 - Make a JSON file of missions");
        System.out.println("5 - Update a mission");
        System.out.println("0 - Exit");
        return InputUtil.handleChoice(0, 5);
    }

    default Object handleUserInput(Object o) {
        int choice = (int) o;

        switch (choice) {
            case 0: {
                break;
            }
            case 1: {
                EntityMenu entityMenu = new PrintEntityMenuHandler();
                ((Function<Object, Object>) entityMenu::show)
                        .andThen(entityMenu::handleInput)
                        .apply(entityMenu);
                break;
            }
            case 2: {
                EntityMenu entityMenu = new FilterEntityMenuHandler();
                ((Function<Object, Object>) entityMenu::show)
                        .andThen(CriteriaUtil::menu)
                        .andThen(entityMenu::handleInput)
                        .apply(entityMenu);
                break;
            }
            case 3: {
                System.out.println("3");
                EntityMenu entityMenu = new MissionEntityMenuHandler();
                ((Function<Object, Object>) entityMenu::handleInput)
                        .apply(entityMenu);
                break;
            }
            case 4: {
                EntityMenu entityMenu = new JSONHandler();
                ((Function<Object, Object>) entityMenu::show)
                        .andThen(entityMenu::handleInput)
                        .apply(entityMenu);
                break;
            }
            case 5: {
                EntityMenu entityMenu = new MissionUpdateHandler();
                ((Function<Object, Object>) entityMenu::show)
                        .andThen(entityMenu::handleInput)
                        .apply(entityMenu);
                break;
            }
        }
        return null;
    }
}
