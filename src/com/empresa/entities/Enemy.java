package com.empresa.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.empresa.main.Game;
import com.empresa.main.Sound;

public class Enemy extends Entity{
	
	public int life;
	
	public Enemy(int x, int y, int width, int height, double speed, int life, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		this.life = life;
	}
	
	public void tick() {
		y+=speed;
		if(y >= Game.HEIGHT) {
			Game.entities.remove(this);
			return;
		}
		
		for(int i = 0; i < Game.entities.size(); i++) {
			 Entity e = Game.entities.get(i);
			 if(e instanceof Bullet) {
				 if(isColliding(e, this)) {
					 Game.entities.remove(i);
					 life--;
					 if(life == 0) {
						 Sound.explosion.play();
						 Explosion explosion = new Explosion(getX(), getY(), 16, 16, 0, null);
						 Game.entities.add(explosion);
						 Game.score+= 25;
						 Game.entities.remove(this);
						 return;
					 }
					 break;
				 }
			 }
		}	
	}
	
	public void render(Graphics g) {	
		super.render(g);
	}
}
