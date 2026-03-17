import java.util.HashMap;

/**
 * Book My Stay Application
 * Centralized Room Inventory
 *
 * @author Student
 * @version 3.0
 */

class InventoryService{

    private HashMap<String,Integer> inventory;

    public InventoryService(){

        inventory=new HashMap<>();

        inventory.put("Single Room",5);
        inventory.put("Double Room",3);
        inventory.put("Suite Room",2);
    }

    public void displayInventory(){

        System.out.println("Current Inventory");

        for(String room:inventory.keySet()){

            System.out.println(room+" : "+inventory.get(room));
        }
    }

    public int getAvailability(String type){

        return inventory.getOrDefault(type,0);
    }

    public void updateAvailability(String type,int count){

        inventory.put(type,count);
    }
}

public class RoomInventory{

    public static void main(String[] args){

        System.out.println("Book My Stay App v3.0");
        System.out.println();

        InventoryService service=new InventoryService();

        service.displayInventory();

    }
}