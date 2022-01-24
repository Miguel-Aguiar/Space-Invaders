package com.empresa.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.empresa.main.Game;
import com.empresa.main.Sound;

public class Turbine extends Enemy{
	
	public int dir, timer;
	public boolean hit;
	public static boolean explode;
	
	public static int curSprite;
	public int curTime;
	public int maxSprite = Entity.EXPLOSION_SPRITE2.length;
	public int maxTime = 10;

	public Turbine(int x, int y, int width, int height, double speed, int life, BufferedImage sprite) {
		super(x, y, width, height, speed, life, sprite);
	}
	
	public void tick() {
		if(y < 14) {
			y+=speed;
			dir = 1;
		}else if(dir == 1) {
			timer++;
			x+=speed;
			if(timer == 145) {
				timer = 0;
				dir = -1;
			}
		}else if(dir == -1) {
			timer++;
			x-=speed;
			if(timer == 146) {
				timer = 0;
				dir = 1;
			}
		}
		
		for(int i = 0; i < Game.entities.size(); i++) {
			 Entity e = Game.entities.get(i);
			 if(e instanceof Bullet) {
				 if(isColliding(e, this)) {
					 Game.entities.remove(i);
					 //Sound.hit.play();
					 life--;
					 hit = true;
					 if(life == 0) {
						explode = true;
						Sound.explosion.play();
						Game.score+= 100;
						Game.entities.remove(this);
						Game.enemies.remove(this);
						if(Game.enemies.size() == 0) {
							Game.nextLevel = true;
						}
						 return;
					 }
					 break;
				 }
			 }
		}
	}
	
	public void render(Graphics g) {
		curTime++;
		if(curTime == maxTime) {
			curTime = 0;
			curSprite++;
			if(curSprite == maxSprite)
				curSprite = 0;
		}
		
		if(hit) {
			hit = false;
			g.drawImage(Entity.BOSS_HIT, (int)x, (int)y, null);
		}
		//g.setColor(Color.white);
		//g.drawRect(getX(),getY(),16,16);
	}
}
