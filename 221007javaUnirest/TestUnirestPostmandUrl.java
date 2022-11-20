package com.zero1.xfersnb;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * https://www.tabnine.com/code/java/methods/com.mashape.unirest.http.Unirest/setTimeouts
 *
 * @author AlvinNg
 */
public class TestUnirestPostmandUrl {

    public void run() {
        try {
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.get("https://postman-echo.com/get")
                    .header("Cookie", "sails.sid=s%3ABX7eOsvvksgLG3b1xU9MY_q7LSOFn5rA.Xs62rbxdN3wycOYNXcyB4%2BQqkfO5yy2eWyTZmWuWwWQ")
                    .asString();

            System.out.println(">>>> response >>> " + response.getBody());
        } catch (UnirestException ex) {
            System.out.println("!!! UnirestException ::: " + ex);
        }
    }

    public static void main(String args[]) {
        TestUnirestPostmandUrl example = new TestUnirestPostmandUrl();
        example.run();
    }
}

/* Sample Dialogue
--- exec-maven-plugin:3.0.0:exec (default-cli) @ xfersNB ---
>>>> response >>> {"args":{},"headers":{"x-forwarded-proto":"https",
"x-forwarded-port":"443","host":"postman-echo.com",
"x-amzn-trace-id":"Root=1-6332b2a3-177a1b6922c06f426aba5a87","accept-encoding":"gzip",
"cookie":"sails.sid=s%3ABX7eOsvvksgLG3b1xU9MY_q7LSOFn5rA.Xs62rbxdN3wycOYNXcyB4%2BQqkfO5yy2eWyTZmWuWwWQ",
"user-agent":"unirest-java/1.3.11"},"url":"https://postman-echo.com/get"}
------------------------------------------------------------------------
BUILD SUCCESS
 */
