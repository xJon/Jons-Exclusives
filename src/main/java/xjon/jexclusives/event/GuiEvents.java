package xjon.jexclusives.event;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xjon.jexclusives.util.JEConfiguration;
import xjon.jexclusives.util.Log;

import java.net.URI;

public class GuiEvents {
    @SubscribeEvent
    public void open(InitGuiEvent.Post e) {
        if (e.getGui() instanceof GuiMultiplayer && !JEConfiguration.recommendedServerHostButtonDisabled)
            e.getButtonList().add(new GuiButton(18436, e.getGui().width - 105, 5, 100, 20, "\u00A7c\u2726\u00A7r Get a Server"));

        if (e.getGui() instanceof GuiScreenResourcePacks && !JEConfiguration.recommendedResourcePackButtonDisabled)
            e.getButtonList().add(new GuiButton(18437, 5, 5, 100, 20, "\u00A76\u00A7l\u16DF\u00A7r Get Sphax"));
    }

    @SubscribeEvent
    public void action(ActionPerformedEvent.Post e) {
        if ((e.getGui() instanceof GuiMultiplayer) && e.getButton().id == 18436)
            openLink("");

        if ((e.getGui() instanceof GuiScreenResourcePacks) && e.getButton().id == 18437)
            openLink("");
    }

    private void openLink(String url) {
        try {
            Class<?> oclass = Class.forName("java.awt.Desktop");
            Object object = oclass.getMethod("getDesktop").invoke(null);
            oclass.getMethod("browse", URI.class).invoke(object, new URI(url));
        } catch (Throwable throwable1) {
            Throwable throwable = throwable1.getCause();
            Log.error("Couldn't open link: " + (throwable == null ? "<UNKNOWN>" : throwable.getMessage()));
        }
    }
}
