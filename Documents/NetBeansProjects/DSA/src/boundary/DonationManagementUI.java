package boundary;

import control.DonationManagement;
import entity.Donation;
import entity.Donor;
import entity.DonationItem;
import adt.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DonationManagementUI {
    private DonationManagement donationManager;
    private Scanner scanner;

    public DonationManagementUI() {
        donationManager = new DonationManagement();
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    addNewDonation();
                    break;
                case 2:
                    removeDonation();
                    break;
                case 3:
                    searchDonation();
                    break;
                case 4:
                    amendDonationDetails();
                    break;
                case 5:
                    trackDonatedItems();
                    break;
                case 6:
                    listDonationsByDonor();
                    break;
                case 7:
                    listAllDonations();
                    break;
                case 8:
                    filterDonations();
                    break;
                case 9:
                    generateSummaryReport();
                    break;
                case 10:
                    saveDonations();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Thank you for using the Donation Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\nDonation Management System");
        System.out.println("1. Add a new donation");
        System.out.println("2. Remove a donation");
        System.out.println("3. Search donation details");
        System.out.println("4. Amend donation details");
        System.out.println("5. Track donated items in categories");
        System.out.println("6. List donations by donor");
        System.out.println("7. List all donations");
        System.out.println("8. Filter donations");
        System.out.println("9. Generate summary report");
        System.out.println("10. Save donations");
        System.out.println("0. Exit");
    }

   private void addNewDonation() {
        System.out.println("\nAdding a new donation");
        Donor donor = inputDonor();
        LocalDate date = inputDate("Enter donation date (YYYY-MM-DD): ");
        ArrayList<DonationItem> items = inputDonationItems();
        donationManager.addDonation(donor, date, items);
        System.out.println("Donation added successfully.");
    }

    private void removeDonation() {
        int donationId = getIntInput("Enter donation ID to remove: ");
        boolean removed = donationManager.removeDonation(donationId);
        if (removed) {
            System.out.println("Donation removed successfully.");
        } else {
            System.out.println("Donation not found.");
        }
    }

    private void searchDonation() {
        int donationId = getIntInput("Enter donation ID to search: ");
        Donation donation = donationManager.searchDonation(donationId);
        if (donation != null) {
            System.out.println(donation);
        } else {
            System.out.println("Donation not found.");
        }
    }

    private void amendDonationDetails() {
        int donationId = getIntInput("Enter donation ID to amend: ");
        Donation donation = donationManager.searchDonation(donationId);
        if (donation != null) {
            LocalDate newDate = inputDate("Enter new donation date (YYYY-MM-DD): ");
            ArrayList<DonationItem> newItems = inputDonationItems();
            boolean amended = donationManager.amendDonationDetails(donationId, newDate, newItems);
            if (amended) {
                System.out.println("Donation details amended successfully.");
            } else {
                System.out.println("Failed to amend donation details.");
            }
        } else {
            System.out.println("Donation not found.");
        }
    }

    private void trackDonatedItems() {
        String category = getStringInput("Enter category to track: ");
        ArrayList<DonationItem> items = donationManager.trackDonatedItemsByCategory(category);
        if (!items.isEmpty()) {
            System.out.println("Donated items in category '" + category + "':");
            for (int i = 1; i <= items.getNumberOfEntries(); i++) {
                System.out.println(items.getEntry(i));
            }
        } else {
            System.out.println("No items found in the specified category.");
        }
    }

    private void listDonationsByDonor() {
        Donor donor = inputDonor();
        ArrayList<Donation> donations = donationManager.listDonationsByDonor(donor);
        if (!donations.isEmpty()) {
            System.out.println("Donations by " + donor.getName() + ":");
            for (int i = 1; i <= donations.getNumberOfEntries(); i++) {
                System.out.println(donations.getEntry(i));
            }
        } else {
            System.out.println("No donations found for the specified donor.");
        }
    }

    private void listAllDonations() {
        ArrayList<Donation> donations = donationManager.listAllDonations();
        if (!donations.isEmpty()) {
            System.out.println("All donations:");
            for (int i = 1; i <= donations.getNumberOfEntries(); i++) {
                System.out.println(donations.getEntry(i));
            }
        } else {
            System.out.println("No donations found.");
        }
    }

    private void filterDonations() {
        LocalDate startDate = inputDate("Enter start date (YYYY-MM-DD): ");
        LocalDate endDate = inputDate("Enter end date (YYYY-MM-DD): ");
        double minValue = getDoubleInput("Enter minimum value: ");
        double maxValue = getDoubleInput("Enter maximum value: ");
        ArrayList<Donation> filteredDonations = donationManager.filterDonations(startDate, endDate, minValue, maxValue);
        if (!filteredDonations.isEmpty()) {
            System.out.println("Filtered donations:");
            for (int i = 1; i <= filteredDonations.getNumberOfEntries(); i++) {
                System.out.println(filteredDonations.getEntry(i));
            }
        } else {
            System.out.println("No donations found matching the criteria.");
        }
    }

    private void generateSummaryReport() {
        String report = donationManager.generateSummaryReport();
        System.out.println(report);
    }

    private Donor inputDonor() {
        System.out.println("Enter donor details:");
        String name = getStringInput("Name: ");
        String email = getStringInput("Email: ");
        String phoneNo = getStringInput("Phone number: ");
        String address = getStringInput("Address: ");
        return new Donor(name, email, phoneNo, address, null, null);
    }

    private ArrayList<DonationItem> inputDonationItems() {
        ArrayList<DonationItem> items = new ArrayList<>();
        boolean addMore = true;
        while (addMore) {
            String name = getStringInput("Item name: ");
            double value = getDoubleInput("Item value: ");
            String category = getStringInput("Item category: ");
            boolean isCash = getBooleanInput("Is this a cash donation? (true/false): ");
            items.add(new DonationItem(name, value, category, isCash));
            addMore = getBooleanInput("Add another item? (true/false): ");
        }
        return items;
    }

    private LocalDate inputDate(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            try {
                String dateStr = getStringInput(prompt);
                return LocalDate.parse(dateStr, formatter);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private boolean getBooleanInput(String prompt) {
        while (true) {
            String input = getStringInput(prompt).toLowerCase();
            if (input.equals("true") || input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("false") || input.equals("no") || input.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter true or false.");
            }
        }
    }

    private void saveDonations() {
        donationManager.saveDonations();
        System.out.println("Donations saved successfully.");
    }

}
