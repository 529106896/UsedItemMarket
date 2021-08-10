package com.TropicalFlavor.service;

import com.TropicalFlavor.po.*;
import org.springframework.stereotype.Service;

public interface UserService {
    //注册函数
    boolean Register(MarketUser marketUser);
    //登录函数
    public MarketUser Login(String userName,String password, boolean IsAdmin);
    //找到物品的主人
    public MarketUser WhoseGoods(String GID);
    //改变个人信息函数
    public boolean ChangeInfo(MarketUser marketUser);
    //新增售卖物品
    public boolean PushSaleGoods(String UID,MarketGoods marketGoods);
    //删除售卖物品
    public boolean RemoveSaleGoods(String UID,MarketGoods marketGoods);
    //物品售罄
    public boolean GoodsSoldOut(MarketGoods marketGoods);
    //添加物品到购物车
    public boolean PushShoppingCart(String UID,MarketGoods marketGoods);
    //从购物车中删除
    public boolean RemoveShoppingCart(String UID,MarketGoods marketGoods);
    //从购物车中结算
    public boolean BuyFromShoppingCart(String UID,MarketGoods marketGoods, String Time, String Date, Double number);
    //直接购买
    public Integer BuyDirectly(String UID,MarketGoods marketGoods, String Time, String Date, Double number);
    //显示购物车
    public ShoppingCart ShowCar(MarketUser marketUser);
    //显示订单
    public PurchaseRecordList ShowPRecord(MarketUser marketUser,boolean IsSent,boolean IsGot);
    //更新订单状态：设置已发货、已确认收货
    public boolean UpdateRecordStatus(String PID,boolean IsSent,boolean IsGot);
    //显示售卖表
    public SaleGoods ShowSales(MarketUser marketUser);
    //显示售卖记录
    public SaleRecordList ShowSRecord(MarketUser marketUser,boolean IsSent,boolean IsGot);
    //根据UID返回MarketUser对象
    public MarketUser WhichUser(String UID);

}
