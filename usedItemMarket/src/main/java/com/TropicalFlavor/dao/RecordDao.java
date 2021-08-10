package com.TropicalFlavor.dao;

import com.TropicalFlavor.po.*;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordDao {
    public boolean DeleteRecord(PurchaseRecord purchaseRecord);

    //public boolean SelectRecord(PurchaseRecord purchaseRecord);

    public boolean InsertRecord(@Param("UID") String UID, @Param("purchaseRecord") PurchaseRecord purchaseRecord);

    public boolean UpdateRecord(@Param("PID") String PID,@Param("IsSent") boolean IsSent,@Param("IsGot") boolean IsGot);

    public List<PurchaseRecord> ShowRecord(@Param("marketUser") MarketUser marketUser,
                                           @Param("IsSent") boolean IsSent,@Param("IsGot") boolean IsGot);

    public List<TradeRecord> ShowAllRecord();
}
