/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.HashMap;
import adt.LinkedList;
import boundary.VolunteerManagementUI;
import dao.VolunteerDAO;
import entity.Donor;
import entity.Volunteer;
import java.util.Iterator;
import static utility.MessageUI.displayInvalidChoiceMessage;
import utility.VolunteerCategory;
import utility.VolunteerGender;
import java.util.Scanner;
import utility.DonorCategory;
import utility.DonorType;
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
        boolean exit = false;
        do{
           int choice = volunteerUI.getSearchMenu();
        switch (choice) {
            case 1:
//                System.out.print("Enter Volunteer ID: ");
//                int id = scanner.nextInt();
//                scanner.nextLine(); 
//                
//                if (volunteerMap.containsKey(id)) {
//                Volunteer volunteer = volunteerMap.get(id);
//                String idToString = String.valueOf(id);
//                volunteerUI.filterHeader(idToString);
//                volunteerUI.displayOutput(filterBy(choice,volunteer.getVolunteerId()));
//                
//                } 
                break;
            case 2:
                String name = volunteerUI.enterVolunteerName();
                volunteerUI.filterHeader(name);
                volunteerUI.displayOutput(filterBy(choice,name));
                break;
            case 3:
                String email = volunteerUI.enterVolunteerEmail();
                volunteerUI.filterHeader(email);
                volunteerUI.displayOutput(filterBy(choice,email));
                break;
            case 4:
                String phoneNo = volunteerUI.enterVolunteerPhoneNo();
                volunteerUI.filterHeader(phoneNo);
                volunteerUI.displayOutput(filterBy(choice,phoneNo));
                break;
            case 5:
                String address = volunteerUI.enterVolunteerName();
                volunteerUI.filterHeader(address);
                volunteerUI.displayOutput(filterBy(choice,address));
                break;
            case 6:
                VolunteerGender gender = volunteerUI.enterVolunteerGender();
                volunteerUI.filterHeader(gender.toString());
                volunteerUI.displayOutput(filterBy(choice,gender));
                break;
            case 7:
                VolunteerCategory category = volunteerUI.enterVolunteerCategory();
                volunteerUI.filterHeader(category.toString());
                volunteerUI.displayOutput(filterBy(choice, category));
            case 0:
                exit = true;
                break;
            }
            if (!exit) {
                enterToContinue();
            }
        }while(!exit);
    }
    
    public LinkedList<Volunteer> filterBy(int criteria, Object searchValue) {
        LinkedList<Volunteer> result = new LinkedList<>();
        Iterator keyIt = volunteerMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Integer key = (Integer) keyIt.next();
            Volunteer volunteer = volunteerMap.get(key);
            switch (criteria) {
                case 1:
//                    String id = (String) searchValue;
//                    if(volunteer.getVolunteerId().toLowerCase().contains(id.toLowerCase())){
//                        result.add(volunteer);
//                    }
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
    
//    public void generateReport() {
//    int totalVolunteers = volunteerMap.size();
//    int countMale = 0, countFemale = 0;
//    int countHaveExperience = 0, countNoExperience = 0;
//    int ageUnder16 = 0, age17to30 = 0, age31to45 = 0, ageAbove45 = 0;
//
//    
//    // Loop through volunteers to gather statistics
//    for (Volunteer volunteer : volunteerMap.values()) {
//        // Gender count
//        if (volunteer.getGender().toLowerCase().equals("Male")) {
//            countMale++;
//        } else if (volunteer.getGender().toLowerCase().equals("Female")) {
//            countFemale++;
//        }
//
//        // Experience category count
//        if (volunteer.getCategory() == VolunteerCategory.HAVE_WORKING_EXPERIENCE) {
//            countHaveExperience++;
//        } else if (volunteer.getCategory() == VolunteerCategory.NO_WORKING_EXPERIENCE) {
//            countNoExperience++;
//        }
//
//        // Age calculation
//        int age = calculateAge(volunteer.getDateOfBirth());
//        if (age <= 16) {
//            ageUnder16++;
//        } else if (age >= 17 && age <= 30) {
//            age17to30++;
//        } else if (age >= 31 && age <= 45) {
//            age31to45++;
//        } else {
//            ageAbove45++;
//        }
//    }
//
//    // Calculate percentages
//    double malePercentage = (totalVolunteers > 0) ? (countMale * 100.0) / totalVolunteers : 0;
//    double femalePercentage = (totalVolunteers > 0) ? (countFemale * 100.0) / totalVolunteers : 0;
//    double haveExperiencePercentage = (totalVolunteers > 0) ? (countHaveExperience * 100.0) / totalVolunteers : 0;
//    double noExperiencePercentage = (totalVolunteers > 0) ? (countNoExperience * 100.0) / totalVolunteers : 0;
//    double ageUnder16Percentage = (totalVolunteers > 0) ? (ageUnder16 * 100.0) / totalVolunteers : 0;
//    double age17to30Percentage = (totalVolunteers > 0) ? (age17to30 * 100.0) / totalVolunteers : 0;
//    double age31to45Percentage = (totalVolunteers > 0) ? (age31to45 * 100.0) / totalVolunteers : 0;
//    double ageAbove45Percentage = (totalVolunteers > 0) ? (ageAbove45 * 100.0) / totalVolunteers : 0;
//
//    // Print the report
//    System.out.println("---------------------------------------------------------------------------------------------------------");
//    System.out.println("|                                       Volunteer Summary Report                                        |");
//    System.out.println("---------------------------------------------------------------------------------------------------------");
//    System.out.println("| Generated on : " + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "                                                |");
//    System.out.println("---------------------------------------------------------------------------------------------------------");
//    System.out.println("|                                                                                                       |");
//    System.out.printf("| Total Number of Volunteer : %-69d |\n", totalVolunteers);
//    System.out.println("---------------------------------------------------------------------------------------------------------");
//    System.out.println("| Volunteer Gender         | Number Of Volunteer                | Percentage (%)                        |");
//    System.out.println("---------------------------------------------------------------------------------------------------------");
//    System.out.printf("| MALE                     | %-35d | %-31.2f%% |\n", countMale, malePercentage);
//    System.out.printf("| FEMALE                   | %-35d | %-31.2f%% |\n", countFemale, femalePercentage);
//    System.out.println("---------------------------------------------------------------------------------------------------------");
//    System.out.printf("| Total                    | %-35d | 100%%                                  |\n", totalVolunteers);
//    System.out.println("---------------------------------------------------------------------------------------------------------");
//    System.out.println("|                                                                                                       |");
//    System.out.printf("| Summary By Volunteer Category : %-64d |\n", totalVolunteers);
//    System.out.println("---------------------------------------------------------------------------------------------------------");
//    System.out.println("| Volunteer Category        | Number Of Volunteer                | Percentage (%)                        |");
//    System.out.println("---------------------------------------------------------------------------------------------------------");
//    System.out.printf("| HAVE_WORKING_EXPERIENCE   | %-35d | %-31.2f%% |\n", countHaveExperience, haveExperiencePercentage);
//    System.out.printf("| NO_WORKING_EXPERIENCE     | %-35d | %-31.2f%% |\n", countNoExperience, noExperiencePercentage);
//    System.out.println("---------------------------------------------------------------------------------------------------------");
//    System.out.printf("| Total                    | %-35d | 100%%                                  |\n", totalVolunteers);
//    System.out.println("---------------------------------------------------------------------------------------------------------");
//    System.out.println("|                                                                                                       |");
//    System.out.println("| Average Age of Volunteer                                                                              |");
//    System.out.println("---------------------------------------------------------------------------------------------------------");
//    System.out.println("| Volunteer Age            | Number Of Volunteer                | Percentage (%)                        |");
//    System.out.println("---------------------------------------------------------------------------------------------------------");
//    System.out.printf("| ( 0 - 16)  years old     | %-35d | %-31.2f%% |\n", ageUnder16, ageUnder16Percentage);
//    System.out.printf("| (17 - 30)  years old     | %-35d | %-31.2f%% |\n", age17to30, age17to30Percentage);
//    System.out.printf("| (31 - 45)  years old     | %-35d | %-31.2f%% |\n", age31to45, age31to45Percentage);
//    System.out.printf("| (Above 45) years old     | %-35d | %-31.2f%% |\n", ageAbove45, ageAbove45Percentage);
//    System.out.println("---------------------------------------------------------------------------------------------------------");
//    System.out.printf("| Total                    | %-35d | 100%%                                  |\n", totalVolunteers);
//    System.out.println("---------------------------------------------------------------------------------------------------------");
//    System.out.println("---------------------------------------------------------------------------------------------------------");
//    System.out.println("|              This report is for staff use only. Please handle the information with care.              |");
//    System.out.println("---------------------------------------------------------------------------------------------------------");
//}

// Helper method to calculate the age from the date of birth
private int calculateAge(String dateOfBirth) {
    java.time.LocalDate birthDate = java.time.LocalDate.parse(dateOfBirth);
    java.time.LocalDate currentDate = java.time.LocalDate.now();
    return java.time.Period.between(birthDate, currentDate).getYears();
}


    
    public static void main(String[] args) {
        VolunteerManagement productMaintenance = new VolunteerManagement();
        productMaintenance.runVolunteerManagement();
    }
}
