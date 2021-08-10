package com.TropicalFlavor.po;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MarketGoods {

    private String GID;

    private String Name;

    private String Kind;

    private Double Price;

    private Double Number;

    private String Image;

    private String Comment;

    public MarketGoods(){};

    public MarketGoods(String name, String kind, Double price, Double number)
    {
        Name = name;
        Kind = kind;
        Price = price;
        Number = number;
    }

    public MarketGoods(String name, String kind, Double price, Double number, String image)
    {
        Name = name;
        Kind = kind;
        Price = price;
        Number = number;
        Image = image;
    }

    public MarketGoods(String name, String kind, Double price, Double number, String image, String comment)
    {
        Name = name;
        Kind = kind;
        Price = price;
        Number = number;
        Image = image;
        Comment = comment;
    }

    public MarketGoods(String GID, String name, String kind, Double price, Double number)
    {
        this.GID = GID;
        Name = name;
        Kind = kind;
        Price = price;
        Number = number;
    }

    public MarketGoods(String GID, String name, String kind, Double price, Double number, String image)
    {
        this.GID = GID;
        Name = name;
        Kind = kind;
        Price = price;
        Number = number;
        Image = image;
    }


    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    public String getImage() {
        return Image;
    }

    public void ChangeInfo(String name, String kind, Double price, Double number){
        Name = name;
        Kind = kind;
        Price = price;
        Number = number;
    }

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getKind() {
        return Kind;
    }

    public void setKind(String kind) {
        Kind = kind;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Double getNumber() {
        return Number;
    }

    public void setNumber(Double number) {
        Number = number;
    }

    @Override
    public String toString()
    {
        return "MarketGoods{" +
                "GID='" + GID + '\'' +
                ", Name='" + Name + '\'' +
                ", Kind='" + Kind + '\'' +
                ", Price=" + Price +
                ", Number=" + Number +
                '}';
    }
}
