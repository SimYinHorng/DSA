package utility;

/**
 * Enum to define statuses for charity events.
 * 
 * PLANNED: Represents events that are scheduled but not yet started.
 * ONGOING: Represents events that are currently in progress.
 * COMPLETED: Represents events that have concluded.
 * CANCELLED: Represents events that were cancelled before they occurred.
 * POSTPONED: Represents events that have been rescheduled to a later date.
 * 
 * Author: Terence
 */
public enum EventStatus {
    PLANNED,       // Represents events that are scheduled but not yet started.
    ONGOING,       // Represents events that are currently in progress.
    COMPLETED,     // Represents events that have concluded.
    CANCELLED,     // Represents events that were cancelled before they occurred.
    POSTPONED      // Represents events that have been rescheduled to a later date.
}
