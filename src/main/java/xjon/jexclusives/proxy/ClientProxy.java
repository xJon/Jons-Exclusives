package xjon.jexclusives.proxy;

import jadarstudios.developercapes.DevCapes;
import xjon.jexclusives.util.Log;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void InitCapes()
	{
		DevCapes.getInstance().registerConfig("put final url here");
	}
	
}
