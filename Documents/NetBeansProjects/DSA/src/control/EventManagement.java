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
import static utility.VolunteerCategory.HAVE_WORKING_EXPERIENCE;
import static utility.VolunteerCategory.NO_WORKING_EXPERIENCE;

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
                continue;
            }

            Event eventToRemove = eventMap.get(eventId);

            if (eventToRemove != null) {
                eventUI.displayEventDetails(eventToRemove);
                System.out.println("Are you sure you want to delete this event?");
                int confirmation = eventUI.confirmationMessage();

                switch (confirmation) {
                    case 1: // Confirm
                        eventMap.remove(eventId);
                        eventDAO.saveToFile(eventMap);
                        System.out.println("Event removed successfully.");
                        continueRemoving = false;
                        break;
                    case 2: // Cancel
                        System.out.println("Event removal canceled, back to removal selection.");
                        removeEvent();
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
                System.out.println("\nEvent not found with ID (1: Yes for Retry. 0: For Exit): " + eventId);

                int retryChoice = eventUI.confirmationEventMessage();

                if (retryChoice == 0) {
                    System.out.println("Exiting removal process.");
                    continueRemoving = false;
                }
                // No need to recursively call removeEvent(); the loop handles retry logic
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
                    EventStatus eventStatus = eventUI.chooseEventStatus();
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
                    if (event.getEventStatus().equals(eventStatus)) {
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

    public void amendEventDetails(Event event) {
        boolean doneEditing = false;
        while (!doneEditing) {
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
                    event.setEventStatus(eventUI.chooseEventStatus());
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
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
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

        line(400);
        // Print a line separator for clarity
    }

    public void removeEventFromVolunteer() {
        boolean continueRemoving = true;

        while (continueRemoving) {
            clearScreen();

            // 1. List all volunteers
            volunteerUI.listAllVolunteer(volunteerMap);
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
                    String eventId = eventUI.inputEventId().toUpperCase(); // Method to input an event ID

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
        }
    }

    public void listAllEventsForVolunteer() {
        clearScreen();

        boolean retry = true;

        while (retry) {
            displayAllEvents(); // Display all events
            System.out.println("Select Event ID to view the participant volunteers (0 to exit):");
            String eventId = eventUI.inputEventId().trim().toUpperCase(); // Method to input an event ID

            if (eventId.equals("0")) {
                System.out.println("Exiting volunteer listing process.");
                return; // Exit method
            }

            Event event = eventMap.get(eventId);

            if (event == null) {
                System.out.println("Event not found with ID: " + eventId);
                retry = eventUI.promptRetry(); // Ask the user if they want to retry
                continue;
            }

            LinkedList<Integer> participantList = event.getParticipantList(); // Get participant list from event

            if (participantList.isEmpty()) {
                System.out.println("No volunteers have been assigned to this event.");
            } else {
                System.out.println("Volunteers participating in Event ID: " + eventId);
                // Use an iterator to traverse through the LinkedList
                Iterator<Integer> iterator = participantList.iterator();
                displayVolunteerHeader();
                while (iterator.hasNext()) {

                    int volunteerId = iterator.next();
                    Volunteer volunteer = volunteerMap.get(volunteerId); // Retrieve volunteer details from the map
                    if (volunteer != null) {

                        System.out.println(volunteer); // Assuming Volunteer class has a proper toString() method

                    } else {
                        System.out.println("Volunteer ID " + volunteerId + " not found.");
                    }
                }
            }
            line(259);

            retry = eventUI.promptRetry(); // Ask the user if they want to retry for another event
        }
    }

    public void displayEventList(LinkedList<String> eventList) {
        clearScreen();

        if (eventList.isEmpty()) {
            System.out.println("\nNo events to display.");
            enterToContinue();
            return;
        }

        // Use an iterator to traverse through the LinkedList
        Iterator<String> iterator = eventList.iterator();
        LinkedList<Event> eventsToDisplay = new LinkedList<>();

        // Retrieve events for each event ID in the eventList
        while (iterator.hasNext()) {
            String eventId = iterator.next();
            Event event = eventMap.get(eventId); // Retrieve event from the event map

            if (event != null) {
                eventsToDisplay.add(event); // Add event to the list for display
            } else {
                System.out.println("\nEvent ");
            }
        }

        if (eventsToDisplay.isEmpty()) {
            System.out.println("\nNo valid events to display.");
        } else {
            displayEvents(eventsToDisplay); // Display events using an iterator
        }

        enterToContinue(); // Wait for user input to continue
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
        Iterator<Event> eventIterator = events.iterator();
        while (eventIterator.hasNext()) {
            Event event = eventIterator.next();
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
            } else if (confirmation == 0) {
                runEventManagement();
            }
        }
        return false;
    }

    private boolean checkExit(Object input) {
        if (input == null) {
            int confirmation = eventUI.getConfirmation();
            if (confirmation == 1) {
                runEventManagement();

            } else if (confirmation == 0) {
                runEventManagement();
            }
        }
        return false;
    }

    public void generateReport() {
        // 0. Report Header:
        String reportTitle = "Event Summary Report";
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        line(124);
        System.out.println("|" + centerText(reportTitle, 122) + "|");
        line(124);
        System.out.println("| Generated on : " + formattedDateTime + spacePadding(87) + "|");

        // 2. Categorize Events Data:
        LinkedList<Event> plannedEvents = new LinkedList<>();
        LinkedList<Event> completedEvents = new LinkedList<>();

        LinkedList<Event> fundraiserEvents = new LinkedList<>();
        LinkedList<Event> awarenessEvents = new LinkedList<>();
        LinkedList<Event> volunteerDriveEvents = new LinkedList<>();
        LinkedList<Event> donationDriveEvents = new LinkedList<>();

        // Initialize lists and variables for sorting and reporting
        LinkedList<Volunteer> sortedVolunteers = new LinkedList<>();
        LinkedStack<Volunteer> bottomVolunteers = new LinkedStack<>();
        CircularLinkedQueue<Volunteer> topVolunteers = new CircularLinkedQueue<>();

        // Initialize counters
        int totalEvents = 0;
        int fundraiserCount = 0;
        int awarenessCount = 0;
        int volunteerDriveCount = 0;
        int donationDriveCount = 0;

        int plannedCount = 0;
        int completedCount = 0;

        int fundraiserVolunteersNeeded = 0;
        int awarenessVolunteersNeeded = 0;
        int volunteerDriveVolunteersNeeded = 0;
        int donationDriveVolunteersNeeded = 0;

        int fundraiserVolunteersAssigned = 0;
        int awarenessVolunteersAssigned = 0;
        int volunteerDriveVolunteersAssigned = 0;
        int donationDriveVolunteersAssigned = 0;

        // Initialize volunteer statistics
        int totalVolunteers = 0;
        int menWithExperience = 0;
        int menWithoutExperience = 0;
        int womenWithExperience = 0;
        int womenWithoutExperience = 0;

        int totalEventsInvolved = 0;
        Iterator<String> eventkeyIt = eventMap.keySet().getIterator();
        Iterator<Integer> volkeyIt = volunteerMap.keySet().getIterator();

        // Process events
        while (eventkeyIt.hasNext()) {
            String key = eventkeyIt.next();
            Event event = eventMap.get(key);

            totalEvents++;

            // Count events by type
            switch (event.getEventType()) {
                case FUNDRAISER -> {
                    fundraiserCount++;
                    fundraiserVolunteersNeeded += event.getVolunteerNeed();
                    fundraiserVolunteersAssigned += event.getParticipantList().getNumberOfEntries();
                }
                case AWARENESS -> {
                    awarenessCount++;
                    awarenessVolunteersNeeded += event.getVolunteerNeed();
                    awarenessVolunteersAssigned += event.getParticipantList().getNumberOfEntries();
                }
                case VOLUNTEER_DRIVE -> {
                    volunteerDriveCount++;
                    volunteerDriveVolunteersNeeded += event.getVolunteerNeed();
                    volunteerDriveVolunteersAssigned += event.getParticipantList().getNumberOfEntries();
                }
                case DONATION_DRIVE -> {
                    donationDriveCount++;
                    donationDriveVolunteersNeeded += event.getVolunteerNeed();
                    donationDriveVolunteersAssigned += event.getParticipantList().getNumberOfEntries();
                }
            }

            // Count events by status
            switch (event.getEventStatus()) {
                case PLANNED:
                    plannedCount++;
                    plannedEvents.add(event);
                    break;
                case COMPLETED:
                    completedCount++;
                    completedEvents.add(event);
                    break;
            }
        }

        while (volkeyIt.hasNext()) {
            Integer key = volkeyIt.next();
            Volunteer volunteer = volunteerMap.get(key);
            totalEventsInvolved += volunteer.getEventList().getNumberOfEntries();
            totalVolunteers++;

            // Ensure volunteer data is valid
            if (volunteer != null && volunteer.getEventList() != null) {
                totalEventsInvolved += volunteer.getEventList().getNumberOfEntries();
                totalVolunteers++;

                // Update category counts
                if ("MALE".equals(volunteer.getGender())) {
                    if (volunteer.getCategory() == VolunteerCategory.HAVE_WORKING_EXPERIENCE) {
                        menWithExperience++;
                    } else if (volunteer.getCategory() == VolunteerCategory.NO_WORKING_EXPERIENCE) {
                        menWithoutExperience++;
                    }
                } else if ("FEMALE".equals(volunteer.getGender())) {
                    if (volunteer.getCategory() == VolunteerCategory.HAVE_WORKING_EXPERIENCE) {
                        womenWithExperience++;
                    } else if (volunteer.getCategory() == VolunteerCategory.NO_WORKING_EXPERIENCE) {
                        womenWithoutExperience++;
                    }
                }
            }
        }

        // Bubble sort volunteers by the number of events they are involved in (descending)
        int n = sortedVolunteers.getNumberOfEntries();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Volunteer v1 = sortedVolunteers.getEntry(j);
                Volunteer v2 = sortedVolunteers.getEntry(j + 1);
                if (v1 != null && v2 != null && v1.getEventList() != null && v2.getEventList() != null) {
                    if (v1.getEventList().getNumberOfEntries() < v2.getEventList().getNumberOfEntries()) {
                        // Swap v1 and v2
                        sortedVolunteers.replace(j, v2);
                        sortedVolunteers.replace(j + 1, v1);
                    }
                }
            }
        }

        // Populate top and bottom volunteer lists
        for (int i = 0; i < sortedVolunteers.getNumberOfEntries(); i++) {
            Volunteer volunteer = sortedVolunteers.getEntry(i);
            if (volunteer != null) {
                if (i < 5) {
                    topVolunteers.enqueue(volunteer);
                }
                bottomVolunteers.push(volunteer);
            }
        }
        // Calculate percentages
        double totalFundraiserEvents = (double) fundraiserCount / totalEvents * 100;
        double totalAwarenessEvents = (double) awarenessCount / totalEvents * 100;
        double totalVolunteerDriveEvents = (double) volunteerDriveCount / totalEvents * 100;
        double totalDonationDriveEvents = (double) donationDriveCount / totalEvents * 100;

        double percentageFundraiserFilled = (fundraiserVolunteersNeeded > 0) ? (double) fundraiserVolunteersAssigned / fundraiserVolunteersNeeded * 100 : 0;
        double percentageAwarenessFilled = (awarenessVolunteersNeeded > 0) ? (double) awarenessVolunteersAssigned / awarenessVolunteersNeeded * 100 : 0;
        double percentageVolunteerDriveFilled = (volunteerDriveVolunteersNeeded > 0) ? (double) volunteerDriveVolunteersAssigned / volunteerDriveVolunteersNeeded * 100 : 0;
        double percentageDonationDriveFilled = (donationDriveVolunteersNeeded > 0) ? (double) donationDriveVolunteersAssigned / donationDriveVolunteersNeeded * 100 : 0;

        double plannedPercentage = (totalEvents > 0) ? (double) plannedCount / totalEvents * 100 : 0;
        double completedPercentage = (totalEvents > 0) ? (double) completedCount / totalEvents * 100 : 0;
        // Calculate average events per volunteer
        double averageEventsPerVolunteer = (totalVolunteers > 0) ? (double) totalEventsInvolved / totalVolunteers : 0;

        // Print the report
        line(124);
        System.out.println("|" + centerText("Summary By Event Category ", 122) + "|");
        line(124);
        System.out.println("| Event Category   | Number Of Events | Percentage (%) | Total Number of Volunteers Needed | Percentage Filled | Completed |");
        line(124);
        System.out.printf("| %-17s| %-17d| %-15.2f| %-34d| %-18.2f| %-9s |\n",
                "FUNDRAISER", fundraiserCount, totalFundraiserEvents,
                fundraiserVolunteersNeeded, percentageFundraiserFilled,
                String.format("%d/%d (%.0f%%)", fundraiserVolunteersAssigned, fundraiserVolunteersNeeded, percentageFundraiserFilled));
        System.out.printf("| %-17s| %-17d| %-15.2f| %-34d| %-18.2f| %-9s |\n",
                "AWARENESS", awarenessCount, totalAwarenessEvents,
                awarenessVolunteersNeeded, percentageAwarenessFilled,
                String.format("%d/%d (%.0f%%)", awarenessVolunteersAssigned, awarenessVolunteersNeeded, percentageAwarenessFilled));
        System.out.printf("| %-17s| %-17d| %-15.2f| %-34d| %-18.2f| %-9s |\n",
                "VOLUNTEER DRIVE", volunteerDriveCount, totalVolunteerDriveEvents,
                volunteerDriveVolunteersNeeded, percentageVolunteerDriveFilled,
                String.format("%d/%d (%.0f%%)", volunteerDriveVolunteersAssigned, volunteerDriveVolunteersNeeded, percentageVolunteerDriveFilled));
        System.out.printf("| %-17s| %-17d| %-15.2f| %-34d| %-18.2f| %-9s |\n",
                "DONATION DRIVE", donationDriveCount, totalDonationDriveEvents,
                donationDriveVolunteersNeeded, percentageDonationDriveFilled,
                String.format("%d/%d (%.0f%%)", donationDriveVolunteersAssigned, donationDriveVolunteersNeeded, percentageDonationDriveFilled));
        line(124);

        System.out.println("|" + centerText("Event Status", 122) + "|");
        line(124);
        System.out.println("| Rank | Event Status   | Percentage (%) | Total Number of Events   | Total Volunteers Involved |");
        line(124);
        System.out.printf("| %-5d| %-15s| %-15.2f| %-25d| %-27d |\n", 1, "Planned", plannedPercentage, plannedCount, plannedCount * 10); // Update based on actual data
        System.out.printf("| %-5d| %-15s| %-15.2f| %-25d| %-27d |\n", 2, "Completed", completedPercentage, completedCount, completedCount * 10); // Update based on actual data
        line(124);

        System.out.println("| Total Number of Volunteers : " + totalVolunteers);
        line(124);
        System.out.println("| Volunteer Category and Gender    | Number Of Volunteers | Percentage (%) | Total Number of Events Involved | Avg. Events |");
        line(124);
        System.out.printf("| %-33s| %-21d| %-15.2f| %-32d| %-11.2f |\n",
                "Men with working experience", menWithExperience,
                (totalVolunteers > 0) ? (double) menWithExperience / totalVolunteers * 100 : 0,
                totalEventsInvolved, averageEventsPerVolunteer);
        System.out.printf("| %-33s| %-21d| %-15.2f| %-32d| %-11.2f |\n",
                "Men without working experience", menWithoutExperience,
                (totalVolunteers > 0) ? (double) menWithoutExperience / totalVolunteers * 100 : 0,
                totalEventsInvolved, averageEventsPerVolunteer);
        System.out.printf("| %-33s| %-21d| %-15.2f| %-32d| %-11.2f |\n",
                "Women with working experience", womenWithExperience,
                (totalVolunteers > 0) ? (double) womenWithExperience / totalVolunteers * 100 : 0,
                totalEventsInvolved, averageEventsPerVolunteer);
        System.out.printf("| %-33s| %-21d| %-15.2f| %-32d| %-11.2f |\n",
                "Women without working experience", womenWithoutExperience,
                (totalVolunteers > 0) ? (double) womenWithoutExperience / totalVolunteers * 100 : 0,
                totalEventsInvolved, averageEventsPerVolunteer);
        line(124);
        
        // Print Top 5 Volunteers
        System.out.println("|" + centerText("Top 5 Volunteers", 122) + "|");
        line(124);
        System.out.println("| Rank | Volunteer ID   | Name           | Total Number of Events |");
        line(124);
        int rank = 1;
        while (!topVolunteers.isEmpty() && rank <= 5) {
            Volunteer volunteer = topVolunteers.dequeue();
            if (volunteer != null) {
                System.out.printf("| %-5d| %-15d| %-17s| %-22d |\n", rank++, volunteer.getVolunteerId(), volunteer.getName(), volunteer.getEventList().getNumberOfEntries());
            }
        }
        line(124);

        // Print Bottom 5 Volunteers
        System.out.println("|" + centerText("Bottom 5 Volunteers", 122) + "|");
        line(124);
        System.out.println("| Rank | Volunteer ID   | Name           | Total Number of Events |");
        line(124);
        int counting = 1;
        while (!bottomVolunteers.isEmpty() && counting <= 5) {
            Volunteer volunteer = bottomVolunteers.pop();
            if (volunteer != null) {
                System.out.printf("| %-5d| %-15d| %-17s| %-22d |\n", counting++, volunteer.getVolunteerId(), volunteer.getName(), volunteer.getEventList().getNumberOfEntries());
            }
        }
        line(124);

        // Report Footer
        System.out.println("|" + centerText("FOR STAFF USE ONLY", 122) + "|");
        line(124);
    }

    private String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text + " ".repeat(Math.max(0, padding));
    }

    private String spacePadding(int length) {
        return " ".repeat(length);
    }

}
