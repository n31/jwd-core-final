package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.function.Supplier;

public interface Application {

    static ApplicationMenu start() throws InvalidStateException {
        final Supplier<ApplicationContext> applicationContextSupplier = NassaContext::getInstance; // todo
        final NassaContext nassaContext = NassaContext.getInstance();
        nassaContext.init();
        return applicationContextSupplier::get;





//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(nassaContext.getProps().getDateTimeFormat());
//        Scanner scan = new Scanner(System.in);
//
//        String name = "n";
//        String startDateString;
//        String endDateString;
//        LocalDate startDate = LocalDate.parse("2020-01-10 20:00:00", formatter);
//        LocalDate endDate = LocalDate.parse("2020-01-10 20:00:00", formatter);
//        Long distance = 1000L;
//        MissionResult missionResult = MissionResult.FAILED;
//
//        System.out.print("enter name: ");
//        name = scan.nextLine();
//
//        System.out.print("enter start date " + nassaContext.getProps().getDateTimeFormat() + " : ");
//        startDateString = scan.nextLine();
//        startDate = LocalDate.parse(startDateString, formatter);
//
//        System.out.print("enter end date " + nassaContext.getProps().getDateTimeFormat() + " : ");
//        endDateString = scan.nextLine();
//        endDate = LocalDate.parse(endDateString, formatter);
//
//        System.out.print("enter distance: ");
//        distance = Long.parseLong(scan.nextLine());
//
//        Arrays.asList(MissionResult.values()).forEach(e -> System.out.println(e.toString()));
//        int i = Integer.parseInt(scan.nextLine());
//        switch (i) {
//            case 1 -> missionResult = MissionResult.CANCELLED;
//            case 2 -> missionResult = MissionResult.COMPLETED;
//            case 3 -> missionResult = MissionResult.FAILED;
//            case 4 -> missionResult = MissionResult.IN_PROGRESS;
//            case 5 -> missionResult = MissionResult.PLANNED;
//            default -> missionResult = MissionResult.CANCELLED;
//        }
//
//
//        FlightMissionFactory missionFactory = new FlightMissionFactory();
//        MissionCrud missionCrud = MissionCrud.getInstance();
//        missionCrud.createMission(missionFactory.create(name, startDate, endDate, distance, missionResult));
//
//        missionCrud.findAllMissions().forEach(JsonWriterUtil::writeJSON);
    }
}
