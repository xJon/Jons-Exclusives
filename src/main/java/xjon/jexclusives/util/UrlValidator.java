package xjon.jexclusives.util;

import java.net.HttpURLConnection;
import java.net.URL;

public class UrlValidator {
	
	public static boolean isUrlValid(String url)
	{
	    HttpURLConnection connection = null;
	    try
	    {
	        URL myurl = new URL(url);        
	        connection = (HttpURLConnection) myurl.openConnection(); 
	        connection.setRequestMethod("HEAD");         
	        int code = connection.getResponseCode();        
	        Log.info(url + " returned respond code " + code); 
	    }
	    
	    catch (Exception e)
	    {
	    	return false;
	    }
	    
	    return true;
	}
	
	public static boolean areUrlsValid(String[] urls)
	{
	    for (int i = 0; i < urls.length; ++i)
	    {
	    	if (!isUrlValid(urls[i]))
	    		return false;
	    }
	    
	    return true;
	}

}
