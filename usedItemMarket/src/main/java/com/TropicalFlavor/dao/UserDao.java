package com.TropicalFlavor.dao;

import com.TropicalFlavor.po.*;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao
{
    public String IsTrue(@Param("sno") String Sno, @Param("password") String password);

    public boolean ChangeInfo(MarketUser marketUser);

    public boolean DeleteUser(MarketUser marketUser);

    public boolean InsertUser(MarketUser marketUser);

    public MarketUser SelectUser(String UID);

    public List<MarketUser> ShowUser();
}
