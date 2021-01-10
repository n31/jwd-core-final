package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.EntityMenu;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.service.impl.MissionCrud;
import com.epam.jwd.core_final.util.InputUtil;
import com.epam.jwd.core_final.util.JsonWriterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class JSONHandler implements EntityMenu {
    private static final Logger log = LoggerFactory.getLogger(JSONHandler.class);

    @Override
    public Object show(Object o) {
        List<FlightMission> missions = MissionCrud.getInstance().findAllMissions();
        missions.forEach(System.out::println);
        System.out.print("Enter mission id: ");
        return InputUtil.handleChoice(1, missions.size());
    }

    @Override
    public Object handleInput(Object o) {
        int id = (int)o;
        JsonWriterUtil.writeJSON(MissionCrud.getInstance().findAllMissions().get(id - 1));
        return true;
    }
}
