package xjon.jexclusives.util;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.config.Configuration;
import xjon.jexclusives.JECore;

public class JEConfiguration {
	
	public static boolean specialLoginsDisabled;
	public static boolean specialLoginsFireworksDisabled;
	public static String customModpackSlug;
	public static String urlForRemoteConfigs;
	
	public static boolean SPECIAL_LOGINS_ENABLED_DEFAULT = false;
	public static boolean SPECIAL_LOGINS_FIREWORKS_ENABLED_DEFAULT = false;
	public static String CUSTOM_MODPACK_SLUG_DEFAULT = "";
	public static String URL_FOR_REMOTE_CONFIGS_DEFAULT = "";
	
	public static final String SPECIAL_LOGINS_ENABLED_NAME = "Disables all special logins features";
	public static final String SPECIAL_LOGINS_FIREWORDS_NAME = "Forces fireworks to be disabled for special logins";
	public static final String CUSTOM_MODPACK_SLUG_NAME = "Slug name of the modpack (Leave blank for non-Technic packs)";
	public static final String URL_FOR_REMOTE_CONFIGS_NAME = "The URL of the remote .json configs";

	public static final String MAIN = Configuration.CATEGORY_GENERAL + Configuration.CATEGORY_SPLITTER + "Main";

	public static void syncConfig()
	{
		FMLCommonHandler.instance().bus().register(JECore.instance);
		
		specialLoginsDisabled = JECore.config.get(MAIN, SPECIAL_LOGINS_ENABLED_NAME, SPECIAL_LOGINS_ENABLED_DEFAULT).getBoolean(SPECIAL_LOGINS_ENABLED_DEFAULT);
		specialLoginsFireworksDisabled = JECore.config.get(MAIN, SPECIAL_LOGINS_FIREWORDS_NAME, SPECIAL_LOGINS_FIREWORKS_ENABLED_DEFAULT).getBoolean(SPECIAL_LOGINS_FIREWORKS_ENABLED_DEFAULT);
		customModpackSlug = JECore.config.get(MAIN, CUSTOM_MODPACK_SLUG_NAME, CUSTOM_MODPACK_SLUG_DEFAULT).getString();
		urlForRemoteConfigs = JECore.config.get(MAIN, URL_FOR_REMOTE_CONFIGS_NAME, URL_FOR_REMOTE_CONFIGS_DEFAULT).getString();
		
		if (JECore.config.hasChanged())
		{
			JECore.config.save();
		}
	}

}
