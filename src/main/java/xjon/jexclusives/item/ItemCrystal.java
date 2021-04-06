package xjon.jexclusives.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xjon.jexclusives.util.Reference;

import javax.annotation.Nullable;
import java.util.List;

public class ItemCrystal extends Item {

    public ItemCrystal() {
        setUnlocalizedName("crystal");
        setRegistryName(new ResourceLocation(Reference.MOD_ID, "crystal"));
        setCreativeTab(CreativeTabs.MISC);
        setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Unexpectedly, the crystal unifies all mods harmonically");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack stack) {
        return TextFormatting.AQUA + super.getItemStackDisplayName(stack);
    }

}
