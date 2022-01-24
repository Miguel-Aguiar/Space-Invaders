package com.empresa.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.empresa.main.Game;
import com.empresa.main.Menu;
import com.empresa.main.Sound;
import com.empresa.world.Camera;

public class Player extends Entity{
	
	public boolean right, left, up, down;
	public boolean isShooting;
	public static int gunLevel = 1;
	public boolean first = true;
	public boolean second = true;
	public boolean third = true;
	public boolean fourth = true;
	public boolean fifth = true;
	public boolean sixth = true;
	
	public int frames;
	public int maxFrames = 10;
	public int curSprite;
	public int maxSprite = Entity.PLAYER_PROP.length;
	
	public int shootFrames;
	public boolean shootDelay;
	
	public int damageFrames;
	public boolean isDamaged;
	
	public Player(int x, int y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
	}
	 
	public void tick() {
		/*Movimentação*/
		if(right) {
			x+=speed;
		}else if(left) {
			x-=speed;
		}
		if(up) {
			if(y > 0)
				y-=speed;
		}else if(down) {
			if(y < Game.HEIGHT-10)
				y+=speed;
		}
		if(x >= Game.WIDTH) {
			x = -16;
		}else if(x+16 < 0) {
			x = Game.WIDTH;
		}
		/*Sistema de tiro*/
		if(shootDelay) {
			shootFrames++;
			if(shootFrames >= 10) {
				shootFrames = 0;
				shootDelay = false;
			}
		}
		if(isShooting) {
			isShooting = false;
			if(!shootDelay) {
				shootDelay = true;
				checkGunLevel();
				Sound.laser.play();
			}
		}
		/*Vida do player*/
		for(int i = 0; i < Game.entities.size(); i++) {
			 Entity e = Game.entities.get(i);
			 if(e instanceof Enemy) {
				 if(isColliding(this, e)) {
					 Game.entities.remove(e);
					 Game.life--;
					 isDamaged = true;
				 }
			 }
		}
		
		for(int i = 0; i < Game.entities.size(); i++) {
			 Entity e = Game.entities.get(i);
			 if(e instanceof EnemyBullet) {
				 if(isColliding(e, this)) {
					 Game.entities.remove(e);
					 Game.life--;
					 isDamaged = true;
				 }
			 }
		}
		
		if(isDamaged) {
			damageFrames++;
			if(damageFrames == 5){
				damageFrames = 0;
				isDamaged = false;
			}
		}
		
		/*GunLevel*/
		checkGunUpgrade();
		
		//Animação
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			curSprite++;
			if(curSprite == maxSprite) {
				curSprite = 0;
			}
		}
		
		/*Vida++*/
		checkScoreLife();
		
		/*Morte*/
		if(Game.life == 0) {
			 Game.gameState = "Menu";
			 Menu.restartGame = true;
		}
	}
	
	public void render(Graphics g) {
		if(!isDamaged) {
			g.drawImage(Entity.PLAYER_SPRITE, getX() - Camera.x, getY() - Camera.y, null);
		}else {
			g.drawImage(Entity.PLAYER_HIT_SPRITE, getX() - Camera.x, getY() - Camera.y, null);
		}
		g.drawImage(Entity.PLAYER_PROP[curSprite], getX()+1 - Camera.x, getY()+9 - Camera.y, null);
	}
	
	public void checkGunLevel() {
		int xx = getX();
		int yy = getY();
		
		if(gunLevel == 1) {
			Bullet bullet = new Bullet(xx+7, yy-2, 1, 5, 3, null);
			Game.entities.add(bullet);
		}else if(gunLevel == 2) {
			Bullet bullet = new Bullet(xx+7, yy-2, 1, 5, 3, null);
			Bullet bullet2 = new Bullet(xx+4, yy+3, 1, 5, 3, null);
			Game.entities.add(bullet);
			Game.entities.add(bullet2);
		}else if(gunLevel == 3) {
			Bullet bullet = new Bullet(xx+7, yy-2, 1, 5, 3, null);
			Bullet bullet2 = new Bullet(xx+4, yy+3, 1, 5, 3, null);
			Bullet bullet3 = new Bullet(xx+10, yy+3, 1, 5, 3, null);
			Game.entities.add(bullet);
			Game.entities.add(bullet2);
			Game.entities.add(bullet3);
		}else if(gunLevel == 4) {
			Bullet bullet = new Bullet(xx+7, yy-2, 1, 5, 3, null);
			Bullet bullet2 = new Bullet(xx+4, yy+3, 1, 5, 3, null);
			Bullet bullet3 = new Bullet(xx+10, yy+3, 1, 5, 3, null);
			Bullet bullet4 = new Bullet(xx+1, yy+3, 1, 5, 3, null);
			Game.entities.add(bullet);
			Game.entities.add(bullet2);
			Game.entities.add(bullet3);
			Game.entities.add(bullet4);
		}else if(gunLevel == 5) {
			Bullet bullet = new Bullet(xx+7, yy-2, 1, 5, 3, null);
			Bullet bullet2 = new Bullet(xx+4, yy+3, 1, 5, 3, null);
			Bullet bullet3 = new Bullet(xx+10, yy+3, 1, 5, 3, null);
			Bullet bullet4 = new Bullet(xx+1, yy+3, 1, 5, 3, null);
			Bullet bullet5 = new Bullet(xx+13, yy+3, 1, 5, 3, null);
			Game.entities.add(bullet);
			Game.entities.add(bullet2);
			Game.entities.add(bullet3);
			Game.entities.add(bullet4);
			Game.entities.add(bullet5);
		}else if(gunLevel == 6) {
			Bullet bullet = new Bullet(xx+7, yy-2, 1, 5, 3, null);
			Bullet bullet2 = new Bullet(xx+4, yy+3, 1, 5, 3, null);
			Bullet bullet3 = new Bullet(xx+10, yy+3, 1, 5, 3, null);
			Bullet bullet4 = new Bullet(xx+1, yy+3, 1, 5, 3, null);
			Bullet bullet5 = new Bullet(xx+13, yy+3, 1, 5, 3, null);
			Bullet bullet6 = new Bullet(xx-2, yy+3, 1, 5, 3, null);
			Game.entities.add(bullet);
			Game.entities.add(bullet2);
			Game.entities.add(bullet3);
			Game.entities.add(bullet4);
			Game.entities.add(bullet5);
			Game.entities.add(bullet6);
		}else if(gunLevel == 7) {
			Bullet bullet = new Bullet(xx+7, yy-2, 1, 5, 3, null);
			Bullet bullet2 = new Bullet(xx+4, yy+3, 1, 5, 3, null);
			Bullet bullet3 = new Bullet(xx+10, yy+3, 1, 5, 3, null);
			Bullet bullet4 = new Bullet(xx+1, yy+3, 1, 5, 3, null);
			Bullet bullet5 = new Bullet(xx+13, yy+3, 1, 5, 3, null);
			Bullet bullet6 = new Bullet(xx-2, yy+3, 1, 5, 3, null);
			Bullet bullet7 = new Bullet(xx+16, yy+3, 1, 5, 3, null);
			Game.entities.add(bullet);
			Game.entities.add(bullet2);
			Game.entities.add(bullet3);
			Game.entities.add(bullet4);
			Game.entities.add(bullet5);
			Game.entities.add(bullet6);
			Game.entities.add(bullet7);
		}
	}
	
	public void checkGunUpgrade() {
		if(Game.score >= 5100) {
			gunLevel = 7;
		}else if(Game.score >= 4250) {
			gunLevel = 6;
		}else if(Game.score >= 3400) {
			gunLevel = 5;
		}else if(Game.score >= 2550) {
			gunLevel = 4;
		}else if(Game.score >= 1700) {
			gunLevel = 3;
		}else if(Game.score >= 850) {
			gunLevel = 2;
		}
	}
	
	public void checkScoreLife() {
		if(Game.score >= 800) {
			if(first) {
				first = false;
				Game.life++;
			}
			if(Game.score >= 1600) {
				if(second) {
					second = false;
					Game.life++;
				}
				if(Game.score >= 2400) {
					if(third) {
						third = false;
						Game.life++;
					}
					if(Game.score >= 3200) {
						if(fourth) {
							fourth = false;
							Game.life++;
						}
						if(Game.score >= 4000) {
							if(fifth) {
								fifth = false;
								Game.life++;
							}
							if(Game.score >= 4800) {
								if(sixth) {
									sixth = false;
									Game.life++;
								}
							}
						}
					}
				}
			}
		}
	}
}
