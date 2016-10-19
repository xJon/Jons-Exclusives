/**
 * DeveloperCapes by Jadar
 * License: MIT License
 * (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 4.0.0.x
 */
package xjon.developercapes.cape;

import java.lang.reflect.Field;
import java.net.URL;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;
import xjon.developercapes.DevCapes;
import xjon.developercapes.HDImageBuffer;

/**
 * Default Cape implementation
 * 
 * @author jadar
 */
public class StaticCape extends AbstractCape {

    public StaticCape(String name, URL url) {
        this.setName(name);
        this.setURL(url);
    }

    public StaticCape(String name) {
        this(name, null);
    }

	/**
	 * Thank you mmdanggg2!
	 */
    @Override
    public void loadTexture(AbstractClientPlayer player) {
    	ResourceLocation location = this.getLocation();

        try {
        	Field playerInfoF;
        	try {
        		playerInfoF = AbstractClientPlayer.class.getDeclaredField("getPlayerInfo");
        	}
        	catch(NoSuchFieldException e) {
            	playerInfoF = AbstractClientPlayer.class.getDeclaredField("func_175155_b");
            }
            playerInfoF.setAccessible(true);
            NetworkPlayerInfo nci = (NetworkPlayerInfo) playerInfoF.get(player);
            
            Field locationCapeF;
            try {
            	locationCapeF = NetworkPlayerInfo.class.getDeclaredField("playerTextures");
            }
            catch(NoSuchFieldException e) {
            	locationCapeF = NetworkPlayerInfo.class.getDeclaredField("field_187107_a");
            }
            locationCapeF.setAccessible(true);
            locationCapeF.set(nci, location);

            playerInfoF.setAccessible(false);
            locationCapeF.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
            DevCapes.logger.error("Setting cape ResourceLocation failed!");
        }

        Minecraft.getMinecraft().renderEngine.loadTexture(location, this.getTexture());
    }

    @Override
    public boolean isTextureLoaded(AbstractClientPlayer player) {
        ResourceLocation cape = player.getLocationCape();
        return cape != null;
    }

    public void setURL(URL url) {
        if (url == null) {
            this.texture = null;
            return;
        }
        this.texture = new ThreadDownloadImageData(null, url.toString(), null, new HDImageBuffer());
    }

    public void setName(String name) {
        this.name = name;
        this.location = new ResourceLocation("DevCapes/" + name);
    }
}