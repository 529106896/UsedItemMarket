package com.TropicalFlavor.controller;

import com.TropicalFlavor.po.MarketGoods;
import com.TropicalFlavor.po.MarketUser;
import com.TropicalFlavor.service.GoodsService;
import com.TropicalFlavor.service.UserService;
import com.TropicalFlavor.tool.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GoodsController
{
    @Resource
    private GoodsService goodsService;

    @Resource
    private UserService userService;

    //进入上传商品页面
    @RequestMapping(value = "/publish_product.do", method = RequestMethod.GET)
    public String publish(HttpServletRequest request, Model model)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        model.addAttribute("marketUser", user);
        model.addAttribute("marketGoods", new MarketGoods());
        model.addAttribute("action", 1);
        return "page/publish_product";
    }

    //进入商品详情页面
    @RequestMapping(value = "/goToProductDetail.do")
    public String goToProductDetail(HttpServletRequest request, Model model, @RequestParam String GID)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        if(StringUtils.getInstance().isNullOrEmpty(user))
        {
            return "page/login_page";
        }
        MarketGoods goods = goodsService.SelectGoodsByPK(GID);
        MarketUser seller = userService.WhoseGoods(GID);

        model.addAttribute("marketUser", user);
        model.addAttribute("marketGoods", goods);
        model.addAttribute("seller", seller);

        request.getSession().setAttribute("marketGoods", goods);

        return "page/detail";
    }

    //用户在商品详情页面点击立即购买按钮
    @RequestMapping(value = "/goToBuyPage.do")
    public String goToBuyPage(HttpServletRequest request, Model model, @RequestParam String GID)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        MarketGoods goods = goodsService.SelectGoodsByPK(GID);

        model.addAttribute("marketUser", user);
        model.addAttribute("marketGoods", goods);

        request.getSession().setAttribute("marketGoods", goods);

        return "page/buy";
    }

    @RequestMapping(value = "/goToSearch.do")
    public String searchByKeyWords(HttpServletRequest request, Model model, @RequestParam String Keywords)
    {
        //System.out.println(Keywords);
        boolean isLastPage = false;
        List<MarketGoods> targetGoodsList = goodsService.SearchGoods(Keywords);
        if(targetGoodsList.size()<=12) {
            targetGoodsList = targetGoodsList.subList(0, targetGoodsList.size());
            isLastPage = true;
        }
        else{
            targetGoodsList=targetGoodsList.subList(0,12);
        }
        MarketUser user = (MarketUser)request.getSession().getAttribute("marketUser");

//        request.getSession().setAttribute("marketUser", user);
//        request.getSession().setAttribute("marketGoodsList", targetGoodsList);

        model.addAttribute("marketGoodsList", targetGoodsList);
        model.addAttribute("marketUser", user);
        model.addAttribute("Keywords", Keywords);
        model.addAttribute("sortKind",4);
        model.addAttribute("currentPage",0);
        model.addAttribute("isLastPage",isLastPage);

        return "page/goods";
    }
}
