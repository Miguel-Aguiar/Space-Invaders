package com.empresa.entities;

import java.awt.image.BufferedImage;

import com.empresa.main.Game;

public class Boss extends Enemy{
	
	public int dir;
	public int frames;

	public Boss(int x, int y, int width, int height, double speed, int life, BufferedImage sprite) {
		super(x, y, width, height, speed, life, sprite);
	}
	
	public void tick() {
		if(Game.enemies.size() == 0) {
			Game.entities.remove(this);
		}
		if(y < 10) {
			y+=speed;
			dir = 1;
		}else if(dir == 1) {
			x+=speed;
			if(x > Game.WIDTH-48) {
				dir = -1;
			}
		}else if(dir == -1) {
			x-=speed;
			if(x < 0) {
				dir = 1;
			}
		}
		
		/*Sistema de tiro*/
		frames++;
		if(frames == 15) {
			int xx = getX();
			int yy = getY();
			EnemyBullet bullet1 = new EnemyBullet(xx+7, yy+29, 1, 5, 1, null);
			Game.entities.add(bullet1);
		}
		if(frames == 30) {
			int xx = getX();
			int yy = getY();
			EnemyBullet bullet2 = new EnemyBullet(xx+10, yy+29, 1, 5, 1, null);
			Game.entities.add(bullet2);
		}
		if(frames == 45) {
			int xx = getX();
			int yy = getY();
			EnemyBullet bullet3 = new EnemyBullet(xx+37, yy+29, 1, 5, 1, null);
			Game.entities.add(bullet3);
		}
		if(frames == 60) {
			int xx = getX();
			int yy = getY();
			EnemyBullet bullet4 = new EnemyBullet(xx+40, yy+29, 1, 5, 1, null);
			Game.entities.add(bullet4);
			frames = 0;
		}
	}
}
