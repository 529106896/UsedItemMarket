package com.TropicalFlavor.dao;

import com.TropicalFlavor.po.*;

public interface UtilsDao
{
    //根据UID选择一个用户
    public MarketUser selectOneUser(String UID);
    //根据GID选择一个商品
    public MarketGoods selectOneGoods(String GID);
    //根据PID选择一条记录
    public PurchaseRecord selectOnePRecord(String PID);
    //根据PID选择一条记录
    public SaleRecord selectOneSRecord(String PID);
    //获取最大的PID实现PID加一
    public long selectMaxPID();
    //获取最大的PID实现PID加一
    public long selectMaxGID();
}
