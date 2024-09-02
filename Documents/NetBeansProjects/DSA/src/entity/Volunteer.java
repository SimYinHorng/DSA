/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.LinkedList;
import java.io.Serializable;
import java.util.Objects;
import utility.VolunteerCategory;
import utility.VolunteerGender;

/**
 *
 * @author user
 */
public class Volunteer implements Serializable {
 private static final long serialVersionUID = 1L;
    private static int nextVolunteerId = 1000;
    private int volunteerId;
    private String name;
    private String email;
    private String phoneNo;
    private String address;
    private String dateOfBirth;
    private VolunteerGender gender;
    private VolunteerCategory category;
    private LinkedList<String> eventList;

    public Volunteer() {
        this.volunteerId = nextVolunteerId++;
        this.eventList = new LinkedList<>(); // List to store event IDs

    }

    public Volunteer(String name, String email, String phoneNo, String address, String dateOfBirth, VolunteerGender gender, VolunteerCategory category) {
        this.volunteerId = nextVolunteerId++;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.category = category;
        this.eventList = new LinkedList<>(); // List to store event IDs

    }

    public static int getNextVolunteerId() {
        return nextVolunteerId;
    }

    public static void setNextVolunteerId(int nextVolunteerId) {
        Volunteer.nextVolunteerId = nextVolunteerId;
    }

    public int getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public VolunteerGender getGender() {
        return gender;
    }

    public void setGender(VolunteerGender gender) {
        this.gender = gender;
    }

    public VolunteerCategory getCategory() {
        return category;
    }

    public void setCategory(VolunteerCategory category) {
        this.category = category;
    }

    public LinkedList<String> getEventList() {
        return eventList;
    }

    public void setEventList(LinkedList<String> eventList) {
        this.eventList = eventList;
    }

    public void addEvent(String eventId) {
        if (eventList == null) {
            eventList = new LinkedList<>();
        }
        eventList.add(eventId);
    }
    
    public void removeEvent(String eventId) {
    if (eventList == null) {
        eventList = new LinkedList<>();
    }

    // Find the position of the eventId
    int position = eventList.findPosition(eventId);

    // Remove the event if it's found
    if (position != -1) {
        eventList.remove(position);
    } else {
        System.out.println("Event ID not found in the list.");
    }
}

    
    @Override
    public String toString() {
        return String.format("| %-14d | %-25s | %-30s | %-15s | %-60s | %-16s | %-11s | %-25s | %15d |", volunteerId, name, email, phoneNo, address, dateOfBirth, gender, category, (!eventList.isEmpty() ? eventList.getNumberOfEntries() : 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Volunteer volunteer = (Volunteer) o;
        return Objects.equals(volunteerId, volunteer.volunteerId)
                && Objects.equals(name, volunteer.name)
                && Objects.equals(email, volunteer.email)
                && Objects.equals(phoneNo, volunteer.phoneNo)
                && Objects.equals(address, volunteer.address)
                && Objects.equals(dateOfBirth, volunteer.dateOfBirth)
                && Objects.equals(gender, volunteer.gender)
                && Objects.equals(category, volunteer.category)
                && Objects.equals(eventList, volunteer.eventList);

    }

}