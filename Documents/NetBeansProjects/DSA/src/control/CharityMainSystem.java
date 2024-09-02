/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import boundary.DonationManagementUI;
import java.util.InputMismatchException;
import java.util.Scanner;
import utility.MessageUI;
import static utility.MessageUI.line;

/**
 *
 * @author user
 */
public class CharityMainSystem {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = -1;
        do {
            MessageUI.clearScreen();
            line(15);
            System.out.println("CHARITY SYSTEM");
            line(15);
            System.out.println("1. Donor Management Subsystem");
            System.out.println("2. Donee Management Subsystem");
            System.out.println("3. Donation Management Subsystem");
            System.out.println("4. Event Management Subsystem");
            System.out.println("5. Volunteer Management Subsystem");
            System.out.println("0. Quit");
            System.out.print("Enter choice: ");
            try {
                choice= scanner.nextInt();
                scanner.nextLine();

            } catch (InputMismatchException e) {
                scanner.nextLine();
                
            }
            switch (choice) {
                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1:
                    DonorManagement donorManagement = new DonorManagement();
                    donorManagement.runDonorManagement();
                    break;
                case 2:
                    DoneeManagement mainDonee = new DoneeManagement();
                    mainDonee.runDoneeManagement();
                    break;
                case 3:
                    DonationManagementUI ui = new DonationManagementUI();
                    ui.run();
                    break;
                case 4:
                    EventManagement eventManagement = new EventManagement();
                    eventManagement.runEventManagement();
                    break;
                case 5:
                    VolunteerManagement volunteerManagement = new VolunteerManagement();
                    volunteerManagement.runVolunteerManagement();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
                    break;
            }
        } while (choice != 0);
    }

}
