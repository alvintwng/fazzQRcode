package com.zero1.qrcode1;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.Scanner;
import org.json.JSONObject;

/**
 * To generate Paynow Base64_qrCode from Fazz, output to screen(console)
 *
 * @author AlvinNg
 */
public class Generate {

    /**
     * Unirest to call Fazz payment paynow, output json string. Unirest code
     * from Postman Fazz's collection:
     *
     * {@code
     *      curl --request POST \
     *          --url https://sandbox.xfers.io/api/v4/payments \
     *          --header 'accept: application/json' \
     *                --header 'content-type: application/json' \
     *                --data '{
     *           "data": {
     *                   "attributes": {
     *                   "merchantPrefix": "ZERO1PTE",
     *                           "referenceId": "INVOICE012345678901234567890123456789"
     *                   }
     *           }'
     * }
     *
     * @param customerId
     * @param merchantPrefix
     * @param attrReferenceId
     * @return JSONObject object
     */
    public JSONObject run(String customerId, String merchantPrefix, String attrReferenceId) {
        JSONObject jObject = null;
        try {
            //String customerId = "ALVIN012";
            //String merchantPrefix = "ZERO1PTE";
            //String attrReferenceId = "INV01234567890123"; // Not allow to duplicate
            String body
                    = "{\"data\": {\"relationships\": {\"customerProfile\": {\"data\": {\"id\": \""
                    + customerId
                    + "\"}}},\"attributes\": {\"merchantPrefix\": \""
                    + merchantPrefix
                    + "\",\"referenceId\": \""
                    + attrReferenceId
                    + "\"}}}";

            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.post("https://sandbox.xfers.io/api/v4/payment_methods/paynow")
                    .header("Content-Type", "application/vnd.api+json")
                    .header("Authorization", "Basic dGVzdF80OWJjOWQ0NGQ1NmQ3MjZhNTkwZWEzNWM3OGY3NzFjODowcEk0b0pkMkJ6N200OG5ya3lqaVZ3Ykh2dm1JY1pobw==")
                    .body(body)
                    .asString();

            String responseBody = response.getBody();
            jObject = new JSONObject(responseBody);
        } catch (UnirestException ex) {
            System.out.println("!!! UnirestException ::: " + ex);
        }
        return jObject;
    }

    /**
     * convert json variables
     *
     * @param jObject
     * @return base64 String
     */
    public String extractQr(JSONObject jObject) {
        JSONObject jData = (JSONObject) jObject.get("data");
        JSONObject jAttribute = (JSONObject) jData.get("attributes");
        JSONObject jInstructions = (JSONObject) jAttribute.get("instructions");
        String qrCodeBase64 = (String) jInstructions.get("qrCodeDisplayImage");
        return qrCodeBase64;
    }

    /**
     * variables input and execute the code
     *
     * @return base64 String
     */
    public static String main() {
        System.out.println("TO generate qrcode");
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Customer Id:: ");
        String customerId = keyboard.nextLine();

        System.out.print("Merchant Prefix (4-8 chars):: ");
        String merchantPrefix = keyboard.nextLine();

        System.out.print("Reference Id (no duplicate):: ");
        String attrReferenceId = keyboard.nextLine();

        Generate runcode = new Generate();
        JSONObject jObject = runcode.run(customerId, merchantPrefix, attrReferenceId);
        String qrcodeBase64 = runcode.extractQr(jObject);
        System.out.println(" qrcode ::");

        return qrcodeBase64;
    }

    /**
     * to run just this code
     */
    public static void main(String[] args) {
        System.out.println(
                main());
    }
}
/* Sample Dialogue
C:\Users\AlvinNg\wk4base64qr\target>java -jar wk4base64qr-1.0-SNAPSHOT-jar-with-dependencies.jar
TO generate qrcode
Customer Id:: ALVIN012
Merchant Prefix (4-8 chars):: ZERO1PTE
Reference Id (no duplicate):: Inv2210101458
 qrcode ::
iVBORw0KGgoAAAANSUhEUg...
 */
/* response
{
    "data": {
        "id": "paynow_08e5649e57169a1402b0fb2eabf6cc25",
        "type": "paynow",
        "attributes": {
            "referenceId": "INVOICE01234567890123456789",
            "instructions": {
                "qrCodeDisplayImage": "iVBORw0KGgoAAAANSUhEUgAAAYYAAAGGCAYAAAB/gCblAA...
*/
