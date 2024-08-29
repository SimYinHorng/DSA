package dao;

import adt.HashMap;
import entity.Donation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Data Access Object for managing Donation entities.
 */
public class DonationDAO {

    private static String fileName = "src/data/donation.dat";  // File to store Donation objects
    private static String idFileName = "src/data/donationId.dat";  // File to store next Donation ID

    /**
     * Saves the Donation HashMap to the file.
     * 
     * @param donationMap The HashMap containing Donation objects.
     */
    public void saveToFile(HashMap<Integer, Donation> donationMap) {
        File file = new File(fileName);
        File idFile = new File(idFileName);
        try {
            // Save the donation map
            FileOutputStream foStream = new FileOutputStream(file);
            ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
            ooStream.writeObject(donationMap);
            ooStream.close();

            // Save the next donation ID
            ObjectOutputStream idStream = new ObjectOutputStream(new FileOutputStream(idFile));
            idStream.writeInt(Donation.getNextDonationId());
            idStream.close();

        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    /**
     * Retrieves the Donation HashMap from the file.
     * 
     * @return A HashMap containing Donation objects.
     */
    public HashMap<Integer, Donation> retrieveFromFile() {
        File file = new File(fileName);
        File idFile = new File(idFileName);
        HashMap<Integer, Donation> donationMap = new HashMap<>();  // Initialize an empty HashMap
        try {
            // Retrieve the donation map
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            donationMap = (HashMap<Integer, Donation>) (oiStream.readObject());
            oiStream.close();

            // Retrieve the next donation ID
            ObjectInputStream idStream = new ObjectInputStream(new FileInputStream(idFile));
            Donation.setNextDonationId(idStream.readInt());
            idStream.close();

        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        }
        return donationMap;
    }
}
 