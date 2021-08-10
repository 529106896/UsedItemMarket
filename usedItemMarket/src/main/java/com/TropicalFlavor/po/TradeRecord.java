package com.TropicalFlavor.po;

public class TradeRecord
{
    private String PID;

    private String BuyerID;

    private String Date;

    private String Time;

    private String SellerID;

    private String Gname;

    private String Gkind;

    private Double Gprice;

    private Double Gnumber;

    private boolean IsSent;

    private boolean IsGot;

    public TradeRecord(String PID, String buyerID, String date, String time, String sellerID, String gname, String gkind, Double gprice, Double gnumber, boolean isSent, boolean isGot)
    {
        this.PID = PID;
        BuyerID = buyerID;
        Date = date;
        Time = time;
        SellerID = sellerID;
        Gname = gname;
        Gkind = gkind;
        Gprice = gprice;
        Gnumber = gnumber;
        IsSent = isSent;
        IsGot = isGot;
    }

    public String getPID()
    {
        return PID;
    }

    public void setPID(String PID)
    {
        this.PID = PID;
    }

    public String getBuyerID()
    {
        return BuyerID;
    }

    public void setBuyerID(String buyerID)
    {
        BuyerID = buyerID;
    }

    public String getDate()
    {
        return Date;
    }

    public void setDate(String date)
    {
        Date = date;
    }

    public String getTime()
    {
        return Time;
    }

    public void setTime(String time)
    {
        Time = time;
    }

    public String getSellerID()
    {
        return SellerID;
    }

    public void setSellerID(String sellerID)
    {
        SellerID = sellerID;
    }

    public String getGname()
    {
        return Gname;
    }

    public void setGname(String gname)
    {
        Gname = gname;
    }

    public String getGkind()
    {
        return Gkind;
    }

    public void setGkind(String gkind)
    {
        Gkind = gkind;
    }

    public Double getGprice()
    {
        return Gprice;
    }

    public void setGprice(Double gprice)
    {
        Gprice = gprice;
    }

    public Double getGnumber()
    {
        return Gnumber;
    }

    public void setGnumber(Double gnumber)
    {
        Gnumber = gnumber;
    }

    public boolean isSent()
    {
        return IsSent;
    }

    public void setSent(boolean sent)
    {
        IsSent = sent;
    }

    public boolean isGot()
    {
        return IsGot;
    }

    public void setGot(boolean got)
    {
        IsGot = got;
    }

    @Override
    public String toString()
    {
        return "TradeRecord{" +
                "PID='" + PID + '\'' +
                ", BuyerID='" + BuyerID + '\'' +
                ", Date='" + Date + '\'' +
                ", Time='" + Time + '\'' +
                ", SellerID='" + SellerID + '\'' +
                ", Gname='" + Gname + '\'' +
                ", Gkind='" + Gkind + '\'' +
                ", Gprice=" + Gprice +
                ", Gnumber=" + Gnumber +
                ", IsSent=" + IsSent +
                ", IsGot=" + IsGot +
                '}';
    }
}
