package xjon.jexclusives.event;

import cpw.mods.fml.common.event.FMLServerStartedEvent;
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

public class PlayerEvents {

	private static int mode = -1;
	private static int downloadsMilestone = -1;
	private static int upToDownloads = -1;
	private static int currentDownloadAmount = -1;
	private static boolean fireworksEnabled = false;
	private static String message = "";

	@SubscribeEvent
	public void onServerStart(FMLServerStartedEvent event) throws Exception {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					if (!JEConfiguration.specialLoginsDisabled) {
						String remoteConfigUrl = JEConfiguration.urlForRemoteConfigs;
						String technicApiUrl = "http://api.technicpack.net/launcher/version/stable4";

						if (UrlValidator.isUrlValid(remoteConfigUrl)) 
						{
							JSONObject remoteConfigs = JsonReader.readJsonFromUrl(remoteConfigUrl);

							if (remoteConfigs.getBoolean("enabled")) 
							{
								message = remoteConfigs.getString("message");
								fireworksEnabled = remoteConfigs.getBoolean("fireworks");
								mode = remoteConfigs.getInt("mode");

								if (remoteConfigs.getInt("mode") == 1) 
								{
										//Store pack data if the mode is equal to 1
										if (!JEConfiguration.customModpackSlug.isEmpty() && UrlValidator.isUrlValid(technicApiUrl)) 
										{
											JSONObject packData = JsonReader.readJsonFromUrl("http://api.technicpack.net/modpack/" + JEConfiguration.customModpackSlug + "?build=" + JsonReader.readJsonFromUrl(technicApiUrl).getInt("build"));
											currentDownloadAmount = packData.getInt("downloads");
											
											if (currentDownloadAmount == 0 && JEConfiguration.customModpackSlug.equals("the-1710-pack") && UrlValidator.isUrlValid("http://the-1710-pack.com/repo?api=true"))
											{
												packData = JsonReader.readJsonFromUrl("http://the-1710-pack.com/repo?api=true");
												currentDownloadAmount = packData.getInt("downloads");
											}

											if (currentDownloadAmount >= remoteConfigs.getInt("x") && currentDownloadAmount <= remoteConfigs.getInt("y"))
											{
												downloadsMilestone = remoteConfigs.getInt("x");
												upToDownloads = remoteConfigs.getInt("y");
											}
										} 
										else 
										{
											Log.error("Either Technic's API is down, or Jon's Exclusives' remote configs' mode is set to 1, for Technic packs only, while local configs are set to non-Technic packs");
										}
								}
							}
						} else
						{
							Log.error("Jon's Exclusives' remote configs for selected URL are down (check if it's correct)");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();
	}
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent event) throws Exception
	{
		switch (mode) 
		{
			case 0: //0: basic message
				event.player.addChatMessage(new ChatComponentText(message));
				Firework.Fireworks(fireworksEnabled, new BlockCoord(event.player), event.player.dimension);
				break;

			case 1: //1: message + Thank you for x downloads! shows up until y downloads are reached. Will work only if Technic's API works
				if (upToDownloads != -1 && downloadsMilestone != -1 && currentDownloadAmount != -1 && currentDownloadAmount <= upToDownloads)
				{
					event.player.addChatMessage(new ChatComponentText(message + "Thank you for " + downloadsMilestone + " downloads!"));
					Firework.Fireworks(fireworksEnabled, new BlockCoord(event.player), event.player.dimension);
				}
				break;

			case 2: //message (for coloring) + player name + Have fun playing!
				event.player.addChatComponentMessage(new ChatComponentText(message + event.player.getDisplayName() + ", have fun playing!"));
				Firework.Fireworks(fireworksEnabled, new BlockCoord(event.player), event.player.dimension);
				break;

			default:
				Log.error("Jon's Exclusives' remote configs' mode is invalid");
				break;
		}
	}
	
}
