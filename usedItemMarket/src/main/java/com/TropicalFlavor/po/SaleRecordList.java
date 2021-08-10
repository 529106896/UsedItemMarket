package com.TropicalFlavor.po;

import java.util.List;

public class SaleRecordList {
    private List<SaleRecord> saleRecordList;

    public SaleRecordList(List<SaleRecord> saleRecordList)
    {
        this.saleRecordList = saleRecordList;
    }

    public List<SaleRecord> getSaleRecordList()
    {
        return saleRecordList;
    }

    public void setSaleRecordList(List<SaleRecord> saleRecordList)
    {
        this.saleRecordList = saleRecordList;
    }

    @Override
    public String toString()
    {
        return "SaleRecordList{" +
                "saleRecordList=" + saleRecordList +
                '}';
    }
}