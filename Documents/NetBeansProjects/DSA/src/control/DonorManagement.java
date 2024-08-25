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
                    donorUI.displayExitMessage();
                    break;
                case 1:
                    addNewDonor();
                    break;
                case 2:
                    displayDonors();
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
    }

    public static void main(String[] args) {
        DonorManagement productMaintenance = new DonorManagement();
        productMaintenance.runDonorManagement();
    }
}
