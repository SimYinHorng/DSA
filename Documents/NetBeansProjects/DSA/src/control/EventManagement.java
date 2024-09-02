package control;

import adt.CircularLinkedQueue;
import adt.HashMap;
import adt.LinkedList;
import adt.LinkedStack;
import boundary.EventManagementUI;
import boundary.VolunteerManagementUI;
import dao.EventDAO;
import dao.VolunteerDAO;
import entity.Event;
import entity.Volunteer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import utility.EventStatus;
import static utility.EventStatus.COMPLETED;
import static utility.EventStatus.PLANNED;
import utility.EventType;
import static utility.EventType.AWARENESS;
import static utility.EventType.DONATION_DRIVE;
import static utility.EventType.FUNDRAISER;
import static utility.EventType.VOLUNTEER_DRIVE;
import static utility.MessageUI.displayInvalidChoiceMessage;
import static utility.MessageUI.clearScreen;
import static utility.MessageUI.displayEventHeader;
import static utility.MessageUI.displayVolunteerHeader;
import static utility.MessageUI.enterToContinue;
import static utility.MessageUI.line;
import utility.VolunteerCategory;
import utility.VolunteerGender;

/**
 *
 * @author Terence Goh Poh Xian
 */
public class EventManagement {

    private HashMap<String, Event> eventMap = new HashMap<>();
    private HashMap<Integer, Volunteer> volunteerMap = new HashMap<>();
    private final EventDAO eventDAO = new EventDAO();
    private final VolunteerDAO volunteerDAO = new VolunteerDAO();
    private final EventManagementUI eventUI = new EventManagementUI();
    private final VolunteerManagementUI volunteerUI = new VolunteerManagementUI();

    public EventManagement() {
        volunteerMap = volunteerDAO.retrieveFromFile();
        eventMap = eventDAO.retrieveFromFile();
    }

    public static void main(String[] args) {
        EventManagement eventManagement = new EventManagement();
        eventManagement.runEventManagement();
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
                    removeEventVolunteer();
                    break;
                case 7:
                    listAllEventsVolunteer();
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
        clearScreen();
        addEvent();
    }

    public void removeTheEvent() {
        clearScreen();
        removeEvent();
    }

    public void searchTheEvent() {
        clearScreen();
        enterToContinue();
        searchEvent();
    }

    public void editTheEvent() {
        clearScreen();
        enterToContinue();
        editEvent();
    }

    public void displayAllEvents() {
        clearScreen();
        listAllEvents(eventMap);
        enterToContinue();
    }

    public void removeEventVolunteer() {
        clearScreen();
        enterToContinue();
        removeEventFromVolunteer();
    }

    public void listAllEventsVolunteer() {
        clearScreen();
        listAllEventsForVolunteer();
    }

    public void generateSummaryReports() {
        generateReport();
    }

    public void addEvent() {
        // Initialize exit flag to control the loop
        boolean exit = false;

        // Gather event details from the user and create a new Event object
        Event newEvent = inputEventDetails();  // ADT: Event

        // Loop until the exit flag is set to true
        do {
            // Display the entered event details for user review
            eventUI.displayEventDetails(newEvent);  // ADT: Event

            // Prompt user to confirm if the event details are correct
            System.out.println("Are the event details correct?");

            // Get user input for confirmation
            int input = eventUI.confirmationMessage();  // ADT: User Input

            // Process user input based on the choice
            switch (input) {
                case 1: // User confirms the event details
                    // Add the new event to the event map
                    eventMap.put(newEvent.getEventId(), newEvent);  // ADT: HashMap
                    // Save the updated event map to a file
                    eventDAO.saveToFile(eventMap);  // ADT: HashMap, File
                    // Inform the user that the event has been added successfully
                    System.out.println("Event added successfully.");
                    // Set exit flag to true to end the loop
                    exit = true;
                    break;

                case 2: // User wants to amend the event details
                    // Allow the user to amend the event details
                    amendEventDetails(newEvent);  // ADT: Event
                    break;

                case 0: // User chooses to cancel the operation
                    // Decrement the next event ID to avoid reuse
                    Event.setNextEventId(Event.getNextEventId() - 1);  // ADT: Event
                    // Set exit flag to true to end the loop
                    exit = true;
                    break;

                default: // Handle invalid user input
                    // Notify the user of invalid choice
                    eventUI.handleInvalidChoice();  // ADT: User Input
            }
            // Continue looping until exit flag is set to true
        } while (!exit);
    }

    public void removeEvent() {
        // Initialize flag to control the loop for removing events
        boolean continueRemoving = true;

        // Loop until continueRemoving is set to false
        do {
            clearScreen();  // Utility method to clear the screen
            displayAllEvents();  // ADT: Display of all events (HashMap or similar)
            System.out.println("Select Event To Delete (0 to exit):");
            String eventId = eventUI.inputEventId();  // ADT: User Input

            if (eventId.equals("0")) {  // User chooses to exit
                System.out.println("Exiting removal process.");
                continueRemoving = false;  // End the loop
            } else {
                // Retrieve the event to remove from the event map
                Event eventToRemove = eventMap.get(eventId);  // ADT: HashMap

                if (eventToRemove != null) {  // Event exists in the map
                    eventUI.displayEventDetails(eventToRemove);  // ADT: Event
                    System.out.println("Are you sure you want to delete this event?");
                    int confirmation = eventUI.confirmationMessage();  // ADT: User Input

                    switch (confirmation) {
                        case 1: // Confirm deletion
                            // Remove the event from the event map
                            eventMap.remove(eventId);  // ADT: HashMap
                            // Save the updated event map to a file
                            eventDAO.saveToFile(eventMap);  // ADT: HashMap, File
                            System.out.println("Event removed successfully.");
                            continueRemoving = false;  // End the loop
                            break;

                        case 2: // Cancel deletion
                            System.out.println("Event removal canceled, back to removal selection.");
                            removeEvent();  // Recursive call to handle cancellation
                            continueRemoving = false;  // End the current loop iteration
                            break;

                        case 0: // Exit
                            System.out.println("Exiting removal process.");
                            continueRemoving = false;  // End the loop
                            break;

                        default: // Handle invalid user input
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }

                } else {  // Event not found in the map
                    System.out.println("\nEvent not found with ID (1: Yes for Retry. 0: For Exit): " + eventId);

                    int retryChoice = eventUI.confirmationEventMessage();  // ADT: User Input

                    if (retryChoice == 0) {  // User chooses to exit
                        System.out.println("Exiting removal process.");
                        continueRemoving = false;  // End the loop
                    }
                    // No need to recursively call removeEvent(); the loop handles retry logic
                }
            }
            // Continue looping until continueRemoving is set to false
        } while (continueRemoving);
    }

    public void searchEvent() {
        // Initialize exit flag to control the loop
        boolean exit = false;

        // Loop until exit flag is set to true
        do {
            // Display the search menu to the user
            int search = eventUI.displaySearchMenu(); // ADT: User Input

            // Process user choice for searching events
            switch (search) {
                case 1:
                    // Filter by Event ID
                    String eventId = eventUI.inputEventId(); // ADT: User Input
                    eventUI.filterHeader("Event ID: " + eventId);
                    eventUI.display(filterBy(1, eventId)); // ADT: Filter by Event ID
                    break;

                case 2:
                    // Filter by Event Name
                    String eventName = eventUI.inputEventName(); // ADT: User Input
                    eventUI.filterHeader("Event Name: " + eventName);
                    eventUI.display(filterBy(2, eventName)); // ADT: Filter by Event Name
                    break;

                case 3:
                    // Filter by Event Address
                    String eventAddress = eventUI.inputEventAddress(); // ADT: User Input
                    eventUI.filterHeader("Event Address: " + eventAddress);
                    eventUI.display(filterBy(3, eventAddress)); // ADT: Filter by Event Address
                    break;

                case 4:
                    // Filter by Event Description
                    String eventDescription = eventUI.inputEventDescription(); // ADT: User Input
                    eventUI.filterHeader("Event Description: " + eventDescription);
                    eventUI.display(filterBy(4, eventDescription)); // ADT: Filter by Event Description
                    break;

                case 5:
                    // Filter by Event Start Date
                    LocalDate startDate = eventUI.inputEventStartDate(); // ADT: User Input
                    eventUI.filterHeader("Start Date: " + startDate);
                    eventUI.display(filterBy(5, startDate)); // ADT: Filter by Event Start Date
                    break;

                case 6:
                    // Filter by Event Start Time
                    LocalTime startTime = eventUI.inputEventStartTime(); // ADT: User Input
                    eventUI.filterHeader("Start Time: " + startTime);
                    eventUI.display(filterBy(6, startTime)); // ADT: Filter by Event Start Time
                    break;

                case 7:
                    // Filter by Event Status
                    EventStatus eventStatus = eventUI.chooseEventStatus(); // ADT: User Input
                    eventUI.filterHeader("Event Status: " + eventStatus);
                    eventUI.display(filterBy(7, eventStatus)); // ADT: Filter by Event Status
                    break;

                case 8:
                    // Filter by Organizer Name
                    String organizerName = eventUI.inputEventOrganizerName(); // ADT: User Input
                    eventUI.filterHeader("Organizer Name: " + organizerName);
                    eventUI.display(filterBy(8, organizerName)); // ADT: Filter by Organizer Name
                    break;

                case 9:
                    // Filter by Organizer Email
                    String organizerEmail = eventUI.inputEventOrganizerEmail(); // ADT: User Input
                    eventUI.filterHeader("Organizer Email: " + organizerEmail);
                    eventUI.display(filterBy(9, organizerEmail)); // ADT: Filter by Organizer Email
                    break;

                case 10:
                    // Filter by Organizer Phone Number
                    String organizerPhoneNo = eventUI.inputEventOrganizerPhoneNo(); // ADT: User Input
                    eventUI.filterHeader("Organizer Phone No: " + organizerPhoneNo);
                    eventUI.display(filterBy(10, organizerPhoneNo)); // ADT: Filter by Organizer Phone Number
                    break;

                case 11:
                    // Filter by Event Type
                    EventType eventType = eventUI.inputEventType(); // ADT: User Input
                    eventUI.filterHeader("Event Type: " + eventType);
                    eventUI.display(filterBy(11, eventType)); // ADT: Filter by Event Type
                    break;

                case 12:
                    // Filter by Volunteer Need
                    int volunteerNeed = eventUI.inputVolunteerNeedForTheEvent(); // ADT: User Input
                    eventUI.filterHeader("Volunteer Need: " + volunteerNeed);
                    eventUI.display(filterBy(12, volunteerNeed)); // ADT: Filter by Volunteer Need
                    break;

                case 0:
                    // Exit the search loop
                    exit = true; // End the loop
                    break;
            }

            // Pause for user input before continuing
            if (!exit) {
                enterToContinue(); // Utility method to wait for user input
            }
            // Continue looping until exit flag is set to true
        } while (!exit);
    }

    public LinkedList<Event> filterBy(int criteria, Object searchValue) {
        // Initialize a LinkedList to store the filtered results
        LinkedList<Event> result = new LinkedList<>(); // ADT: LinkedList

        // Obtain an iterator over the keys of the eventMap
        Iterator keyIt = eventMap.keySet().getIterator(); // ADT: Iterator, HashMap

        // Iterate through each key in the eventMap
        while (keyIt.hasNext()) {
            // Retrieve the event associated with the current key
            String key = (String) keyIt.next(); // ADT: String (Key)
            Event event = eventMap.get(key); // ADT: HashMap, Event

            // Filter events based on the specified criteria
            switch (criteria) {
                case 1: // Filter by Event ID
                    String eventId = (String) searchValue; // ADT: String
                    if (event.getEventId().equalsIgnoreCase(eventId)) {
                        result.add(event); // ADT: LinkedList
                    }
                    break;

                case 2: // Filter by Event Name
                    String eventName = (String) searchValue; // ADT: String
                    if (event.getEventName().toLowerCase().contains(eventName.toLowerCase())) {
                        result.add(event); // ADT: LinkedList
                    }
                    break;

                case 3: // Filter by Event Address
                    String eventAddress = (String) searchValue; // ADT: String
                    if (event.getEventAddress().toLowerCase().contains(eventAddress.toLowerCase())) {
                        result.add(event); // ADT: LinkedList
                    }
                    break;

                case 4: // Filter by Event Description
                    String eventDescription = (String) searchValue; // ADT: String
                    if (event.getEventDescription().toLowerCase().contains(eventDescription.toLowerCase())) {
                        result.add(event); // ADT: LinkedList
                    }
                    break;

                case 5: // Filter by Event Start Date
                    LocalDate startDate = (LocalDate) searchValue; // ADT: LocalDate
                    if (event.getEventStartDate().equals(startDate)) {
                        result.add(event); // ADT: LinkedList
                    }
                    break;

                case 6: // Filter by Event Start Time
                    LocalTime startTime = (LocalTime) searchValue; // ADT: LocalTime
                    if (event.getEventStartTime().equals(startTime)) {
                        result.add(event); // ADT: LinkedList
                    }
                    break;

                case 7: // Filter by Event Status
                    EventStatus eventStatus = (EventStatus) searchValue; // ADT: EventStatus
                    if (event.getEventStatus().equals(eventStatus)) {
                        result.add(event); // ADT: LinkedList
                    }
                    break;

                case 8: // Filter by Organizer Name
                    String organizerName = (String) searchValue; // ADT: String
                    if (event.getEventOrganizerName().toLowerCase().contains(organizerName.toLowerCase())) {
                        result.add(event); // ADT: LinkedList
                    }
                    break;

                case 9: // Filter by Organizer Email
                    String organizerEmail = (String) searchValue; // ADT: String
                    if (event.getEventOrganizerEmail().toLowerCase().contains(organizerEmail.toLowerCase())) {
                        result.add(event); // ADT: LinkedList
                    }
                    break;

                case 10: // Filter by Organizer Phone Number
                    String organizerPhoneNo = (String) searchValue; // ADT: String
                    if (event.getEventOrganizerPhoneNo().contains(organizerPhoneNo)) {
                        result.add(event); // ADT: LinkedList
                    }
                    break;

                case 11: // Filter by Event Type
                    EventType eventType = (EventType) searchValue; // ADT: EventType
                    if (event.getEventType().equals(eventType)) {
                        result.add(event); // ADT: LinkedList
                    }
                    break;

                case 12: // Filter by Volunteer Need
                    int availableVolunteers = (int) searchValue; // ADT: int
                    if (event.getAvailableVolunteerNeeded() == availableVolunteers) {
                        result.add(event); // ADT: LinkedList
                    }
                    break;
            }
        }
        // Return the list of events that match the filter criteria
        return result; // ADT: LinkedList
    }

    public void editEvent() {
        // Initialize flag to control the loop
        boolean exit = false;

        // Loop until exit flag is set to true
        do {
            clearScreen(); // Utility method to clear the screen
            displayAllEvents(); // ADT: Display of all events (HashMap or similar)

            System.out.println("Select the event to edit (Id, 0 to exit):");
            String eventId = eventUI.inputEventId(); // ADT: User Input

            if (eventId.equals("0")) { // User chooses to exit
                exit = true; // End the loop
            } else {
                // Retrieve the event to edit from the event map
                Event eventToAmend = eventMap.get(eventId); // ADT: HashMap, Event

                if (eventToAmend != null) { // Event exists in the map
                    boolean detailsConfirmed = false;
                    while (!detailsConfirmed) {
                        // Method to allow user to amend the event details
                        amendEventDetails(eventToAmend); // ADT: Event
                        clearScreen(); // Utility method to clear the screen
                        eventUI.displayEventDetails(eventToAmend); // ADT: Display Event Details

                        System.out.println("Are the details correct?");
                        int confirm = eventUI.confirmationMessage(); // ADT: User Input

                        switch (confirm) {
                            case 1: // Confirm changes
                                // Update the event in the map
                                eventMap.put(eventToAmend.getEventId(), eventToAmend); // ADT: HashMap, Event
                                // Save the updated event map to a file
                                eventDAO.saveToFile(eventMap); // ADT: HashMap, File
                                clearScreen(); // Utility method to clear the screen
                                System.out.println("Event details updated successfully.");
                                detailsConfirmed = true; // End the loop for editing details
                                enterToContinue(); // Utility method to wait for user input
                                break;

                            case 2: // Edit details again
                                System.out.println("Let's edit the details again.");
                                editEvent(); // Recursive call to edit details
                                break;

                            case 0: // Exit to main menu
                                System.out.println("Exiting to main menu.");
                                exit = true; // End the main loop
                                break;

                            default: // Handle invalid user input
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                    }
                } else { // Event not found in the map
                    System.out.println("Event not found. Please try again.");
                }
            }
            // Continue looping until exit flag is set to true
        } while (!exit);
    }

    public void amendEventDetails(Event event) {
        boolean doneEditing = false; // Flag to control the loop

        // Loop until the editing is done
        while (!doneEditing) {
            clearScreen(); // Utility method to clear the screen
            System.out.println("Current Event Details:");
            eventUI.displayEventDetails(event); // ADT: Display Event Details

            int choice = eventUI.getEditMenu(); // ADT: User Input

            // Switch case to handle different editing options
            switch (choice) {
                case 0: // Exit editing
                    doneEditing = true;
                    break;
                case 1: // Edit Event Name
                    event.setEventName(eventUI.inputEventName()); // ADT: Event, String
                    break;
                case 2: // Edit Event Address
                    event.setEventAddress(eventUI.inputEventAddress()); // ADT: Event, String
                    break;
                case 3: // Edit Event Description
                    event.setEventDescription(eventUI.inputEventDescription()); // ADT: Event, String
                    break;
                case 4: // Edit Event Start Date
                    event.setEventStartDate(eventUI.inputEventStartDate()); // ADT: Event, LocalDate
                    break;
                case 5: // Edit Event End Date
                    LocalDate startDate = event.getEventStartDate(); // ADT: LocalDate
                    LocalDate endDate = eventUI.inputEventEndDate(startDate); // ADT: LocalDate
                    if (endDate.compareTo(startDate) >= 0) {
                        event.setEventEndDate(endDate); // ADT: Event
                    } else {
                        System.out.println("End date cannot be before start date.");
                    }
                    break;
                case 6: // Edit Event Start Time
                    event.setEventStartTime(eventUI.inputEventStartTime()); // ADT: Event, LocalTime
                    break;
                case 7: // Edit Event End Time
                    LocalTime startTime = event.getEventStartTime(); // ADT: LocalTime
                    LocalTime endTime = eventUI.inputEventEndTime(startTime); // ADT: LocalTime
                    if (endTime.compareTo(startTime) >= 0) {
                        event.setEventEndTime(endTime); // ADT: Event
                    } else {
                        System.out.println("End time cannot be before start time.");
                    }
                    break;
                case 8: // Edit Event Status
                    event.setEventStatus(eventUI.chooseEventStatus()); // ADT: Event, EventStatus
                    break;
                case 9: // Edit Event Organizer Name
                    event.setEventOrganizerName(eventUI.inputEventOrganizerName()); // ADT: Event, String
                    break;
                case 10: // Edit Event Organizer Email
                    event.setEventOrganizerEmail(eventUI.inputEventOrganizerEmail()); // ADT: Event, String
                    break;
                case 11: // Edit Event Organizer Phone Number
                    event.setEventOrganizerPhoneNo(eventUI.inputEventOrganizerPhoneNo()); // ADT: Event, String
                    break;
                case 12: // Edit Event Type
                    event.setEventType(eventUI.inputEventType()); // ADT: Event, EventType
                    break;
                case 13: // Edit Volunteer Need
                    event.setVolunteerNeed(eventUI.inputVolunteerNeedForTheEvent()); // ADT: Event, int
                    break;
                default: // Handle invalid choice
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void listAllEvents(HashMap<String, Event> eventMap) {
        // Check if the HashMap is available and not empty
        if (eventMap == null || eventMap.isEmpty()) {
            System.out.println("No events to display.");
            enterToContinue(); // Utility method to wait for user input
            runEventManagement(); // Method to return to the main menu
        }

        // Display header for events
        displayEventHeader(); // ADT: Display Header

        // Initialize a LinkedList to hold events
        LinkedList<Event> eventList = new LinkedList<>(); // ADT: LinkedList

        // Iterate over the keys of the HashMap
        Iterator<String> keyIterator = eventMap.keySet().getIterator(); // ADT: Iterator
        while (keyIterator.hasNext()) {
            String eventId = keyIterator.next(); // ADT: String (Key)
            Event event = eventMap.get(eventId); // ADT: HashMap, Event
            if (event != null) { // Check for null events
                eventList.add(event); // ADT: LinkedList
            }
        }

        // Manually sort the LinkedList by event ID using insertion sort
        int numberOfEntries = eventList.getNumberOfEntries(); // ADT: LinkedList
        for (int i = 1; i < numberOfEntries; i++) {
            Event key = eventList.getEntry(i); // ADT: LinkedList, Event
            int j = i - 1;

            // Move elements of eventList[0..i-1] that are greater than key to one position ahead
            while (j >= 0 && eventList.getEntry(j) != null
                    && eventList.getEntry(j).getEventId().compareTo(key.getEventId()) > 0) {
                eventList.replace(j + 1, eventList.getEntry(j)); // ADT: LinkedList
                j--;
            }
            eventList.replace(j + 1, key); // ADT: LinkedList
        }

        // Display sorted events
        for (int i = 1; i <= numberOfEntries; i++) {
            Event event = eventList.getEntry(i); // ADT: LinkedList, Event
            if (event != null) { // Check for null events
                System.out.println(event.toString()); // ADT: Event
            }
        }

        // Print a line separator for clarity
        line(400); // Utility method
    }

    public void removeEventFromVolunteer() {
        boolean continueRemoving = true;

        while (continueRemoving) {
            clearScreen();

            // 1. List all volunteers
            volunteerUI.listAllVolunteer(volunteerMap);
            if (volunteerMap != null) {
                System.out.println("Select Volunteer To Remove Event From (0 to exit):");

                // 2. Receive User Input
                String volunteerId = eventUI.inputVolunteerId(); // Method to input an integer ID

                // 3. Validation for exit
                if (volunteerId.equals("0")) {
                    System.out.println("Exiting removal process.");
                    continueRemoving = false;
                    continue;
                }

                try {
                    int volId = Integer.parseInt(volunteerId);
                    Volunteer volunteer = volunteerMap.get(volId);

                    if (volunteer != null) {
                        // 4. Display Volunteer Details
                        LinkedList<String> eventList = volunteer.getEventList();
                        displayEventList(eventList);
                        System.out.println("Select Event To Remove (0 to exit):");

                        // 5. Receive Event ID
                        String eventId = eventUI.inputEventId(); // Method to input an event ID

                        // 6. Validation for exit
                        if (eventId.equals("0")) {
                            System.out.println("Exiting removal process.");
                            continueRemoving = false;
                            continue;
                        }

                        // 7. Check if Event Exists in Volunteer’s List
                        if (eventList.contains(eventId)) {

                            System.out.println("Are you sure you want to remove this event from the volunteer?");
                            int confirmation = eventUI.getConfirmation(); // Method to get user confirmation

                            switch (confirmation) {
                                case 1: // Confirm    int position = eventList.findPosition(eventId);
                                    int position = eventList.findPosition(eventId);

                                    eventList.remove(position); // Remove event from volunteer's event list
                                    Event event = eventMap.get(eventId); // Retrieve event
                                    if (event != null) {
                                        LinkedList<Integer> participantList = event.getParticipantList();
                                        participantList.remove(volId); // Remove volunteer from event
                                        eventDAO.saveToFile(eventMap); // Save updated event data
                                    }
                                    volunteerDAO.saveToFile(volunteerMap); // Save updated volunteer data
                                    System.out.println("Event removed from volunteer successfully.");
                                    enterToContinue(); // Wait for user input to continue
                                    continueRemoving = false;
                                    break;
                                case 2: // Cancel
                                    System.out.println("Event removal canceled.");
                                    enterToContinue(); // Wait for user input to continue
                                    continueRemoving = false;
                                    break;
                                case 0: // Exit
                                    System.out.println("Exiting removal process.");
                                    continueRemoving = false;
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                                    break;
                            }
                        } else {
                            System.out.println("Event not found in this volunteer's list.");
                            int retryChoice = eventUI.getRetryChoice(); // Method to get user choice for retry or exit

                            if (retryChoice == 0) {
                                System.out.println("Exiting removal process.");
                                continueRemoving = false;
                            }
                        }
                    } else {
                        System.out.println("Volunteer not found with ID: " + volId);
                        int retryChoice = eventUI.getRetryChoice(); // Method to get user choice for retry or exit

                        if (retryChoice == 0) {
                            System.out.println("Exiting removal process.");
                            continueRemoving = false;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Volunteer ID format. Please enter a numeric ID.");
                }
            } else {
                System.out.println("No Volunteer exist!");
                enterToContinue();
                runEventManagement();
            }
        }
    }

    public void listAllEventsForVolunteer() {
        clearScreen(); // ADT: Utility method to clear the screen

        boolean retry = true; // Flag to control the retry loop

        while (retry) {
            displayAllEvents(); // ADT: Display all events

            System.out.println("Select Event ID to view the participant volunteers (0 to exit):");
            String eventId = eventUI.inputEventId(); // ADT: User Input, Event ID

            if (eventId.equals("0")) {
                System.out.println("Exiting volunteer listing process.");
                return; // Exit method
            }

            Event event = eventMap.get(eventId); // ADT: HashMap, Event

            if (event == null) {
                System.out.println("Event not found with ID: " + eventId);
                retry = eventUI.promptRetry(); // ADT: User Input for retry
                continue;
            }

            LinkedList<Integer> participantList = event.getParticipantList(); // ADT: LinkedList of participant IDs

            if (participantList.isEmpty()) {
                System.out.println("No volunteers have been assigned to this event.");
            } else {
                System.out.println("Volunteers participating in Event ID: " + eventId);
                // Use an iterator to traverse through the LinkedList
                Iterator<Integer> iterator = participantList.iterator(); // ADT: Iterator
                displayVolunteerHeader(); // ADT: Display Header
                while (iterator.hasNext()) {
                    int volunteerId = iterator.next(); // ADT: Integer
                    Volunteer volunteer = volunteerMap.get(volunteerId); // ADT: HashMap, Volunteer
                    if (volunteer != null) {
                        System.out.println(volunteer); // Assuming Volunteer class has a proper toString() method
                    } else {
                        System.out.println("Volunteer ID " + volunteerId + " not found.");
                    }
                }
            }
            line(259); // ADT: Utility method to print a line separator

            retry = eventUI.promptRetry(); // ADT: User Input for retry
        }
    }

    public void displayEventList(LinkedList<String> eventList) {
        clearScreen(); // ADT: Utility method to clear the screen

        if (eventList.isEmpty()) {
            System.out.println("\nNo events to display.");
            enterToContinue(); // ADT: Utility method to wait for user input
            return;
        }

        // Use an iterator to traverse through the LinkedList
        Iterator<String> iterator = eventList.iterator(); // ADT: Iterator
        LinkedList<Event> eventsToDisplay = new LinkedList<>(); // ADT: LinkedList of Event objects

        // Retrieve events for each event ID in the eventList
        while (iterator.hasNext()) {
            String eventId = iterator.next(); // ADT: String (Event ID)
            Event event = eventMap.get(eventId); // ADT: HashMap, Event
            if (event != null) {
                eventsToDisplay.add(event); // ADT: LinkedList
            } else {
                System.out.println("\nEvent " + eventId + " not found.");
            }
        }

        if (eventsToDisplay.isEmpty()) {
            System.out.println("\nNo valid events to display.");
        } else {
            displayEvents(eventsToDisplay); // ADT: Display Events using LinkedList
        }

        enterToContinue(); // ADT: Utility method to wait for user input
    }

    private void displayEvents(LinkedList<Event> events) {
        if (events.isEmpty()) {
            System.out.println("\nNo events to display.");
            return;
        }

        System.out.println("\nDisplaying Events:");
        System.out.println("================================================================================================================================");
        System.out.println(String.format("| %-10s | %-40s | %-30s | %-16s | %-16s |",
                "Event ID", "Event Name", "Organizer", "Start Date", "End Date"));
        System.out.println("================================================================================================================================");

        // Use an iterator to traverse the list of events
        Iterator<Event> eventIterator = events.iterator(); // ADT: Iterator
        while (eventIterator.hasNext()) {
            Event event = eventIterator.next(); // ADT: LinkedList, Event
            System.out.println(String.format("| %-10s | %-40s | %-30s | %-16s | %-16s |",
                    event.getEventId(),
                    event.getEventName(),
                    event.getEventOrganizerName(),
                    event.getEventStartDate().toString(),
                    event.getEventEndDate().toString()));
        }

        System.out.println("================================================================================================================================");
    }

    public Event inputEventDetails() {
        clearScreen();
        System.out.println("You can type 'EXIT' at any time for the following inputs: Date, Time, or Volunteer Need. Typing 'EXIT' will initiate the exit process.");

        String eventName = eventUI.inputEventName();
        if (checkExit(eventName)) {
            return null;
        }

        String eventAddress = eventUI.inputEventAddress();
        if (checkExit(eventAddress)) {
            return null;
        }

        LocalDate eventStartDate = eventUI.inputEventStartDate();
        if (checkExit(eventStartDate)) {
            return null;
        }

        LocalDate eventEndDate = eventUI.inputEventEndDate(eventStartDate);
        if (checkExit(eventEndDate)) {
            return null;
        }

        LocalTime eventStartTime = eventUI.inputEventStartTime();
        if (checkExit(eventStartTime)) {
            return null;
        }

        LocalTime eventEndTime = eventUI.inputEventEndTime(eventStartTime);
        if (checkExit(eventEndTime)) {
            return null;
        }

        String eventDescription = eventUI.inputEventDescription();
        if (checkExit(eventDescription)) {
            return null;
        }

        String eventOrganizerName = eventUI.inputEventOrganizerName();
        if (checkExit(eventOrganizerName)) {
            return null;
        }

        String eventOrganizerEmail = eventUI.inputEventOrganizerEmail();
        if (checkExit(eventOrganizerEmail)) {
            return null;
        }

        String eventOrganizerPhoneNo = eventUI.inputEventOrganizerPhoneNo();
        if (checkExit(eventOrganizerPhoneNo)) {
            return null;
        }

        EventStatus eventStatus = eventUI.chooseEventStatus();
        if (checkExit(eventStatus)) {
            return null;
        }

        EventType eventType = eventUI.inputEventType();
        if (checkExit(eventType)) {
            return null;
        }

        int volunteerNeed = eventUI.inputVolunteerNeedForTheEvent();
        if (volunteerNeed == -1 && checkExit("-1")) {
            return null;
        }

        int availableVolunteerNeeded = eventUI.inputAvailableVolunteerNeeded(volunteerNeed);
        if (availableVolunteerNeeded == -1 && checkExit("-1")) {
            return null;
        }

        return new Event(eventName, eventAddress, eventStartDate, eventEndDate, eventStartTime,
                eventEndTime, eventDescription, eventOrganizerName, eventOrganizerEmail, eventOrganizerPhoneNo,
                eventStatus, eventType, volunteerNeed, availableVolunteerNeeded);
    }

    private boolean checkExit(String input) {
        if (input.equalsIgnoreCase("EXIT") || input.equals("-1")) {
            int confirmation = eventUI.getConfirmation();
            if (confirmation == 1) {
                runEventManagement();
                return true;
            } else if (confirmation == 0) {
                runEventManagement();
                return true;
            } else if (confirmation == 2) {
                inputEventDetails();
                return false;
            }
        }
        return false;
    }

    private boolean checkExit(Object input) {
        if (input == null) {
            int confirmation = eventUI.getConfirmation();
            if (confirmation == 1) {
                runEventManagement();
                return true;
            } else if (confirmation == 0) {
                runEventManagement();
                return true;
            } else if (confirmation == 2) {
                inputEventDetails();
                return false;
            }
        }
        return false;
    }

    public void generateReport() {

        if (eventMap != null && volunteerMap != null) {
            // 0. Report Header:
            String reportTitle = "Event Summary Report";
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);

            // Print the report
            line(124);
            System.out.println("|" + centerText(reportTitle, 122) + "|");
            line(124);
            System.out.println("| Generated on : " + formattedDateTime + spacePadding(87) + "|");
            line(124);

            // 2. Categorize Events Data:
            // Initialize lists and variables for sorting and reporting
            // Initialize counters
            int totalEvents = 0;
            int fundraiserCount = 0;
            int awarenessCount = 0;
            int volunteerDriveCount = 0;
            int donationDriveCount = 0;

            int fundraiserVolunteersNeeded = 0;
            int awarenessVolunteersNeeded = 0;
            int volunteerDriveVolunteersNeeded = 0;
            int donationDriveVolunteersNeeded = 0;

            int fundraiserVolunteersAssigned = 0;
            int awarenessVolunteersAssigned = 0;
            int volunteerDriveVolunteersAssigned = 0;
            int donationDriveVolunteersAssigned = 0;
////////////////////////////////////////////////////////////////
            int plannedCount = 0;
            int completedCount = 0;

            int plannedVolunteersNeeded = 0;
            int comVolunteersNeeded = 0;

            int plannedVolunteersAssigned = 0;
            int comVolunteersAssigned = 0;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            Iterator<String> eventkeyIt = eventMap.keySet().getIterator();

            LinkedList<Event> plannedEvents = new LinkedList<>();
            LinkedList<Event> completedEvents = new LinkedList<>();

            // Process events
            while (eventkeyIt.hasNext()) {
                String key = eventkeyIt.next();
                Event event = eventMap.get(key);

                totalEvents++;

                // Count events by type
                switch (event.getEventType()) {
                    case FUNDRAISER:
                        fundraiserCount++;
                        fundraiserVolunteersNeeded += event.getVolunteerNeed();
                        fundraiserVolunteersAssigned += event.getParticipantList().getNumberOfEntries();
                        break;

                    case AWARENESS:
                        awarenessCount++;
                        awarenessVolunteersNeeded += event.getVolunteerNeed();
                        awarenessVolunteersAssigned += event.getParticipantList().getNumberOfEntries();
                        break;

                    case VOLUNTEER_DRIVE:
                        volunteerDriveCount++;
                        volunteerDriveVolunteersNeeded += event.getVolunteerNeed();
                        volunteerDriveVolunteersAssigned += event.getParticipantList().getNumberOfEntries();
                        break;

                    case DONATION_DRIVE:
                        donationDriveCount++;
                        donationDriveVolunteersNeeded += event.getVolunteerNeed();
                        donationDriveVolunteersAssigned += event.getParticipantList().getNumberOfEntries();
                        break;

                }

                // Count events by status
                switch (event.getEventStatus()) {
                    case PLANNED:
                        plannedCount++;
                        plannedEvents.add(event);
                        plannedVolunteersNeeded += event.getVolunteerNeed();
                        plannedVolunteersAssigned += event.getParticipantList().getNumberOfEntries();

                        break;
                    case COMPLETED:
                        completedCount++;
                        completedEvents.add(event);
                        comVolunteersNeeded += event.getVolunteerNeed();
                        comVolunteersAssigned += event.getParticipantList().getNumberOfEntries();

                        break;
                }
            }
            //////////////////////////////////////////////////////////////////////
            double totalFundraiserEvents = (double) fundraiserCount / totalEvents * 100;
            double totalAwarenessEvents = (double) awarenessCount / totalEvents * 100;
            double totalVolunteerDriveEvents = (double) volunteerDriveCount / totalEvents * 100;
            double totalDonationDriveEvents = (double) donationDriveCount / totalEvents * 100;

            double percentageFundraiserFilled = (fundraiserVolunteersNeeded > 0) ? (double) fundraiserVolunteersAssigned / fundraiserVolunteersNeeded * 100 : 0;
            double percentageAwarenessFilled = (awarenessVolunteersNeeded > 0) ? (double) awarenessVolunteersAssigned / awarenessVolunteersNeeded * 100 : 0;
            double percentageVolunteerDriveFilled = (volunteerDriveVolunteersNeeded > 0) ? (double) volunteerDriveVolunteersAssigned / volunteerDriveVolunteersNeeded * 100 : 0;
            double percentageDonationDriveFilled = (donationDriveVolunteersNeeded > 0) ? (double) donationDriveVolunteersAssigned / donationDriveVolunteersNeeded * 100 : 0;

            System.out.println("|" + centerText("Summary By Event Category ", 122) + "|");
            line(124);
            System.out.println("| Event Category  | Number Of Events | Percentage (%) | Total Number of Volunteers Needed | Percentage Filled | Completed  |");
            line(124);

            System.out.printf("| %-16s| %-17d| %-15.2f| %-34d| %-18.2f| %-10s |\n",
                    "FUNDRAISER", fundraiserCount, totalFundraiserEvents,
                    fundraiserVolunteersNeeded, percentageFundraiserFilled,
                    String.format("%d/%d (%.0f%%)", fundraiserVolunteersAssigned, fundraiserVolunteersNeeded, percentageFundraiserFilled));

            System.out.printf("| %-16s| %-17d| %-15.2f| %-34d| %-18.2f| %-10s |\n",
                    "AWARENESS", awarenessCount, totalAwarenessEvents,
                    awarenessVolunteersNeeded, percentageAwarenessFilled,
                    String.format("%d/%d (%.0f%%)", awarenessVolunteersAssigned, awarenessVolunteersNeeded, percentageAwarenessFilled));

            System.out.printf("| %-16s| %-17d| %-15.2f| %-34d| %-18.2f| %-10s |\n",
                    "VOLUNTEER DRIVE", volunteerDriveCount, totalVolunteerDriveEvents,
                    volunteerDriveVolunteersNeeded, percentageVolunteerDriveFilled,
                    String.format("%d/%d (%.0f%%)", volunteerDriveVolunteersAssigned, volunteerDriveVolunteersNeeded, percentageVolunteerDriveFilled));

            System.out.printf("| %-16s| %-17d| %-15.2f| %-34d| %-18.2f| %-10s |\n",
                    "DONATION DRIVE", donationDriveCount, totalDonationDriveEvents,
                    donationDriveVolunteersNeeded, percentageDonationDriveFilled,
                    String.format("%d/%d (%.0f%%)", donationDriveVolunteersAssigned, donationDriveVolunteersNeeded, percentageDonationDriveFilled));
            line(124);

/////////////////////////////////////////////////////////////////////////
            double plannedPercentage = (totalEvents > 0) ? (double) plannedCount / totalEvents * 100 : 0;
            double completedPercentage = (totalEvents > 0) ? (double) completedCount / totalEvents * 100 : 0;

            double percentageComVolInvolve = (comVolunteersNeeded > 0) ? (double) comVolunteersAssigned / comVolunteersNeeded * 100 : 0;
            double percentagePlanVolInvolve = (plannedVolunteersNeeded > 0) ? (double) plannedVolunteersAssigned / plannedVolunteersNeeded * 100 : 0;

            System.out.println("|" + centerText("Event Status", 122) + "|");
            line(124);
            System.out.println("| Event Status | Number Of Events | Percentage (%) | Total Number of Volunteers Needed | Percentage Filled | Completed     |");
            line(124);
            System.out.printf("| %-13s| %-17d| %-15.2f| %-34d| %-18.2f| %-13s |\n",
                    "Planned", plannedEvents.getNumberOfEntries(), plannedPercentage,
                    plannedVolunteersNeeded, percentagePlanVolInvolve,
                    String.format("%d/%d (%.0f%%)", plannedVolunteersAssigned, plannedVolunteersNeeded, percentagePlanVolInvolve)); // Update based on actual data

            System.out.printf("| %-13s| %-17d| %-15.2f| %-34d| %-18.2f| %-13s |\n",
                    "Completed", completedEvents.getNumberOfEntries(), completedPercentage,
                    comVolunteersNeeded, percentageComVolInvolve,
                    String.format("%d/%d (%.0f%%)", comVolunteersAssigned, comVolunteersNeeded, percentageComVolInvolve)); // Update based on actual data
            line(124);

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            LinkedList<Volunteer> maleVolunteerHaveWorkingExp = new LinkedList<>();
            LinkedList<Volunteer> maleVolunteerNoWorkingExp = new LinkedList<>();
            LinkedList<Volunteer> femaleVolunteerHaveWorkingExp = new LinkedList<>();
            LinkedList<Volunteer> femaleVolunteerNoWorkingExp = new LinkedList<>();

            int totalVolunteers = 0;

            int menWithExperience = 0;
            int menWithoutExperience = 0;
            int womenWithExperience = 0;
            int womenWithoutExperience = 0;

            int totalEventMenExp = 0;
            int totalEventMenNoExp = 0;
            int totalEventWomenExp = 0;
            int totalEventWomenNoExp = 0;

            int totalEventsInvolved = 0;

            Iterator<Integer> volkeyIt = volunteerMap.keySet().getIterator();

            while (volkeyIt.hasNext()) {
                Integer key = volkeyIt.next();
                Volunteer volunteer = volunteerMap.get(key);
                totalVolunteers++;

                // Ensure volunteer data is valid
                if (volunteer != null && volunteer.getEventList() != null) {
                    totalEventsInvolved += volunteer.getEventList().getNumberOfEntries();

                    // Update category counts
                    if (volunteer.getGender() == VolunteerGender.MALE) {
                        if (volunteer.getCategory() == VolunteerCategory.HAVE_WORKING_EXPERIENCE) {
                            maleVolunteerHaveWorkingExp.add(volunteer);
                            totalEventMenExp += volunteer.getEventList().getNumberOfEntries();

                            menWithExperience++;
                        } else if (volunteer.getCategory() == VolunteerCategory.NO_WORKING_EXPERIENCE) {
                            maleVolunteerNoWorkingExp.add(volunteer);
                            totalEventMenNoExp += volunteer.getEventList().getNumberOfEntries();

                            menWithoutExperience++;
                        }

                    } else if (volunteer.getGender() == VolunteerGender.FEMALE) {
                        if (volunteer.getCategory() == VolunteerCategory.HAVE_WORKING_EXPERIENCE) {
                            femaleVolunteerHaveWorkingExp.add(volunteer);
                            totalEventWomenExp += volunteer.getEventList().getNumberOfEntries();

                            womenWithExperience++;
                        } else if (volunteer.getCategory() == VolunteerCategory.NO_WORKING_EXPERIENCE) {
                            femaleVolunteerNoWorkingExp.add(volunteer);
                            totalEventWomenNoExp += volunteer.getEventList().getNumberOfEntries();

                            womenWithoutExperience++;
                        }
                    }
                }
            }

            // Calculate average events per volunteer
            double maleWithExp = (totalVolunteers > 0) ? (double) menWithExperience / totalVolunteers * 100 : 0;
            double maleWithNoExp = (totalVolunteers > 0) ? (double) menWithoutExperience / totalVolunteers * 100 : 0;
            double femaleWithExp = (totalVolunteers > 0) ? (double) womenWithExperience / totalVolunteers * 100 : 0;
            double femaleWithNoExp = (totalVolunteers > 0) ? (double) womenWithoutExperience / totalVolunteers * 100 : 0;

            double eMaleWithExp = (totalEventsInvolved > 0) ? (double) totalEventMenExp / totalEventsInvolved * 100 : 0;
            double eMaleWithNoExp = (totalEventsInvolved > 0) ? (double) totalEventMenNoExp / totalEventsInvolved * 100 : 0;
            double eFemaleWithExp = (totalEventsInvolved > 0) ? (double) totalEventWomenExp / totalEventsInvolved * 100 : 0;
            double eFemaleWithNoExp = (totalEventsInvolved > 0) ? (double) totalEventWomenNoExp / totalEventsInvolved * 100 : 0;

            System.out.println("| Total Number of Volunteers : " + totalVolunteers + " \tTotal Events Involved : " + totalEventsInvolved + "\t\t\t\t\t\t\t   |");
            line(124);
            System.out.println("| Volunteer Category and Gender    | Number Of Volunteers | Percentage (%) | Total Number of Events Involved | % of Events |");
            line(124);
            System.out.printf("| %-33s| %-21d| %-15.2f| %-32d| %-11.2f |\n",
                    "Men with working experience",
                    maleVolunteerHaveWorkingExp.getNumberOfEntries(), maleWithExp, totalEventMenExp, eMaleWithExp);

            System.out.printf("| %-33s| %-21d| %-15.2f| %-32d| %-11.2f |\n",
                    "Men without working experience",
                    maleVolunteerNoWorkingExp.getNumberOfEntries(), maleWithNoExp, totalEventMenNoExp, eMaleWithNoExp);

            System.out.printf("| %-33s| %-21d| %-15.2f| %-32d| %-11.2f |\n",
                    "Women with working experience",
                    femaleVolunteerHaveWorkingExp.getNumberOfEntries(), femaleWithExp, totalEventWomenExp, eFemaleWithExp);

            System.out.printf("| %-33s| %-21d| %-15.2f| %-32d| %-11.2f |\n",
                    "Women without working experience",
                    femaleVolunteerNoWorkingExp.getNumberOfEntries(), femaleWithNoExp, totalEventWomenNoExp, eFemaleWithNoExp);
            line(124);
////////////////////////////////////////////////////////////////////////////////////////////////

// Bubble sort volunteers by the number of events they are involved in (descending)
            volkeyIt = volunteerMap.keySet().getIterator();
            LinkedList<Volunteer> sortedVolunteers = new LinkedList<>();
            LinkedStack<Volunteer> bottomVolunteers = new LinkedStack<>();
            CircularLinkedQueue<Volunteer> topVolunteers = new CircularLinkedQueue<>();

            while (volkeyIt.hasNext()) {
                Integer key = volkeyIt.next();
                Volunteer volunteer = volunteerMap.get(key);
                sortedVolunteers.add(volunteer);
            }

            int n = sortedVolunteers.getNumberOfEntries();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    Volunteer v1 = sortedVolunteers.getEntry(j + 1);
                    Volunteer v2 = sortedVolunteers.getEntry(j + 2);
                    if (v1 != null && v2 != null && v1.getEventList() != null && v2.getEventList() != null) {
                        if (v1.getEventList().getNumberOfEntries() < v2.getEventList().getNumberOfEntries()) {
                            // Swap v1 and v2
                            sortedVolunteers.replace(j + 1, v2);
                            sortedVolunteers.replace(j + 2, v1);
                        }
                    }
                }
            }

            // Print Top 5 Volunteers
            System.out.println("|" + centerText("Top 5 Volunteers", 122) + "|");
            line(124);
            System.out.println("| Rank | Volunteer ID | Name             | Gender    | Phone No.   | Email                    | Count Of Events | Rate     |");

            line(124);

            for (int i = 1; i < sortedVolunteers.getNumberOfEntries() + 1; i++) {
                Volunteer volunteer = sortedVolunteers.getEntry(i);
                if (volunteer != null) {
                    if (i < 6) {
                        topVolunteers.enqueue(volunteer);
                    }
                    bottomVolunteers.push(volunteer);
                }
            }
            int rank = 1;

            int countTop = 1;
            while (!topVolunteers.isEmpty() && countTop <= 5) {
                Volunteer volunteer = topVolunteers.dequeue();

                if (volunteer != null) {
                    int totalEventCount = volunteer.getEventList().getNumberOfEntries();
                    double volunteerParticipantRate = (totalEvents > 0) ? (double) totalEventCount / totalEvents : 0;
                    System.out.printf("| %-5d| %-13d| %-17s| %-10s| %-12s| %-25s| %-15d | %-8.2f |\n",
                            countTop++, volunteer.getVolunteerId(), volunteer.getName(), volunteer.getGender(),
                            volunteer.getPhoneNo(), volunteer.getEmail(), totalEventCount, volunteerParticipantRate);
                }
            }

            // Print Bottom 5 Volunteers
            line(124);
            System.out.println(
                    "|" + centerText("Bottom 5 Volunteers", 122) + " |");
            line(124);
            System.out.println("| Rank | Volunteer ID | Name             | Gender    | Phone No.   | Email                    | Count Of Events | Rate     |");
            line(124);

            int counting = 1;
            while (!bottomVolunteers.isEmpty() && counting <= 5) {
                Volunteer volunteer = bottomVolunteers.pop();

                if (volunteer != null) {
                    int totalEventCount = volunteer.getEventList().getNumberOfEntries();
                    double volunteerParticipantRate = (totalEvents > 0) ? (double) totalEventCount / totalEvents : 0;
                    System.out.printf("| %-5d| %-13d| %-17s| %-10s| %-12s| %-25s| %-15d | %-8.2f |\n",
                            counting++, volunteer.getVolunteerId(), volunteer.getName(), volunteer.getGender(),
                            volunteer.getPhoneNo(), volunteer.getEmail(), totalEventCount, volunteerParticipantRate);
                }
            }

            line(124);
            // Report Footer
            System.out.println("|" + centerText("This report is for staff use only. Please handle the information with care.", 122) + " |");
            line(124);
            enterToContinue();
        } else {
            System.out.println("No Volunteer or No Event exist!");
            enterToContinue();
            runEventManagement();

        }
    }

    private String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text + " ".repeat(Math.max(0, padding));
    }

    private String spacePadding(int length) {
        return " ".repeat(length);
    }

}