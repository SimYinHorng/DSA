package control;

import java.util.HashMap;
import dao.DonationDAO;
import entity.Donation;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

/**
 * Control class for managing donations.
 */
public class DonationManagement {

    private DonationDAO donationDAO;
    private HashMap<Integer, Donation> donationMap;

    /**
     * Constructor to initialize DonationManagement.
     */
    public DonationManagement() {
        donationDAO = new DonationDAO();
        donationMap = donationDAO.getAllDonations();
    }

    /**
     * Adds a new donation to the system.
     * 
     * @param donation The Donation object to add.
     */
    public void addDonation(Donation donation) {
        donationDAO.saveDonation(donation);
        donationMap.put(donation.getDonationId(), donation);
    }

    /**
     * Removes a donation by its ID.
     * 
     * @param donationId The ID of the donation to remove.
     * @return true if the donation was removed, false otherwise.
     */
    public boolean removeDonation(int donationId) {
        if (donationMap.containsKey(donationId)) {
            donationDAO.deleteDonation(donationId);
            donationMap.remove(donationId);
            return true;
        } else {
            System.out.println("Donation with ID " + donationId + " not found.");
            return false;
        }
    }

    /**
     * Updates an existing donation.
     * 
     * @param donation The updated Donation object.
     * @return true if the donation was updated, false otherwise.
     */
    public boolean updateDonation(Donation donation) {
        if (donationMap.containsKey(donation.getDonationId())) {
            donationDAO.updateDonation(donation);
            donationMap.put(donation.getDonationId(), donation);
            return true;
        } else {
            System.out.println("Donation with ID " + donation.getDonationId() + " not found.");
            return false;
        }
    }

    /**
     * Retrieves a donation by its ID.
     * 
     * @param donationId The ID of the donation to retrieve.
     * @return The Donation object, or null if not found.
     */
    public Donation getDonationById(int donationId) {
        return donationMap.get(donationId);
    }

    /**
     * Lists all donations.
     * 
     * @return A List of all Donation objects.
     */
    public List<Donation> listAllDonations() {
        Collection<Donation> donations = donationMap.values(); // Get all donations
        return new ArrayList<>(donations); // Convert to ArrayList
    }

    /**
     * Filters donations by a minimum amount.
     * 
     * @param minAmount The minimum amount for filtering.
     * @return A List of Donation objects that meet the criteria.
     */
    public List<Donation> filterDonationsByAmount(double minAmount) {
        List<Donation> filteredDonations = new ArrayList<>();
        Collection<Donation> donations = donationMap.values(); // Get all donations
        for (Donation donation : donations) {
            if (donation.getAmount() >= minAmount) {
                filteredDonations.add(donation);
            }
        }
        return filteredDonations;
    }

    /**
     * Generates a report of all donations.
     * 
     * @return A formatted String report of all donations.
     */
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Donation Report\n");
        report.append("=====================\n");
        Collection<Donation> donations = donationMap.values(); // Get all donations
        for (Donation donation : donations) {
            report.append(donation.toString()).append("\n");
        }
        return report.toString();
    }
}
