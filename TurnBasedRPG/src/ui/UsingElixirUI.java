package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import items.Elixir;

public class UsingElixirUI {
	
	private String elixirMessage;
	private Elixir elixir;
	private int screenWidth;
	private int screenHeight;
	
	private boolean messageIsVisible;
	private long UIStartTime;
	private static final int UI_DURATION = 2000; // 2000ms = 2s (tempo de duração da interface gráfica na tela)
	
	public UsingElixirUI(int screenWidth, int screenHeight, Elixir elixir) {
		
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.elixir = elixir;
		this.messageIsVisible = false;
	}
	
	public void showMessage() {
		
		if (elixir != null) {
		
			this.messageIsVisible = true;
			this.UIStartTime = System.currentTimeMillis();
			this.elixirMessage = "Você ganhou " + elixir.getElixirAttackBoost() + " de poder de ataque até o fim desse combate!";
		}
	}
	
	public void draw(Graphics g2D) {

		if (!messageIsVisible) {
			return;
		}
		
		Font font = new Font("Arial Black", Font.BOLD, 18);
		g2D.setFont(font);
		FontMetrics metrics = g2D.getFontMetrics(font);
		
		int textWidth = metrics.stringWidth(elixirMessage);
		int textHeight = metrics.getHeight();
		
		int x = (screenWidth - textWidth) / 2;
		int y = screenHeight / 4;
		
		g2D.setColor(new Color(0, 0, 0, 150));
		g2D.fillRect(x - 10, y - textHeight, textWidth + 20, textHeight + 10);
		
		g2D.setColor(Color.WHITE);
		g2D.drawRect(x - 10, y - textHeight, textWidth + 20, textHeight + 10);
		
		g2D.setColor(Color.RED);
		g2D.drawString(elixirMessage, x, y);
			
	}
	
	
	// o print da UI com a informação sobre o elixir aparece e some após 2 segundos
	public void update() {
		
		if (messageIsVisible && (System.currentTimeMillis() - UIStartTime) > UI_DURATION) {
			messageIsVisible = false;
		}
	}

}
