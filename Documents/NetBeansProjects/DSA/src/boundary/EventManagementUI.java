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

    public String inputEventName() {
        System.out.print("Enter Event Name: ");
        String eventName = scanner.nextLine();
        System.out.print("\n ");
      
        while (eventName.isEmpty()) {
            System.out.print("\nEvent Name cannot be empty. \nEnter Event Name: ");
            eventName = scanner.nextLine();
        }
        return eventName;
    }

    public String inputEventAddress() {
        System.out.print("\nEnter Event Address: ");
        String eventAddress = scanner.nextLine();
        while (eventAddress.isEmpty()) {
            System.out.print("\nEvent Address cannot be empty. \nEnter Event Address: ");
            eventAddress = scanner.nextLine();
        }
        return eventAddress;
    }

    public String inputEventDescription() {
        System.out.print("\nEnter Event Description: ");
        String eventDescription = scanner.nextLine();
        while (eventDescription.isEmpty()) {
            System.out.print("\nEvent Description cannot be empty. \nEnter Event Description: ");
            eventDescription = scanner.nextLine();
        }
        return eventDescription;
    }

    public LocalDate inputEventStartDate() {
        while (true) {
            System.out.print("\nEnter Event Start Date (yyyy-MM-dd): ");
            String input = scanner.nextLine().trim();
            try {
                LocalDate date = LocalDate.parse(input, DATE_FORMATTER);
                if (date.isBefore(LocalDate.now())) {
                    System.out.println("\nStart date cannot be in the past.");
                } else {
                    return date;
                }
            } catch (DateTimeParseException e) {
                System.out.println("\nInvalid date format. Please use yyyy-MM-dd.");
            }
        }
    }

    public LocalDate inputEventEndDate(LocalDate startDate) {
        while (true) {
            System.out.print("\nEnter Event End Date (yyyy-MM-dd): ");
            String input = scanner.nextLine().trim();
            try {
                LocalDate date = LocalDate.parse(input, DATE_FORMATTER);
                if (date.isEqual(startDate) || date.isAfter(startDate)) {
                    return date;
                } else {
                    System.out.println("\nEnd date must be the same as or after the start date.");
                }
            } catch (Exception e) {
                System.out.println("\nInvalid date format. Please use yyyy-MM-dd.");
            }
        }
    }

    public LocalTime inputEventStartTime() {
        while (true) {
            System.out.print("\nEnter Event Start Time (24hours format HH:mm): ");
            String input = scanner.nextLine().trim();
            try {
                return LocalTime.parse(input, TIME_FORMATTER);
            } catch (Exception e) {
                System.out.println("\nInvalid time format. Please use 24hours format HH:mm.");
            }
        }
    }

    public LocalTime inputEventEndTime(LocalTime startTime) {
        while (true) {
            System.out.print("\nEnter Event End Time (24hours format HH:mm): ");
            String input = scanner.nextLine().trim();
            try {
                LocalTime endTime = LocalTime.parse(input, TIME_FORMATTER);
                if (endTime.isAfter(startTime)) {
                    return endTime;
                } else {
                    System.out.println("\nEnd time must be after the start time and cannot be the same.");
                }
            } catch (Exception e) {
                System.out.println("\nInvalid time format. Please use 24hours format HH:mm.");
            }
        }
    }

    public String inputEventOrganizerName() {
        System.out.print("\nEnter Organizer Name: ");
        String eventOrganizerName = scanner.nextLine();
        while (eventOrganizerName.isEmpty()) {
            System.out.print("\nOrganizer Name cannot be empty. \nEnter Organizer Name: ");
            eventOrganizerName = scanner.nextLine();
        }
        return eventOrganizerName;
    }

    public String inputEventOrganizerEmail() {
        System.out.print("\nEnter Organizer Email: ");
        String eventOrganizerEmail = scanner.nextLine();
        while (!isValidEmail(eventOrganizerEmail)) {
            System.out.print("\nInvalid email format. \nEnter Organizer Email: ");
            eventOrganizerEmail = scanner.nextLine();
        }
        return eventOrganizerEmail;
    }

    public String inputEventOrganizerPhoneNo() {
        System.out.print("\nEnter Organizer Phone No: ");
        String eventOrganizerPhoneNo = scanner.nextLine();
        while (!isValidPhoneNumber(eventOrganizerPhoneNo)) {
            System.out.print("\nInvalid phone number format. Enter Organizer Phone No: ");
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
        inputAvailableVolunteerNeeded(volunteerNeed);
        return volunteerNeed;
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

    public String inputEventId() {
        System.out.print("Input Event ID: ");
        return scanner.nextLine().toUpperCase();
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

    public String inputVolunteerId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Volunteer ID: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a numeric Volunteer ID.");
            scanner.next(); // clear invalid input
        }
        return scanner.next();
    }

    public int getConfirmation() {
        Scanner scanner = new Scanner(System.in);
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
        Scanner scanner = new Scanner(System.in);
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
