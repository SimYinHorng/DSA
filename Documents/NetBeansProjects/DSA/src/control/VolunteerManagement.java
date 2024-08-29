/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.HashMap;
import boundary.VolunteerManagementUI;
import dao.VolunteerDAO;
import entity.Volunteer;
import static utility.MessageUI.displayInvalidChoiceMessage;
import utility.VolunteerCategory;
import utility.VolunteerType;
import java.util.Scanner;



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
                            VolunteerType type = volunteerUI.enterVolunteerType();
                            newVolunteer.setType(type);
                            break;
                        case 6:
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
        System.out.print("Enter Volunteer ID to remove: ");
        int volunteerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

    if (volunteerMap.containsKey(volunteerId)) {
            System.out.println("Removing volunteer with ID: " + volunteerId);
            volunteerMap.remove(volunteerId);
            volunteerDAO.saveToFile(volunteerMap);
            System.out.println("Volunteer removed successfully.");
        } else {
        System.out.println("No volunteer found with the given ID.");
        }
    }
    
    private void searchVolunteer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void assignVolunteer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void searchEventUnderVolunteer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void listVolunteer() {
        volunteerUI.listAllVolunteer(volunteerMap);
    }

    private void filterVolunteer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void generateReport() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String[] args) {
        VolunteerManagement productMaintenance = new VolunteerManagement();
        productMaintenance.runVolunteerManagement();
    }
}
