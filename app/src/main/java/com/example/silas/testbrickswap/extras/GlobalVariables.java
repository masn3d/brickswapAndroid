package com.example.silas.testbrickswap.extras;

/**
 * Created by Silas on 10-06-2016.
 */
public class GlobalVariables {

    private String imageUrl;
    private String serverUrl = "http://brickswap-msncphbusiness.rhcloud.com";
    private String id;
    private String userName;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
