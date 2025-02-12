package forestry.modules;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public interface IClientModuleHandler extends ISidedModuleHandler {

	default void registerSprites(TextureStitchEvent.Pre event) {
	}

	default void handleSprites(TextureStitchEvent.Post event) {
	}

	default void bakeModels(ModelBakeEvent event) {
	}

	default void registerModels(ModelRegistryEvent event) {
	}

	default void setupClient(FMLClientSetupEvent event) {
	}

	default void setupLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
	}

	default void setupRenderers(EntityRenderersEvent.RegisterRenderers event) {
	}
}
