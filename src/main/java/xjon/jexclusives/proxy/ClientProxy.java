package xjon.jexclusives.proxy;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import xjon.developercapes.DevCapes;
import xjon.jexclusives.event.PlayerEvents;
import xjon.jexclusives.util.JEConfiguration;
import xjon.jexclusives.util.Log;
import xjon.jexclusives.util.UrlValidator;

import java.net.URL;

public class ClientProxy extends CommonProxy {

    @Override
    public void initCapes() {
        if (UrlValidator.isUrlValid(JEConfiguration.remoteConfigUrl)) {
            try {
                PlayerEvents.PackJson remoteConfigs = new Gson().fromJson(Resources.toString(new URL(JEConfiguration.remoteConfigUrl), Charsets.UTF_8), PlayerEvents.PackJson.class);

                if (remoteConfigs.capeurl != null) {
                    Log.info("Loading custom capes...");
                    DevCapes.getInstance().registerConfig(remoteConfigs.capeurl);
                } else {
                    Log.error("Jon's Exclusives' remote configs' capeurl is either down or missing");
                }
            } catch (Exception e) {
            }
        } else {
            Log.error("Jon's Exclusives' remote configs for selected URL are down (check if it's correct)");
        }
    }

}
