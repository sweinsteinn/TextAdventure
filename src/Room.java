import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
public class Room {
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Item> inventory;
    private String longDescription;
    public Room (String description, String longDescription){
        this.description = description;
        this.longDescription = longDescription;
        exits = new HashMap<String, Room>();
        inventory = new HashMap<String, Item>();
    }
    private String getExitString() {
        String returnString = "Exit";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += "  " + exits;
        }
        return returnString;
    }
    public String getLongDescription(){
        return longDescription + "/n" + getExitString() + "\n" + getItemString();
    }
        public void setExit(String direction, Room neighbor){
        exits.put(direction,neighbor);
    }
    public Room getExit(String direction){
        return exits.get(direction);
    }
    public void setItem(String name, Item item){
        inventory.put(name, item);
    }
    public Item getItem(String name){
        return inventory.remove(name);

    }
    public String getItemString(){
        String returnString = "Room Inv:";
        Set<String> keys = inventory.keySet();
        for(String item: keys){
            returnString += " " + item;
        }
        return returnString;
    }
    public String getShortDescription(){
        return description;
    }
}
