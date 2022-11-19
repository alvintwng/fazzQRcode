package com.zero1.wk3testjar;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 *
 * @author AlvinNg
 */
public class GetPaymentViaContractIdJAR {

    public void run() {
        try {
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.get("https://sandbox.xfers.io/api/v4/payments/contract_123fa2aac54847b38ee0257782bdd6ff/")
                    .header("Content-Type", "application/vnd.api+json")
                    .header("Authorization", "Basic dGVzdF80OWJjOWQ0NGQ1NmQ3MjZhNTkwZWEzNWM3OGY3NzFjODowcEk0b0pkMkJ6N200OG5ya3lqaVZ3Ykh2dm1JY1pobw==")
                    .asString();

            System.out.println(">>>> response >>> " + response.getBody());
        } catch (UnirestException ex) {
            System.out.println("!!! UnirestException ::: " + ex);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        GetPaymentViaContractIdJAR example = new GetPaymentViaContractIdJAR();
        example.run();
    }
}

/* Configure to run JAR
1. Properites > Run > `com.zero1.wk3testjar.GetPaymentViaContractIdJAR`
2. pom.xml > added `<build> ... </build>`
3. java -jar <jarfile>.jar

Sample Dialogue
C:\Users\AlvinNg\wk3TestJar\target>java -jar wk3TestJar-1.0-SNAPSHOT-jar-with-dependencies.jar
Hello World!
>>>> response >>> {"data":{"id":"contract_123fa2aac54847b38ee0257782bdd6ff","type":"payment","attributes":{"status":"paid","amount":"0.1",
"createdAt":"2022-10-07T10:43:13+08:00","description":null,"expiredAt":"2022-11-01T20:03:53+07:00","referenceId":"Alvin221007B","fees":"0.0",
"paymentMethod":{"id":"paynow_ae98a6a949ab9d20d0a3c89982e2b89b","type":"paynow","qrCodeDisplayImage":
"iVBORw0KGgoAAAANSUhEUgAAAYYAAAGGCAYAAAB/gCblAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdT...uAAAAAElFTkSuQmCC"}}}}
------------------------------------------------------------------------
BUILD SUCCESS
------------------------------------------------------------------------
Total time:  2.323 s
Finished at: 2022-10-07T11:12:40+08:00
------------------------------------------------------------------------
*/
