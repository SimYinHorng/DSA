/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author user
 */
public class MessageUI {

    public static void displayInvalidChoiceMessage() {
        System.out.println("\nInvalid choice");
    }

    public static void displayExitMessage() {
        System.out.println("\nExiting system");
    }

    public static void displayDonorHeader() {
        line(205);
        System.out.printf("|%8s|%-25s|%-30s|%-15s|%-80s|%-11s|%-12s|%-15s|\n", "Donor Id", "Donor Name", "Donor Email", "Phone No.", "Address", "TYPE", "CATEGORY", "No. of Donation");
        line(205);
    }

    public static void line(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
