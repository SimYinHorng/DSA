package control;

import adt.HashMap;
import adt.ArrayList;
import adt.LinkedList;
import boundary.EventManagementUI;
import control.VolunteerManagement;

import dao.EventDAO;
import dao.EventInitializer;
import dao.VolunteerDAO;
import entity.Event;
import entity.Volunteer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import utility.EventType;
import static utility.MessageUI.displayInvalidChoiceMessage;
import static utility.MessageUI.clearScreen;
import static utility.MessageUI.displayEventHeader;
import static utility.MessageUI.enterToContinue;
import static utility.MessageUI.line;

public class EventManagement {

    private HashMap<String, Event> eventMap = new HashMap<>();
    private HashMap<Integer, Volunteer> volunteerMap = new HashMap<>();
    private ArrayList<Event> eventList = new ArrayList<>();
    private EventDAO eventDAO = new EventDAO();
    private VolunteerDAO volunteerDAO = new VolunteerDAO();
    private EventInitializer eventIni = new EventInitializer();
    private EventManagementUI eventUI = new EventManagementUI();
    private VolunteerManagement volunteer = new VolunteerManagement();

    public EventManagement() {

        eventMap = eventDAO.retrieveFromFile();
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
                    removeTheEvent();
                    break;
                case 3:
                    searchTheEvent();
                    break;
                case 4:
                    editTheEvent();
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
        addEvent();
    }

    public void removeTheEvent() {
        removeEvent();
    }

    public void searchTheEvent() {
        searchEvent();
    }

    public void editTheEvent() {
        editEvent();
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
                System.out.println("Select Event To View The  (0 to exit):");

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
                        eventDAO.saveToFile(eventMap);
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
                    event.setEventDescription(eventUI.inputEventDescription());
                    break;
                case 4:
                    event.setEventStartDate(eventUI.inputEventStartDate());
                    break;
                case 5:
                    LocalDate startDate = event.getEventStartDate();
                    LocalDate endDate = eventUI.inputEventEndDate(startDate);
                    if (endDate.compareTo(startDate) >= 0) {
                        event.setEventEndDate(endDate);
                    } else {
                        System.out.println("End date cannot be before start date.");
                    }
                    break;

                case 6:
                    event.setEventStartTime(eventUI.inputEventStartTime());
                    break;
                case 7:
                    LocalTime startTime = event.getEventStartTime();
                    LocalTime endTime = eventUI.inputEventEndTime(startTime);
                    if (endTime.compareTo(startTime) >= 0) {
                        event.setEventEndTime(endTime);
                    } else {
                        System.out.println("End time cannot be before start time.");
                    }
                    break;

                case 8:
                    event.setEventStatus(eventUI.inputEventStatus());
                    break;
                case 9:
                    event.setEventOrganizerName(eventUI.inputEventOrganizerName());
                    break;
                case 10:
                    event.setEventOrganizerEmail(eventUI.inputEventOrganizerEmail());
                    break;
                case 11:
                    event.setEventOrganizerPhoneNo(eventUI.inputEventOrganizerPhoneNo());
                    break;
                case 12:
                    event.setEventType(eventUI.inputEventType());
                    break;
                case 13:
                    event.setVolunteerNeed(eventUI.inputVolunteerNeedForTheEvent());
                    break;
                case 14:
                    event.setAvailableVolunteerNeeded(eventUI.inputAvailableVolunteerNeeded(event.getVolunteerNeed()));
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

    public void addEvent() {
        boolean exit = false;
        Event newEvent = inputEventDetails();

        while (!exit) {
            eventUI.displayEventDetails(newEvent);
            System.out.println("Are the event details correct?");
            int input = eventUI.confirmationMessage();
            switch (input) {
                case 1:
                    eventMap.put(newEvent.getEventId(), newEvent);
                    eventDAO.saveToFile(eventMap);
                    System.out.println("Event added successfully.");
                    exit = true;
                    break;
                case 2:
                    amendEventDetails(newEvent);
                    break;
                case 0:
                    Event.setNextEventId(Event.getNextEventId() - 1);
                    exit = true;
                    break;

                default:
                    eventUI.handleInvalidChoice();
            }
        }
    }

    public void removeEvent() {
        boolean continueRemoving = true;

        while (continueRemoving) {
            clearScreen();
            displayAllEvents();
            System.out.println("Select Event To Delete (0 to exit):");
            String eventId = eventUI.inputEventId().toUpperCase();
            if (eventId.equals("0")) {
                System.out.println("Exiting removal process.");
                continueRemoving = false;
            } else {
                Event removeEventById = eventMap.get(eventId);
                if (removeEventById != null) {
                    eventUI.displayEventDetails(removeEventById);
                    System.out.println("Are you sure you want to delete this event?");
                    int confirmation = eventUI.confirmationMessage();
                    if (confirmation == 1) {
                        eventMap.remove(eventId);
                        eventDAO.saveToFile(eventMap);
                        System.out.println("Event removed successfully.");
                        enterToContinue();
                        break;
                    } else if (confirmation == 2) {
                        System.out.println("Event removal canceled.");
                        enterToContinue();
                        break;
                    } else if (confirmation == 0) {
                        System.out.println("Exiting removal process.");
                        continueRemoving = false;
                        break;
                    }
                } else {
                    System.out.println("Event not found with ID: " + eventId);

                    int retryChoice = eventUI.confirmationEventMessage();
                    if (retryChoice == 0) {
                        System.out.println("Exiting removal process.");
                        break;
                    } else if (retryChoice == 1) {
                        removeEvent();
                    }
                }
            }
        }
    }

    public void searchEvent() {
        boolean exit = false;
        do {
            int search = eventUI.displaySearchMenu(); // Display the search menu to the user
            switch (search) {
                case 1:
                    String eventId = eventUI.inputEventId();
                    eventUI.filterHeader("Event ID: " + eventId);
                    eventUI.display(filterBy(1, eventId)); // Filter by Event ID
                    break;
                case 2:
                    String eventName = eventUI.inputEventName();
                    eventUI.filterHeader("Event Name: " + eventName);
                    eventUI.display(filterBy(2, eventName)); // Filter by Event Name
                    break;
                case 3:
                    String eventAddress = eventUI.inputEventAddress();
                    eventUI.filterHeader("Event Address: " + eventAddress);
                    eventUI.display(filterBy(3, eventAddress)); // Filter by Event Address
                    break;
                case 4:
                    String eventDescription = eventUI.inputEventDescription();
                    eventUI.filterHeader("Event Description: " + eventDescription);
                    eventUI.display(filterBy(15, eventDescription)); // Filter by Event Description
                    break;
                case 5:
                    LocalDate startDate = eventUI.inputEventStartDate();
                    eventUI.filterHeader("Start Date: " + startDate);
                    eventUI.display(filterBy(4, startDate)); // Filter by Event Start Date
                    break;
                case 6:
                    LocalTime startTime = eventUI.inputEventStartTime();
                    eventUI.filterHeader("Start Time: " + startTime);
                    eventUI.display(filterBy(6, startTime)); // Filter by Event Start Time
                    break;
                case 7:
                    String eventStatus = eventUI.inputEventStatus();
                    eventUI.filterHeader("Event Status: " + eventStatus);
                    eventUI.display(filterBy(8, eventStatus)); // Filter by Event Status
                    break;
                case 8:
                    String organizerName = eventUI.inputEventOrganizerName();
                    eventUI.filterHeader("Organizer Name: " + organizerName);
                    eventUI.display(filterBy(9, organizerName)); // Filter by Organizer Name
                    break;
                case 9:
                    String organizerEmail = eventUI.inputEventOrganizerEmail();
                    eventUI.filterHeader("Organizer Email: " + organizerEmail);
                    eventUI.display(filterBy(10, organizerEmail)); // Filter by Organizer Email
                    break;
                case 10:
                    String organizerPhoneNo = eventUI.inputEventOrganizerPhoneNo();
                    eventUI.filterHeader("Organizer Phone No: " + organizerPhoneNo);
                    eventUI.display(filterBy(11, organizerPhoneNo)); // Filter by Organizer Phone Number
                    break;
                case 11:
                    EventType eventType = eventUI.inputEventType();
                    eventUI.filterHeader("Event Type: " + eventType);
                    eventUI.display(filterBy(12, eventType)); // Filter by Event Type
                    break;
                case 12:
                    int volunteerNeed = eventUI.inputVolunteerNeedForTheEvent();
                    eventUI.filterHeader("Volunteer Need: " + volunteerNeed);
                    eventUI.display(filterBy(13, volunteerNeed)); // Filter by Volunteer Need
                    break;
                case 0:
                    exit = true; // Exit the search loop
                    break;
            }
            if (!exit) {
                enterToContinue(); // Pause for user input before continuing
            }
        } while (!exit);
    }

    public LinkedList<Event> filterBy(int criteria, Object searchValue) {
        LinkedList<Event> result = new LinkedList<>();
        Iterator keyIt = eventMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            String key = (String) keyIt.next();
            Event event = eventMap.get(key);

            switch (criteria) {
                case 1: // Event ID
                    String eventId = (String) searchValue;
                    if (event.getEventId().equalsIgnoreCase(eventId)) {
                        result.add(event);
                    }
                    break;
                case 2: // Event Name
                    String eventName = (String) searchValue;
                    if (event.getEventName().toLowerCase().contains(eventName.toLowerCase())) {
                        result.add(event);
                    }
                    break;
                case 3: // Event Address
                    String eventAddress = (String) searchValue;
                    if (event.getEventAddress().toLowerCase().contains(eventAddress.toLowerCase())) {
                        result.add(event);
                    }
                    break;
                case 4: // Event Start Date
                    LocalDate startDate = (LocalDate) searchValue;
                    if (event.getEventStartDate().equals(startDate)) {
                        result.add(event);
                    }
                    break;
                case 5: // Event End Date
                    LocalDate endDate = (LocalDate) searchValue;
                    if (event.getEventEndDate().equals(endDate)) {
                        result.add(event);
                    }
                    break;
                case 6: // Event Start Time
                    LocalTime startTime = (LocalTime) searchValue;
                    if (event.getEventStartTime().equals(startTime)) {
                        result.add(event);
                    }
                    break;
                case 7: // Event End Time
                    LocalTime endTime = (LocalTime) searchValue;
                    if (event.getEventEndTime().equals(endTime)) {
                        result.add(event);
                    }
                    break;
                case 8: // Event Status
                    String eventStatus = (String) searchValue;
                    if (event.getEventStatus().equalsIgnoreCase(eventStatus)) {
                        result.add(event);
                    }
                    break;
                case 9: // Organizer Name
                    String organizerName = (String) searchValue;
                    if (event.getEventOrganizerName().toLowerCase().contains(organizerName.toLowerCase())) {
                        result.add(event);
                    }
                    break;
                case 10: // Organizer Email
                    String organizerEmail = (String) searchValue;
                    if (event.getEventOrganizerEmail().toLowerCase().contains(organizerEmail.toLowerCase())) {
                        result.add(event);
                    }
                    break;
                case 11: // Organizer Phone No
                    String organizerPhoneNo = (String) searchValue;
                    if (event.getEventOrganizerPhoneNo().contains(organizerPhoneNo)) {
                        result.add(event);
                    }
                    break;
                case 12: // Event Type
                    EventType eventType = (EventType) searchValue;
                    if (event.getEventType().equals(eventType)) {
                        result.add(event);
                    }
                    break;
                case 13: // Volunteer Need
                    int volunteerNeed = (int) searchValue;
                    if (event.getVolunteerNeed() == volunteerNeed) {
                        result.add(event);
                    }
                    break;
                case 14: // Available Volunteer Needed
                    int availableVolunteers = (int) searchValue;
                    if (event.getAvailableVolunteerNeeded() == availableVolunteers) {
                        result.add(event);
                    }
                    break;
                case 15: // Event Description
                    String eventDescription = (String) searchValue;
                    if (event.getEventDescription().toLowerCase().contains(eventDescription.toLowerCase())) {
                        result.add(event);
                    }
                    break;
            }
        }
        return result;
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
                                eventDAO.saveToFile(eventMap);
                                clearScreen();
                                System.out.println("Event details updated successfully.");
                                detailsConfirmed = true;
                                enterToContinue();
                                break;
                            case 2:
                                System.out.println("Let's edit the details again.");
                                editEvent();
                                break;
                            case 0:
                                System.out.println("Exiting to main menu.");
                                exit = true;
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

    public void listAllEvents(HashMap<String, Event> eventMap) {
        // Check if the HashMap is available and not empty
        if (eventMap == null || eventMap.isEmpty()) {
            System.out.println("No events to display.");
            return;
        }

        // Display header for events
        displayEventHeader();
        line(305);

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

    // Add a volunteer to an event
    public void addVolunteerToEvent(String eventId, Volunteer volunteer) {
//        eventMap = eventDAO.retrieveFromFile(); // Retrieve the current state of the event data
//        volunteerMap = volunteerDAO.retrieveFromFile(); // Retrieve the current state of the volunteer data
//
//        // Check if the event exists
//        Event event = eventMap.get(eventId);
//        if (event != null) {
//            ArrayList<Volunteer> participantList = event.getParticipantList();
//
//            participantList.add(volunteer); // Add the volunteer to the event
//
//            // Update the event in the map
//            event.setParticipantList(participantList);
//            eventMap.put(eventId, event);
//
//            // Save the updated data back to files
//            ArrayList<Event> eventList = new ArrayList<>();
//            eventList.add((Event) eventMap.values()); // Replace for loop with addAll method
//            eventDAO.saveToFile(eventMap, eventList);
//
//            System.out.println("Volunteer added to event successfully.");
//        } else {
//            System.out.println("Event with ID " + eventId + " not found.");
//        }
    }

    public Event inputEventDetails() {
        String eventName = eventUI.inputEventName();
        String eventAddress = eventUI.inputEventAddress();
        LocalDate eventStartDate = eventUI.inputEventStartDate();
        LocalDate eventEndDate = eventUI.inputEventEndDate(eventStartDate);
        LocalTime eventStartTime = eventUI.inputEventStartTime();
        LocalTime eventEndTime = eventUI.inputEventEndTime(eventStartTime);
        String eventDescription = eventUI.inputEventDescription();
        String eventOrganizerName = eventUI.inputEventOrganizerName();
        String eventOrganizerEmail = eventUI.inputEventOrganizerEmail();
        String eventOrganizerPhoneNo = eventUI.inputEventOrganizerPhoneNo();
        String eventStatus = eventUI.inputEventStatus();
        EventType eventType = eventUI.inputEventType();
        int volunteerNeed = eventUI.inputVolunteerNeedForTheEvent();
        int availableVolunteerNeeded = eventUI.inputAvailableVolunteerNeeded(volunteerNeed);

        // Assuming eventID is generated elsewhere
        // For simplicity, you might want to handle eventID generation or retrieval
        return new Event(eventName, eventAddress, eventStartDate, eventEndDate, eventStartTime,
                eventEndTime, eventDescription, eventOrganizerName, eventOrganizerEmail, eventOrganizerPhoneNo,
                eventStatus, eventType, volunteerNeed, availableVolunteerNeeded);
    }
}
