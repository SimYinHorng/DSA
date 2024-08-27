package entity;

import adt.ArrayList;
import java.io.Serializable;
import java.util.Objects;
import utility.DoneeCategory; 

public class Donee implements Serializable {

    private static int nextDoneeId = 100;
    private int doneeId;
    private String name;
    private String email;
    private String phoneNo;
    private String address;
    private DoneeCategory category; 
    private ArrayList<Donation> donationList;

    public Donee() {
        this.doneeId = nextDoneeId++;
    }

    public Donee(String name, String email, String phoneNo, String address, DoneeCategory category) {
        this.doneeId = nextDoneeId++;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.category = category;
        this.donationList = new ArrayList<>();
    }

    public Donee(String name, String email, String phoneNo, String address, DoneeCategory category, ArrayList<Donation> donationList) {
        this.doneeId = nextDoneeId++;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.category = category;
        this.donationList = donationList;
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

    public ArrayList<Donation> getDonationList() {
        return donationList;
    }

    public void setDonationList(ArrayList<Donation> donationList) {
        this.donationList = donationList;
    }

    @Override
    public String toString() {
        return String.format("|%-8d|%-25s|%-30s|%-15s|%-80s|%-12s|%15d|", 
                             doneeId, name, email, phoneNo, address, category, 
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
        return Objects.equals(doneeId, donee.doneeId)
                && Objects.equals(name, donee.name)
                && Objects.equals(email, donee.email)
                && Objects.equals(phoneNo, donee.phoneNo)
                && Objects.equals(address, donee.address)
                && Objects.equals(category, donee.category); 
    }
}
