import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Book My Stay Application
 * Data Persistence & System Recovery
 *
 * @author Student
 * @version 12.0
 */
class PersistenceService {
    private static final String INVENTORY_FILE = "inventory.txt";
    private static final String BOOKINGS_FILE  = "bookings.txt";

    // Save inventory to file
    public void saveInventory(HashMap<String, Integer> inventory) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INVENTORY_FILE))) {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
            System.out.println("Inventory saved to " + INVENTORY_FILE);
        } catch (IOException e) {
            System.out.println("Error saving inventory : " + e.getMessage());
        }
    }

    // Load inventory from file
    public HashMap<String, Integer> loadInventory() {
        HashMap<String, Integer> inventory = new HashMap<>();
        File file = new File(INVENTORY_FILE);
        if (!file.exists()) {
            System.out.println("No inventory file found. Starting with default state.");
            return inventory;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(INVENTORY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    inventory.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
            System.out.println("Inventory loaded from " + INVENTORY_FILE);
        } catch (IOException e) {
            System.out.println("Error loading inventory : " + e.getMessage());
        }
        return inventory;
    }

    // Save booking history to file
    public void saveBookings(HashMap<String, String> bookings) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKINGS_FILE))) {
            for (Map.Entry<String, String> entry : bookings.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
            System.out.println("Bookings saved to " + BOOKINGS_FILE);
        } catch (IOException e) {
            System.out.println("Error saving bookings : " + e.getMessage());
        }
    }

    // Load booking history from file
    public HashMap<String, String> loadBookings() {
        HashMap<String, String> bookings = new HashMap<>();
        File file = new File(BOOKINGS_FILE);
        if (!file.exists()) {
            System.out.println("No bookings file found. Starting with empty history.");
            return bookings;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKINGS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    bookings.put(parts[0], parts[1]);
                }
            }
            System.out.println("Bookings loaded from " + BOOKINGS_FILE);
        } catch (IOException e) {
            System.out.println("Error loading bookings : " + e.getMessage());
        }
        return bookings;
    }
}

public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {
        System.out.println("Book My Stay App v12.0");
        System.out.println();

        PersistenceService persistenceService = new PersistenceService();

        // --- Simulate current system state ---
        HashMap<String, Integer> inventory = new HashMap<>();
        inventory.put("Single Room", 3);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room",  1);

        HashMap<String, String> bookings = new HashMap<>();
        bookings.put("R001", "Azam:Single Room");
        bookings.put("R002", "Madhav:Double Room");
        bookings.put("R003", "Soumya:Suite Room");

        // --- Save state (shutdown) ---
        System.out.println("--- Saving System State ---");
        persistenceService.saveInventory(inventory);
        persistenceService.saveBookings(bookings);
        System.out.println();

        // --- Simulate restart by clearing in-memory state ---
        inventory.clear();
        bookings.clear();
        System.out.println("--- System Restarted ---");
        System.out.println();

        // --- Restore state (startup) ---
        System.out.println("--- Restoring System State ---");
        inventory = persistenceService.loadInventory();
        bookings  = persistenceService.loadBookings();
        System.out.println();

        // --- Display recovered state ---
        System.out.println("Recovered Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println();

        System.out.println("Recovered Bookings:");
        for (Map.Entry<String, String> entry : bookings.entrySet()) {
            System.out.println("Reservation ID : " + entry.getKey() + " | Details : " + entry.getValue());
        }
        System.out.println();

        System.out.println("System recovery complete. Ready to accept bookings.");
    }
}