package forestry.core.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;

public class BlockResourceStorage extends Block {
	private EnumResourceType type;

	public BlockResourceStorage(EnumResourceType type) {
		super(Block.Properties.of().mapColor(MapColor.METAL).strength(3f, 5f));
		this.type = type;
	}

	public EnumResourceType getType() {
		return this.type;
	}
}
