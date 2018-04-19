package util;

import java.util.ArrayList;
import java.util.List;

public class SnippetUtility {
    /**
     * finds the range of snippets from both algorithms
     *
     * @param codeOne first code
     * @param codeTwo second code
     * @return returns the list of ranges
     */
    public static List<Integer> findSnippetRanges(String codeOne, String codeTwo) {

        int m = codeOne.length();
        int n = codeTwo.length();
        List<Integer> commonCodes = commonSubSequence(codeOne, codeTwo, m, n);
        return commonCodes;
    }

    private static List<Integer> commonSubSequence(String codeOne, String codeTwo, int m, int n) {
        int[][] L = new int[m+1][n+1];

        // Following steps build L[m+1][n+1] in bottom up fashion. Note
        // that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1]
        for (int i=0; i<=m; i++)
        {
            for (int j=0; j<=n; j++)
            {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (codeOne.charAt(i-1) == codeTwo.charAt(j-1))
                    L[i][j] = L[i-1][j-1] + 1;
                else
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
            }
        }

        List<Integer> codeOneIndices = new ArrayList<>();
        List<Integer> codeTwoIndices = new ArrayList<>();

        int i = m, j = n;
        while(i > 0 && j > 0) {
            if(codeOne.charAt(i-1) == codeTwo.charAt(j-1)) {
                codeOneIndices.add(0,i-1);
                codeTwoIndices.add(0,j-1);
                i--; j--;
            } else if(L[i-1][j] > L[i][j-1]) {
                i--;
            } else{
                j--;
            }
        }
        codeOneIndices.addAll(codeTwoIndices);
        return codeOneIndices;
    }
}
