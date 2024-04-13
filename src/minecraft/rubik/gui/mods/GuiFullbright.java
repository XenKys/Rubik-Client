package rubik.gui.mods;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.mods.ModInstances;
import rubik.mods.impl.Fullbright;

public class GuiFullbright extends GuiScreen {
	private final GuiScreen previousGuiScreen;
	private Fullbright mod = ModInstances.getFullbrightMod();
	
	public GuiFullbright(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}
	
	@Override
    public void initGui()
    {
        this.buttonList.clear();
        
        int i = -16;
 
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100 + 50, this.height / 4 + 24 + i, 98, 20, I18n.format(mod.isEnabled() ? "�aEnabled" : "�cDisabled", new Object[0])));
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
	            {
	            	if (mod.isEnabled()) {
	            		mod.setEnabled(false);
	            		
	            		mc.gameSettings.gammaSetting = 1.0F;
	            	} else {
	            		mod.setEnabled(true);
	            		
	            		mc.gameSettings.gammaSetting = 10.0F;
	            	}
	            	
	            	this.initGui();
	            }
                break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, I18n.format("Fullbright Settings", new Object[0]), this.width / 2, 40, 0xFFFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}