package xjon.jexclusives.util;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import xjon.jexclusives.JECore;

public class JEConfiguration {

    public static final String MAIN = Configuration.CATEGORY_GENERAL + Configuration.CATEGORY_SPLITTER + "Main";
    public static final String Logins = Configuration.CATEGORY_GENERAL + Configuration.CATEGORY_SPLITTER + "Logins";
    public static final String Other = Configuration.CATEGORY_GENERAL + Configuration.CATEGORY_SPLITTER + "Other";
    public static boolean specialLoginsDisabled;
    public static boolean specialLoginsFireworksDisabled;
    public static boolean recommendedServerHostButtonDisabled;
    public static String customModpackSlug;
    public static String urlForRemoteConfigs;

    public static void syncConfig() {
        MinecraftForge.EVENT_BUS.register(JECore.instance);

        urlForRemoteConfigs = JECore.config.get(MAIN, "Configs URL", "", "The URL of the remote .json configs").setRequiresMcRestart(true).getString();
        customModpackSlug = JECore.config.get(MAIN, "Modpack Slug", "", "Slug name of the modpack (Leave blank for non-Technic packs)").setRequiresMcRestart(true).getString();

        specialLoginsDisabled = JECore.config.get(Logins, "Disable Special Logins", false, "Disables all special logins features").setRequiresMcRestart(true).getBoolean(false);
        specialLoginsFireworksDisabled = JECore.config.get(Logins, "Disable Fireworks", false, "Disables fireworks for special logins").setRequiresWorldRestart(true).getBoolean(false);

        recommendedServerHostButtonDisabled = JECore.config.get(Other, "Hide Server Button", false, "Hides the recommended server host button").setRequiresMcRestart(true).getBoolean(false);
        if (JECore.config.hasChanged()) {
            JECore.config.save();
        }
    }

}
