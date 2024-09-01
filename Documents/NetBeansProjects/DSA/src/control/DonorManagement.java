/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.ArrayList;
import adt.CircularLinkedQueue;
import adt.HashMap;
import adt.LinkedList;
import adt.LinkedStack;
import boundary.DonorManagementUI;
import dao.DonationDAO;
import dao.DonorDAO;
import entity.Donation;
import entity.DonationItem;
import entity.Donor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import utility.DonorCategory;
import utility.DonorType;
import static utility.MessageUI.displayInvalidChoiceMessage;
import static utility.MessageUI.enterToContinue;

/**
 *
 * @author Sim Yin Horng
 */
public class DonorManagement {

    private HashMap<Integer, Donor> donorMap = new HashMap<>();
    private DonorDAO donorDAO = new DonorDAO();
    private DonorManagementUI donorUI = new DonorManagementUI();

    public DonorManagement() {
        donorMap = donorDAO.retrieveFromFile();
        DonationDAO donationDAO = new DonationDAO();
        ArrayList<Donation> donationList = donationDAO.loadDonations();

        for (int i = 0; i < donationList.getNumberOfEntries(); i++) {
            Donation donation = donationList.getEntry(i);
            if (donation != null) {
                int donorID = donation.getDonor().getDonorId();
                if (donorMap.containsKey(donorID)) {
                    Donor donor = donorMap.get(donorID);
                    donor.getDonationList().add(donation);
                }

            }
        }

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
                case 4:
                    SearchDonor();
                    break;
                case 5:
                    ListDonorDonations();
                    break;
                case 6:
                    FilterDonor();
                    break;
                case 7:
                    CategoriseDonor();
                    break;
                case 8:
                    GenerateSummaryReport();
                    break;
                default:
                    displayInvalidChoiceMessage();
                    break;
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
                    Donor.setNextDonorId(Donor.getNextDonorId() - 1);
                    exit = true;
                    break;
            }

        } while (!exit);
    }

    public static void main(String[] args) {
        DonorManagement productMaintenance = new DonorManagement();
        productMaintenance.runDonorManagement();
    }

    public void removeDonor() {
        boolean exit = false;
        do {
            donorUI.listAllDonors(donorMap);
            exit = deleteFunction();

        } while (!exit);

    }

    public void updateDonorDetails() {
        boolean exit = false;
        do {
            donorUI.listAllDonors(donorMap);
            exit = updateFunction();

        } while (!exit);
    }

    public void SearchDonor() {
        boolean exit = false;
        do {
            LinkedList<Donor> displayList = new LinkedList<>();
            boolean recordFound = false;
            int search = donorUI.getSearchMenu();
            switch (search) {
                case 1:
                    String name = donorUI.inputDonorName();
                    donorUI.filterHeader(name);
                    displayList = filterBy(search, name);
                    donorUI.display(displayList);
                    break;
                case 2:
                    String email = donorUI.inputSearchEmail();
                    donorUI.filterHeader(email);
                    displayList = filterBy(search, email);
                    donorUI.display(displayList);
                    break;
                case 3:
                    String phoneNo = donorUI.inputSearchPhoneNo();
                    donorUI.filterHeader(phoneNo);
                    displayList = filterBy(search, phoneNo);
                    donorUI.display(displayList);
                    break;
                case 4:
                    String address = donorUI.inputDonorAddress();
                    donorUI.filterHeader(address);
                    displayList = filterBy(search, address);
                    donorUI.display(displayList);
                    break;
                case 5:
                    DonorType type = donorUI.inputDonorType();
                    donorUI.filterHeader(type.toString());
                    displayList = filterBy(search, type);
                    donorUI.display(displayList);
                    break;
                case 6:
                    DonorCategory cat = donorUI.inputDonorCat();
                    donorUI.filterHeader(cat.toString());
                    displayList = filterBy(search, cat);
                    donorUI.display(displayList);
                    break;
                case 0:
                    exit = true;
                    break;
            }
            recordFound = !displayList.isEmpty();
            if (!exit && recordFound) {
                subMenu();
            }

        } while (!exit);
    }

    public void ListDonorDonations() {
        boolean exit = false;
        do {
            donorUI.listAllDonors(donorMap);
            exit = listFunction();
        } while (!exit);
    }

    public void FilterDonor() {
        boolean exit = false;
        do {
            LinkedList<Donor> displayList = new LinkedList<>();
            boolean recordFound = false;
            int filter = donorUI.getFilterMenu();
            switch (filter) {
                case 1:
                    String emailDomain = donorUI.inputEmailDomain();
                    donorUI.filterHeader(emailDomain);
                    displayList = filterByEmailDomain(emailDomain);
                    donorUI.display(displayList);
                    break;
                case 2:
                    int slc = donorUI.donationFilterMenu();
                    switch (slc) {
                        case 1:
                            int above = donorUI.inputQty();
                            donorUI.filterHeader("Donation Above " + above);
                            displayList = filterByDonation("ABOVE", above);
                            donorUI.display(displayList);
                            break;
                        case 2:
                            System.out.println("Enter First Number");
                            int first = donorUI.inputQty();
                            System.out.println("Enter Second Number");
                            int second = donorUI.inputQty();
                            displayList = filterBetween(first, second);
                            donorUI.display(displayList);
                            break;
                        case 3:
                            int below = donorUI.inputQty();
                            donorUI.filterHeader("Donation below " + below);
                            displayList = filterByDonation("BELOW", below);
                            donorUI.display(displayList);
                            break;
                        case 4:
                            int equal = donorUI.inputQty();
                            donorUI.filterHeader("Donation below " + equal);
                            displayList = filterByDonation("EQUAL", equal);
                            donorUI.display(displayList);
                            break;
                        case 0:
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Select a Category to exclude");
                    DonorType type = donorUI.inputDonorType();
                    donorUI.filterHeader("excluding " + type.toString());
                    displayList = excludeCategory(type);
                    donorUI.display(displayList);
                    break;
                case 0:
                    exit = true;
                    break;
            }
            recordFound = !displayList.isEmpty();
            if (!exit && recordFound) {
                subMenu();
            }

        } while (!exit);
    }

    public void CategoriseDonor() {
        HashMap<DonorCategory, LinkedList<Donor>> donorCatMap = new HashMap<>();
        LinkedList<Donor> govDonor = new LinkedList<>();
        LinkedList<Donor> privateDonor = new LinkedList<>();
        LinkedList<Donor> publicDonor = new LinkedList<>();

        Iterator keyIt = donorMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Integer key = (Integer) keyIt.next();
            Donor donor = donorMap.get(key);

            switch (donor.getType()) {
                case GOVERNMENT:
                    govDonor.add(donor);
                    break;
                case PRIVATE:
                    privateDonor.add(donor);
                    break;
                case PUBLIC:
                    publicDonor.add(donor);
                    break;
                default:
                    break;
            }
        }

        boolean exit = false;
        DonorType type = null;
        do {
            boolean recordFound = false;
            int cat = donorUI.getCategorizeMenu();
            switch (cat) {
                case 1:
                    type = DonorType.GOVERNMENT;
                    double percentage = (double) govDonor.getNumberOfEntries() / donorMap.size() * 100;
                    donorUI.categorizeHeader(govDonor.getNumberOfEntries(), percentage, DonorType.GOVERNMENT.toString());
                    donorUI.display(govDonor);
                    recordFound = !govDonor.isEmpty();
                    break;
                case 2:
                    type = DonorType.PUBLIC;
                    double percentagePublic = (double) publicDonor.getNumberOfEntries() / donorMap.size() * 100;
                    donorUI.categorizeHeader(publicDonor.getNumberOfEntries(), percentagePublic, DonorType.PUBLIC.toString());
                    donorUI.display(publicDonor);
                    recordFound = !publicDonor.isEmpty();
                    break;
                case 3:
                    type = DonorType.PRIVATE;
                    double percentagePrivate = (double) privateDonor.getNumberOfEntries() / donorMap.size() * 100;
                    donorUI.categorizeHeader(privateDonor.getNumberOfEntries(), percentagePrivate, DonorType.PRIVATE.toString());
                    donorUI.display(privateDonor);
                    recordFound = !privateDonor.isEmpty();
                    break;
                case 0:
                    exit = true;
                    break;
            }
            if (!exit && recordFound) {
                subMenu();
            }

        } while (!exit);

    }

    public void GenerateSummaryReport() {
        //date
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        //categorize donor for data
        LinkedList<Donor> govDonor = new LinkedList<>();
        LinkedList<Donor> privateDonor = new LinkedList<>();
        LinkedList<Donor> publicDonor = new LinkedList<>();
        LinkedList<Donor> indiDonor = new LinkedList<>();
        LinkedList<Donor> orgDonor = new LinkedList<>();
        Iterator keyIt = donorMap.keySet().getIterator();
        int publicDonationQty = 0;
        int privateDonationQty = 0;
        int govDonationQty = 0;
        int indiDonationQty = 0;
        int orgDonationQty = 0;
        double publicTtlDonationAmt = 0;
        double privateTtlDonationAmt = 0;
        double govTtlDonationAmt = 0;
        double indiTtlDonationAmt = 0;
        double orgTtlDonationAmt = 0;
        while (keyIt.hasNext()) {
            Integer key = (Integer) keyIt.next();
            Donor donor = donorMap.get(key);

            switch (donor.getType()) {
                case GOVERNMENT:
                    govDonor.add(donor);
                    Iterator govIt = donor.getDonationList().iterator();
                    while (govIt.hasNext()) {
                        Donation donation = (Donation) govIt.next();
                        if (donation != null) {
                            govTtlDonationAmt += donation.getTotalValue();
                            govDonationQty++;
                        }

                    }
                    break;
                case PRIVATE:
                    privateDonor.add(donor);
                    Iterator privateIt = donor.getDonationList().iterator();
                    while (privateIt.hasNext()) {
                        Donation donation = (Donation) privateIt.next();
                        if (donation != null) {
                            privateTtlDonationAmt += donation.getTotalValue();
                            privateDonationQty++;
                        }

                    }
                    break;
                case PUBLIC:
                    publicDonor.add(donor);
                    Iterator publicIt = donor.getDonationList().iterator();
                    while (publicIt.hasNext()) {
                        Donation donation = (Donation) publicIt.next();
                        if (donation != null) {
                            publicTtlDonationAmt += donation.getTotalValue();
                            publicDonationQty++;
                        }

                    }
                    break;
                default:
                    break;
            }

            switch (donor.getCategory()) {
                case INDIVIDUAL:
                    indiDonor.add(donor);
                    Iterator indiIt = donor.getDonationList().iterator();
                    while (indiIt.hasNext()) {
                        Donation donation = (Donation) indiIt.next();
                        if (donation != null) {
                            indiTtlDonationAmt += donation.getTotalValue();
                            indiDonationQty++;
                        }

                    }
                    break;
                case ORGANIZATION:
                    orgDonor.add(donor);
                    Iterator orgIt = donor.getDonationList().iterator();
                    while (orgIt.hasNext()) {
                        Donation donation = (Donation) orgIt.next();
                        if (donation != null) {
                            orgTtlDonationAmt += donation.getTotalValue();
                            orgDonationQty++;
                        }

                    }
                    break;
            }

        }
        //for top 5 and bottom 5 donor
        keyIt = donorMap.keySet().getIterator();
        LinkedList<Donor> sortedDonor = new LinkedList<>();
        LinkedStack<Donor> botDonor = new LinkedStack<>();
        CircularLinkedQueue<Donor> topDonor = new CircularLinkedQueue<>();
        while (keyIt.hasNext()) {
            Integer key = (Integer) keyIt.next();
            Donor donor = donorMap.get(key);
            sortedDonor.add(donor);
        }

        //bubble sorts
        int n = sortedDonor.getNumberOfEntries();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Donor donor1 = sortedDonor.getEntry(j + 1);
                Donor donor2 = sortedDonor.getEntry(j + 2);
                if (donor1.getDonationList().getNumberOfEntries() < donor2.getDonationList().getNumberOfEntries()) {
                    // Swap donor1 and donor2
                    sortedDonor.replace(j + 1, donor2);
                    sortedDonor.replace(j + 2, donor1);
                }
            }
        }

        for (int i = 1; i < sortedDonor.getNumberOfEntries() + 1; i++) {
            Donor donor = sortedDonor.getEntry(i);
            topDonor.enqueue(donor);
            botDonor.push(donor);
        }

        int totalDonor = donorMap.size();
        int publicDonorQty = publicDonor.getNumberOfEntries();
        int privateDonorQty = privateDonor.getNumberOfEntries();
        int govDonorQty = govDonor.getNumberOfEntries();
        int indiDonorQty = indiDonor.getNumberOfEntries();
        int orgDonorQty = orgDonor.getNumberOfEntries();

        donorUI.reportHeader(formattedDateTime, totalDonor);

        System.out.printf("| %-15s| %-17d| %-15.2f| %-26d| %-21.2f|\n", DonorType.PUBLIC.toString(), publicDonorQty, (double) publicDonorQty / totalDonor * 100, publicDonationQty, publicTtlDonationAmt);
        System.out.printf("| %-15s| %-17d| %-15.2f| %-26d| %-21.2f|\n", DonorType.PRIVATE.toString(), privateDonorQty, (double) privateDonorQty / totalDonor * 100, privateDonationQty, privateTtlDonationAmt);
        System.out.printf("| %-15s| %-17d| %-15.2f| %-26d| %-21.2f|\n", DonorType.GOVERNMENT.toString(), govDonorQty, (double) govDonorQty / totalDonor * 100, govDonationQty, govTtlDonationAmt);

        donorUI.reportCatHeader();
        System.out.printf("| %-15s| %-17d| %-15.2f| %-26d| %-21.2f|\n", DonorCategory.INDIVIDUAL.toString(), indiDonorQty, (double) indiDonorQty / totalDonor * 100, indiDonationQty, indiTtlDonationAmt);
        System.out.printf("| %-15s| %-17d| %-15.2f| %-26d| %-21.2f|\n", DonorCategory.ORGANIZATION.toString(), orgDonorQty, (double) orgDonorQty / totalDonor * 100, orgDonationQty, orgTtlDonationAmt);
        donorUI.topDonorHeader();
        for (int i = 1; i < 6; i++) {
            Donor donor = topDonor.dequeue();
            if (donor != null) {
                double amount = 0;
                Iterator amtIt = donor.getDonationList().iterator();
                while (amtIt.hasNext()) {
                    Donation donation = (Donation) amtIt.next();
                    amount += donation.getTotalValue();
                }

                System.out.printf("| %-5d| %-27s| %-15s| %-26d| %-21.2f|\n", i, donor.getName(), donor.getCategory().toString(), donor.getDonationList().getNumberOfEntries(), amount);
            }

        }
        donorUI.botDonorHeader();
        for (int i = 1; i < 6; i++) {
            Donor donor = botDonor.pop();
            if (donor != null) {
                double amount = 0;
                Iterator amtIt = donor.getDonationList().iterator();
                while (amtIt.hasNext()) {
                    Donation donation = (Donation) amtIt.next();
                    amount += donation.getTotalValue();
                }

                System.out.printf("| %-5d| %-27s| %-15s| %-26d| %-21.2f|\n", i, donor.getName(), donor.getCategory().toString(), donor.getDonationList().getNumberOfEntries(), amount);
            }

        }
        donorUI.reportFooter();
        enterToContinue();
    }

    public void subMenu() {
        int choice;

        do {
            choice = donorUI.getSubMenu();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    deleteFunction();
                    break;
                case 2:
                    updateFunction();
                    break;
                case 3:
                    listFunction();
                    break;
                default:
                    displayInvalidChoiceMessage();
                    break;
            }
        } while (choice != 0);
    }

    public boolean deleteFunction() {
        boolean exit = false;
        System.out.println("Select Donor To Delete (0 to exit)");
        int removeId = donorUI.inputDonorID();
        if (removeId == 0) {
            exit = true;
        } else {
            Donor deleteDonor = donorMap.get(removeId);
            if (deleteDonor != null) {
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
            } else {
                System.out.println("Invalid Donor Id!!!");
                enterToContinue();
            }
        }
        return exit;
    }

    public boolean updateFunction() {
        boolean exit = false;
        System.out.println("Select Donor To Edit (0 to exit)");
        int editId = donorUI.inputDonorID();
        if (editId == 0) {
            exit = true;
        } else {

            Donor editDonor = donorMap.get(editId);
            Donor oldDonor = clone(editDonor);
            System.out.println(oldDonor.toString());
            if (editDonor == null) {
                displayInvalidChoiceMessage();
            } else {
                boolean stopEdit = false;
                boolean edited = false;
                while (!stopEdit) {
                    donorUI.displayDonorDetails(editDonor);
                    int edit = donorUI.getEditMenu();
                    switch (edit) {
                        case 1:
                            String name = donorUI.inputDonorName();
                            editDonor.setName(name);
                            edited = true;
                            break;
                        case 2:
                            String email = donorUI.inputDonorEmail();
                            editDonor.setEmail(email);
                            edited = true;
                            break;
                        case 3:
                            String phoneNo = donorUI.inputDonorPhoneNo();
                            editDonor.setPhoneNo(phoneNo);
                            edited = true;
                            break;
                        case 4:
                            String address = donorUI.inputDonorAddress();
                            editDonor.setAddress(address);
                            edited = true;
                            break;
                        case 5:
                            DonorType type = donorUI.inputDonorType();
                            editDonor.setType(type);
                            edited = true;
                            break;
                        case 6:
                            DonorCategory cat = donorUI.inputDonorCat();
                            editDonor.setCategory(cat);
                            edited = true;
                            break;
                        case 0:
                            stopEdit = true;
                            break;
                    }
                    if (edited) {
                        donorUI.displayDonorDetails(editDonor);
                        System.out.println("Are the details correct?");
                        int confirm = donorUI.confirmationMessage();
                        switch (confirm) {
                            case 1:
                                donorMap.put(editDonor.getDonorId(), editDonor);
                                donorDAO.saveToFile(donorMap);
                                exit = true;
                                break;
                            case 2:
                                editDonor = clone(oldDonor);
                                stopEdit = false;
                                break;
                            case 0:
                                exit = true;
                                break;
                        }
                    }
                }
            }

        }
        return exit;
    }

    public boolean listFunction() {
        boolean exit = false;
        System.out.println("Select Donor To List its Donation (0 to exit)");
        int listId = donorUI.inputDonorID();
        if (listId == 0) {
            exit = true;
        } else {
            Donor listDonor = donorMap.get(listId);
            if (listDonor != null) {
                donorUI.displayDonorDetails(listDonor);
                donorUI.displayDonorDonations(listDonor.getDonationList());
                enterToContinue();
            } else {
                System.out.println("Invalid Donor Id!!!");
                enterToContinue();
            }
        }
        return exit;
    }

    public LinkedList<Donor> filterBy(int criteria, Object searchValue) {
        LinkedList<Donor> result = new LinkedList<>();
        Iterator keyIt = donorMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Integer key = (Integer) keyIt.next();
            Donor donor = donorMap.get(key);
            switch (criteria) {
                case 1:
                    String name = (String) searchValue;
                    if (donor.getName().toLowerCase().contains(name.toLowerCase())) {
                        result.add(donor);
                    }
                    break;
                case 2:
                    String email = (String) searchValue;
                    if (donor.getEmail().toLowerCase().contains(email.toLowerCase())) {
                        result.add(donor);
                    }
                    break;
                case 3:
                    String phoneNo = (String) searchValue;
                    if (donor.getPhoneNo().contains(phoneNo)) {
                        result.add(donor);
                    }
                    break;
                case 4:
                    String address = (String) searchValue;
                    if (donor.getAddress().toLowerCase().contains(address.toLowerCase())) {
                        result.add(donor);
                    }
                    break;
                case 5:
                    DonorType type = (DonorType) searchValue;
                    if (donor.getType().equals(type)) {
                        result.add(donor);
                    }
                    break;
                case 6:
                    DonorCategory cat = (DonorCategory) searchValue;
                    if (donor.getCategory().equals(cat)) {
                        result.add(donor);
                    }
                    break;
            }

        }
        return result;
    }

    public LinkedList<Donor> filterByEmailDomain(String emailDomain) {
        LinkedList<Donor> result = new LinkedList<>();
        Iterator keyIt = donorMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Integer key = (Integer) keyIt.next();
            Donor donor = donorMap.get(key);

            if (donor.getEmail().contains(emailDomain)) {
                result.add(donor);
            }
        }
        return result;

    }

    public LinkedList<Donor> filterByDonation(String condition, int qty) {
        LinkedList<Donor> result = new LinkedList<>();
        Iterator keyIt = donorMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Integer key = (Integer) keyIt.next();
            Donor donor = donorMap.get(key);
            switch (condition) {
                case "ABOVE":
                    if (donor.getDonationList().getNumberOfEntries() >= qty) {
                        result.add(donor);
                    }
                    break;
                case "BELOW":
                    if (donor.getDonationList().getNumberOfEntries() <= qty) {
                        result.add(donor);
                    }
                    break;
                case "EQUAL":
                    if (donor.getDonationList().getNumberOfEntries() == qty) {
                        result.add(donor);
                    }
                    break;
            }

        }
        return result;
    }

    public LinkedList<Donor> filterBetween(int firstNum, int secondNum) {
        LinkedList<Donor> result = new LinkedList<>();
        Iterator keyIt = donorMap.keySet().getIterator();

        int largerNum;
        int smallerNum;

        if (firstNum > secondNum) {
            largerNum = firstNum;
            smallerNum = secondNum;
        } else {
            largerNum = secondNum;
            smallerNum = firstNum;
        }

        while (keyIt.hasNext()) {
            Integer key = (Integer) keyIt.next();
            Donor donor = donorMap.get(key);

            if (donor.getDonationList().getNumberOfEntries() < largerNum && donor.getDonationList().getNumberOfEntries() > smallerNum) {
                result.add(donor);
            }
        }
        return result;
    }

    public LinkedList<Donor> excludeCategory(DonorType type) {
        LinkedList<Donor> result = new LinkedList<>();
        Iterator keyIt = donorMap.keySet().getIterator();

        while (keyIt.hasNext()) {
            Integer key = (Integer) keyIt.next();
            Donor donor = donorMap.get(key);

            if (donor.getType() != type) {
                result.add(donor);
            }
        }
        return result;
    }

    public Donor clone(Donor oldDonor) {
        Donor clonedDonor = new Donor();
        clonedDonor.setDonorId(oldDonor.getDonorId());
        clonedDonor.setName(oldDonor.getName());
        clonedDonor.setEmail(oldDonor.getEmail());
        clonedDonor.setPhoneNo(oldDonor.getPhoneNo());
        clonedDonor.setAddress(oldDonor.getAddress());
        clonedDonor.setType(oldDonor.getType());
        clonedDonor.setCategory(oldDonor.getCategory());
        LinkedList<Donation> newList = new LinkedList<>();
        Iterator listIt = oldDonor.getDonationList().iterator();
        while (listIt.hasNext()) {
            Donation donation = (Donation) listIt.next();
            newList.add(donation);
        }

        clonedDonor.setDonationList(newList);
        return clonedDonor;
    }

}
