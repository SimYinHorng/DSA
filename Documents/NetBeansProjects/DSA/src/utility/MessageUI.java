/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class MessageUI {

    static Scanner scanner = new Scanner(System.in);

    public static void displayInvalidChoiceMessage() {
        System.out.println("\nInvalid choice");
        System.out.println("Press Enter to continue ... ");
        scanner.nextLine();
        clearScreen();
    }

    public static void enterToContinue() {
        System.out.println("Press Enter to continue ... ");
        scanner.nextLine();
    }

    public static void displayExitMessage() {
        System.out.println("\nExiting system");
    }

    public static void displayDonorHeader() {
        line(205);
        System.out.printf("|%8s|%-25s|%-30s|%-15s|%-80s|%-11s|%-12s|%-15s|\n", "Donor Id", "Donor Name", "Donor Email", "Phone No.", "Address", "TYPE", "CATEGORY", "No. of Donation");
        line(205);
    }

    public static void displayDoneeHeader() {
        line(290);
        System.out.printf("%-4s|%-8s|%-15s|%-25s|%-15s|%-60s|%-12s|%-15s|%-50s|%-15s|%-17s|%-15s|%15s|\n","No",
                "Donee Id", "Name", "Email", "Phone No.", "Address", "Category", "Date of Birth",
                "Needs Description", "Reg. Date", "Last Assist. Date", "Status", "No. of Donations");
        line(290);
    }

    public static void displayEventHeader() {
        line(400);
        System.out.printf(
                "| %-10s | %-45s | %-45s | %-12s | %-12s | %-12s | %-12s | %-66s | %-30s | %-30s | %-15s | %-10s | %-15s | %-20s | %-20s |\n",
                "Event ID", "Event Name", "Address", "Start Date", "End Date", "Start Time",
                "End Time", "Description", "Organizer", "Email", "Phone No", "Status",
                "Type", "Volunteer Need", "Available Volunteers"
        );
        line(400);

    }

    public static void displayVolunteerHeader() {
        line(239); 
            System.out.printf("| %-14s | %-25s | %-30s | %-15s | %-60s | %-16s | %-11s | %-25s | %15s |\n",
                      "Volunteer Id", "Volunteer Name", "Volunteer Email", "Phone No.", "Address", "Date Of Birth", "Gender", "Category", "No Event Assign");
        line(239);
    }

    public static void line(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }

    public static void clearScreen() {
        //this not working
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

}
