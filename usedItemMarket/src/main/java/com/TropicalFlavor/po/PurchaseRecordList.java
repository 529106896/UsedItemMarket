package com.TropicalFlavor.po;

import java.util.List;

public class PurchaseRecordList {
    private List<PurchaseRecord> purchaseRecordList;

    public List<PurchaseRecord> getPurchaseRecordList()
    {
        return purchaseRecordList;
    }

    public void setPurchaseRecordList(List<PurchaseRecord> purchaseRecordList)
    {
        this.purchaseRecordList = purchaseRecordList;
    }

    public PurchaseRecordList(List<PurchaseRecord> purchaseRecordList)
    {
        this.purchaseRecordList = purchaseRecordList;
    }

    @Override
    public String toString()
    {
        return "PurchaseRecordList{" +
                "purchaseRecordList=" + purchaseRecordList +
                '}';
    }
}
