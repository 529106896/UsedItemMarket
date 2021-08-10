package com.TropicalFlavor.dao;
import com.TropicalFlavor.po.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SRecordDao
{
    public boolean DeleteRecord(SaleRecord purchaseRecord);

    public SaleRecord SelectRecord(SaleRecord purchaseRecord);

    public boolean InsertRecord(SaleRecord purchaseRecord);

    public List<SaleRecord> ShowRecord(@Param("marketUser") MarketUser marketUser,
                                       @Param("IsSent") boolean IsSent, @Param("IsGot") boolean IsGot);
}
