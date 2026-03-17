import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Book My Stay Application
 * Room Allocation Service
 *
 * @author Student
 * @version 6.0
 */

class BookingService{

    private HashMap<String,Integer> inventory;

    private HashMap<String,Set<String>> allocatedRooms;

    public BookingService(){

        inventory=new HashMap<>();

        inventory.put("Single Room",2);
        inventory.put("Double Room",2);

        allocatedRooms=new HashMap<>();

        allocatedRooms.put("Single Room",new HashSet<>());
        allocatedRooms.put("Double Room",new HashSet<>());
    }

    public void allocateRoom(String guest,String type){

        int available=inventory.getOrDefault(type,0);

        if(available>0){

            String roomId=type.charAt(0)+""+(100+available);

            allocatedRooms.get(type).add(roomId);

            inventory.put(type,available-1);

            System.out.println("Booking Confirmed");
            System.out.println("Guest : "+guest);
            System.out.println("Room Allocated : "+roomId);
            System.out.println();

        }

        else{

            System.out.println("No rooms available for "+guest);
        }
    }
}

public class RoomAllocationService{

    public static void main(String[] args){

        System.out.println("Book My Stay App v6.0");
        System.out.println();

        BookingService service=new BookingService();

        service.allocateRoom("Azam","Single Room");

        service.allocateRoom("Madhav","Double Room");

        service.allocateRoom("Soumya","Single Room");

    }
}