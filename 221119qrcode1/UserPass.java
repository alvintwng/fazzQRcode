package com.zero1.qrcode1;

import org.apache.commons.codec.binary.Base64;

/**
 * To encode the API_KEY with the SECRET_KEY.
 *
 * @author AlvinNg
 */
public class UserPass {

    private String apikey = "test_49bc9d44d56d726a590ea35c78f771c8"; // Sandbox Api_key
    private String secret = "0pI4oJd2Bz7m48nrkyjiVwbHvvmIcZho"; // Sandbox Seccret_key
    private String encoded;

    /**
     * Constructor to use the preset apikey and secret pass. And generate
     * encoded string, which obtain at getEncoded().
     */
    public UserPass() {
        bytesEncodedStr();
    }

    /**
     * Constructor to use the new apikey and new pass. And generate encoded
     * string.
     *
     * @param apikey
     * @param secret
     */
    public UserPass(String apikey, String secret) {
        this.apikey = apikey;
        this.secret = secret;
        bytesEncodedStr();
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     *
     * @return encoded String
     */
    public String getEncoded() {
        return encoded;
    }

    private void bytesEncodedStr() {
        String str = apikey + ":" + secret;
        byte[] bytesEncoded = Base64.encodeBase64(str.getBytes());
        encoded = new String(bytesEncoded);
    }
    
    public static String encodedString(String apikey, String secret) {
        UserPass u = new UserPass(apikey, secret);
        return u.getEncoded();
    }

    /**
     * To test this code only.
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        UserPass u = new UserPass("this", "that");
        System.out.println(">>> " + u.getEncoded());
    }
}
