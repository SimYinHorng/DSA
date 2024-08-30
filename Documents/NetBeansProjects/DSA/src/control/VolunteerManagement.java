/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.HashMap;
import boundary.VolunteerManagementUI;
import dao.VolunteerDAO;
import entity.Volunteer;
import java.util.Iterator;
import static utility.MessageUI.displayInvalidChoiceMessage;
import utility.VolunteerCategory;
import utility.VolunteerGender;
import java.util.Scanner;
import static utility.MessageUI.enterToContinue;



/**
 *
 * @author user
 */
public class VolunteerManagement {
    Scanner scanner = new Scanner(System.in);
    private HashMap<Integer, Volunteer> volunteerMap = new HashMap<>();
    private VolunteerDAO volunteerDAO = new VolunteerDAO();
    private VolunteerManagementUI volunteerUI = new VolunteerManagementUI();

    public VolunteerManagement() {
        volunteerMap = volunteerDAO.retrieveFromFile();
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
                    assignVolunteer();
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
                    //generateReport();
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
                    System.out.println("Volunteer added successfullt!!\n");
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

    private void removeVolunteer() {
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
                    System.out.println("Volunteer removed successfully.\n");
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

    private void searchVolunteer() {
        System.out.println("\n----------------");
        System.out.println("Search Volunteer");
        System.out.println("----------------");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Search by Gender");
        System.out.println("4. Search by Category");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                searchById();
                break;
            case 2:
                //searchByName();
                break;
            case 3:
                //searchByGender();
                break;
            case 4:
                //searchByCategory();
                break;
            case 0:
                return; // Exit the search
        default:
            System.out.println("Invalid choice. Please try again.");
            searchVolunteer(); // Retry searching
        }
    }
    
    //search by id
    private void searchById() {
        boolean continueSearching = true;

        while (continueSearching) {
        System.out.print("\nEnter Volunteer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (volunteerMap.containsKey(id)) {
            Volunteer volunteer = volunteerMap.get(id);
            volunteerUI.displayVolunteerDetails(volunteer);
        } else {
            System.out.println("No volunteer found with the given ID.\n");
        }

        System.out.print("Do you want to search by another ID? (Y/N): ");
        String response = scanner.nextLine().trim().toLowerCase();
        if (!response.equals("y")) {
            continueSearching = false;
        }
    }
}
    
//    //search by name
//    private void searchByName() {
//    boolean continueSearching = true;
//
//    while (continueSearching) {
//        System.out.print("Enter Volunteer Name: ");
//        String name = scanner.nextLine().trim().toLowerCase();
//
//        boolean found = false;
//        for (Volunteer volunteer : volunteerMap.values()) {
//            if (volunteer.getName().toLowerCase().contains(name)) {
//                volunteerUI.displayVolunteerDetails(volunteer);
//                found = true;
//            }
//        }
//        if (!found) {
//            System.out.println("No volunteer found with the given name.");
//        }
//
//        System.out.print("Do you want to search by another name? (Y/N): ");
//        String response = scanner.nextLine().trim().toLowerCase();
//        if (!response.equals("y")) {
//            continueSearching = false;
//        }
//    }
//}
//    
//    //search by gender
//    private void searchByGender() {
//    System.out.print("Enter Volunteer Gender (Male/Female): ");
//    String genderInput = scanner.nextLine().trim().toLowerCase();
//
//    // Convert the input to a Gender enum if your gender is stored as an enum
//    VolunteerGender gender;
//    try {
//        gender = VolunteerGender.valueOf(genderInput.toUpperCase());
//    } catch (IllegalArgumentException e) {
//        System.out.println("Invalid gender input. Please enter 'Male' or 'Female'.");
//        return; // Exit the method if the input is invalid
//    }
//
//    boolean found = false;
//    Iterator<Volunteer> iterator = volunteerMap.values().iterator();
//    while (iterator.hasNext()) {
//        Volunteer volunteer = iterator.next();
//        if (volunteer.getGender().equals(gender)) {  // Ensure this method is defined in the Volunteer class
//            volunteerUI.displayVolunteerDetails(volunteer);
//            found = true;
//        }
//    }
//    if (!found) {
//        System.out.println("No volunteer found with the given gender.");
//    }
//
//    // Ask if the user wants to search again
//    System.out.print("Do you want to search for another volunteer? (Y/N): ");
//    String input = scanner.nextLine().trim().toLowerCase();
//    if (input.equals("y")) {
//        searchVolunteer();  // Restart the search
//    }
//}
//    
//    //search by category
//    private void searchByCategory() {
//    boolean continueSearching = true;
//
//    while (continueSearching) {
//        System.out.print("Enter Volunteer Category (HAVE_WORKING_EXPERIENCE/NO_WORKING_EXPERIENCE): ");
//        String categoryInput = scanner.nextLine().trim().toUpperCase();
//
//        VolunteerCategory category;
//        try {
//            category = VolunteerCategory.valueOf(categoryInput);
//        } catch (IllegalArgumentException e) {
//            System.out.println("Invalid category. Please enter 'HAVE_WORKING_EXPERIENCE' or 'NO_WORKING_EXPERIENCE'.");
//            continue;
//        }
//
//        boolean found = false;
//        for (Volunteer volunteer : volunteerMap.values()) {
//            if (volunteer.getCategory() == category) {
//                volunteerUI.displayVolunteerDetails(volunteer);
//                found = true;
//            }
//        }
//        if (!found) {
//            System.out.println("No volunteer found with the given category.");
//        }
//
//        System.out.print("Do you want to search by another category? (Y/N): ");
//        String response = scanner.nextLine().trim().toLowerCase();
//        if (!response.equals("y")) {
//            continueSearching = false;
//        }
//    }
//}

    private void assignVolunteer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void searchEventUnderVolunteer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void listVolunteer() {
        volunteerUI.listAllVolunteer(volunteerMap);
        enterToContinue();
    }

    private void filterVolunteer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
//    private void generateReport() {
//    // Initialize counters and accumulators
//    int totalVolunteers = 0;
//    int countHaveExperience = 0;
//    int countNoExperience = 0;
//    int countMale = 0;
//    int countFemale = 0;
//    int totalAge = 0;
//    
//    // Iterate through the volunteer map to gather data
//    for (Volunteer volunteer : volunteerMap.values()) {
//        totalVolunteers++;
//        
//        // Count experience categories
//        if (volunteer.getCategory() == VolunteerCategory.HAVE_WORKING_EXPERIENCE) {
//            countHaveExperience++;
//        } else if (volunteer.getCategory() == VolunteerCategory.NO_WORKING_EXPERIENCE) {
//            countNoExperience++;
//        }
//        
//        // Count gender
//        if (volunteer.getGender() == VolunteerGender.MALE) {
//            countMale++;
//        } else if (volunteer.getGender() == VolunteerGender.FEMALE) {
//            countFemale++;
//        }
//        
//        // Calculate total age for average age calculation
//        String[] dobParts = volunteer.getDateOfBirth().split("-");
//        int birthYear = Integer.parseInt(dobParts[0]);
//        int currentYear = java.time.Year.now().getValue();
//        totalAge += (currentYear - birthYear);
//    }
//    
//    // Calculate average age
//    double averageAge = totalVolunteers > 0 ? (double) totalAge / totalVolunteers : 0;
//    
//    // Print detailed summary report
//    System.out.println("\n---------------------------------");
//    System.out.println("Detailed Volunteer Summary Report");
//    System.out.println("---------------------------------");
//    System.out.printf("%-40s %d%n", "Total number of volunteers:", totalVolunteers);
//    System.out.printf("%-40s %d%n", "Volunteers with working experience:", countHaveExperience);
//    System.out.printf("%-40s %d%n", "Volunteers without working experience:", countNoExperience);
//    System.out.printf("%-40s %d%n", "Male volunteers:", countMale);
//    System.out.printf("%-40s %d%n", "Female volunteers:", countFemale);
//    System.out.printf("%-40s %.2f%n", "Average age of volunteers:", averageAge);
//    System.out.println("---------------------------------\n");
//}

    
    public static void main(String[] args) {
        VolunteerManagement productMaintenance = new VolunteerManagement();
        productMaintenance.runVolunteerManagement();
    }
}
