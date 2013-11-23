package eecs285;

public class Driver {

	private Player player1;
	private Player player2;
	private GameWindow gameWindow;
	
	public Driver(Player player1, Player player2, GameWindow gameWindow){
		this.player1 = player1;
		this.player2 = player2;
		this.gameWindow = gameWindow;
	}
	
	public void run(){
	    long lastLoopTime = System.nanoTime();
	    final int TARGET_FPS = 60;
	    final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	    long lastFpsTime = 0;
	    while(true){
	        long now = System.nanoTime();
	        long updateLength = now - lastLoopTime;
	        lastLoopTime = now;
	        double delta = updateLength / ((double)OPTIMAL_TIME);

	        lastFpsTime += updateLength;
	        if(lastFpsTime >= 1000000000){
	            lastFpsTime = 0;
	        }

	        this.update(delta);

	        try{
	     //       Room.gameTime = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
	     //       System.out.println(Room.gameTime);
	     //       Thread.sleep(Room.gameTime);
	        }catch(Exception e){
	        
	        }
	    }
	}
	
	public void update(double delta){
		//update player 1
		//update player 2
		//update gameWindow
	}

}
