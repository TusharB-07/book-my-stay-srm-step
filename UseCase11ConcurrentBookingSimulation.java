import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Book My Stay Application
 * Concurrent Booking Simulation (Thread Safety)
 *
 * @author Student
 * @version 11.0
 */
class SharedBookingQueue {
    private Queue<String[]> queue;

    public SharedBookingQueue() {
        queue = new LinkedList<>();
    }

    public synchronized void addRequest(String guestName, String roomType) {
        queue.add(new String[]{guestName, roomType});
    }

    public synchronized String[] getNextRequest() {
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}

class SharedInventory {
    private HashMap<String, Integer> inventory;

    public SharedInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public synchronized boolean allocateRoom(String guestName, String roomType) {
        int available = inventory.getOrDefault(roomType, 0);
        if (available > 0) {
            inventory.put(roomType, available - 1);
            System.out.println("Booking Confirmed");
            System.out.println("Guest     : " + guestName);
            System.out.println("Room Type : " + roomType);
            System.out.println("Remaining : " + (available - 1));
            System.out.println();
            return true;
        } else {
            System.out.println("Booking Failed");
            System.out.println("Guest     : " + guestName);
            System.out.println("Room Type : " + roomType);
            System.out.println("Reason    : No rooms available");
            System.out.println();
            return false;
        }
    }
}

class ConcurrentBookingProcessor extends Thread {
    private SharedBookingQueue bookingQueue;
    private SharedInventory inventory;

    public ConcurrentBookingProcessor(SharedBookingQueue bookingQueue, SharedInventory inventory) {
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
    }

    @Override
    public void run() {
        while (true) {
            String[] request = bookingQueue.getNextRequest();
            if (request == null) break;
            inventory.allocateRoom(request[0], request[1]);
        }
    }
}

public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Book My Stay App v11.0");
        System.out.println();

        SharedBookingQueue bookingQueue = new SharedBookingQueue();
        SharedInventory inventory = new SharedInventory();

        // Add concurrent booking requests
        bookingQueue.addRequest("Azam",   "Single Room");
        bookingQueue.addRequest("Madhav", "Single Room");
        bookingQueue.addRequest("Soumya", "Single Room");
        bookingQueue.addRequest("Riya",   "Double Room");
        bookingQueue.addRequest("Kiran",  "Suite Room");
        bookingQueue.addRequest("Dev",    "Suite Room");

        System.out.println("Processing Concurrent Booking Requests:");
        System.out.println();

        // Launch multiple threads to simulate concurrent access
        ConcurrentBookingProcessor t1 = new ConcurrentBookingProcessor(bookingQueue, inventory);
        ConcurrentBookingProcessor t2 = new ConcurrentBookingProcessor(bookingQueue, inventory);
        ConcurrentBookingProcessor t3 = new ConcurrentBookingProcessor(bookingQueue, inventory);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("All booking requests processed.");
    }
}