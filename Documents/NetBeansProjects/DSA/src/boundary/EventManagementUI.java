package boundary;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import adt.HashMap;
import entity.Event;
import entity.Volunteer;
import java.util.Iterator;
import java.util.Scanner;
import utility.EventType;
import static utility.MessageUI.displayEventHeader;
import static utility.MessageUI.line;
import static utility.MessageUI.displayInvalidChoiceMessage;
import static utility.MessageUI.enterToContinue;

/**
 * UI class for managing events.
 *
 * Author: Terence
 */
public class EventManagementUI {

    Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public int getMenuChoice() {
        System.out.println("EVENT MAIN MENU");
        System.out.println("1. Add New Event");
        System.out.println("2. Remove Event");
        System.out.println("3. Search Event");
        System.out.println("4. Amend Event Detail");
        System.out.println("5. List All Events");
        System.out.println("6. Remove Event from a Volunteer");
        System.out.println("7. List All Events for a Volunteer");
        System.out.println("8. Generate Summary Reports");
        System.out.println("0. Quit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    public Event inputEventDetails() {
        String eventName = inputEventName();
        String eventAddress = inputEventAddress();
        String eventStartDate = inputEventStartDate();
        String eventEndDate = inputEventEndDate(eventStartDate);
        String eventStartTime = inputEventStartTime();
        String eventEndTime = inputEventEndTime(eventStartTime);
        String eventOrganizerName = inputEventOrganizerName();
        String eventOrganizerEmail = inputEventOrganizerEmail();
        String eventOrganizerPhoneNo = inputEventOrganizerPhoneNo();
        EventType eventType = inputEventType();

        return new Event(eventName, eventAddress, eventStartDate, eventEndDate, eventStartTime, eventEndTime, eventOrganizerName, eventOrganizerEmail, eventOrganizerPhoneNo, eventType);
    }

    public String inputEventName() {
        System.out.print("Enter Event Name: ");
        String eventName = scanner.nextLine();
        while (eventName.isEmpty()) {
            System.out.print("Event Name cannot be empty. Enter Event Name: ");
            eventName = scanner.nextLine();
        }
        return eventName;
    }

    public String inputEventAddress() {
        System.out.print("Enter Event Address: ");
        String eventAddress = scanner.nextLine();
        while (eventAddress.isEmpty()) {
            System.out.print("Event Address cannot be empty. Enter Event Address: ");
            eventAddress = scanner.nextLine();
        }
        return eventAddress;
    }

    public String inputEventStartDate() {
        String eventStartDate;
        LocalDate parsedDate = null;

        do {
            System.out.print("Enter Event Start Date (YYYY-MM-DD): ");
            eventStartDate = scanner.nextLine();
            parsedDate = parseDate(eventStartDate);

            if (parsedDate == null) {
                System.out.println("Invalid date format. Expected format is YYYY-MM-DD.");
            }
        } while (parsedDate == null);

        return eventStartDate;
    }

    public String inputEventEndDate(String eventStartDate) {
        String eventEndDate;
        LocalDate parsedStartDate = parseDate(eventStartDate);
        LocalDate parsedEndDate = null;

        do {
            System.out.print("Enter Event End Date (YYYY-MM-DD): ");
            eventEndDate = scanner.nextLine();
            parsedEndDate = parseDate(eventEndDate);

            if (parsedEndDate == null) {
                System.out.println("Invalid date format. Expected format is YYYY-MM-DD.");
            } else if (parsedEndDate.isBefore(parsedStartDate)) {
                System.out.println("End date cannot be before start date.");
                parsedEndDate = null;
            }
        } while (parsedEndDate == null);

        return eventEndDate;
    }

    private LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public String inputEventStartTime() {
        String eventStartTime;
        LocalTime parsedTime = null;

        do {
            System.out.print("Enter Event Start Time (HH:MM): ");
            eventStartTime = scanner.nextLine();
            parsedTime = parseTime(eventStartTime);

            if (parsedTime == null) {
                System.out.println("Invalid time format. Expected format is HH:MM.");
            }
        } while (parsedTime == null);

        return eventStartTime;
    }

    public String inputEventEndTime(String eventStartTime) {
        String eventEndTime;
        LocalTime parsedStartTime = parseTime(eventStartTime);
        LocalTime parsedEndTime = null;

        do {
            System.out.print("Enter Event End Time (HH:MM): ");
            eventEndTime = scanner.nextLine();
            parsedEndTime = parseTime(eventEndTime);

            if (parsedEndTime == null) {
                System.out.println("Invalid time format. Expected format is HH:MM.");
            } else if (parsedEndTime.isBefore(parsedStartTime)) {
                System.out.println("End time cannot be before start time.");
                parsedEndTime = null;
            }
        } while (parsedEndTime == null);

        return eventEndTime;
    }

    private LocalTime parseTime(String time) {
        try {
            return LocalTime.parse(time, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public String inputEventOrganizerName() {
        System.out.print("Enter Organizer Name: ");
        String eventOrganizerName = scanner.nextLine();
        while (eventOrganizerName.isEmpty()) {
            System.out.print("Organizer Name cannot be empty. Enter Organizer Name: ");
            eventOrganizerName = scanner.nextLine();
        }
        return eventOrganizerName;
    }

    public String inputEventOrganizerEmail() {
        System.out.print("Enter Organizer Email: ");
        String eventOrganizerEmail = scanner.nextLine();
        while (!isValidEmail(eventOrganizerEmail)) {
            System.out.print("Invalid email format. Enter Organizer Email: ");
            eventOrganizerEmail = scanner.nextLine();
        }
        return eventOrganizerEmail;
    }

    public String inputEventOrganizerPhoneNo() {
        System.out.print("Enter Organizer Phone No: ");
        String eventOrganizerPhoneNo = scanner.nextLine();
        while (!isValidPhoneNumber(eventOrganizerPhoneNo)) {
            System.out.print("Invalid phone number format. Enter Organizer Phone No: ");
            eventOrganizerPhoneNo = scanner.nextLine();
        }
        return eventOrganizerPhoneNo;
    }

    // Validation methods
   
// Helper method to validate email format
    private boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

// Helper method to validate phone number format
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\d{10}$");
    }

    public EventType inputEventType() {
        boolean validInput = false;
        int input;
        EventType type = null;
        do {
            System.out.println("Select Event Type:");
            System.out.println("1. Fundraiser");
            System.out.println("2. Awareness");
            System.out.println("3. Volunteer Drive");
            System.out.println("4. Donation Drive");
            System.out.print("Enter choice: ");
            input = scanner.nextInt();

            switch (input) {
                case 1:
                    type = EventType.FUNDRAISER;
                    validInput = true;
                    break;
                case 2:
                    type = EventType.AWARENESS;
                    validInput = true;
                    break;
                case 3:
                    type = EventType.VOLUNTEER_DRIVE;
                    validInput = true;
                    break;
                case 4:
                    type = EventType.DONATION_DRIVE;
                    validInput = true;
                    break;
                default:
                    displayInvalidChoiceMessage();
                    break;
            }

        } while (!validInput);

        return type;
    }

    public void listAllEvents(HashMap<String, Event> eventMap) {
        // Get an iterator over the key set of the HashMap
        Iterator<String> keyIt = eventMap.keySet().getIterator();

        displayEventHeader();
        while (keyIt.hasNext()) {
            String key = keyIt.next();
            Event event = eventMap.get(key); // Retrieve event using String key
            System.out.println(event.toString());
        }
    }

    public void displayEventDetails(Event event) {
        System.out.println("Event Details");
        System.out.println("Event ID           : " + event.getEventId());
        System.out.println("Event Name         : " + event.getEventName());
        System.out.println("Event Address      : " + event.getEventAddress());
        System.out.println("Event Start Date   : " + event.getEventStartDate());
        System.out.println("Event End Date     : " + event.getEventEndDate());
        System.out.println("Event Start Time   : " + event.getEventStartTime());
        System.out.println("Event End Time     : " + event.getEventEndTime());
        System.out.println("Organizer Name     : " + event.getEventOrganizerName());
        System.out.println("Organizer Email    : " + event.getEventOrganizerEmail());
        System.out.println("Organizer Phone No : " + event.getEventOrganizerPhoneNo());
        System.out.println("Event Type         : " + event.getEventType());
    }

    public int getEditMenu() {
        boolean correctInput = false;
        int choice;
        do {
            System.out.println("Which Event Detail Needs to be Edited?");
            System.out.println("1. Event Name");
            System.out.println("2. Event Address");
            System.out.println("3. Event Start Date");
            System.out.println("4. Event End Date");
            System.out.println("5. Event Start Time");
            System.out.println("6. Event End Time");
            System.out.println("7. Organizer Name");
            System.out.println("8. Organizer Email");
            System.out.println("9. Organizer Phone No");
            System.out.println("10. Event Type");
            System.out.println("0. Quit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            if (choice >= 0 && choice <= 10) {
                correctInput = true;
            } else {
                displayInvalidChoiceMessage();
            }

        } while (!correctInput);
        return choice;
    }

    public int getVolunteerEventMenu() {
        System.out.println("1. Remove Event from Volunteer");
        System.out.println("2. List All Events for a Volunteer");
        System.out.println("0. Quit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    public void generateSummaryReport() {
        System.out.println("Generating Summary Report...");
        // Implement the logic to generate summary reports.
    }//Havent did yet

    public void displayExitMessage() {
        System.out.println("Exit From Event Management System");
    }

    public int confirmationMessage() {
        boolean correctInput = false;
        int input;
        do {
            System.out.println("1-Yes 2-No 0-Exit");
            System.out.print("Enter No: ");
            input = scanner.nextInt();
            if (input >= 0 && input <= 2) {
                correctInput = true;
            } else {
                displayInvalidChoiceMessage();
            }
        } while (!correctInput);
        return input;
    }

    public String inputEventId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input Event Id:");

        // Reading the input as a string and then converting it to an integer
        String eventId = scanner.nextLine();

        return eventId;
    }

    // Method to display the search menu
    public void displaySearchMenu() {
        System.out.println("Search by:");
        System.out.println("1. Event ID");
        System.out.println("2. Event Name");
        System.out.println("3. Event Address");
        System.out.println("4. Event Organizer Name");
        System.out.println("5. Event Type");
        System.out.print("Enter choice: ");
    }

    // Method to get the search criteria from the user
    public int getSearchCriteria() {
        return scanner.nextInt();
    }

    // Method to get a keyword from the user
    public String getSearchKeyword(String keywordType) {
        System.out.print("Enter " + keywordType + " keyword: ");
        return scanner.nextLine().toLowerCase();
    }

    
}
