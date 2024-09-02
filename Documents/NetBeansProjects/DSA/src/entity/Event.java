package entity;

import adt.LinkedList;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import utility.EventStatus;
import utility.EventType;

/**
 *
 * @author Terence Goh Poh Xian
 */

public class Event implements Serializable {
 private static final long serialVersionUID = 1L;
    private static int nextEventId = 1;
    private String eventId;
    private String eventName;
    private String eventAddress;
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
    private LocalTime eventStartTime;
    private LocalTime eventEndTime;
    private String eventDescription;
    private String eventOrganizerName;
    private String eventOrganizerEmail;
    private String eventOrganizerPhoneNo;
    private EventStatus eventStatus;
    private EventType eventType;
    private int volunteerNeed;
    private int availableVolunteerNeeded;
    private LocalDateTime lastEditTime;
    private LinkedList<Integer> participantList; // List to store volunteer IDs

    
    public Event() {
        this.eventId = generateEventId();
        this.lastEditTime = LocalDateTime.now();
        this.participantList = new LinkedList<>(); // Initialize participant list
    }

    public Event(String eventName, String eventAddress, LocalDate eventStartDate, LocalDate eventEndDate,
            LocalTime eventStartTime, LocalTime eventEndTime, String eventDescription,
            String eventOrganizerName, String eventOrganizerEmail, String eventOrganizerPhoneNo,
            EventStatus eventStatus, EventType eventType, int volunteerNeed, int availableVolunteerNeeded) {
        this.eventId = generateEventId();
        this.eventName = eventName;
        this.eventAddress = eventAddress;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventDescription = eventDescription;
        this.eventOrganizerName = eventOrganizerName;
        this.eventOrganizerEmail = eventOrganizerEmail;
        this.eventOrganizerPhoneNo = eventOrganizerPhoneNo;
        this.eventStatus = eventStatus;
        this.eventType = eventType;
        this.volunteerNeed = volunteerNeed;
        this.availableVolunteerNeeded = availableVolunteerNeeded;
        this.lastEditTime = LocalDateTime.now();
        this.participantList = new LinkedList<>(); // Initialize participant list
    }

    private String generateEventId() {
        return String.format("E%04d", nextEventId++); // e0001, e0002, etc.
    }

    public static int getNextEventId() {
        return nextEventId;
    }

    public static void setNextEventId(int nextEventId) {
        Event.nextEventId = nextEventId;
    }

    // Getters and Setters
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

    public LocalDate getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(LocalDate eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public LocalDate getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(LocalDate eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public LocalTime getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(LocalTime eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public LocalTime getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(LocalTime eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
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

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public int getVolunteerNeed() {
        return volunteerNeed;
    }

    public void setVolunteerNeed(int volunteerNeed) {
        this.volunteerNeed = volunteerNeed;
    }

    public int getAvailableVolunteerNeeded() {
        return availableVolunteerNeeded;
    }

    public void setAvailableVolunteerNeeded(int availableVolunteerNeeded) {
        this.availableVolunteerNeeded = availableVolunteerNeeded;
    }

    public LocalDateTime getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(LocalDateTime lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public LinkedList<Integer> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(LinkedList<Integer> participantList) {
        this.participantList = participantList;
    }


    @Override
    public String toString() {
        return String.format("| %-10s | %-45s | %-45s | %-12s | %-12s | %-12s | %-12s | %-66s | %-30s | %-30s | %-15s | %-10s | %-15s | %-20s | %-20s |",
                eventId, eventName, eventAddress, eventStartDate, eventEndDate, eventStartTime,
                eventEndTime, eventDescription, eventOrganizerName, eventOrganizerEmail, eventOrganizerPhoneNo, eventStatus,
                eventType, volunteerNeed, availableVolunteerNeeded);
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
        return volunteerNeed == event.volunteerNeed
                && availableVolunteerNeeded == event.availableVolunteerNeeded
                && Objects.equals(eventId, event.eventId)
                && Objects.equals(eventName, event.eventName)
                && Objects.equals(eventAddress, event.eventAddress)
                && Objects.equals(eventStartDate, event.eventStartDate)
                && Objects.equals(eventEndDate, event.eventEndDate)
                && Objects.equals(eventStartTime, event.eventStartTime)
                && Objects.equals(eventEndTime, event.eventEndTime)
                && Objects.equals(eventDescription, event.eventDescription)
                && Objects.equals(eventOrganizerName, event.eventOrganizerName)
                && Objects.equals(eventOrganizerEmail, event.eventOrganizerEmail)
                && Objects.equals(eventOrganizerPhoneNo, event.eventOrganizerPhoneNo)
                && Objects.equals(eventStatus, event.eventStatus)
                && eventType == event.eventType
                && Objects.equals(participantList, event.participantList); // Include participant list
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventName, eventAddress, eventStartDate, eventEndDate,
                eventStartTime, eventEndTime, eventDescription, eventOrganizerName, eventOrganizerEmail,
                eventOrganizerPhoneNo, eventStatus, eventType, volunteerNeed, availableVolunteerNeeded, participantList);
    }
}