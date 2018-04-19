package util;

import com.google.gson.Gson;
import com.phasec.plagsafe.models.MatchSnippet;
import com.phasec.plagsafe.models.Report;

import java.util.List;

public class DataFormatUtility {

    private DataFormatUtility() {

    }
    /**
     * get the JSON format of reports
     *
     * @param reports
     * @return JSON string representing list of Reports
     */
    public static String getJsonString(List<Report> reports) {
        Gson gson = new Gson();
        return gson.toJson(reports);
    }

    public static String getJsonString(MatchSnippet snippet) {
        Gson gson = new Gson();
        return gson.toJson(snippet);
    }

    /**
     * get the JSON format of string
     *
     * @param str string to be converted
     * @return JSON string value
     */
    public static String getJsonString(String str) {
        Gson gson = new Gson();
        return gson.toJson(str);
    }
}
