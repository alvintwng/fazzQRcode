package com.zero1.qrcode1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * To generate const qrcodeBase64 in jsfile, qrcodeBase64.js.
 * To be read by html for display qr image.
 *
 * const qrcodeBase64=' ...<String> '
 * 
 * @author AlvinNg
 */
public class JsBase64 {

    public static void run(String bigString) {
        String textfile = "qrcodeBase64.js";

        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream(textfile));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file " + textfile);
            System.exit(0);
        }
        outputStream.print("const qrcodeBase64=' ");
        outputStream.print(bigString);
        outputStream.println("  '");

        outputStream.close();

        System.out.println("QRCode Base64 String generated to file: " + textfile);
    }
}
