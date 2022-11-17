package com.itplus.mtfood.Model;


import java.io.Serializable;

public class Food implements Serializable {
    private String name;
    private String pic;
    private String description;
    private int fee;
    private int numberInCart;

    public Food() {
    }

    public Food(String name, String pic, String description, int fee, int numberInCart) {
        this.name = name;
        this.pic = pic;
        this.fee = fee;
        this.numberInCart = getNumberInCart();
    }

    public Food(String name, String pic, String description, int fee) {
        this.name = name;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
