package eecs285;

import java.io.IOException;

import Networking.ClientServerSocket;

public class Driver {

	private Player player1;
	private Player player2;
	private GameWindow gameWindow;
	
    ClientServerSocket network;
	
	
	public Driver(Player player1, Player player2, GameWindow gameWindow, ClientServerSocket network){
		this.player1 = player1;
		this.player2 = player2;
		this.gameWindow = gameWindow;
		
		this.network = network;
		
	}
	
	public void run(){
	    long lastLoopTime = System.nanoTime();
	    final int TARGET_FPS = 20;
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
		//update player 1 info
		//send updated player 1 information

		char a = 0;
		try {
			a = (char)System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = "" + a;
		System.out.println(s);
		
		//get player 2 information
		//String recieved = network.recvString();
		//update player 2
		
		network.sendString(s);
		
		
		String fromInternet = network.recvString();
		
		//System.out.println(recieved);
		
		//render player 1
		//render player 2
		//render game window
		
	}

}
