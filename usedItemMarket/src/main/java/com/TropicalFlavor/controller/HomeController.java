package com.TropicalFlavor.controller;

import com.TropicalFlavor.po.MarketGoods;
import com.TropicalFlavor.po.MarketUser;
import com.TropicalFlavor.service.GoodsService;
import com.TropicalFlavor.tool.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController
{
    @Resource
    private GoodsService goodsService;

    //进入首页、返回首页
    @RequestMapping(value = {"/","/home.do"})
    public String home(HttpServletRequest request, Model model)
    {
        MarketUser marketUser = (MarketUser) request.getSession().getAttribute("marketUser");

        List<MarketGoods> bookList = goodsService.SearchGoodsByKind("书");
        List<MarketGoods> showBookList;
        if(bookList.size() >= 5)
            showBookList = bookList.subList(0, 5);
        else
            showBookList = bookList;

        List<MarketGoods> dailyList = goodsService.SearchGoodsByKind("日用品");
        List<MarketGoods> showDailyList;
        if(dailyList.size() >= 5)
            showDailyList = dailyList.subList(0, 5);
        else
            showDailyList = dailyList;

        List<MarketGoods> studyList = goodsService.SearchGoodsByKind("学习用品");
        List<MarketGoods> showStudyList;

        if(studyList.size() >= 5)
            showStudyList = studyList.subList(0, 5);
        else
            showStudyList = studyList;

        List<MarketGoods> bedList = goodsService.SearchGoodsByKind("床上用品");
        List<MarketGoods> showBedList;

        if(bedList.size() >= 5)
            showBedList = bedList.subList(0, 5);
        else
            showBedList = bedList;

        model.addAttribute("bookList", showBookList);
        model.addAttribute("dailyList", showDailyList);
        model.addAttribute("bedList", showBedList);
        model.addAttribute("studyList", showStudyList);

        if(!StringUtils.getInstance().isNullOrEmpty(marketUser))
        {
            model.addAttribute("marketUser", marketUser);
        }
        else
        {
            marketUser = new MarketUser();
            model.addAttribute("marketUser", marketUser);
        }
        try
        {

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "page/login_page";
        }
        return "index";
    }

//    //进入商城
//    @RequestMapping(value = "/goToMallPage.do")
//    public String mallPage(HttpServletRequest request, Model model)
//    {
//        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
//
////        if(StringUtils.getInstance().isNullOrEmpty(user))
////        {
////            return "page/login_page";
////        }
//        model.addAttribute("marketUser", user);
//
//        boolean isLastPage = false;
//
//        if(goodsService.getGoodsSum() <= 12)
//        {
//            isLastPage = true;
//        }
//        model.addAttribute("isLastPage", isLastPage);
//
//        List<MarketGoods> list = selectTen(0);
//
//        model.addAttribute("marketGoodsList", list);
//        model.addAttribute("currentPage", 0);
//
//        return "page/goods";
//    }

    //进入商城
    @RequestMapping(value = "/goToMallPage.do")
    public ModelAndView mallPage(HttpServletRequest request, Model model)
    {
        ModelAndView mv = new ModelAndView();
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
        request.getSession().setAttribute("Type",null);
//        if(StringUtils.getInstance().isNullOrEmpty(user))
//        {
//            return "page/login_page";
//        }
//        model.addAttribute("marketUser", user);
        mv.addObject("marketUser",user);
        boolean isLastPage = false;

        if(goodsService.getGoodsSum() <= 12)
        {
            isLastPage = true;
        }
//        model.addAttribute("isLastPage", isLastPage);

        mv.addObject("isLastPage",isLastPage);
        List<MarketGoods> list = goodsService.selectTenGoods(0);

        /*model.addAttribute("marketGoodsList", list);
        model.addAttribute("currentPage", 0);*/

        mv.addObject("marketGoodsList",list);
        mv.addObject("currentPage",0);
        mv.addObject("sortKind", 4);
        mv.addObject("page", 1);

        mv.setViewName("page/goods");

        return mv;
    }

//    //前往首页
//    @RequestMapping(value = "/goToFirstPage.do")
//    public String goToFirstPage(HttpServletRequest request, Model model)
//    {
//        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
//        List<MarketGoods> list = selectTen(0);
//
//        boolean isLastPage = false;
//
//        if(goodsService.getGoodsSum() <= 12)
//        {
//            isLastPage = true;
//        }
//
//        model.addAttribute("marketUser", user);
//        model.addAttribute("isLastPage", isLastPage);
//
//        model.addAttribute("marketGoodsList", list);
//        model.addAttribute("currentPage", 0);
//
//        return "page/goods";
//    }

    //前往首页
    @RequestMapping(value = "/goToFirstPage.do")
    public ModelAndView goToFirstPage(HttpServletRequest request, Model model, String sortKind, String keywords)
    {
        ModelAndView mv = new ModelAndView();
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");

        boolean isLastPage = false;

        if(goodsService.getGoodsSum() <= 12)
        {
            isLastPage = true;
        }

        List<MarketGoods> list;

        Integer sk = Integer.parseInt(sortKind);
        if (keywords != null) {
            list = goodsService.SortList(sk, goodsService.SearchGoods(keywords)) ;
        }
        else
        {
            list = goodsService.SortBySortKind(0, sk);
        }

        mv.addObject("isLastPage",isLastPage);
        mv.addObject("marketGoodsList",list);
        mv.addObject("currentPage", 0);
        mv.setViewName("page/goods");

        return mv;
    }

//    //前往尾页
//    @RequestMapping(value = "/goToLastPage.do")
//    public String goToLastPage(HttpServletRequest request, Model model)
//    {
//        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
//
//        Integer sum = goodsService.getGoodsSum();
//
//        Integer page;
//
//        if(sum%12==0)
//            page = sum - 12;
//        else
//            page = sum - sum%12;
//
//
//        List<MarketGoods> list = selectTen(page);
//
//        boolean isLastPage = true;
//
//        model.addAttribute("isLastPage", isLastPage);
//        model.addAttribute("marketUser", user);
//        model.addAttribute("marketGoodsList", list);
//        model.addAttribute("currentPage", page);
//
//        return "page/goods";
//    }

    //前往尾页
    @RequestMapping(value = "/goToLastPage.do")
    public ModelAndView goToLastPage(HttpServletRequest request, Model model, String sortKind, String keywords)
    {
        ModelAndView mv = new ModelAndView();
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");

        Integer sum = goodsService.getGoodsSum();

        Integer page;

        if(sum % 12==0)
            page = sum - 12;
        else
            page = sum - sum % 12;
        List<MarketGoods> list;

        Integer sk = Integer.parseInt(sortKind);
        if (keywords != null) {
            list = goodsService.SortList(sk, goodsService.SearchGoods(keywords)) ;
        }
        else
        {
            list = goodsService.SortBySortKind(page * 12, sk);
        }
        mv.addObject("isLastPage", true);
        mv.addObject("marketGoodsList",list);
        mv.addObject("currentPage", page);
        mv.setViewName("page/goods");

        return mv;

    }

//    //翻页
//    @RequestMapping(value = "/changePage.do")
//    public String changePage(HttpServletRequest request, Model model, @RequestParam String pages)
//    {
//        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
//
////        if(StringUtils.getInstance().isNullOrEmpty(user))
////        {
////            return "page/login_page";
////        }
//        model.addAttribute("marketUser", user);
//        Integer page = Integer.parseInt(pages);
//
//        List<MarketGoods> list = selectTen(page);
//
//        boolean isLastPage = false;
//
//        if(page + 12 >= goodsService.getGoodsSum())
//        {
//            isLastPage = true;
//        }
//        //System.out.println(goodsService.getGoodsSum());
//
//        model.addAttribute("isLastPage", isLastPage);
//
//        model.addAttribute("marketGoodsList", list);
//        model.addAttribute("currentPage", page);
//
//        return "page/goods";
//    }

    //翻页
    @RequestMapping(value = "/changePage.do")
    public ModelAndView changePage(HttpServletRequest request, @RequestParam String pages, String sortKind, String keywords)
    {
        MarketUser user = (MarketUser) request.getSession().getAttribute("marketUser");
//        if(StringUtils.getInstance().isNullOrEmpty(user))
//        {
//            return "page/login_page";
//        }
        ModelAndView mv = new ModelAndView();
        /*model.addAttribute("marketUser", user);*/
        mv.addObject("marketUser", user);
        Integer page = Integer.parseInt(pages);
        List<MarketGoods> list;

        Integer sk = Integer.parseInt(sortKind);
        //System.out.println(keywords);
        if (keywords != null && !keywords.equals("")) {
            list = goodsService.SortList(sk, goodsService.SearchGoods(keywords));
        }
        else
        {
            list = goodsService.SortList(sk, goodsService.SearchGoods("书 床上用品 日用品 学习用品"));
        }
        //System.out.println(list.size());

        boolean isLastPage = false;

        if(page + 12 >= list.size())
        {
            isLastPage = true;
            list = list.subList(page, list.size());
        }
        else {
            list = list.subList(page, page + 12);
        }
        //System.out.println(goodsService.getGoodsSum());

//        model.addAttribute("isLastPage", isLastPage);
        mv.addObject("isLastPage", isLastPage);
        //System.out.println(page);

        //System.out.println(list);

        /*model.addAttribute("marketGoodsList", list);
        model.addAttribute("currentPage", page);
        model.addAttribute("currentType", type);*/



        mv.addObject("marketGoodsList", list);
        mv.addObject("currentPage", page);
        mv.addObject("sortKind", sortKind);
        mv.addObject("Keywords",keywords);

        mv.setViewName("page/goods");
        return mv;
    }

//    //获取十个商品，用于在商品页面展示
//    private List<MarketGoods> selectTen(int start)
//    {
//        Map map = new HashMap();
//        map.put("start", start);
//        List<MarketGoods> list = goodsService.selectTenGoods(map);
//        System.out.println(list);
//        return list;
//    }
}
