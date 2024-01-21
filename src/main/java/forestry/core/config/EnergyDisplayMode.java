package forestry.core.config;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

import java.text.NumberFormat;


public enum EnergyDisplayMode {
	RF;

	EnergyDisplayMode() {
	}

	public String formatEnergyValue(int energy) {
		return NumberFormat.getIntegerInstance(Minecraft.getInstance().getLocale()).format((float) energy) + " RF";
	}
}
