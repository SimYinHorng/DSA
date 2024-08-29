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

    public static void displayEventHeader() {
        line(305);
        System.out.printf(
                "%-10s %-20s %-30s %-15s %-15s %-10s %-10s %-20s%n",
                "Event Id", "Event Name", "Event Address", "Start Date", "End Date", "Start Time", "End Time", "Organizer"
        );
        line(305);
    }
     
    public static void displayVolunteerHeader() {
    line(250); 
    System.out.printf("%-14s %-25s | %-30s | %-15s | %-50s | %-10s | %-10s |\n",
                      "Volunteer Id", "Volunteer Name", "Volunteer Email", "Phone No.", "Address", "TYPE", "CATEGORY");
    line(205);
}

    public static void line(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }

    public static void clearScreen() {
        //this not working
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
