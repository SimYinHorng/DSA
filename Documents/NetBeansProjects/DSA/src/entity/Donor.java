/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ArrayList;
import adt.LinkedList;
import java.io.Serializable;
import java.util.Objects;
import utility.DonorCategory;
import utility.DonorType;

/**
 *
 * @author user
 */
public class Donor implements Serializable {

    private static int nextDonorId = 100;
    private int donorId;
    private String name;
    private String email;
    private String phoneNo;
    private String address;
    private DonorType type; // public, private, government
    private DonorCategory category; // individual, organization
    private LinkedList<Donation> donationList;

    public Donor() {
        this.donorId = nextDonorId++;
    }

    public Donor(String name, String email, String phoneNo, String address, DonorType type,DonorCategory category) {
        this.donorId = nextDonorId++;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.type = type;
        this.category = category;
        this.donationList = new LinkedList<>();
    }

    public Donor(String name, String email, String phoneNo, String address, DonorType type, DonorCategory category,LinkedList<Donation> donationList) {
        this.donorId = nextDonorId++;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.type = type;
        this.category = category;
        this.donationList = donationList;
    }

    public static int getNextDonorId() {
        return nextDonorId;
    }

    public static void setNextDonorId(int nextDonorId) {
        Donor.nextDonorId = nextDonorId;
    }

    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
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

    public DonorType getType() {
        return type;
    }

    public void setType(DonorType type) {
        this.type = type;
    }

    public DonorCategory getCategory() {
        return category;
    }

    public void setCategory(DonorCategory category) {
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
        return String.format("|%-8d|%-25s|%-30s|%-15s|%-80s|%-11s|%-12s|%15d|", donorId, name, email, phoneNo, address, type,category, (!donationList.isEmpty() ? donationList.getNumberOfEntries() : 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Donor donor = (Donor) o;
        return Objects.equals(donorId, donor.donorId)
                && Objects.equals(name, donor.name)
                && Objects.equals(email, donor.email)
                && Objects.equals(phoneNo, donor.phoneNo)
                && Objects.equals(address, donor.address)
                && Objects.equals(type, donor.type);
    }

}
