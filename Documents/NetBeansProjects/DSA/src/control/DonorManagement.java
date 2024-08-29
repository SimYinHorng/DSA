/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.HashMap;
import boundary.DonorManagementUI;
import dao.DonorDAO;
import entity.Donor;
import utility.DonorCategory;
import utility.DonorType;
import static utility.MessageUI.displayInvalidChoiceMessage;
import static utility.MessageUI.enterToContinue;

/**
 *
 * @author user
 */
public class DonorManagement {

    private HashMap<Integer, Donor> donorMap = new HashMap<>();
    private DonorDAO donorDAO = new DonorDAO();
    private DonorManagementUI donorUI = new DonorManagementUI();

    public DonorManagement() {
        donorMap = donorDAO.retrieveFromFile();
    }

    public void runDonorManagement() {
        int choice = 0;
        do {
            choice = donorUI.getMenuChoice();
            switch (choice) {
                case 0:
                    DonorManagementUI.displayExitMessage();
                    break;
                case 1:
                    addNewDonor();
                    break;
                case 2:
                    removeDonor();
                    break;
                case 3:
                    updateDonorDetails();
                    break;
                default:
                    displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    public void addNewDonor() {
        boolean exit = false;
        Donor newDonor = donorUI.inputDonorDetails();
        do {
            donorUI.displayDonorDetails(newDonor);
            System.out.println("Are the donor details correct?");
            int input = donorUI.confirmationMessage();
            switch (input) {
                case 1:
                    donorMap.put(newDonor.getDonorId(), newDonor);
                    donorDAO.saveToFile(donorMap);
                    exit = true;
                    break;
                case 2:
                    int choice = donorUI.getEditMenu();
                    switch (choice) {
                        case 1:
                            String name = donorUI.inputDonorName();
                            newDonor.setName(name);
                            break;
                        case 2:
                            String email = donorUI.inputDonorEmail();
                            newDonor.setEmail(email);
                            break;
                        case 3:
                            String phoneNo = donorUI.inputDonorPhoneNo();
                            newDonor.setPhoneNo(phoneNo);
                            break;
                        case 4:
                            String address = donorUI.inputDonorAddress();
                            newDonor.setAddress(address);
                            break;
                        case 5:
                            DonorType type = donorUI.inputDonorType();
                            newDonor.setType(type);
                            break;
                        case 6:
                            DonorCategory cat = donorUI.inputDonorCat();
                            newDonor.setCategory(cat);
                            break;
                    }
                    break;
                case 0:
                    exit = true;
                    break;
            }

        } while (!exit);
    }

    public void displayDonors() {
        donorUI.listAllDonors(donorMap);
        enterToContinue();
    }

    public static void main(String[] args) {
        DonorManagement productMaintenance = new DonorManagement();
        productMaintenance.runDonorManagement();
    }

    private void removeDonor() {
        boolean exit = false;
        do {
            donorUI.listAllDonors(donorMap);
            System.out.println("Select Donor To Delete (0 to exit)");
            int removeId = donorUI.inputDonorID();
            if (removeId == 0) {
                exit = true;
            } else {
                //need validation for valid donor
                Donor deleteDonor = donorMap.get(removeId);
                donorUI.displayDonorDetails(deleteDonor);
                System.out.println("Confirm Delete this Donor? ");
                int confirm = donorUI.confirmationMessage();
                switch (confirm) {
                    case 1:
                        donorMap.remove(removeId);
                        donorDAO.saveToFile(donorMap);
                        break;
                    case 2:
                        break;
                    case 0:
                        exit = true;
                        break;
                }
            }

        } while (!exit);

    }

    private void updateDonorDetails() {
        boolean exit = false;
        do {
            donorUI.listAllDonors(donorMap);
            System.out.println("Select Donor To Edit (0 to exit)");
            int editId = donorUI.inputDonorID();
            if (editId == 0) {
                exit = true;
            } else {
                Donor editDonor = donorMap.get(editId);
                boolean stopEdit = false;
                while (!stopEdit) {
                    donorUI.displayDonorDetails(editDonor);
                    int edit = donorUI.getEditMenu();
                    switch (edit) {
                        case 1:
                            String name = donorUI.inputDonorName();
                            editDonor.setName(name);
                            break;
                        case 2:
                            String email = donorUI.inputDonorEmail();
                            editDonor.setEmail(email);
                            break;
                        case 3:
                            String phoneNo = donorUI.inputDonorPhoneNo();
                            editDonor.setPhoneNo(phoneNo);
                            break;
                        case 4:
                            String address = donorUI.inputDonorAddress();
                            editDonor.setAddress(address);
                            break;
                        case 5:
                            DonorType type = donorUI.inputDonorType();
                            editDonor.setType(type);
                            break;
                        case 6:
                            DonorCategory cat = donorUI.inputDonorCat();
                            editDonor.setCategory(cat);
                            break;
                        case 0:
                            stopEdit = true;
                            break;
                    }
                    donorUI.displayDonorDetails(editDonor);
                    System.out.println("Are the details correct?");
                    int confirm = donorUI.confirmationMessage();
                    switch (confirm) {
                        case 1:
                            donorMap.put(editDonor.getDonorId(), editDonor);
                            donorDAO.saveToFile(donorMap);
                            break;
                        case 2:
                            break;
                        case 0:
                            exit = true;
                            break;
                    }
                }

            }
        } while (!exit);
    }
}
