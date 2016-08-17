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
			String[] usedUrls = new String[3];
			usedUrls[0] = "http://api.enderplay.com/pack/" + JEConfiguration.customModpackSlug + ".json";
			usedUrls[1] = "http://the-1710-pack.com/cache/pack_platform.json";
			usedUrls[2] = "http://mianite.us/cache/pack_platform.json";
			
			if (UrlValidator.areUrlsValid(usedUrls))
			{
				JSONObject remoteConfigs = JsonReader.readJsonFromUrl(usedUrls[0]);
				int args = remoteConfigs.getInt("args");
				String message = remoteConfigs.getString("message");
				boolean fireworks = remoteConfigs.getBoolean("fireworks");
				
					switch (args)
					{
					case 0: //Basic message \/
						event.player.addChatMessage(new ChatComponentText(message));
						if (fireworks && !JEConfiguration.specialLoginsFireworksDisabled)
						{
							//Fireworks here
						}
						break;
						
					case 1: //Custom feature \/
						switch (JEConfiguration.customModpackSlug.toLowerCase())
						{
						case "the-1710-pack":
							JSONObject packData1 = JsonReader.readJsonFromUrl("http://the-1710-pack.com/cache/pack_platform.json");
							if (packData1.getInt("downloads") >= 400000 && packData1.getInt("downloads") <= 410000)
								{
									event.player.addChatMessage(new ChatComponentText("§2§lThank you for 400,000 downloads!"));
									if (fireworks && !JEConfiguration.specialLoginsFireworksDisabled)
									{
										//Fireworks here
									}
								}
							break;
						
						case "mianite":
							JSONObject packData2 = JsonReader.readJsonFromUrl("http://mianite.us/cache/pack_platform.json");
							if (packData2.getInt("downloads") >= 350000 && packData2.getInt("downloads") <= 355000)
								{
									event.player.addChatMessage(new ChatComponentText("§2§lThank you for 350,000 downloads!"));
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
						break;
						
					default:
						Log.error("Jon's Exclusives remote configs' args for the set modpack slug are invalid");
						break;
					}
			}
			else if (!JEConfiguration.customModpackSlug.isEmpty())
			{
				Log.error("Either the configurable modpack slug is invalid or Jon's Exclusives' remote configs are offline");
			}
		}
	}
	
}
