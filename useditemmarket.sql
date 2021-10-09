-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: useditemmarket
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `marketgoods`
--

DROP TABLE IF EXISTS `marketgoods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marketgoods` (
  `GID` varchar(10) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Kind` varchar(30) DEFAULT NULL,
  `Price` double(10,2) NOT NULL,
  `Number` int NOT NULL,
  `Image` varchar(500) DEFAULT NULL,
  `Comment` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`GID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marketgoods`
--

LOCK TABLES `marketgoods` WRITE;
/*!40000 ALTER TABLE `marketgoods` DISABLE KEYS */;
INSERT INTO `marketgoods` VALUES ('1000000009','白夜行','书',12.00,1,'image\\9Ck802ifG71627542738960.jpg','一本侦探悬疑小说，日本知名作家所著'),('1000000010','女士用自行车','日用品',100.00,1,'image\\0l0E2NyUvH1627543094856.jpg','轻便快捷，出行必备'),('1000000011','条纹床单','床上用品',20.00,1,'image\\xe2UT3m7jB1627543151919.jpg','蓝灰条纹的床单，仅用过一次'),('1000000012','耐用A4纸','学习用品',0.10,100,'image\\VCPRiANIZI1627543248728.jpg','一大包A4纸，用不完了，现在一毛一张'),('1000000013','刺客信条4免安装硬盘版','日用品',56.00,1,'image\\CFqc6OuCsM1627543405857.jpg','自己以前租的盗版碟子，通关10次了，现在出了'),('1000000014','魔兽世界月卡','日用品',20.00,1,'image\\QCSOEnI5YF1627543489337.jpg','点卡买多了，出了'),('1000000015','巫妖王历险记','书',25.00,1,'image\\9XYh3NxwBa1627543534675.jpg','洛丹伦第一孝子阿尔萨斯的故事'),('1000000016','漩涡鸣人手办','日用品',500.00,1,'image\\xpBdpAklhN1627543617620.jpg','B站倒闭时的手办，建议珍藏'),('1000000017','气垫枕头','床上用品',21.00,1,'image\\1URR7MH6nL1627543686584.jpg','气垫枕头，太高了，枕着不舒服，只用过一次，出了'),('1000000019','计算机网络','书',20.00,0,'image\\YXSVCl6F8S1627543842988.jpg','黄指导上课用的教科书，建议现在就买，书店比这个贵'),('1000000020','外观酷炫的电动车','日用品',250.00,1,'image\\PlkO0N509M1627543929430.jpg','自己用了四年的电动车，翔安不让骑了，趁机出了'),('1000000022','夏日太空被','床上用品',10.00,2,'image\\9PebP0CpI81627544057176.jpg','适合夏天的太空被，盖起来很舒服'),('1000000023','计算机组成原理','书',10.00,1,'image\\JGPBVjSUHX1627544181860.jpg','计组上课的教科书，老师说了推荐来学长这里买，还等什么？快来买吧！'),('1000000024','不锈钢脸盆','日用品',5.00,1,'image\\UZUpb319yi1627544284371.jpg','虽然是脸盆，但是可以用来洗脸、洗脚、吃饭等，一盆多用'),('1000000025','纯棉枕巾','床上用品',12.00,2,'image\\3LpqsARzCJ1627545264526.jpg','三个枕巾，蓝色黄色粉色，没用过'),('1000000026','一套三角板','学习用品',10.00,1,'image\\hURWsp2Uni1627545313562.jpg','注意，是一整套！不单卖！'),('1000000027','晨光橡皮','学习用品',1.00,6,'image\\zXHLgXT6uE1627545818216.jpg','晨光橡皮，6个'),('1000000028','盲僧至高之拳皮肤','日用品',200.00,1,'image\\wpBufiJkLd1627545950370.jpg','盲僧专用装逼皮肤'),('1000000029','三色马克笔','学习用品',5.00,6,'image\\2UUwQpUQ1H1627546040170.jpg','一根五块，不搞价，因为五字不行'),('1000000030','坐垫','床上用品',14.00,1,'image\\5oP7mJ45la1627547035531.jpg','一个小坐垫，可以用来在床上垫屁股'),('1000000031','黑色笔袋','学习用品',16.00,1,'image\\yDTxtmPj3R1627547215013.jpg','自己用的笔袋，要毕业了，出掉'),('1000000032','晨光笔芯','学习用品',0.50,10,'image\\8afyUZYSzc1627607148479.jpg','好用不贵，一周只需要一根'),('1000000033','测试商品','书',12.00,1,'image\\cJyWKr0fQi1627612735822.jpg','这是一个测试商品');
/*!40000 ALTER TABLE `marketgoods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salegoods`
--

DROP TABLE IF EXISTS `salegoods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salegoods` (
  `UID` char(18) NOT NULL,
  `GID` varchar(10) NOT NULL,
  PRIMARY KEY (`UID`,`GID`),
  CONSTRAINT `salegoods_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `user` (`UID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salegoods`
--

LOCK TABLES `salegoods` WRITE;
/*!40000 ALTER TABLE `salegoods` DISABLE KEYS */;
INSERT INTO `salegoods` VALUES ('NORM11920192203642','1000000009'),('NORM11920192203642','1000000010'),('NORM11920192203642','1000000011'),('NORM11920192203642','1000000012'),('NORM11920192203642','1000000033'),('NORM11920192203643','1000000013'),('NORM11920192203643','1000000014'),('NORM11920192203643','1000000015'),('NORM11920192203643','1000000016'),('NORM11920192203643','1000000017'),('NORM11920192203643','1000000032'),('NORM11920192203644','1000000019'),('NORM11920192203644','1000000020'),('NORM11920192203644','1000000022'),('NORM11920192203644','1000000031'),('NORM11920192203645','1000000023'),('NORM11920192203645','1000000024'),('NORM11920192203645','1000000025'),('NORM11920192203645','1000000026'),('NORM11920192203646','1000000027'),('NORM11920192203646','1000000028'),('NORM11920192203646','1000000029'),('NORM11920192203646','1000000030');
/*!40000 ALTER TABLE `salegoods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingcart`
--

DROP TABLE IF EXISTS `shoppingcart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shoppingcart` (
  `UID` char(18) NOT NULL,
  `GID` varchar(10) NOT NULL,
  `Number` int NOT NULL,
  PRIMARY KEY (`UID`,`GID`),
  CONSTRAINT `shoppingcart_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `user` (`UID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingcart`
--

LOCK TABLES `shoppingcart` WRITE;
/*!40000 ALTER TABLE `shoppingcart` DISABLE KEYS */;
INSERT INTO `shoppingcart` VALUES ('NORM11920192203642','1000000032',10),('NORM11920192203644','1000000012',100),('NORM34520192201617','1000000018',50);
/*!40000 ALTER TABLE `shoppingcart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `traderecord`
--

DROP TABLE IF EXISTS `traderecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `traderecord` (
  `PID` varchar(10) NOT NULL,
  `BuyerID` char(18) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Time` time DEFAULT NULL,
  `GID` varchar(10) DEFAULT NULL,
  `Gname` varchar(255) DEFAULT NULL,
  `Gkind` varchar(30) DEFAULT NULL,
  `Gprice` double(10,2) NOT NULL,
  `Gnumber` int NOT NULL,
  `IsSent` varchar(45) DEFAULT '0',
  `IsGot` varchar(45) DEFAULT '0',
  PRIMARY KEY (`PID`),
  KEY `BuyerID` (`BuyerID`),
  CONSTRAINT `traderecord_ibfk_1` FOREIGN KEY (`BuyerID`) REFERENCES `user` (`UID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `traderecord`
--

LOCK TABLES `traderecord` WRITE;
/*!40000 ALTER TABLE `traderecord` DISABLE KEYS */;
INSERT INTO `traderecord` VALUES ('1000000001','ADMI0123456789','2021-07-14','00:25:57','65121','0.5mm晨光黑色笔芯','学习用品',1.00,1,'0','0'),('1000000002','NORM11920192203644','2021-07-30','08:48:59','1000000025','纯棉枕巾','床上用品',12.00,1,'1','1'),('1000000003','NORM34520192201617','2021-07-30','10:41:05','1000000019','计算机网络','书',20.00,1,'1','1'),('1000000004','NORM11920192203642','2021-07-30','10:44:49','1000000032','晨光笔芯','学习用品',0.50,40,'0','0');
/*!40000 ALTER TABLE `traderecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UID` char(18) NOT NULL,
  `Uname` varchar(30) NOT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `PhoneNum` char(11) DEFAULT NULL,
  `Password` varchar(32) NOT NULL,
  `Status` varchar(45) DEFAULT '0',
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('ADMI0123456789','admin','123456','123456','25d55ad283aa400af464c76d713c07ad','0'),('NORM11920192203642','袁佳','529106896@qq.com','1860389963','bdafe63038dfa0ac509cc80321e5433c','0'),('NORM11920192203643','测试用户1','qq6666774@163.com','12345','e10adc3949ba59abbe56e057f20f883e','0'),('NORM11920192203644','测试用户2','qq6666774@126.com','12345','e10adc3949ba59abbe56e057f20f883e','0'),('NORM11920192203645','测试用户3','qq529106896@126.com','12345','e10adc3949ba59abbe56e057f20f883e','0'),('NORM11920192203646','测试用户4','qq529106896@163.com','12345','e10adc3949ba59abbe56e057f20f883e','0'),('NORM11920192203647','张大炮','5465423231@qq.com','4156485615','e10adc3949ba59abbe56e057f20f883e','0'),('NORM11920192203648','李云龙','318979318@qq.com','111888','e10adc3949ba59abbe56e057f20f883e','2'),('NORM22920192204246','刘赫昭','1020872823@qq.com','12345','e10adc3949ba59abbe56e057f20f883e','0'),('NORM30220192201014','程昊天','2022568708@qq.com','119','e10adc3949ba59abbe56e057f20f883e','2'),('NORM34520192201612','谢建祥','2579510936@qq.com','120','e10adc3949ba59abbe56e057f20f883e','0'),('NORM34520192201617','徐荪睿','318979318@qq.com','110','e10adc3949ba59abbe56e057f20f883e','0'),('NORM99999999','测试添加用户1','test@qq.com','12345678','13f46434735cd97fbaa9b49397af98f7','1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-09 20:49:30
