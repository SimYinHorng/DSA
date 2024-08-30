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
import static utility.VolunteerCategory.HAVE_WORKING_EXPERIENCE;
import static utility.VolunteerCategory.NO_WORKING_EXPERIENCE;
import utility.VolunteerGender;
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
        System.out.println("\n-------------------");
        System.out.println("VOLUNTEER MAIN MENU");
        System.out.println("-------------------");
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
            System.out.println("\n-----------------------");
            System.out.println("Which Part Need to Edit");
            System.out.println("-----------------------");
            System.out.println("1. Volunteer Name");
            System.out.println("2. Volunteer Email");
            System.out.println("3. Volunteer Phone No");
            System.out.println("4. Volunteer Address");
            System.out.println("5. Volunteer Date Of Birth");
            System.out.println("6. Volunteer Gender ");
            System.out.println("7. Volunteer Category");
            System.out.println("0. Quit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            if (choice >= 0 && choice <= 7) {
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
        line(259);
    }
    
    public void displayVolunteerDetails(Volunteer volunteer) {
        System.out.println("--------------------------------------------------------------");
        System.out.println("Volunteer Details");
        System.out.println("--------------------------------------------------------------");
        System.out.println("Volunteer ID            : " + volunteer.getVolunteerId());
        System.out.println("Volunteer Name          : " + volunteer.getName());
        System.out.println("Volunteer Email         : " + volunteer.getEmail());
        System.out.println("Volunteer Phone No      : " + volunteer.getPhoneNo());
        System.out.println("Volunteer Address       : " + volunteer.getAddress());
        System.out.println("Volunteer Date Of Birth : " + volunteer.getDateOfBirth());
        System.out.println("Volunteer Gender        : " + volunteer.getGender());
        System.out.println("Volunteer Category      : " + volunteer.getCategory());
        System.out.println("--------------------------------------------------------------\n");
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
            System.out.print("Enter Volunteer Email: ");
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
                System.out.println("Invalid Phone Number format!!!");
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

    public String enterVolunteerDateOfBirth(){
        String dateOfBirth;
        String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
        do {
            System.out.print("Enter Volunteer Date Of Birth [YYYY-MM-DD]: ");
            dateOfBirth = scanner.nextLine();
            if (!dateOfBirth.matches(regex)) {
                System.out.println("Invalid Date Of Birth! Please enter in the format [YYYY-MM-DD].");
                enterToContinue();
            }
        } while (!dateOfBirth.matches(regex));

        return dateOfBirth;
    }
    
    public VolunteerGender enterVolunteerGender() {
        boolean validInput = false;
        int input;
        VolunteerGender gender = null;
        do {
            System.out.println("1. Male");
            System.out.println("2. Female");
            System.out.print("Enter Volunteer Gender: ");
            input = scanner.nextInt();

            switch (input) {
                case 1:
                    gender = VolunteerGender.MALE;
                    validInput = true;
                    break;
                case 2:
                    gender = VolunteerGender.FEMALE;
                    validInput = true;
                    break;
                default:
                    displayInvalidChoiceMessage();
                    break;
            }

        } while (!validInput);

        return gender;
    }

    public VolunteerCategory enterVolunteerCategory() {
        boolean validInput = false;
        int input;
        VolunteerCategory category = null;
        do {
            System.out.println("1. Have working experience ");
            System.out.println("2. No working experience ");
            System.out.print("Enter Volunteer Category: ");
            input = scanner.nextInt();

            switch (input) {
                case 1:
                    category = VolunteerCategory.HAVE_WORKING_EXPERIENCE;
                    validInput = true;
                    break;
                case 2:
                    category = VolunteerCategory.NO_WORKING_EXPERIENCE;
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
        String volunteerDateOfBirth = enterVolunteerDateOfBirth();
        VolunteerGender volunteerGender = enterVolunteerGender();
        VolunteerCategory volunteerCategory = enterVolunteerCategory();
   
        return new Volunteer(volunteerName, volunteerEmail, volunteerPhoneNo, volunteerAddress, volunteerDateOfBirth, volunteerGender, volunteerCategory);
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
