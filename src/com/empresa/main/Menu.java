package com.empresa.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.empresa.entities.Entity;


public class Menu {
	
	public String[] options = {"New Game", "SelectLevel", "Exit"};
	public String[] levels = {"1", "2", "3", "4", "5", "Back"};
	public boolean up, down, enter, pause, start = true, selectLevel, creditos, menuInicial;
	public static boolean restartGame;
	public static boolean end = false;
	public int currentOption = 0;
	public int maxOption = options.length - 1;
	public int currentOption2 = 0;
	public int maxOption2 = levels.length - 1;
	
	public double frames;
	
	public void tick() {
		//Menu Inicial
		if(Game.gameState == "MenuInicial") {
			if(enter) {
				enter = false;
				Game.gameState = "Menu";
			}
		}
		
		//Restart game
		if(restartGame) {
			if(enter) {
				enter = false;
				restartGame = false;
				EnemySpawn.checkTimer();
				Game.gameState = "Jogando";
				Game.life = 3;
			}
		}
		
		//End Game
		if(end) {
			if(enter) {
				Game.level = 0;
				enter = false;
				end = false;
				creditos = true;
			}
		}
		
		if(creditos) {
			frames+= 0.5;
			if(frames >= 350) {
				creditos = false;
				Game.gameState = "MenuInicial";
			}
			
		}
		
		//Next Level
		if(Game.nextLevel) {
			if(Game.level == 5) {
				enter = false;
				Game.nextLevel = false;
				end = true;
				return;
			}
			if(enter) {
				enter = false;
				Game.level++;
				Game.nextLevel = false; 
				Game.gameState = "Jogando";
				start = false;
			}
		}
		//Comandos do menu
		if(start) {
			if(up) {
				up = false;
				currentOption--;
				if(currentOption < 0)
					currentOption = maxOption;
			}
			if(down) {
				down = false;
				currentOption++;
				if(currentOption > maxOption)
					currentOption = 0;
			}
			if(enter) {
				enter = false;
				if(options[currentOption] == "New Game") {
					start = false;
					Game.gameState = "Jogando";
				}
				
				if(options[currentOption] == "SelectLevel") {
					//Selecionar nível
					start = false;
					selectLevel = true;
				}
				
				if(options[currentOption] == "Exit") {
					System.exit(1);
				}
			}
		}
		//Comandos do Selecionar nível
		if(selectLevel) {
			if(up) {
				up = false;
				currentOption2--;
				if(currentOption2 < 0)
					currentOption2 = maxOption2;
			}
			if(down) {
				down = false;
				currentOption2++;
				if(currentOption2 > maxOption2)
					currentOption2 = 0;
			}
			if(enter) {
				enter = false;
				if(levels[currentOption2] == "1") {
					if(Game.unlockLevel[0] == true) {
						selectLevel = false;
						Game.level = 1;
						Game.gameState = "Jogando";
					}
				}else if(levels[currentOption2] == "2") {
					if(Game.unlockLevel[1] == true) {
						selectLevel = false;
						Game.level = 2;
						Game.gameState = "Jogando";
					}
				}else if(levels[currentOption2] == "3") {
					if(Game.unlockLevel[2] == true) {
						selectLevel = false;
						Game.level = 3;
						Game.gameState = "Jogando";
					}
				}else if(levels[currentOption2] == "4") {
					if(Game.unlockLevel[3] == true) {
						selectLevel = false;
						Game.level = 4;
						Game.gameState = "Jogando";
					}
				}else if(levels[currentOption2] == "5") {
					if(Game.unlockLevel[4] == true) {
						selectLevel = false;
						Game.level = 5;
						Game.gameState = "Jogando";
					}
				}else if(levels[currentOption2] == "Back") {
					selectLevel = false;
					start = true;
				}
			}
		}
		
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 0, 0, 220));
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		
		//Menu
		if(creditos) {
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD, 10));
			g.drawString("Game Developer:", 5, 180-(int)frames);
			g.drawString("Miguel Aguiar", 5, 200-(int)frames);
			g.drawString("Game Designer:", 5, 240-(int)frames);
			g.drawString("Guilherme Aguiar", 5, 260-(int)frames);
			
			g.drawString("Muito obrigado por", 5, 300-(int)frames);
			g.drawString("jogar nosso jogo", 5, 310-(int)frames);
			
			g.drawString("Esperamos que", 5, 330-(int)frames);
			g.drawString("tenha se divertido!", 5, 340-(int)frames);
		}
		
		if(end) {
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD, 10));
			g.drawString("Parabéns,", 36, 60);
			g.drawString(" você finalizou o jogo!!", 5, 80);
		}
		
		if(start) {
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD, 10));
			if(pause) {
				g.drawString("Continue", 39, 50);
			}else {
				g.drawString("New Game", 34, 50);
			}
			g.drawString("Select Level", 30, 70);
			g.drawString("Exit", 50, 90);
			
			//Marcação
			g.setColor(Color.red);
			if(options[currentOption] == "New Game") {
				if(!pause) 
					g.drawRect(33, 41, 55, 10);
				else 
					g.drawRect(38, 41, 45, 10);
			}
			if(options[currentOption] == "SelectLevel") {
				g.drawRect(29, 61, 62, 10);
			}
			if(options[currentOption] == "Exit") {
				g.drawRect(49, 81, 20, 10);
			}
			
		//Selecionar nível
		}else if(selectLevel) {
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD, 10));
			
			g.drawString("Level 1", 42, 30);
			if(Game.unlockLevel[0] == false) {
				g.drawImage(Entity.LOCK, 72, 30, null);
			}
			g.drawString("Level 2", 42, 50);
			if(Game.unlockLevel[1] == false) {
				g.drawImage(Entity.LOCK, 80, 50-11, null);
			}
			g.drawString("Level 3", 42, 70);
			if(Game.unlockLevel[2] == false) {
				g.drawImage(Entity.LOCK, 80, 70-11, null);
			}
			g.drawString("Level 4", 42, 90);
			if(Game.unlockLevel[3] == false) {
				g.drawImage(Entity.LOCK, 80, 90-11, null);
			}
			g.drawString("Level 5", 42, 110);
			if(Game.unlockLevel[4] == false) {
				g.drawImage(Entity.LOCK, 80, 110-11, null);
			}
			g.drawString("Back", 48, 130);
			
			//Marcação
			g.setColor(Color.red);
			if(levels[currentOption2] == "1") {
				g.drawRect(41, 21, 38, 10);
			}else if(levels[currentOption2] == "2") {
				g.drawRect(41, 41, 38, 10);
			}else if(levels[currentOption2] == "3") {
				g.drawRect(41, 61, 38, 10);
			}else if(levels[currentOption2] == "4") {
				g.drawRect(41, 81, 38, 10);
			}if(levels[currentOption2] == "5") {
				g.drawRect(41, 101, 38, 10);
			}else if(levels[currentOption2] == "Back") {
				g.drawRect(47, 121, 26, 10);
			}
		}else if(Game.nextLevel) {
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD, 15));
			g.drawString("Next Level", 22, 60);
			g.setColor(Color.red);
			g.drawRect(20,45,80,18);
		}
		//Restart Game
		if(restartGame) {
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD, 13));
			g.drawString("Restart Level", 17, 60);
		}
		
	}
	
}
