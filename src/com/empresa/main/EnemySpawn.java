package com.empresa.main;

import com.empresa.entities.Boss;
import com.empresa.entities.Enemy;
import com.empresa.entities.Entity;
import com.empresa.entities.Turbine;

public class EnemySpawn {
	
	public int timer = 30;
	public int targetTime = 60*2;
	public static int curTime;
	public static int levelTime;
	public boolean comet;
	
	public void tick() {
		levelTime++;
		if(levelTime < 60*timer) {
			curTime++;
			if(curTime == targetTime) {
				curTime = 0;
				checkEnemyLevel();
				if(levelTime >= 60*timer/2) {
					checkEnemyLevel();
				}
			}
		}else if(levelTime == 60*timer) {
			//Boss
			checkBossLevel();
		}
		checkTimer();
	}
	
	public void checkEnemyLevel() {
		int yy = 0;
		int xx = Entity.rand.nextInt(Game.WIDTH-16);
		if(Game.level == 1) {
			Enemy enemy;
			if(Entity.rand.nextInt(2) > 0) {
				enemy = new Enemy(xx, yy, 16, 16, 1.25, 2, Entity.ENEMY_SPRITE1);
			}else {
				enemy = new Enemy(xx, yy, 16, 16, 0.9, 1, Entity.COMET_SPRITE[1]);
				Enemy enemy2 = new Enemy(xx, yy-15, 16, 16, 0.9, 1, Entity.COMET_SPRITE[0]);
				Game.entities.add(enemy2);
			}
			Game.entities.add(enemy);
		}else if(Game.level == 2) {
			Enemy enemy;
			if(Entity.rand.nextInt(2) == 0) {
				enemy = new Enemy(xx, yy, 16, 16, 1.25, 4, Entity.ENEMY_SPRITE2);
			}else {
				enemy = new Enemy(xx, yy, 16, 16, 0.9, 2, Entity.COMET_SPRITE[1]);
				Enemy enemy2 = new Enemy(xx, yy-16, 16, 16, 0.9, 2, Entity.COMET_SPRITE[0]);
				Game.entities.add(enemy2);
			}
			Game.entities.add(enemy);
		}else if(Game.level == 3) {
			Enemy enemy;
			if(Entity.rand.nextInt(2) == 0) {
				enemy = new Enemy(xx, yy, 16, 16, 1.25, 6, Entity.ENEMY_SPRITE3);
			}else {
				enemy = new Enemy(xx, yy, 16, 16, 0.9, 3, Entity.COMET_SPRITE[1]);
				Enemy enemy2 = new Enemy(xx, yy-16, 16, 16, 0.9, 3, Entity.COMET_SPRITE[0]);
				Game.entities.add(enemy2);
			}
			Game.entities.add(enemy);
		}else if(Game.level == 4) {
			Enemy enemy;
			if(Entity.rand.nextInt(2) == 0) {
				enemy = new Enemy(xx, yy, 16, 16, 1.25, 8, Entity.ENEMY_SPRITE4);
			}else {
				enemy = new Enemy(xx, yy, 16, 16, 0.9, 4, Entity.COMET_SPRITE[1]);
				Enemy enemy2 = new Enemy(xx, yy-16, 16, 16, 0.9, 4, Entity.COMET_SPRITE[0]);
				Game.entities.add(enemy2);
			}
			Game.entities.add(enemy);
		}else if(Game.level == 5) {
			Enemy enemy;
			if(Entity.rand.nextInt(2) == 0) {
				enemy = new Enemy(xx, yy, 16, 16, 1.25, 10, Entity.ENEMY_SPRITE5);
			}else {
				enemy = new Enemy(xx, yy, 16, 16, 0.9, 5, Entity.COMET_SPRITE[1]);
				Enemy enemy2 = new Enemy(xx, yy-16, 16, 16, 0.9, 5, Entity.COMET_SPRITE[0]);
				Game.entities.add(enemy2);
			}
			Game.entities.add(enemy);
		}
	}
	
	public void checkBossLevel() {
		if(Game.level == 1) {
			Boss boss = new Boss(0, 0-30, 48, 32, 0.5, 1000, Entity.BOSS_SPRITE1);
			Turbine turbine1 = new Turbine(3, 0-26, 16, 16, 0.5, 20, null);
			Turbine turbine2 = new Turbine(29, 0-26, 16, 16, 0.5, 20, null);
			Game.entities.add(boss);
			Game.entities.add(turbine1);
			Game.entities.add(turbine2);
			Game.enemies.add(turbine1);
			Game.enemies.add(turbine2);
		}else if(Game.level == 2) {
			Boss boss = new Boss(0, 0-30, 48, 32, 0.5, 1000, Entity.BOSS_SPRITE2);
			Turbine turbine1 = new Turbine(3, 0-26, 16, 16, 0.5, 30, null);
			Turbine turbine2 = new Turbine(29, 0-26, 16, 16, 0.5, 30, null);
			Game.entities.add(boss);
			Game.entities.add(turbine1);
			Game.entities.add(turbine2);
			Game.enemies.add(turbine1);
			Game.enemies.add(turbine2);
		}else if(Game.level == 3) {
			Boss boss = new Boss(0, 0-30, 32, 48, 0.5, 1000, Entity.BOSS_SPRITE3);
			Turbine turbine1 = new Turbine(3, 0-26, 16, 16, 0.5, 40, null);
			Turbine turbine2 = new Turbine(29, 0-26, 16, 16, 0.5, 40, null);
			Game.entities.add(boss);
			Game.entities.add(turbine1);
			Game.entities.add(turbine2);
			Game.enemies.add(turbine1);
			Game.enemies.add(turbine2);
		}else if(Game.level == 4) {
			Boss boss = new Boss(0, 0-30, 48, 32, 0.5, 1000, Entity.BOSS_SPRITE4);
			Turbine turbine1 = new Turbine(3, 0-26, 16, 16, 0.5, 50, null);
			Turbine turbine2 = new Turbine(29, 0-26, 16, 16, 0.5, 50, null);
			Game.entities.add(boss);
			Game.entities.add(turbine1);
			Game.entities.add(turbine2);
			Game.enemies.add(turbine1);
			Game.enemies.add(turbine2);
		}else if(Game.level == 5) {
			Boss boss = new Boss(0, 0-30, 48, 32, 0.5, 1000, Entity.BOSS_SPRITE5);
			Turbine turbine1 = new Turbine(3, 0-26, 16, 16, 0.5, 60, null);
			Turbine turbine2 = new Turbine(29, 0-26, 16, 16, 0.5, 60, null);
			Game.entities.add(boss);
			Game.entities.add(turbine1);
			Game.entities.add(turbine2);
			Game.enemies.add(turbine1);
			Game.enemies.add(turbine2);
		}
	}
	
	public static void checkTimer() {
		int i = 0;
		if(Game.nextLevel || Menu.restartGame) {
			i++;
			Game.unlockLevel[i] = true;
			curTime = 0;
			levelTime = 0;
		}
	}
}
