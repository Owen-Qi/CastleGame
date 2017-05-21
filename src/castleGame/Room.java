package castleGame;

//import castleGame.Room;

public class Room {
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;

    public Room(String description) 
    {
        this.description = description;
    }

    public void setExits(Room north, Room east, Room south, Room west) 
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
    }
    
    public String getExitDesc() {
		StringBuffer sb = new StringBuffer();
		if (northExit != null) {
			sb.append("north ");
		}
		if (southExit != null) {
			sb.append("south ");
		}
		if (eastExit != null) {
			sb.append("east ");
		}
		if (westExit != null) {
			sb.append("west ");
		}
		
		return sb.toString();
	}
    
    public Room getExit(String direction) {
		Room ret = null;
		if(direction.equals("north")) {
            ret = northExit;
        }
        if(direction.equals("east")) {
        	ret = eastExit;
        }
        if(direction.equals("south")) {
        	ret = southExit;
        }
        if(direction.equals("west")) {
        	ret = westExit;
        }
        
        return ret;
	}

    @Override
    public String toString()
    {
        return description;
    }
}
