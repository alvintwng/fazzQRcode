/**
 * Last revised date: 221111, Alvin
 * https://github.com/Zero1-Philip/fazz-callback-sample/tree/main/qrcode1
 */
package com.zero1.qrcode1;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * To generate const qrcodeBase64 in jsfile, qrcodeBase64.js:.
 *
 * const qrcodeBase64=' ...<String> '
 *
 * @see Qrcode1.java
 */
public class JsBase64 {

    public static void run(Generate gen) {
        String bigString = gen.getBigString();
        String paynowId = gen.getPaynowId();
        String attrReferenceId = gen.getAttrReferenceId();

        // jsfile name
        String textfile = "qrcodeBase64.js";

        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream(textfile));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file " + textfile);
            System.exit(0);
        }
        outputStream.print("const qrcodeBase64='");
        outputStream.print(bigString);
        outputStream.println("'; ");

        outputStream.print("const paynowId='");
        outputStream.print(paynowId);
        outputStream.println("'; ");

        outputStream.print("const attrReferenceId='");
        outputStream.print(attrReferenceId);
        outputStream.println("'; ");

        outputStream.close();

        System.out.println("QRCode Base64 String generated to file: " + textfile);
    }

    /**
     * url String to open in browser
     *
     * @param url, html file, path, image
     */
    public static void openInBrowser(String url) {
        File htmlFile = new File(url);
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException ex) {
            System.out.println(">>>> " + ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        JsBase64.openInBrowser("JsfileToImg.html");
    }
}
