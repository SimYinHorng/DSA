/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.HashMap;
import boundary.EventManagementUI;
import dao.EventDAO;
import entity.Event;
import utility.EventType;
import java.util.Scanner;
import static utility.MessageUI.displayInvalidChoiceMessage;
import static utility.MessageUI.clearScreen;
import static utility.MessageUI.enterToContinue;

/**
 *
 * @author user
 */
public class EventManagement {

    Scanner scanner = new Scanner(System.in);

    private HashMap<String, Event> eventMap = new HashMap<>();
    private EventDAO eventDAO = new EventDAO();
    private EventManagementUI eventUI = new EventManagementUI();

    public EventManagement() {
        eventMap = eventDAO.retrieveFromFile();
    }

    public void runEventManagement() {
        int choice = 0;
        do {
            choice = eventUI.getMenuChoice();
            switch (choice) {
                case 0:
                    eventUI.displayExitMessage();
                    break;
                case 1:
                    addNewEvent();
                    break;
                case 2:
                    removeEvent();
                    break;
                case 3:
                    searchEvent();
                    break;
                case 4:
                    editEvent();
                    break;
                case 5:
                    displayAllEvents();
                    break;
                case 6:
                    removeEventFromVolunteer();
                    break;
                case 7:
                    listAllEventsForVolunteer();
                    break;
                case 8:
                    generateSummaryReports();
                    break;
                default:
                    displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    public void addNewEvent() {
        boolean exit = false;
        Event newEvent = eventUI.inputEventDetails();
        do {
            eventUI.displayEventDetails(newEvent);
            System.out.println("Are the event details correct?");
            int input = eventUI.confirmationMessage();
            switch (input) {
                case 1:
                    if (validateEvent(newEvent)) {
                        eventMap.put(newEvent.getEventId(), newEvent);
                        eventDAO.saveToFile(eventMap);
                        System.out.println("Event added successfully.");
                        exit = true;
                    } else {
                        System.out.println("Invalid event details. Please correct them.");
                    }
                    break;
                case 2:
                    amendEventDetails(newEvent);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!exit);
    }

    private boolean validateEvent(Event event) {
        // Implement validation logic here
        return event.getEventName() != null && !event.getEventName().isEmpty();
    }

 public void removeEvent() {
    boolean continueRemoving = true;

    while (continueRemoving) {
        try {
            clearScreen();
            displayAllEvents();
            System.out.println("Select Event To Delete (0 to exit):");

            String eventId = eventUI.inputEventId().toUpperCase();

            if (eventId.equals("0")) {
                System.out.println("Exiting removal process.");
                break; // Exit the loop
            }

            Event event = eventMap.get(eventId);
            if (event != null) {
                eventUI.displayEventDetails(event);
                System.out.println("Are you sure you want to delete this event?");
                
                int confirmation = eventUI.confirmationMessage();
                if (confirmation == 1) {
                    eventMap.remove(eventId);
                    eventDAO.saveToFile(eventMap);
                    System.out.println("Event removed successfully.");
                    enterToContinue();
                    break; // Exit the loop after successful removal
                } else if (confirmation == 2) {
                    System.out.println("Event removal canceled.");
                    continue; // Restart the loop
                } else if (confirmation == 0) {
                    System.out.println("Exiting removal process.");
                    break; // Exit the loop
                }
            } else {
                System.out.println("Event not found with ID: " + eventId);
                System.out.println("Would you like to try again? (1-Yes, 0-Exit)");
                
                int retryChoice = eventUI.confirmationMessage();
                if (retryChoice == 0) {
                    System.out.println("Exiting removal process.");
                    break; // Exit the loop
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            break; // Exit the loop on error
        }
    }
}

    public void searchEvent() {
        String eventId = eventUI.inputEventId();
        if (eventMap.containsKey(eventId)) {
            eventUI.displayEventDetails(eventMap.get(eventId));
        } else {
            System.out.println("Event not found.");

//    Scanner scanner = new Scanner(System.in);
//
//    // Display the search criteria options to the user
//    System.out.println("Select search field:");
//    System.out.println("1. Event ID");
//    System.out.println("2. Event Name");
//    System.out.println("3. Event Address");
//    System.out.println("4. Event Organizer Name");
//    System.out.println("5. Event Type");
//    
//    // Get the search criteria as an integer
//    int searchCriteria = scanner.nextInt();
//    scanner.nextLine(); // Consume newline character
        // Call the method to search based on the criteria
//    searchEventType(searchCriteria, eventUI); // Pass `this` to represent the current instance of `EventManagementUI`
    }

// Method to search events based on criteria
//public void searchEventType(int searchCriteria, EventManagementUI eventUI) {
//    switch (searchCriteria) {
//        case 1:
//            // Search by Event ID
//            String eventId = eventUI.getSearchKeyword("Enter Event ID: ");
//            Event event = eventMap.get(eventId.toUpperCase()); // Convert to uppercase to match stored IDs
//            if (event != null) {
//                eventUI.displayEventDetails(event);
//            } else {
//                System.out.println("Event not found.");
//            }
//            break;
//        case 2:
//            // Search by Event Name
//            String eventName = eventUI.getSearchKeyword("Enter Event Name: ");
//            searchAndDisplayEventsByField("Event Name", eventName);
//            break;
//        case 3:
//            // Search by Event Address
//            String eventAddress = eventUI.getSearchKeyword("Enter Event Address: ");
//            searchAndDisplayEventsByField("Event Address", eventAddress);
//            break;
//        case 4:
//            // Search by Event Organizer Name
//            String organizerName = eventUI.getSearchKeyword("Enter Event Organizer Name: ");
//            searchAndDisplayEventsByField("Event Organizer Name", organizerName);
//            break;
//        case 5:
//            // Search by Event Type
//            String eventType = eventUI.getSearchKeyword("Enter Event Type: ");
//            searchAndDisplayEventsByField("Event Type", eventType);
//            break;
//        default:
//            System.out.println("Invalid choice.");
//            break;
//    }
//}
//     private void searchAndDisplayEventsByField(String field, String keyword) {
//    boolean found = false; // Flag to check if any events are found
//  ArrayList<Event> eventList = new ArrayList<>();
//        // Using enhanced for-each loop
//        for (Event event : eventList) {
//              boolean match = false;
//        switch (field) {
//            case "Event Name":
//                match = event.getEventName().toLowerCase().contains(keyword.toLowerCase());
//                break;
//            case "Event Address":
//                match = event.getEventAddress().toLowerCase().contains(keyword.toLowerCase());
//                break;
//            case "Event Organizer Name":
//                match = event.getEventOrganizerName().toLowerCase().contains(keyword.toLowerCase());
//                break;
//            case "Event Type":
//                match = event.getEventType().toString().toLowerCase().contains(keyword.toLowerCase());
//                break;
//        }
//        if (match) {
//            displayEventDetails(event);
//            found = true;
//        }
//    }
//
//    if (!found) {
//        System.out.println("No events found matching the criteria.");
//    }
//}
//
    }
    // Method to display event details
// Method to get the event type from the user
    public void editEvent() {
        boolean exit = false;
        do {
            clearScreen();
            displayAllEvents(); // Display all events
            System.out.println("Select the event to edit (Id, 0 to exit):");
            String eventId = eventUI.inputEventId(); // Assuming inputEventId() returns a String
            enterToContinue();

                
            if (eventId.equals("0")) {
                exit = true; // Exit the loop if the user inputs '0'
            } else {
                Event eventToAmend = eventMap.get(eventId); // Retrieve the event from the map
                if (eventToAmend != null) {
                    boolean detailsConfirmed = false;
                    while (!detailsConfirmed) {
                        amendEventDetails(eventToAmend); // Amend the event details
                        clearScreen();
                        eventUI.displayEventDetails(eventToAmend); // Show updated event details
                        System.out.println("Are the details correct?");
                        int confirm = eventUI.confirmationMessage();
                        switch (confirm) {
                            case 1:
                                eventMap.put(eventToAmend.getEventId(), eventToAmend);
                                eventDAO.saveToFile(eventMap);
                                clearScreen();
                                System.out.println("Event details updated successfully.");
                                detailsConfirmed = true; // Exit the confirmation loop
                                enterToContinue();
                                break;
                            case 2:
                                System.out.println("Let's edit the details again.");
                                // Continue the loop to amend details again
                                break;
                            case 0:
                                System.out.println("Exiting to main menu.");
                                exit = true;
                                detailsConfirmed = true; // Exit both loops
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                } else {
                    System.out.println("Event not found. Please try again.");
                }
            }
        } while (!exit);
    }

    public void amendEventDetails(Event event) {
        boolean doneEditing = false;
        while (!doneEditing) {
            enterToContinue();
            clearScreen();
            System.out.println("Current Event Details:");
            eventUI.displayEventDetails(event); // Show current event details

            int choice = eventUI.getEditMenu(); // Get the user's choice

            switch (choice) {
                case 0:
                    doneEditing = true; // Exit the loop
                    break;
                case 1:
                    event.setEventName(eventUI.inputEventName());
                    break;
                case 2:
                    event.setEventAddress(eventUI.inputEventAddress());
                    break;
                case 3:
                    event.setEventStartDate(eventUI.inputEventStartDate());
                    break;
                case 4:
                    // Ensure the end date is after the start date
                    String startDate = event.getEventStartDate();
                    String endDate = eventUI.inputEventEndDate(startDate);
                    if (endDate.compareTo(startDate) >= 0) {
                        event.setEventEndDate(endDate);
                    } else {
                        System.out.println("End date cannot be before start date.");
                    }
                    break;
                case 5:
                    // Set start time and validate
                    event.setEventStartTime(eventUI.inputEventStartTime());
                    break;
                case 6:
                    // Ensure the end time is after the start time
                    String startTime = event.getEventStartTime();
                    String endTime = eventUI.inputEventEndTime(startTime);
                    if (endTime.compareTo(startTime) >= 0) {
                        event.setEventEndTime(endTime);
                    } else {
                        System.out.println("End time cannot be before start time.");
                    }
                    break;
                case 7:
                    event.setEventOrganizerName(eventUI.inputEventOrganizerName());
                    break;
                case 8:
                    event.setEventOrganizerEmail(eventUI.inputEventOrganizerEmail());
                    break;
                case 9:
                    event.setEventOrganizerPhoneNo(eventUI.inputEventOrganizerPhoneNo());
                    break;
                case 10:
                    event.setEventType(eventUI.inputEventType());
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public EventType getEventType() {
        System.out.println("Select Event Type to search:");
        System.out.println("1. Fundraiser");
        System.out.println("2. Awareness");
        System.out.println("3. Volunteer Drive");
        System.out.println("4. Donation Drive");
        System.out.print("Enter choice: ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (typeChoice) {
            case 1:
                return EventType.FUNDRAISER;
            case 2:
                return EventType.AWARENESS;
            case 3:
                return EventType.VOLUNTEER_DRIVE;
            case 4:
                return EventType.DONATION_DRIVE;
            default:
                System.out.println("Invalid choice. Please try again.");
                return null;
        }
    }

 
    public void displayAllEvents() {
        eventUI.listAllEvents(eventMap);
        enterToContinue();
    }

    public static void main(String[] args) {
        EventManagement eventManagement = new EventManagement();
        eventManagement.runEventManagement();
    }

    private void removeEventFromVolunteer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void listAllEventsForVolunteer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void generateSummaryReports() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
