package xjon.jexclusives.event;

import org.json.JSONObject;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraft.util.ChatComponentText;
import xjon.jexclusives.util.BlockCoord;
import xjon.jexclusives.util.Firework;
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
			
			if (UrlValidator.isUrlValid(remoteConfigUrl))
			{
				JSONObject remoteConfigs = JsonReader.readJsonFromUrl(remoteConfigUrl);
				
				if (remoteConfigs.getBoolean("enabled"))
				{
					String message = remoteConfigs.getString("message");
					boolean fireworksEnabled = remoteConfigs.getBoolean("fireworks");
				
						switch (remoteConfigs.getInt("mode"))
						{
						case 0: //0: basic message
							event.player.addChatMessage(new ChatComponentText(message));
							Firework.Fireworks(fireworksEnabled, new BlockCoord(event.player), event.player.dimension);
							break;
							
						case 1: //1: message + Thank you for x downloads! shows up until y downloads are reached. Will work only if Technic's API works
							if (!JEConfiguration.customModpackSlug.isEmpty() && UrlValidator.isUrlValid(technicApiUrl))
							{
								JSONObject packData = JsonReader.readJsonFromUrl("http://api.technicpack.net/modpack/" + JEConfiguration.customModpackSlug + "?build=" + JsonReader.readJsonFromUrl(technicApiUrl).getInt("build"));
								if (JEConfiguration.customModpackSlug == "the-1710-pack" && packData.getInt("downloads") == 0 && UrlValidator.isUrlValid("http://the-1710-pack.com/repo?api=true"))
								{
									packData = JsonReader.readJsonFromUrl("http://the-1710-pack.com/repo?api=true");
								}

								if (packData.getInt("downloads") >= remoteConfigs.getInt("x") && packData.getInt("downloads") <= remoteConfigs.getInt("y"))
								{
									event.player.addChatMessage(new ChatComponentText(message + "Thank you for " + remoteConfigs.getInt("x") + " downloads!"));
									Firework.Fireworks(fireworksEnabled, new BlockCoord(event.player), event.player.dimension);
								}
							}
							else
							{
								Log.error("Either Technic's API is down, or Jon's Exclusives' remote configs' mode is set to 1, for Technic packs only, while local configs are set to non-Technic packs");
							}
							break;
						
						case 2: //message (for coloring) + player name + Have fun playing!
							event.player.addChatComponentMessage(new ChatComponentText(message + event.player.getDisplayName() + ", Have fun playing!"));
							Firework.Fireworks(fireworksEnabled, new BlockCoord(event.player), event.player.dimension);
							break;
							
						default:
							Log.error("Jon's Exclusives' remote configs' mode is invalid");
							break;
						}
				}
			}
			else
			{
				Log.error("Jon's Exclusives' remote configs for selected URL are down (check if it's correct)");
			}
		}
	}
	
}
