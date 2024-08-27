/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.HashMap;
import boundary.EventManagementUI;
import dao.EventDAO;
import entity.Event;
import java.io.IOException;
import static utility.MessageUI.displayInvalidChoiceMessage;

/**
 *
 * @author user
 */
public class EventManagement {

    private HashMap<Integer, Event> eventMap = new HashMap<>();
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
                    amendEventDetails();
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
                    editEventDetails(newEvent);
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

    private void editEventDetails(Event event) {
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
                event.setEventEndDate(eventUI.inputEventEndDate());
                break;
            case 5:
                event.setEventStartTime(eventUI.inputEventStartTime());
                break;
            case 6:
                event.setEventEndTime(eventUI.inputEventEndTime());
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

 public void removeEvent() {
    try {
        // Convert input event ID to integer if necessary
        int eventId = eventUI.inputEventId(); // Ensure inputEventId returns a valid string
        
        // Check if the eventMap contains the key
    if (eventMap.containsKey(eventId)) {
            System.out.println("Removing event with ID: " + eventId);
            eventMap.remove(eventId);
            eventDAO.saveToFile(eventMap); // Ensure this saves correctly
            System.out.println("Event removed successfully.");
        } else {
            System.out.println("Event not found.");
        }
    } catch (NumberFormatException e) {
        System.out.println("Invalid event ID format. Please enter a numeric ID.");
    }
}

    public void searchEvent() {
        int eventId = eventUI.inputEventId();
        if (eventMap.containsKey(eventId)) {
            eventUI.displayEventDetails(eventMap.get(eventId));
        } else {
            System.out.println("Event not found.");
        }
    }

    public void amendEventDetails() {
        int eventId = eventUI.inputEventId();
        if (eventMap.containsKey(eventId)) {
            Event existingEvent = eventMap.get(eventId);
            eventUI.displayEventDetails(existingEvent);
            addNewEvent();
        } else {
            System.out.println("Event not found.");
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
