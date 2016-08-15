package xjon.jexclusives;

import net.minecraft.init.Blocks;
import xjon.jexclusives.proxy.CommonProxy;
import xjon.jexclusives.util.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class JECore
{
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public final String url = "";
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		proxy.InitCapes(url);
	}
	
}
