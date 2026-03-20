import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Book My Stay Application
 * Add-On Service Selection
 *
 * @author Student
 * @version 6.0
 */

class AddOnService {

    private String name;
    private double price;

    public AddOnService(String name, double price) {

        this.name = name;
        this.price = price;
    }

    public String getName() {

        return name;
    }

    public double getPrice() {

        return price;
    }

    public void display() {

        System.out.println("Service : " + name + " | Price : " + price);
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

    public List<AddOnService> getServices(String reservationId) {

        return reservationServices.getOrDefault(reservationId, new ArrayList<>());
    }

    public double calculateAdditionalCost(String reservationId) {

        double total = 0;

        for (AddOnService service : getServices(reservationId)) {

            total += service.getPrice();
        }

        return total;
    }

    public void displayServices(String reservationId) {

        System.out.println("Add-On Services for Reservation ID : " + reservationId);

        List<AddOnService> services = getServices(reservationId);

        if (services.isEmpty()) {

            System.out.println("No add-on services selected.");

        } else {

            for (AddOnService service : services) {

                service.display();
            }

            System.out.println("Additional Cost : " + calculateAdditionalCost(reservationId));
        }
    }
}

public class AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("Book My Stay App v6.0");
        System.out.println();

        AddOnServiceManager manager = new AddOnServiceManager();

        manager.addService("R001", new AddOnService("Breakfast", 500));
        manager.addService("R001", new AddOnService("Airport Pickup", 800));
        manager.addService("R001", new AddOnService("Spa", 1200));

        manager.addService("R002", new AddOnService("Breakfast", 500));
        manager.addService("R002", new AddOnService("Room Cleaning", 300));

        manager.displayServices("R001");
        System.out.println();
        manager.displayServices("R002");
    }
}
