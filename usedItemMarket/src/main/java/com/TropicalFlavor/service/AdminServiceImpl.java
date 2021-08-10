package com.TropicalFlavor.service;

import com.TropicalFlavor.dao.RecordDao;
import com.TropicalFlavor.dao.SalesDao;
import com.TropicalFlavor.dao.UserDao;
import com.TropicalFlavor.po.MarketGoods;
import com.TropicalFlavor.po.MarketUser;
import com.TropicalFlavor.po.SaleGoods;
import com.TropicalFlavor.po.TradeRecord;
import com.TropicalFlavor.util.Md5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Transactional
@Service
public class AdminServiceImpl implements AdminService{

    @Resource
    private UserDao userDao;

    @Resource
    private SalesDao salesDao;

    @Resource
    private RecordDao recordDao;
    @Override
    public boolean InsertMarketUser(MarketUser marketUser) {
        if (!userDao.InsertUser(marketUser)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean DeleteMarketUser(String UID) {
        if(!userDao.DeleteUser(userDao.SelectUser(UID))){
            return false;
        }
        return true;
    }

    //注销用户
    @Override
    public boolean logOffMarketUser(String UID)
    {
        MarketUser user = userDao.SelectUser(UID);
        user.setStatus(2);

//        SaleGoods userGoods = new SaleGoods(salesDao.ShowGoods(user));
//
//        List<MarketGoods> userGoodsList = userGoods.getGoods();
//
//        for(MarketGoods i : userGoodsList)
//        {
//
//        }

        //user.setPassword(Md5Util.getMD5());
        if(!userDao.ChangeInfo(user))
            return false;
        return true;
    }

//    @Override
//    public List<MarketUser> ShowUser() {
//        return userDao.ShowUser();
//    }
    @Override
    public List<MarketUser> ShowUser() {
        List<MarketUser> marketUserList = userDao.ShowUser();
        Iterator<MarketUser> i = marketUserList.iterator();
        while (i.hasNext()){
            if(i.next().getUID().substring(0,4).equals("ADMI")){
                i.remove();
            }
        }
        return marketUserList;
    }
    @Override
    public List<TradeRecord> ShowAllRecord()
    {
        List<TradeRecord> tradeRecords= recordDao.ShowAllRecord();
        return  tradeRecords;
    }
    //修改用户信息
    @Override
    public boolean ChangeUserInfo(MarketUser marketUser) {
        if(!userDao.ChangeInfo(marketUser))
            return false;
        return true;
    }
}
