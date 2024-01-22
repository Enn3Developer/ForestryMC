package forestry.arboriculture.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class BlockCharcoal extends Block {

	public BlockCharcoal() {
		super(Block.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM)
				.strength(5.0f, 10.0f)
				.sound(SoundType.STONE));
	}
}
