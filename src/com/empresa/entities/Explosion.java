package com.empresa.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.empresa.main.Game;

public class Explosion extends Entity{

	private int frames;
	private int maxFrames = 7;
	private int maxAnimation = Entity.EXPLOSION_SPRITE.length;
	private int curAnimation = 0;
	
	public Explosion(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			curAnimation++;
			if(curAnimation == maxAnimation) {
				Game.entities.remove(this);
				return;
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(Entity.EXPLOSION_SPRITE[curAnimation], getX(), getY(), null);
	}

}
