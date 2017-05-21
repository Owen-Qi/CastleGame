package castleGame;

import java.util.HashMap;

public class Room {
    private String description;
    HashMap<String, Room> exits = new HashMap<String, Room>();

    public Room(String description) 
    {
        this.description = description;
    }
    
    public void setExit(String direction, Room room) {
		this.exits.put(direction, room);
	}
    
    public String getExitDesc() {
		StringBuffer sb = new StringBuffer();
		
		for (String direction : this.exits.keySet()) {
			sb.append(direction + " ");
		}
		
		return sb.toString();
	}
    
    public Room getExit(String direction) {
		return this.exits.get(direction);
	}

    @Override
    public String toString()
    {
        return description;
    }
}
