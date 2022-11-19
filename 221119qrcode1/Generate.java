/**
 * Last revised date: 221118, Alvin
 * https://github.com/Zero1-Philip/fazz-callback-sample/tree/main/qrcode1
 */
package com.zero1.qrcode1;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * To generate Paynow Base64_qrCode from Fazz, output to screen(console)
 *
 * Fazz version used: v4-SG-FAST
 *
 * @see Qrcode1.java
 */
public class Generate extends FazzAttribute {

    public Generate() {
    }

    public Generate(String baseUrl, String apiKey, String password) {
        setBaseUrl(baseUrl);
        setApikey(apiKey);
        setPassword(password);
    }

    /**
     * Unirest to call Fazz payment paynow, output json string. Unirest code
     * from Postman Fazz's collection:
     * <pre>
     * curl --location --request POST 'https://sandbox.xfers.io/api/v4/payment_methods/paynow' \
     * --header 'Content-Type: application/vnd.api+json' \
     * --header 'Authorization: Basic dGVzdF80OWJjOWQ0NGQ1NmQ3Mj...' \
     * --data-raw '{
     * "data": {
     * "attributes": {
     * "referenceId": "QR221117A",
     * "bankShortCode": "fazz"
     * }}}' </pre>
     *
     * @param customerId
     * @param attrReferenceId
     * @return JSONObject object
     */
    private JSONObject httpPostPaynowQR(String attrReferenceId) {

        JSONObject jObject = null;
        try {
            String body = "{\"data\": {\"attributes\": {\"referenceId\": \""
                    + attrReferenceId
                    + "\",\"bankShortCode\": \"fazz\"}}}";

            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.post(getBaseUrl() + "/v4/payment_methods/paynow")
                    .header("Content-Type", "application/vnd.api+json")
                    .basicAuth(getApikey(), getPassword())
                    .body(body)
                    .asString();

            String responseBody = response.getBody();
            jObject = new JSONObject(responseBody);
        } catch (UnirestException ex) {
            System.out.println(">>> Error on UnirestException ::: " + ex);
        }
        return jObject;
    }

    /**
     * convert json to variables.
     *
     * Sample json
     * <pre>  {
     * "data": {
     * "id": "paynow_20ab8438a314fe60242c0f3b197c38f7",
     * "type": "paynow",
     * "attributes": {
     * "referenceId": "qr221117a",
     * "instructions": {
     * "qrCodeDisplayImage": "iVBORw0KGgoAAAANSUhEUgAA...
     * }}} </pre>
     *
     * @param jObject
     * @return base64 qrcode String
     */
    private void extractQr(JSONObject jObject) {
        String qrCodeBase64 = "";
        try {
            JSONObject jData = (JSONObject) jObject.get("data");
            JSONObject jAttribute = (JSONObject) jData.get("attributes");
            JSONObject jInstructions = (JSONObject) jAttribute.get("instructions");

            String paynowIdStr = (String) jData.get("id");
            System.out.println("Paynow id :: " + paynowIdStr);
            this.setPaynowId(paynowIdStr);
            qrCodeBase64 = (String) jInstructions.get("qrCodeDisplayImage");
        } catch (JSONException e) {
            System.out.println(">>> Error on JSONException ::: " + e);
            System.out.println("Please check your inputs.");
            System.exit(0);
        }
        this.setBigString(qrCodeBase64);
    }

    /**
     * variables input and execute the code
     *
     * @return base64 String
     */
    public void compute() {
        System.out.println("Input QRCode reference");
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Reference Id : ");

        String attrReferenceId = keyboard.nextLine();
        this.setAttrReferenceId(attrReferenceId);

        JSONObject jObject = httpPostPaynowQR(attrReferenceId);
        extractQr(jObject);
    }

    /**
     * to test this Sandbox code
     */
    public static void main(String[] args) {
        Generate generate = new Generate();
        generate.setSandbox();
        generate.compute();
        System.out.println(" QRCode ::" + generate.getBigString());
    }
}
/* Sample Dialogue
Input reference:
Reference Id :: QR221117A
 paynow id :: paynow_20ab8438a314fe60242c0f3b197c38f7
 QRCode ::iVBORw0KGgoAAAANSUhEUgAAAYYAAAGGCAYAAAB/...
 */
