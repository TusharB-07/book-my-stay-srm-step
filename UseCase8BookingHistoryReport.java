import java.util.ArrayList;
import java.util.List;

/**
 * Book My Stay Application
 * Booking History & Reporting
 *
 * @author Student
 * @version 8.0
 */
class BookingRecord {
    String reservationId;
    String guestName;
    String roomType;
    double totalCost;

    public BookingRecord(String reservationId, String guestName, String roomType, double totalCost) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.totalCost = totalCost;
    }

    public void display() {
        System.out.println("Reservation ID : " + reservationId);
        System.out.println("Guest         : " + guestName);
        System.out.println("Room Type     : " + roomType);
        System.out.println("Total Cost    : " + totalCost);
    }
}

class BookingHistory {
    private List<BookingRecord> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    public void addBooking(BookingRecord record) {
        history.add(record);
    }

    public List<BookingRecord> getHistory() {
        return history;
    }

    public void displayAll() {
        System.out.println("Booking History:");
        System.out.println();
        for (BookingRecord record : history) {
            record.display();
            System.out.println();
        }
    }
}

class BookingReportService {
    public void generateReport(BookingHistory bookingHistory) {
        List<BookingRecord> history = bookingHistory.getHistory();
        System.out.println("Booking Report:");
        System.out.println();
        System.out.println("Total Bookings : " + history.size());
        double totalRevenue = 0;
        for (BookingRecord record : history) {
            totalRevenue += record.totalCost;
        }
        System.out.println("Total Revenue  : " + totalRevenue);
    }
}

public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {
        System.out.println("Book My Stay App v8.0");
        System.out.println();

        BookingHistory bookingHistory = new BookingHistory();

        bookingHistory.addBooking(new BookingRecord("R001", "Azam", "Single Room", 2500));
        bookingHistory.addBooking(new BookingRecord("R002", "Madhav", "Double Room", 5000));
        bookingHistory.addBooking(new BookingRecord("R003", "Soumya", "Suite Room", 6500));

        bookingHistory.displayAll();

        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(bookingHistory);
    }
}