package com.TropicalFlavor.controller;

import com.TropicalFlavor.po.*;
import com.TropicalFlavor.service.AdminService;
import com.TropicalFlavor.service.GoodsService;
import com.TropicalFlavor.service.UserService;
import com.TropicalFlavor.tool.StringUtils;
import com.TropicalFlavor.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class userController
{
    @Resource
    private UserService service;

    @Resource
    private GoodsService goodsService;

    @Resource
    private AdminService adminService;

    //登出
    @RequestMapping(value = "/logout.do")
    public String logout(HttpServletRequest request) {
        try {
            request.getSession().removeAttribute("marketUser");
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
        return "redirect:/";
    }

    //进入登录页面
    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {
        return "page/login_page";
    }

    //登录
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(HttpServletRequest request, @RequestParam String sNum,
                        @RequestParam String Password, @RequestParam boolean isAdmin,
                        Model model) {
        String loginMessage = "";
        if (StringUtils.getInstance().isNullOrEmpty(sNum) || StringUtils.getInstance().isNullOrEmpty(Password))
        {
            loginMessage = "用户名或密码不能为空！";
            model.addAttribute("loginMessage", loginMessage);
            return "page/login_page";
        }
        MarketUser user = service.Login(sNum, Password, isAdmin);
        if (user == null)
        {
            loginMessage = "用户名或密码错误！";
            model.addAttribute("loginMessage", loginMessage);
            return "page/login_page";
        }
        if(user.getStatus()==2)
        {
            loginMessage = "您的用户已注销！";
            model.addAttribute("loginMessage", loginMessage);
            return "page/login_page";
        }
        String access;
        if(isAdmin)
        {
            access = "Admin";
        }
        else
        {
            access = "Norm";
        }
        request.getSession().setAttribute("access", access);
        request.getSession().setAttribute("marketUser", user);

        if(user.getStatus()==1)
        {
            model.addAttribute("marketUser", user);
            model.addAttribute("originPwd", Password);
            return "page/personal/new_password";
        }

        if(access.equals("Admin"))
        {
            model.addAttribute("marketUser", user);
            return "redirect:/goToAdminStage.do";
        }

        return "redirect:/";
    }

    //查看用户基本信息
    @RequestMapping(value = "/personal_info.do")
    public String personInfo(HttpServletRequest request, Model model) {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        String access = (String) request.getSession().getAttribute("access");
        if (StringUtils.getInstance().isNullOrEmpty(user))
            return "page/login_page";
        model.addAttribute("access", access);
        model.addAttribute("marketUser", user);
        return "page/personal/information";
    }

    @RequestMapping(value = "/goToEditPersonalInfo.do")
    public String goToEditPersonInfo(HttpServletRequest request, Model model) {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        model.addAttribute("marketUser", user);
        return "page/editperson";
    }

    //修改用户信息
    @RequestMapping(value = "/editPersonalInfo.do", method = RequestMethod.POST)
    //@ResponseBody
    public String editPersonalInfo(HttpServletRequest request, Model model,
                                   @RequestParam(required = false) String Uname,
                                   @RequestParam(required = false) String PhoneNum,
                                   @RequestParam(required = false) String Email
    ) {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");

        //model.addAttribute("marketUser", user);

        //符合要求即可修改，否则重新加载页面
        if (!Uname.isEmpty()) {
            if (Uname.length() <= 30)
                user.setUname(Uname);
            else
            {
                return "redirect:/goToEditPersonalInfo.do";
            }
        }

        if (!PhoneNum.isEmpty()) {
            if (PhoneNum.length() <= 11)
                user.setPhoneNum(PhoneNum);
            else
                return "redirect:/goToEditPersonalInfo.do";
        }

        if (!Email.isEmpty()) {
            if (Email.length() <= 50)
                user.setEmail(Email);
            else
                return "redirect:/goToEditPersonalInfo.do";
        }

        boolean isEditSuccess = service.ChangeInfo(user);
        if (!isEditSuccess)
            return "redirect:/goToEditPersonalInfo.do";

        request.getSession().setAttribute("marketUser", user);
        return "redirect:/personal_info.do";
    }

//    public String getGID()
//    {
//        return String.format("%d",utilsDao.selectMaxGID()+1);
//    }
//    public String getPID()
//    {
//        return String.format("%d",utilsDao.selectMaxPID()+1);
//    }

    //用户上传商品
    @RequestMapping(value = "/insertGoods.do", method = RequestMethod.POST)
    public String insertGoods(@RequestParam(required = false) MultipartFile image,
                              @RequestParam(required = false) String Comment,
                              @RequestParam String Name,
                              @RequestParam String Kind,
                              @RequestParam Double Price,
                              @RequestParam Double Number,
                              HttpServletRequest request, Model model) throws IOException {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        model.addAttribute("marketUser", user);

        //新增商品
        if (StringUtils.getInstance().isNullOrEmpty(Name) ||
                StringUtils.getInstance().isNullOrEmpty(Kind) ||
                StringUtils.getInstance().isNullOrEmpty(Price) ||
                StringUtils.getInstance().isNullOrEmpty(Number))
            return "redirect:/publish_product.do";

        MarketGoods goods;

        if(!image.isEmpty())
        {
            String random;
            String path = request.getSession().getServletContext().getRealPath("/");
            String [] paths = path.split("\\\\");
            StringBuilder parentPath = new StringBuilder();
            for (int i = 0; i < paths.length - 2; i++)
            {
                parentPath.append(paths[i]).append("\\");
            }
            parentPath.append("src\\main\\webapp");
            random = "image\\" + StringUtils.getInstance().getRandomChar() + System.currentTimeMillis() + ".jpg";
            File file = new File(String.valueOf(parentPath), random);

            if(!file.exists())
                file.mkdir();
            try
            {
                image.transferTo(file);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            goods = new MarketGoods(Name, Kind, Price, Number, random, Comment);
        }
        else
        {
            goods = new MarketGoods(Name, Kind, Price, Number, null, Comment);
        }

        boolean isInsertGoodsSuccess = service.PushSaleGoods(user.getUID(), goods);

        if (isInsertGoodsSuccess) {
            model.addAttribute("marketGoods", goods);
            model.addAttribute("marketUser", user);
            model.addAttribute("action", 2);
            return "redirect:/personal_info.do";
        } else
            return "redirect:/publish_product.do";
    }
    //进入我的商品页面
    @RequestMapping(value = "/my_publish_product_page.do")
    public String showMyProduct(HttpServletRequest request, Model model)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        model.addAttribute("marketUser", user);

        SaleGoods goods = service.ShowSales(user);

        model.addAttribute("marketGoodsList", goods.getGoods());

        return "page/personal/my_publish_product";
    }

    //下架商品
    @RequestMapping(value = "/deleteGoods.do")
    public String deleteMyProduct(HttpServletRequest request, Model model, @RequestParam String GID)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        model.addAttribute("marketUser", user);

        MarketGoods marketGoods = goodsService.SelectGoodsByPK(GID);

        service.RemoveSaleGoods(user.getUID(), marketGoods);

        if(marketGoods.getImage() != null)
        {
            String oldImagePath = marketGoods.getImage();
            String [] Path;
            Path = oldImagePath.split("\\\\");
            String imageName = Path[1];

            String path = request.getSession().getServletContext().getRealPath("/");
            String [] paths = path.split("\\\\");
            StringBuilder parentPath = new StringBuilder();
            for (int i = 0; i < paths.length - 2; i++)
            {
                parentPath.append(paths[i]).append("\\");
            }
            parentPath.append("src\\main\\webapp\\image\\").append(imageName);

            String oldPath = String.valueOf(parentPath);
            File oldImage = new File(oldPath);

            //System.out.println(oldImage.getAbsolutePath());

            if(oldImage.exists())
            {
                oldImage.delete();
            }
        }

        return "redirect:/my_publish_product_page.do";
    }


    //进入修改商品信息页面
    @RequestMapping(value = "/modifiedPublishProduct.do")
    public String modifyMyProduct(HttpServletRequest request, Model model, @RequestParam String GID)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");

        MarketGoods goods = goodsService.SelectGoodsByPK(GID);

        //System.out.println(goods);

        model.addAttribute("marketUser", user);
        model.addAttribute("marketGoods", goods);

        request.getSession().setAttribute("marketGoods", goods);

        return "page/edit_product_info";
    }

    //修改商品信息
    @RequestMapping(value = "/modifyProductInfo.do", method = RequestMethod.POST)
    public String modifyProduct(@RequestParam(required = false) MultipartFile image,
                                @RequestParam(required = false) String Comment,
                                @RequestParam(required = false) String Name,
                                @RequestParam(required = false) String Kind,
                                @RequestParam(required = false) Double Price,
                                @RequestParam(required = false) Double Number,
                                HttpServletRequest request, Model model)
    {
        MarketGoods goods = (MarketGoods) request.getSession().getAttribute("marketGoods");
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");

        //System.out.println(goods);

        if(!image.isEmpty() && goods.getImage() != null)
        {
            String oldImagePath = goods.getImage();
            String [] Path;
            Path = oldImagePath.split("\\\\");
            String imageName = Path[1];

            String path = request.getSession().getServletContext().getRealPath("/");
            String [] paths = path.split("\\\\");
            StringBuilder parentPath = new StringBuilder();
            for (int i = 0; i < paths.length - 2; i++)
            {
                parentPath.append(paths[i]).append("\\");
            }
            parentPath.append("src\\main\\webapp\\image\\").append(imageName);

            String oldPath = String.valueOf(parentPath);
            File oldImage = new File(oldPath);

            //System.out.println(oldImage.getAbsolutePath());

            if(oldImage.exists())
            {
                oldImage.delete();
            }

            String random;
            parentPath.delete(0, parentPath.length());
            for (int i = 0; i < paths.length - 2; i++)
            {
                parentPath.append(paths[i]).append("\\");
            }
            parentPath.append("src\\main\\webapp");
            random = "image\\" + StringUtils.getInstance().getRandomChar() + System.currentTimeMillis() + ".jpg";
            File file = new File(String.valueOf(parentPath), random);

            //File file = new File(path, random);

            if(!file.exists())
                file.mkdir();
            try
            {
                image.transferTo(file);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            goods.setImage(random);
        }
        else if (!image.isEmpty() && goods.getImage() == null)
        {
            String random;
            String path = request.getSession().getServletContext().getRealPath("/");
            String [] paths = path.split("\\\\");
            StringBuilder parentPath = new StringBuilder();
            for (int i = 0; i < paths.length - 2; i++)
            {
                parentPath.append(paths[i]).append("\\");
            }
            parentPath.append("src\\main\\webapp");
            random = "image\\" + StringUtils.getInstance().getRandomChar() + System.currentTimeMillis() + ".jpg";
            File file = new File(String.valueOf(parentPath), random);

            if(!file.exists())
                file.mkdir();
            try
            {
                image.transferTo(file);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            goods.setImage(random);
        }

        goods.setName(Name);
        goods.setKind(Kind);
        goods.setPrice(Price);
        goods.setNumber(Number);
        goods.setComment(Comment);

        boolean isEditSuccess = goodsService.ChangeGoodsInfo(goods);

        if(!isEditSuccess)
            return "redirect:/modifiedPublishProduct.do";
        else
        {
            model.addAttribute("marketGoods", goods);
            return "redirect:/my_publish_product_page.do";

        }
    }


    //用户点击立即购买商品
    @RequestMapping(value = "/buyDirectly.do", method = RequestMethod.POST)
    //@ResponseBody
    public String buyDirectly(HttpServletRequest request, Model model, @RequestParam String GID, @RequestParam Double Number) {
        MarketGoods goods = goodsService.SelectGoodsByPK(GID);
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String[] time = formatter.format(date).split(" ");
        String Day = time[0];
        String Time = time[1];

        Integer buyStatus = service.BuyDirectly(user.getUID(), goods, Time, Day, Number);

        if (buyStatus == 2)
        {
            model.addAttribute("marketUser", user);
            return "redirect:/goToMallPage.do";
        }
        else if (buyStatus == 1)
        {
            model.addAttribute("marketUser", user);
            String message = "购买失败，不能购买自己的商品";
            model.addAttribute("message", message);
            model.addAttribute("marketGoods", goods);
            return "page/buy";
        }
        else
        {
            model.addAttribute("marketUser", user);
            String message = "超出最大购买数量";
            model.addAttribute("message", message);
            model.addAttribute("marketGoods", goods);
            return "page/buy";
        }
    }

    //用户点击我的购买记录
    //默认显示交成功的记录
    @RequestMapping(value = "/my_buy_history.do")
    public String goToMyHistory(HttpServletRequest request, Model model)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        PurchaseRecordList purchaseRecordList = service.ShowPRecord(user,true,true);

        List<PurchaseRecord> myList = purchaseRecordList.getPurchaseRecordList();

        Map<String,PurchaseRecord> myMap=new HashMap<>();
        for (PurchaseRecord purchaseRecord : myList)
        {
            String sellerUID=goodsService.WhoseGoods(purchaseRecord.getGID());
            myMap.put(sellerUID,purchaseRecord);
        }

        request.getSession().setAttribute("myRecordList", myMap);

        model.addAttribute("marketUser", user);
        model.addAttribute("myRecordList", myMap);
        return "/page/personal/my_buy_history";
    }

    //购买记录待发货
    @RequestMapping(value = "/my_buy_history_ToBeSentOut.do")
    public String goToMyHistory_ToBeSentOut(HttpServletRequest request, Model model)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        PurchaseRecordList purchaseRecordList = service.ShowPRecord(user,false,false);

        List<PurchaseRecord> myList = purchaseRecordList.getPurchaseRecordList();

        Map<String,PurchaseRecord> myMap=new HashMap<>();
        for (PurchaseRecord purchaseRecord : myList)
        {
            String sellerUID=goodsService.WhoseGoods(purchaseRecord.getGID());
            myMap.put(sellerUID,purchaseRecord);
        }

        request.getSession().setAttribute("myRecordList", myMap);

        model.addAttribute("marketUser", user);
        model.addAttribute("myRecordList", myMap);
        return "/page/personal/my_buy_history_ToBeSentOut";
    }

    //购买记录待确认收货
    @RequestMapping(value = "/my_buy_history_ToBeCheck.do")
    public String goToMyHistory_ToBeCheck(HttpServletRequest request, Model model)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        PurchaseRecordList purchaseRecordList = service.ShowPRecord(user,true,false);

        List<PurchaseRecord> myList = purchaseRecordList.getPurchaseRecordList();

        Map<String,PurchaseRecord> myMap=new HashMap<>();
        for (PurchaseRecord purchaseRecord : myList)
        {
            String sellerUID=goodsService.WhoseGoods(purchaseRecord.getGID());
            myMap.put(sellerUID,purchaseRecord);
        }

        request.getSession().setAttribute("myRecordList", myMap);

        model.addAttribute("marketUser", user);
        model.addAttribute("myRecordList", myMap);
        return "/page/personal/my_buy_history_ToBeCheck";
    }
    //作为买家确认收货
    @RequestMapping(value = "/my_buy_history_GotCheck.do")
    public String goToMyHistory_GotCheck(HttpServletRequest request, Model model,@RequestParam String PID)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        service.UpdateRecordStatus(PID,true,true);
        return "redirect:/my_buy_history_ToBeCheck.do";
    }
    //查看售卖记录
    //默认为交易成功界面
    @RequestMapping(value = "/my_sell_history.do")
    public String goToMySellHistory(HttpServletRequest request, Model model)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        SaleRecordList saleRecordList = service.ShowSRecord(user,true,true);

        List<SaleRecord> myList = saleRecordList.getSaleRecordList();

        request.getSession().setAttribute("myRecordList", myList);

        model.addAttribute("marketUser", user);
        model.addAttribute("myRecordList", myList);
        return "/page/personal/my_sell_history";
    }

    //作为卖家待发货
    @RequestMapping(value = "/my_sell_history_ToBeSentOut.do")
    public String goToMySellHistory_ToBeSentOut(HttpServletRequest request, Model model)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        SaleRecordList saleRecordList = service.ShowSRecord(user,false,false);

        List<SaleRecord> myList = saleRecordList.getSaleRecordList();

        request.getSession().setAttribute("myRecordList", myList);

        model.addAttribute("marketUser", user);
        model.addAttribute("myRecordList", myList);
        return "/page/personal/my_sell_history_ToBeSentOut";
    }

    //作为卖家待买家确认收货
    @RequestMapping(value = "/my_sell_history_ToBeCheck.do")
    public String goToMySellHistory_ToBeCheck(HttpServletRequest request, Model model)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        SaleRecordList saleRecordList = service.ShowSRecord(user,true,false);

        List<SaleRecord> myList = saleRecordList.getSaleRecordList();

        request.getSession().setAttribute("myRecordList", myList);

        model.addAttribute("marketUser", user);
        model.addAttribute("myRecordList", myList);
        return "/page/personal/my_sell_history_ToBeCheck";
    }
    //作为卖家发货
    @RequestMapping(value = "/my_buy_history_SentOut.do")
    public String goToMyHistory_SentOut(HttpServletRequest request, Model model,@RequestParam String PID)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        service.UpdateRecordStatus(PID,true,false);
        return "redirect:/my_sell_history_ToBeSentOut.do";
    }

    //我的购物车
    @RequestMapping(value = "/goToMyCart.do")
    public String goToMyCart(HttpServletRequest request, Model model)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        if(StringUtils.getInstance().isNullOrEmpty(user))
        {
            return "page/login_page";
        }

        ShoppingCart myCart = service.ShowCar(user);
        List<MarketGoods> myCartList = myCart.getGoods();

        for(MarketGoods temp : myCartList)
        {
            MarketUser tempUser = service.WhoseGoods(temp.getGID());
            if(tempUser.getUname().contains("已注销"))
                temp.setKind("NOT");
        }

        model.addAttribute("marketUser", user);
        model.addAttribute("myCartList", myCartList);

        return "page/cart";
    }

    //添加到我的购物车
    @RequestMapping(value = "/addToMyCart.do")
    //@ResponseBody
    public ModelAndView addCar(HttpServletRequest request, Model model, @RequestParam String GID)
    {
        ModelAndView mv = new ModelAndView();
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        MarketGoods goods = goodsService.SelectGoodsByPK(GID);
        MarketUser seller = service.WhoseGoods(GID);

        mv.addObject("marketUser", user);
        mv.addObject("marketGoods", goods);
        mv.addObject("seller", seller);
        mv.setViewName("page/detail");


        service.PushShoppingCart(user.getUID(), goodsService.SelectGoodsByPK(GID));
        return mv;
    }

    //从我的购物车移除
    @RequestMapping(value = "/removeFromCart.do")
    public String removeCar(HttpServletRequest request, Model model, @RequestParam String GID)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        MarketGoods goods = goodsService.SelectGoodsByPK(GID);

        model.addAttribute("marketUser", user);

        service.RemoveShoppingCart(user.getUID(), goods);

        return "redirect:/goToMyCart.do";
    }

    //从我的购物车结算
    @RequestMapping(value = "/buyFromCart.do")
    public String buyCar(HttpServletRequest request, Model model, @RequestParam Double Number, @RequestParam String GID)
    {
        MarketGoods goods = goodsService.SelectGoodsByPK(GID);
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String[] time = formatter.format(date).split(" ");
        String Day = time[0];
        String Time = time[1];

        service.BuyFromShoppingCart(user.getUID(), goods, Time, Day, Number);

        return "redirect:/goToMyCart.do";
    }

    //状态为初始密码状态的用户重置密码
    @RequestMapping(value = "/newPassword.do", method = RequestMethod.POST)
    public String resetPassword(HttpServletRequest request, Model model, @RequestParam String Password)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        model.addAttribute("marketUser", user);

        user.setPassword(Md5Util.getMD5(Password));
        user.setStatus(0);
        service.ChangeInfo(user);



        return "redirect:/logout.do";
    }

    //用户修改个人密码
    @RequestMapping(value = "/resetMyPassword.do")
    public String resetMyPassword(HttpServletRequest request, Model model)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        model.addAttribute("marketUser", user);

        return "page/personal/reset_password";
    }

    //用户提交修改后的密码
    @RequestMapping(value = "/submitMyPassword.do", method = RequestMethod.POST)
    public String updateMyPassword(HttpServletRequest request, Model model, @RequestParam String Password)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        model.addAttribute("marketUser", user);

        user.setPassword(Md5Util.getMD5(Password));

        service.ChangeInfo(user);

        return "redirect:/logout.do";
    }

}
