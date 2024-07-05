package combat;

import javax.swing.*;

import entities.Entity;
import game.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Move {
	
	private Timer timer;
    private final int delay = 1000 / 60;
    private int spriteIndex = 0;
	private int spriteCounter = 0;

    public Move(Entity entity, GamePanel gamePanel, Square targetSquare) {
    	
    	this.timer = new Timer(delay, new ActionListener() {

			@Override
            public void actionPerformed(ActionEvent e) {
                updatePosition(entity, targetSquare);
				gamePanel.repaint();
				gamePanel.revalidate();
            }
        });
        timer.start();
    }

    private void updatePosition(Entity entity, Square targetSquare) {
        
    	updatePlayerAnimation();
    	
        if (entity.getX() < targetSquare.getX()) {
        	
        	entity.setCurrentPlayerPosition(entity.getRightDirection()[spriteIndex + 1]);
        	entity.setX(entity.getX() + Math.min(entity.getSpeed(), targetSquare.getX() - entity.getX()));
        	
        } else if (entity.getX() > targetSquare.getX()) {
			
        	entity.setCurrentPlayerPosition(entity.getLeftDirection()[spriteIndex + 1]);
        	entity.setX(entity.getX() - Math.max(entity.getSpeed(), targetSquare.getX() - entity.getX()));
        	
		} else if (entity.getY() < targetSquare.getY()) {
			
			entity.setCurrentPlayerPosition(entity.getDownDirection()[spriteIndex + 1]);
        	entity.setY(entity.getY() + Math.min(entity.getSpeed(), targetSquare.getY() - entity.getY()));
        	
		} else if (entity.getY() > targetSquare.getY()) {
			
			entity.setCurrentPlayerPosition(entity.getUpDirection()[spriteIndex + 1]);
        	entity.setY(entity.getY() - Math.max(entity.getSpeed(), targetSquare.getY() - entity.getY()));
        	
		} else {
			
			entity.setCurrentPlayerPosition(entity.getRightDirection()[0]);
			timer.stop();
		}
    }
    
    private void updatePlayerAnimation() {
		
		spriteCounter++;
		
		if (spriteCounter > 10) {
			
			spriteIndex = (spriteIndex + 1) % 2;
			spriteCounter = 0;
		}
		
	}
	
}
