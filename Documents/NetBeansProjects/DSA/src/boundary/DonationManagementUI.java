package boundary;

import control.DonationManagement;
import entity.Donation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import utility.MessageUI;
import static utility.MessageUI.displayInvalidChoiceMessage;
import static utility.MessageUI.enterToContinue;

public class DonationManagementUI {

    private final Scanner scanner = new Scanner(System.in);
    private final DonationManagement donationManagement = new DonationManagement(); // Use DonationManagement

    public int showDonationManagementMenu() {
        boolean flag = false;
        int choice;

        do {
            System.out.println("Welcome to the Donation Management Module!");
            System.out.println("1. Add a new Donation");
            System.out.println("2. Remove Donation");
            System.out.println("3. Update Donation");
            System.out.println("4. Search Donation");
            System.out.println("5. List Donations");
            System.out.println("6. Filter Donations");
            System.out.println("7. Generate Report");
            System.out.println("What would you like to do?");
            choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            if (choice >= 0 && choice <= 7) {
                flag = true;
            } else {
                displayInvalidChoiceMessage();
            }

        } while (!flag);

        return choice;
    }

    public void addNewDonation() {
        Donation newDonation = createNewDonation(); // Create a new donation using the input methods
        donationManagement.addDonation(newDonation); // Add the donation using DonationManagement
        System.out.println("Donation added successfully!");
    }

    public void removeDonation() {
        System.out.print("Enter the Donation ID to remove: ");
        int donationId = scanner.nextInt();
        scanner.nextLine();

        if (donationManagement.removeDonation(donationId)) {
            System.out.println("Donation removed successfully!");
        } else {
            System.out.println("Donation ID not found.");
        }
    }

    public void updateDonation() {
        System.out.print("Enter the Donation ID to update: ");
        int donationId = scanner.nextInt();
        scanner.nextLine();

        Donation donation = donationManagement.getDonationById(donationId);
        if (donation != null) {
            System.out.println("Updating Donation ID: " + donationId);
            donation.setDonorId(inputDonorId());
            donation.setDoneeId(inputDoneeId());
            donation.setAmount(inputAmount());
            donation.setDescription(inputDescription());
            donation.setDonationDate(inputDonationDate());

            donationManagement.updateDonation(donation); // Update the donation using DonationManagement
            System.out.println("Donation updated successfully!");
        } else {
            System.out.println("Donation ID not found.");
        }
    }

    public void searchDonation() {
        System.out.print("Enter the Donation ID to search: ");
        int donationId = scanner.nextInt();
        scanner.nextLine();

        Donation donation = donationManagement.getDonationById(donationId);
        if (donation != null) {
            displayDonationDetails(donation); // Display the donation details
        } else {
            System.out.println("Donation ID not found.");
        }
    }

    public void listDonations() {
        List<Donation> donations = donationManagement.listAllDonations();
        if (donations.isEmpty()) {
            System.out.println("No donations found.");
        } else {
            for (Donation donation : donations) {
                displayDonationDetails(donation);
            }
        }
    }

    public void filterDonations() {
        System.out.print("Enter minimum donation amount to filter: ");
        double minAmount = scanner.nextDouble();
        scanner.nextLine();

        List<Donation> filteredDonations = donationManagement.filterDonationsByAmount(minAmount);
        if (filteredDonations.isEmpty()) {
            System.out.println("No donations found for the specified criteria.");
        } else {
            for (Donation donation : filteredDonations) {
                displayDonationDetails(donation);
            }
        }
    }

    public void generateReport() {
        String report = donationManagement.generateReport();
        System.out.println(report);
    }

    public Donation createNewDonation() {
        String donorId = inputDonorId();
        String doneeId = inputDoneeId();
        double amount = inputAmount();
        String description = inputDescription();
        LocalDate donationDate = inputDonationDate();
        return new Donation(donorId, doneeId, amount, description, donationDate);
    }

    public String inputDonorId() {
        System.out.print("Enter Donor ID: ");
        return scanner.nextLine();
    }

    public String inputDoneeId() {
        System.out.print("Enter Donee ID: ");
        return scanner.nextLine();
    }

    public double inputAmount() {
        double amount = 0;
        boolean validAmount = false;

        do {
            System.out.print("Enter Donation Amount: ");
            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                scanner.nextLine(); // clear buffer
                if (amount > 0) {
                    validAmount = true;
                } else {
                    System.out.println("Amount must be positive.");
                    enterToContinue();
                }
            } else {
                displayInvalidChoiceMessage();
                scanner.next(); // clear buffer
            }
        } while (!validAmount);

        return amount;
    }

    public String inputDescription() {
        System.out.print("Enter Donation Description: ");
        return scanner.nextLine();
    }

    public LocalDate inputDonationDate() {
        LocalDate donationDate = null;
        while (donationDate == null) {
            System.out.print("Enter Donation Date (DD-MM-YYYY): ");
            String dateInput = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            try {
                donationDate = LocalDate.parse(dateInput, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in DD-MM-YYYY format.");
                enterToContinue();
            }
        }
        return donationDate;
    }

    public void displayDonationDetails(Donation donation) {
        MessageUI.line(15);
        System.out.println("Donation Details");
        MessageUI.line(15);
        System.out.println("Donation ID        : " + donation.getDonationId());
        System.out.println("Donor ID           : " + donation.getDonorId());
        System.out.println("Donee ID           : " + donation.getDoneeId());
        System.out.println("Amount             : " + donation.getAmount());
        System.out.println("Description        : " + donation.getDescription());
        System.out.println("Donation Date      : " + donation.getDonationDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    public int confirmationMessage() {
        boolean correctInput = false;
        int input = -1;
        do {
            System.out.println("1-Yes 2-No 0-Exit");
            System.out.print("Enter No:");
            try {
                input = scanner.nextInt();
            } catch (Exception ex) {
                scanner.nextLine(); // clear buffer
            }
            if (input >= 0 && input <= 2) {
                correctInput = true;
            } else {
                displayInvalidChoiceMessage();
            }
        } while (!correctInput);
        return input;
    }
}
