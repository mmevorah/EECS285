package eecs285;

public class Game {

	private Player player1;
	private Player player2;
	private GameWindow gameWindow;
	private Driver driver;
	
	public Game(/*wall paper, character1, character2*/){
		this.player1 = new Player();
		this.player2 = new Player();
		this.gameWindow = new GameWindow();
		driver = new Driver(player1, player2, gameWindow);
		driver.run();
	}
	
}
