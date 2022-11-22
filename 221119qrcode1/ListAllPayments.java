/**
 * Last revised date: 221118, Alvin
 * https://github.com/Zero1-Philip/fazz-callback-sample/tree/main/qrcode1
 */
package com.zero1.qrcode1;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * To list all payments from FAZZ.
 *
 * @see Qrcode1.java
 */
public class ListAllPayments extends FazzAttribute {

    public ListAllPayments() {
    }

    public ListAllPayments(String baseUrl, String apiKey, String password) {
        super(baseUrl, apiKey, password);
    }

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
    private JSONObject httpGetListAllPaymt() {
        JSONObject jObject = null;
        try {
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.get(getBaseUrl() + "/v4/payments")
                    .header("content-type", "application/vnd.api+json")
                    .basicAuth(getApikey(), getPassword())
                    .asString();
            String responseBody = response.getBody();
            jObject = new JSONObject(responseBody);

        } catch (UnirestException e) {
            System.out.println("!!! UnirestException ::: " + e);
        } catch (Exception e) {
            System.out.println("> Exception Error at ListAllPayments.java :: " + e);
            System.exit(0);
        }
        return jObject;
    }

    public JSONObject getHttpGetListAllPaymt() {
        return httpGetListAllPaymt();
    }

    /**
     * Print out payments list from JSON to concole.
     *
     * @param jObject
     */
    private void extractQr(JSONObject jObject) {
        try {
            JSONArray jdata = (JSONArray) jObject.getJSONArray("data");

            System.out.printf("%-40s %12s %5s  %-25s  %-30s  %n", "contract id",
                    "status", "amount", "createdAt", "reference/paynow Id");
            System.out.printf("%-40s %12s %5s  %-25s  %-30s  %n", "-----------",
                    "------", "------", "---------", "-------------------");

            int indexLgth = jdata.length();
            for (int i = 0; i < indexLgth; i++) {
                JSONObject jData2 = (JSONObject) jdata.get(i);
                JSONObject jAttribute2 = (JSONObject) jData2.get("attributes");
                System.out.print(jData2.get("id"));
                System.out.printf("  %12s", (jAttribute2.get("status"))); // pending
                System.out.printf("%5s  ", (jAttribute2.get("amount"))); // 1.3
                System.out.printf("%25s  ", (jAttribute2.get("createdAt")));
                System.out.printf("%-30s  ", (jAttribute2.get("referenceId")));
                System.out.println("");
            }
        } catch (JSONException e) {
            System.out.println("> Exception Error JSONException at ListAllPayments.java :: " + e);
        }
    }

    /**
     * To list all payments
     */
    public void generatePaymentList() {
        JSONObject jObject = httpGetListAllPaymt();
        extractQr(jObject);
    }

    /**
     * to test in this Sandbox code
     */
    public static void main(String[] args) {
        ListAllPayments list = new ListAllPayments();
        list.setSandbox();
        list.setAPI(list.getBaseUrl(), list.getApikey(), list.getPassword());
//        f.setAPI(f.getBaseUrl(), "test_wrong_String", f.getPassword());
        list.generatePaymentList();
    }
}
/* Console

contract id                                    status amount  createdAt                  referenceId                     
contract_c1df2265a2e64491850a6e75a86bd29d       pending  1.3  2022-10-07T10:50:49+08:00  Alvin221007C                    
contract_123fa2aac54847b38ee0257782bdd6ff          paid  0.1  2022-10-07T10:43:13+08:00  Alvin221007B 
...
 */
