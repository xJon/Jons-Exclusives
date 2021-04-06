package xjon.jexclusives.util;

import java.net.HttpURLConnection;
import java.net.URL;

public class UrlValidator {

    public static boolean isUrlValid(String urlString) {
        HttpURLConnection connection;

        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");

            int code = connection.getResponseCode();
            Log.info(urlString + " returned respond code " + code);

            int codeX = code / 100;
            if (codeX == 4 || codeX == 5) return false;
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static boolean areUrlsValid(String[] urls) {
        for (String url : urls) {
            if (!isUrlValid(url))
                return false;
        }

        return true;
    }

}
