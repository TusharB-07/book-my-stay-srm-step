import java.util.HashMap;

/**
 * Book My Stay Application
 * Room Search Service
 *
 * @author Student
 * @version 4.0
 */

class Inventory{

    private HashMap<String,Integer> rooms;

    public Inventory(){

        rooms=new HashMap<>();

        rooms.put("Single Room",5);
        rooms.put("Double Room",0);
        rooms.put("Suite Room",2);
    }

    public HashMap<String,Integer> getRooms(){

        return rooms;
    }
}

class RoomSearchService{

    public void showAvailableRooms(Inventory inv){

        System.out.println("Book My Stay App v4.0");
        System.out.println();

        System.out.println("Available Rooms:");
        System.out.println();

        for(String type:inv.getRooms().keySet()){

            int count=inv.getRooms().get(type);

            if(count>0){

                System.out.println("Room Type : "+type);
                System.out.println("Available : "+count);
                System.out.println();
            }
        }
    }
}

public class RoomSearch{

    public static void main(String[] args){

        Inventory inv=new Inventory();

        RoomSearchService search=new RoomSearchService();

        search.showAvailableRooms(inv);

    }
}