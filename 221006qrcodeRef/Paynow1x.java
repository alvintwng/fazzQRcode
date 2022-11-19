package com.zero1.paynow1x;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Sandbox mode
 *
 * Will create One-Time Paynow with Input Amount and RefId
 *
 * Follow by will trigger 'Received_Payment'
 *
 * @author AlvinNg
 */
public class Paynow1x {

    private static final String BASEURL = "https://sandbox.xfers.io/api";
    private static final String PASSWORD = "test";
    private static final String SECRET = "0pI4";

    private String bigString;
    private String paynowId;
    private String merchantPrefix = "Zero1";

    private JSONObject jObject = null;
    private Object jContractId;

    public void setjObject(JSONObject jObject) {
        this.jObject = jObject;
    }

    public JSONObject getjObject() {
        return jObject;
    }

    public String getBigString() {
        return bigString;
    }

    public String getPaynowId() {
        return paynowId;
    }

    /**
     * Temporary create customerId for Live mode, and sandbox mode.
     */
    public JSONObject create(double amount, String attrReferenceId) {

        JSONObject jObject = null;
        try {
            //String customerId = "customer_profile_a887e224-aa00-48f6-a6b9-f26a413a57f1";
            String body
                    = "{\"data\": {\"attributes\": {\"amount\": \""
                    + amount
                    + "\",\"referenceId\": \""
                    + attrReferenceId
                    + "\",\"paymentMethodType\": \"paynow\",\"paymentMethodOptions\": {\"merchantPrefix\": \""
                    + merchantPrefix
                    + "\"}}}}";

            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.post(BASEURL + "/v4/payments")
                    .header("Content-Type", "application/vnd.api+json")
                    .basicAuth(PASSWORD, SECRET)
                    .body(body)
                    .asString();

            String responseBody = response.getBody();
            jObject = new JSONObject(responseBody);
        } catch (UnirestException ex) {
            System.out.println(">>> Error on UnirestException ::: " + ex);
            System.exit(0);
        }

        this.setjObject(jObject);
        return jObject;
    }

    public void triggerPmt() {
        try {
            String body = "{\"data\": {\"attributes\": {\"action\": \"receive_payment\"}}}";
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.post(BASEURL
                    + "/v4/payments/" + jContractId + "/tasks")
                    .header("Content-Type", "application/vnd.api+json")
                    .basicAuth(PASSWORD, SECRET)
                    .body(body)
                    .asString();

            String responseBody = response.getBody();
            jObject = new JSONObject(responseBody);
        } catch (UnirestException ex) {
            System.out.println(">>> Error on UnirestException ::: " + ex);
            System.exit(0);
        }
    }

    /**
     * convert json to variables
     *
     * @param jObject
     * @return base64 qrcode String
     */
    private void extractQr(JSONObject jObject) {
        String qrCodeBase64 = "";
        try {
            JSONObject jData = (JSONObject) jObject.get("data");
            jContractId = jData.get("id");
            System.out.println(">> jContractId: " + jContractId);

            JSONObject jAttribute = (JSONObject) jData.get("attributes");
            Object jStatus = jAttribute.get("status");
            System.out.println(">> jStatus: " + jStatus);

            Object jAmount = jAttribute.get("amount");
            System.out.println(">> jAmount: " + jAmount);

            Object jExpiredAt = jAttribute.get("expiredAt");
            System.out.println(">> jExpiredAt: " + jExpiredAt);

            Object jReferenceId = jAttribute.get("referenceId");
            System.out.println(">> jReferenceId: " + jReferenceId);

            JSONObject jPaymentMethod = (JSONObject) jAttribute.get("paymentMethod");
            Object jId = jPaymentMethod.get("id");
            System.out.println(">> jId: " + jId);

            qrCodeBase64 = (String) jPaymentMethod.get("qrCodeDisplayImage");
            paynowId = (String) jId;

        } catch (JSONException e) {
            System.out.println(">>> Error on JSONException ::: " + e);
            System.out.println("Please check your inputs.");
            System.exit(0);
        }
        bigString = qrCodeBase64;
    }

    /**
     * to test this Sandbox code
     */
    public static void sandbox(double amt, String ref) {
        Paynow1x paynow = new Paynow1x();
        paynow.create(amt, ref);
        paynow.extractQr(paynow.jObject);
        paynow.triggerPmt();
        //System.out.println(generate.getBigString());
    }

    public static void main(String args[]) {
        Paynow1x.sandbox(0.1, "Inv221031E");
    }
}
/* Sample Dialogue
>>>> jObject >> {"data":{"attributes":{"createdAt":"2022-10-2 ...
>> jContractId: contract_90e001c5474946f9a1e636436621ca1d
>> jStatus: pending
>> jAmount: 0.1
>> jExpiredAt: 2022-10-27T07:30:30Z
>> jReferenceId: Inv221027E
>> jId: paynow_557bce30e7da50b1b45e9d23e8cd61be
 */
