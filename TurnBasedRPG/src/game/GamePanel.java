package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import playerClasses.Player;
import userInputs.InputsFromKeyboard;

public class GamePanel extends JPanel {
	
	private int deltaX;
	private int deltaY;
	private InputsFromKeyboard inputsFromKeyboard;
	private BufferedImage image;
	private BufferedImage tempBackground;
	
	
	public GamePanel(Player playerClass) {
		
		importImage();
		
		inputsFromKeyboard = new InputsFromKeyboard(this, playerClass);
		this.addKeyListener(inputsFromKeyboard);
	
	}
	
	
	private void importImage() {
		
		InputStream is = getClass().getResourceAsStream("/knightFront.png");
		InputStream is2 = getClass().getResourceAsStream("/woodfloor.png");
		
		try {
			image = ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			tempBackground = ImageIO.read(is2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D)g; // convertendo os gráficos p/ 2D propriamente
		
		g2D.setColor(Color.green);
		
		g2D.drawImage(image, 100 + deltaX, 100 + deltaY, 48, 48, null);
		g2D.drawImage(tempBackground, 0, 0, 48, 48, null);
		
		g2D.dispose();
	}
	
	
	public void changeDeltaX(int speed) {
		
		this.deltaX += speed;
		repaint();
	}
	
	public void changeDeltaY(int speed) {
		
		this.deltaY += speed;
		repaint();
	}
	
	}	
	

