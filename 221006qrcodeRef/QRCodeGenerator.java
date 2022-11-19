package com.zero1.qrcodegenerator;

/*
 * ref: 
 * https://crunchify.com/java-simple-qr-code-generator-example/
 * https://github.com/zxing/zxing
 * https://zxing.github.io/zxing/apidocs/ ;
 */
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 */
public class QRCodeGenerator {

    public static void main(String[] args) {
        String myCodeText = "https://zero1.sg/";
        String filePath = "zero1-QRCode.png";
        int size = 512;
        String zFileType = "png";
        File zFile = new File(filePath);
        try {
            Map<EncodeHintType, Object> zHintType = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            zHintType.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            // Now with version 3.4.1 you could change margin (white border size)
            zHintType.put(EncodeHintType.MARGIN, 1);
            /* default = 4 */
            Object put = zHintType.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            QRCodeWriter mYQRCodeWriter = new QRCodeWriter(); // throws com.google.zxing.WriterException
            BitMatrix zBitMatrix = mYQRCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                    size, zHintType);
            int zWidth = zBitMatrix.getWidth();
            // The BufferedImage subclass describes an Image with an accessible buffer of crunchifyImage data.
            BufferedImage zImage = new BufferedImage(zWidth, zWidth,
                    BufferedImage.TYPE_INT_RGB);
            // Creates a Graphics2D, which can be used to draw into this BufferedImage.
            zImage.createGraphics();
            // This Graphics2D class extends the Graphics class to provide more sophisticated control over geometry, coordinate transformations, color management, and text layout.
            // This is the fundamental class for rendering 2-dimensional shapes, text and images on the Java(tm) platform.
            Graphics2D zGraphics = (Graphics2D) zImage.getGraphics();
            // setColor() sets this graphics context's current color to the specified color.
            // All subsequent graphics operations using this graphics context use this specified color.
            zGraphics.setColor(Color.white);
            // fillRect() fills the specified rectangle. The left and right edges of the rectangle are at x and x + width - 1.
            zGraphics.fillRect(0, 0, zWidth, zWidth);
            // TODO: Please change this color as per your need
            zGraphics.setColor(Color.BLUE);
            for (int i = 0; i < zWidth; i++) {
                for (int j = 0; j < zWidth; j++) {
                    if (zBitMatrix.get(i, j)) {
                        zGraphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            // A class containing static convenience methods for locating
            // ImageReaders and ImageWriters, and performing simple encoding and decoding.
            ImageIO.write(zImage, zFileType, zFile);
            System.out.println("\nCongratulation.. You have successfully created QR Code.. \n"
                    + "Check your code here: " + filePath);
            
//            // Convert to base64 String
//            // https://stackoverflow.com/questions/35515506/create-qr-code-base64-and-put-in-img-tag-of-html
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            ImageIO.write(zImage, zFileType, outputStream);
//            String base64 = new String(Base64.getEncoder().encode(outputStream.toByteArray()));
//            System.out.println(">>> base64:: " + base64);

//            // Decode base64 String to image
//            // ref: https://www.baeldung.com/java-base64-image-string
//            byte[] decodedBytes = Base64.getDecoder().decode(base64);
//            FileUtils.writeByteArrayToFile(new File(outputFileName), decodedBytes);

//            // Decode base64 String to buffer
//            byte[] decodedBytes = Base64.getDecoder().decode(base64);
//            ByteArrayInputStream inputStream = new ByteArrayInputStream(decodedBytes);
//            BufferedImage bfrdImgobj = ImageIO.read(inputStream);
            
        } catch (WriterException e) {
            System.out.println("\nSorry.. Something went wrong...\n");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
