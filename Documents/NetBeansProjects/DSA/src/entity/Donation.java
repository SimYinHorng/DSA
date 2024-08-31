package entity;

import java.io.Serializable;
import java.time.LocalDate;
import adt.ArrayList;  // Import your custom ArrayList

public class Donation implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int nextDonationId = 1000;
    private int donationId;
    private Donor donor;
    private LocalDate donationDate;
    private ArrayList<DonationItem> items; // Use your custom ArrayList

    public Donation(Donor donor, LocalDate donationDate) {
        this.donationId = nextDonationId++;
        this.donor = donor;
        this.donationDate = donationDate;
        this.items = new ArrayList<>();
    }

    // Getter for donationId
    public int getDonationId() {
        return donationId;
    }

    // Setter for donationId
    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public LocalDate getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(LocalDate donationDate) {
        this.donationDate = donationDate;
    }

    public ArrayList<DonationItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<DonationItem> items) {
        this.items = items;
    }
    
    public void addItem(DonationItem item) {
        items.add(item);
    }
    
    public void removeItem(DonationItem item) {
        for (int i = 1; i <= items.getNumberOfEntries(); i++) { // Iterate using 1-based index
            if (items.getEntry(i).equals(item)) {
                items.remove(i);
                break;
            }
        }
    }

    public double getTotalValue() {
        double total = 0;
        for (int i = 1; i <= items.getNumberOfEntries(); i++) { // Use 1-based index
            total += items.getEntry(i).getValue(); // Ensure getValue() method is implemented in DonationItem
        }
        return total;
    }

    @Override
    public String toString() {
        return String.format("Donation ID: %d, Donor: %s, Date: %s, Total Value: %.2f", 
                             donationId, donor.getName(), donationDate, getTotalValue());
    }
}
