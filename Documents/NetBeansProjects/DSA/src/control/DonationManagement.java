package control;

import boundary.DonationManagementUI; 
import entity.Donation;
import entity.Donor;
import entity.DonationItem;
import dao.DonationDAO;
import adt.ArrayList;
import java.time.LocalDate;

public class DonationManagement {
    private ArrayList<Donation> donations;
    private DonationDAO donationDAO;

    public DonationManagement() {
        donationDAO = new DonationDAO();  
        donations = donationDAO.loadDonations();  
        if (donations == null) {
            donations = new ArrayList<>(); 
        }
    }

    public void addDonation(Donor donor, LocalDate date, ArrayList<DonationItem> items) {
        Donation newDonation = new Donation(donor, date);
        for (int i = 1; i <= items.getNumberOfEntries(); i++) {  
            newDonation.addItem(items.getEntry(i));  
        }
        donations.add(newDonation); 
    }

    public boolean removeDonation(int donationId) {
        for (int i = 1; i <= donations.getNumberOfEntries(); i++) {  
            if (donations.getEntry(i).getDonationId() == donationId) {
                donations.remove(i);  
                return true;
            }
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

    public ArrayList<Donation> listDonationsByDonor(Donor donor) {
        ArrayList<Donation> donorDonations = new ArrayList<>();
        for (int i = 1; i <= donations.getNumberOfEntries(); i++) {  
            if (donations.getEntry(i).getDonor().equals(donor)) {
                donorDonations.add(donations.getEntry(i));  
            }
        }
        return donorDonations;
    }

    public ArrayList<Donation> listAllDonations() {
        return donations;
    }

    public ArrayList<Donation> filterDonations(LocalDate startDate, LocalDate endDate, double minValue, double maxValue) {
        ArrayList<Donation> filteredDonations = new ArrayList<>();
        for (int i = 1; i <= donations.getNumberOfEntries(); i++) {  
            Donation donation = donations.getEntry(i);
            if ((donation.getDonationDate().isAfter(startDate) || donation.getDonationDate().isEqual(startDate)) &&
                (donation.getDonationDate().isBefore(endDate) || donation.getDonationDate().isEqual(endDate)) &&
                donation.getTotalValue() >= minValue && donation.getTotalValue() <= maxValue) {
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
