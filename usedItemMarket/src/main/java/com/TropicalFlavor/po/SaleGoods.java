package com.TropicalFlavor.po;

import java.util.List;

public class SaleGoods {
    private List<MarketGoods> Goods;

    public List<MarketGoods> getGoods()
    {
        return Goods;
    }

    public void setGoods(List<MarketGoods> goods)
    {
        Goods = goods;
    }

    public SaleGoods(List<MarketGoods> goods)
    {
        Goods = goods;
    }

    @Override
    public String toString()
    {
        return "SaleGoods{" +
                "Goods=" + Goods +
                '}';
    }
}
