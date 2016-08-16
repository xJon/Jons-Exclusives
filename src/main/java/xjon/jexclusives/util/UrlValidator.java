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
	        int time = connection.getResponseCode();        
	        System.out.println(url + "responded after" + time); 
	    }
	    
	    catch (Exception e)
	    {
	    	return false;
	    }
	    
	    return true;
	}

}
