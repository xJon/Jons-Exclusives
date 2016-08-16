package xjon.jexclusives.event;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import xjon.jexclusives.util.JEConfiguration;
import xjon.jexclusives.util.Log;
import xjon.jexclusives.util.Reference;

public class ConfigEvents {

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.modID.equals(Reference.MOD_ID))
		{
			JEConfiguration.syncConfig();
			Log.info("Configuration changed");
		}
	}
	
}
