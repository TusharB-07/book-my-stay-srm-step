import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Book My Stay Application
 * Add-On Service Selection
 *
 * @author Student
 * @version 7.0
 */
class AddOnService {
    String name;
    double cost;

    public AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public void display() {
        System.out.println("Service : " + name + " | Cost : " + cost);
    }
}

class AddOnServiceManager {
    private Map<String, List<AddOnService>> reservationServices;

    public AddOnServiceManager() {
        reservationServices = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {
        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).add(service);
    }

    public void displayServices(String reservationId) {
        List<AddOnService> services = reservationServices.get(reservationId);
        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services for reservation " + reservationId);
            return;
        }
        System.out.println("Add-On Services for Reservation : " + reservationId);
        for (AddOnService s : services) {
            s.display();
        }
    }

    public double calculateTotalCost(String reservationId) {
        List<AddOnService> services = reservationServices.get(reservationId);
        if (services == null) return 0;
        double total = 0;
        for (AddOnService s : services) {
            total += s.cost;
        }
        return total;
    }
}

public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        System.out.println("Book My Stay App v7.0");
        System.out.println();

        AddOnServiceManager manager = new AddOnServiceManager();

        // Define available add-on services
        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService spa = new AddOnService("Spa", 1500);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 800);

        // Attach services to reservation IDs
        manager.addService("R001", breakfast);
        manager.addService("R001", spa);
        manager.addService("R002", airportPickup);

        // Display services and costs for each reservation
        manager.displayServices("R001");
        System.out.println("Total Additional Cost : " + manager.calculateTotalCost("R001"));
        System.out.println();

        manager.displayServices("R002");
        System.out.println("Total Additional Cost : " + manager.calculateTotalCost("R002"));
    }
}