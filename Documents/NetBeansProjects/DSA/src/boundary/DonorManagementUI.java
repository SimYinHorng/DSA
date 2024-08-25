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
        System.out.println("Number of Donations : " + donor.getDonationList().getNumberOfEntries());
    }

    public String inputDonorName() {
        System.out.print("Enter Donor name: ");
        String name = scanner.nextLine();
        return name;
    }

    public String inputDonorEmail() {
        System.out.print("Enter Donor Email: ");
        String email = scanner.nextLine();
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        System.out.println(email + " : " + matcher.matches() + "\n");
        return email;
    }

    public String inputDonorPhoneNo() {
        System.out.print("Enter Donor Phone No: ");
        String phoneNo = scanner.nextLine();
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

        return phoneNo;
    }

    public String inputDonorAddress() {
        System.out.print("Enter Donor Address: ");
        String address = scanner.nextLine();
        return address;
    }

    public DonorType inputDonorType() {
        System.out.println("1. Public");
        System.out.println("2. Private ");
        System.out.println("3. Government ");
        System.out.print("Enter Donor Type: ");
        int input = scanner.nextInt();
        DonorType type = null;
        switch (input) {
            case 1:
                type = DonorType.PUBLIC;
                break;
            case 2:
                type = DonorType.PRIVATE;
                break;
            case 3:
                type = DonorType.GOVERNMENT;
                break;
        }

        return type;
    }

    public DonorCategory inputDonorCat() {
        System.out.println("1. Individual");
        System.out.println("2. Organization ");
        System.out.print("Enter Donor Category: ");
        int input = scanner.nextInt();
        DonorCategory category = null;
        switch (input) {
            case 1:
                category = DonorCategory.INDIVIDUAL;
                break;
            case 2:
                category = DonorCategory.ORGANIZATION;
                break;
        }

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

    public void displayExitMessage() {
        System.out.println("Exit From Donor Management System");
    }

    public char confirmationMessage() {

        System.out.println("Y-Yes N-No X-Exit");
        char input = scanner.next().charAt(0);

        return input;
    }
}
