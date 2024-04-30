package rubik.gui;

import java.awt.Color;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import rubik.gui.hud.HUDManager;
import rubik.gui.hud.IRenderer;
import rubik.gui.hud.ScreenPosition;

public class GuiModPositioning extends GuiScreen {
	private final HashMap<IRenderer, ScreenPosition> renderers = new HashMap<IRenderer, ScreenPosition>();
	
	private Optional<IRenderer> selectedRenderer = Optional.empty();
	
	private int prevX;
	private int prevY;
	
	private static final int marginX = 2;
	private static final int marginY = 2;
	
	public GuiModPositioning(HUDManager manager) {
		Collection<IRenderer> registeredRenderers = manager.getRegisteredRenderers();
		
		for (IRenderer renderer : registeredRenderers) {
			if (!renderer.isEnabled()) {
				continue;
			}
			
			ScreenPosition pos = renderer.load();
			
			if (pos == null) {
				pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
			}
			
			adjustBounds(renderer, pos);
			
			this.renderers.put(renderer, pos);
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
	    super.drawDefaultBackground();

	    final float zBackup = this.zLevel;
	    
	    this.zLevel = 200;

	    drawHollowRect(marginX, marginY, this.width - marginX * 2 - 1, this.height - marginY * 2 - 1, Color.MAGENTA.getRGB());
	    
	    for (IRenderer renderer : renderers.keySet()) {
	        ScreenPosition pos = renderers.get(renderer);
	        
	        renderer.renderDummy(pos);
	        
	        drawHollowRect(pos.getAbsoluteX(), pos.getAbsoluteY(), renderer.getWidth(), renderer.getHeight(), Color.GRAY.getRGB());
	        drawFillRect(pos.getAbsoluteX(), pos.getAbsoluteY(), renderer.getWidth(), renderer.getHeight(), new Color(255, 255, 255, 40).getRGB());
	    }
	    
	    if (selectedRenderer.isPresent()) {
	    	ScreenPosition pos = renderers.get(selectedRenderer.get());
	        
	        drawHollowRect(pos.getAbsoluteX(), pos.getAbsoluteY(), selectedRenderer.get().getWidth(), selectedRenderer.get().getHeight(), Color.MAGENTA.getRGB());
	    }

	    this.zLevel = zBackup;
	}

	private void drawHollowRect(int x, int y, int width, int height, int color) {
		this.drawHorizontalLine(x, x + width, y, color);
		this.drawHorizontalLine(x, x + width, y + height, color);
		this.drawVerticalLine(x, y + height, y, color);
		this.drawVerticalLine(x + width, y + height, y, color);
	}
	
	private void drawFillRect(int x, int y, int width, int height, int color) {
		drawRect(x, y, x + width, y + height, color);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (keyCode == Keyboard.KEY_ESCAPE) {
			renderers.entrySet().forEach((entry) -> {
				entry.getKey().save(entry.getValue());
			});
			
			this.mc.displayGuiScreen(null);
		}
	}
	
	@Override
	protected void mouseClickMove(int x, int y, int button, long time) {
		if (selectedRenderer.isPresent()) {
			moveSelectedRenderBy(x - prevX, y - prevY);
		}
		
		this.prevX = x;
		this.prevY = y;
	}

	private void moveSelectedRenderBy(int offsetX, int offsetY) {
		IRenderer renderer = selectedRenderer.get();
		ScreenPosition pos = renderers.get(renderer);
		
		pos.setAbsolute(pos.getAbsoluteX() + offsetX, pos.getAbsoluteY() + offsetY);
		
		adjustBounds(renderer, pos);
	}

	@Override
	public void onGuiClosed() {
		for (IRenderer renderer : renderers.keySet()) {
			renderer.save(renderers.get(renderer));
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}
	
	private void adjustBounds(IRenderer renderer, ScreenPosition pos) {
		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
		
		int screenWidth = res.getScaledWidth();
		int screenHeight = res.getScaledHeight();
		
		int absoluteX = Math.max(marginX, Math.min(pos.getAbsoluteX(), Math.max(screenWidth - renderer.getWidth() - marginX - 1, 0)));
		int absoluteY = Math.max(marginY, Math.min(pos.getAbsoluteY(), Math.max(screenHeight - renderer.getHeight() - marginY - 1, 0)));
		
		pos.setAbsolute(absoluteX, absoluteY);
	}
	
	@Override
	protected void mouseClicked(int x, int y, int mouseButton) throws IOException {
		this.prevX = x;
		this.prevY = y;
		
		loadMouseOver(x, y);
	}

	private void loadMouseOver(int x, int y) {
		selectedRenderer = renderers.keySet().stream().filter(renderer -> isMouseOver(renderer, x, y)).findFirst();
	}
	
	private boolean isMouseOver(IRenderer renderer, int mouseX, int mouseY) {
	    ScreenPosition pos = renderers.get(renderer);
	    
	    int absoluteX = pos.getAbsoluteX();
		int absoluteY = pos.getAbsoluteY();
	    
	    return (mouseX >= absoluteX && mouseX <= absoluteX + renderer.getWidth()) && (mouseY >= absoluteY && mouseY <= absoluteY + renderer.getHeight());
	}
	
	public static int getMarginX() {
		return marginX;
	}
	
	public static int getMarginY() {
		return marginY;
	}
}
