package com.zero1.xfersnb;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

/**
 *
 * @author AlvinNg
 */
public class PostmanOkHttp {

    public static void main(String args[]) {

        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://postman-echo.com/get")
                    .get()
                    .addHeader("accept", "application/json")
                    .build();

            Response response = client.newCall(request).execute();
            
            System.out.println(">>> Class type:: " + response.getClass().getSimpleName());
            System.out.println(">>> respnse:: " + response.toString());
        } catch (IOException e) {
            System.out.println("!!! IOException:: " + e);
        }
    }
}
/* Sample Dialogue
--- exec-maven-plugin:3.0.0:exec (default-cli) @ xfersNB ---
>>> Class type:: Response
>>> respnse:: Response{protocol=http/1.1, code=200, message=OK, url=https://postman-echo.com/get}
------------------------------------------------------------------------
*/
