package com.example.silas.testbrickswap.brickswap;

import java.util.ArrayList;

/**
 * Created by Silas on 05-06-2016.
 */
public class LegoSet {

    private String id;
    private String postDate;
    private String posterID;
    private String price;
    private String productName;
    private String title;
    private ArrayList<String> imageList;

    public LegoSet(String id, String postDate, String posterID, String price, String productName, String title, ArrayList imageList) {
        this.id = id;
        this.postDate = postDate;
        this.posterID = posterID;
        this.price = price;
        this.productName = productName;
        this.title = title;
        this.imageList = imageList;
    }

    public LegoSet(String id, String postDate, String posterID, String price, String productName, String title) {
        this.id = id;
        this.postDate = postDate;
        this.posterID = posterID;
        this.price = price;
        this.productName = productName;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getPosterID() {
        return posterID;
    }

    public void setPosterID(String posterID) {
        this.posterID = posterID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList imageList) {
        this.imageList = imageList;
    }
}
