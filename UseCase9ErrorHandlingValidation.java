import java.util.HashMap;

/**
 * Book My Stay Application
 * Error Handling & Validation
 *
 * @author Student
 * @version 9.0
 */
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class InvalidBookingValidator {
    private HashMap<String, Integer> inventory;

    public InvalidBookingValidator() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 0);
    }

    public void validateAndBook(String guestName, String roomType) {
        try {
            if (guestName == null || guestName.trim().isEmpty()) {
                throw new InvalidBookingException("Guest name cannot be empty.");
            }
            if (!inventory.containsKey(roomType)) {
                throw new InvalidBookingException("Invalid room type : " + roomType);
            }
            int available = inventory.get(roomType);
            if (available <= 0) {
                throw new InvalidBookingException("No rooms available for type : " + roomType);
            }
            inventory.put(roomType, available - 1);
            System.out.println("Booking Confirmed");
            System.out.println("Guest     : " + guestName);
            System.out.println("Room Type : " + roomType);
        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed : " + e.getMessage());
        }
        System.out.println();
    }
}

public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        System.out.println("Book My Stay App v9.0");
        System.out.println();

        InvalidBookingValidator validator = new InvalidBookingValidator();

        // Valid booking
        validator.validateAndBook("Azam", "Single Room");

        // Invalid room type
        validator.validateAndBook("Madhav", "Penthouse");

        // No rooms available
        validator.validateAndBook("Soumya", "Suite Room");

        // Empty guest name
        validator.validateAndBook("", "Double Room");

        // Valid booking
        validator.validateAndBook("Riya", "Double Room");
    }
}