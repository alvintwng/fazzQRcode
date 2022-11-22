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

    public FazzAttribute() {
    }

    public FazzAttribute(String baseUrl, String apikey, String password) {
        this.baseUrl = baseUrl;
        this.apikey = apikey;
        this.password = password;
    }

    /**
     * Set the FazzAttribute with the following param. And check for correct
     * entries.
     *
     * @param baseUrl
     * @param apikey
     * @param password
     */
    public void setAPI(String baseUrl, String apikey, String password) {
        this.baseUrl = baseUrl;
        this.apikey = apikey;
        this.password = password;

        okApiPass();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getApikey() {
        return apikey;
    }

    public String getPassword() {
        return password;
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

    /**
     * To determine if the baseUrl, ApiKey and password is correctly enter.
     *
     * @return true if there is no JSONn with `data`
     */
    private boolean okApiPass() {
        ListAllPayments list = new ListAllPayments(
                baseUrl, apikey, password);

        boolean ifNotOk = list.getHttpGetListAllPaymt().isNull("data");
        if (ifNotOk) {
            System.out.println(">> Error in authentication."
                    + " Please re-enter the API key, or the Secret.");
            System.exit(0);
        }
        return ifNotOk;
    }

}
