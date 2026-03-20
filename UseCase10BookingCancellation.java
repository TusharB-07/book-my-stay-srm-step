import java.util.HashMap;
import java.util.Stack;

/**
 * Book My Stay Application
 * Booking Cancellation & Inventory Rollback
 *
 * @author Student
 * @version 10.0
 */
class CancelledBooking {
    String reservationId;
    String guestName;
    String roomType;
    String roomId;

    public CancelledBooking(String reservationId, String guestName, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public void display() {
        System.out.println("Reservation ID : " + reservationId);
        System.out.println("Guest          : " + guestName);
        System.out.println("Room Type      : " + roomType);
        System.out.println("Room ID        : " + roomId);
    }
}

class CancellationService {
    private HashMap<String, CancelledBooking> activeBookings;
    private HashMap<String, Integer> inventory;
    private Stack<String> rollbackStack;

    public CancellationService() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 0);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 2);

        activeBookings = new HashMap<>();
        activeBookings.put("R001", new CancelledBooking("R001", "Azam", "Single Room", "S101"));
        activeBookings.put("R002", new CancelledBooking("R002", "Madhav", "Double Room", "D101"));
        activeBookings.put("R003", new CancelledBooking("R003", "Soumya", "Single Room", "S102"));

        rollbackStack = new Stack<>();
    }

    public void cancelBooking(String reservationId) {
        if (!activeBookings.containsKey(reservationId)) {
            System.out.println("Cancellation Failed : Reservation " + reservationId + " not found.");
            System.out.println();
            return;
        }

        CancelledBooking booking = activeBookings.get(reservationId);

        // Push room ID to rollback stack
        rollbackStack.push(booking.roomId);

        // Restore inventory
        int current = inventory.getOrDefault(booking.roomType, 0);
        inventory.put(booking.roomType, current + 1);

        // Remove from active bookings
        activeBookings.remove(reservationId);

        System.out.println("Booking Cancelled Successfully");
        booking.display();
        System.out.println("Inventory Restored : " + booking.roomType + " = " + inventory.get(booking.roomType));
        System.out.println();
    }

    public void displayRollbackStack() {
        System.out.println("Rollback Stack (Released Room IDs) : " + rollbackStack);
        System.out.println();
    }

    public void displayInventory() {
        System.out.println("Current Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " : " + inventory.get(type));
        }
        System.out.println();
    }
}

public class UseCase10BookingCancellation {
    public static void main(String[] args) {
        System.out.println("Book My Stay App v10.0");
        System.out.println();

        CancellationService service = new CancellationService();

        // Display initial inventory
        service.displayInventory();

        // Valid cancellations
        service.cancelBooking("R001");
        service.cancelBooking("R002");

        // Invalid cancellation (non-existent)
        service.cancelBooking("R099");

        // Already cancelled
        service.cancelBooking("R001");

        // Display rollback stack
        service.displayRollbackStack();

        // Display updated inventory
        service.displayInventory();
    }
}