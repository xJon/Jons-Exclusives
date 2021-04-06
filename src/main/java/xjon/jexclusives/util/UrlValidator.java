package xjon.jexclusives.util;

import java.net.HttpURLConnection;
import java.net.URL;

public class UrlValidator {

    public static boolean isUrlValid(String url) {
        HttpURLConnection connection;
        try {
            URL myurl = new URL(url);
            connection = (HttpURLConnection) myurl.openConnection();
            connection.setRequestMethod("HEAD");
            int code = connection.getResponseCode();
            Log.info(url + " returned respond code " + code);
            if (code == 404) {
                return false;
            }
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
