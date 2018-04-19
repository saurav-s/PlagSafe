package util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeightPropertyReader {

    private static WeightPropertyReader readerObject;

    private int renamingWeight;
    private int refactoringWeight;
    private int logicalWeight;

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
        this.renamingWeight = 2;
        this.refactoringWeight = 3;
        this.logicalWeight = 4;

    }


    public int getRenamingWeight() {
        return renamingWeight;
    }

    public void setRenamingWeight(int renaming_weight) {
        this.renamingWeight = renaming_weight;
    }

    public int getRefactoringWeight() {
        return refactoringWeight;
    }

    public void setRefactoringWeight(int refactoring_weight) {
        this.refactoringWeight = refactoring_weight;
    }

    public int getLogicalWeight() {
        return logicalWeight;
    }

    public void setLogicalWeight(int logical_weight) {
        this.logicalWeight = logical_weight;
    }
}
