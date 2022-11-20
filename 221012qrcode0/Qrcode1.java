package com.zero1.qrcode1;

import java.util.Scanner;

/**
 * Main menu
 *
 * @author AlvinNg
 */
public class Qrcode1 {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        boolean toContinue = true;
        do {
            System.out.println("Select:");
            System.out.println("1. Generate QR base64 to console");
            System.out.println("2. Generate QR base64 to JS file");
            System.out.println("3. List all payments");
            System.out.print("Choice:  ");
            if (!keyboard.hasNextInt()) {
                System.exit(0);
            }
            int choice = keyboard.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println();
                    System.out.println(Generate.main());
                }
                case 2 -> {
                    System.out.println();
                    String bigString = Generate.main();
                    JsBase64.run(bigString);
                }
                case 3 -> {
                    System.out.println();
                    ListAllPayments.main(args);
                }
                default ->
                    System.exit(0);
            }
            System.out.println("\nTo continue: 'Y'?");
            String cont = keyboard.next();
            toContinue = (cont.equalsIgnoreCase("Y"));
        } while (toContinue);
    }
}