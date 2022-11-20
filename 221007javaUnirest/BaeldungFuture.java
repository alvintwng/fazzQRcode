package com.zero1.testapi;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Handling Asynchronous Requests
 *
 * Unirest also has the capability to handle asynchronous requests – using
 * java.util.concurrent.Future and callback methods.
 *
 * The com.mashape.unirest.http.async.Callback<T> interface provides three
 * methods, failed(), cancelled() and completed().
 *
 * Override the methods to perform the necessary operations depending on the
 * response.
 *
 * ref: https://www.baeldung.com/unirest#handling-asynchronous-requests
 *
 * @author AlvinNg
 */
public class BaeldungFuture {

    //@Test
    public void whenAysncRequestShouldReturnOk() {

        try {
            Future<HttpResponse<JsonNode>> future = Unirest.post(
                    "http://www.mocky.io/v2/5a9ce37b3100004f00ab5154?mocky-delay=10000ms")
                    .header("accept", "application/json")
                    .asJsonAsync(new Callback<JsonNode>() {

                        public void failed(UnirestException e) {
                            // Do something if the request failed
                            System.out.println(">> falied.");
                        }

                        public void completed(HttpResponse<JsonNode> response) {
                            // Do something if the request is successful
                            System.out.println(">> completed.");
                        }

                        public void cancelled() {
                            // Do something if the request is cancelled
                            System.out.println(">> cancelled.");
                        }
                    });

            //assertEquals(200, future.get().getStatus());
            System.out.println("future.get().getStatus(): "
                    + future.get().getStatus());

            //Unirest spawns a background event loop to handle the operations,
            //we need to shut down that loop before exiting our application:
            Unirest.shutdown();

        } catch (InterruptedException ex) {
            System.out.println(">>>> InterruptedException >>> " + ex);
        } catch (ExecutionException ex) {
            System.out.println(">>>> ExecutionException >>> " + ex);
        } catch (IOException ex) {
            System.out.println(">>>> IOException >>> " + ex);
        }
    }

    public static void main(String args[]) {

        BaeldungFuture f = new BaeldungFuture();
        f.whenAysncRequestShouldReturnOk();

    }
}

/* Sample Dialogue
--- exec-maven-plugin:3.0.0:exec (default-cli) @ Paynow1x ---
>> completed.
assertEquals(200, future.get().getStatus());200

 */
