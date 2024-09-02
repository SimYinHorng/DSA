package boundary;

import adt.LinkedList;
import entity.Event;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import utility.EventStatus;
import utility.EventType;
import static utility.MessageUI.*;

/**
 *
 * @author Terence Goh Poh Xian
 */
public class EventManagementUI {

    public static final Scanner scanner = new Scanner(System.in);
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

        int choice = -1;

        while (choice < 0 || choice > 8) { // Adjust the range based on the menu options
            System.out.print("Enter choice: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < 0 || choice > 8) {
                    System.out.println("Invalid choice. Please enter a number between 0 and 8.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }

        return choice;

    }

    public String inputVolunteerId() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Volunteer ID: ");
        while (!scan.hasNextInt()) {
            System.out.println("Invalid input. Please enter a numeric Volunteer ID.");
            scan.next(); // clear invalid input
        }
        return scan.next();
    }

    public String inputEventId() {
        Scanner scan = new Scanner(System.in);
        System.out.print("{Event ID (e.g., E0001) or '0' to exit} ");
        String eventId = scan.nextLine().toUpperCase().trim();
        if (eventId.equals("0")) {
            return eventId; // Return null or some sentinel value to indicate exit
        }
        if (eventId.matches("^E\\d{4}$")) {
            return eventId; // Return if format is valid
        }
        while (!scan.hasNext()) {

            System.out.println("Invalid Event ID format. Please enter an ID in the format E0001.");
            scan.next();
        }

        return eventId;
    }

    public String inputEventName() {
        System.out.print("Enter Event name: ");

        Scanner scan = new Scanner(System.in);
        String eventName = scan.nextLine();

        if (eventName.equalsIgnoreCase("EXIT")) {
            return eventName; // Return null or some sentinel value to indicate exit
        }
        if (eventName.matches("^[a-zA-Z\\s]+$|^[a-zA-Z0-9\\s&.,'-]+$")) {
            return eventName; // Return if format is valid
        }
        while (!scan.hasNext()) {

            System.out.println("Invalid Event ID format. Please enter an ID in the format E0001.");
            scan.next();
        }

        return eventName;
    }

    public String inputEventAddress() {
        System.out.print("Enter Event Address: ");

        Scanner scan = new Scanner(System.in);
        String eventAddress = scan.nextLine();

        if (eventAddress.equalsIgnoreCase("EXIT")) {
            return "eventAddress"; // Return null or some sentinel value to indicate exit
        }
        if (eventAddress.matches("^[a-zA-Z\\s]+$|^[a-zA-Z0-9\\s&.,'-]+$")) {
            return eventAddress; // Return if format is valid
        }
        while (!scan.hasNext()) {

            System.out.println("Invalid Event ID format. Please enter an ID in the format E0001.");
            scan.next();
        }

        return eventAddress;
    }

    public String inputEventDescription() {
        System.out.print("Enter Event Description: ");

        Scanner scan = new Scanner(System.in);
        String eventDescription = scan.nextLine();

        if (eventDescription.equalsIgnoreCase("EXIT")) {
            return eventDescription; // Return null or some sentinel value to indicate exit
        }
        if (eventDescription.matches("^[a-zA-Z\\s]+$|^[a-zA-Z0-9\\s&.,'-]+$")) {
            return eventDescription; // Return if format is valid
        }
        while (!scan.hasNext()) {

            System.out.println("Invalid Event ID format. Please enter an ID in the format E0001.");
            scan.next();
        }

        return eventDescription;
    }

    public LocalDate inputEventStartDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("\nEnter Event Start Date (yyyy-MM-dd) or 'EXIT' to cancel: ");
            String eventStartDate = scan.nextLine();

            // Check if the user wants to exit
            if (eventStartDate.equalsIgnoreCase("EXIT")) {
                return null; // Indicate that the user wants to exit
            }

            try {
                // Try to parse the input date
                LocalDate date = LocalDate.parse(eventStartDate, dateFormatter);

                // Ensure the date is not in the past
                if (date.isBefore(LocalDate.now())) {
                    System.out.println("The event start date cannot be in the past. Please enter a valid date.");
                } else {
                    return date; // Return the valid date
                }
            } catch (DateTimeParseException e) {
                // Handle invalid date format
                System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
            }
        }
    }

    public LocalDate inputEventEndDate(LocalDate startDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("\nEnter Event End Date (yyyy-MM-dd) or 'EXIT' to cancel: ");
            String eventEndDate = scan.nextLine();

            // Check if the user wants to exit
            if (eventEndDate.equalsIgnoreCase("EXIT")) {
                return null; // Indicate that the user wants to exit
            }

            try {
                // Try to parse the input date
                LocalDate endDate = LocalDate.parse(eventEndDate, dateFormatter);

                // Ensure the end date is not before the start date
                if (endDate.isBefore(startDate)) {
                    System.out.println("The event end date cannot be before the start date. Please enter a valid date.");
                } else {
                    return endDate; // Return the valid end date
                }
            } catch (DateTimeParseException e) {
                // Handle invalid date format
                System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
            }
        }
    }

    public LocalTime inputEventStartTime() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("Enter Event Start Time (HH:mm) or 'EXIT' to cancel: ");
            String eventStartTime = scan.nextLine();

            // Check if the user wants to exit
            if (eventStartTime.equalsIgnoreCase("EXIT")) {
                return null; // Indicate that the user wants to exit
            }

            try {
                // Try to parse the input time
                LocalTime startTime = LocalTime.parse(eventStartTime, timeFormatter);
                return startTime; // Return the valid start time
            } catch (DateTimeParseException e) {
                // Handle invalid time format
                System.out.println("Invalid time format. Please enter the time in the format HH:mm.");
            }
        }
    }

    public LocalTime inputEventEndTime(LocalTime startTime) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("Enter Event End Time (HH:mm) or 'EXIT' to cancel: ");
            String eventEndTime = scan.nextLine();

            // Check if the user wants to exit
            if (eventEndTime.equalsIgnoreCase("EXIT")) {
                return null; // Indicate that the user wants to exit
            }

            try {
                // Try to parse the input time
                LocalTime endTime = LocalTime.parse(eventEndTime, timeFormatter);

                // Ensure the end time is not before the start time
                if (endTime.isBefore(startTime)) {
                    System.out.println("The event end time cannot be before the start time. Please enter a valid time.");
                } else {
                    return endTime; // Return the valid end time
                }
            } catch (DateTimeParseException e) {
                // Handle invalid time format
                System.out.println("Invalid time format. Please enter the time in the format HH:mm.");
            }
        }
    }

    public String inputEventOrganizerName() {
        System.out.print("Enter Organizer Event name: ");

        Scanner scan = new Scanner(System.in);
        String orgName = scan.nextLine();

        if (orgName.equalsIgnoreCase("EXIT")) {
            return orgName; // Return null or some sentinel value to indicate exit
        }
        if (orgName.matches("^[a-zA-Z\\s]+$|^[a-zA-Z0-9\\s&.,'-]+$")) {
            return orgName; // Return if format is valid
        }
        while (!scan.hasNext()) {

            System.out.println("Invalid Event ID format. Please enter an ID in the format E0001.");
            scan.next();
        }

        return orgName;
    }

    public String inputEventOrganizerEmail() {
        System.out.print("\nEnter Organizer Email: ");
        String eventOrganizerEmail = scanner.nextLine();

        while (!eventOrganizerEmail.equalsIgnoreCase("EXIT") && !isValidEmail(eventOrganizerEmail)) {
            System.out.print("\nInvalid email format. Enter Organizer Email (or type 'EXIT' to cancel): ");
            eventOrganizerEmail = scanner.nextLine();
        }

        // Handle EXIT condition
        if (eventOrganizerEmail.equalsIgnoreCase("EXIT")) {
            return null; // Indicate the process was exited
        }

        return eventOrganizerEmail;
    }

    public String inputEventOrganizerPhoneNo() {
        System.out.print("\nEnter Organizer Phone No: ");
        String eventOrganizerPhoneNo = scanner.nextLine();

        while (!eventOrganizerPhoneNo.equalsIgnoreCase("EXIT") && !isValidPhoneNumber(eventOrganizerPhoneNo)) {
            System.out.print("\nInvalid phone number format. Enter Organizer Phone No (or type 'EXIT' to cancel): ");
            eventOrganizerPhoneNo = scanner.nextLine();
        }

        // Handle EXIT condition
        if (eventOrganizerPhoneNo.equalsIgnoreCase("EXIT")) {
            return null; // Indicate the process was exited
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

    public EventStatus chooseEventStatus() {
        EventStatus[] statuses = EventStatus.values();

        System.out.println("Select Event Status:");
        for (int i = 0; i < statuses.length; i++) {
            System.out.printf("%d. %s%n", i + 1, statuses[i]);
        }

        int choice = -1;
        while (choice < 1 || choice > statuses.length) {
            System.out.print("Enter choice (number): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > statuses.length) {
                    System.out.println("Invalid choice. Please select a number between 1 and " + statuses.length + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        return statuses[choice - 1];
    }

    public int inputVolunteerNeedForTheEvent() {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the number of volunteers needed for the event or '0' to exit: ");
            String input = scan.nextLine().trim();

            // Check if the user wants to exit
            if (input.equalsIgnoreCase("EXIT")) {
                return -1; // Indicate that the user wants to exit
            }

            try {
                int volunteersNeeded = Integer.parseInt(input);

                // Ensure the number is positive
                if (volunteersNeeded > 0) {
                    return volunteersNeeded; // Return the valid number of volunteers needed
                } else {
                    System.out.println("The number of volunteers must be a positive integer. Please try again.");
                }
            } catch (NumberFormatException e) {
                // Handle invalid integer format
                System.out.println("Invalid input. Please enter a valid positive integer.");
            }
        }
    }

    public int inputAvailableVolunteerNeeded(int volunteerNeed) {
        int availableVolunteerNeeded = volunteerNeed;

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
        if (event == null) {
            System.out.println("Error: Event details are not available.");
            return;
        }

        // Print Header
        System.out.println("================================================================");
        System.out.println("                          Event Details                         ");
        System.out.println("================================================================");

        // Print Event Details with improved formatting
        System.out.printf("Event ID                   : %-20s%n", event.getEventId());
        System.out.printf("Event Name                 : %-20s%n", event.getEventName());
        System.out.printf("Event Address              : %-20s%n", event.getEventAddress());
        System.out.printf("Event Description          : %-20s%n", event.getEventDescription());
        System.out.printf("Event Start Date           : %-20s%n", event.getEventStartDate());
        System.out.printf("Event End Date             : %-20s%n", event.getEventEndDate());
        System.out.printf("Event Start Time           : %-20s%n", event.getEventStartTime());
        System.out.printf("Event End Time             : %-20s%n", event.getEventEndTime());
        System.out.printf("Event Status               : %-20s%n", event.getEventStatus());
        System.out.printf("Organizer Name             : %-20s%n", event.getEventOrganizerName());
        System.out.printf("Organizer Email            : %-20s%n", event.getEventOrganizerEmail());
        System.out.printf("Organizer Phone No         : %-20s%n", event.getEventOrganizerPhoneNo());
        System.out.printf("Event Type                 : %-20s%n", event.getEventType());
        System.out.printf("Volunteer Need             : %-20d%n", event.getVolunteerNeed());
        System.out.printf("Available Volunteers Needed: %-20d%n", event.getAvailableVolunteerNeeded());

        // Print Footer
        System.out.println("================================================================");
    }

    public int getEditMenu() {
        int choice = -1; // Initialize to an invalid choice
        boolean correctInput = false;

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
            System.out.println("0. Quit");
            System.out.print("Enter choice: ");

            // Check if input is an integer
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                // Validate choice
                if (choice >= 0 && choice <= 13) {
                    correctInput = true;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 0 and 13.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear invalid input
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
        int input = -1; // Initialize with an invalid value

        while (input < 0 || input > 2) {
            System.out.println("1-Yes 2-No 0-Exit");
            System.out.print("Enter choice: ");

            try {
                input = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (input < 0 || input > 2) {
                    System.out.println("Invalid choice. Please enter 0, 1, or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }

        return input;
    }

    public int confirmationEventMessage() {
        int input = -1; // Initialize with an invalid value

        while (input != 0 && input != 1) {
            System.out.println("Please confirm your choice:");
            System.out.println("1 - Yes");
            System.out.println("0 - Exit");
            System.out.print("Enter choice: ");

            try {
                input = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (input != 0 && input != 1) {
                    System.out.println("Invalid choice. Please enter 0 or 1.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }

        return input;
    }

    public int displaySearchMenu() {
        int choice = -1; // Initialize to an invalid choice
        boolean correctInput = false;

        do {
            // Clear the console for better readability (if supported by the environment)
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("============================================");
            System.out.println("            Search Menu Options            ");
            System.out.println("============================================");
            System.out.println("1. Search by Event ID");
            System.out.println("2. Search by Event Name");
            System.out.println("3. Search by Event Address");
            System.out.println("4. Search by Event Description");
            System.out.println("5. Search by Event Start Date");
            System.out.println("6. Search by Event Start Time");
            System.out.println("7. Search by Event Status");
            System.out.println("8. Search by Organizer Name");
            System.out.println("9. Search by Organizer Email");
            System.out.println("10. Search by Organizer Phone No");
            System.out.println("11. Search by Event Type");
            System.out.println("12. Search by Available Volunteer Needed");
            System.out.println("0. Quit");
            System.out.println("============================================");
            System.out.print("Enter your choice (0-12): ");

            choice = validateInt(); // Method to validate integer input
            System.out.println("");

            // Check if the input choice is within valid range
            if (choice >= 0 && choice <= 12) {
                correctInput = true;
            } else {
                displayInvalidChoiceMessage(); // Display invalid choice message
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
        line(400);
        System.out.printf("|Search Result Of -  %-378s|\n", search);
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
        line(400);
    }

    public int getConfirmation() {
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number (0, 1, or 2).");
            scanner.next(); // clear invalid input
        }
        return scanner.nextInt();
    }

    public int getRetryChoice() {
        System.out.println("0. Exit");
        System.out.println("1. Retry");
        System.out.print("Enter choice: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter 0 or 1.");
            scanner.next(); // clear invalid input
        }
        return scanner.nextInt();
    }

    public boolean promptRetry() {
        System.out.println("Would you like to view another event? (y/n):");
        String response = scanner.nextLine().trim().toLowerCase(); // Method to input a choice
        return response.equals("y");
    }

}