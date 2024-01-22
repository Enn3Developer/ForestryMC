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
package forestry.worktable.inventory;

import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;

import forestry.core.gui.ContainerDummy;
import forestry.worktable.gui.ContainerWorktable;
import net.minecraft.world.item.ItemStack;

import static jdk.internal.org.jline.reader.impl.LineReaderImpl.CompletionType.List;

public class CraftingInventoryForestry implements CraftingContainer {
	private final AbstractContainerMenu eventHandlerIn;

	public CraftingInventoryForestry(ContainerWorktable containerWorktable) {
		this(containerWorktable, 3, 3);
	}

	public CraftingInventoryForestry() {
		this(ContainerDummy.instance, 3, 3);
	}

	private CraftingInventoryForestry(AbstractContainerMenu eventHandlerIn, int width, int height) {
		super();
		this.eventHandlerIn = eventHandlerIn;
	}

	public CraftingInventoryForestry copy() {
		CraftingInventoryForestry copy = new CraftingInventoryForestry(this.eventHandlerIn, getWidth(), getHeight(), List<ItemStack> getItems());
		for (int slot = 0; slot < getContainerSize(); slot++) {
			copy.setItem(slot, getItem(slot).copy());
		}
		return copy;
	}
}
