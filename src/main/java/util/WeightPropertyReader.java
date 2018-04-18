package util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeightPropertyReader {

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
        this.renaming_weight = 2;
        this.refactoring_weight = 3;
        this.logical_weight = 4;

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
