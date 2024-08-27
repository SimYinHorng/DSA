package boundary;

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
        String eventEndDate = inputEventEndDate();
        String eventStartTime = inputEventStartTime();
        String eventEndTime = inputEventEndTime();
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
        System.out.print("Enter Event Start Date (YYYY-MM-DD): ");
        String eventStartDate = scanner.nextLine();
        while (!isValidDate(eventStartDate)) {
            System.out.print("Invalid date format. Enter Event Start Date (YYYY-MM-DD): ");
            eventStartDate = scanner.nextLine();
        }
        return eventStartDate;
    }

    public String inputEventEndDate() {
        System.out.print("Enter Event End Date (YYYY-MM-DD): ");
        String eventEndDate = scanner.nextLine();
        while (!isValidDate(eventEndDate)) {
            System.out.print("Invalid date format. Enter Event End Date (YYYY-MM-DD): ");
            eventEndDate = scanner.nextLine();
        }
        return eventEndDate;
    }

    public String inputEventStartTime() {
        System.out.print("Enter Event Start Time (HH:MM): ");
        String eventStartTime = scanner.nextLine();
        while (!isValidTime(eventStartTime)) {
            System.out.print("Invalid time format. Enter Event Start Time (HH:MM): ");
            eventStartTime = scanner.nextLine();
        }
        return eventStartTime;
    }

    public String inputEventEndTime() {
        System.out.print("Enter Event End Time (HH:MM): ");
        String eventEndTime = scanner.nextLine();
        while (!isValidTime(eventEndTime)) {
            System.out.print("Invalid time format. Enter Event End Time (HH:MM): ");
            eventEndTime = scanner.nextLine();
        }
        return eventEndTime;
    }

    private String inputEventOrganizerName() {
        System.out.print("Enter Organizer Name: ");
        String eventOrganizerName = scanner.nextLine();
        while (eventOrganizerName.isEmpty()) {
            System.out.print("Organizer Name cannot be empty. Enter Organizer Name: ");
            eventOrganizerName = scanner.nextLine();
        }
        return eventOrganizerName;
    }

    private String inputEventOrganizerEmail() {
        System.out.print("Enter Organizer Email: ");
        String eventOrganizerEmail = scanner.nextLine();
        while (!isValidEmail(eventOrganizerEmail)) {
            System.out.print("Invalid email format. Enter Organizer Email: ");
            eventOrganizerEmail = scanner.nextLine();
        }
        return eventOrganizerEmail;
    }

    private String inputEventOrganizerPhoneNo() {
        System.out.print("Enter Organizer Phone No: ");
        String eventOrganizerPhoneNo = scanner.nextLine();
        while (!isValidPhoneNumber(eventOrganizerPhoneNo)) {
            System.out.print("Invalid phone number format. Enter Organizer Phone No: ");
            eventOrganizerPhoneNo = scanner.nextLine();
        }
        return eventOrganizerPhoneNo;
    }


    // Validation methods
    private boolean isValidDate(String date) {
        // Add validation logic for the date format (YYYY-MM-DD)
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    private boolean isValidTime(String time) {
        // Add validation logic for the time format (HH:MM)
        return time.matches("\\d{2}:\\d{2}");
    }

    private boolean isValidEmail(String email) {
        // Simple email validation pattern
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    private boolean isValidPhoneNumber(String phoneNo) {
        // Simple phone number validation (assuming 10-15 digits)
        return phoneNo.matches("\\d{10,15}");
    }

    private EventType inputEventType() {
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

    public void listAllEvents(HashMap<Integer, Event> eventMap) {
        Iterator keyIt = eventMap.keySet().getIterator();

        displayEventHeader();
        while (keyIt.hasNext()) {
            System.out.println(eventMap.get((Integer)keyIt.next()).toString());
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

    public int inputEventId() {
        System.out.println("Input Event Id:");
        scanner.nextLine();
        return 0;
    }
}
