/**
 * Last revised date: 221118, Alvin
 * https://github.com/Zero1-Philip/fazz-callback-sample/tree/main/qrcode1
 */
package com.zero1.qrcode1;

import java.util.Scanner;

/**
 * Main menu
 *
 * @see main(String[] args)
 */
public class Qrcode1 extends FazzAttribute {

    private Generate generate;
    private ListAllPayments listPayments;
    private static boolean mode = false; // true is LIVE mode

    /**
     * Command prompt menu to select codes
     */
    public void selectionMenu() {
        Scanner keyboard = new Scanner(System.in);

        generate = new Generate(
                getBaseUrl(), getApikey(), getPassword());

        listPayments = new ListAllPayments(
                getBaseUrl(), getApikey(), getPassword());

        boolean toContinue = true;
        doWhile:
        do {
            System.out.println("\nSelect:");
            System.out.println("1. Generate QR base64 to console");
            System.out.println("2. Generate Fazz QRCode to browser");
            System.out.println("3. List all payments");
            System.out.println("4. Beaconstac QRCode");
            System.out.println("9. Exit");
            System.out.print("Choice:  ");
            if (!keyboard.hasNextInt()) {
                keyboard.next();
                continue doWhile;
            }
            int choice = keyboard.nextInt();

            switch (choice) {
                case 1: {
                    System.out.println("\n:: Generate QR base64 to console ::");
                    generate.compute();
                    System.out.println(" QRCode :: " + generate.getBigString());
                    break;
                }
                case 2: {
                    System.out.println("\n:: Generate Fazz QRCode to browser ::");
                    generate.compute();
                    JsBase64.run(generate);
                    JsBase64.openInBrowser("JsfileToImg.html");
                    break;
                }
                case 3: {
                    System.out.println("\n:: List all payments ::");
                    listPayments.generatePaymentList();
                    break;
                }
                case 4: {
                    System.out.println("\n:: Beaconstac QRCode ::");
                    generate.compute();
                    Beaconstac.generateBase64ToQRCode(generate.getBigString());
                    break;
                }
                case 9: {
                    System.out.println("\nThanks for using ...");
                    toContinue = false;
                    break;
                }
                default:
                    System.out.println("'9' to Exit.");
            }
        } while (toContinue);
    }

    /**
     * Main code to start.
     *
     * right-click, choose `Run File`, result will shown at console.
     *
     * @param args
     */
    public static void main(String[] args) {

        Qrcode1 q = new Qrcode1();

        Scanner keyboard = new Scanner(System.in);
        System.out.print("Please enter 'LIVE' or else will be in sandbox mode: ");
        String liveOrSand = keyboard.nextLine();

        mode = liveOrSand.equalsIgnoreCase("live");

        if (mode) {

            q.setBaseUrl("https://www.xfers.io/api");
            System.out.print("Api_Key :: ");
            q.setApikey(keyboard.nextLine());
            System.out.print("Password :: ");
            q.setPassword(keyboard.nextLine());

            System.out.println("\n === LIVE mode ===");

        } else {
            // set attributes to Sandbox mode
            q.setSandbox();

            System.out.println("\n=== SANDBOX mode ===");
        }

        System.out.println("BaseUrl :: " + q.getBaseUrl());
        System.out.println("Api_Key :: " + q.getApikey());
        System.out.println("Password :: " + q.getPassword());

        // Execute QRCode1, after all necessary information inputs
        q.selectionMenu();
    }
}

/* Command Prompt:
C:\Users\...>java -jar qrcode1-dependencies.jar
Please enter 'LIVE' or else will be in sandbox mode: 

=== SANDBOX mode ===
Enter baseUrl :: https://sandbox.xfers.io/api
Api_Key :: test_49bc9d44d56d726a590ea35c78f771c8
Password :: 0pI4oJd2Bz7m48nrkyjiVwbHvvmIcZho

Select:
1. Generate QR base64 to console
2. Generate QR base64 to JS file
3. List all payments
4. Beacontac QRCode
9. Exit
Choice:  4

:: Beaconstac QRCode ::
Input reference:
Reference Id :: Inv221110A
 paynow id :: paynow_dd2c87d14cd980ae23d70033f78dd95e
 qrcode ::
beaconstac qrcode generator
> urlText: 00000101021226500009SG.PAYNOW010120213201411660RXFR0...
Input QR image file name: Sandbox
Input QR Frame Text: This is sandbox only
> Beaconstac image Id: 1171804
Qrcode may download from :: https://beaconstac-content....

Select:
 */
