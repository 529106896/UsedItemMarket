package com.TropicalFlavor.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil implements Runnable {

    private String email;// 收件人邮箱

    private String pwd;// 重置密码

    private Integer status;//邮件类型 1：重置密码 2：注销用户

    public MailUtil(String email, String pwd, Integer status) {
        this.email = email;
        this.pwd = pwd;
        this.status = status;
    }

    public MailUtil(String email, Integer status)
    {
        this.email = email;
        this.status = status;
    }

    public void run() {
        // 1.创建连接对象javax.mail.Session
        // 2.创建邮件对象 javax.mail.Message
        // 3.发送一封激活邮件
        String from = "529106896@qq.com";// 发件人电子邮箱
        String host = "smtp.qq.com"; // 指定发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)

        Properties properties = System.getProperties();// 获取系统属性

        properties.setProperty("mail.smtp.host", host);// 设置邮件服务器
        properties.setProperty("mail.smtp.auth", "true");// 打开认证

        try {
            //QQ邮箱需要下面这段代码，163邮箱不需要
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);


            // 1.获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("529106896@qq.com", "ufkftsbqbiekbghg"); // 发件人邮箱账号、授权码
                }
            });

            // 2.创建邮件对象
            Message message = new MimeMessage(session);
            // 2.1设置发件人
            message.setFrom(new InternetAddress(from));
            // 2.2设置接收人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            String content = "";

            if(status == 1)
            {
                // 2.3设置邮件主题
                message.setSubject("密码重置通知");
                // 2.4设置邮件内容
                content = "<html><head></head><body><h1>鉴于您账号的使用出现违规或异常，为避免盗号风险，已为您重置密码</h1>" +
                        "<h3>密码："+ pwd +"</h3>" +
                        "<h3>请及时修改以重获权限</h3>"+
                        "</body></html>";
            }
            else if(status == 2)
            {
                // 2.3设置邮件主题
                message.setSubject("用户注销通知");
                // 2.4设置邮件内容
                content = "<html><head></head><body><h1>鉴于您账号的使用出现违规操作，已注销您的用户</h1>" +
                        "<h3>我们将保留您的商品发布记录与联系方式，其余用户仍可通过联系方式与您进行线下交易</h3>" +
                        "<h3>感谢您的使用</h3>"+
                        "</body></html>";
            }

            else if(status == 3)
            {
                // 2.3设置邮件主题
                message.setSubject("用户注册通知");
                // 2.4设置邮件内容
                content = "<html><head></head><body><h1>已经为您在厦大二手交易平台注册新账号</h1>" +
                        "<h3>账号为您的学号,默认密码为"+pwd+" </h3>" +
                        "<h3>请及时上线修改密码获取权限,感谢您的使用</h3>"+
                        "</body></html>";
            }

            message.setContent(content, "text/html;charset=UTF-8");
            // 3.发送邮件
            Transport.send(message);
            System.out.println("邮件成功发送!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
