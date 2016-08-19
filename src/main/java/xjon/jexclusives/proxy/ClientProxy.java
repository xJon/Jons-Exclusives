package xjon.jexclusives.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import jadarstudios.developercapes.DevCapes;
import xjon.jexclusives.event.ClientPlayerEvents;
import xjon.jexclusives.util.JEConfiguration;
import xjon.jexclusives.util.JsonReader;
import xjon.jexclusives.util.Log;
import xjon.jexclusives.util.UrlValidator;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void initCapes()
	{
		if (UrlValidator.isUrlValid(JEConfiguration.urlForRemoteConfigs))
		{
			try
			{
				if (UrlValidator.isUrlValid(JsonReader.readJsonFromUrl(JEConfiguration.urlForRemoteConfigs).getString("capeurl")))
				{
					DevCapes.getInstance().registerConfig(JsonReader.readJsonFromUrl(JEConfiguration.urlForRemoteConfigs).getString("capeurl"));
				}
				else
				{
					Log.error("Jon's Exclusives' remote configs' capeurl is down");
				}
			}
			catch (Exception e) { }
		}
		else
		{
			Log.error("Jon's Exclusives' remote configs for selected URL are down (check if it's correct)");
		}
	}
	
	@Override
	public void clientPlayerEvents()
	{
		FMLCommonHandler.instance().bus().register(new ClientPlayerEvents());
	}
	
}
