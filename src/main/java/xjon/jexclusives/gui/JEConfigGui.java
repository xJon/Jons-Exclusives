package xjon.jexclusives.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import xjon.jexclusives.JECore;
import xjon.jexclusives.util.Reference;

import java.util.ArrayList;
import java.util.List;

public class JEConfigGui extends GuiConfig {

    public JEConfigGui(GuiScreen screen) {
        super(screen, getConfigElements(),
                Reference.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(JECore.config.toString()));
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> configs = new ArrayList<IConfigElement>();
        configs.addAll(new ConfigElement(JECore.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements());
        return configs;
    }

}
