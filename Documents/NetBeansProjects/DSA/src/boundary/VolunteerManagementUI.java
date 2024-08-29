/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.HashMap;
import entity.Volunteer;
import java.util.Iterator;
import java.util.Scanner;
import utility.VolunteerCategory;
import static utility.VolunteerCategory.INDIVIDUAL;
import static utility.VolunteerCategory.ORGANIZATION;
import utility.VolunteerType;
import static utility.MessageUI.displayVolunteerHeader;
import static utility.MessageUI.displayInvalidChoiceMessage;
import static utility.MessageUI.enterToContinue;
import static utility.MessageUI.line;

/**
 *
 * @author Eason
 */
public class VolunteerManagementUI {
    
    Scanner scanner = new Scanner(System.in);
    
    public int getMenuChoice(){
        System.out.println("VOLUNTEER MAIN MENU");
        System.out.println("1. Add new Volunteer");
        System.out.println("2. Remove a Volunteer");
        System.out.println("3. Search Volunteer");
        System.out.println("4. Assign Volunteer to events");
        System.out.println("5. Search events under a Volunteer");
        System.out.println("6. List all Volunteer");
        System.out.println("7. Filter Volunteer based on criteria");
        System.out.println("8. Generate Summary Report");
        System.out.println("0. Quit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
        
    }
    
    /**
     *
     * @return
     */
    public int getEditMenu() {
        boolean correctInput = false;
        int choice;
        do {
            System.out.println("Which Part Need to Edit");
            System.out.println("1. Volunteer Name");
            System.out.println("2. Volunteer Email");
            System.out.println("3. Volunteer Phone No");
            System.out.println("4. Volunteer Address");
            System.out.println("5. Volunteer Type ");
            System.out.println("6. Volunteer Category");
            System.out.println("0. Quit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            if (choice >= 0 && choice <= 6) {
                correctInput = true;
            } else {
                displayInvalidChoiceMessage();
            }

        } while (!correctInput);
        return choice;
    }
    
    public void listAllVolunteer(HashMap<Integer, Volunteer> volunteerMap) {
        Iterator keyIt = volunteerMap.keySet().getIterator();

        displayVolunteerHeader();
        while (keyIt.hasNext()) {
            System.out.println(volunteerMap.get((Integer) keyIt.next()).toString());
        }
        line(205);
    }
    
    public void displayVolunteerDetails(Volunteer volunteer) {
        System.out.println("Volunteer Details");
        System.out.println("Volunteer ID            : " + volunteer.getVolunteerId());
        System.out.println("Volunteer Name          : " + volunteer.getName());
        System.out.println("Volunteer Email         : " + volunteer.getEmail());
        System.out.println("VOlunteer Phone No      : " + volunteer.getPhoneNo());
        System.out.println("Volunteer Address       : " + volunteer.getAddress());
        System.out.println("Volunteer Type          : " + volunteer.getType());
        System.out.println("Volunteer Category      : " + volunteer.getCategory());
    }
    
    public String enterVolunteerName() {
        boolean validName = false;
        String regex = "^[a-zA-Z\\s]+$|^[a-zA-Z0-9\\s&.,'-]+$";

        String name;
        do {
            System.out.print("Enter Volunteer name: ");
            name = scanner.nextLine();
            if (name.matches(regex)) {
                validName = true;
            } else {
                System.out.println("Invalid Name or Company Name !!!");
                enterToContinue();
            }
        } while (!validName);

        return name;
    }

    public String enterVolunteerEmail() {
        String email;
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        do {
            System.out.print("Enter Donor Email: ");
            email = scanner.nextLine();

            if (!email.matches(regex)) {
                System.out.println("Invalid Email !!!");
                enterToContinue();
            }
        } while (!email.matches(regex));

        return email;
    }

    public String enterVolunteerPhoneNo() {
        String phoneNo;
        String regex = "^(0\\d-\\d{8}|01\\d-\\d{8})$";
        do {
            System.out.print("Enter Volunteer Phone No: ");
            phoneNo = scanner.nextLine();
            if (!phoneNo.matches(regex)) {
                System.out.println("Invalid Phone Number with - !!!");
                enterToContinue();
            }
        } while (!phoneNo.matches(regex));

        return phoneNo;
    }

    public String enterVolunteerAddress() {
        System.out.print("Enter Volunteer Address: ");
        String address = scanner.nextLine();
        return address;
    }

    public VolunteerType enterVolunteerType() {
        boolean validInput = false;
        int input;
        VolunteerType type = null;
        do {
            System.out.println("1. Public");
            System.out.println("2. Private ");
            System.out.println("3. Government ");
            System.out.print("Enter Volunteer Type: ");
            input = scanner.nextInt();

            switch (input) {
                case 1:
                    type = VolunteerType.PUBLIC;
                    validInput = true;
                    break;
                case 2:
                    type = VolunteerType.PRIVATE;
                    validInput = true;
                    break;
                case 3:
                    type = VolunteerType.GOVERNMENT;
                    validInput = true;
                    break;
                default:
                    displayInvalidChoiceMessage();
                    break;
            }

        } while (!validInput);

        return type;
    }

    public VolunteerCategory enterVolunteerCategory() {
        boolean validInput = false;
        int input;
        VolunteerCategory category = null;
        do {
            System.out.println("1. Individual");
            System.out.println("2. Organization ");
            System.out.print("Enter Volunteer Category: ");
            input = scanner.nextInt();

            switch (input) {
                case 1:
                    category = VolunteerCategory.INDIVIDUAL;
                    validInput = true;
                    break;
                case 2:
                    category = VolunteerCategory.ORGANIZATION;
                    validInput = true;
                    break;
                default:
                    displayInvalidChoiceMessage();
                    break;
            }
        } while (!validInput);
        return category;
    }

    public Volunteer enterVolunteerDetails() {
        String volunteerName = enterVolunteerName();
        String volunteerEmail = enterVolunteerEmail();
        String volunteerPhoneNo = enterVolunteerPhoneNo();
        String volunteerAddress = enterVolunteerAddress();
        VolunteerCategory volunteerCategory = enterVolunteerCategory();
        switch (volunteerCategory) {
            case INDIVIDUAL:
                return new Volunteer(volunteerName, volunteerEmail, volunteerPhoneNo, volunteerAddress, volunteerEmail, VolunteerType.PRIVATE, volunteerCategory);
            case ORGANIZATION:
                VolunteerType volunteerType = enterVolunteerType();
                return new Volunteer(volunteerName, volunteerEmail, volunteerPhoneNo, volunteerAddress, volunteerEmail, volunteerType, volunteerCategory);
        }
        return new Volunteer();
    }

    public static void displayExitMessage() {
        System.out.println("Exit From Volunteer Management System");
    }

    public int confirmationMessage() {
        boolean correctInput = false;
        int input;
        do {
            System.out.println("1-Yes 2-No 0-Exit");
            System.out.print("Enter No:");
            input = scanner.nextInt();
            if (input >= 0 && input <= 2) {
                correctInput = true;
            } else {
                displayInvalidChoiceMessage();
            }
        } while (!correctInput);
        return input;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
}
