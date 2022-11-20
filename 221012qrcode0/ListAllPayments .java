package com.zero1.qrcode1;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * To list all payments from Fazz.
 *
 * @author AlvinNg
 */
public class ListAllPayments {

    /**
     * Unirest to call Fazz List all payments, output json string. Unirest code
     * from Postman Fazz's collection:
     *
     * {@code
     *  curl https://sandbox.xfers.com/api/v4/payments \
     *      --header 'content-type: application/vnd.api+json' \
     *      -u test_api_key:secret_key
     * }
     *
     * @return JSONObject object
     */
    public JSONObject run() {
        JSONObject jObject = null;
        try {
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.get("https://sandbox.xfers.com/api/v4/payments")
                    .header("content-type", "application/vnd.api+json")
                    .header("Authorization", "Basic dGVzdF80OWJjOWQ0NGQ1NmQ3MjZhNTkwZWEzNWM3OGY3NzFjODowcEk0b0pkMkJ6N200OG5ya3lqaVZ3Ykh2dm1JY1pobw==")
                    .asString();

            //System.out.println(">>>> response >>> " + response.getBody());
            String responseBody = response.getBody();
            //System.out.println(">>>> response >>> " + responseBody); // >>>> response >>> {"data":[{"id":"contract_c1df2265a2e64491850a6 ...
            jObject = new JSONObject(responseBody);

        } catch (UnirestException e) {
            System.out.println("!!! UnirestException ::: " + e);
        }
        return jObject;
    }

     /**
     * Output necessary info to concole.
     *
     * @param jObject
     */
    public void extractQr(JSONObject jObject) {

        JSONArray jdata = (JSONArray) jObject.getJSONArray("data"); // >>>> jdata >>> [{"attributes":{"creat ...
        //System.out.println(">>>> jdata >>> " + jdata); // missong ContractId
        //System.out.println(">>>> jdata lgth >>> " + jdata.length()); // 10
        int indexLgth = jdata.length();
        System.out.println("contract id \t\t\t\t\t status amount");
        for (int i = 0; i < indexLgth; i++) {

            //System.out.println(">>>> jdata.get(0) >>> " + jdata.get(0)); //{"attributes":{"createdAt":"2022-10-07T10:50:49+08:00"
            //System.out.println(">>>> jdata.get(1) >>> " + jdata.get(1));// {"attributes":{"createdAt":"2022-10-07T10:43:13+08:00"
            JSONObject jData2 = (JSONObject) jdata.get(i);
            //System.out.println(">> jData2 lgth >> " + jData2.length()); //3
            //System.out.println(">> jData2 id >> " + jData2.get("id")); // contract_c1df2265a2e64491850a6e75a86bd29d
            System.out.print(jData2.get("id")); // contract_c1df2265a2e64491850a6e75a86bd29d
            //System.out.println(">> jData2 attributes >> " + jData2.get("attributes")); // {"createdAt":"2022-10-07T10:50:49+08:00","ex
            JSONObject jAttribute2 = (JSONObject) jData2.get("attributes");
            //System.out.println(" >>> jAttribute2 status >>> " + jAttribute2.get("status")); // pending
            //System.out.println(" >>> jAttribute2 amount >>> " + jAttribute2.get("amount")); // 1.3
            System.out.print("\t " + jAttribute2.get("amount")); // 1.3
            System.out.println("\t" + jAttribute2.get("status")); // pending
        }


    }

    public static void main(String[] args) {

        Wk4ListPayments example = new Wk4ListPayments();
//        example.run();
        JSONObject jObject = example.run();
        example.extractQr(jObject);
    }
}
/*
{
    "data": [
        {
            "id": "contract_02d4db2075314dfe83f1210e26f5c23e",
            "type": "payment",
            "attributes": {
                "status": "paid",
                "amount": "0.2",
                "createdAt": "2022-10-06T11:48:40+08:00",
                "description": null,
                "expiredAt": null,
                "referenceId": "paynow_test_4e41c484eb_1665028120",
                "fees": "0.0",
                "paymentMethod": {
                    "id": "paynow_e54323446b353fb3138bb8ba74546ecf",
                    "type": "paynow",
                    "referenceId": "Inv221006C",
                    "instructions": {
                        "qrCodeDisplayImage": "iVBORw0KGgoAAAANSUhEUgAAAYYA
 */
/* Console
contract id                                    status amount  createdAt                  referenceId                     
contract_c1df2265a2e64491850a6e75a86bd29d       pending  1.3  2022-10-07T10:50:49+08:00  Alvin221007C                    
contract_123fa2aac54847b38ee0257782bdd6ff          paid  0.1  2022-10-07T10:43:13+08:00  Alvin221007B 
...
 */ 
