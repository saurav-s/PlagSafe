package util;

public class LevenshteinDistanceGeneratorUtility {
	
	public static int getLevenshteinDistance(String str1, String str2) {

		int[][] dp = new int[str1.length() + 1][str2.length() + 1];

		dp[0][0] = 0;
		for (int i = 1; i < dp.length; i++) {
			dp[i][0] = i;
		}

		for (int j = 1; j < dp[0].length; j++) {
			dp[0][j] = j;
		}

		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
				}
			}
		}
		return dp[dp.length - 1][dp[0].length - 1];

	}

}
