package boundary;

import adt.ArrayList;
import adt.HashMap;
import entity.Donee;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import utility.DoneeCategory;
import utility.DoneeStatus;
import utility.MessageUI;
import static utility.MessageUI.displayInvalidChoiceMessage;
import static utility.MessageUI.enterToContinue;
import static utility.MessageUI.line;

public class DoneeManagementUI {

    Scanner scanner = new Scanner(System.in);
    private HashMap<Integer, Donee> doneeMap = new HashMap<>();

    public int showDoneeManagementMenu() {

        boolean flag = false;
        int choice;

        do {
            System.out.println("Welcome to the Donee Management Module!");
            System.out.println("1. Add a new Donee");
            System.out.println("2. Search Donee");
            System.out.println("3. List Donee");
            System.out.println("4. Filter Donee");
            System.out.println("5. Generate Report");
            System.out.println("0. Exit");
            System.out.println("What would you like to do?");
            choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            if (choice >= 0 && choice <= 4) {
                flag = true;
            } else {
                displayInvalidChoiceMessage();
            }

        } while (!flag);

        return choice;

    }

    public Donee createNewDonee() {
        ArrayList<Donee> doneeList;

        String name = inputName();
        String email = inputEmail();
        String phoneNo = inputPhoneNo();
        String address = inputAddress();
        DoneeCategory category = inputCategory();
        DoneeStatus status = inputStatus();
        LocalDate dateOfBirth = inputDOB();
        String needsDescription = inputNeedsDesc();
        LocalDate registrationDate = LocalDate.now();
        Donee newDonee = new Donee(name, email, phoneNo, address, category, dateOfBirth, needsDescription, registrationDate, status);

        return newDonee;

    }

    public String inputName() {
        boolean validName = false;
        String regex = "^[a-zA-Z\\s]+$|^[a-zA-Z0-9\\s&.,'-]+$";

        String name;
        do {
            System.out.print("Enter Donee name: ");
            name = scanner.nextLine();
            if (name.matches(regex)) {
                validName = true;
            } else {
                System.out.println("Invalid Name or Company Name!!!");
                enterToContinue();
            }
        } while (!validName);

        return name;
    }

    public String inputEmail() {
        String email;
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        do {
            System.out.print("Enter Donee Email: ");
            email = scanner.nextLine();

            if (!email.matches(regex)) {
                System.out.println("Invalid Email!!!");
                enterToContinue();
            }
        } while (!email.matches(regex));

        return email;
    }

    public String inputPhoneNo() {
        String phoneNo;
        String regex = "^(0\\d-\\d{8}|01\\d-\\d{8})$";
        do {
            System.out.println("Example: (01x-xxxxxxxx)/ (0x-xxxxxxxx)");
            System.out.print("Enter Donee Phone No : ");
            phoneNo = scanner.nextLine();
            if (!phoneNo.matches(regex)) {
                System.out.println("Invalid Phone Number!!!");
                enterToContinue();
            }
        } while (!phoneNo.matches(regex));

        return phoneNo;
    }

    public String inputAddress() {
        System.out.print("Enter Donee Address: ");
        String address = scanner.nextLine();
        return address;
    }

    public LocalDate inputDOB() {
        LocalDate dateOfBirth = null;
        while (dateOfBirth == null) {
            System.out.print("Enter Date of Birth (DD-MM-YYYY): ");
            DateTimeFormatter dobFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String dobInput = scanner.nextLine();
            try {
                dateOfBirth = LocalDate.parse(dobInput, dobFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in DD-MM-YYYY format.");
            }
        }

        return dateOfBirth;
    }

    public String inputNeedsDesc() {
        System.out.print("Enter Needs Description: ");
        String needs = scanner.nextLine();
        return needs;
    }

    public DoneeCategory inputCategory() {
        boolean catInputValidation = false;
        DoneeCategory category = null;
        do {
            System.out.println("1. Individual");
            System.out.println("2. Organization");
            System.out.print("Enter Donor Category: ");

            if (scanner.hasNextInt()) {
                int catInput = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer

                switch (catInput) {
                    case 1:
                        category = DoneeCategory.INDIVIDUAL;
                        catInputValidation = true;
                        break;
                    case 2:
                        category = DoneeCategory.ORGANIZATION;
                        catInputValidation = true;
                        break;
                    default:
                        displayInvalidChoiceMessage();
                        break;
                }
            } else {
                displayInvalidChoiceMessage();
                scanner.next();
            }
        } while (!catInputValidation);

        return category;
    }

    public DoneeStatus inputStatus() {
        boolean statusInputValidation = false;
        int statusInput;
        DoneeStatus status = null;
        do {
            System.out.println("1. Active");
            System.out.println("2. Inactive ");
            System.out.println("3. Suspended ");
            System.out.print("Enter Donor Status: ");

            if (scanner.hasNextInt()) {
                statusInput = scanner.nextInt();
                scanner.nextLine();

                switch (statusInput) {
                    case 1:
                        status = DoneeStatus.ACTIVE;
                        statusInputValidation = true;
                        break;
                    case 2:
                        status = DoneeStatus.INACTIVE;
                        statusInputValidation = true;
                        break;
                    case 3:
                        status = DoneeStatus.SUSPENDED;
                        statusInputValidation = true;
                        break;

                    default:
                        displayInvalidChoiceMessage();
                        break;
                }

            } else {
                displayInvalidChoiceMessage();
                scanner.next();
            }

        } while (!statusInputValidation);

        return status;
    }

    public void displayDoneeDetails(Donee donee) {
        MessageUI.line(15);
        System.out.println("Donee Details");
        MessageUI.line(15);
        System.out.println("Donee ID            : " + donee.getDoneeId());
        System.out.println("Name                : " + donee.getName());
        System.out.println("Email               : " + donee.getEmail());
        System.out.println("Phone No            : " + donee.getPhoneNo());
        System.out.println("Address             : " + donee.getAddress());
        System.out.println("Category            : " + donee.getCategory());
        System.out.println("Date of Birth       : " + donee.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.println("Needs Description   : " + donee.getNeedsDescription());
        System.out.println("Registration Date   : " + donee.getRegistrationDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.println("Last Assistance Date: " + (donee.getLastAssistanceDate() != null ? donee.getLastAssistanceDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "Not yet assisted"));
        System.out.println("Status              : " + donee.getStatus());
        System.out.println("Number of Donations Received : " + donee.getDonationList().getNumberOfEntries());
        System.out.println("\n");
    }

    public void showDoneeEditMenu(Donee donee) {
        displayDoneeDetails(donee);
        boolean flag = false;

        do {
            System.out.println("Which detail would you like to change?");
            System.out.println("1. Name");
            System.out.println("2. Email");
            System.out.println("3. Phone Number");
            System.out.println("4. Address");
            System.out.println("5. Category");
            System.out.println("6. Status");
            System.out.println("7. Date Of Birth");
            System.out.println("8. Description");

            if (scanner.hasNextInt()) {
                int editInput = scanner.nextInt();
                scanner.nextLine();

                switch (editInput) {
                    case 1:
                        String editedName = inputName();
                        donee.setName(editedName);
                        flag = true;
                        break;

                    case 2:
                        String editedEmail = inputEmail();
                        donee.setEmail(editedEmail);
                        flag = true;
                        break;

                    case 3:
                        String editedPhone = inputPhoneNo();
                        donee.setPhoneNo(editedPhone);
                        flag = true;
                        break;

                    case 4:
                        String editedAddress = inputAddress();
                        donee.setAddress(editedAddress);
                        flag = true;
                        break;

                    case 5:
                        DoneeCategory editedCategory = inputCategory();
                        donee.setCategory(editedCategory);
                        flag = true;
                        break;

                    case 6:
                        DoneeStatus editedStatus = inputStatus();
                        donee.setStatus(editedStatus);
                        flag = true;
                        break;

                    case 7:
                        LocalDate editedDate = inputDOB();
                        donee.setDateOfBirth(editedDate);
                        flag = true;
                        break;

                    case 8:
                        String editedDesc = inputNeedsDesc();
                        donee.setNeedsDescription(editedDesc);
                        flag = true;
                        break;

                    default:
                        displayInvalidChoiceMessage();
                        break;
                }
            } else {
                displayInvalidChoiceMessage();
                scanner.next();
            }

        } while (!flag);

    }

    public int confirmationMessage() {
        boolean correctInput = false;
        int input = -1;
        do {
            System.out.println("1-Yes 2-No 0-Exit");
            System.out.print("Enter Number:");
            try {
                input = scanner.nextInt();
            } catch (InputMismatchException ex) {
                scanner.nextLine();
            }
            if (input >= 0 && input <= 2) {
                correctInput = true;
            } else {
                displayInvalidChoiceMessage();
            }
        } while (!correctInput);
        return input;
    }

    public void listAllDonees(HashMap<Integer, Donee> doneeMap) {
        Iterator keyIt = doneeMap.keySet().getIterator();

        MessageUI.displayDoneeHeader();
        while (keyIt.hasNext()) {
            System.out.println(doneeMap.get((Integer) keyIt.next()).toString());
        }
        line(205);
    }

    public ArrayList<Donee> showDoneeSearchMenu(HashMap<Integer, Donee> doneeMap) {
        this.doneeMap = doneeMap;
        ArrayList<Donee> resultList = null;

        boolean flag = false;

        do {
            System.out.println("How would you like to search");
            System.out.println("1. ID");
            System.out.println("2. Name");
            System.out.println("3. Email");
            System.out.println("4. Phone Number");
            System.out.println("5. Address");
            System.out.println("6. Category");
            System.out.println("7. Status");
            System.out.println("8. Age");
            System.out.println("9. Description");

            if (scanner.hasNextInt()) {
                int searchInput = scanner.nextInt();
                scanner.nextLine();

                switch (searchInput) {
                    case 1:
                        System.out.print("Enter ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        MessageUI.displayDoneeHeader();
                        resultList = searchById(id);
                        flag = true;
                        break;

                    case 2:
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        MessageUI.displayDoneeHeader();
                        resultList = searchByName(name);
                        flag = true;
                        break;

                    case 3:
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        MessageUI.displayDoneeHeader();
                        resultList = searchByEmail(email);
                        flag = true;
                        break;

                    case 4:
                        System.out.print("Enter Phone Number: ");
                        String phoneNo = scanner.nextLine();
                        MessageUI.displayDoneeHeader();
                        resultList = searchByPhoneNo(phoneNo);
                        flag = true;
                        break;

                    case 5:
                        System.out.print("Enter Address: ");
                        String address = scanner.nextLine();
                        MessageUI.displayDoneeHeader();
                        resultList = searchByAddress(address);
                        flag = true;
                        break;

                    case 6:
                        System.out.println("Select Category: ");
                        System.out.println("1. Individual ");
                        System.out.println("2. Organization");
                        System.out.println("3. Family");

                        if (scanner.hasNextInt()) {
                            int catChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch (catChoice) {
                                case 1:
                                    MessageUI.displayDoneeHeader();
                                    resultList = searchByCategory("INDIVIDUAL");
                                    flag = true;
                                    break;

                                case 2:
                                    MessageUI.displayDoneeHeader();
                                    resultList = searchByCategory("ORGANIZATION");
                                    flag = true;
                                    break;

                                case 3:
                                    MessageUI.displayDoneeHeader();
                                    resultList = searchByCategory("FAMILY");
                                    flag = true;
                                    break;

                                default:
                                    displayInvalidChoiceMessage();
                                    break;
                            }
                        } else {
                            displayInvalidChoiceMessage();
                            scanner.next();
                        }

                        flag = true;
                        break;

                    case 7:
                        System.out.println("Select Status: ");
                        System.out.println("1. Active ");
                        System.out.println("2. Inactive");
                        System.out.println("3. Suspended");

                        if (scanner.hasNextInt()) {
                            int statusChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch (statusChoice) {
                                case 1:
                                    MessageUI.displayDoneeHeader();
                                    resultList = searchByStatus("ACTIVE");
                                    flag = true;
                                    break;

                                case 2:
                                    MessageUI.displayDoneeHeader();
                                    resultList = searchByStatus("INACTIVE");
                                    flag = true;
                                    break;

                                case 3:
                                    MessageUI.displayDoneeHeader();
                                    resultList = searchByStatus("SUSPENDED");
                                    flag = true;
                                    break;

                                default:
                                    displayInvalidChoiceMessage();
                                    break;
                            }
                        } else {
                            displayInvalidChoiceMessage();
                            scanner.next();
                        }

                        flag = true;
                        break;

                    case 8:
                        System.out.print("Enter Age: ");
                        int age = scanner.nextInt();
                        MessageUI.displayDoneeHeader();
                        resultList = searchByAge(age);
                        flag = true;
                        break;

                    case 9:
                        System.out.print("Enter Description: ");
                        String description = scanner.nextLine();
                        MessageUI.displayDoneeHeader();
                        resultList = searchByDescription(description);
                        flag = true;
                        break;

                    default:
                        displayInvalidChoiceMessage();
                        break;
                }
            } else {
                displayInvalidChoiceMessage();
                scanner.next();
            }
        } while (!flag);

        return resultList;
    }

    private ArrayList<Donee> searchById(int doneeId) {
        ArrayList<Donee> resultList = new ArrayList<>();
        Donee donee = doneeMap.get(doneeId);
        if (donee != null) {
            System.out.println("1. " + donee.toString());
            resultList.add(donee);
        } else {
            System.out.println("No donee found with ID: " + doneeId);
        }
        return resultList;
    }

    private ArrayList<Donee> searchByName(String name) {
        ArrayList<Donee> resultList = new ArrayList<>();
        int index = 1;
        int foundCount = 0;
        Iterator<Integer> keyIt = doneeMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Donee donee = doneeMap.get(keyIt.next());
            if (donee.getName().equalsIgnoreCase(name)) {
                System.out.println(index++ + ". " + donee.toString());
                resultList.add(donee);
                foundCount++;
            }
        }

        System.out.println(foundCount + " donee(s) found with name: " + name);
        return resultList;
    }

    private ArrayList<Donee> searchByEmail(String email) {
        ArrayList<Donee> resultList = new ArrayList<>();
        int index = 1;
        int foundCount = 0;
        Iterator<Integer> keyIt = doneeMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Donee donee = doneeMap.get(keyIt.next());
            if (donee.getEmail().equalsIgnoreCase(email)) {
                System.out.println(index++ + ". " + donee.toString());
                resultList.add(donee);
                foundCount++;
            }
        }

        System.out.println(foundCount + " donee(s) found with email: " + email);
        return resultList;
    }

    private ArrayList<Donee> searchByPhoneNo(String phoneNo) {
        ArrayList<Donee> resultList = new ArrayList<>();
        int index = 1;
        int foundCount = 0;
        Iterator<Integer> keyIt = doneeMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Donee donee = doneeMap.get(keyIt.next());
            if (donee.getPhoneNo().equalsIgnoreCase(phoneNo)) {
                System.out.println(index++ + ". " + donee.toString());
                resultList.add(donee);
                foundCount++;
            }
        }

        System.out.println(foundCount + " donee(s) found with phone number: " + phoneNo);
        return resultList;
    }

    private ArrayList<Donee> searchByAddress(String address) {
        ArrayList<Donee> resultList = new ArrayList<>();
        int index = 1;
        int foundCount = 0;
        Iterator<Integer> keyIt = doneeMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Donee donee = doneeMap.get(keyIt.next());
            if (donee.getAddress().contains(address)) {
                System.out.println(index++ + ". " + donee.toString());
                resultList.add(donee);
                foundCount++;
            }
        }

        System.out.println(foundCount + " donee(s) found with address: " + address);
        return resultList;
    }

    private ArrayList<Donee> searchByCategory(String category) {
        ArrayList<Donee> resultList = new ArrayList<>();
        int index = 1;
        int foundCount = 0;
        Iterator<Integer> keyIt = doneeMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Donee donee = doneeMap.get(keyIt.next());
            if (donee.getCategory().toString().equalsIgnoreCase(category)) {
                System.out.println(index++ + ". " + donee.toString());
                resultList.add(donee);
                foundCount++;
            }
        }

        System.out.println(foundCount + " donee(s) found with category: " + category);
        return resultList;
    }

    private ArrayList<Donee> searchByStatus(String status) {
        ArrayList<Donee> resultList = new ArrayList<>();
        int index = 1;
        int foundCount = 0;
        Iterator<Integer> keyIt = doneeMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Donee donee = doneeMap.get(keyIt.next());
            if (donee.getStatus().toString().equalsIgnoreCase(status)) {
                System.out.println(index++ + ". " + donee.toString());
                resultList.add(donee);
                foundCount++;
            }
        }

        System.out.println(foundCount + " donee(s) found with status: " + status);
        return resultList;
    }

    private ArrayList<Donee> searchByAge(int age) {
        ArrayList<Donee> resultList = new ArrayList<>();
        int index = 1;
        int foundCount = 0;
        LocalDate today = LocalDate.now();
        Iterator<Integer> keyIt = doneeMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Donee donee = doneeMap.get(keyIt.next());
            int doneeAge = today.getYear() - donee.getDateOfBirth().getYear();
            if (doneeAge == age) {
                System.out.println(index++ + ". " + donee.toString());
                resultList.add(donee);
                foundCount++;
            }
        }

        System.out.println(foundCount + " donee(s) found with age: " + age);
        return resultList;
    }

    private ArrayList<Donee> searchByDescription(String description) {
        ArrayList<Donee> resultList = new ArrayList<>();
        int index = 1;
        int foundCount = 0;
        Iterator<Integer> keyIt = doneeMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Donee donee = doneeMap.get(keyIt.next());
            if (donee.getNeedsDescription().contains(description)) {
                System.out.println(index++ + ". " + donee.toString());
                resultList.add(donee);
                foundCount++;
            }
        }

        System.out.println(foundCount + " donee(s) found with description: " + description);
        return resultList;
    }

    public int selectDoneeAction() {
        int selectInput = -1;
        boolean flag = false;

        do {
            System.out.println("What would you like to do?");
            System.out.println("1. Edit");
            System.out.println("2. Remove");
            System.out.println("3. View Donations Made");
            System.out.println("0. Cancel");

            if (scanner.hasNextInt()) {
                selectInput = scanner.nextInt();
                scanner.nextLine();

                if (selectInput >= 0 && selectInput <= 3) {
                    flag = true;
                } else {
                    displayInvalidChoiceMessage();
                }
            } else {
                displayInvalidChoiceMessage();
                scanner.next();
            }
        } while (!flag);

        return selectInput;
    }

}
