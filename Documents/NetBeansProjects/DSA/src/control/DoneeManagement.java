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
        doneeList.clear();
        Iterator keyIt = doneeMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Integer key = (Integer) keyIt.next();
            Donee donee = doneeMap.get(key);
            doneeList.add(donee);
        }

        switch (choice) {
            case 0:
                DonorManagementUI.displayExitMessage();
                break;

            case 1:
                registrationReport();
                MessageUI.enterToContinue();
                break;

            case 2:
                statusReport();
                MessageUI.enterToContinue();
                break;

            case 3:
                needsReport();
                MessageUI.enterToContinue();
                break;

            default:
                displayInvalidChoiceMessage();
                break;
        }

    }

    public void registrationReport() {
        int choice = doneeUI.showRegistrationReportMenu();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM-yyyy");
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");

        switch (choice) {
            case 0:
                DonorManagementUI.displayExitMessage();
                break;

            case 1:
                System.out.print("Enter the date for the daily report (dd-MM-yyyy): ");
                String dayInput = scanner.nextLine();
                LocalDate specificDay = null;
                try {
                    specificDay = LocalDate.parse(dayInput, formatter);
                    if (specificDay.isAfter(LocalDate.now())) {
                        throw new DateTimeParseException("Date cannot be in the future.", dayInput, 0);
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy format.");
                    return;
                }

                LinkedList<Donee> dayDonees = new LinkedList<>();
                Iterator<Donee> iterator = doneeList.iterator();
                while (iterator.hasNext()) {
                    Donee donee = iterator.next();
                    LocalDate regDate = donee.getRegistrationDate();
                    if (regDate.isEqual(specificDay)) {
                        dayDonees.add(donee);
                    }
                }

                if (dayDonees.isEmpty()) {
                    System.out.println("No donees registered on " + specificDay.format(formatter) + ".");
                } else {
                    System.out.printf("\n\n\n\n\n%-30s %-10s\n", "Daily Report for", specificDay.format(formatter));
                    System.out.println("--------------------------------------------------------------");
                    System.out.printf("%-4s %-11s %-20s %-17s\n", "No", "Donee ID", "Name", "Registration Date");
                    System.out.println("--------------------------------------------------------------");
                    Iterator<Donee> dayIterator = dayDonees.iterator();
                    int index = 1;
                    while (dayIterator.hasNext()) {
                        Donee donee = dayIterator.next();
                        System.out.printf("%-4d %-11d %-20s %-17s\n", index++, donee.getDoneeId(), donee.getName(), donee.getRegistrationDate());
                    }
                    System.out.println("--------------------------------------------------------------");
                }
                break;

            case 2:
                System.out.print("Enter the month and year for the monthly report (MM-yyyy): ");
                String monthInput = scanner.nextLine();
                LocalDate firstDayOfMonth = null;

                try {
                    firstDayOfMonth = LocalDate.parse("01-" + monthInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    if (firstDayOfMonth.isAfter(LocalDate.now())) {
                        throw new DateTimeParseException("Month cannot be in the future.", monthInput, 0);
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid month-year format. Please enter the month and year in MM-yyyy format.");
                    return;
                }

                LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());
                LinkedList<Donee> monthDonees = new LinkedList<>();
                Iterator<Donee> iterator2 = doneeList.iterator();
                while (iterator2.hasNext()) {
                    Donee donee = iterator2.next();
                    LocalDate regDate = donee.getRegistrationDate();
                    if ((regDate.isEqual(firstDayOfMonth) || regDate.isAfter(firstDayOfMonth))
                            && (regDate.isEqual(lastDayOfMonth) || regDate.isBefore(lastDayOfMonth))) {
                        monthDonees.add(donee);
                    }
                }

                if (monthDonees.isEmpty()) {
                    System.out.println("No donees registered in " + firstDayOfMonth.format(monthFormatter) + ".");
                } else {
                    System.out.printf("\n\n\n\n\n%-30s %-10s\n", "Monthly Report for", firstDayOfMonth.format(monthFormatter));
                    System.out.println("--------------------------------------------------------------");
                    System.out.printf("%-4s %-11s %-20s %-17s\n", "No", "Donee ID", "Name", "Registration Date");
                    System.out.println("--------------------------------------------------------------");
                    Iterator<Donee> monthIterator = monthDonees.iterator();
                    int index = 1;
                    while (monthIterator.hasNext()) {
                        Donee donee = monthIterator.next();
                        System.out.printf("%-4d %-11d %-20s %-17s\n", index++, donee.getDoneeId(), donee.getName(), donee.getRegistrationDate());
                    }
                    System.out.println("--------------------------------------------------------------");
                }
                break;

            case 3:
                System.out.print("Enter the year for the yearly report (yyyy): ");
                String yearInput = scanner.nextLine();
                LocalDate firstDayOfYear = null;
                try {
                    firstDayOfYear = LocalDate.parse("01-01-" + yearInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    if (firstDayOfYear.isAfter(LocalDate.now())) {
                        throw new DateTimeParseException("Year cannot be in the future.", yearInput, 0);
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid year format. Please enter the year in yyyy format.");
                    return;
                }

                LocalDate lastDayOfYear = firstDayOfYear.withDayOfYear(firstDayOfYear.lengthOfYear());
                LinkedList<Donee> yearDonees = new LinkedList<>();
                Iterator<Donee> iterator3 = doneeList.iterator();
                while (iterator3.hasNext()) {
                    Donee donee = iterator3.next();
                    LocalDate regDate = donee.getRegistrationDate();
                    if ((regDate.isEqual(firstDayOfYear) || regDate.isAfter(firstDayOfYear))
                            && (regDate.isEqual(lastDayOfYear) || regDate.isBefore(lastDayOfYear))) {
                        yearDonees.add(donee);
                    }
                }

                if (yearDonees.isEmpty()) {
                    System.out.println("No donees registered in " + firstDayOfYear.format(yearFormatter) + ".");
                } else {
                    System.out.printf("\n\n\n\n\n%-30s %-10s\n", "Yearly Report for", firstDayOfYear.format(yearFormatter));
                    System.out.println("--------------------------------------------------------------");
                    System.out.printf("%-4s %-11s %-20s %-17s\n", "No", "Donee ID", "Name", "Registration Date");
                    System.out.println("--------------------------------------------------------------");
                    Iterator<Donee> yearIterator = yearDonees.iterator();
                    int index = 1;
                    while (yearIterator.hasNext()) {
                        Donee donee = yearIterator.next();
                        System.out.printf("%-4d %-11d %-20s %-17s\n", index++, donee.getDoneeId(), donee.getName(), donee.getRegistrationDate());
                    }
                    System.out.println("--------------------------------------------------------------");
                }
                break;

            default:
                displayInvalidChoiceMessage();
                break;
        }

    }

    public void statusReport() {
        LinkedList<Donee> resultList = null;
        String status = null;

        System.out.println("Select Status: ");
        System.out.println("1. Active ");
        System.out.println("2. Inactive");
        System.out.println("3. Suspended");

        if (scanner.hasNextInt()) {
            int statusChoice = scanner.nextInt();
            scanner.nextLine();

            switch (statusChoice) {
                case 1:
                    status = "ACTIVE";
                    break;

                case 2:
                    status = "INACTIVE";
                    break;

                case 3:
                    status = "SUSPENDED";
                    break;

                default:
                    displayInvalidChoiceMessage();
                    break;
            }

            resultList = searchByStatus(status);

            if (resultList.isEmpty()) {
                System.out.println("No donees found with the selected status.");
            } else {

                System.out.printf("\n\n\n\n\n%-12s %-10s\n", "Report for ", status);
                System.out.println("--------------------------------------------------------------");
                System.out.printf("%-4s %-11s %-20s %-17s\n", "No", "Donee ID", "Name", "Status");
                System.out.println("--------------------------------------------------------------");

                Iterator<Donee> iterator = resultList.iterator();
                int index = 1;
                while (iterator.hasNext()) {
                    Donee donee = iterator.next();
                    System.out.printf("%-4d %-11d %-20s %-17s\n", index++, donee.getDoneeId(), donee.getName(), donee.getStatus());
                }

                System.out.println("--------------------------------------------------------------");

            }
        } else {
            displayInvalidChoiceMessage();
            scanner.next();
        }

    }

    private LinkedList<Donee> searchByStatus(String status) {
        LinkedList<Donee> resultList = new LinkedList<>();
        Iterator<Integer> keyIt = doneeMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Donee donee = doneeMap.get(keyIt.next());
            if (donee.getStatus().toString().equalsIgnoreCase(status)) {
                resultList.add(donee);
            }
        }
        return resultList;
    }

    public void needsReport() {
        System.out.print("Enter search prompt for needs: ");
        String needsDescription = scanner.nextLine().trim().toLowerCase();

        LinkedList<Donee> resultList = new LinkedList<>();
        Iterator<Integer> keyIt = doneeMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Donee donee = doneeMap.get(keyIt.next());
            if (donee.getNeedsDescription().toLowerCase().contains(needsDescription.toLowerCase())) {
                resultList.add(donee);
            }
        }

        if (!resultList.isEmpty()) {

            System.out.printf("\n\n\n\n\n%-12s %-10s\n", "Report for", needsDescription.toUpperCase());
            System.out.println("--------------------------------------------------------------");
            System.out.printf("%-4s %-11s %-20s %-20s\n", "No", "Donee ID", "Name", "Description");
            System.out.println("--------------------------------------------------------------");

            Iterator<Donee> resultIt = resultList.iterator();
            int index = 1;
            while (resultIt.hasNext()) {

                Donee donee = resultIt.next();
                System.out.printf("%-4d %-11d %-20s %-20s\n", index++, donee.getDoneeId(), donee.getName(), donee.getNeedsDescription());
            }
            System.out.println("--------------------------------------------------------------");

        } else {
            System.out.println("No donees found with matching needs.");
        }
    }

    public static void main(String[] args) {
        DoneeManagement mainDonee = new DoneeManagement();

        mainDonee.runDoneeManagement();

    }

}
