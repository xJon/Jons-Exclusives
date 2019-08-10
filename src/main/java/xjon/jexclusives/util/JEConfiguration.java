package xjon.jexclusives.util;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import xjon.jexclusives.JECore;

public class JEConfiguration {
	
	public static boolean specialLoginsDisabled;
	public static boolean specialLoginsFireworksDisabled;
	public static String customModpackSlug;
	public static String urlForRemoteConfigs;

	public static final String MAIN = Configuration.CATEGORY_GENERAL + Configuration.CATEGORY_SPLITTER + "Main";
	public static final String Logins = Configuration.CATEGORY_GENERAL + Configuration.CATEGORY_SPLITTER + "Logins";

	
	public static void syncConfig()
	{
		MinecraftForge.EVENT_BUS.register(JECore.instance);

		urlForRemoteConfigs = JECore.config.get(MAIN, "Configs URL", "", "The URL of the remote .json configs").setRequiresMcRestart(true).getString();
		customModpackSlug = JECore.config.get(MAIN, "Modpack Slug", "", "Slug name of the modpack (Leave blank for non-Technic packs)").setRequiresMcRestart(true).getString();
		
		specialLoginsDisabled = JECore.config.get(Logins, "Disable Special Logins", false, "Disables all special logins features").setRequiresMcRestart(true).getBoolean(false);
		specialLoginsFireworksDisabled = JECore.config.get(Logins, "Disable Fireworks", false, "Disables fireworks for special logins").setRequiresWorldRestart(true).getBoolean(false);
				
		if (JECore.config.hasChanged())
		{
			JECore.config.save();
		}
	}

}
