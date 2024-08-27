/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.ArrayList;
import adt.HashMap;
import boundary.EventManagementUI;
import dao.EventDAO;
import entity.Event;
import utility.EventType;
import java.io.IOException;
import java.util.Scanner;
import static utility.MessageUI.displayInvalidChoiceMessage;

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
                    // Get the event to amend
                    String eventId = eventUI.inputEventId(); // Assuming inputEventId() returns a String
                    Event eventToAmend = eventMap.get(eventId); // Retrieve the event from the map
                    if (eventToAmend != null) {
                        amendEventDetails(eventToAmend); // Correct method call
                    } else {
                        System.out.println("Event not found.");
                    }
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
                // Get the event ID from the user and convert it to uppercase
                String eventId = eventUI.inputEventId().toUpperCase();

                // Check if the eventMap contains the key
                if (eventMap.containsKey(eventId)) {
                    // Fetch the event from the map
                    Event event = eventMap.get(eventId);

                    // Display event details
                    eventUI.displayEventDetails(event);

                    // Ask for confirmation to remove the event
                    System.out.println("Are you sure you want to delete this event?");
                    int confirmation = eventUI.confirmationMessage();

                    if (confirmation == 1) { // Assuming 1 is Yes
                        System.out.println("Removing event with ID: " + eventId);
                        eventMap.remove(eventId);
                        eventDAO.saveToFile(eventMap); // Save the updated map
                        System.out.println("Event removed successfully.");
                        continueRemoving = false; // Exit the loop after successful removal
                    } else if (confirmation == 2) { // Assuming 2 is No
                        System.out.println("Event removal canceled.");
                        // Continue to prompt for new event ID
                    } else if (confirmation == 0) { // Assuming 0 is Exit
                        System.out.println("Exiting removal process.");
                        continueRemoving = false; // Exit the loop if user chooses to exit
                    } else {
                        System.out.println("Invalid choice. Please enter 1 for Yes, 2 for No, or 0 to Exit.");
                    }
                } else {
                    System.out.println("Event not found with ID: " + eventId);
                    // Ask if the user wants to try again or exit
                    System.out.println("Would you like to try again or exit?");
                    int retryChoice = eventUI.confirmationMessage();

                    if (retryChoice == 0) { // Assuming 0 is Exit
                        System.out.println("Exiting removal process.");
                        continueRemoving = false; // Exit the loop if user chooses to exit
                    } else if (retryChoice == 1) { // Assuming 1 is Retry
                        System.out.println("Please enter a valid event ID.");
                        // Continue to prompt for new event ID
                    } else {
                        System.out.println("Invalid choice. Please enter 1 to retry or 0 to exit.");
                    }
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                continueRemoving = false; // Exit the loop on error
            }
        }
    }

  // Method to search events based on criteria
    public void searchEvent(int searchCriteria, EventManagementUI eventUI) {
        switch (searchCriteria) {
            case 1:
                // Search by Event ID
                String eventId = eventUI.getSearchKeyword("Enter Event ID: ");
                Event event = eventMap.get(eventId.toUpperCase()); // Convert to uppercase to match stored IDs
                if (event != null) {
                    eventUI.displayEventDetails(event);
                } else {
                    System.out.println("Event not found.");
                }
                break;
            case 2:
                // Search by Event Name
                String eventName = eventUI.getSearchKeyword("Enter Event Name: ");
                searchAndDisplayEventsByField("Event Name", eventName);
                break;
            case 3:
                // Search by Event Address
                String eventAddress = eventUI.getSearchKeyword("Enter Event Address: ");
                searchAndDisplayEventsByField("Event Address", eventAddress);
                break;
            case 4:
                // Search by Event Organizer Name
                String organizerName = eventUI.getSearchKeyword("Enter Event Organizer Name: ");
                searchAndDisplayEventsByField("Event Organizer Name", organizerName);
                break;
            case 5:
                // Search by Event Type
                String eventType = eventUI.getSearchKeyword("Enter Event Type: ");
                searchAndDisplayEventsByField("Event Type", eventType);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    // Helper method to search and display events based on a specific field
 private void searchAndDisplayEventsByField(String field, String keyword) {
    boolean found = false; // Flag to check if any events are found
    for (Event event : eventMap.values()) {
        boolean match = false;
        switch (field) {
            case "Event Name":
                match = event.getEventName().toLowerCase().contains(keyword.toLowerCase());
                break;
            case "Event Address":
                match = event.getEventAddress().toLowerCase().contains(keyword.toLowerCase());
                break;
            case "Event Organizer Name":
                match = event.getEventOrganizerName().toLowerCase().contains(keyword.toLowerCase());
                break;
            case "Event Type":
                match = event.getEventType().toString().toLowerCase().contains(keyword.toLowerCase());
                break;
        }
        if (match) {
            displayEventDetails(event);
            found = true;
        }
    }

    if (!found) {
        System.out.println("No events found matching the criteria.");
    }
}
   
// Method to get the event type from the user
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

    // Method to display event details
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

    public void amendEventDetails(Event event) {
        int choice = eventUI.getEditMenu();
        switch (choice) {
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
                // Pass the current start date to ensure the end date is not before it
                String startDate = event.getEventStartDate();
                String endDate = eventUI.inputEventEndDate(startDate);
                event.setEventEndDate(endDate);
                break;
            case 5:
                // Set start time first to validate end time
                event.setEventStartTime(eventUI.inputEventStartTime());
                break;
            case 6:
                // Pass the current start time to ensure the end time is not before it
                String startTime = event.getEventStartTime();
                String endTime = eventUI.inputEventEndTime(startTime);
                event.setEventEndTime(endTime);
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
                event.setEventType(eventUI.inputEventType()); // Assuming this method exists
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void displayAllEvents() {
        eventUI.listAllEvents(eventMap);
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
