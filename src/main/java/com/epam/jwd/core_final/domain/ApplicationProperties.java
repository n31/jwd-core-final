package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.util.PropertyReaderUtil;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */
public class ApplicationProperties {
    String inputRootDir;
    String outputRootDir;
    String crewFileName;
    String missionsFileName;
    String spaceshipsFileName;
    Integer fileRefreshRate;
    String dateTimeFormat;

    public ApplicationProperties() {
        PropertyReaderUtil.loadProperties();
        this.inputRootDir = PropertyReaderUtil.getInputRootDir();
        this.outputRootDir = PropertyReaderUtil.getOutputRootDir();
        this.crewFileName = PropertyReaderUtil.getCrewFileName();
        this.missionsFileName = PropertyReaderUtil.getMissionsFileName();
        this.spaceshipsFileName = PropertyReaderUtil.getSpaceshipsFileName();
        this.fileRefreshRate = PropertyReaderUtil.getFileRefreshRate();
        this.dateTimeFormat = PropertyReaderUtil.getDateTimeFormat();
    }

    public String getInputRootDir() {
        return inputRootDir;
    }

    public String getOutputRootDir() {
        return outputRootDir;
    }

    public String getCrewFileName() {
        return crewFileName;
    }

    public String getMissionsFileName() {
        return missionsFileName;
    }

    public String getSpaceshipsFileName() {
        return spaceshipsFileName;
    }

    public Integer getFileRefreshRate() {
        return fileRefreshRate;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }
}
