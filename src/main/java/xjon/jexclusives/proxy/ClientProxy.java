package xjon.jexclusives.proxy;

import jadarstudios.developercapes.DevCapes;
import xjon.jexclusives.util.Log;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void InitCapes(String url)
	{
		DevCapes.getInstance().registerConfig(url);
		Log.info("debug");
	}
	
}
