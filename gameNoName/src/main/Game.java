package main;

public class Game implements Runnable{
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120; //Max FPS
	
	//constructor
	public Game() {
		gamePanel = new GamePanel(); //Creates Frame
		gameWindow = new GameWindow(gamePanel); //Creates Window
		gamePanel.requestFocus();
		startGameLoop();
		
	}
	
	//Starts Game loop
	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	//Game Loop
	public void run() {
		//FPS LIMITER
		double timePerFrame = 1000000000.0 / FPS_SET; //in Nanoseconds
		long lastFrame = System.nanoTime();
		long now = System.nanoTime();
		
		int frames = 0;
		long lastCheck = System.currentTimeMillis();
		
		while (true) { 
			now = System.nanoTime();
			if (now - lastFrame >= timePerFrame) {
				gamePanel.repaint(); //refreshes the frame
				lastFrame = now;
				frames++;
			}
			
			//FPS counter
			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS " + frames);
				frames = 0;
			}
			
		}
		
	}
}
