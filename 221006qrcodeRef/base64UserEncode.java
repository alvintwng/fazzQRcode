package com.zero1.wk3testjar;

import org.apache.commons.codec.binary.Base64;

public class Base64UserEncode {

    public static void Base64Encode(String str) {
        // import org.apache.commons.codec.binary.Base64;

        // Encode data on your side using BASE64
        byte[] bytesEncoded = Base64.encodeBase64(str.getBytes());
        System.out.println("encoded value is " + new String(bytesEncoded));
        // ref: https://www.itcodar.com/csharp/how-to-encode-and-decode-a-base64-string.html
    }

    public static void main(String args[]) {
        Base64Encode("test_49bc9d44d56d726a590ea35c78f771c8:0pI4oJd2Bz7m48nrkyjiVwbHvvmIcZho");
    }
}
/* Sample Dialogue
--- exec-maven-plugin:3.0.0:exec (default-cli) @ wk3TestJar ---
encoded value is dGVzdF80OWJjOWQ0NGQ1NmQ3MjZhNTkwZWEzNWM3OGY3NzFjODowcEk0b0pkMkJ6N200OG5ya3lqaVZ3Ykh2dm1JY1pobw==
------------------------------------------------------------------------
 */
