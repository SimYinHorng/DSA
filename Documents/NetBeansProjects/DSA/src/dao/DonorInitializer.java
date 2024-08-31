/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.HashMap;
import entity.Donor;
import java.util.Iterator;
import utility.DonorCategory;
import utility.DonorType;

/**
 *
 * @author user
 */
//delete the donor.dat and donorId.dat then use run this initializer
public class DonorInitializer {

    public HashMap<Integer, Donor> initializeDonor() {
        HashMap<Integer, Donor> donorMap = new HashMap<>();
        Donor donor1 = new Donor("Lucas Ooi", "lucasooi@gmail.com", "016-4561234", "Damansara Heights, Off Jalan Semantan, Kuala Lumpur Wilayah Persekutuan, 50630", DonorType.PRIVATE, DonorCategory.INDIVIDUAL);

        Donor donor2 = new Donor("Amelia Tan", "ameliatan@yahoo.com", "017-9876543", "Taman Tun Dr Ismail, Kuala Lumpur, 60000", DonorType.PRIVATE, DonorCategory.INDIVIDUAL);

        Donor donor3 = new Donor("Michael Wong", "michael.wong@gmail.com", "012-3456789", "Mont Kiara, Jalan Kiara, Kuala Lumpur, 50480", DonorType.GOVERNMENT, DonorCategory.ORGANIZATION);

        Donor donor4 = new Donor("Sarah Lee", "sarah.lee@hotmail.com", "018-2233445", "Bangsar, Jalan Maarof, Kuala Lumpur, 59000", DonorType.PUBLIC, DonorCategory.ORGANIZATION);

        Donor donor5 = new Donor("David Lim", "davidlim@hotmail.com", "019-5544332", "Sri Hartamas, Jalan Sri Hartamas 1, Kuala Lumpur, 50480", DonorType.PRIVATE, DonorCategory.INDIVIDUAL);

        Donor donor6 = new Donor("Emily Ng", "emily.ng@gmail.com", "013-6677889", "Bukit Tunku, Jalan Langgak Tunku, Kuala Lumpur, 50480", DonorType.PRIVATE, DonorCategory.INDIVIDUAL);

        Donor donor7 = new Donor("Yap Org Sdn.Bhd", "yap@gmail.com", "04-3344556", "Desa ParkCity, Jalan Residen, Kuala Lumpur, 52200", DonorType.PUBLIC, DonorCategory.ORGANIZATION);

        Donor donor8 = new Donor("Rebecca Tan", "rebecca.tan@gmail.com", "012-9988776", "Setapak, Jalan Ayer Jerneh, Kuala Lumpur, 53300", DonorType.GOVERNMENT, DonorCategory.ORGANIZATION);

        Donor donor9 = new Donor("Joshua Lim", "joshualim@yahoo.com", "017-4455667", "Cheras, Jalan Cheras, Kuala Lumpur, 56100", DonorType.PRIVATE, DonorCategory.INDIVIDUAL);

        Donor donor10 = new Donor("Sophia Teo", "sophia.teo@hotmail.com", "014-3322110", "Taman Desa, Jalan Desa Utama, Kuala Lumpur, 58100", DonorType.PRIVATE, DonorCategory.INDIVIDUAL);

        Donor donor11 = new Donor("Benjamin Tan", "benjamin.tan@yahoo.com", "019-7788990", "Sentul, Jalan Ipoh, Kuala Lumpur, 51100", DonorType.PRIVATE, DonorCategory.ORGANIZATION);

        Donor donor12 = new Donor("Tech Innovators Inc.", "contact@techinnovators.com", "03-7654321", "Tech Park, Cyberjaya, Selangor, 63000", DonorType.PUBLIC, DonorCategory.ORGANIZATION);
        Donor donor13 = new Donor("Green Earth Foundation", "info@greenearth.org", "03-2345678", "Eco Avenue, Shah Alam, Selangor, 40000", DonorType.PUBLIC, DonorCategory.ORGANIZATION);
        Donor donor14 = new Donor("Community Care Society", "support@communitycare.my", "03-1234567", "Charity Lane, Georgetown, Penang, 10200", DonorType.PUBLIC, DonorCategory.ORGANIZATION);
        Donor donor15 = new Donor("Private Wealth Group", "inquiries@privatewealth.com", "03-8765432", "Finance Towers, Jalan Tun Razak, Kuala Lumpur, 50400", DonorType.PRIVATE, DonorCategory.ORGANIZATION);
        Donor donor16 = new Donor("Government Dev.Agency", "contact@govdev.my", "03-9876543", "Putrajaya, Federal Territory, 62000", DonorType.GOVERNMENT, DonorCategory.ORGANIZATION);
        Donor donor17 = new Donor("National Health.org", "info@health.gov.my", "03-4567890", "Health Building, Jalan Pudu, Kuala Lumpur, 55100", DonorType.GOVERNMENT, DonorCategory.ORGANIZATION);
        Donor donor18 = new Donor("Educational Trust", "info@edu-trust.org", "03-1122334", "Education Hub, Bukit Jalil, Kuala Lumpur, 57000", DonorType.PUBLIC, DonorCategory.ORGANIZATION);
        Donor donor19 = new Donor("Innovation Lab", "contact@innovatelab.com", "03-4433221", "Innovation Drive, Petaling Jaya, Selangor, 46000", DonorType.PUBLIC, DonorCategory.ORGANIZATION);
        Donor donor20 = new Donor("Urban Corporation", "support@urban-dev.com", "03-9988776", "City Square, Johor Bahru, Johor, 80000", DonorType.GOVERNMENT, DonorCategory.ORGANIZATION);

        Donor[] donors = {donor1, donor2, donor3, donor4, donor5, donor6, donor7, donor8, donor9, donor10, donor11, donor12, donor13, donor14, donor15, donor16, donor17, donor18, donor19, donor20};

        for (int i = 0; i < donors.length; i++) {
            donorMap.put(donors[i].getDonorId(), donors[i]);
        }
        return donorMap;
    }

    public static void main(String[] args) {
        DonorInitializer dInt = new DonorInitializer();
        HashMap<Integer, Donor> donorMap = dInt.initializeDonor();
        Iterator keyIt = donorMap.keySet().getIterator();
        DonorDAO donorDAO = new DonorDAO();
        donorDAO.saveToFile(donorMap);
    }

}
