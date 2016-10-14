package xjon.jexclusives.util;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class Firework {
	
    private static final Random rand = new Random();
	
	public static void Fireworks(boolean enabled, BlockCoord pos, int dimId	)
	{
		if (enabled && !JEConfiguration.specialLoginsFireworksDisabled)
		{
			spawnFirework(pos, dimId);
		}
	}
	
	/**
	 * Thank you tterrag!
	 */
	
	  public static EntityFireworkRocket getRandomFirework(World world) {
		    return getRandomFirework(world, new BlockCoord(0, 0, 0));
		  }

	  public static EntityFireworkRocket getRandomFirework(World world, BlockCoord pos) {
		    ItemStack firework = new ItemStack(Items.fireworks);
		    firework.setTagCompound(new NBTTagCompound());
		    NBTTagCompound expl = new NBTTagCompound();
		    expl.setBoolean("Flicker", true);
		    expl.setBoolean("Trail", true);

		    int[] colors = new int[rand.nextInt(8) + 1];
		    for (int i = 0; i < colors.length; i++) {
		      colors[i] = ItemDye.dyeColors[rand.nextInt(16)];
		    }
		    expl.setIntArray("Colors", colors);
		    byte type = (byte) (rand.nextInt(3) + 1);
		    type = type == 3 ? 4 : type;
		    expl.setByte("Type", type);

		    NBTTagList explosions = new NBTTagList();
		    explosions.appendTag(expl);

		    NBTTagCompound fireworkTag = new NBTTagCompound();
		    fireworkTag.setTag("Explosions", explosions);
		    fireworkTag.setByte("Flight", (byte) 1);
		    firework.getTagCompound().setTag("Fireworks", fireworkTag);

		    EntityFireworkRocket e = new EntityFireworkRocket(world, pos.x + 0.5, pos.y + 0.5, pos.z + 0.5, firework);
		    return e;
		  }

	  public static void spawnFirework(BlockCoord block, int dimID) {
	    spawnFirework(block, dimID, 0);
	  }

	  public static void spawnFirework(BlockCoord block, int dimID, int range) {
		    World world = DimensionManager.getWorld(dimID);

		    BlockPos pos = new BlockPos(block.x, block.y, block.z);
		    IBlockState bs = world.getBlockState(pos);
		    // don't bother if there's no randomness at all
		    if (range > 0) {
		      pos = new BlockPos(moveRandomly(block.x, range), block.y, moveRandomly(block.z, range));

		      int tries = -1;
		      while (!world.isAirBlock(new BlockPos(pos)) && !bs.getBlock().isReplaceable(world, pos)) {
		        tries++;
		        if (tries > 100) {
		          return;
		        }
		      }
		    }

		    world.spawnEntityInWorld(getRandomFirework(world, new BlockCoord(pos)));		    
		  }
	    
	    private static double moveRandomly(double base, double range) {
	        return base + 0.5 + rand.nextDouble() * range - (range / 2);
	      }

}
