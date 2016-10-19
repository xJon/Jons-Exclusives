/**
 * DeveloperCapes by Jadar
 * License: MIT License
 * (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 4.0.0.x
 */
package xjon.developercapes;

import jline.internal.Log;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xjon.developercapes.cape.ICape;
import xjon.developercapes.user.User;
import xjon.developercapes.user.UserManager;

/**
 * This is not the class you are looking for.
 * 
 * @author jadar
 */
public class RenderEventHandler {

    @SubscribeEvent
    public void renderPlayer(RenderLivingEvent.Pre event) {
        if (event.getEntity() instanceof AbstractClientPlayer) {
            AbstractClientPlayer player = (AbstractClientPlayer) event.getEntity();

            UserManager manager = UserManager.getInstance();
            User user = manager.getUser(player.getUniqueID().toString());
            if (user == null) return;

            ICape cape = user.capes.get(0);
            if (cape == null) return;

            boolean flag = cape.isTextureLoaded(player);
            if (!flag) {
                cape.loadTexture(player);
            }
        }
    }
}