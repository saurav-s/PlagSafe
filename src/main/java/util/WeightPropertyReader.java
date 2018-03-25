package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class WeightPropertyReader {
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";
    private static Logger logger = LoggerFactory.getLogger(WeightPropertyReader.class);
    private static WeightPropertyReader readerObject;

    private int renaming_weight;
    private int refactoring_weight;
    private int logical_weight;

    // creating a private constructor
    private WeightPropertyReader() {

    }

    // static method to get reader instance
    /**
     *
     * @return
     */
    public static WeightPropertyReader makeReaderObject() {
        if(readerObject == null)
            return new WeightPropertyReader();
        return readerObject;
    }

    /**
     *
     * @throws IOException
     */
    public void loadComparisonProperties() throws IOException {
        File configFile = new File(CONFIG_FILE_PATH);
        try{
            FileReader reader = new FileReader(configFile);
            Properties properties = new Properties();
            properties.load(reader);

            this.renaming_weight = Integer.parseInt(properties.getProperty("renaming.weight"));
            this.refactoring_weight = Integer.parseInt(properties.getProperty("refactoring.weight"));
            this.logical_weight = Integer.parseInt(properties.getProperty("logical.weight"));

        } catch(IOException e) {
            logger.error("Unable to read properties file" + e.getMessage());
        }
    }


    public int getRenaming_weight() {
        return renaming_weight;
    }

    public void setRenaming_weight(int renaming_weight) {
        this.renaming_weight = renaming_weight;
    }

    public int getRefactoring_weight() {
        return refactoring_weight;
    }

    public void setRefactoring_weight(int refactoring_weight) {
        this.refactoring_weight = refactoring_weight;
    }

    public int getLogical_weight() {
        return logical_weight;
    }

    public void setLogical_weight(int logical_weight) {
        this.logical_weight = logical_weight;
    }
}
