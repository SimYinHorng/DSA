package dao;

import adt.HashMap;
import entity.Event;
import utility.EventStatus;
import utility.EventType;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Terence Goh Poh Xian
 */
public class EventInitializer {

    public HashMap<String, Event> initializeEvent() {

        HashMap<String, Event> eventMap = new HashMap<>();

        // Sample data for initialization
        Event[] events = new Event[]{
            new Event(
            "Charity Gala",
            "123 Charity Lane, New York, NY",
            LocalDate.of(2024, 9, 15),
            LocalDate.of(2024, 9, 15),
            LocalTime.of(18, 0),
            LocalTime.of(22, 0),
            "A night of fun and fundraising.",
            "John Doe",
            "john.doe@example.com",
            "011234567890",
            EventStatus.PLANNED,
            EventType.FUNDRAISER,
            50,
            50
            ),
            new Event(
            "Community Clean-Up",
            "456 Green Street, Los Angeles, CA",
            LocalDate.of(2024, 10, 5),
            LocalDate.of(2024, 10, 5),
            LocalTime.of(9, 0),
            LocalTime.of(12, 0),
            "Helping to keep our community clean.",
            "Jane Smith",
            "jane.smith@example.com",
            "019876543210",
            EventStatus.PLANNED,
            EventType.VOLUNTEER_DRIVE,
            30,
            30
            ),
            new Event(
            "Health Awareness Workshop",
            "789 Wellness Blvd, San Francisco, CA",
            LocalDate.of(2024, 11, 20),
            LocalDate.of(2024, 11, 20),
            LocalTime.of(10, 0),
            LocalTime.of(15, 0),
            "A workshop to promote health and wellness.",
            "Alice Johnson",
            "alice.johnson@example.com",
            "011122334455",
            EventStatus.PLANNED,
            EventType.AWARENESS,
            40,
            40
            ),
            new Event(
            "Holiday Donation Drive",
            "101 Festive Road, Chicago, IL",
            LocalDate.of(2024, 12, 10),
            LocalDate.of(2024, 12, 10),
            LocalTime.of(8, 0),
            LocalTime.of(14, 0),
            "Collecting donations for the holiday season.",
            "Bob Brown",
            "alice.johnson@example.com",
            "01556778899",
            EventStatus.PLANNED,
            EventType.DONATION_DRIVE,
            70,
            70
            ),
            new Event(
            "Fundraising Dinner",
            "1234 Dining Lane, Boston, MA",
            LocalDate.of(2024, 10, 1),
            LocalDate.of(2024, 10, 1),
            LocalTime.of(19, 0),
            LocalTime.of(23, 0),
            "An elegant dinner to raise funds for charity.",
            "Emily Davis",
            "john.doe@example.com",
            "016677889900",
            EventStatus.PLANNED,
            EventType.FUNDRAISER,
            60,
            60
            ),
            new Event(
            "Volunteer Training",
            "2345 Skill Road, Seattle, WA",
            LocalDate.of(2024, 11, 1),
            LocalDate.of(2024, 11, 1),
            LocalTime.of(14, 0),
            LocalTime.of(17, 0),
            "Training session for new volunteers.",
            "Michael Wilson",
            "john.doe@example.com",
            "013344556677",
            EventStatus.PLANNED,
            EventType.VOLUNTEER_DRIVE,
            20,
            20
            ),
            new Event(
            "Environmental Awareness Campaign",
            "6789 Nature Ave, Denver, CO",
            LocalDate.of(2024, 12, 5),
            LocalDate.of(2024, 12, 5),
            LocalTime.of(9, 0),
            LocalTime.of(13, 0),
            "Campaign to raise awareness about environmental issues.",
            "Laura Martin",
            "john.doe@example.com",
            "02233445566",
            EventStatus.PLANNED,
            EventType.AWARENESS,
            50,
            50
            ),
            new Event(
            "Toy Drive",
            "1010 Kids Street, Dallas, TX",
            LocalDate.of(2024, 12, 15),
            LocalDate.of(2024, 12, 15),
            LocalTime.of(10, 0),
            LocalTime.of(16, 0),
            "Collecting toys for children in need.",
            "Tom Harris",
            "john.doe@example.com",
            "04455667788",
            EventStatus.PLANNED,
            EventType.DONATION_DRIVE,
            40,
            40
            ),
            new Event(
            "Arts and Crafts Fair",
            "2020 Art Lane, Miami, FL",
            LocalDate.of(2024, 11, 10),
            LocalDate.of(2024, 11, 10),
            LocalTime.of(11, 0),
            LocalTime.of(17, 0),
            "Showcasing local artists and craftspeople.",
            "Linda Lee",
            "john.doe@example.com",
            "016677889900",
            EventStatus.PLANNED,
            EventType.FUNDRAISER,
            70,
            70
            ),
            new Event(
            "Technology Symposium",
            "3030 Tech Avenue, Austin, TX",
            LocalDate.of(2024, 10, 15),
            LocalDate.of(2024, 10, 15),
            LocalTime.of(9, 0),
            LocalTime.of(18, 0),
            "Discussing the latest trends in technology.",
            "James Green", "john.doe@example.com",
            "09988776655",
            EventStatus.PLANNED,
            EventType.AWARENESS,
            80,
            80
            ),
            new Event(
            "Music Festival",
            "4040 Music Road, Nashville, TN",
            LocalDate.of(2024, 9, 20),
            LocalDate.of(2024, 9, 22),
            LocalTime.of(12, 0),
            LocalTime.of(23, 0),
            "A weekend of live music performances.",
            "Sarah Turner",
            "john.doe@example.com",
            "08877665544",
            EventStatus.PLANNED,
            EventType.FUNDRAISER,
            90,
            90
            )
        };

        // Adding events to the map
        for (Event event : events) {
            eventMap.put(event.getEventName(), event);
        }

        return eventMap;
    }

    public static void main(String[] args) {
        EventInitializer eventInitializer = new EventInitializer();
        HashMap<String, Event> eventMap = eventInitializer.initializeEvent();

        // Assuming EventDAO has a saveToFile method for saving events
        EventDAO eventDAO = new EventDAO();
        eventDAO.saveToFile(eventMap);

    }
}
