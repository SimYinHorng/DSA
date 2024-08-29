package control;

import adt.HashMap;
import boundary.DoneeManagementUI;
import boundary.DonorManagementUI;
import dao.DoneeDAO;
import dao.DonorDAO;
import entity.Donee;
import java.util.Scanner;
import utility.DoneeCategory;
import static utility.MessageUI.displayInvalidChoiceMessage;
import static utility.MessageUI.enterToContinue;

public class DoneeManagement {

    private HashMap<Integer, Donee> doneeMap = new HashMap<>();
    private DoneeDAO doneeDAO = new DoneeDAO();
    private DoneeManagementUI doneeUI = new DoneeManagementUI();

    public DoneeManagement() {
        doneeMap = doneeDAO.retrieveFromFile();
    }

    public void runDoneeManagement() {
        int choice;

        do {
            choice = doneeUI.showDoneeManagementMenu();
            menuChoice(choice);
        } while (choice != 0);

    }

    public void menuChoice(int choice) {
        switch (choice) {
            case 0:
                DonorManagementUI.displayExitMessage();
                break;

            case 1:
                addDonee();
                break;

            case 2:
                searchDonee();
                break;

            case 3:
                listDonee();
                break;

            case 4:
                //generateReport();
                break;

            default:
                displayInvalidChoiceMessage();
                break;
        }

    }

    public void addDonee() {
        boolean exit = false;
        Donee newDonee = doneeUI.createNewDonee();

        do {

            doneeUI.displayDoneeDetails(newDonee);
            System.out.println("Are these details correct?");
            int input = doneeUI.confirmationMessage();

            switch (input) {
                case 1:
                    doneeMap.put(newDonee.getDoneeId(), newDonee);
                    doneeDAO.saveToFile(doneeMap);
                    exit = true;
                    break;

                case 2:
                    editDonee(newDonee);
                    break;

                case 0:
                    break;

                default:
                    displayInvalidChoiceMessage();
                    break;
            }

        } while (!exit);
    }

    public void editDonee(Donee donee) {
        doneeUI.showDoneeEditMenu(donee);

    }

    public void listDonee() {
        doneeUI.listAllDonees(doneeMap);
        enterToContinue();

    }

    public void searchDonee() {
        doneeUI.showDoneeSearchMenu(doneeMap);

    }

    public static void main(String[] args) {
        DoneeManagement mainDonee = new DoneeManagement();

        mainDonee.runDoneeManagement();

    }

}
