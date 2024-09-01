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
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Scanner;
import utility.DoneeCategory;
import utility.MessageUI;
import static utility.MessageUI.displayInvalidChoiceMessage;
import static utility.MessageUI.enterToContinue;

public class DoneeManagement {

    private HashMap<Integer, Donee> doneeMap = new HashMap<>();
    private DoneeDAO doneeDAO = new DoneeDAO();
    private DoneeManagementUI doneeUI = new DoneeManagementUI();
    private LinkedList<Donee> doneeList = new LinkedList<>();
    Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public DoneeManagement() {
        doneeMap = doneeDAO.retrieveFromFile();
    }

    public void runDoneeManagement() {
        int choice;

        do {
            MessageUI.clearScreen();
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
                MessageUI.enterToContinue();
                break;

            case 2:
                searchDonee();
                MessageUI.enterToContinue();
                break;

            case 3:
                listDonee();
                break;

            case 4:
                filterDonee();
                MessageUI.enterToContinue();
                break;

            case 5:
                generateReport();
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
        LinkedList<Donee> resultList = doneeUI.showDoneeSearchMenu(doneeMap);
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
        int choice = doneeUI.filterDoneeMenu();
        boolean flag = false;
        LinkedList<Donee> filteredDonees = new LinkedList<>();
        doneeList.clear();
        Iterator keyIt = doneeMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Integer key = (Integer) keyIt.next();
            Donee donee = doneeMap.get(key);
            doneeList.add(donee);
        }

        switch (choice) {
            case 1:

                int minAge = getValidatedAge("Enter minimum age: ");
                int maxAge = getValidatedAge("Enter maximum age: ");

                if (minAge > maxAge) {
                    System.out.println("Minimum age cannot be greater than maximum age.");
                    break;
                }

                filteredDonees = filterByAgeGroup(minAge, maxAge);
                MessageUI.displayDoneeHeader();
                doneeUI.displayDonees(filteredDonees);
                break;

            case 2:

                LocalDate startDate = getValidatedDate("Enter start date (DD-MM-YYYY): ");
                LocalDate endDate = getValidatedDate("Enter end date (DD-MM-YYYY): ");

                if (startDate.isAfter(endDate)) {
                    System.out.println("Start date cannot be after end date.");
                    break;
                }

                filteredDonees = filterByRegistrationDate(startDate, endDate);
                MessageUI.displayDoneeHeader();
                doneeUI.displayDonees(filteredDonees);
                break;

            case 3:

                int minDonation = getValidatedDonationCount("Enter minimum donation count: ");
                int maxDonation = getValidatedDonationCount("Enter maximum donation count: ");

                if (minDonation > maxDonation) {
                    System.out.println("Minimum donation count cannot be greater than maximum donation count.");
                    break;
                }

                filteredDonees = filterBetweenDonation(minDonation, maxDonation);
                MessageUI.displayDoneeHeader();
                doneeUI.displayDonees(filteredDonees);
                break;

            case 0:
                flag = true;
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

    }

    private int getValidatedAge(String prompt) {
        int age = -1;
        while (age < 0) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                scanner.nextLine(); // Clear newline
                if (age < 0) {
                    System.out.println("Age cannot be negative.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }
        return age;
    }

    private LocalDate getValidatedDate(String prompt) {
        LocalDate date = null;
        while (date == null) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                date = LocalDate.parse(input, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in DD-MM-YYYY format.");
            }
        }
        return date;
    }

    private int getValidatedDonationCount(String prompt) {
        int donationCount = -1;
        while (donationCount < 0) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                donationCount = scanner.nextInt();
                scanner.nextLine();
                if (donationCount < 0) {
                    System.out.println("Donation count cannot be negative.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
        return donationCount;
    }

    private LinkedList<Donee> filterByAgeGroup(int minAge, int maxAge) {
        LinkedList<Donee> result = new LinkedList<>();
        LocalDate today = LocalDate.now();
        Iterator<Donee> iterator = doneeList.iterator();

        while (iterator.hasNext()) {
            Donee donee = iterator.next();
            int doneeAge = today.getYear() - donee.getDateOfBirth().getYear();
            if (doneeAge >= minAge && doneeAge <= maxAge) {
                result.add(donee);
            }
        }
        return result;
    }

    private LinkedList<Donee> filterByRegistrationDate(LocalDate startDate, LocalDate endDate) {
        LinkedList<Donee> result = new LinkedList<>();
        Iterator<Donee> iterator = doneeList.iterator();

        while (iterator.hasNext()) {
            Donee donee = iterator.next();
            LocalDate regDate = donee.getRegistrationDate();
            if ((regDate.isEqual(startDate) || regDate.isAfter(startDate))
                    && (regDate.isEqual(endDate) || regDate.isBefore(endDate))) {
                result.add(donee);
            }
        }
        return result;
    }

    private LinkedList<Donee> filterBetweenDonation(int firstNum, int secondNum) {
        LinkedList<Donee> result = new LinkedList<>();
        int largerNum = Math.max(firstNum, secondNum);
        int smallerNum = Math.min(firstNum, secondNum);
        Iterator<Donee> iterator = doneeList.iterator();

        while (iterator.hasNext()) {
            Donee donee = iterator.next();
            int donationCount = donee.getDonationList().getNumberOfEntries();
            if (donationCount < largerNum && donationCount > smallerNum) {
                result.add(donee);
            }
        }
        return result;
    }

    public boolean viewAllDonations(Donee donee) {
        boolean exit = false;

        doneeUI.displayDoneeDetails(donee);
        doneeUI.displayDoneeDonations(donee.getDonationList());
        enterToContinue();

        return exit;
    }

    public Donee selectDoneeFromResults(LinkedList<Donee> doneeList) {
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

    private void generateReport() {
        int choice = doneeUI.showDoneeReportMenu();

        switch (choice) {
            case 0:
                DonorManagementUI.displayExitMessage();
                break;

            case 1:
                registrationReport();
                MessageUI.enterToContinue();
                break;

            case 2:
                //statusReport();
                MessageUI.enterToContinue();
                break;

            case 3:
                //needsReport();
                break;

            default:
                displayInvalidChoiceMessage();
                break;
        }

    }

    public void registrationReport() {
        int choice = doneeUI.showRegistrationReportMenu();

        switch (choice) {
            case 0:
                DonorManagementUI.displayExitMessage();
                break;

            case 1:
                LocalDate today = LocalDate.now();
                LinkedList<Donee> todayDonees = new LinkedList<>();

                Iterator<Donee> iterator = doneeList.iterator();

                while (iterator.hasNext()) {
                    Donee donee = iterator.next();
                    LocalDate regDate = donee.getRegistrationDate();
                    if (regDate.isEqual(today)) {
                        todayDonees.add(donee);
                    }
                }

                if (todayDonees.isEmpty()) {
                    System.out.println("No donees registered today.");
                } else {

                    System.out.printf("%-3s %-6s %-20s %-17s\n", "No", "ID", "Name", "Registration Date");
                    Iterator<Donee> todayIterator = todayDonees.iterator();
                    int index = 1;
                    while (todayIterator.hasNext()) {
                        Donee donee = todayIterator.next();
                        System.out.printf("%-3d. %-6d %-20s %-17s\n", index++, donee.getDoneeId(), donee.getName(), donee.getRegistrationDate());
                    }
                }

                break;

            case 2:

                break;

            case 3:

                break;

            default:
                displayInvalidChoiceMessage();
                break;
        }

    }

    public static void main(String[] args) {
        DoneeManagement mainDonee = new DoneeManagement();

        mainDonee.runDoneeManagement();

    }

}
