package castleGame;

import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private Room currentRoom;
    private HashMap<String, Handler> handler = new HashMap<String, Handler>();
       
    // 初始化
    public Game() 
    {
        createRooms();
        createHandlers();
    }

    // 构建房间
    private void createRooms()
    {
        Room outside, lobby, pub, study, bedroom;
      
        //	制造房间
        outside = new Room("城堡外");
        lobby = new Room("大堂");
        pub = new Room("小酒吧");
        study = new Room("书房");
        bedroom = new Room("卧室");
        
        //	初始化房间的出口
        outside.setExit("east", lobby);
        outside.setExit("south", study);
        outside.setExit("west", pub);
        lobby.setExit("west", outside);
        pub.setExit("east", outside);
        study.setExit("north", outside);
        study.setExit("east", bedroom);
        bedroom.setExit("west", study);
        
        lobby.setExit("up", pub);
        pub.setExit("down", lobby);

        currentRoom = outside;  //	从城堡门外开始
    }
    
    // 构建操作
    public void createHandlers() {
    	handler.put("bye", new Handler() {
			@Override
			public boolean isExit() { return false; }
			
			@Override
			public void doCmd(String arg0) {
		        System.out.println("感谢您的光临。再见！");
			}
		});
    	
    	handler.put("help", new Handler() {
			@Override
			public boolean isExit() { return true; }
			
			@Override
			public void doCmd(String arg0) {
		        System.out.println("迷路了吗？你可以做的命令有：go bye help");
		        System.out.println("如：\tgo east");				
			}
		});
    	
    	handler.put("go", new Handler() {
			@Override
			public boolean isExit() { return false; }
			
			@Override
			public void doCmd(String arg0) {
				goRoom(arg0);
			}
		});
    }

    // 打印欢迎信息
    private void printWelcome() {
        System.out.println();
        System.out.println("欢迎来到城堡！");
        System.out.println("这是一个超级无聊的游戏。");
        System.out.println("如果需要帮助，请输入 'help' 。");
        System.out.println();
        System.out.println("现在你在" + currentRoom);
        System.out.print("出口有：");
        System.out.println(currentRoom.getExitDesc());
    }

    // 操作
    public void goRoom(String direction) 
    {
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("那里没有门！");
        }
        else {
            currentRoom = nextRoom;
            System.out.println("你在" + currentRoom);
            System.out.print("出口有: ");
            System.out.println(currentRoom.getExitDesc());
        }
    }

    // 主循环
    public void play() {
		Scanner in = new Scanner(System.in);
		this.printWelcome();
		
    	while ( true ) {
    		String line = in.nextLine();
    		String[] words = line.split(" ");
    		
    		String direction = "";
    		if (words.length > 1) {
    			direction = words[1];
			}
    		
    		Handler action = this.handler.get(words[0]);
    		if (action != null) {
    			action.doCmd(direction);
			}
    		
    		if (action.isExit()) { // 如果是退出的handler，则退出循环
				break;
			}
    		
    	}
    	in.close();
	    	
    }
	
	public static void main(String[] args) {
		Game game = new Game();
		game.play();

	}

}