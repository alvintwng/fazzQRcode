``` java
public class OpenInBrowser {
    
    // ref: https://stackoverflow.com/questions/20517434/how-to-open-html-file-using-java

    public static void main(String[] args) {
        String url = "JsfileToImg.html";

        File htmlFile = new File(url);
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException ex) {
            System.out.println(">>>> " + ex.getMessage());
        }
    }
}
```
