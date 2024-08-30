package control;

import adt.HashMap;
import adt.ArrayList;
import boundary.EventManagementUI;
import control.VolunteerManagement;
import dao.EventDAO;
import dao.VolunteerDAO;
import entity.Event;
import entity.Volunteer;
import java.io.IOException;
import java.util.Iterator;
import utility.EventType;
import java.util.Scanner;
import static utility.MessageUI.displayInvalidChoiceMessage;
import static utility.MessageUI.clearScreen;
import static utility.MessageUI.displayEventHeader;
import static utility.MessageUI.enterToContinue;
import static utility.MessageUI.line;

public class EventManagement {

    Scanner scanner = new Scanner(System.in);

    private HashMap<String, Event> eventMap = new HashMap<>();
    private HashMap<Integer, Volunteer> volunteerMap = new HashMap<>();
    private ArrayList<Event> eventList = new ArrayList<>();
    private EventDAO eventDAO = new EventDAO();
    private VolunteerDAO volunteerDAO = new VolunteerDAO();

    private EventManagementUI eventUI = new EventManagementUI();
    private VolunteerManagement volunteer = new VolunteerManagement();

    public EventManagement() {
        eventMap = eventDAO.retrieveFromFile();
        volunteerMap = volunteerDAO.retrieveFromFile();
        // Initialize eventList if necessary
        // eventList = eventDAO.retrieveEventList();
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

        while (!exit) {
            eventUI.displayEventDetails(newEvent);
            System.out.println("Are the event details correct?");
            int input = eventUI.confirmationMessage();

            switch (input) {
                case 1:
                    if (validateEvent(newEvent)) {
                        eventMap.put(newEvent.getEventId(), newEvent);
                        eventList.add(newEvent);
                        eventDAO.saveToFile(eventMap, eventList);
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
                    handleInvalidChoice();
            }
        }
    }

    private void handleInvalidChoice() {
        System.out.println("Invalid choice. Please try again.");
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
                    break;
                }

                Event event = eventMap.get(eventId);
                if (event != null) {
                    eventUI.displayEventDetails(event);
                    System.out.println("Are you sure you want to delete this event?");

                    int confirmation = eventUI.confirmationMessage();
                    if (confirmation == 1) {
                        eventMap.remove(eventId);
                        eventDAO.saveToFile(eventMap, eventList);
                        System.out.println("Event removed successfully.");
                        enterToContinue();
                        break;
                    } else if (confirmation == 2) {
                        System.out.println("Event removal canceled.");
                        continue;
                    } else if (confirmation == 0) {
                        System.out.println("Exiting removal process.");
                        break;
                    }
                } else {
                    System.out.println("Event not found with ID: " + eventId);
                    System.out.println("Would you like to try again? (1-Yes, 0-Exit)");

                    int retryChoice = eventUI.confirmationMessage();
                    if (retryChoice == 0) {
                        System.out.println("Exiting removal process.");
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                break;
            }
        }
    }

    public void searchEvent() {
        String eventId = eventUI.inputEventId();
        if (eventMap.containsKey(eventId)) {
            eventUI.displayEventDetails(eventMap.get(eventId));
        } else {
            System.out.println("Event not found.");
        }
    }

    public void editEvent() {
        boolean exit = false;
        do {
            clearScreen();
            displayAllEvents();
            System.out.println("Select the event to edit (Id, 0 to exit):");
            String eventId = eventUI.inputEventId();
            enterToContinue();

            if (eventId.equals("0")) {
                exit = true;
            } else {
                Event eventToAmend = eventMap.get(eventId);
                if (eventToAmend != null) {
                    boolean detailsConfirmed = false;
                    while (!detailsConfirmed) {
                        amendEventDetails(eventToAmend);
                        clearScreen();
                        eventUI.displayEventDetails(eventToAmend);
                        System.out.println("Are the details correct?");
                        int confirm = eventUI.confirmationMessage();
                        switch (confirm) {
                            case 1:
                                eventMap.put(eventToAmend.getEventId(), eventToAmend);
                                eventDAO.saveToFile(eventMap, eventList);
                                clearScreen();
                                System.out.println("Event details updated successfully.");
                                detailsConfirmed = true;
                                enterToContinue();
                                break;
                            case 2:
                                System.out.println("Let's edit the details again.");
                                break;
                            case 0:
                                System.out.println("Exiting to main menu.");
                                exit = true;
                                detailsConfirmed = true;
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

    public void displayAllEvents() {
        listAllEvents(eventMap);
        enterToContinue();
    }

    private void removeEventFromVolunteer() {
            boolean continueRemoving = true;

        while (continueRemoving) {
            try {
                clearScreen();
                displayAllEvents();
                System.out.println("Select Event To View The Volunteer (0 to exit):");

                String eventId = eventUI.inputEventId().toUpperCase();

                if (eventId.equals("0")) {
                    System.out.println("Exiting removal process.");
                    break;
                }

                Event event = eventMap.get(eventId);
                if (event != null) {
                    eventUI.displayEventDetails(event);
                    System.out.println("Are you sure you want to delete this event?");

                    int confirmation = eventUI.confirmationMessage();
                    if (confirmation == 1) {
                        eventMap.remove(eventId);
                        eventDAO.saveToFile(eventMap, eventList);
                        System.out.println("Event removed successfully.");
                        enterToContinue();
                        break;
                    } else if (confirmation == 2) {
                        System.out.println("Event removal canceled.");
                        continue;
                    } else if (confirmation == 0) {
                        System.out.println("Exiting removal process.");
                        break;
                    }
                } else {
                    System.out.println("Event not found with ID: " + eventId);
                    System.out.println("Would you like to try again? (1-Yes, 0-Exit)");

                    int retryChoice = eventUI.confirmationMessage();
                    if (retryChoice == 0) {
                        System.out.println("Exiting removal process.");
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                break;
            }
        }
    

    }

    private void listAllEventsForVolunteer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void generateSummaryReports() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private boolean validateEvent(Event event) {
        return event.getEventName() != null && !event.getEventName().isEmpty();
    }

    public void amendEventDetails(Event event) {
        boolean doneEditing = false;
        while (!doneEditing) {
            enterToContinue();
            clearScreen();
            System.out.println("Current Event Details:");
            eventUI.displayEventDetails(event);

            int choice = eventUI.getEditMenu();

            switch (choice) {
                case 0:
                    doneEditing = true;
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
                    String startDate = event.getEventStartDate();
                    String endDate = eventUI.inputEventEndDate(startDate);
                    if (endDate.compareTo(startDate) >= 0) {
                        event.setEventEndDate(endDate);
                    } else {
                        System.out.println("End date cannot be before start date.");
                    }
                    break;
                case 5:
                    event.setEventStartTime(eventUI.inputEventStartTime());
                    break;
                case 6:
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

    public static void main(String[] args) {
        EventManagement eventManagement = new EventManagement();
        eventManagement.runEventManagement();
    }

    // Add a volunteer to an event
    public void addVolunteerToEvent(String eventId, Volunteer volunteer) {
        eventMap = eventDAO.retrieveFromFile(); // Retrieve the current state of the event data
        volunteerMap = volunteerDAO.retrieveFromFile(); // Retrieve the current state of the volunteer data

        // Check if the event exists
        Event event = eventMap.get(eventId);
        if (event != null) {
            ArrayList<Volunteer> participantList = event.getParticipantList();

            participantList.add(volunteer); // Add the volunteer to the event

            // Update the event in the map
            event.setParticipantList(participantList);
            eventMap.put(eventId, event);

            // Save the updated data back to files
            ArrayList<Event> eventList = new ArrayList<>();
            eventList.add((Event) eventMap.values()); // Replace for loop with addAll method
            eventDAO.saveToFile(eventMap, eventList);

            System.out.println("Volunteer added to event successfully.");
        } else {
            System.out.println("Event with ID " + eventId + " not found.");
        }
    }

    public void listAllEvents(HashMap<String, Event> eventMap) {
        // Check if the HashMap is available and not empty
        if (eventMap == null || eventMap.isEmpty()) {
            System.out.println("No events to display.");
            return;
        }

        // Display header for events
        displayEventHeader();

        // Iterate over the keys of the HashMap
        Iterator<String> keyIterator = eventMap.keySet().getIterator();
        while (keyIterator.hasNext()) {
            String eventId = keyIterator.next();
            Event event = eventMap.get(eventId);
            if (event != null) {  // Check for null events
                System.out.println(event.toString());
            } else {
                System.out.println("Encountered null event.");
            }
        }

        // Print a line separator for clarity
        line(305);
    }

}
