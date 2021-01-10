package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Main {
    static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Application started");
        ApplicationMenu menu = null;
        try {
            menu = Application.start();
            while(true) {
                try {
                    Integer choice = menu.printAvailableOptions();
                    if (choice == 0) {
                        break;
                    }
                    menu.handleUserInput(choice);
                } catch (Exception e) {
                    logger.error("Something get wrong" + e.getMessage());
                }
            }
        } catch (InvalidStateException e) {
            logger.error("Application failed - " + e.getMessage());
        }
    }
}