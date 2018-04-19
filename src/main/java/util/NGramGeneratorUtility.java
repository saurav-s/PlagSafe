package util;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an utility for generation of N-grams from an input String
 * @author Tridiv
 *
 */
public class NGramGeneratorUtility {

	private NGramGeneratorUtility() {

	}

	/**
	 * Generates a list of N-grams from the input string and N
	 * @param str
	 * @param n
	 * @return List of n-grams
	 */
	public static List<String> getNGramList(String str, int n){
		
		str = str.replaceAll("\\s+", "");
		char [] arr = str.toCharArray();
		List<String> strList = new ArrayList<>();
		for(int i=0; i<arr.length; i++){
			StringBuilder sb = new StringBuilder();
			for(int j=0; j<n; j++){
				if(i+j>=arr.length){
					break;
				}
				char c = arr[i+j];
				sb.append(c);
			}
			strList.add(sb.toString());
		}
		
		return strList;
		
		
	}
}
