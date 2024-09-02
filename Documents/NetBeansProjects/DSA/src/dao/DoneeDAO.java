package dao;

import adt.HashMap;
import entity.Donee;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Chew Wei Seng
 */

public class DoneeDAO {

    private static String fileName = "src/data/donee.dat"; 

    private static String idFileName = "src/data/doneeId.dat"; 

    public void saveToFile(HashMap<Integer, Donee> doneeMap) { 
        File file = new File(fileName);
        File idFile = new File(idFileName);
        try {
            FileOutputStream foStream = new FileOutputStream(file);
            ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
            ooStream.writeObject(doneeMap);
            ooStream.close();

            ObjectOutputStream idStream = new ObjectOutputStream(new FileOutputStream(idFile));
            idStream.writeInt(Donee.getNextDoneeId()); 
            idStream.close();
//            System.out.println("Donee map saved successfully.");
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public HashMap<Integer, Donee> retrieveFromFile() { 
        File file = new File(fileName);
        File idFile = new File(idFileName);
        HashMap<Integer, Donee> doneeMap = new HashMap<>(); 
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            doneeMap = (HashMap<Integer, Donee>) (oiStream.readObject());
            oiStream.close();

            ObjectInputStream idStream = new ObjectInputStream(new FileInputStream(idFile));
            Donee.setNextDoneeId(idStream.readInt()); 
            idStream.close();

        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        }
        return doneeMap;

    }
}
