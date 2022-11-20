/**
 * Last revised date: 221118, Alvin
 * https://github.com/Zero1-Philip/fazz-callback-sample/tree/main/qrcode1
 */
package com.zero1.qrcode1;

import java.util.Scanner;
import org.json.JSONObject;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

/**
 * Beaconstac format api to generate qrcode, and download qrcode.
 *
 * ref: https://dashboard.beaconstac.com/
 *
 * ref: https://www.beaconstac.com/
 *
 * @see Qrcode1.java
 * @see generateQRCode()
 */
public class Beaconstac {

    private final String BASE_URL = "https://api.beaconstac.com/api";
    private String ORG_ID;      //   beaconstac registered info
    private String TOKEN;       //   beaconstac registered info

//    public void setORG_ID(String ORG_ID) {
//        this.ORG_ID = ORG_ID;
//    }
//
//    public void setTOKEN(String TOKEN) {
//        this.TOKEN = TOKEN;
//    }
    /**
     * To convert base64 String to qrcode return text
     *
     * @param base64 base64 String, from FAZZ
     * @return text scanned from this qrcode, URL of the QR Code
     */
    public static String base64ToText(String base64) {

        String qrText = "";

        try {
            // Base64 decode
            byte[] decodedBytes = Base64.getDecoder().decode(base64);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(decodedBytes);

            // `Scan` the image from base64 String, output as text  
            BufferedImage bfrdImgobj = ImageIO.read(inputStream);
            LuminanceSource source = new BufferedImageLuminanceSource(bfrdImgobj);
            BinaryBitmap binarybitmapobj = new BinaryBitmap(new HybridBinarizer(source));
            Result resultobj = new MultiFormatReader().decode(binarybitmapobj);

            qrText = resultobj.getText();
            return qrText;
        } catch (NotFoundException e) {
            System.out.println("\nSorry.. Something went wrong...\n");
            System.out.println(">>> " + e.getMessage());
        } catch (IOException e) {
            System.out.println(">>> " + e.getMessage());

        }
        return qrText;
    }

    /**
     * To generate Beaconstac qrcode base on input text
     *
     * @param qrText String, URL of the QR Code
     * @return beaconstac id of the QR Code
     */
    private Integer httpPostQRGenerate(String qrText) {

        Scanner keyboard = new Scanner(System.in);

        // Input nexcessary reference texts
        System.out.print("Organization ID : ");
        ORG_ID = keyboard.next();
        System.out.print("Token : ");
        TOKEN = "TOKEN " + keyboard.next();
        keyboard.nextLine();
        System.out.print("Input QR image file name: ");
        String refNo = keyboard.nextLine();

        System.out.print("Input Text to be displayed in frame: ");
        String frameText = keyboard.nextLine();
        while (frameText.length() >= 31) {
            System.out.println("Text should not exceed 30 chars. Try again.");
            System.out.print("Input Text to be displayed in frame: : ");
            frameText = keyboard.nextLine();
        }

        // Using Beaconstac API to generate qrcode from input text, with above references
        JSONObject jObject = null;
        try {
            String body
                    = "{\"name\": \"" + refNo + "\",\"organization\": " + ORG_ID
                    + ",\"qr_type\": 1,\"fields_data\": {\"qr_type\": 1,\"url\": \"" + qrText
                    + "\"},\"attributes\":{\"color\":\"#808080\",\"colorDark\":\"#808080\","
                    + "\"margin\":80,\"isVCard\":false,\"frameText\":\"" + frameText
                    + "\",\"logoImage\":\"https://zero1.sg/img/zero1-red.jpg\",\"logoScale\":0.55,\"frameColor\":\"#ff0000\""
                    + ",\"frameStyle\":\"banner-bottom\", \"logoMargin\":10,\"dataPattern\":\"circle\",\"eyeBallShape\":\"circle\""
                    + ",\"gradientType\":\"none\",\"eyeFrameColor\":\"#ff0000\",\"eyeFrameShape\":\"rounded\"}}";

            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.post(BASE_URL + "/2.0/qrcodes/")
                    .header("Content-Type", "application/json")
                    .header("Authorization", TOKEN)
                    .body(body)
                    .asString();
            String responseBody = response.getBody();

            jObject = new JSONObject(responseBody);

        } catch (UnirestException ex) {
            System.out.println(">>> Error on UnirestException ::: " + ex);
            System.exit(0);
        }

        // extract the id from json
        int imgId = (Integer) jObject.get("id");

        return imgId;
    }

    /*
    curl -L -X GET 'https://api.beaconstac.com/api/2.0/qrcodes/1143664/download/?size=1024&error_correction_level=5&canvas_type=svg'
    -H 'Authorization: Token bb083ed70f0da39b8d19712ba5ea64ec2325ac2b'
     */
    /**
     * To call the image's json as per the beaconstac id of the qrcode
     *
     * @param imgId beaconstac id of the QR Code
     * @param imgType Image types supported are PNG, JPEG, SVG and PDF
     * @return
     */
    private JSONObject httpGetDownloadImg(int imgId, String imgType) {
        JSONObject jObject = null;
        try {
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest
                    .get(BASE_URL + "/2.0/qrcodes/" + imgId
                            + "/download/?size=1024&error_correction_level=5&canvas_type=" + imgType)
                    .header("content-type", "application/json")
                    .header("Authorization", TOKEN)
                    .asString();
            String responseBody = response.getBody();
            jObject = new JSONObject(responseBody);
        } catch (UnirestException e) {
            System.out.println("!!! UnirestException ::: " + e);
            System.exit(0);
        }

        return jObject;
    }

    /**
     * To generate QRCode via Beaconstac format from text.
     *
     * @param qrText
     */
    public static void generateTextToQRCode(String qrText) {
        Beaconstac b = new Beaconstac();
        System.out.println("Beaconstac QRcode Generator");

        // gererate Beaconstac qr
        int imgId = b.httpPostQRGenerate(qrText);
        System.out.println("> Beaconstac image Id: " + imgId);

        // Get link for download file
        String imgType = "png";
        JSONObject jsonObject = (JSONObject) b.httpGetDownloadImg(imgId, imgType);

        //Reading the String
        JSONObject jsonUrl = (JSONObject) jsonObject.get("urls");
        String imgUrl = (String) jsonUrl.get(imgType);

        System.out.println("> QRCode may download from :: " + imgUrl);
    }

    /**
     * Main. To generate QRCode via Beaconstac format base on base64 String.
     *
     * @param base64 QRCode String from FAZZ
     */
    public static void generateBase64ToQRCode(String base64) {

        // Convert base64 String to qr-text
        String qrText = base64ToText(base64);
        System.out.println("> Url text: " + qrText);
        System.out.println();

        // Generate qrText to QRCode
        generateTextToQRCode(qrText);
    }

    /**
     * Testing with sample
     *
     * @see Sample.java
     */
    public static void main(String[] args) {

//        String base64 = Sample.base64; // `https://zero1.sg/`
//        Beaconstac.generateBase64ToQRCode(base64);
        String qrText = "https://zero1.sg/";
        Beaconstac.generateTextToQRCode(qrText);
    }
}

/*
beaconstacFormat() curl reference:

curl -L -X POST 'https://api.beaconstac.com/api/2.0/qrcodes/'
    -H 'Authorization: Token bb083ed70f0da39b8d19712ba5ea64ec2325ac2b'
    -H 'Content-Type: application/json' --data-raw '{
    "name": "Z01R002-03",
    "organization": 208507,
    "qr_type": 1,
    "fields_data": {
        "qr_type": 1,
        "url": "https://zero1.sg/"
    },
    "attributes":{
        "color":"#808080",
        "colorDark":"#808080",
        "margin":80,
        "isVCard":false,
        "frameText":"Z01R002      powered by FAZZ",
        "logoImage":"https://zero1.sg/img/zero1-red.jpg",
        "logoScale":0.55,
        "frameColor":"#ff0000",
        "frameStyle":"banner-bottom",
        "logoMargin":10,
        "dataPattern":"circle",
        "eyeBallShape":"circle",
        "gradientType":"none",
        "eyeFrameColor":"#ff0000",
        "eyeFrameShape":"rounded"
    }
}'
 */
