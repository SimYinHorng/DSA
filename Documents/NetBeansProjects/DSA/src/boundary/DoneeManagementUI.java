package boundary;

import adt.ArrayList;
import entity.Donee;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import utility.DoneeCategory;
import utility.DoneeStatus;
import utility.MessageUI;
import static utility.MessageUI.displayInvalidChoiceMessage;
import static utility.MessageUI.enterToContinue;

public class DoneeManagementUI {

    Scanner scanner = new Scanner(System.in);

    public int showDoneeManagementMenu() {

        boolean flag = false;
        int choice;

        do {
            System.out.println("Welcome to the Donee Management Module!");
            System.out.println("1. Add a new Donee");
            System.out.println("2. Remove Donee");
            System.out.println("3. Update Donee");
            System.out.println("4. Search Donee");
            System.out.println("5. List Donee");
            System.out.println("6. Filter Donee");
            System.out.println("7. Generate Report");
            System.out.println("What would you like to do?");
            choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            if (choice >= 0 && choice <= 7) {
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
    }

    public int confirmationMessage() {
        boolean correctInput = false;
        int input = -1;
        do {
            System.out.println("1-Yes 2-No 0-Exit");
            System.out.print("Enter No:");
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

}
