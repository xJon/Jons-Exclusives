package xjon.jexclusives.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import jadarstudios.developercapes.DevCapes;
import xjon.jexclusives.event.ClientPlayerEvents;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void initCapes()
	{
		DevCapes.getInstance().registerConfig("put final cape json file url here");
	}
	
	@Override
	public void clientPlayerEvents()
	{
		FMLCommonHandler.instance().bus().register(new ClientPlayerEvents());
	}
	
}
