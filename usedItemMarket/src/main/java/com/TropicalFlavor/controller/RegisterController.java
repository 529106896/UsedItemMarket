package com.TropicalFlavor.controller;

import com.TropicalFlavor.po.MarketUser;
import com.TropicalFlavor.service.UserService;
import com.TropicalFlavor.tool.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController
{

    @Resource
    private UserService userService;

    @RequestMapping("/goToRegister.do")
    public String goToRegister()
    {
        return "/page/register1";
    }

    @RequestMapping("/registerUser.do")
    public String insertUser(HttpServletRequest request, Model model, @RequestParam String sNum, @RequestParam String Uname,
                             @RequestParam String Password, @RequestParam String Email, @RequestParam String PhoneNum,
                             @RequestParam String rePassword)
    {
//        String insertUserToken = (String)request.getSession().getAttribute("token");
//        //String realUID = (String)request.getSession().getAttribute("UID");
//
//        if(StringUtils.getInstance().isNullOrEmpty(insertUserToken) || !insertUserToken.equals(token))
//        {
//            return "redirect:/goToRegister.do";
//        }
        String sNumError;
        String pwdError;
        String emailError;
        String phoneNumError;
        String uNameError;
        String emptyError;
        boolean isInfoCorrect = true;
        if(sNum.isEmpty() || Uname.isEmpty() || Email.isEmpty()
                || PhoneNum.isEmpty() || Password.isEmpty() || rePassword.isEmpty())
        {
            emptyError = "注册信息不能留空！";
            model.addAttribute("emptyError", emptyError);
            //return "redirect:/goToRegister.do";
            return "/page/register1";
        }

        if(sNum.length() > 14)
        {
            sNumError = "学号过长！";
            model.addAttribute("sNumError", sNumError);
            isInfoCorrect = false;
        }
        if(Uname.length() > 30)
        {
            uNameError = "姓名过长！";
            model.addAttribute("uNameError", uNameError);
            isInfoCorrect = false;
        }
        if(!Email.contains("@"))
        {
            emailError = "邮箱格式格式错误！";
            model.addAttribute("emailError", emailError);
            isInfoCorrect = false;
        }
        if(Email.length() > 50)
        {
            emailError = "邮箱过长！";
            model.addAttribute("emailError", emailError);
            isInfoCorrect = false;
        }
        if(PhoneNum.length() > 11)
        {
            phoneNumError = "电话过长！";
            model.addAttribute("phoneNumError", phoneNumError);
            isInfoCorrect = false;
        }
        if(!Password.equals(rePassword))
        {
            pwdError = "两次密码输入不一致";
            model.addAttribute("pwdError", pwdError);
            isInfoCorrect = false;
        }
        if(!isInfoCorrect)
        {
            return "page/register1";
        }


        MarketUser user = new MarketUser(Uname, Email, PhoneNum, Password, sNum);
        boolean isRegisterSuccess = userService.Register(user);
        if(isRegisterSuccess)
        {
            request.getSession().setAttribute("marketUser", user);
            return "redirect:/";
        }
        else
        {
            return "redirect:/goToRegister.do";
        }
    }

}
