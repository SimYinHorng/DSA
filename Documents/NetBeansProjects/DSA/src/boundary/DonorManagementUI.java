/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.HashMap;
import adt.LinkedList;
import entity.Donor;
import java.util.Iterator;
import java.util.Scanner;
import utility.DonorType;
import static utility.MessageUI.displayDonorHeader;
import java.util.InputMismatchException;
import utility.DonorCategory;
import utility.MessageUI;
import static utility.MessageUI.displayInvalidChoiceMessage;
import static utility.MessageUI.enterToContinue;
import static utility.MessageUI.line;

/**
 *
 * @author user
 */
public class DonorManagementUI {

    Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        boolean correctInput = false;
        int choice;
        do {
            line(15);
            System.out.println("DONOR MAIN MENU");
            line(15);
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

            choice = validateInt();
            System.out.println("");
            if (choice >= 0 && choice <= 8) {
                correctInput = true;
            } else {
                displayInvalidChoiceMessage();
            }

        } while (!correctInput);

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
            choice = validateInt();
            System.out.println("");
            if (choice >= 0 && choice <= 6) {
                correctInput = true;
            } else {
                displayInvalidChoiceMessage();
            }

        } while (!correctInput);
        return choice;
    }

    public int getSearchMenu() {
        boolean correctInput = false;
        int choice;
        do {
            System.out.println("Search By:");
            System.out.println("1. Donor Name");
            System.out.println("2. Donor Email");
            System.out.println("3. Donor Phone No");
            System.out.println("4. Donor Address");
            System.out.println("5. Donor Type ");
            System.out.println("6. Donor Category");
            System.out.println("0. Quit");
            System.out.print("Enter choice: ");
            choice = validateInt();
            System.out.println("");
            if (choice >= 0 && choice <= 6) {
                correctInput = true;
            } else {
                displayInvalidChoiceMessage();
            }

        } while (!correctInput);
        return choice;
    }

    public int getCategorizeMenu() {
        boolean correctInput = false;
        int choice;
        do {
            System.out.println("Select One Category:");
            System.out.println("1. Government");
            System.out.println("2. Public");
            System.out.println("3. Private");
            System.out.println("0. Quit");
            System.out.print("Enter choice: ");
            choice = validateInt();
            System.out.println("");
            if (choice >= 0 && choice <= 3) {
                correctInput = true;
            } else {
                displayInvalidChoiceMessage();
            }

        } while (!correctInput);
        return choice;
    }

    public int getFilterMenu() {
        boolean correctInput = false;
        int choice;
        do {
            System.out.println("Select Filter:");
            System.out.println("1. Email Domain");
            System.out.println("2. Donation Range");
            System.out.println("3. Category Exclusion");
            System.out.println("0. Quit");
            System.out.print("Enter choice: ");
            choice = validateInt();
            System.out.println("");
            if (choice >= 0 && choice <= 3) {
                correctInput = true;
            } else {
                displayInvalidChoiceMessage();
            }

        } while (!correctInput);
        return choice;
    }

    public int donationFilterMenu() {
        boolean correctInput = false;
        int choice;
        do {
            System.out.println("Donation:");
            System.out.println("1. Above or Equal x quantity");
            System.out.println("2. Between x & y quantity");
            System.out.println("3. Below or Equal x quantity");
            System.out.println("4. Equal to x quantity");
            System.out.println("0. Quit");
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

    public void listAllDonors(HashMap<Integer, Donor> donorMap) {
        Iterator keyIt = donorMap.keySet().getIterator();

        displayDonorHeader();
        while (keyIt.hasNext()) {
            System.out.println(donorMap.get((Integer) keyIt.next()).toString());
        }
        line(205);
    }

    public void display(LinkedList<Donor> donorList) {
        Iterator linkedIt = donorList.iterator();
        displayDonorHeader();
        if (donorList.isEmpty()) {
            System.out.printf("| %-202s|\n", "No Record Found");
        } else {
            while (linkedIt.hasNext()) {
                System.out.println(linkedIt.next().toString());
            }

        }
        line(205);
    }

    public void categorizeHeader(int qty, double percentage, String title) {
        line(205);
        System.out.printf("| CATEGORY : %-191s|\n", title);
        line(205);
        System.out.printf("| Number of Donor : %-30d %.2f%%  %-144s |\n", qty, percentage, "of Donors");
    }

    public void displayDonorDetails(Donor donor) {
        MessageUI.line(15);
        System.out.println("Donor Details");
        MessageUI.line(15);
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

    public String inputSearchEmail() {
        System.out.print("Enter Donor Email: ");
        String email = scanner.nextLine();
        return email;
    }

    public String inputDonorPhoneNo() {
        String phoneNo;
        String regex = "^(0\\d-\\d{8}|01\\d-\\d{8})$";
        do {
            System.out.println("Example: (01x-xxxxxxxx)/ (0x-xxxxxxxx)");
            System.out.print("Enter Donor Phone No : ");
            phoneNo = scanner.nextLine();
            if (!phoneNo.matches(regex)) {
                System.out.println("Invalid Phone Number!!!");
                enterToContinue();
            }
        } while (!phoneNo.matches(regex));

        return phoneNo;
    }

    public String inputSearchPhoneNo() {
        System.out.println("Example: (01x-xxxxxxxx)/ (0x-xxxxxxxx)");
        System.out.print("Enter Donor Phone No : ");
        String phoneNo = scanner.nextLine();
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

    public String inputEmailDomain() {
        String emailDomain;
        String regex = "^@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        do {
            System.out.print("Enter Email Domain (e.g., @gmail.com): ");
            emailDomain = scanner.nextLine();

            if (!emailDomain.matches(regex)) {
                System.out.println("Invalid Email Domain!!!");
                enterToContinue();
            }
        } while (!emailDomain.matches(regex));

        return emailDomain;
    }

    public void reportHeader(String date, int total) {
        reportLine();
        System.out.printf("|%59s%45s\n", "Donor Summary Report", "|");
        reportLine();
        System.out.printf("| Generated on : %-86s |\n", date);
        reportLine();
        reportRow();
        System.out.printf("| Total Number of Donors : %-76d |\n", total);
        reportLine();
        System.out.printf("| Donor Type     | Number Of Donors | Percentage (%%) | Total Number of Donations | Total Donations (RM) |\n");
        reportLine();
    }
    
    public static void reportLine(){
        line(105);
    }

    public static void reportRow(){
        System.out.printf("|%-103s|\n", "");
    }
    
    public void reportCatHeader() {
        reportLine();
        reportRow();
        System.out.printf("| Summary By Donor Category : %-74s|\n","");
        reportLine();
        System.out.printf("| Donor Category | Number Of Donors | Percentage (%%) | Total Number of Donations | Total Donations (RM) |\n");
        reportLine();
    }
    
    public void topDonorHeader(){
        reportLine();
        reportRow();
        System.out.printf("| Top 5 Donors (by Total Number of Donations) %-58s|\n","");
        reportLine();
        System.out.printf("| Rank | Donor Name | Donor Type | Donor Category | Total Number of Donations |  Total Donations (RM)   |\n");
        reportLine();
        
    }
    
    public void botDonorHeader(){
        reportLine();
        reportRow();
        System.out.printf("| Bottom 5 Donors (by Total Number of Donations) %-55s|\n","");
        reportLine();
        System.out.printf("| Rank | Donor Name | Donor Type | Donor Category | Total Number of Donations |  Total Donations (RM)   |\n");
        reportLine();
    }

    public static void displayExitMessage() {
        System.out.println("Exit From Donor Management System");
    }

    public int inputDonorID() {
        boolean validInput = false;
        int id = -1;
        do {
            System.out.print("Donor ID: ");

            id = validateInt();
            if (id != -1) {
                validInput = true;
            } else {
                System.out.println("Please Enter Integer");
            }

        } while (!validInput);

        return id;
    }

    public int confirmationMessage() {
        boolean correctInput = false;
        int input = -1;
        do {
            System.out.println("1-Yes 2-No 0-Exit");
            System.out.print("Enter No:");
            try {
                input = scanner.nextInt();
            } catch (InputMismatchException ex) {
                scanner.nextLine();
            }
            if (input >= 0 && input <= 2) {
                correctInput = true;
            } else {
                displayInvalidChoiceMessage();
            }
        } while (!correctInput);
        return input;
    }

    public void filterHeader(String search) {
        line(205);
        System.out.printf("|Search Result Of : %-184s|\n", search);
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
}
