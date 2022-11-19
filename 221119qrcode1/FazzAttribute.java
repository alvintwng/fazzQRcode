package com.zero1.qrcode1;

/**
 * Set necessary attributes for FAZZ to generate qr.
 */
public class FazzAttribute {

    private String baseUrl;
    private String apikey;
    private String password;
    private String bigString;
    private String attrReferenceId;
    private String paynowId;

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBigString(String bigString) {
        this.bigString = bigString;
    }

    public String getBigString() {
        return bigString;
    }

    public void setAttrReferenceId(String attrReferenceId) {
        this.attrReferenceId = attrReferenceId;
    }

    public String getAttrReferenceId() {
        return attrReferenceId;
    }

    public void setPaynowId(String paynowId) {
        this.paynowId = paynowId;
    }

    public String getPaynowId() {
        return paynowId;
    }

    /**
     * set url, api, secret to Sandbox
     */
    public void setSandbox() {
        baseUrl = "https://sandbox.xfers.com/api";
        apikey = "test_49bc9d44d56d726a590ea35c78f771c8";
        password = "0pI4oJd2Bz7m48nrkyjiVwbHvvmIcZho";
    }

}
