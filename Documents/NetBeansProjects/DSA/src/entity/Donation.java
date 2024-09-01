package entity;

import java.io.Serializable;
import java.time.LocalDate;
import adt.ArrayList;  

public class Donation implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int nextDonationId = 1000;
    private int donationId;
    private Donor donor;
    private LocalDate donationDate;
    private ArrayList<DonationItem> items; 

    public Donation(Donor donor, LocalDate donationDate) {
        this.donationId = nextDonationId++;
        this.donor = donor;
        this.donationDate = donationDate;
        this.items = new ArrayList<>();
    }

    
    public int getDonationId() {
        return donationId;
    }

    
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
        for (int i = 1; i <= items.getNumberOfEntries(); i++) { 
            if (items.getEntry(i).equals(item)) {
                items.remove(i);
                break;
            }
        }
    }

    public double getTotalValue() {
        double total = 0;
        for (int i = 1; i <= items.getNumberOfEntries(); i++) { 
            total += items.getEntry(i).getValue(); 
        }
        return total;
    }

    @Override
    public String toString() {
        return String.format("Donation ID: %d, Donor: %s, Date: %s, Total Value: %.2f", 
                             donationId, donor.getName(), donationDate, getTotalValue());
    }
}
