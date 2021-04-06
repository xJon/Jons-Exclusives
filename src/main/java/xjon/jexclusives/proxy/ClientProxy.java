package xjon.jexclusives.proxy;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import net.minecraftforge.common.MinecraftForge;
import xjon.developercapes.DevCapes;
import xjon.jexclusives.event.GuiEvents;
import xjon.jexclusives.event.PlayerEvents;
import xjon.jexclusives.util.JEConfiguration;
import xjon.jexclusives.util.Log;
import xjon.jexclusives.util.UrlValidator;

import java.net.URL;

public class ClientProxy extends CommonProxy {

    @Override
    public void initCapes() {
        if (JEConfiguration.urlForRemoteConfigs != null && !JEConfiguration.urlForRemoteConfigs.isEmpty()) {
            if (UrlValidator.isUrlValid(JEConfiguration.urlForRemoteConfigs)) {
                try {
                    PlayerEvents.PackJson remoteConfigs = new Gson().fromJson(Resources.toString(new URL(JEConfiguration.urlForRemoteConfigs), Charsets.UTF_8), PlayerEvents.PackJson.class);

                    if (remoteConfigs.capeurl != null) {
                        Log.info("Loading custom capes...");
                        DevCapes.getInstance().registerConfig(remoteConfigs.capeurl);
                    } else {
                        Log.error("Capes were not initialized as the remote config's capeurl is either missing or invalid");
                    }
                } catch (Exception e) {
                }
            } else {
                Log.warn("Capes were not initialized as the remote config URL is invalid");
            }
        } else {
            Log.warn("Capes were not initialized as the remote config URL is missing");
        }
    }

    @Override
    public void registerGuiEvents() {
        MinecraftForge.EVENT_BUS.register(new GuiEvents());
    }
}
