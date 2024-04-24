package rubik.gui.mods;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.gui.GuiModColor;
import rubik.gui.GuiSlider;
import rubik.mods.ModInstances;
import rubik.mods.impl.togglesprintsneak.ToggleSprintSneak;

public class GuiToggleSprintSneak extends GuiScreen {
	private final GuiScreen previousGuiScreen;
	private ToggleSprintSneak mod = ModInstances.getToggleSprintSneakMod();
	
	private GuiSlider sliderFlyBoost;
	
	public GuiToggleSprintSneak(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}
	
	@Override
    public void initGui()
    {
        this.buttonList.clear();
        
        int i = -16;
 
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 24 + i, 98, 20, I18n.format(mod.isEnabled() ? "�aEnabled" : "�cDisabled", new Object[0])));
        this.buttonList.add(new GuiButton(2, this.width / 2 + 2, this.height / 4 + 24 + i, 98, 20, I18n.format((mod.isFlyBoostEnabled() ? "�a" : "�c") + "Fly Boost", new Object[0])));
        this.buttonList.add(sliderFlyBoost = new GuiSlider(3, this.width / 2 - 100, this.height / 4 + 48 + i, 200, 20, "Fly Boost Factor", 1, 8, mod.getFlyBoostFactor()));
        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 72 + i, 98, 20, I18n.format((mod.isToggleSprintEnabled() ? "�a" : "�c") + "Toggle Sprint", new Object[0])));
        this.buttonList.add(new GuiButton(5, this.width / 2 + 2, this.height / 4 + 72 + i, 98, 20, I18n.format((mod.isToggleSneakEnabled() ? "�a" : "�c") + "Toggle Sneak", new Object[0])));
        this.buttonList.add(new GuiButton(6, this.width / 2 - 100, this.height / 4 + 96 + i, 98, 20, I18n.format((mod.isShadowEnabled() ? "�a" : "�c") + "Text Shadow", new Object[0])));
        this.buttonList.add(new GuiButton(7, this.width / 2 + 2, this.height / 4 + 96 + i, 98, 20, I18n.format("Color", new Object[0])));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        switch (button.id)
        {
            case 0:
            	this.mc.displayGuiScreen(this.previousGuiScreen);
            	break;
            case 1:
            	mod.setEnabled(!mod.isEnabled());
            	this.initGui();
                break;
            case 2:
            	mod.setFlyBoostEnabled(!mod.isFlyBoostEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setFlyBoostFactor(sliderFlyBoost.func_175217_d() * 8.0F);
            	break;
            case 4:
            	mod.setToggleSprintEnabled(!mod.isToggleSprintEnabled());
            	this.initGui();
            	break;
            case 5:
            	mod.setToggleSneakEnabled(!mod.isToggleSneakEnabled());
            	this.initGui();
            	break;
            case 6:
            	mod.setShadowEnabled(!mod.isShadowEnabled());
            	this.initGui();
            	break;
            case 7:
            	this.mc.displayGuiScreen(new GuiModColor(this, mod.getColorManager()));
                break;
        }
    }
    
    @Override
    public void mouseClickMove(final int mouseX, final int mouseY, final int clickedMouseButton, final long timeSinceLastClick) {
    	mod.setFlyBoostFactor(sliderFlyBoost.func_175217_d() * 8.0F);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, I18n.format("Toggle Sprint / Sneak Settings", new Object[0]), this.width / 2, 40, 0xFFFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}