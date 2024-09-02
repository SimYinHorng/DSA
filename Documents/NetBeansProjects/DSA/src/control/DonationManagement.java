package control;

import boundary.DonationManagementUI;
import entity.Donation;
import entity.Donor;
import entity.Donee;
import entity.DonationItem;
import dao.DonationDAO;
import adt.ArrayList;
import java.time.LocalDate;
import java.util.Scanner; // Import Scanner for user input

public class DonationManagement {

    private ArrayList<Donation> donations;
    private ArrayList<Donee> donees; // Assuming donees are stored here
    private DonationDAO donationDAO;

    public DonationManagement() {
        donationDAO = new DonationDAO();
        donations = donationDAO.loadDonations();
        if (donations == null) {
            donations = new ArrayList<>();
        }
        // Load donees
        donees = loadDonees(); // Method to load donees
    }

    private ArrayList<Donee> loadDonees() {
        // Logic to load donees from a data source
        return new ArrayList<>(); // Replace with actual loading logic
    }

    public Donee getDoneeByName(String name) {
        for (int i = 1; i <= donees.getNumberOfEntries(); i++) {
            Donee donee = donees.getEntry(i);
            if (donee.getName().equalsIgnoreCase(name)) {
                return donee;
            }
        }
        return null;
    }

    public Donee getDoneeById(int doneeId) {
        for (int i = 1; i <= donees.getNumberOfEntries(); i++) {
            Donee donee = donees.getEntry(i);
            if (donee.getDoneeId() == doneeId) {
                return donee;
            }
        }
        return null;
    }

    public void addDonation(Donor donor, String doneeName, int doneeId, LocalDate date, ArrayList<DonationItem> items) {
        Donee donee = getDoneeByName(doneeName);
        if (donee == null) {
            donee = getDoneeById(doneeId);
        }

        if (donee != null) {
            Donation newDonation = new Donation(donor, donee, date); // Updated to include donor and donee
            for (int i = 1; i <= items.getNumberOfEntries(); i++) {
                newDonation.addItem(items.getEntry(i));
            }
            donations.add(newDonation);
        } else {
            System.out.println("Donee not found. Please provide a valid Donee name or ID.");
        }
    }

    public void addDonation(Donor donor, LocalDate date, ArrayList<DonationItem> items) {
        Donation newDonation = new Donation(donor, date);
        for (int i = 1; i <= items.getNumberOfEntries(); i++) {
            newDonation.addItem(items.getEntry(i));
        }
        donations.add(newDonation);
    }

    public void addDonation(Donee donee, LocalDate date, ArrayList<DonationItem> items) {
        Donation newDonation = new Donation(donee, date);
        for (int i = 1; i <= items.getNumberOfEntries(); i++) {
            newDonation.addItem(items.getEntry(i));
        }
        donations.add(newDonation);
    }

    public boolean removeDonation(int donationId) {
    Donation donationToRemove = searchDonation(donationId);

        if (donationToRemove != null) {
            // Display detailed information of the donation to be removed
            System.out.println("Are you sure you want to remove the following donation?");
            System.out.println("Donor: " + donationToRemove.getDonor().getName());
            System.out.println("Date: " + donationToRemove.getDonationDate());
            System.out.println("Total Value: $" + String.format("%.2f", donationToRemove.getTotalValue()));
            System.out.println("Donated Items:");

            // Inside your removeDonation method
// Display each item's details
            for (int j = 1; j <= donationToRemove.getItems().getNumberOfEntries(); j++) {
                DonationItem item = donationToRemove.getItems().getEntry(j);
                System.out.println("  - Item: " + item.getName()
                        + // Corrected method call here
                        ", Category: " + item.getCategory()
                        + ", Value: $" + String.format("%.2f", item.getValue()));
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter 'yes' to confirm, or 'no' to cancel: ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes")) {
            // Proceed with the removal
            for (int i = 1; i <= donations.getNumberOfEntries(); i++) {
                if (donations.getEntry(i).getDonationId() == donationId) {
                    donations.remove(i);
                    System.out.println("Donation successfully removed.");
                    return true;
                }
            }
        } else {
            // User canceled the removal
            System.out.println("Donation removal canceled.");
        }
    } else {
        System.out.println("Donation with ID " + donationId + " not found.");
    }

    return false;
}


    public Donation searchDonation(int donationId) {
        for (int i = 1; i <= donations.getNumberOfEntries(); i++) {
            if (donations.getEntry(i).getDonationId() == donationId) {
                return donations.getEntry(i);
            }
        }
        return null;
    }

    public boolean amendDonationDetails(int donationId, LocalDate newDate, ArrayList<DonationItem> newItems) {
        Donation donation = searchDonation(donationId);
        if (donation != null) {
            donation.setDonationDate(newDate);
            donation.getItems().clear();
            for (int i = 1; i <= newItems.getNumberOfEntries(); i++) {
                donation.addItem(newItems.getEntry(i));
            }
            return true;
        }
        return false;
    }

    public ArrayList<DonationItem> trackDonatedItemsByCategory(String category) {
        ArrayList<DonationItem> trackedItems = new ArrayList<>();
        for (int i = 1; i <= donations.getNumberOfEntries(); i++) {
            Donation donation = donations.getEntry(i);
            for (int j = 1; j <= donation.getItems().getNumberOfEntries(); j++) {
                DonationItem item = donation.getItems().getEntry(j);
                if (item.getCategory().equalsIgnoreCase(category)) {
                    trackedItems.add(item);
                }
            }
        }
        return trackedItems;
    }

    public ArrayList<Donation> listDonationsByDonor(String donorName) {
        ArrayList<Donation> donorDonations = new ArrayList<>();
        for (int i = 1; i <= donations.getNumberOfEntries(); i++) {
            Donation donation = donations.getEntry(i);
            if (donation.getDonor() != null && donation.getDonor().getName().equalsIgnoreCase(donorName)) {
                donorDonations.add(donation);
            }
        }
        return donorDonations;
    }

    public ArrayList<Donation> listDonationsByDonee(Donee donee) {
        ArrayList<Donation> doneeDonations = new ArrayList<>();
        for (int i = 1; i <= donations.getNumberOfEntries(); i++) {
            Donation donation = donations.getEntry(i);
            if (donation.getDonee() != null && donation.getDonee().equals(donee)) {
                doneeDonations.add(donation);
            }
        }
        return doneeDonations;
    }

    public ArrayList<Donation> listAllDonations() {
        return donations;
    }

    public ArrayList<Donation> filterDonations(LocalDate startDate, LocalDate endDate, double minValue, double maxValue) {
        ArrayList<Donation> filteredDonations = new ArrayList<>();
        for (int i = 1; i <= donations.getNumberOfEntries(); i++) {
            Donation donation = donations.getEntry(i);
            if ((donation.getDonationDate().isAfter(startDate) || donation.getDonationDate().isEqual(startDate))
                    && (donation.getDonationDate().isBefore(endDate) || donation.getDonationDate().isEqual(endDate))
                    && donation.getTotalValue() >= minValue && donation.getTotalValue() <= maxValue) {
                filteredDonations.add(donation);
            }
        }
        return filteredDonations;
    }

    public String generateSummaryReport() {
        StringBuilder report = new StringBuilder();
        double totalValue = 0;
        for (int i = 1; i <= donations.getNumberOfEntries(); i++) {
            Donation donation = donations.getEntry(i);
            totalValue += donation.getTotalValue();
            report.append(donation.toString()).append("\n");
        }
        report.append("\nTotal Donations: ").append(donations.getNumberOfEntries());
        report.append("\nTotal Value: $").append(String.format("%.2f", totalValue));
        return report.toString();
    }

    public void saveDonations() {
        donationDAO.saveDonations(donations);
    }

    public static void main(String[] args) {
        DonationManagementUI ui = new DonationManagementUI();
        ui.run();
    }
}
