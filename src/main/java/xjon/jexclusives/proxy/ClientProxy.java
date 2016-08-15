package xjon.jexclusives.proxy;

import jadarstudios.developercapes.DevCapes;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void InitCapes(String url)
	{
		DevCapes.getInstance().registerConfig(url);
	}
	
}
