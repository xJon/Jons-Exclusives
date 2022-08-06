package xjon.jexclusives.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import xjon.jexclusives.util.Log;
import xjon.jexclusives.util.JEConfiguration;

import java.net.URI;

public class GuiEvents {
    @SubscribeEvent
    public void open(InitGuiEvent.Post e) {
        if (e.gui instanceof GuiMultiplayer && !JEConfiguration.recommendedServerHostButtonDisabled)
            e.buttonList.add(new GuiButton(18436, e.gui.width - 105, 5, 100, 20, "\u00A7c\u2726\u00A7r Get a Server"));
    }

    @SubscribeEvent
    public void action(ActionPerformedEvent.Post e) {
        if ((e.gui instanceof GuiMultiplayer) && e.button.id == 18436) {
            try {
                Class<?> oclass = Class.forName("java.awt.Desktop");
                Object object = oclass.getMethod("getDesktop").invoke(null);
                oclass.getMethod("browse", URI.class).invoke(object, new URI("https://bit.ly/The-1-7-10-Pack-AklizMenu"));
            } catch (Throwable throwable1) {
                Throwable throwable = throwable1.getCause();
                Log.error("Couldn't open link: " + (throwable == null ? "<UNKNOWN>" : throwable.getMessage()));
            }


        }
    }
}
