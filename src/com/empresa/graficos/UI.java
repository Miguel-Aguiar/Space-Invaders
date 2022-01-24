package com.empresa.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.empresa.main.Game;

public class UI {

	public void render(Graphics g) {
		if(Game.gameState == "Menu") {
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD, 20));
			g.drawString("Comandos:", 5, 450);
			g.drawString("W / Cima", 5, 500);
			g.drawString("S / Baixo", 5, 525);
			g.drawString("A / Esquerda", 5, 550);
			g.drawString("D / Direita", 5, 575);
			g.drawString("M / Z - atirar", 5, 600);
		}
		if(Game.gameState == "Jogando") {
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD, 19));
			g.drawString("Score: " + Game.score ,10, 20);
			
			g.drawString("Vida : " + Game.life, 400, 20);
		}
		
		if(Game.gameState == "MenuInicial") {
			g.drawImage(Game.bg_menu_inicial, 0, 0, null);
		}
	}
}
