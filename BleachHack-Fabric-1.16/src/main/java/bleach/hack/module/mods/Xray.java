/*
 * This file is part of the BleachHack distribution (https://github.com/BleachDrinker420/BleachHack/).
 * Copyright (c) 2021 Bleach and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package bleach.hack.module.mods;

import org.lwjgl.glfw.GLFW;

import com.google.common.eventbus.Subscribe;

import bleach.hack.event.events.EventTick;
import bleach.hack.module.Category;
import bleach.hack.module.Module;
import bleach.hack.setting.base.SettingToggle;
import bleach.hack.setting.other.SettingLists;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class Xray extends Module {

	private double gamma;

	public Xray() {
		super("Xray", GLFW.GLFW_KEY_X, Category.RENDER, "Baritone is for zoomers",
				new SettingToggle("Fluids", false).withDesc("Show fluids"),
				SettingLists.newBlockList("Edit Blocks", "Edit Xray Blocks",
						Blocks.IRON_ORE,
						Blocks.GOLD_ORE,
						Blocks.LAPIS_ORE,
						Blocks.REDSTONE_ORE,
						Blocks.DIAMOND_ORE,
						Blocks.EMERALD_ORE,
						Blocks.GOLD_BLOCK,
						Blocks.LAPIS_BLOCK,
						Blocks.REDSTONE_BLOCK,
						Blocks.DIAMOND_BLOCK,
						Blocks.EMERALD_BLOCK,
						Blocks.NETHER_GOLD_ORE,
						Blocks.ANCIENT_DEBRIS).withDesc("Edit the xray blocks"));
	}
	
	public boolean isVisible(Block block) {
		return !isEnabled() || getSetting(1).asList(Block.class).contains(block);
	}

	@Override
	public void onEnable() {
		super.onEnable();

		mc.chunkCullingEnabled = false;
		mc.worldRenderer.reload();

		gamma = mc.options.gamma;
	}

	@Override
	public void onDisable() {
		mc.options.gamma = gamma;

		mc.chunkCullingEnabled = true;
		mc.worldRenderer.reload();

		super.onDisable();
	}

	@Subscribe
	public void onTick(EventTick eventPreUpdate) {
		mc.options.gamma = 69.420;
	}
	
	/*@Subscribe
	public void onChunkCulling(EventChunkCulling event) {
		event.setCull(false);
	}*/
}
