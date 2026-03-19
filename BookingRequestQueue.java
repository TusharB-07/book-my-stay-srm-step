import java.util.LinkedList;
import java.util.Queue;

/**
 * Book My Stay Application
 * Booking Request Queue
 *
 * @author Student
 * @version 5.0
 */

class Reservation{

    String guestName;
    String roomType;

    public Reservation(String guestName,String roomType){

        this.guestName=guestName;
        this.roomType=roomType;
    }

    public void display(){

        System.out.println(guestName+" requested "+roomType);
    }
}

public class BookingRequestQueue{

    public static void main(String[] args){

        System.out.println("Book My Stay App v5.0");
        System.out.println();

        Queue<Reservation> queue=new LinkedList<>();

        queue.add(new Reservation("Azam","Single Room"));
        queue.add(new Reservation("Madhav","Double Room"));
        queue.add(new Reservation("Soumya","Suite Room"));

        System.out.println("Booking Requests:");

        for(Reservation r:queue){

            r.display();
        }

        System.out.println();
        System.out.println("Processing Next Request:");

        Reservation next=queue.poll();

        if(next!=null){

            next.display();
        }

    }
}