package control;

import adt.ArrayList;
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
    Scanner scanner = new Scanner(System.in);

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
        ArrayList<Donee> resultList = doneeUI.showDoneeSearchMenu(doneeMap);
        Donee selectedDonee = selectDoneeFromResults(resultList);
        int choice = doneeUI.selectDoneeAction();

        switch (choice) {
            case 0:
                break;

            case 1:
                editDonee(selectedDonee);
                break;

            case 2:
                removeDonee(selectedDonee);
                break;

            case 3:
                viewAllDonations(selectedDonee);

                break;

            default:
                displayInvalidChoiceMessage();
                break;
        }

    }

    public void removeDonee(Donee donee) {
        //tmr do

    }

    public void viewAllDonations(Donee donee) {

    }

    public Donee selectDoneeFromResults(ArrayList<Donee> doneeList) {
        if (doneeList.isEmpty()) {
            System.out.println("No donee found.");
            return null;
        }

        System.out.println("\nWhat would you like to do with the selected donee?");
        for (int i = 0; i < doneeList.getNumberOfEntries(); i++) {
            System.out.println((i + 1) + ". " + doneeList.getEntry(i).toString());
        }

        int selection = -1;
        do {
            System.out.print("Enter the number corresponding to the donee: ");
            if (scanner.hasNextInt()) {
                selection = scanner.nextInt();
                scanner.nextLine();
                if (selection > 0 && selection <= doneeList.getNumberOfEntries()) {
                    return doneeList.getEntry(selection - 1);
                } else {
                    displayInvalidChoiceMessage();
                }
            } else {
                displayInvalidChoiceMessage();
                scanner.next();
            }
        } while (selection < 1 || selection > doneeList.getNumberOfEntries());

        return null;
    }

    public static void main(String[] args) {
        DoneeManagement mainDonee = new DoneeManagement();

        mainDonee.runDoneeManagement();

    }

}
