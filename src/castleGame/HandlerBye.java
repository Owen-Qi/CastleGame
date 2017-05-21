package castleGame;

public class HandlerBye extends Handler {

	public HandlerBye(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isExit() {
		return true;
	}

	@Override
	public void doCmd(String arg0) {
        System.out.println("感谢您的光临。再见！");
	}

}
