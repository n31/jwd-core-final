package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InputUtil {
    private static final ApplicationProperties props = new ApplicationProperties();
    private static final Scanner scanner = new Scanner(System.in);
    private InputUtil() {
    }

    public static int handleChoice(int lowerBound, int upperBound) {
        boolean wrongChoice = true;
        int choice = -1;
        while (wrongChoice) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < lowerBound || choice > upperBound) {
                    System.out.printf("Lower and upper bounds: [%d, %d]\n", lowerBound, upperBound);
                    continue;
                }
                wrongChoice = false;
            } catch (Exception e) {
                System.out.println("Wrong choice");
            }
        }
        return choice;
    }

    public static LocalDateTime handleDate() {
        boolean wrongDate = true;
        LocalDateTime dateTime = null;
        do {
            try {
                String date = scanner.nextLine();
                String dateTimeFormat = props.getDateTimeFormat();
                dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(dateTimeFormat));
                wrongDate = false;
            } catch (Exception e) {
                System.out.printf("%s -> ", props.getDateTimeFormat());
                wrongDate = true;
            }
        } while (wrongDate);
        return dateTime;
    }

    public static String handleLine() {
        return scanner.nextLine();
    }
}
