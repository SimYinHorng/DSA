package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a Donation entity.
 */
public class Donation implements Serializable, Comparable<Donation> {

    private static int nextDonationId = 1;
    private int donationId;
    private String donorId;
    private String doneeId;
    private double amount;
    private String description;
    private LocalDate donationDate;

    public Donation() {
        this.donationId = nextDonationId++;
    }

    public Donation(String donorId, String doneeId, double amount, String description, LocalDate donationDate) {
        this.donationId = nextDonationId++;
        this.donorId = donorId;
        this.doneeId = doneeId;
        this.amount = amount;
        this.description = description;
        this.donationDate = donationDate;
    }

    public static int getNextDonationId() {
        return nextDonationId;
    }

    public static void setNextDonationId(int nextDonationId) {
        Donation.nextDonationId = nextDonationId;
    }

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public String getDoneeId() {
        return doneeId;
    }

    public void setDoneeId(String doneeId) {
        this.doneeId = doneeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(LocalDate donationDate) {
        this.donationDate = donationDate;
    }

    @Override
    public String toString() {
        return String.format("|%-10d|%-15s|%-15s|%-10.2f|%-30s|%-15s|", 
                donationId, donorId, doneeId, amount, description, 
                donationDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donation donation = (Donation) o;
        return donationId == donation.donationId &&
                Double.compare(donation.amount, amount) == 0 &&
                Objects.equals(donorId, donation.donorId) &&
                Objects.equals(doneeId, donation.doneeId) &&
                Objects.equals(description, donation.description) &&
                Objects.equals(donationDate, donation.donationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(donationId, donorId, doneeId, amount, description, donationDate);
    }

    @Override
    public int compareTo(Donation other) {
        return Integer.compare(this.donationId, other.donationId);
    }
}
