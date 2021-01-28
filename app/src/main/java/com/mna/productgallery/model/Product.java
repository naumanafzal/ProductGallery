package com.mna.productgallery.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Product implements Serializable {

    @SerializedName("code")
    private String code;

    @SerializedName("type")
    private String type;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private String price;

    @SerializedName("image")
    private String image;

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     *
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return this.image;
    }

    public String getCode() {
        return code;
    }

    /**
     *
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(String price) {
        this.price = price;
    }

}
