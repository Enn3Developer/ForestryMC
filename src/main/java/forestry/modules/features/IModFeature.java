package forestry.modules.features;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * A feature can be used to provide an game object like a block, an item or an fluid. There are different implementations
 * of this class for every game objects.
 * <p>
 * Features are automatically loaded by the modules if you annotate the class that contains the public static final fields.
 * Events like {@link #register(RegistryEvent.Register)} are automatically fired by modules.
 *
 * @see IBlockFeature
 * @see IItemFeature
 * @see IFluidFeature
 * @see FeatureType
 */
public interface IModFeature {
	String getIdentifier();

	FeatureType getType();

	String getModId();

	String getModuleId();

	void create();

	default <T extends IForgeRegistry<T>> void register(RegisterEvent.Register<T> event) {
	}

	@OnlyIn(Dist.CLIENT)
	default void clientSetupRenderers(EntityRenderersEvent.RegisterRenderers event) {
	}
}
