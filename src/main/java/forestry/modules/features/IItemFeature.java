package forestry.modules.features;

import java.util.function.Supplier;

import net.minecraft.world.item.Item;

import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.IForgeRegistry;

import forestry.api.core.IItemProvider;
import forestry.core.proxy.Proxies;

public interface IItemFeature<I extends Item> extends IModFeature, IItemProvider<I>, net.minecraft.world.level.ItemLike {

	Supplier<I> getItemConstructor();

	void setItem(I item);

	default I item() {
		I item = getItem();
		if (item == null) {
			throw new IllegalStateException("Called feature getter method before content creation was called in the pre init.");
		}
		return item;
	}

	@Override
	default Item asItem() {
		return item();
	}

	@Override
	default void create() {
		I item = getItemConstructor().get();
		item.setRegistryName(getModId(), getIdentifier());
		setItem(item);
	}

	@SuppressWarnings("unchecked")
	default <T extends IForgeRegistryEntry<T>> void register(RegisterEvent.register<T> event) {
		IForgeRegistry<T> registry = event.getForgeRegistry();
		Class<T> superType = registry.getRegistrySuperType();
		if (Item.class.isAssignableFrom(superType) && hasItem()) {
			registry.register((T) item());
			Proxies.common.registerItem(item());
		}
	}
}
