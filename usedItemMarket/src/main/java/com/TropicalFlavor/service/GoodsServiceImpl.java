package com.TropicalFlavor.service;

import com.TropicalFlavor.dao.*;
import com.TropicalFlavor.po.MarketGoods;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Transactional
@Service
public class GoodsServiceImpl implements GoodsService{

    @Resource
    private GoodsDao goodsDao;

    @Resource
    private SalesDao salesDao;

    @Resource
    private UserDao userDao;

    @Override
    public boolean ChangeGoodsInfo(MarketGoods marketGoods)
    {
        return goodsDao.ChangeInfo(marketGoods);
    }

    @Override
    public List<MarketGoods> SearchGoods(String Keywords) {
        List<String> KeywordsList = new ArrayList<String>(Arrays.asList(Keywords.split("\\s+")));
        Map<String,MarketGoods> TempMap = new HashMap<>();
        Set<MarketGoods> TempListWithMarketGoods = new HashSet<>();
        for (String s : KeywordsList) {
            TempListWithMarketGoods.addAll(goodsDao.SelectGoodsByName(s));
        }
        for (String s : KeywordsList) {
            TempListWithMarketGoods.addAll(goodsDao.SelectGoodsByKind(s));
        }
        for(MarketGoods i:TempListWithMarketGoods)
        TempMap.put(i.getGID(),i);
        return new ArrayList<>(TempMap.values());
    }

    @Override
    public List<MarketGoods> SearchGoodsByKind(String Keywords) {
        List<String> KeywordsList = new ArrayList<>(Arrays.asList(Keywords.split("\\s+")));
        List<MarketGoods> TempListWithMarketGoods = new ArrayList<>();
        for (String s : KeywordsList) {
            TempListWithMarketGoods.addAll(goodsDao.SelectGoodsByKind(s));
        }
        return TempListWithMarketGoods;
    }

    @Override
    public Integer getGoodsSum()
    {
        return goodsDao.getGoodsSum();
    }

    @Override
    public String WhoseGoods(String GID)
    {
        return salesDao.WhoseGoods(GID);
    }

    @Override
    public List<MarketGoods> selectTenGoods(Integer begin)
    {
        return goodsDao.selectByGID(begin);
    }

    @Override
    public MarketGoods SelectGoodsByPK(String GID)
    {
        return goodsDao.SelectGoods(GID);
    }

    @Override
    public List<MarketGoods> SortBySortKind(Integer begin, Integer sortKind) {
        switch (sortKind){
            case 0:
                return goodsDao.selectByPrice(true, begin);
            case 1:
                return goodsDao.selectByPrice(false, begin);
            case 2:
                return goodsDao.selectByNumber(true, begin);
            case 3:
                return goodsDao.selectByNumber(false, begin);
            case 4:
                return goodsDao.selectByGID(begin);
        }
        return null;
    }

    @Override
    public List<MarketGoods> SortList(Integer sortKind, List<MarketGoods> marketGoodsList) {
        switch (sortKind){
            case 0: marketGoodsList.sort(new Comparator<MarketGoods>() {
                @Override
                public int compare(MarketGoods o1, MarketGoods o2) {
                    return o1.getPrice().compareTo(o2.getPrice());
                }
            });
                break;
            case 1: marketGoodsList.sort(new Comparator<MarketGoods>() {
                @Override
                public int compare(MarketGoods o1, MarketGoods o2) {
                    return -o1.getPrice().compareTo(o2.getPrice());
                }
            });
                break;
            case 2: marketGoodsList.sort(new Comparator<MarketGoods>() {
                @Override
                public int compare(MarketGoods o1, MarketGoods o2) {
                    return o1.getNumber().compareTo(o2.getNumber());
                }
            });
                break;
            case 3: marketGoodsList.sort(new Comparator<MarketGoods>() {
                @Override
                public int compare(MarketGoods o1, MarketGoods o2) {
                    return -o1.getNumber().compareTo(o2.getNumber());
                }
            });
                break;
            case 4: marketGoodsList.sort(new Comparator<MarketGoods>() {
                @Override
                public int compare(MarketGoods o1, MarketGoods o2) {
                    return o1.getGID().compareTo(o2.getGID());
                }
            });
                break;
            default: break;
        }
        return marketGoodsList;
    }
}
