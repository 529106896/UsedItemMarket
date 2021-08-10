package com.TropicalFlavor.po;

import java.util.List;

public class ShoppingCart {
    private List<MarketGoods> Goods;

    public ShoppingCart(List<MarketGoods> goods)
    {
        Goods = goods;
    }

    public List<MarketGoods> getGoods()
    {
        return Goods;
    }

    public void setGoods(List<MarketGoods> goods)
    {
        Goods = goods;
    }

    public void insertGoods(MarketGoods marketGoods) { Goods.add(marketGoods); }

    public void deleteGoods(MarketGoods marketGoods) { Goods.remove(marketGoods); }

    @Override
    public String toString()
    {
        return "ShoppingCart{" +
                "Goods=" + Goods +
                '}';
    }
}
