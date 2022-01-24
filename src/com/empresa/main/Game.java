package com.empresa.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.empresa.entities.Entity;
import com.empresa.entities.Player;
import com.empresa.entities.Turbine;
import com.empresa.graficos.Spritesheet;
import com.empresa.graficos.UI;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener{
	
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	public Thread thread;
	public boolean isRunning;
	public final static int WIDTH = 120;
	public final static int HEIGHT = 160;
	public final static int SCALE = 4;
	public BufferedImage image;
	
	public static List<Entity> entities;
	public static List<Turbine> enemies;
	public static Spritesheet spritesheet;
	public static Player player;
	public static EnemySpawn enemySpawn;
	public static Menu menu;
	
	public static String gameState = "MenuInicial";
	
	public static int score;
	public static int life = 3;
	public static int level = 1;
	public static boolean nextLevel;
	
	public static boolean[] unlockLevel = {true, false, false, false, false};
	
	public BufferedImage bg;
	public BufferedImage bg2;
	public BufferedImage bg_terra;
	public int backY = 0;
	public int backY2 = HEIGHT;
	public int backSpd = 1;
	
	public static BufferedImage bg_menu_inicial;
	
	
	public UI ui;
	
	public Game() {
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		//Inicializando objetos
		spritesheet = new Spritesheet("/spritesheet.png");
		player = new Player(WIDTH/2-8, HEIGHT-16, 16, 16, 1.5, Entity.PLAYER_SPRITE);
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Turbine>();
		menu = new Menu();
		enemySpawn = new EnemySpawn();
		ui = new UI();
		entities.add(player);
		
		try {
			bg_menu_inicial = ImageIO.read(getClass().getResource("/menu_inicial.png"));
			bg = ImageIO.read(getClass().getResource("/bg.png"));
			bg2 = ImageIO.read(getClass().getResource("/bg.png"));
			bg_terra = ImageIO.read(getClass().getResource("/bg_terra.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initFrame() {
		frame = new JFrame("Space Protector");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();	
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[]args) {
		Game game = new Game();
		game.start();
	}
	
	public void tick() {
		if(nextLevel) {
			gameState = "Menu";
		}
		
		if(gameState == "Jogando") {
			for(int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.tick();
			}
			enemySpawn.tick();
			
			/*Background*/
			backY+=backSpd;
			if(backY-160 >= 0) {
				backY = -160;
			}
			backY2+=backSpd;
			if(backY2-160 >= 0) {
				backY2 = -160;
			}
			/******/
		}else if(gameState == "Menu" || gameState == "MenuInicial") {
			menu.tick();
		}
		
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(bg, 0, backY, null);
		g.drawImage(bg, 0, backY2, null);
			
		/* Renderização do jogo */
		Collections.sort(entities, Entity.nodeSorter);
			
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		/***/
		/*Renderização do menu*/
		
		if(gameState == "Menu") {
			menu.render(g);
		}
		
		/******/
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image,0,0,WIDTH*SCALE,HEIGHT*SCALE,null);
		ui.render(g);
		bs.show();
	}

	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		while(isRunning) {
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >=1) {
				tick();
				render();
				frames++;
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer+=1000;
			}
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}else if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		}else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_Z || e.getKeyCode() == KeyEvent.VK_M) {
			player.isShooting = true;	
		}
		
		if(gameState == "Menu") {
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				menu.up = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				menu.down = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				menu.enter = true;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(gameState == "Jogando") {
				menu.start = true;
				gameState = "Menu";
				menu.pause = true;
			}
		}
		
		if(gameState == "MenuInicial") {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				menu.enter = true;
			}
		}
		
		if(Menu.restartGame) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				menu.enter = true;
			}
		}
		
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}else if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		}else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_Z || e.getKeyCode() == KeyEvent.VK_M) {
			player.isShooting = false;
		}
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}
}
