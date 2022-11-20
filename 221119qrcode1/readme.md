
#### Text scanned from Fazz QRcode (Sandbox) via iPhone
00000101021226500009SG.PAYNOW010120213201411660RXFR0301004142021041012022352040000530370254040.165802SG5914XFERS PTE. LTD6009Singapore62140110DONTSCANME63045205

```
Ref:
Id: paynow_0b4c0d834b8bc81246694bef8893e16c
referenceId: SANDBOX_
```

copied from *readme.md*
### Fazz - generate paynow, and check status in console.

- `Qrcode1.java` - **main**, selection menu. *Run* this file to execute.
- `FazzAttribute.java` - *Fazz* attributes to generate QRCode
- `Generate.java` - to generate base64 qrcode String.
- `JsBase64.java` - to outputStream base64 String to new `qrcodeBase64.js` file.
- `ListAllPayments.java` - list all payments.
- `Beaconstac.java` - Generate QRCode via Beaconstac format. 
- UserPass.java - convert api_key amd password to single String. (not in use)
- (root)`JsfileToImg.html` -  Linked to `qrcodeBase64.js`, to display QRCode image. 
- (foot)`sampleResult.txt` - sample console results, with Sandbox API and Pass.

---
#### note for `jar` file
* If there is *< jar_file.jar >* file, at **command prompt**, run as such:
```
    java -jar jar_file.jar
```
* If select `LIVE` mode, enter
    - ApiKey : *< LIVE_API_Key >*, &
    - Secret : *< LIVE_secret_key >*

* For the generated QRcode string, it will be output to JS file.
    - Use `JsFileToImg.html` to view.

---



---
