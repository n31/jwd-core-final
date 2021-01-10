package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReaderUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertyReaderUtil.class);
    private static final Properties properties = new Properties();
    private static String pathFromRepsitoryRoot = "src/main/resources";
    private PropertyReaderUtil() { }

    /**
     * try-with-resource using FileInputStream
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */
    public static void loadProperties() {
        final String propertiesFileName = "src/main/resources/application.properties";
        try (InputStream iStream = new FileInputStream(propertiesFileName)) {
            properties.load(iStream);
            if (!properties.isEmpty()) {
                logger.info("Properties file loaded");
            }
        } catch(IOException e) {
            logger.error("Properties file loading failed - " + e.getMessage());
            System.exit(1);
        }
    }

    public static String getInputRootDir() {
        return pathFromRepsitoryRoot + File.separator +  properties.getProperty("inputRootDir");
    }

    public static String getOutputRootDir() {
        return pathFromRepsitoryRoot + File.separator + properties.getProperty("outputRootDir");
    }

    public static String getCrewFileName() {
        return properties.getProperty("crewFileName");
    }

    public static String getMissionsFileName() {
        return properties.getProperty("missionsFileName");
    }

    public static String getSpaceshipsFileName() {
        return properties.getProperty("spaceshipsFileName");
    }

    public static int getFileRefreshRate() {
        return Integer.parseInt(properties.getProperty("fileRefreshRate"));
    }

    public static String getDateTimeFormat() {
        return properties.getProperty("dateTimeFormat");
    }
}
