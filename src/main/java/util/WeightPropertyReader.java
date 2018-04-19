package util;

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
     * reads weight values
     */
    public void loadComparisonProperties() {
        this.renamingWeight = 2;
        this.refactoringWeight = 3;
        this.logicalWeight = 4;

    }


    public int getRenamingWeight() {
        return renamingWeight;
    }

    public void setRenamingWeight(int renamingWeight) {
        this.renamingWeight = renamingWeight;
    }

    public int getRefactoringWeight() {
        return refactoringWeight;
    }

    public void setRefactoringWeight(int refactoringWeight) {
        this.refactoringWeight = refactoringWeight;
    }

    public int getLogicalWeight() {
        return logicalWeight;
    }

    public void setLogicalWeight(int logicalWeight) {
        this.logicalWeight = logicalWeight;
    }
}
