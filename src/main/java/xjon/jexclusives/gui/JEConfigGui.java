package xjon.jexclusives.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import xjon.jexclusives.JECore;
import xjon.jexclusives.util.Reference;

public class JEConfigGui extends GuiConfig {
	
	public JEConfigGui(GuiScreen screen)
	{
		super(screen, new ConfigElement(JECore.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
				Reference.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(JECore.config.toString()));
	}

}
