package entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.ScreenSettings;

public abstract class Player extends Entity {

	public Player(ScreenSettings screenSettings, String filePath) {

		super(screenSettings, filePath);
	}


}
