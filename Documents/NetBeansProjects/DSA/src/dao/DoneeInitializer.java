package dao;

import adt.ArrayList;
import adt.HashMap;
import adt.LinkedList;
import entity.Donation;
import entity.DonationItem;
import entity.Donee;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Random;
import utility.DoneeCategory;
import utility.DoneeStatus;

/**
 *
 * @author Chew Wei Seng
 */


public class DoneeInitializer {

    public HashMap<Integer, Donee> initializeDonee() {
        HashMap<Integer, Donee> doneeMap = new HashMap<>();
        Random random = new Random();

        LinkedList<Donation> donationList = new LinkedList<>();

        Donee donee1 = new Donee("Amina Ali", "amina.ali@example.com", "011-12345678", "Jalan Kenanga, Kuala Lumpur, 50000", DoneeCategory.INDIVIDUAL, LocalDate.of(1985, 5, 20), "Needs medical assistance", LocalDate.of(2023, 1, 10), DoneeStatus.ACTIVE);
        Donee donee2 = new Donee("Ravi Kumar", "ravi.kumar@example.com", "012-23456789", "Jalan Cempaka, Petaling Jaya, 46000", DoneeCategory.INDIVIDUAL, LocalDate.of(1978, 7, 15), "Needs educational support", LocalDate.of(2023, 2, 25), DoneeStatus.ACTIVE);
        Donee donee3 = new Donee("Siti Nurhaza", "siti.nurhaza@example.com", "013-34567890", "Jalan Damai, Johor Bahru, 80000", DoneeCategory.FAMILY, LocalDate.of(1990, 11, 30), "Needs family support", LocalDate.of(2023, 3, 5), DoneeStatus.INACTIVE);
        Donee donee4 = new Donee("John Tan", "john.tan@example.com", "014-45678901", "Jalan Bukit, Georgetown, 10200", DoneeCategory.INDIVIDUAL, LocalDate.of(1982, 3, 10), "Needs financial aid", LocalDate.of(2023, 4, 20), DoneeStatus.ACTIVE);
        Donee donee5 = new Donee("Lina Mohamed", "lina.mohamed@example.com", "015-56789012", "Jalan Raya, Kota Kinabalu, 88000", DoneeCategory.FAMILY, LocalDate.of(1987, 9, 25), "Needs housing assistance", LocalDate.of(2023, 5, 15), DoneeStatus.ACTIVE);
        Donee donee6 = new Donee("Ali Rahman", "ali.rahman@example.com", "016-67890123", "Jalan Murni, Kuantan, 25000", DoneeCategory.INDIVIDUAL, LocalDate.of(1993, 6, 5), "Needs vocational training", LocalDate.of(2023, 6, 10), DoneeStatus.INACTIVE);
        Donee donee7 = new Donee("Maya Lim", "maya.lim@example.com", "017-78901234", "Jalan Ria, Melaka, 75000", DoneeCategory.FAMILY, LocalDate.of(1980, 2, 14), "Needs child education support", LocalDate.of(2023, 7, 25), DoneeStatus.ACTIVE);
        Donee donee8 = new Donee("Ahmad Faiz", "ahmad.faiz@example.com", "018-89012345", "Jalan Selamat, Kuala Terengganu, 20000", DoneeCategory.ORGANIZATION, LocalDate.of(1975, 8, 30), "Needs health care supplies", LocalDate.of(2023, 8, 30), DoneeStatus.ACTIVE);
        Donee donee9 = new Donee("Nina Teo", "nina.teo@example.com", "019-90123456", "Jalan Kurnia, Ipoh, 31400", DoneeCategory.INDIVIDUAL, LocalDate.of(1989, 12, 20), "Needs legal aid", LocalDate.of(2023, 9, 10), DoneeStatus.INACTIVE);
        Donee donee10 = new Donee("Zainab Ali", "zainab.ali@example.com", "010-12345678", "Jalan Seri, Shah Alam, 40000", DoneeCategory.FAMILY, LocalDate.of(1984, 4, 10), "Needs food supplies", LocalDate.of(2023, 10, 5), DoneeStatus.ACTIVE);
        Donee donee11 = new Donee("Mohd Hafiz", "mohd.hafiz@example.com", "011-23456789", "Jalan Harmoni, Penang, 10100", DoneeCategory.ORGANIZATION, LocalDate.of(1988, 6, 15), "Needs emergency funds", LocalDate.of(2023, 11, 20), DoneeStatus.ACTIVE);
        Donee donee12 = new Donee("Nurul Iman", "nurul.iman@example.com", "012-34567890", "Jalan Bahagia, Kuala Lumpur, 50000", DoneeCategory.FAMILY, LocalDate.of(1992, 5, 25), "Needs medical check-ups", LocalDate.of(2023, 12, 15), DoneeStatus.INACTIVE);
        Donee donee13 = new Donee("Syed Amir", "syed.amir@example.com", "013-45678901", "Jalan Melati, Alor Setar, 05100", DoneeCategory.INDIVIDUAL, LocalDate.of(1981, 7, 30), "Needs disability support", LocalDate.of(2024, 1, 10), DoneeStatus.ACTIVE);
        Donee donee14 = new Donee("Farah Aziz", "farah.aziz@example.com", "014-56789012", "Jalan Teratai, Kuala Lumpur, 50000", DoneeCategory.ORGANIZATION, LocalDate.of(1986, 3, 15), "Needs educational materials", LocalDate.of(2024, 2, 20), DoneeStatus.ACTIVE);
        Donee donee15 = new Donee("Tengku Adnan", "tengku.adnan@example.com", "015-67890123", "Jalan Indah, Johor Bahru, 80000", DoneeCategory.INDIVIDUAL, LocalDate.of(1991, 8, 5), "Needs housing support", LocalDate.of(2024, 3, 15), DoneeStatus.INACTIVE);
        Donee donee16 = new Donee("Laila Hassan", "laila.hassan@example.com", "016-78901234", "Jalan Kenari, Seremban, 70200", DoneeCategory.FAMILY, LocalDate.of(1979, 9, 10), "Needs childcare assistance", LocalDate.of(2024, 4, 10), DoneeStatus.ACTIVE);
        Donee donee17 = new Donee("Kamal Yusof", "kamal.yusof@example.com", "017-89012345", "Jalan Seribu, Putrajaya, 62000", DoneeCategory.ORGANIZATION, LocalDate.of(1984, 2, 20), "Needs disaster relief", LocalDate.of(2024, 5, 20), DoneeStatus.ACTIVE);
        Donee donee18 = new Donee("Sasha Lee", "sasha.lee@example.com", "018-90123456", "Jalan Timur, Kuching, 93000", DoneeCategory.FAMILY, LocalDate.of(1995, 6, 25), "Needs job training", LocalDate.of(2024, 6, 25), DoneeStatus.INACTIVE);
        Donee donee19 = new Donee("Rashid Ahmad", "rashid.ahmad@example.com", "019-01234567", "Jalan Langit, Melaka, 75000", DoneeCategory.INDIVIDUAL, LocalDate.of(1983, 10, 10), "Needs transport support", LocalDate.of(2024, 7, 10), DoneeStatus.ACTIVE);
        Donee donee20 = new Donee("Diana Ooi", "diana.ooi@example.com", "010-23456789", "Jalan Padu, Kuala Lumpur, 50000", DoneeCategory.ORGANIZATION, LocalDate.of(1987, 12, 15), "Needs cultural support", LocalDate.of(2024, 8, 15), DoneeStatus.ACTIVE);

        Donee[] donees = {donee1, donee2, donee3, donee4, donee5, donee6, donee7, donee8, donee9, donee10, donee11, donee12, donee13, donee14, donee15, donee16, donee17, donee18, donee19, donee20};

        ArrayList<Donation> donations = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Donee donee = donees[random.nextInt(donees.length)];
            Donation donation = new Donation(donee, LocalDate.now());

            // Add 1 to 5 random DonationItems to each Donation
            int numberOfItems = 1 + random.nextInt(5);
            for (int j = 0; j < numberOfItems; j++) {
                DonationItem item = generateRandomItem();
                donation.addItem(item);
            }

            donations.add(donation);
        }

        // Assign donations to donees
        LinkedList<Donation>[] donationLists = new LinkedList[20];
        for (int i = 0; i < donationLists.length; i++) {
            donationLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < donations.getNumberOfEntries(); i++) {
            int index = random.nextInt(donationLists.length);
            Donation donation = donations.getEntry(i);
            donationLists[index].add(donation);
        }

        for (int i = 0; i < donationLists.length; i++) {
            if (donationLists[i].isEmpty()) {
                donationLists[i].add(donations.getEntry(i));
            }
            donees[i].setDonationList(donationLists[i]);
        }

        for (int i = 0; i < donees.length; i++) {
            doneeMap.put(donees[i].getDoneeId(), donees[i]);
        }

        return doneeMap;
    }

    private static String[] itemNames = {"Books", "Clothes", "Food", "Medicine", "Furniture", "Electronics"};
    private static String[] categories = {"Education", "Health", "Emergency", "Community Support"};

    public static DonationItem generateRandomItem() {
        Random random = new Random();
        String name = itemNames[random.nextInt(itemNames.length)];
        double value = 50 + (2000 - 50) * random.nextDouble(); // random value
        String category = categories[random.nextInt(categories.length)];
        boolean isCash = random.nextBoolean();

        return new DonationItem(name, value, category, isCash);
    }

    public static void main(String[] args) {
        DoneeInitializer dInt = new DoneeInitializer();
        HashMap<Integer, Donee> doneeMap = dInt.initializeDonee();
        Iterator<Integer> keyIt = doneeMap.keySet().getIterator();
        DoneeDAO doneeDAO = new DoneeDAO();
        doneeDAO.saveToFile(doneeMap);
    }

}


