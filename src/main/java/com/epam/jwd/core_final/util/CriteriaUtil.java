package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.context.impl.MissionUpdateHandler;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.epam.jwd.core_final.domain.Rank.resolveRankById;
import static com.epam.jwd.core_final.domain.Role.resolveRoleById;

public class CriteriaUtil {
    private CriteriaUtil() {
    }

    public static Object menu(Object o) {
        Object map = null;
        switch ((Integer) o) {
            case 1: {
                // CrewMember menu
                do {
                    System.out.println("1 - Role");
                    System.out.println("2 - Rank");
                    System.out.println("3 - isReadyForNextMission");
                    System.out.println("Choose options you want to find members by");
                    System.out.println("Separate numbers using spaces");
                    try {
                        Scanner scanner = new Scanner(System.in);
                        String line = scanner.nextLine();
                        map = handleCrewMemberOptions(line);
                    } catch (Exception e) {
                        map = null;
                    }
                } while (map == null);
                break;
            }
            case 2: {
                do {
                    System.out.println("1 - flightDistance");
                    System.out.println("2 - isReadyForNextMission");
                    System.out.println("Choose options you want to find members by");
                    System.out.println("Separate numbers using spaces");
                    try {
                        Scanner scanner = new Scanner(System.in);
                        String line = scanner.nextLine();
                        map = handleSpaceshipOptions(line);
                    } catch (Exception e) {
                        map = null;
                    }
                } while (map == null);
                break;
            }
            case 3: {
                System.out.println("1 - Name");
                System.out.println("2 - distance");
                System.out.println("3 - missionResult");
                System.out.println("Choose options you want to find members by");
                System.out.println("Separate numbers using spaces");
                try {
                    Scanner scanner = new Scanner(System.in);
                    String line = scanner.nextLine();
                    map = handleMissionOptions(line);
                } catch (Exception e) {
                    map = null;
                }
            }
        }
        return map;
    }

    private static Object handleCrewMemberOptions(Object o) {
        List<Integer> list = separateArgs(o);
        Map<String, Object> choices = new HashMap<>();
        choices.put("crewMemberOptions", true);
        for (Integer i : list) {
            switch (i) {
                case 1: {
                    // Role
                    System.out.println("What role are you willing to get?");
                    System.out.println("1 - MISSION_SPECIALIST");
                    System.out.println("2 - FLIGHT_ENGINEER");
                    System.out.println("3 - PILOT");
                    System.out.println("4 - COMMANDER");
                    long choice = InputUtil.handleChoice(1, 4);
                    Role role = resolveRoleById(choice);
                    choices.put("role", role);
                    break;
                }
                case 2: {
                    System.out.println("What rank are you willing to get?");
                    System.out.println("1 - TRAINEE");
                    System.out.println("2 - SECOND_OFFICER");
                    System.out.println("3 - FIRST_OFFICER");
                    System.out.println("4 - CAPTAIN");
                    long choice = InputUtil.handleChoice(1, 4);
                    Rank rank = resolveRankById(choice);
                    choices.put("rank", rank);
                    break;
                }
                case 3: {
                    System.out.println("Is a crewMember ready for the next mission? (1 - yes/0 - no)");
                    long choice = InputUtil.handleChoice(0, 1);
                    choices.put("isReadyForNextMissions", choice != 0);
                    break;
                }
                default: {
                    return null;
                }
            }
        }
        return choices;
    }

    private static Object handleSpaceshipOptions(Object o) {
        List<Integer> list = separateArgs(o);
        Map<String, Object> choices = new HashMap<>();
        choices.put("spaceshipOptions", true);
        for (Integer i : list) {
            switch (i) {
                case 1: {
                    // Role
                    System.out.println("What distance are you willing to get? (Number)");
                    long choice = InputUtil.handleChoice(0, Integer.MAX_VALUE);
                    choices.put("flightDistance", choice);
                    break;
                }
                case 2: {
                    System.out.println("Is a spaceship ready for the next mission? (1 - yes/0 - no)");
                    long choice = InputUtil.handleChoice(0, 1);
                    choices.put("isReadyForNextMissions", choice != 0);
                    break;
                }
                default: {
                    return null;
                }
            }
        }
        return choices;
    }

    private static Object handleMissionOptions(Object o) {
        List<Integer> args = separateArgs(o);
        Map<String, Object> choices = new HashMap<>();
        choices.put("missionOptions", true);
        for (Integer arg : args) {
            switch (arg) {
                case 1: {
                    System.out.println("Input a mission's name:");
                    String name = InputUtil.handleLine();
                    choices.put("name", name);
                    break;
                }
                case 2: {
                    System.out.println("Enter a distance:");
                    long distance = InputUtil.handleChoice(0, Integer.MAX_VALUE);
                    choices.put("distance", distance);
                    break;
                }
                case 3: {
                    System.out.println("Enter a mission result");
                    System.out.println("1 - CANCELLED");
                    System.out.println("2 - FAILED");
                    System.out.println("3 - PLANNED");
                    System.out.println("4 - IN_PROGRESS");
                    System.out.println("5 - COMPLETED");
                    int choice = InputUtil.handleChoice(1, 5);
                    MissionResult result = handleMissionResultByChoice((long)choice);
                    choices.put("missionResult", result);
                    break;
                }
                default: {
                    return null;
                }
            }
        }
        return choices;
    }

    public static List<Integer> separateArgs(Object o) {
        String[] args = ((String) o).split(" ");
        return Arrays.stream(args)
                .map(String::trim)
                .map(Integer::valueOf)
                .distinct()
                .collect(Collectors.toList());
    }

    private static MissionResult handleMissionResultByChoice(Long choice) {
        return MissionUpdateHandler.extractMissionResult(choice);
    }
}
