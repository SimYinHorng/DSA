package dao;

import adt.HashMap;
import entity.Event;
import java.io.*;

public class EventDAO {

    private static final String FILE_NAME = "src/data/event.dat";
    private static final String ID_FILE_NAME = "src/data/eventId.dat";

    public void saveToFile(HashMap<String, Event> eventMap) {
        try (ObjectOutputStream eventStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
             ObjectOutputStream idStream = new ObjectOutputStream(new FileOutputStream(ID_FILE_NAME))) {
            
            eventStream.writeObject(eventMap);
            idStream.writeInt(Event.getNextEventId());

        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("\nCannot save to file: " + e.getMessage());
        }
    }

    public HashMap<String, Event> retrieveFromFile() {
        HashMap<String, Event> eventMap = new HashMap<>();

        try {
            File eventFile = new File(FILE_NAME);
            File idFile = new File(ID_FILE_NAME);
            
            if (eventFile.exists()) {
                try (ObjectInputStream eventStream = new ObjectInputStream(new FileInputStream(eventFile))) {
                    eventMap = (HashMap<String, Event>) eventStream.readObject();
                }
            } else {
                System.out.println("\nEvent file does not exist. Creating a new one.");
            }
            
            if (idFile.exists()) {
                try (ObjectInputStream idStream = new ObjectInputStream(new FileInputStream(idFile))) {
                    Event.setNextEventId(idStream.readInt());
                }
            } else {
                System.out.println("\nID file does not exist. Setting default event ID.");
                Event.setNextEventId(1); // Set a default value if file is missing
            }

        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("\nCannot read from file: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("\nClass not found: " + e.getMessage());
        }

        return eventMap;
    }
}
