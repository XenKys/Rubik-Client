package rubik.mods;

import net.minecraft.client.Minecraft;
import rubik.FileManager;
import rubik.gui.hud.HUDManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.impl.ArmorStatus;
import rubik.mods.impl.ArmorStatus.ArmorStatusMode;
import rubik.mods.impl.BlockOverlay;
import rubik.mods.impl.CPSDisplay;
import rubik.mods.impl.Chat;
import rubik.mods.impl.Clock;
import rubik.mods.impl.CoordinatesDisplay;
import rubik.mods.impl.FPSDisplay;
import rubik.mods.impl.Freelook;
import rubik.mods.impl.Fullbright;
import rubik.mods.impl.Keystrokes;
import rubik.mods.impl.MemoryUsage;
import rubik.mods.impl.OldAnimations;
import rubik.mods.impl.PingDisplay;
import rubik.mods.impl.PotionEffects;
import rubik.mods.impl.PotsCounter;
import rubik.mods.impl.togglesprintsneak.ToggleSprintSneak;
import rubik.mods.impl.Sidebar;

public class ModInstances {
	private static FPSDisplay fpsDisplayMod;
	private static CPSDisplay cpsDisplayMod;
	private static PingDisplay pingDisplayMod;
	private static Keystrokes keystrokesMod;
	private static ArmorStatus armorStatusMod;
	private static PotionEffects potionEffectsMod;
	private static CoordinatesDisplay coordinatesDisplayMod;
	private static ToggleSprintSneak toggleSprintSneakMod;
	private static Clock clockMod;
	private static MemoryUsage memoryUsageMod;
	private static PotsCounter potsCounterMod;
	private static Freelook freelookMod = new Freelook();
	private static Fullbright fullbrightMod = new Fullbright();
	private static OldAnimations oldAnimationsMod = new OldAnimations();
	private static Sidebar sidebarMod = new Sidebar();
	private static Chat chatMod = new Chat();
	private static BlockOverlay blockOverlayMod = new BlockOverlay();
	
	public static void register(HUDManager manager) {
		manager.register(fpsDisplayMod = new FPSDisplay());
		manager.register(cpsDisplayMod = new CPSDisplay());
		manager.register(pingDisplayMod = new PingDisplay());
		manager.register(keystrokesMod = new Keystrokes());
		manager.register(armorStatusMod = new ArmorStatus());
		manager.register(potionEffectsMod = new PotionEffects());
		manager.register(coordinatesDisplayMod = new CoordinatesDisplay());
		manager.register(toggleSprintSneakMod = new ToggleSprintSneak());
		manager.register(clockMod = new Clock());
		manager.register(memoryUsageMod = new MemoryUsage());
		manager.register(potsCounterMod = new PotsCounter());
	}
	
	public static FPSDisplay getFPSDisplayMod() {
		return fpsDisplayMod;
	}
	
	public static CPSDisplay getCPSDisplayMod() {
		return cpsDisplayMod;
	}
	
	public static PingDisplay getPingDisplayMod() {
		return pingDisplayMod;
	}
	
	public static Keystrokes getKeystrokesMod() {
		return keystrokesMod;
	}
	
	public static ArmorStatus getArmorStatusMod() {
		return armorStatusMod;
	}
	
	public static PotionEffects getPotionEffectsMod() {
		return potionEffectsMod;
	}
	
	public static CoordinatesDisplay getCoordinatesDisplayMod() {
		return coordinatesDisplayMod;
	}
	
	public static ToggleSprintSneak getToggleSprintSneakMod() {
		return toggleSprintSneakMod;
	}
	
	public static Clock getClockMod() {
		return clockMod;
	}
	
	public static MemoryUsage getMemoryUsageMod() {
		return memoryUsageMod;
	}
	
	public static PotsCounter getPotsCounterMod() {
		return potsCounterMod;
	}
	
	public static Freelook getFreelookMod() {
		return freelookMod;
	}
	
	public static Fullbright getFullbrightMod() {
		return fullbrightMod;
	}
	
	public static OldAnimations getOldAnimationsMod() {
		return oldAnimationsMod;
	}
	
	public static Sidebar getSidebarMod() {
		return sidebarMod;
	}
	
	public static Chat getChatMod() {
		return chatMod;
	}
	
	public static BlockOverlay getBlockOverlayMod() {
		return blockOverlayMod;
	}
}
