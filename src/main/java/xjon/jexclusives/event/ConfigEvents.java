package xjon.jexclusives.event;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
