package entity;

import adt.ArrayList;
import adt.LinkedList;
import java.io.Serializable;
import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Objects;
import utility.DoneeCategory;
import utility.DoneeStatus;

/**
 *
 * @author Chew Wei Seng
 */

public class Donee implements Serializable {

    private static int nextDoneeId = 100;
    private int doneeId;
    private String name;
    private String email;
    private String phoneNo;
    private String address;
    private DoneeCategory category;
    private LinkedList<Donation> donationList;
    private LocalDate dateOfBirth;
    private String needsDescription;
    private LocalDate registrationDate;
    private LocalDate lastAssistanceDate;
    private DoneeStatus status;

    public Donee() {
        this.doneeId = nextDoneeId++;
    }

    public Donee(String name, String email, String phoneNo, String address, DoneeCategory category, LocalDate dateOfBirth, String needsDescription, LocalDate registrationDate, DoneeStatus status) {
        this.doneeId = nextDoneeId++;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.category = category;
        this.donationList = new LinkedList<>();
        this.dateOfBirth = dateOfBirth;
        this.needsDescription = needsDescription;
        this.registrationDate = registrationDate;
        this.lastAssistanceDate = null;
        this.status = status;
    }

    public Donee(String name, String email, String phoneNo, String address, DoneeCategory category, LinkedList<Donation> donationList, LocalDate dateOfBirth, String needsDescription, LocalDate registrationDate, LocalDate lastAssistanceDate, DoneeStatus status) {
        this.doneeId = nextDoneeId++;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.category = category;
        this.donationList = donationList;
        this.dateOfBirth = dateOfBirth;
        this.needsDescription = needsDescription;
        this.registrationDate = registrationDate;
        this.lastAssistanceDate = lastAssistanceDate;
        this.status = status;
    }

    public LocalDate updateLastAssistanceDate(Donee donee) {
        LinkedList<Donation> donations = donee.getDonationList();
        LocalDate latestDate = donee.getLastAssistanceDate();
        if (donations == null || donations.isEmpty()) {
            donee.setLastAssistanceDate(null);
            return null;
        }

        Iterator<Donation> iterator = donations.iterator();

        while (iterator.hasNext()) {
            Donation donation = iterator.next();

            if (donation != null) {
                LocalDate donationDate = donation.getDonationDate();
                if (latestDate == null || donationDate.isAfter(latestDate)) {
                    latestDate = donationDate;
                }
            }

        }

        return latestDate;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNeedsDescription() {
        return needsDescription;
    }

    public void setNeedsDescription(String needsDescription) {
        this.needsDescription = needsDescription;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setLastAssistanceDate(LocalDate date) {
        this.lastAssistanceDate = date;
    }

    public LocalDate getLastAssistanceDate() {
        return lastAssistanceDate;
    }

    public DoneeStatus getStatus() {
        return status;
    }

    public void setStatus(DoneeStatus status) {
        this.status = status;
    }

    public static int getNextDoneeId() {
        return nextDoneeId;
    }

    public static void setNextDoneeId(int nextDoneeId) {
        Donee.nextDoneeId = nextDoneeId;
    }

    public int getDoneeId() {
        return doneeId;
    }

    public void setDoneeId(int doneeId) {
        this.doneeId = doneeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DoneeCategory getCategory() {
        return category;
    }

    public void setCategory(DoneeCategory category) {
        this.category = category;
    }

    public LinkedList<Donation> getDonationList() {
        return donationList;
    }

    public void setDonationList(LinkedList<Donation> donationList) {
        this.donationList = donationList;
    }

    @Override
    public String toString() {
        return String.format("|%-8d|%-15s|%-25s|%-15s|%-60s|%-12s|%-15s|%-50s|%-15s|%-17s|%-15s|%15d|",
                doneeId, name, email, phoneNo, address, category, dateOfBirth, needsDescription, registrationDate, lastAssistanceDate, status,
                (!donationList.isEmpty() ? donationList.getNumberOfEntries() : 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Donee donee = (Donee) o;
        return doneeId == donee.doneeId
                && Objects.equals(name, donee.name)
                && Objects.equals(email, donee.email)
                && Objects.equals(phoneNo, donee.phoneNo)
                && Objects.equals(address, donee.address)
                && Objects.equals(category, donee.category)
                && Objects.equals(dateOfBirth, donee.dateOfBirth)
                && Objects.equals(needsDescription, donee.needsDescription)
                && Objects.equals(registrationDate, donee.registrationDate)
                && Objects.equals(lastAssistanceDate, donee.lastAssistanceDate)
                && Objects.equals(status, donee.status);
    }
}
