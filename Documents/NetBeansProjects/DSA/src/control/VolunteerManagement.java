/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.HashMap;
import adt.LinkedList;
import boundary.EventManagementUI;
import boundary.VolunteerManagementUI;
import dao.EventDAO;
import dao.VolunteerDAO;
import entity.Event;
import entity.Volunteer;
import java.util.Iterator;
import static utility.MessageUI.displayInvalidChoiceMessage;
import utility.VolunteerCategory;
import utility.VolunteerGender;
import java.util.Scanner;
import static utility.MessageUI.clearScreen;
import static utility.MessageUI.displayVolunteerHeader;
import static utility.MessageUI.enterToContinue;
import static utility.MessageUI.line;

/**
 *
 * @author Goh Wei Xian
 */
public class VolunteerManagement {

    Scanner scanner = new Scanner(System.in);
    private HashMap<String, Event> eventMap = new HashMap<>();
    private HashMap<Integer, Volunteer> volunteerMap = new HashMap<>();
    private EventDAO eventDAO = new EventDAO();
    private VolunteerDAO volunteerDAO = new VolunteerDAO();
    private EventManagementUI eventUI = new EventManagementUI();
    private EventManagement event = new EventManagement();
    private VolunteerManagementUI volunteerUI = new VolunteerManagementUI();

    public VolunteerManagement() {
        volunteerMap = volunteerDAO.retrieveFromFile();
        eventMap = eventDAO.retrieveFromFile();
    }

    public void runVolunteerManagement() {
        int choice = 0;
        do {
            choice = volunteerUI.getMenuChoice();
            switch (choice) {
                case 0:
                    volunteerUI.displayExitMessage();
                    break;
                case 1:
                    addNewVolunteer();
                    break;
                case 2:
                    removeVolunteer();
                    break;
                case 3:
                    searchVolunteer();
                    break;
                case 4:
                    assignVolunteerToEvent();
                    break;
                case 5:
                    searchEventUnderVolunteer();
                    break;
                case 6:
                    listVolunteer();
                    break;
                case 7:
                    filterVolunteer();
                    break;
                case 8:
                    generateReport();
                    break;
                default:
                    displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    public void addNewVolunteer() {
        boolean exit = false;
        Volunteer newVolunteer = volunteerUI.enterVolunteerDetails();
        do {
            volunteerUI.displayVolunteerDetails(newVolunteer);
            System.out.println("Are the volunteer details correct?");
            int input = volunteerUI.confirmationMessage();
            switch (input) {
                case 1:
                    volunteerMap.put(newVolunteer.getVolunteerId(), newVolunteer);
                    volunteerDAO.saveToFile(volunteerMap);
                    System.out.println("\n==============================");
                    System.out.println("Volunteer added successfully!!");
                    System.out.println("==============================");
                    exit = true;
                    break;
                case 2:
                    int choice = volunteerUI.getEditMenu();
                    switch (choice) {
                        case 1:
                            String name = volunteerUI.enterVolunteerName();
                            newVolunteer.setName(name);
                            break;
                        case 2:
                            String email = volunteerUI.enterVolunteerEmail();
                            newVolunteer.setEmail(email);
                            break;
                        case 3:
                            String phoneNo = volunteerUI.enterVolunteerPhoneNo();
                            newVolunteer.setPhoneNo(phoneNo);
                            break;
                        case 4:
                            String address = volunteerUI.enterVolunteerAddress();
                            newVolunteer.setAddress(address);
                            break;
                        case 5:
                            String dateOfBirth = volunteerUI.enterVolunteerDateOfBirth();
                            newVolunteer.setDateOfBirth(dateOfBirth);
                            break;
                        case 6:
                            VolunteerGender gender = volunteerUI.enterVolunteerGender();
                            newVolunteer.setGender(gender);
                            break;
                        case 7:
                            VolunteerCategory cat = volunteerUI.enterVolunteerCategory();
                            newVolunteer.setCategory(cat);
                            break;

                    }
                    break;
                case 0:
                    exit = true;
                    break;
            }

        } while (!exit);
    }

    public void removeVolunteer() {
        boolean continueRemoving = true;

        while (continueRemoving) {
            volunteerUI.listAllVolunteer(volunteerMap);
            int volunteerId;

            while (true) {
                System.out.print("Enter Volunteer ID to remove: ");
                volunteerId = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (volunteerMap.containsKey(volunteerId)) {
                    // Prompt user and read input on the same line
                    System.out.print("Are you sure you want to remove the volunteer with ID: " + volunteerId + "? (Y/N): ");
                    String confirmation = scanner.nextLine().trim().toLowerCase();

                    if (confirmation.equals("y")) {
                        volunteerMap.remove(volunteerId);
                        volunteerDAO.saveToFile(volunteerMap);
                        System.out.println("\n===============================");
                        System.out.println("Volunteer removed successfully.");
                        System.out.println("===============================");
                        break; // Exit inner loop after successful removal
                    } else {
                        System.out.println("Operation cancelled. No volunteer was removed.\n");
                        break; // Exit inner loop if operation is cancelled
                    }
                } else {
                    System.out.println("No volunteer found with the given ID. Please try again.\n");
                }
            }

            // Ask if the user wants to remove another volunteer
            System.out.print("Do you want to remove another volunteer? (Y/N): ");
            String removeMore = scanner.nextLine().trim().toLowerCase();
            continueRemoving = removeMore.equals("y");
        }
    }

    public void searchVolunteer() {
        boolean exit = false;
        do {
            int choice = volunteerUI.getSearchMenu();
            switch (choice) {
                case 1:
                    int id = volunteerUI.enterVolunteerId();

                    if (volunteerMap.containsKey(id)) {
                        String idToString = String.valueOf(id);
                        volunteerUI.filterHeader(idToString);
                        volunteerUI.displayOutput(filterBy(choice, idToString));
                    } else {
                        line(239);
                        System.out.printf("|Search Result Of : %-239s|\n", id);
                        displayVolunteerHeader();
                        System.out.printf("| %-202s|\n", "No Record Found");
                        line(239);
                    }

                    break;
                case 2:
                    String name = volunteerUI.enterVolunteerName();
                    volunteerUI.filterHeader(name);
                    volunteerUI.displayOutput(filterBy(choice, name));
                    break;
                case 3:
                    String email = volunteerUI.enterVolunteerEmail();
                    volunteerUI.filterHeader(email);
                    volunteerUI.displayOutput(filterBy(choice, email));
                    break;
                case 4:
                    String phoneNo = volunteerUI.enterVolunteerPhoneNo();
                    volunteerUI.filterHeader(phoneNo);
                    volunteerUI.displayOutput(filterBy(choice, phoneNo));
                    break;
                case 5:
                    String address = volunteerUI.enterVolunteerName();
                    volunteerUI.filterHeader(address);
                    volunteerUI.displayOutput(filterBy(choice, address));
                    break;
                case 6:
                    VolunteerGender gender = volunteerUI.enterVolunteerGender();
                    volunteerUI.filterHeader(gender.toString());
                    volunteerUI.displayOutput(filterBy(choice, gender));
                    break;
                case 7:
                    VolunteerCategory category = volunteerUI.enterVolunteerCategory();
                    volunteerUI.filterHeader(category.toString());
                    volunteerUI.displayOutput(filterBy(choice, category));
                    break;
                case 0:
                    exit = true;
                    break;
            }
            if (!exit) {
                enterToContinue();
            }
        } while (!exit);
    }

    public LinkedList<Volunteer> filterBy(int criteria, Object searchValue) {
        LinkedList<Volunteer> result = new LinkedList<>();
        Iterator keyIt = volunteerMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Integer key = (Integer) keyIt.next();
            Volunteer volunteer = volunteerMap.get(key);
            switch (criteria) {
                case 1:
                    int id = Integer.parseInt(searchValue.toString());
                    if (volunteer.getVolunteerId() == id) {
                        result.add(volunteer);
                    }
                    break;
                case 2:
                    String name = (String) searchValue;
                    if (volunteer.getName().toLowerCase().contains(name.toLowerCase())) {
                        result.add(volunteer);
                    }
                    break;
                case 3:
                    String email = (String) searchValue;
                    if (volunteer.getEmail().toLowerCase().contains(email.toLowerCase())) {
                        result.add(volunteer);
                    }
                case 4:
                    String phoneNo = (String) searchValue;
                    if (volunteer.getPhoneNo().contains(phoneNo)) {
                        result.add(volunteer);
                    }
                    break;
                case 5:
                    String address = (String) searchValue;
                    if (volunteer.getAddress().toLowerCase().contains(address.toLowerCase())) {
                        result.add(volunteer);
                    }
                    break;
                case 6:
                    VolunteerGender gender = (VolunteerGender) searchValue;
                    if (volunteer.getGender().equals(gender)) {
                        result.add(volunteer);
                    }
                    break;
                case 7:
                    VolunteerCategory category = (VolunteerCategory) searchValue;
                    if (volunteer.getCategory().equals(category)) {
                        result.add(volunteer);
                    }
                    break;
            }

        }
        return result;
    }

    public void assignVolunteerToEvent() {
        clearScreen();

        // 1. Display Events
        event.displayAllEvents(); // Display all events

        // 2. Input Event ID
        System.out.println("Select Event ID to assign volunteers (0 to exit):");
        String eventId = eventUI.inputEventId().toUpperCase(); // Input an event ID

        // 3. Header Event
        eventUI.filterHeader("Event ID: " + eventId);
        eventUI.display(event.filterBy(1, eventId)); // Filter by Event ID

        // 4. Validation if 0 then Exit
        if (eventId.equals("0")) {
            System.out.println("Exiting volunteer assignment process.");
            return; // Exit method
        }

        // 5. Retrieve Event and Validate
        Event event = eventMap.get(eventId);
        if (event == null) {
            System.out.println("Event not found with ID: " + eventId);
            return;
        }

        // 6. Get the volunteer Need Value
        int neededVolunteers = event.getVolunteerNeed();

        // 7. Get the linked list from event to store a group of Volunteer ID
        LinkedList<Integer> participantList = event.getParticipantList();

        clearScreen();

        // 8. Validation for make sure the volunteer still needed
        while (neededVolunteers > 0) {

            // 9. Display Volunteer
            listVolunteer(); // Display all volunteers

            // 10. Get Input Volunteer ID
            System.out.println("Select Volunteer ID to assign to event (0 to exit):");
            String id = eventUI.inputVolunteerId();

            // 11. Validation to Exit
            if (id.equals("0")) {
                System.out.println("Exiting volunteer assignment process.");
                return; // Exit method
            }

            // 12. To make the id become integer 
            try {
                int volId = Integer.parseInt(id);
                Volunteer volunteer = volunteerMap.get(volId);

                if (volunteer == null) {
                    System.out.println("Volunteer not found with ID: " + volId);
                    System.out.println("Available IDs in map: " + volunteerMap.keySet());
                } else if (participantList.contains(volId)) {
                    System.out.println("Volunteer already assigned to this event.");
                } else {
                    // 13. Assign Volunteer to Event
                    participantList.add(volId);
                    LinkedList<String> eventList = volunteer.getEventList();

                    eventList.add(eventId);
                    neededVolunteers--;
                    event.setVolunteerNeed(neededVolunteers);
                    eventDAO.saveToFile(eventMap); // Save event data
                    volunteerDAO.saveToFile(volunteerMap); // Save volunteer data

                    System.out.println("Volunteer assigned successfully. Volunteers needed: " + neededVolunteers);
                }

                if (neededVolunteers == 0) {
                    System.out.println("All required volunteers have been assigned.");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Volunteer ID format. Please enter a numeric ID.");
            }
        }

        enterToContinue(); // Wait for user input to continue
    }

    public void searchEventUnderVolunteer() {
        boolean continueSearch = true;

        while (continueSearch) {
            clearScreen();

            // 1. List all volunteers
            volunteerUI.listAllVolunteer(volunteerMap);
            System.out.println("Select Volunteer To Search (0 to exit):");

            // 2. Receive User Input
            String volunteerId = eventUI.inputVolunteerId(); // Method to input an integer ID
            volunteerUI.filterHeader(volunteerId);

            // 3. Validation for exit
            if (volunteerId.equals("0")) {
                System.out.println("Exiting search process.");
                continueSearch = false;
                continue;
            }

            try {
                int volId = Integer.parseInt(volunteerId);
                Volunteer volunteer = volunteerMap.get(volId);

                if (volunteer != null) {
                    // 4. Display Volunteer Details
                    LinkedList<String> eventList = volunteer.getEventList();

                    event.displayEventList(eventList);  
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Volunteer ID format. Please enter a numeric ID.");
            }
        }
    }

    public void listVolunteer() {
        volunteerUI.listAllVolunteer(volunteerMap);
        enterToContinue();
    }

    public void filterVolunteer() {
        boolean exit = false;
        do {
            LinkedList<Volunteer> displayList = new LinkedList<>();
            int slc = volunteerUI.eventVounteerFilterMenu();
            switch (slc) {
                case 1:
                    int above = volunteerUI.inputQty();
                    volunteerUI.filterHeader("Volunteer Consists Of Event Above " + above);
                    displayList = filterByEvent("ABOVE", above);
                    volunteerUI.display(filterByEvent("ABOVE", above));
                    break;
                case 2:
                    System.out.println("Enter First Number");
                    int first = volunteerUI.inputQty();
                    System.out.println("Enter Second Number");
                    int second = volunteerUI.inputQty();
                    volunteerUI.display(filterBetween(first, second));
                    break;
                case 3:
                    int below = volunteerUI.inputQty();
                    volunteerUI.filterHeader("Volunteer Consists Of Event Below " + below);
                    displayList = filterByEvent("BELOW", below);
                    volunteerUI.display(filterByEvent("BELOW", below));
                    break;
                case 4:
                    int equal = volunteerUI.inputQty();
                    volunteerUI.filterHeader("Volunteer Consists Of Event Equal To " + equal);
                    displayList = filterByEvent("EQUAL", equal);
                    volunteerUI.display(filterByEvent("EQUAL", equal));
                    break;
                case 0:
                    exit = true;
                    break;
            }
            if(!exit){
                enterToContinue();
            }
        } while (!exit);
    }
    
    public LinkedList<Volunteer> filterByEvent(String condition, int qty) {
        LinkedList<Volunteer> result = new LinkedList<>();
        Iterator keyIt = volunteerMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Integer key = (Integer) keyIt.next();
            Volunteer volunteer = volunteerMap.get(key);
            switch (condition) {
                case "ABOVE":
                    if (volunteer.getEventList().getNumberOfEntries() >= qty) {
                        result.add(volunteer);
                        
                    }
                    break;
                case "BELOW":
                    if (volunteer.getEventList().getNumberOfEntries() <= qty) {
                        result.add(volunteer);
                    }
                    break;
                case "EQUAL":
                    if (volunteer.getEventList().getNumberOfEntries() == qty) {
                        result.add(volunteer);
                    }
                    break;
            }

        }
        return result;
    }

    public LinkedList<Volunteer> filterBetween(int firstNum, int secondNum) {
        LinkedList<Volunteer> result = new LinkedList<>();
        Iterator keyIt = volunteerMap.keySet().getIterator();

        int largerNum;
        int smallerNum;

        if (firstNum > secondNum) {
            largerNum = firstNum;
            smallerNum = secondNum;
        } else {
            largerNum = secondNum;
            smallerNum = firstNum;
        }

        while (keyIt.hasNext()) {
            Integer key = (Integer) keyIt.next();
            Volunteer volunteer = volunteerMap.get(key);

            if (volunteer.getEventList().getNumberOfEntries() < largerNum && volunteer.getEventList().getNumberOfEntries() > smallerNum) {
                result.add(volunteer);
            }
        }
        return result;
    }
    
    public void generateReport() {
        LinkedList<Volunteer> maleVolunteer = new LinkedList<>();
        LinkedList<Volunteer> femaleVolunteer = new LinkedList<>();
        LinkedList<Volunteer> haveWorkingExp = new LinkedList<>();
        LinkedList<Volunteer> noWorkingExp = new LinkedList<>();
        int totalVolunteers = volunteerMap.size();
        int countMale = 0, countFemale = 0;
        int countHaveExperience = 0, countNoExperience = 0;
        int ageUnder16 = 0, age17to30 = 0, age31to45 = 0, ageAbove45 = 0;


        Iterator<Integer> iterator = volunteerMap.keySet().getIterator();
        
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            Volunteer volunteer = volunteerMap.get(key);
            //
            if (volunteer.getGender() == VolunteerGender.MALE) {
                maleVolunteer.add(volunteer);
                countMale++;
            }
            
            else if (volunteer.getGender() == VolunteerGender.FEMALE) {
                femaleVolunteer.add(volunteer);
                countFemale++;
            }
            
            //
            if (volunteer.getCategory() == VolunteerCategory.HAVE_WORKING_EXPERIENCE) {
                haveWorkingExp.add(volunteer);
                countHaveExperience++;
            } 
            else if (volunteer.getCategory() == VolunteerCategory.NO_WORKING_EXPERIENCE) {
                noWorkingExp.add(volunteer);
                countNoExperience++;
            }

            int age = calculateAge(volunteer.getDateOfBirth());
            if (age <= 16) {
                ageUnder16++;
            } else if (age >= 17 && age <= 30) {
                age17to30++;
            } else if (age >= 31 && age <= 45) {
                age31to45++;
            } else {
                ageAbove45++;
            }
        }
            
        

        // Calculate percentages
        double malePercentage = (totalVolunteers > 0) ? (countMale * 100.0) / totalVolunteers : 0;
        double femalePercentage = (totalVolunteers > 0) ? (countFemale * 100.0) / totalVolunteers : 0;
        double haveExperiencePercentage = (totalVolunteers > 0) ? (countHaveExperience * 100.0) / totalVolunteers : 0;
        double noExperiencePercentage = (totalVolunteers > 0) ? (countNoExperience * 100.0) / totalVolunteers : 0;
        double ageUnder16Percentage = (totalVolunteers > 0) ? (ageUnder16 * 100.0) / totalVolunteers : 0;
        double age17to30Percentage = (totalVolunteers > 0) ? (age17to30 * 100.0) / totalVolunteers : 0;
        double age31to45Percentage = (totalVolunteers > 0) ? (age31to45 * 100.0) / totalVolunteers : 0;
        double ageAbove45Percentage = (totalVolunteers > 0) ? (ageAbove45 * 100.0) / totalVolunteers : 0;

        // Print the report
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("|                                       Volunteer Summary Report                                        |");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("| Generated on : " + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "                                                                    |");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("|                                                                                                       |");
        System.out.printf("| Total Number of Volunteer : %-73d |\n", totalVolunteers);
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("| Volunteer Gender         | Number Of Volunteer                | Percentage (%)                        |");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.printf("| MALE                     | %-34d | %-36.2f%% |\n", maleVolunteer.getNumberOfEntries(), malePercentage);
        System.out.printf("| FEMALE                   | %-34d | %-36.2f%% |\n", femaleVolunteer.getNumberOfEntries(), femalePercentage);
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.printf("| Total                    | %-34d | 100%%                                  |\n", totalVolunteers);
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("|                                                                                                       |");
        System.out.printf("| Summary By Volunteer Category : %-69d |\n", totalVolunteers);
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("| Volunteer Category       | Number Of Volunteer                | Percentage (%)                        |");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.printf("| HAVE_WORKING_EXPERIENCE  | %-34d | %-36.2f%% |\n", haveWorkingExp.getNumberOfEntries(), haveExperiencePercentage);
        System.out.printf("| NO_WORKING_EXPERIENCE    | %-34d | %-36.2f%% |\n", noWorkingExp.getNumberOfEntries(), noExperiencePercentage);
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.printf("| Total                    | %-34d | 100%%                                  |\n", totalVolunteers);
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("|                                                                                                       |");
        System.out.println("| Average Age of Volunteer                                                                              |");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("| Volunteer Age            | Number Of Volunteer                | Percentage (%)                        |");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.printf("| ( 0 - 16)  years old     | %-34d | %-36.2f%% |\n", ageUnder16, ageUnder16Percentage);
        System.out.printf("| (17 - 30)  years old     | %-34d | %-36.2f%% |\n", age17to30, age17to30Percentage);
        System.out.printf("| (31 - 45)  years old     | %-34d | %-36.2f%% |\n", age31to45, age31to45Percentage);
        System.out.printf("| (Above 45) years old     | %-34d | %-36.2f%% |\n", ageAbove45, ageAbove45Percentage);
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.printf("| Total                    | %-34d | 100%%                                  |\n", totalVolunteers);
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("|              This report is for staff use only. Please handle the information with care.              |");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        enterToContinue();
    }

    public int calculateAge(String dateOfBirth) {
        java.time.LocalDate birthDate = java.time.LocalDate.parse(dateOfBirth);
        java.time.LocalDate currentDate = java.time.LocalDate.now();
        return java.time.Period.between(birthDate, currentDate).getYears();
    }

    public static void main(String[] args) {
        VolunteerManagement productMaintenance = new VolunteerManagement();
        productMaintenance.runVolunteerManagement();
    }
   
}