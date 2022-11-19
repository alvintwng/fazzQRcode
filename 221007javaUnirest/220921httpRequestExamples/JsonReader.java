
package com.zero1.xfersnb;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * https://www.javainterviewpoint.com/read-json-java-jsonobject-jsonarray/
 *
 * @author AlvinNg
 */
public class JsonReader {

    public static String postman() {
        String x = "Hello";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://postman-echo.com/get"))
                    .header("accept", "application/json")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println(response.body());
            System.out.println(">>>> response:" + response.getClass().toString());
            return response.body();
        } catch (InterruptedException | IOException e) {
            System.out.println("Exception: " + e);
            System.exit(0);
        }

        return x;
    }

    public static void main(String args[]) {

        String data = postman();
        //System.out.println(data);
        /*
                {
            "args":{},
            "headers":{
                "x-forwarded-proto":"https",
                "x-forwarded-port":"443",
                "host":"postman-echo.com",
                "x-amzn-trace-id":"Root=1-632abcfe-771f8bb7444def3d2b5a83b2",
                "accept":"application/json",
                "user-agent":"Java-http-client/17.0.4.1"},
                "url":"https://postman-echo.com/get"
            }
         */

        JSONParser parser = new JSONParser();
        try {
            Object jsonObj = parser
                    .parse(data);

            //convert Object to JSONObject
            JSONObject jsonObject = (JSONObject) jsonObj;

            //Reading the String
            Object temp = (Object) jsonObject.get("args");

            System.out.println(">> temp: " + temp.getClass().getSimpleName());

            JSONObject headers = (JSONObject) jsonObject.get("headers");
            System.out.println(">> headers: " + headers.getClass().getSimpleName());

            //Reading the String
            String host = (String) headers.get("host");
            System.out.println("host: " + host);

            String accept = (String) headers.get("accept");
            System.out.println("accept: " + accept);

        } catch (Exception e) {
            System.out.println("!!! Exception: " + e);
        }
    }
}

/* Sample Dialogue
--- exec-maven-plugin:3.0.0:exec (default-cli) @ xfersNB ---
>>>> response:class jdk.internal.net.http.HttpResponseImpl
>> temp: JSONObject
>> headers: JSONObject
host: postman-echo.com
accept: application/json
------------------------------------------------------------------------
BUILD SUCCESS
------------------------------------------------------------------------
Total time:  2.668 s
Finished at: 2022-09-21T17:56:27+08:00
------------------------------------------------------------------------
*/
