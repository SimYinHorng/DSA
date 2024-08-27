/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.HashMap;
import entity.Volunteer;
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
public class VolunteerDAO {
    private static String fileName = "src/data/volunteer.dat";

    private static String idFileName = "src/data/volunteerId.dat";

    public void saveToFile(HashMap<Integer, Volunteer> volunteerMap) {
        File file = new File(fileName);
        File idFile = new File(idFileName);
        try {

            FileOutputStream foStream = new FileOutputStream(file);
            ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
            ooStream.writeObject(volunteerMap);
            ooStream.close();

            ObjectOutputStream idStream = new ObjectOutputStream(new FileOutputStream(idFile));
            idStream.writeInt(Volunteer.getNextVolunteerId());
            idStream.close();
//            System.out.println("Donor map saved successfully.");
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public HashMap<Integer, Volunteer> retrieveFromFile() {
        File file = new File(fileName);
        File idFile = new File(idFileName);
        HashMap<Integer, Volunteer> volunteerMap = new HashMap<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            volunteerMap = (HashMap<Integer, Volunteer>) (oiStream.readObject());
            oiStream.close();

            ObjectInputStream idStream = new ObjectInputStream(new FileInputStream(idFile));
            Volunteer.setNextVolunteerId(idStream.readInt());
            oiStream.close();

//            System.out.println("Donor map retrieved successfully.");
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        }
        return volunteerMap;

    }
}
