package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import utility.EventType;

/**
 * Represents an event in the charity event management system.
 *
 * Author: Terence
 */
public class Event implements Serializable {

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
    private String eventStatus;
    private EventType eventType;
    private int volunteerNeed;
    private int availableVolunteerNeeded;
    private LocalDateTime lastEditTime;

    public Event() {
        this.eventId = generateEventId();
        this.lastEditTime = LocalDateTime.now();
    }

    public Event(String eventName, String eventAddress, LocalDate eventStartDate, LocalDate eventEndDate,
            LocalTime eventStartTime, LocalTime eventEndTime, String eventDescription,
            String eventOrganizerName, String eventOrganizerEmail, String eventOrganizerPhoneNo,
            String eventStatus, EventType eventType, int volunteerNeed, int availableVolunteerNeeded) {
        this.eventId = generateEventId();
        this.eventName = validateNonEmpty(eventName, "Event name");
        this.eventAddress = validateNonEmpty(eventAddress, "Event address");
        this.eventStartDate = Objects.requireNonNull(eventStartDate, "Event start date cannot be null");
        this.eventEndDate = Objects.requireNonNull(eventEndDate, "Event end date cannot be null");
        this.eventStartTime = Objects.requireNonNull(eventStartTime, "Event start time cannot be null");
        this.eventEndTime = Objects.requireNonNull(eventEndTime, "Event end time cannot be null");
        this.eventDescription = validateNonEmpty(eventDescription, "Event description");
        this.eventOrganizerName = validateNonEmpty(eventOrganizerName, "Event organizer name");
        this.eventOrganizerEmail = validateNonEmpty(eventOrganizerEmail, "Event organizer email");
        this.eventOrganizerPhoneNo = validateNonEmpty(eventOrganizerPhoneNo, "Event organizer phone number");
        this.eventStatus = validateNonEmpty(eventStatus, "Event status");
        this.eventType = Objects.requireNonNull(eventType, "Event type cannot be null");
        this.volunteerNeed = volunteerNeed;
        this.availableVolunteerNeeded = availableVolunteerNeeded;
        this.lastEditTime = LocalDateTime.now();
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
        this.eventName = validateNonEmpty(eventName, "Event name");
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = validateNonEmpty(eventAddress, "Event address");
    }

    public LocalDate getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(LocalDate eventStartDate) {
        this.eventStartDate = Objects.requireNonNull(eventStartDate, "Event start date cannot be null");
    }

    public LocalDate getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(LocalDate eventEndDate) {
        this.eventEndDate = Objects.requireNonNull(eventEndDate, "Event end date cannot be null");
    }

    public LocalTime getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(LocalTime eventStartTime) {
        this.eventStartTime = Objects.requireNonNull(eventStartTime, "Event start time cannot be null");
    }

    public LocalTime getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(LocalTime eventEndTime) {
        this.eventEndTime = Objects.requireNonNull(eventEndTime, "Event end time cannot be null");
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = validateNonEmpty(eventDescription, "Event description");
    }

    public String getEventOrganizerName() {
        return eventOrganizerName;
    }

    public void setEventOrganizerName(String eventOrganizerName) {
        this.eventOrganizerName = validateNonEmpty(eventOrganizerName, "Event organizer name");
    }

    public String getEventOrganizerEmail() {
        return eventOrganizerEmail;
    }

    public void setEventOrganizerEmail(String eventOrganizerEmail) {
        this.eventOrganizerEmail = validateNonEmpty(eventOrganizerEmail, "Event organizer email");
    }

    public String getEventOrganizerPhoneNo() {
        return eventOrganizerPhoneNo;
    }

    public void setEventOrganizerPhoneNo(String eventOrganizerPhoneNo) {
        this.eventOrganizerPhoneNo = validateNonEmpty(eventOrganizerPhoneNo, "Event organizer phone number");
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = validateNonEmpty(eventStatus, "Event status");
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = Objects.requireNonNull(eventType, "Event type cannot be null");
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

    private String validateNonEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty.");
        }
        return value;
    }

    @Override
    public String toString() {
        return String.format("Event ID: %-10s | Name: %-20s | Address: %-30s | Start Date: %-15s | End Date: %-15s | Start Time: %-10s | End Time: %-10s | Description: %-50s | Organizer: %-20s | Email: %-20s | Phone: %-10s | Status: %-10s | Type: %-10s | Volunteers Needed: %-3d | Available Volunteers: %-3d",
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
                && eventType == event.eventType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventName, eventAddress, eventStartDate, eventEndDate,
                eventStartTime, eventEndTime, eventDescription, eventOrganizerName, eventOrganizerEmail,
                eventOrganizerPhoneNo, eventStatus, eventType, volunteerNeed, availableVolunteerNeeded);
    }
}
