package castleGame;

public class Handler {
	
	public Game game;
	
	public Handler(Game game) {
		this.game = game;
	}
	
	public void doCmd(String arg0) {};
	public boolean isExit() {
		return false;
	};

}
