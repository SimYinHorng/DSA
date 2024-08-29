
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.HashMap;
import entity.Donor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author user
 */
public class DonorDAO {

    private static String fileName = "src/data/donor.dat";

    private static String idFileName = "src/data/donorId.dat";

    public void saveToFile(HashMap<Integer, Donor> donorMap) {
        File file = new File(fileName);
        File idFile = new File(idFileName);
        try {

            FileOutputStream foStream = new FileOutputStream(file);
            ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
            ooStream.writeObject(donorMap);
            ooStream.close();

            ObjectOutputStream idStream = new ObjectOutputStream(new FileOutputStream(idFile));
            idStream.writeInt(Donor.getNextDonorId());
            idStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public HashMap<Integer, Donor> retrieveFromFile() {
        File file = new File(fileName);
        File idFile = new File(idFileName);
        HashMap<Integer, Donor> donorMap = new HashMap<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            donorMap = (HashMap<Integer, Donor>) (oiStream.readObject());
            oiStream.close();

            ObjectInputStream idStream = new ObjectInputStream(new FileInputStream(idFile));
            Donor.setNextDonorId(idStream.readInt());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        }
        return donorMap;

    }
}
