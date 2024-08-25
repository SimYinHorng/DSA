/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.HashMap;
import entity.Donor;
import java.util.Iterator;
import java.util.Scanner;
import utility.DonorType;
import static utility.MessageUI.displayDonorHeader;
import static utility.MessageUI.line;
import dao.DonorDAO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utility.DonorCategory;
import utility.MessageUI;
import static utility.MessageUI.displayInvalidChoiceMessage;
import static utility.MessageUI.enterToContinue;

/**
 *
 * @author user
 */
public class DonorManagementUI {

    Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("DONOR MAIN MENU");
        System.out.println("1. Add new Donor");
        System.out.println("2. Remove a Donor");
        System.out.println("3. Update Donor Details");
        System.out.println("4. Search Donor");
        System.out.println("5. List Donors with All donations");
        System.out.println("6. Filter Donor by Criteria");
        System.out.println("7. Categorise Donor");
        System.out.println("8. Generate Report");
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
            System.out.println("Which Part Need to Edit");
            System.out.println("1. Donor Name");
            System.out.println("2. Donor Email");
            System.out.println("3. Donor Phone No");
            System.out.println("4. Donor Address");
            System.out.println("5. Donor Type ");
            System.out.println("6. Donor Category");
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

    public void listAllDonors(HashMap<Integer, Donor> donorMap) {
        Iterator keyIt = donorMap.keySet().getIterator();

        displayDonorHeader();
        while (keyIt.hasNext()) {
            System.out.println(donorMap.get((Integer) keyIt.next()).toString());
        }
        line(205);
    }

    public void displayDonorDetails(Donor donor) {
        System.out.println("Donor Details");
        System.out.println("Donor ID            : " + donor.getDonorId());
        System.out.println("Donor Name          : " + donor.getName());
        System.out.println("Donor Email         : " + donor.getEmail());
        System.out.println("Donor Phone No      : " + donor.getPhoneNo());
        System.out.println("Donor Address       : " + donor.getAddress());
        System.out.println("Donor Type          : " + donor.getType());
        System.out.println("Donor Category      : " + donor.getCategory());
        System.out.println("Number of Donations : " + donor.getDonationList().getNumberOfEntries());
    }

    public String inputDonorName() {
        boolean validName = false;
        String regex = "^[a-zA-Z\\s]+$|^[a-zA-Z0-9\\s&.,'-]+$";

        String name;
        do {
            System.out.print("Enter Donor name: ");
            name = scanner.nextLine();
            if (name.matches(regex)) {
                validName = true;
            } else {
                System.out.println("Invalid Name or Company Name!!!");
                enterToContinue();
            }
        } while (!validName);

        return name;
    }

    public String inputDonorEmail() {
        String email;
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        do {
            System.out.print("Enter Donor Email: ");
            email = scanner.nextLine();

            if (!email.matches(regex)) {
                System.out.println("Invalid Email!!!");
                enterToContinue();
            }
        } while (!email.matches(regex));

        return email;
    }

    public String inputDonorPhoneNo() {
        String phoneNo;
        String regex = "^(0\\d-\\d{8}|01\\d-\\d{8})$";
        do {
            System.out.print("Enter Donor Phone No: ");
            phoneNo = scanner.nextLine();
            if (!phoneNo.matches(regex)) {
                System.out.println("Invalid Phone Number!!!");
                enterToContinue();
            }
        } while (!phoneNo.matches(regex));

        return phoneNo;
    }

    public String inputDonorAddress() {
        System.out.print("Enter Donor Address: ");
        String address = scanner.nextLine();
        return address;
    }

    public DonorType inputDonorType() {
        boolean validInput = false;
        int input;
        DonorType type = null;
        do {
            System.out.println("1. Public");
            System.out.println("2. Private ");
            System.out.println("3. Government ");
            System.out.print("Enter Donor Type: ");
            input = scanner.nextInt();

            switch (input) {
                case 1:
                    type = DonorType.PUBLIC;
                    validInput = true;
                    break;
                case 2:
                    type = DonorType.PRIVATE;
                    validInput = true;
                    break;
                case 3:
                    type = DonorType.GOVERNMENT;
                    validInput = true;
                    break;
                default:
                    displayInvalidChoiceMessage();
                    break;
            }

        } while (!validInput);

        return type;
    }

    public DonorCategory inputDonorCat() {
        boolean validInput = false;
        int input;
        DonorCategory category = null;
        do {
            System.out.println("1. Individual");
            System.out.println("2. Organization ");
            System.out.print("Enter Donor Category: ");
            input = scanner.nextInt();

            switch (input) {
                case 1:
                    category = DonorCategory.INDIVIDUAL;
                    validInput = true;
                    break;
                case 2:
                    category = DonorCategory.ORGANIZATION;
                    validInput = true;
                    break;
                default:
                    displayInvalidChoiceMessage();
                    break;
            }
        } while (!validInput);
        return category;
    }

    public Donor inputDonorDetails() {
        String donorName = inputDonorName();
        String donorEmail = inputDonorEmail();
        String donorPhoneNo = inputDonorPhoneNo();
        String donorAddress = inputDonorAddress();
        DonorCategory donorCategory = inputDonorCat();
        switch (donorCategory) {
            case INDIVIDUAL:
                return new Donor(donorName, donorEmail, donorPhoneNo, donorAddress, donorEmail, DonorType.PRIVATE, donorCategory);
            case ORGANIZATION:
                DonorType donorType = inputDonorType();
                return new Donor(donorName, donorEmail, donorPhoneNo, donorAddress, donorEmail, donorType, donorCategory);
        }
        return new Donor();
    }

    public static void displayExitMessage() {
        System.out.println("Exit From Donor Management System");
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
