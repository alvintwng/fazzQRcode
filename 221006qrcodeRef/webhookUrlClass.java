package com.zero1.testapi;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class UrlClass {

    public static void main(String args[]) {
        try {
            URL website = new URL("https://webhook.site/token/8b232988-c29e-4038-8c57-fd13f943822f/request/latest/raw");
            Scanner inputStream = new Scanner(
                    new InputStreamReader(website.openStream()));

            while (inputStream.hasNextLine()) {
                String s = inputStream.nextLine();
                System.out.println(s);
            }
            inputStream.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

/* Sample Dialogue
--- exec-maven-plugin:3.0.0:exec (default-cli) @ Paynow1x ---
{"data":{"id":"contract_daeee3c3c72e49808a0bd1251a142c12","type":"payment","attributes":{"st...
*/