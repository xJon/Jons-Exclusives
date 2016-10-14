package xjon.jexclusives.event;

import java.net.URL;
import java.util.HashMap;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;

import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import xjon.jexclusives.util.BlockCoord;
import xjon.jexclusives.util.Firework;
import xjon.jexclusives.util.JEConfiguration;
import xjon.jexclusives.util.Log;
import xjon.jexclusives.util.UrlValidator;

public class PlayerEvents {

	private static int mode = -1;
	private static int downloadsMilestone = -1;
	private static int upToDownloads = -1;
	private static int currentDownloadAmount = -1;
	private static boolean fireworksEnabled = false;
	private static String message = "";


	public static class PackJson {
		boolean enabled;
		String message;
		boolean fireworks;
		int mode;
		int x;
		int y;
		public String capeurl;
	}

	static class TechnicApiData {
		int build;
		HashMap<String, String> url;
	}

	static class TechnicModpackApiData {
		int downloads;
	}

	public static void fetchData() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					if (!JEConfiguration.specialLoginsDisabled) {
						String remoteConfigUrl = JEConfiguration.urlForRemoteConfigs;
						String technicApiUrl = "http://api.technicpack.net/launcher/version/stable4";

						if (UrlValidator.isUrlValid(remoteConfigUrl)) 
						{
							PackJson remoteConfigs = new Gson().fromJson(Resources.toString(new URL(remoteConfigUrl), Charsets.UTF_8), PackJson.class);

							if (remoteConfigs.enabled)
							{
								message = remoteConfigs.message;
								fireworksEnabled = remoteConfigs.fireworks;
								mode = remoteConfigs.mode;

								if (remoteConfigs.mode == 1)
								{
										//Store pack data if the mode is equal to 1
										if (!JEConfiguration.customModpackSlug.isEmpty() && UrlValidator.isUrlValid(technicApiUrl)) 
										{
											TechnicApiData technicApiData = new Gson().fromJson(Resources.toString(new URL(technicApiUrl), Charsets.UTF_8), TechnicApiData.class);
											TechnicModpackApiData packData = new Gson().fromJson(Resources.toString(new URL("http://api.technicpack.net/modpack/" + JEConfiguration.customModpackSlug + "?build=" + technicApiData.build), Charsets.UTF_8), TechnicModpackApiData.class);
											
											currentDownloadAmount = packData.downloads;
											
											//Use backup if needed
											if (currentDownloadAmount == 0 && JEConfiguration.customModpackSlug.equals("the-1710-pack") && UrlValidator.isUrlValid("http://the-1710-pack.com/repo?api=true"))
											{
												packData = new Gson().fromJson(Resources.toString(new URL("http://the-1710-pack.com/repo?api=true"), Charsets.UTF_8), TechnicModpackApiData.class);
												currentDownloadAmount = packData.downloads;
											}

											if (currentDownloadAmount >= remoteConfigs.x && currentDownloadAmount <= remoteConfigs.y)
											{
												downloadsMilestone = remoteConfigs.x;
												upToDownloads = remoteConfigs.y;
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
