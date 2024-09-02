/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.HashMap;
import adt.LinkedList;
import entity.Volunteer;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import utility.VolunteerCategory;
import utility.VolunteerGender;
import static utility.MessageUI.displayVolunteerHeader;
import static utility.MessageUI.displayInvalidChoiceMessage;
import static utility.MessageUI.enterToContinue;
import static utility.MessageUI.line;

/**
 *
 * @author Goh Wei Xian
 */
public class VolunteerManagementUI {
    
    Scanner scanner = new Scanner(System.in);
    
    public int getMenuChoice(){
        System.out.println("\n===================");
        System.out.println("VOLUNTEER MAIN MENU");
        System.out.println("===================");
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
    
    public int getEditMenu() {
        boolean correctInput = false;
        int choice;
        do {
            System.out.println("\n=======================");
            System.out.println("Which Part Need to Edit");
            System.out.println("=======================");
            System.out.println("1. Volunteer Name");
            System.out.println("2. Volunteer Email");
            System.out.println("3. Volunteer Phone No");
            System.out.println("4. Volunteer Address");
            System.out.println("5. Volunteer Date Of Birth");
            System.out.println("6. Volunteer Gender ");
            System.out.println("7. Volunteer Category");
            System.out.println("0. Back");
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
        line(239);
    }
    
    public void displayVolunteerDetails(Volunteer volunteer) {
        System.out.println("==============================================================");
        System.out.println("Volunteer Details");
        System.out.println("==============================================================");
        System.out.println("Volunteer ID            : " + volunteer.getVolunteerId());
        System.out.println("Volunteer Name          : " + volunteer.getName());
        System.out.println("Volunteer Email         : " + volunteer.getEmail());
        System.out.println("Volunteer Phone No      : " + volunteer.getPhoneNo());
        System.out.println("Volunteer Address       : " + volunteer.getAddress());
        System.out.println("Volunteer Date Of Birth : " + volunteer.getDateOfBirth());
        System.out.println("Volunteer Gender        : " + volunteer.getGender());
        System.out.println("Volunteer Category      : " + volunteer.getCategory());
        System.out.println("==============================================================\n");
    }
    
    public int eventVounteerFilterMenu() {
        boolean correctInput = false;
        int choice;
        do {
            System.out.println("\n====================================");
            System.out.println("Number of events assign to Volunteer");
            System.out.println("====================================");
            System.out.println("1. Above or Equal x quantity");
            System.out.println("2. Between x & y quantity");
            System.out.println("3. Below or Equal x quantity");
            System.out.println("4. Equal to x quantity");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            choice = validateInt();
            System.out.println("");
            if (choice >= 0 && choice <= 4) {
                correctInput = true;
            } else {
                displayInvalidChoiceMessage();
            }

        } while (!correctInput);
        return choice;
    }
    
    public int inputQty() {
        int qty;
        boolean validInt = false;
        do {
            System.out.print("Enter a Number : ");
            qty = validateInt();
            if (qty < 0) {
                displayInvalidChoiceMessage();
            } else {
                validInt = true;
            }
        } while (!validInt);
        return qty;
    }

    public void display(LinkedList<Volunteer> volunteerList) {
        Iterator linkedIt = volunteerList.iterator();
        displayVolunteerHeader();
        if (volunteerList.isEmpty()) {
            System.out.printf("| %-239s|\n", "No Record Found ...");
        } else {
            while (linkedIt.hasNext()) {
                System.out.println(linkedIt.next().toString());
            }

        }
        line(239);
    }
    
    public void filterHeader(String search) {
        line(239);
        System.out.printf("|Search Result Of : %-239s|\n", search);
    }
    
    public void displayOutput(LinkedList<Volunteer> volunteerList) {
        Iterator linkedIt = volunteerList.iterator();
        displayVolunteerHeader();
        if (volunteerList.isEmpty()) {
            System.out.printf("| %-239s|\n", "No Record Found");
        } else {
            while (linkedIt.hasNext()) {
                System.out.println(linkedIt.next().toString());
            }
        }
        line(239);
    }
    
    public int getSearchMenu() {
        boolean correctInput = false;
        int choice;
        do {
            System.out.println("\n================");
            System.out.println("Search Volunteer");
            System.out.println("================");
            System.out.println("1. Search by ID");
            System.out.println("2. Search by Name");
            System.out.println("3. Search by Email");
            System.out.println("4. Search by Phone Number");
            System.out.println("5. Search by Address");
            System.out.println("6. Search by Gender");
            System.out.println("7. Search by Category");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            choice = validateInt();
            System.out.println("");
            if (choice >= 0 && choice <= 7) {
                correctInput = true;
            } else {
                displayInvalidChoiceMessage();
            }
        } while (!correctInput);
        return choice;
    }
    
    public int validateInt() {
        try {
            int number = scanner.nextInt();
            scanner.nextLine();
            return number;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }

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
                System.out.println("Invalid Name. Plese re-enter !!!");
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
        String regex = "^(0\\d-\\d{7,8}|01\\d-\\d{7,8})$";
        do {
            System.out.print("Enter Volunteer Phone No (xxx-xxxxxxx): ");
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
            System.out.println("\n================");
            System.out.println("VOLUNTEER GENDER");
            System.out.println("================");
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
            System.out.println("\n==================");
            System.out.println("VOLUNTEER CATEGORY");
            System.out.println("==================");
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
    
    public int enterVolunteerId() {
        boolean validInput = false;
        int id = -1;
        do {
            System.out.print("Enter Volunteer Id: ");

            id = validateInt();
            if (id != -1) {
                validInput = true;
            } else {
                System.out.println("\nInvalid input. Please Enter Integer!!");
            }

        } while (!validInput);

        return id;
    }

    public static void displayExitMessage() {
        System.out.println("\n=====================================");
        System.out.println("Exit From Volunteer Management System");
        System.out.println("=====================================");
    }

    public int confirmationMessage() {
        boolean correctInput = false;
        int input;
        do {
            System.out.println("==================");
            System.out.println("1-Yes 2-No 0-Back");
            System.out.println("=================");
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