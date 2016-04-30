/*******************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 ******************************************************************************/
package forestry.core.gui.ledgers;

import java.util.Locale;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;

import com.mojang.authlib.GameProfile;

import forestry.core.access.EnumAccess;
import forestry.core.access.IAccessHandler;
import forestry.core.access.IAccessOwnerListener;
import forestry.core.access.IRestrictedAccess;
import forestry.core.proxy.Proxies;
import forestry.core.render.TextureManager;
import forestry.core.utils.PlayerUtil;
import forestry.core.utils.Translator;

/**
 * Ledger displaying ownership information
 */
public class OwnerLedger extends Ledger implements IAccessOwnerListener {

	private final IAccessHandler accessHandler;

	public OwnerLedger(LedgerManager manager, IRestrictedAccess tile) {
		super(manager, "owner");

		this.accessHandler = tile.getAccessHandler();
		accessHandler.addOwnerListener(this);
		onOwnerSet(accessHandler.getOwner());
	}

	private boolean isAccessButton(int mouseX, int mouseY) {

		int shiftX = currentShiftX;
		int shiftY = currentShiftY + 44;

		return mouseX >= shiftX && mouseX <= currentShiftX + currentWidth && mouseY >= shiftY && mouseY <= shiftY + 12;
	}

	@Override
	public boolean isVisible() {
		return accessHandler.isOwned();
	}

	@Override
	public void draw(int x, int y) {
		// Draw background
		drawBackground(x, y);

		// Draw icon
		EnumAccess accessType = accessHandler.getAccess();
		TextureAtlasSprite accessIcon = TextureManager.getInstance().getDefault("misc/access." + accessType.toString().toLowerCase(Locale.ENGLISH));
		drawSprite(accessIcon, x + 3, y + 4);

		// Draw description
		if (!isFullyOpened()) {
			return;
		}

		drawHeader(Translator.translateToLocal("for.gui.owner"), x + 22, y + 8);

		drawText(PlayerUtil.getOwnerName(accessHandler), x + 22, y + 20);

		Minecraft minecraft = Proxies.common.getClientInstance();
		boolean playerIsOwner = accessHandler.isOwner(minecraft.thePlayer);
		if (playerIsOwner) {
			drawSubheader(Translator.translateToLocal("for.gui.access") + ':', x + 22, y + 32);
			// Access rules
			drawSprite(accessIcon, x + 20, y + 40);
			drawText(Translator.translateToLocal(accessType.getUnlocalizedName()), x + 38, y + 44);
		}
	}

	@Override
	public String getTooltip() {
		return Translator.translateToLocal("for.gui.owner") + ": " + PlayerUtil.getOwnerName(accessHandler);
	}

	@Override
	public boolean handleMouseClicked(int x, int y, int mouseButton) {

		if (isAccessButton(x, y)) {
			Minecraft minecraft = Proxies.common.getClientInstance();
			EntityPlayer player = minecraft.thePlayer;

			return accessHandler.switchAccess(player);
		}

		return false;
	}

	@Override
	public void onOwnerSet(GameProfile gameProfile) {
		Minecraft minecraft = Proxies.common.getClientInstance();
		boolean playerIsOwner = PlayerUtil.isSameGameProfile(minecraft.thePlayer.getGameProfile(), gameProfile);
		if (playerIsOwner) {
			maxHeight = 60;
		} else {
			maxHeight = 36;
		}
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		accessHandler.removeOwnerListener(this);
	}
}
