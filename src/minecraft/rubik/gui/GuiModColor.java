package rubik.gui;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.ColorManager;
import rubik.gui.GuiSlider;

public class GuiModColor extends GuiScreen {
	private final GuiScreen previousGuiScreen;
	private ColorManager color;
	
	private GuiSlider sliderRed;
    private GuiSlider sliderGreen;
    private GuiSlider sliderBlue;
    private GuiSlider sliderAlpha;
	
	public GuiModColor(GuiScreen previousGuiScreen, ColorManager color) {
		this.previousGuiScreen = previousGuiScreen;
		this.color = color;
	}
	
	@Override
    public void initGui()
    {
        this.buttonList.clear();
        
        int i = -16;
        int j = 75;
        
		this.buttonList.add(sliderRed = new GuiSlider(1, this.width / 2 - j, this.height / 4 + 24 + i, 150, 20, "Red", 0, 255, color.getRed()));
        this.buttonList.add(sliderGreen = new GuiSlider(2, this.width / 2 - j, this.height / 4 + 48 + i, 150, 20, "Green", 0, 255, color.getGreen()));
        this.buttonList.add(sliderBlue = new GuiSlider(3, this.width / 2 - j, this.height / 4 + 72 + i, 150, 20, "Blue", 0, 255, color.getBlue()));
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
            	color.setRed((int) (sliderRed.func_175217_d() * 255.0F));
            	break;
            case 2:
            	color.setGreen((int) (sliderGreen.func_175217_d() * 255.0F));
            	break;
            case 3:
            	color.setBlue((int) (sliderBlue.func_175217_d() * 255.0F));
            	break;
        }
    }
    
    @Override
    public void mouseClickMove(final int mouseX, final int mouseY, final int clickedMouseButton, final long timeSinceLastClick) {
    	color.setRed((int) (sliderRed.func_175217_d() * 255.0F));
    	color.setGreen((int) (sliderGreen.func_175217_d() * 255.0F));
    	color.setBlue((int) (sliderBlue.func_175217_d() * 255.0F));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, I18n.format("Customize Mod Color", new Object[0]), this.width / 2, 40, 0xFFFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
        
        int rectLeft = this.width / 2 - 75;
        int rectTop = this.height / 4 + 80;
        int rectWidth = 150 - 1;
        int rectHeight = 20 - 1;
        
        drawRect(rectLeft, rectTop, rectLeft + rectWidth, rectTop + rectHeight, color.getRGB());
        drawHollowRect(rectLeft, rectTop, rectWidth, rectHeight, Color.BLACK.getRGB());
        this.fontRendererObj.drawStringWithShadow("Current Color", (rectLeft + (rectWidth - this.fontRendererObj.getStringWidth("Current Color")) / 2) + 1, (rectTop + (rectHeight - this.fontRendererObj.FONT_HEIGHT) / 2) + 1, Color.WHITE.getRGB());
    }
    
    private void drawHollowRect(int x, int y, int width, int height, int color) {
		this.drawHorizontalLine(x, x + width, y, color);
		this.drawHorizontalLine(x, x + width, y + height, color);
		this.drawVerticalLine(x, y + height, y, color);
		this.drawVerticalLine(x + width, y + height, y, color);
	}
}
