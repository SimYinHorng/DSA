package control;

import adt.ArrayList;
import adt.HashMap;
import adt.LinkedList;
import boundary.DoneeManagementUI;
import boundary.DonorManagementUI;
import dao.DoneeDAO;
import dao.DonorDAO;
import entity.Donation;
import entity.Donee;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
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
                filterDonee();
                break;

            case 5:
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
        doneeDAO.saveToFile(doneeMap);
        enterToContinue();

    }

    public void searchDonee() {
        ArrayList<Donee> resultList = doneeUI.showDoneeSearchMenu(doneeMap);
        Donee selectedDonee = selectDoneeFromResults(resultList);

        if (selectedDonee != null) {
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

    }

    public void removeDonee(Donee donee) {
        if (donee != null && doneeMap.containsKey(donee.getDoneeId())) {
            doneeUI.displayDoneeDetails(donee);

            System.out.println("Are you sure you want to remove the donee with ID " + donee.getDoneeId() + "?");
            int confirmation = doneeUI.confirmationMessage();

            if (confirmation == 1) {
                doneeMap.remove(donee.getDoneeId());
                doneeDAO.saveToFile(doneeMap);
                System.out.println("Donee with ID " + donee.getDoneeId() + " has been successfully removed.");
            } else if (confirmation == 2) {
                System.out.println("Operation canceled. Donee was not removed.");
            } else {
                System.out.println("Exiting removal process.");
            }
        } else {
            System.out.println("Donee not found or invalid Donee.");
        }
        enterToContinue();
    }

    public void filterDonee() {

    }

    public boolean viewAllDonations(Donee donee) {
        boolean exit = false;

        doneeUI.displayDoneeDetails(donee);
        doneeUI.displayDoneeDonations(donee.getDonationList());
        enterToContinue();

        return exit;
    }

   

    public Donee selectDoneeFromResults(ArrayList<Donee> doneeList) {
        if (doneeList.isEmpty()) {
            System.out.println("No donee found.");
            return null;
        }

        int selection = -1;
        do {
            System.out.print("Enter the number corresponding to the donee: ");
            if (scanner.hasNextInt()) {
                selection = scanner.nextInt();
                scanner.nextLine();
                if (selection > 0 && selection <= doneeList.getNumberOfEntries()) {
                    return doneeList.getEntry(selection);
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
