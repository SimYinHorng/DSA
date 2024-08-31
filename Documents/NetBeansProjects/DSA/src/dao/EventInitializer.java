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
            "1234567890",
            EventStatus.PLANNED,
            EventType.FUNDRAISER,
            100,
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
            "0987654321",
            EventStatus.PLANNED,
            EventType.VOLUNTEER_DRIVE,
            30,
            20
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
            "1122334455",
            EventStatus.PLANNED,
            EventType.AWARENESS,
            50,
            25
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
            "bob.brown@example.com",
            "5566778899",
            EventStatus.PLANNED,
            EventType.DONATION_DRIVE,
            75,
            40
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
            "emily.davis@example.com",
            "6677889900",
            EventStatus.PLANNED,
            EventType.FUNDRAISER,
            120,
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
            "michael.wilson@example.com",
            "3344556677",
            EventStatus.PLANNED,
            EventType.VOLUNTEER_DRIVE,
            40,
            30
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
            "laura.martin@example.com",
            "2233445566",
            EventStatus.PLANNED,
            EventType.AWARENESS,
            60,
            35
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
            "tom.harris@example.com",
            "4455667788",
            EventStatus.PLANNED,
            EventType.DONATION_DRIVE,
            80,
            45
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
            "linda.lee@example.com",
            "6677889900",
            EventStatus.PLANNED,
            EventType.FUNDRAISER,
            90,
            50
            ),
            new Event(
            "Technology Symposium",
            "3030 Tech Avenue, Austin, TX",
            LocalDate.of(2024, 10, 15),
            LocalDate.of(2024, 10, 15),
            LocalTime.of(9, 0),
            LocalTime.of(18, 0),
            "Discussing the latest trends in technology.",
            "James Green",
            "james.green@example.com",
            "9988776655",
            EventStatus.PLANNED,
            EventType.AWARENESS,
            200,
            100
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
            "sarah.turner@example.com",
            "8877665544",
            EventStatus.PLANNED,
            EventType.FUNDRAISER,
            150,
            75
            ),
            new Event(
            "Book Drive",
            "5050 Read Street, Philadelphia, PA",
            LocalDate.of(2024, 12, 1),
            LocalDate.of(2024, 12, 1),
            LocalTime.of(10, 0),
            LocalTime.of(15, 0),
            "Collecting books for underprivileged children.",
            "Daniel Scott",
            "daniel.scott@example.com",
            "2233446677",
            EventStatus.PLANNED,
            EventType.DONATION_DRIVE,
            70,
            30
            ),
            new Event(
            "Pet Adoption Fair",
            "6060 Paw Avenue, San Diego, CA",
            LocalDate.of(2024, 11, 25),
            LocalDate.of(2024, 11, 25),
            LocalTime.of(11, 0),
            LocalTime.of(16, 0),
            "Adopting pets to loving homes.",
            "Karen White",
            "karen.white@example.com",
            "3344558899",
            EventStatus.PLANNED,
            EventType.VOLUNTEER_DRIVE,
            40,
            20
            ),
            new Event(
            "Cooking Workshop",
            "7070 Chef Lane, Seattle, WA",
            LocalDate.of(2024, 10, 20),
            LocalDate.of(2024, 10, 20),
            LocalTime.of(15, 0),
            LocalTime.of(18, 0),
            "Learn new recipes and cooking techniques.",
            "Michelle Adams",
            "michelle.adams@example.com",
            "5566772233",
            EventStatus.PLANNED,
            EventType.AWARENESS,
            35,
            20
            ),
            new Event(
            "Science Fair",
            "8080 Discovery Drive, Houston, TX",
            LocalDate.of(2024, 11, 15),
            LocalDate.of(2024, 11, 15),
            LocalTime.of(9, 0),
            LocalTime.of(16, 0),
            "Showcasing student scientific projects.",
            "George Clark",
            "george.clark@example.com",
            "6677889900",
            EventStatus.PLANNED,
            EventType.FUNDRAISER,
            60,
            30
            ),
            new Event(
            "Gardening Workshop",
            "9090 Bloom Street, Portland, OR",
            LocalDate.of(2024, 10, 10),
            LocalDate.of(2024, 10, 10),
            LocalTime.of(10, 0),
            LocalTime.of(13, 0),
            "Learn about urban gardening techniques.",
            "Nancy Evans",
            "nancy.evans@example.com",
            "9988776655",
            EventStatus.PLANNED,
            EventType.AWARENESS,
            45,
            25
            ),
            new Event(
            "Fitness Challenge",
            "1011 Workout Lane, Denver, CO",
            LocalDate.of(2024, 11, 30),
            LocalDate.of(2024, 11, 30),
            LocalTime.of(8, 0),
            LocalTime.of(12, 0),
            "Participate in various fitness activities.",
            "David Harris",
            "david.harris@example.com",
            "2233445566",
            EventStatus.PLANNED,
            EventType.VOLUNTEER_DRIVE,
            50,
            30
            ),
            new Event(
            "Cultural Festival",
            "1212 Celebration Street, Atlanta, GA",
            LocalDate.of(2024, 9, 25),
            LocalDate.of(2024, 9, 27),
            LocalTime.of(10, 0),
            LocalTime.of(22, 0),
            "Celebrate diverse cultures with food and music.",
            "Sophia Martinez",
            "sophia.martinez@example.com",
            "3344556677",
            EventStatus.PLANNED,
            EventType.FUNDRAISER,
            150,
            70
            ),
            new Event(
            "Crafts Workshop",
            "1313 Artistry Lane, Minneapolis, MN",
            LocalDate.of(2024, 10, 30),
            LocalDate.of(2024, 10, 30),
            LocalTime.of(13, 0),
            LocalTime.of(16, 0),
            "Create and learn new craft techniques.",
            "Olivia Lewis",
            "olivia.lewis@example.com",
            "5566778899",
            EventStatus.PLANNED,
            EventType.AWARENESS,
            40,
            20
            ),
            new Event(
            "Outdoor Movie Night",
            "1414 Cinema Boulevard, Nashville, TN",
            LocalDate.of(2024, 12, 20),
            LocalDate.of(2024, 12, 20),
            LocalTime.of(18, 30),
            LocalTime.of(21, 30),
            "Watch a classic movie under the stars.",
            "Ethan Robinson",
            "ethan.robinson@example.com",
            "6677889900",
            EventStatus.PLANNED,
            EventType.FUNDRAISER,
            80,
            40
            ),
            new Event(
            "Local Farmers Market",
            "1515 Market Street, San Francisco, CA",
            LocalDate.of(2024, 11, 10),
            LocalDate.of(2024, 11, 10),
            LocalTime.of(9, 0),
            LocalTime.of(14, 0),
            "Support local farmers and artisans.",
            "Mia Wilson",
            "mia.wilson@example.com",
            "9988776655",
            EventStatus.PLANNED,
            EventType.DONATION_DRIVE,
            60,
            30
            ),
            new Event(
            "Charity Run",
            "1616 Runway Lane, Los Angeles, CA",
            LocalDate.of(2024, 9, 30),
            LocalDate.of(2024, 9, 30),
            LocalTime.of(7, 0),
            LocalTime.of(11, 0),
            "Run for a cause and support charity.",
            "Lucas Thompson",
            "lucas.thompson@example.com",
            "2233445566",
            EventStatus.PLANNED,
            EventType.VOLUNTEER_DRIVE,
            100,
            50
            ),
            new Event(
            "Film Festival",
            "1717 Cinema Street, New York, NY",
            LocalDate.of(2024, 12, 10),
            LocalDate.of(2024, 12, 12),
            LocalTime.of(18, 0),
            LocalTime.of(23, 0),
            "Watch and discuss independent films.",
            "Ava Jackson",
            "ava.jackson@example.com",
            "3344556677",
            EventStatus.PLANNED,
            EventType.AWARENESS,
            80,
            40
            ),
            new Event(
            "Winter Wonderland",
            "1818 Frost Avenue, Chicago, IL",
            LocalDate.of(2024, 12, 15),
            LocalDate.of(2024, 12, 15),
            LocalTime.of(10, 0),
            LocalTime.of(17, 0),
            "Enjoy winter-themed activities and crafts.",
            "Noah Harris",
            "noah.harris@example.com",
            "5566778899",
            EventStatus.PLANNED,
            EventType.DONATION_DRIVE,
            90,
            45
            )
        };

        for (Event event : events) {
            eventMap.put(event.getEventId(), event);
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
