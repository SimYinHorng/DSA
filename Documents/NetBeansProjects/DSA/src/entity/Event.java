package entity;

import adt.ArrayList;
import java.io.Serializable;
import java.util.Objects;
import utility.EventType;

/**
 * Represents an event in the charity event management system.
 * 
 * Author: Terence
 */
public class Event implements Serializable{

    private static int nextEventId = 1;
    private String eventId;
    private String eventName;
    private String eventAddress;
    private String eventStartDate;
    private String eventEndDate;
    private String eventStartTime;
    private String eventEndTime;
    private String eventOrganizerName;
    private String eventOrganizerEmail;
    private String eventOrganizerPhoneNo;
    private EventType eventType;
    private ArrayList<Volunteer> participantList;

    public Event() {
        this.eventId = generateEventId();
    }
      // Method to generate the formatted event ID
    private String generateEventId() {
        return String.format("E%04d", nextEventId++); // e0001, e0002, etc.
    }


    public Event(String eventName, String eventAddress, String eventStartDate, String eventEndDate,
                 String eventStartTime, String eventEndTime, String eventOrganizerName,
                 String eventOrganizerEmail, String eventOrganizerPhoneNo, EventType eventType) {
        this.eventId = generateEventId();
        this.eventName = eventName;
        this.eventAddress = eventAddress;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventOrganizerName = eventOrganizerName;
        this.eventOrganizerEmail = eventOrganizerEmail;
        this.eventOrganizerPhoneNo = eventOrganizerPhoneNo;
        this.eventType = eventType;
        this.participantList = new ArrayList<>();
    }

    // Getters and Setters
    public static int getNextEventId() {
        return nextEventId;
    }

    public static void setNextEventId(int nextEventId) {
        Event.nextEventId = nextEventId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(String eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public String getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(String eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getEventOrganizerName() {
        return eventOrganizerName;
    }

    public void setEventOrganizerName(String eventOrganizerName) {
        this.eventOrganizerName = eventOrganizerName;
    }

    public String getEventOrganizerEmail() {
        return eventOrganizerEmail;
    }

    public void setEventOrganizerEmail(String eventOrganizerEmail) {
        this.eventOrganizerEmail = eventOrganizerEmail;
    }

    public String getEventOrganizerPhoneNo() {
        return eventOrganizerPhoneNo;
    }

    public void setEventOrganizerPhoneNo(String eventOrganizerPhoneNo) {
        this.eventOrganizerPhoneNo = eventOrganizerPhoneNo;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public ArrayList<Volunteer> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(ArrayList<Volunteer> participantList) {
        this.participantList = participantList;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-30s %-15s %-15s %-10s %-10s %-20s%n", 
            eventId, eventName, eventAddress, eventStartDate, eventEndDate, eventStartTime, 
            eventEndTime, eventOrganizerName, eventOrganizerEmail, eventOrganizerPhoneNo, eventType, 
            (!participantList.isEmpty() ? participantList.getNumberOfEntries() : 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return eventId == event.eventId &&
               Objects.equals(eventName, event.eventName) &&
               Objects.equals(eventAddress, event.eventAddress) &&
               Objects.equals(eventStartDate, event.eventStartDate) &&
               Objects.equals(eventEndDate, event.eventEndDate) &&
               Objects.equals(eventStartTime, event.eventStartTime) &&
               Objects.equals(eventEndTime, event.eventEndTime) &&
               Objects.equals(eventOrganizerName, event.eventOrganizerName) &&
               Objects.equals(eventOrganizerEmail, event.eventOrganizerEmail) &&
               Objects.equals(eventOrganizerPhoneNo, event.eventOrganizerPhoneNo) &&
               eventType == event.eventType;
    }
}
