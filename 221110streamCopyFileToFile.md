Base64ToPng.java
``` java
package com.zero1.wk08base64png;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Base64ToPng {

    /**
     * https://stackoverflow.com/questions/61475520/how-can-i-copy-png-files-and-dynamic-directory-path
     */
    public void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);

        // Transfer all byte from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

    public static void main(String[] args) {
        Base64ToPng b = new Base64ToPng();
        try {
            File src = new File("zero1QR.png");
            File dst = new File("copied.png");
            b.copy(src, dst);
        } catch (IOException e) {
            System.out.println(">>> " + e.getMessage());
        }
    }
}
/*
C:\Users\AlvinNg\xfersWk08\wk08Base64Png>dir /w
 Volume in drive C is Windows-SSD
 Volume Serial Number is 6E2A-67EF

 Directory of C:\Users\AlvinNg\xfersWk08\wk08Base64Png

[.]               [..]              copied.png        pom.xml           qrcodeBase64.js   [src]
[target]          zero1QR.png
               4 File(s)         52,523 bytes
*/
```
