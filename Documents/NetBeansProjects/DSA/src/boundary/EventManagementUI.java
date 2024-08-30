package boundary;

import adt.LinkedList;
import entity.Event;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import utility.EventType;
import static utility.MessageUI.*;

public class EventManagementUI {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

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
        scanner.nextLine(); // Consume newline
        return choice;
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

    public String inputEventDescription() {
        System.out.print("Enter Event Description: ");
        String eventDescription = scanner.nextLine();
        while (eventDescription.isEmpty()) {
            System.out.print("Event Description cannot be empty. Enter Event Description: ");
            eventDescription = scanner.nextLine();
        }
        return eventDescription;
    }

    public LocalDate inputEventStartDate() {
        while (true) {
            System.out.print("Enter Event Start Date (yyyy-MM-dd): ");
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, DATE_FORMATTER);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }
    }

    public LocalDate inputEventEndDate(LocalDate startDate) {
        while (true) {
            System.out.print("Enter Event End Date (yyyy-MM-dd): ");
            String input = scanner.nextLine().trim();
            try {
                LocalDate date = LocalDate.parse(input, DATE_FORMATTER);
                if (date.isEqual(startDate) || date.isAfter(startDate)) {
                    return date;
                } else {
                    System.out.println("End date must be the same as or after the start date.");
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }
    }

    public LocalTime inputEventStartTime() {
        while (true) {
            System.out.print("Enter Event Start Time (24hours format HH:mm): ");
            String input = scanner.nextLine().trim();
            try {
                return LocalTime.parse(input, TIME_FORMATTER);
            } catch (Exception e) {
                System.out.println("Invalid time format. Please use 24hours format HH:mm.");
            }
        }
    }

    public LocalTime inputEventEndTime(LocalTime startTime) {
        while (true) {
            System.out.print("Enter Event End Time (24hours format HH:mm): ");
            String input = scanner.nextLine().trim();
            try {
                LocalTime endTime = LocalTime.parse(input, TIME_FORMATTER);
                if (endTime.isAfter(startTime)) {
                    return endTime;
                } else {
                    System.out.println("End time must be after the start time and cannot be the same.");
                }
            } catch (Exception e) {
                System.out.println("Invalid time format. Please use 24hours format HH:mm.");
            }
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

    // Helper method to validate email format
    private boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

// Helper method to validate phone number format
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\d{10}$");
    }

    public String inputEventStatus() {
        System.out.print("Enter Event Status: ");
        String eventStatus = scanner.nextLine();
        while (eventStatus.isEmpty()) {
            System.out.print("Event Status cannot be empty. Enter Event Status: ");
            eventStatus = scanner.nextLine();
        }
        return eventStatus;
    }

    public int inputVolunteerNeedForTheEvent() {
        int volunteerNeed;
        do {
            System.out.print("Enter number of volunteers needed: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
                System.out.print("Enter number of volunteers needed: ");
            }
            volunteerNeed = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (volunteerNeed < 0) {
                System.out.println("Number of volunteers cannot be negative.");
            }
        } while (volunteerNeed < 0);
        return volunteerNeed;
    }

    public int inputAvailableVolunteerNeeded(int volunteerNeed) {
        int availableVolunteerNeeded;
        do {
            System.out.print("Enter number of available volunteers: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
                System.out.print("Enter number of available volunteers: ");
            }
            availableVolunteerNeeded = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (availableVolunteerNeeded < 0 || availableVolunteerNeeded > volunteerNeed) {
                System.out.println("Number of available volunteers cannot be negative or exceed the total number needed.");
            }
        } while (availableVolunteerNeeded < 0 || availableVolunteerNeeded > volunteerNeed);
        return availableVolunteerNeeded;
    }

    public EventType inputEventType() {
        boolean validInput = false;
        EventType type = null;
        do {
            System.out.println("Select Event Type:");
            System.out.println("1. Fundraiser");
            System.out.println("2. Awareness");
            System.out.println("3. Volunteer Drive");
            System.out.println("4. Donation Drive");
            System.out.print("Enter choice: ");
            int input = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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

    public void displayEventDetails(Event event) {
        System.out.println("Event Details");
        System.out.println("Event ID                   : " + event.getEventId());
        System.out.println("Event Name                 : " + event.getEventName());
        System.out.println("Event Address              : " + event.getEventAddress());
        System.out.println("Event Description          : " + event.getEventDescription());
        System.out.println("Event Start Date           : " + event.getEventStartDate());
        System.out.println("Event End Date             : " + event.getEventEndDate());
        System.out.println("Event Start Time           : " + event.getEventStartTime());
        System.out.println("Event End Time             : " + event.getEventEndTime());
        System.out.println("Event Status               : " + event.getEventStatus());
        System.out.println("Organizer Name             : " + event.getEventOrganizerName());
        System.out.println("Organizer Email            : " + event.getEventOrganizerEmail());
        System.out.println("Organizer Phone No         : " + event.getEventOrganizerPhoneNo());
        System.out.println("Event Type                 : " + event.getEventType());
        System.out.println("Volunteer Need             : " + event.getVolunteerNeed());
        System.out.println("Available Volunteers Needed: " + event.getAvailableVolunteerNeeded());
    }

    public int getEditMenu() {
        boolean correctInput = false;
        int choice;
        do {
            System.out.println("Which Event Detail Needs to be Edited?");
            System.out.println("1. Event Name");
            System.out.println("2. Event Address");
            System.out.println("3. Event Description");
            System.out.println("4. Event Start Date");
            System.out.println("5. Event End Date");
            System.out.println("6. Event Start Time");
            System.out.println("7. Event End Time");
            System.out.println("8. Event Status");
            System.out.println("9. Organizer Name");
            System.out.println("10. Organizer Email");
            System.out.println("11. Organizer Phone No");
            System.out.println("12. Event Type");
            System.out.println("13. Volunteer Need");
            System.out.println("14. Available Volunteer Needed");
            System.out.println("0. Quit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (choice >= 0 && choice <= 14) {
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
        scanner.nextLine(); // Consume newline
        return choice;
    }

    public void generateSummaryReport() {
        System.out.println("Generating Summary Report...");
        // Implement the logic to generate summary reports.
    }

    public void displayExitMessage() {
        System.out.println("Exit From Event Management System");
    }

    public int confirmationMessage() {
        boolean correctInput = false;
        int input;
        do {
            System.out.println("1-Yes 2-No 0-Exit");
            System.out.print("Enter choice: ");
            input = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (input >= 0 && input <= 2) {
                correctInput = true;
            } else {
                displayInvalidChoiceMessage();
            }
        } while (!correctInput);
        return input;
    }

    public int confirmationEventMessage() {
        boolean correctInput = false;
        int input;
        do {
            System.out.println("1-Yes 0-Exit");
            System.out.print("Enter choice: ");
            input = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (input >= 0 && input <= 1) {
                correctInput = true;
            } else {
                displayInvalidChoiceMessage();
            }
        } while (!correctInput);
        return input;
    }

    public String inputEventId() {
        System.out.print("Input Event ID: ");
        return scanner.nextLine();
    }

    public int displaySearchMenu() {
        boolean correctInput = false;
        int choice;
        do {

            System.out.println("Search by:");
            System.out.println("1. Event ID");
            System.out.println("2. Event Name");
            System.out.println("3. Event Address");
            System.out.println("4. Event Description");
            System.out.println("5. Event Start Date");
            System.out.println("6. Event Start Time");
            System.out.println("7. Event Status");
            System.out.println("8. Organizer Name");
            System.out.println("9. Organizer Email");
            System.out.println("10. Organizer Phone No");
            System.out.println("11. Event Type");
            System.out.println("12. Available Volunteer Needed");
            System.out.println("0. Quit");
            System.out.print("Enter choice: ");
            choice = validateInt();
            System.out.println("");
            if (choice >= 0 && choice <= 6) {
                correctInput = true;
            } else {
                displayInvalidChoiceMessage();
            }

        } while (!correctInput);
        return choice;
    }

    public int getSearchCriteria() {
        return scanner.nextInt();
    }

    public String getSearchKeyword(String keywordType) {
        System.out.print("Enter " + keywordType + " keyword: ");
        return scanner.nextLine().toLowerCase();
    }

    public void handleInvalidChoice() {
        System.out.println("Invalid choice. Please try again.");
    }

    public int validateInt() {
        try {
            int number = scanner.nextInt();
            scanner.nextLine();
            return number;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }

    }

    public void filterHeader(String search) {
        line(505);
        System.out.printf("|Search Result Of : %-184s|\n", search);
    }

    public void display(LinkedList<Event> eventList) {
        Iterator<Event> eventIt = eventList.iterator();
        displayEventHeader(); // Method to display the header for events
        if (eventList.isEmpty()) {
            System.out.printf("| %-202s|\n", "No Record Found");
        } else {
            while (eventIt.hasNext()) {
                System.out.println(eventIt.next().toString()); // Adjusted for Event entity
            }
        }
        line(205); // Method to print a line with the specified length
    }

}
