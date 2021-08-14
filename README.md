# UsedItemMarket
大二小学期Java程序设计实践的项目，校园二手交易市场

部分代码参考https://github.com/wsk1103/Used-Trading-Platform

本项目是软件工程专业大二小学期Java程序设计实践的项目作业，主要采用：SpringMVC、SSM框架、MySQL、thymeleaf、jquery、ajax、JavaScript等

本项目为五人小组——热带风味泸溪河 的合作项目，非本人一人完成

<h3>使用前必看：使用须知.docx、系统设计.docx（或者直接看相应md也可以）</h3>

<h2>以下也是必看内容</h2>

课程项目要求如下：
![image](https://user-images.githubusercontent.com/54974387/128868555-e729700f-8980-48a1-9a44-c18f665d47ed.png)

![image](https://user-images.githubusercontent.com/54974387/128868649-3ab551b9-8600-4c77-959d-3c82ef440c47.png)

已实现的项目需求如下：

1、登录注册

（1）管理员的账号和密码是内定的，无需注册

（2）用户登录（注销）

（3）用户注册

2、管理员管理用户

（1）更改用户信息（其中密码只可重置不可随意修改）

（2）注销用户

（3）新增用户（密码为默认密码）

（4）查看买卖记录

3、卖家发布商品

（1）下架商品（将商品数量置为0，保留在数据库中）

（2）新增商品

（3）修改商品信息

4、买家购买商品

（1）提供商品的查找、排序、分类功能

（2）添加到购物车后可以修改商品数量或删除商品、在购物车中下单

（3）直接购买下单

5、个人信息

（1）查看买卖记录

（2）修改个人信息

（3）查看管理售卖商品

（4）查看管理购物车

6、拓展功能

（1）密码加密

（2）邮箱通知用户注销或重置密码

（3）用户完成交易需经历：买家下单->卖家发货->买家确认收货


<h4>关于系统设计以及数据库建表，因本人不太会使用markdown编写，图片插入后各种格式有问题，所以统一写在“系统设计.doc”中，可自行查阅</h4>


<h2>写在后面：</h2>

    因为这是我们第一次尝试完整地去开发一个可运行的网站，再加上学习+开发周期总共五周，时间较为仓促，所以各种功能并不完善。网页都是我们自己手写的，没有套用模板，所以很多地方会显得比较简陋。甚至在我们期末展示前半个小时，仍会发现bug
    
    这里说一下目前没有修复的bug（懒得修了）：
    
    1.商品分类逻辑是直接从商品名和商品种类中去模糊匹配，所以如果商品名中带有种类名，分类功能会出错
    
      举个例子：我们总共有四种分类——书、生活用品、学习用品、床上用品。当我需要选出所有“书”时，相应分类应该只显示kind属性为“书”的商品。但如果学习用品类的商品中有一个商品的名字是“订书机”，因为其中含有“书”这个关键字，所以在分类筛选时会把订书机也筛选出来，这就产生了错误
      
      一个可能的修复方法是重写分类逻辑，应该是在goodsController.java中，找到对应的mapper和service方法修改即可
      
    2.重置密码（包括重置个人密码与密码为初始密码时重置）时，有错误信息提示时仍可提交。这个是因为在最后的代码整合时不小心把正确的代码覆盖掉了，只需要在对应的html中找到对应的js修改即可
    
    3.翻页逻辑是手动实现的，比较笨拙，具体可以看controller中的带page的方法，可能存在的隐患是商品种类较多时会很卡。听说可以使用webhelper去实现翻页，但是开发时间比较短，没有尝试
    
    4.注册、上传、修改都没有去限制输入数据的合法性，因为数据库中的数据类型基本都是varchar，所以你给数量属性输汉字也是可以的
    
    5.上传图片功能很可能有bug（但我测试了几个，还没太大问题）
