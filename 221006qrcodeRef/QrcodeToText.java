package com.one0;

/**
 * ref: https://www.codespeedy.com/qr-code-to-text-converter-in-java/
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class QrcodeToText {

    public static void main(String args[]) throws IOException, NotFoundException {

        File fileobj = new File("C:\\Users\\AlvinNg\\Desktop\\qrcode.png");

        BufferedImage bfrdImgobj = ImageIO.read(fileobj);
        LuminanceSource source = new BufferedImageLuminanceSource(bfrdImgobj);
        BinaryBitmap binarybitmapobj = new BinaryBitmap(new HybridBinarizer(source));

        Result resultobj = new MultiFormatReader().decode(binarybitmapobj);

        System.out.println("Data Stored In our QR Code" + "  " + resultobj.getText());
    }

}
/*
--- exec-maven-plugin:3.0.0:exec (default-cli) @ beaconstac ---
Data Stored In our QR Code  00000101021226500009SG.PAYNOW0... 110DONTSCANME63045205
------------------------------------------------------------------------
 */
