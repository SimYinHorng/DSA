/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.HashMap;
import boundary.DonorManagementUI;
import dao.DonorDAO;
import entity.Donor;
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
        do {
            Donor newDonor = donorUI.inputDonorDetails();
            donorUI.displayDonorDetails(newDonor);
            System.out.println("Are the donor details correct?");
            char input = donorUI.confirmationMessage();
            switch (input) {
                case 'Y':
                    donorMap.put(newDonor.getDonorId(), newDonor);
                    donorDAO.saveToFile(donorMap);
                    break;
                case'N':
                    //here need put edit wan
                    
                    break;
                case 'X':
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
