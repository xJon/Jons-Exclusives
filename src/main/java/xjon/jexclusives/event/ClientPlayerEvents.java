package xjon.jexclusives.event;

import org.json.JSONException;
import org.json.JSONObject;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import xjon.jexclusives.util.JEConfiguration;
import xjon.jexclusives.util.JsonReader;
import xjon.jexclusives.util.Log;
import xjon.jexclusives.util.UrlValidator;


public class ClientPlayerEvents {
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent event) throws Exception
	{
		if (!JEConfiguration.specialLoginsDisabled)
		{
			String remoteConfigUrl =  JEConfiguration.urlForRemoteConfigs;
			String technicApiUrl = "http://api.technicpack.net/launcher/version/stable4";
			
			if (UrlValidator.isUrlValid(remoteConfigUrl) && UrlValidator.isUrlValid(technicApiUrl))
			{
				JSONObject remoteConfigs = JsonReader.readJsonFromUrl(remoteConfigUrl);
				
				if (remoteConfigs.getBoolean("enabled"))
				{
					String message = remoteConfigs.getString("message");
					boolean fireworks = remoteConfigs.getBoolean("fireworks");
				
						switch (remoteConfigs.getInt("args"))
						{
						case 0: //0: Basic message
							event.player.addChatMessage(new ChatComponentText(message));
							if (fireworks && !JEConfiguration.specialLoginsFireworksDisabled)
							{
								//Fireworks here
							}
							break;
							
						case 1: //1: Special message for surpassing x downloads, to show up until y downloads are reached. Will work only if Technic's API work
							JSONObject packData = JsonReader.readJsonFromUrl("http://api.technicpack.net/modpack/" + JEConfiguration.customModpackSlug + "?build=" + JsonReader.readJsonFromUrl(technicApiUrl).getInt("build"));
							if (packData.getInt("downloads") >= remoteConfigs.getInt("x") && packData.getInt("downloads") <= remoteConfigs.getInt("y"))
								{
									event.player.addChatMessage(new ChatComponentText(message + "Thank you for " + remoteConfigs.getInt("x") + " downloads!"));
									Log.info("DEBUG");
									if (fireworks && !JEConfiguration.specialLoginsFireworksDisabled)
										{
											//Fireworks here
										}
									}
							break;
							
						default:
							Log.error("Jon's Exclusives remote configs' args for the set modpack slug are invalid");
							break;
						}
				}
			}
			else
			{
				Log.error("Either Jon's Exclusives' remote configs for selected pack slug are down (check if it's correct), or Technic's API is down (or both)");
			}
		}
	}
	
}
