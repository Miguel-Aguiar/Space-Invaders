package com.empresa.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.empresa.main.Game;

public class EnemyBullet extends Entity{

	public EnemyBullet(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		y+=speed;
		if(y > Game.HEIGHT) {
			Game.entities.remove(this);
			return;
		}
	}

	public void render(Graphics g){
		g.setColor(Color.yellow);
		g.fillRect(getX(), getY(), width, height);
	}
		
}
