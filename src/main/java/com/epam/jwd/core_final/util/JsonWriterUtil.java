package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.FlightMission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWriterUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonWriterUtil.class);
    private static ApplicationProperties props = new ApplicationProperties();

    public static void writeJSON(FlightMission flightMission) {
        try {
            File file = new File(props.getOutputRootDir() );
            file.mkdirs();
            File jsonFile = new File(props.getOutputRootDir() + File.separator + flightMission.getName() + ".json");
            if (jsonFile.createNewFile()) {
                FileWriter fileWriter = new FileWriter(jsonFile);
                fileWriter.write(flightMission.toJSON());
                fileWriter.close();
            }
        } catch(IOException e) {
            logger.error(e.getMessage());
        }
    }
}
