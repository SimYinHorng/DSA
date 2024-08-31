package dao;

import adt.HashMap;
import entity.Volunteer;
import utility.VolunteerCategory;
import utility.VolunteerGender;

public class VolunteerInitializer {

    public HashMap<Integer, Volunteer> initializeVolunteers() {
        HashMap<Integer, Volunteer> volunteerMap = new HashMap<>();

        // Sample data for initialization
        Volunteer[] volunteers = new Volunteer[]{
            new Volunteer("Alice Smith", "alice.smith@example.com", "01112345678", "456 Elm Street, Springfield", "1990-05-12", VolunteerGender.FEMALE, VolunteerCategory.HAVE_WORKING_EXPERIENCE),
            new Volunteer("Bob Johnson", "bob.johnson@example.com", "01123456789", "789 Oak Avenue, Springfield", "1985-03-22", VolunteerGender.MALE, VolunteerCategory.NO_WORKING_EXPERIENCE),
            new Volunteer("Carol White", "carol.white@example.com", "01134567890", "123 Maple Road, Springfield", "1992-07-14", VolunteerGender.FEMALE, VolunteerCategory.HAVE_WORKING_EXPERIENCE),
            new Volunteer("David Brown", "david.brown@example.com", "01145678901", "234 Pine Street, Springfield", "1988-12-05", VolunteerGender.MALE, VolunteerCategory.NO_WORKING_EXPERIENCE),
            new Volunteer("Eva Davis", "eva.davis@example.com", "01156789012", "345 Birch Lane, Springfield", "1995-01-23", VolunteerGender.FEMALE, VolunteerCategory.HAVE_WORKING_EXPERIENCE),
            new Volunteer("Frank Miller", "frank.miller@example.com", "01167890123", "456 Cedar Drive, Springfield", "1987-09-30", VolunteerGender.MALE, VolunteerCategory.NO_WORKING_EXPERIENCE),
            new Volunteer("Grace Wilson", "grace.wilson@example.com", "01178901234", "567 Redwood Blvd, Springfield", "1991-04-18", VolunteerGender.FEMALE, VolunteerCategory.HAVE_WORKING_EXPERIENCE),
            new Volunteer("Hank Moore", "hank.moore@example.com", "01189012345", "678 Willow Street, Springfield", "1983-08-11", VolunteerGender.MALE, VolunteerCategory.NO_WORKING_EXPERIENCE),
            new Volunteer("Ivy Taylor", "ivy.taylor@example.com", "01190123456", "789 Fir Avenue, Springfield", "1994-11-07", VolunteerGender.FEMALE, VolunteerCategory.HAVE_WORKING_EXPERIENCE),
            new Volunteer("Jack Anderson", "jack.anderson@example.com", "01101234567", "890 Spruce Road, Springfield", "1986-06-21", VolunteerGender.MALE, VolunteerCategory.NO_WORKING_EXPERIENCE),
            new Volunteer("Kara Thomas", "kara.thomas@example.com", "01112345679", "123 Walnut Street, Springfield", "1993-10-03", VolunteerGender.FEMALE, VolunteerCategory.HAVE_WORKING_EXPERIENCE),
            new Volunteer("Liam Scott", "liam.scott@example.com", "01123456780", "234 Chestnut Lane, Springfield", "1989-02-15", VolunteerGender.MALE, VolunteerCategory.NO_WORKING_EXPERIENCE),
            new Volunteer("Mia Harris", "mia.harris@example.com", "01134567891", "345 Pine Road, Springfield", "1996-07-28", VolunteerGender.FEMALE, VolunteerCategory.HAVE_WORKING_EXPERIENCE),
            new Volunteer("Noah Clark", "noah.clark@example.com", "01145678902", "456 Oak Drive, Springfield", "1984-09-19", VolunteerGender.MALE, VolunteerCategory.NO_WORKING_EXPERIENCE),
            new Volunteer("Olivia Lewis", "olivia.lewis@example.com", "01156789023", "567 Cedar Street, Springfield", "1992-01-12", VolunteerGender.FEMALE, VolunteerCategory.HAVE_WORKING_EXPERIENCE),
            new Volunteer("Paul Walker", "paul.walker@example.com", "01167890134", "678 Maple Avenue, Springfield", "1985-03-08", VolunteerGender.MALE, VolunteerCategory.NO_WORKING_EXPERIENCE),
            new Volunteer("Quinn Young", "quinn.young@example.com", "01178901245", "789 Birch Lane, Springfield", "1997-11-16", VolunteerGender.FEMALE, VolunteerCategory.HAVE_WORKING_EXPERIENCE),
            new Volunteer("Rachel King", "rachel.king@example.com", "01189012356", "890 Fir Drive, Springfield", "1991-12-25", VolunteerGender.FEMALE, VolunteerCategory.NO_WORKING_EXPERIENCE),
            new Volunteer("Sam Wright", "sam.wright@example.com", "01190123467", "123 Redwood Street, Springfield", "1989-06-30", VolunteerGender.MALE, VolunteerCategory.HAVE_WORKING_EXPERIENCE),
            new Volunteer("Tina Martinez", "tina.martinez@example.com", "01101234578", "234 Willow Road, Springfield", "1994-08-14", VolunteerGender.FEMALE, VolunteerCategory.NO_WORKING_EXPERIENCE),
            new Volunteer("Ursula Green", "ursula.green@example.com", "01112345680", "345 Spruce Avenue, Springfield", "1990-04-10", VolunteerGender.FEMALE, VolunteerCategory.HAVE_WORKING_EXPERIENCE),
            new Volunteer("Victor Adams", "victor.adams@example.com", "01123456781", "456 Fir Lane, Springfield", "1987-11-22", VolunteerGender.MALE, VolunteerCategory.NO_WORKING_EXPERIENCE)
        };

        for (Volunteer volunteer : volunteers) {
            volunteerMap.put(volunteer.getVolunteerId(), volunteer);
        }

        return volunteerMap;
    }

    public static void main(String[] args) {
        VolunteerInitializer volunteerInitializer = new VolunteerInitializer();
        HashMap<Integer, Volunteer> volunteerMap = volunteerInitializer.initializeVolunteers();

        // Assuming a VolunteerDAO class exists with a saveToFile method
        VolunteerDAO volunteerDAO = new VolunteerDAO();
        volunteerDAO.saveToFile(volunteerMap);
    }
}
