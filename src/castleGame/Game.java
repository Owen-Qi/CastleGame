package castleGame;

import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private Room currentRoom;
    private HashMap<String, Handler> handler = new HashMap<String, Handler>();
       
    // ��ʼ��
    public Game() 
    {
        createRooms();
        createHandlers();
    }

    // ��������
    private void createRooms()
    {
        Room outside, lobby, pub, study, bedroom;
      
        //	���췿��
        outside = new Room("�Ǳ���");
        lobby = new Room("����");
        pub = new Room("С�ư�");
        study = new Room("�鷿");
        bedroom = new Room("����");
        
        //	��ʼ������ĳ���
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

        currentRoom = outside;  //	�ӳǱ����⿪ʼ
    }
    
    // ��������
    public void createHandlers() {
    	handler.put("bye", new Handler() {
			@Override
			public boolean isExit() { return false; }
			
			@Override
			public void doCmd(String arg0) {
		        System.out.println("��л���Ĺ��١��ټ���");
			}
		});
    	
    	handler.put("help", new Handler() {
			@Override
			public boolean isExit() { return true; }
			
			@Override
			public void doCmd(String arg0) {
		        System.out.println("��·������������������У�go bye help");
		        System.out.println("�磺\tgo east");				
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

    // ��ӡ��ӭ��Ϣ
    private void printWelcome() {
        System.out.println();
        System.out.println("��ӭ�����Ǳ���");
        System.out.println("����һ���������ĵ���Ϸ��");
        System.out.println("�����Ҫ������������ 'help' ��");
        System.out.println();
        System.out.println("��������" + currentRoom);
        System.out.print("�����У�");
        System.out.println(currentRoom.getExitDesc());
    }

    // ����
    public void goRoom(String direction) 
    {
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("����û���ţ�");
        }
        else {
            currentRoom = nextRoom;
            System.out.println("����" + currentRoom);
            System.out.print("������: ");
            System.out.println(currentRoom.getExitDesc());
        }
    }

    // ��ѭ��
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
    		
    		if (action.isExit()) { // ������˳���handler�����˳�ѭ��
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