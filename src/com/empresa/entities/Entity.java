package com.empresa.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.Random;
import com.empresa.main.Game;
import com.empresa.world.Camera;

public class Entity {
	
	public static BufferedImage PLAYER_SPRITE = Game.spritesheet.getSprite(0, 0, 16, 16);
	public static BufferedImage PLAYER_HIT_SPRITE = Game.spritesheet.getSprite(48, 0, 16, 16);
	public static BufferedImage[] PLAYER_PROP = {Game.spritesheet.getSprite(16, 0, 16, 16),
												Game.spritesheet.getSprite(32, 0, 16, 16)};
	
	public static BufferedImage[] COMET_SPRITE = {Game.spritesheet.getSprite(16, 80, 16, 16),
												Game.spritesheet.getSprite(16, 96, 16, 16)};
	
	public static BufferedImage ENEMY_SPRITE1 = Game.spritesheet.getSprite(0, 16, 16, 16);
	public static BufferedImage ENEMY_SPRITE2 = Game.spritesheet.getSprite(96, 0, 16, 16);
	public static BufferedImage ENEMY_SPRITE3 = Game.spritesheet.getSprite(96, 32, 16, 16);
	public static BufferedImage ENEMY_SPRITE4 = Game.spritesheet.getSprite(96, 80, 16, 16);
	public static BufferedImage ENEMY_SPRITE5 = Game.spritesheet.getSprite(64, 144, 16, 16);
	
	public static BufferedImage BOSS_SPRITE1 = Game.spritesheet.getSprite(0, 48, 48, 32);
	public static BufferedImage BOSS_SPRITE2 = Game.spritesheet.getSprite(112, 0, 48, 32);
	public static BufferedImage BOSS_SPRITE3 = Game.spritesheet.getSprite(112, 32, 48, 48);
	public static BufferedImage BOSS_SPRITE4 = Game.spritesheet.getSprite(112, 80, 48, 32);
	public static BufferedImage BOSS_SPRITE5 = Game.spritesheet.getSprite(96, 120, 48, 40);
	
	public static BufferedImage BOSS_HIT = Game.spritesheet.getSprite(0,80, 16, 16);
	
	
	public static BufferedImage EXPLOSION_SPRITE[] = {Game.spritesheet.getSprite(0, 32, 16, 16),
													 Game.spritesheet.getSprite(16, 32, 16, 16),
													 Game.spritesheet.getSprite(32, 32, 16, 16),
													 Game.spritesheet.getSprite(48, 32, 16, 16),
													 Game.spritesheet.getSprite(64, 32, 16, 16)};
	
	public static BufferedImage EXPLOSION_SPRITE2[] = {Game.spritesheet.getSprite(48, 48, 16, 16),
													  Game.spritesheet.getSprite(64, 48, 16, 16),
													 Game.spritesheet.getSprite(80, 48, 16, 16)};
	
	public static BufferedImage LOCK = Game.spritesheet.getSprite(80, 16, 16, 16);
	
	public double x;
	public double y;
	protected int z;
	protected int width;
	protected int height;
	protected double speed;
	
	public int depth;
	
	private BufferedImage sprite;
	
	public static Random rand = new Random();
	
	
	
	public Entity(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.sprite = sprite;
	}
	
	public static Comparator<Entity> nodeSorter = new Comparator<Entity>() {
		@Override
		public int compare(Entity n0, Entity n1) {
			if(n1.depth < n0.depth)
				return + 1;
			if(n1.depth > n0.depth)
				return - 1;
			return 0;
		}
	};
	
	public void updateCamera(){
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2), 0, Game.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2), 0, Game.HEIGHT*16 - Game.HEIGHT);
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void tick() {
	}
	
	public static boolean isColliding(Entity e1, Entity e2) {
		
		Rectangle e1Mask = new Rectangle(e1.getX(), e1.getY(), e1.width, e1.height);
		Rectangle e2Mask = new Rectangle(e2.getX(), e2.getY(), e2.width, e2.height);
		if(e1Mask.intersects(e2Mask) && e1.z == e2.z) {
			return true;
		}
		return false;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, getX() - Camera.x, getY() - Camera.y, null);
	}
}
