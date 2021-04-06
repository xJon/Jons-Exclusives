package xjon.jexclusives.util;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.config.Configuration;
import xjon.jexclusives.JECore;

public class JEConfiguration {

    public static final String MAIN = Configuration.CATEGORY_GENERAL + Configuration.CATEGORY_SPLITTER + "Main";

    public static final String SPECIAL_LOGINS_DISABLED_NAME = "Disables all special logins features";
    public static final String SPECIAL_LOGINS_FIREWORKS_DISABLED_NAME = "Forces fireworks to be disabled for special logins";
    public static final String RECOMMENDED_SERVER_HOST_BUTTON_DISABLED_NAME = "Hides the recommended server host button";
    public static final String CUSTOM_MODPACK_SLUG_NAME = "Slug name of the modpack (Leave blank for non-Technic packs)";
    public static final String REMOTE_CONFIG_URL_NAME = "The URL of the remote config";

    public static boolean specialLoginsDisabled;
    public static boolean specialLoginsFireworksDisabled;
    public static boolean recommendedServerHostButtonDisabled;
    public static String customModpackSlug;
    public static String remoteConfigUrl;

    public static boolean SPECIAL_LOGINS_DISABLED_DEFAULT = false;
    public static boolean SPECIAL_LOGINS_FIREWORKS_DISABLED_DEFAULT = false;
    public static boolean RECOMMENDED_SERVER_HOST_BUTTON_DISABLED_DEFAULT = false;
    public static String CUSTOM_MODPACK_SLUG_DEFAULT = "";
    public static String REMOTE_CONFIG_URL_DEFAULT = "";

    public static void syncConfig() {
        FMLCommonHandler.instance().bus().register(JECore.instance);

        specialLoginsDisabled = JECore.config.get(MAIN, SPECIAL_LOGINS_DISABLED_NAME, SPECIAL_LOGINS_DISABLED_DEFAULT).getBoolean(SPECIAL_LOGINS_DISABLED_DEFAULT);
        specialLoginsFireworksDisabled = JECore.config.get(MAIN, SPECIAL_LOGINS_FIREWORKS_DISABLED_NAME, SPECIAL_LOGINS_FIREWORKS_DISABLED_DEFAULT).getBoolean(SPECIAL_LOGINS_FIREWORKS_DISABLED_DEFAULT);
        recommendedServerHostButtonDisabled = JECore.config.get(MAIN, RECOMMENDED_SERVER_HOST_BUTTON_DISABLED_NAME, RECOMMENDED_SERVER_HOST_BUTTON_DISABLED_DEFAULT).getBoolean(RECOMMENDED_SERVER_HOST_BUTTON_DISABLED_DEFAULT);
        customModpackSlug = JECore.config.get(MAIN, CUSTOM_MODPACK_SLUG_NAME, CUSTOM_MODPACK_SLUG_DEFAULT).getString();
        remoteConfigUrl = JECore.config.get(MAIN, REMOTE_CONFIG_URL_NAME, REMOTE_CONFIG_URL_DEFAULT).getString();

        if (JECore.config.hasChanged()) JECore.config.save();
    }

}
