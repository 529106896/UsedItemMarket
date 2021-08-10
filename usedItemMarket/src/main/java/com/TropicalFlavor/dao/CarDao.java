package com.TropicalFlavor.dao;
import com.TropicalFlavor.po.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarDao {
    public boolean DeleteGoods(@Param("UID") String UID, @Param("marketGoods") MarketGoods marketGoods);

    public boolean InsertGoods(@Param("UID") String UID, @Param("marketGoods") MarketGoods marketGoods);

    public boolean ChangeCart(@Param("UID") String UID, @Param("marketGoods") MarketGoods marketGoods);

    public List<MarketGoods> ShowGoods(MarketUser marketUser);
}
