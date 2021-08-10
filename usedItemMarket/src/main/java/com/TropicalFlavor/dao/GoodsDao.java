package com.TropicalFlavor.dao;
import com.TropicalFlavor.po.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsDao
{
    public boolean GoodsSoldOut(MarketGoods marketGoods);

    public boolean ChangeInfo(MarketGoods marketGoods);

    public boolean DeleteGoods(MarketGoods marketGoods);

    public List<MarketGoods> SelectGoodsByName(String KeyWords);

    public List<MarketGoods> SelectGoodsByKind(String KeyWords);

    public boolean InsertGoods(MarketGoods marketGoods);

    public Integer getGoodsSum();

    public MarketGoods SelectGoods(String GID);
    public List<MarketGoods> selectTenGoods(Map map);

    public List<MarketGoods> selectByPrice(@Param("ASC")Boolean ASC, @Param("begin") Integer begin);

    public List<MarketGoods> selectByNumber(@Param("ASC")Boolean ASC,@Param("begin") Integer begin);

    public List<MarketGoods> selectByGID(Integer begin);
}
