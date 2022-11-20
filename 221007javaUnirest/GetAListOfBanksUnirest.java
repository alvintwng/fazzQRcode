package com.zero1.xfersnb;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 *
 * @author AlvinNg
 */
public class GetAListOfBanksUnirest {

    public void run() {
        try {
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.get("https://sandbox.xfers.io/api/v4/banks")
                    .header("Content-Type", "application/vnd.api+json")
                    .header("Authorization", "Basic dGVzdF80OWJjOWQ0NGQ1NmQ3MjZhNTkwZWEzNWM3OGY3NzFjODowcEk0b0pkMkJ6N200OG5ya3lqaVZ3Ykh2dm1JY1pobw==")
                    .asString();

            System.out.println(">>>> response >>> " + response.getBody());
        } catch (UnirestException ex) {
            System.out.println("!!! UnirestException ::: " + ex);
        }
    }

    public static void main(String args[]) {
        GetAListOfBanksUnirest example = new GetAListOfBanksUnirest();
        example.run();
    }
}

/* Sample Dialogue
>>>> response >>> {"data":[{"id":"dbs","type":"bank","attributes":{"name":"Development Bank ..
------------------------------------------------------------------------
BUILD SUCCESS
*/