package dao;

import entity.Donation;
import adt.ArrayList;
import java.io.*;

public class DonationDAO {
    private static final String FILE_NAME = "donations.ser";

    
    public void saveDonations(ArrayList<Donation> donations) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(donations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public ArrayList<Donation> loadDonations() {
        ArrayList<Donation> donations = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                donations = (ArrayList<Donation>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return donations;
    }
}

