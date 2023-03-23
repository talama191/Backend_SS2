-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ecommerce
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `FKl70asp4l4w0jmbm1tqyofho4o` (`user_id`),
  CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (2,2),(3,3),(4,4),(9,9);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_line`
--

DROP TABLE IF EXISTS `cart_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_line` (
  `quantity` int DEFAULT NULL,
  `unit_price` bigint DEFAULT NULL,
  `cart_cart_id` int NOT NULL,
  `product_id` int NOT NULL,
  PRIMARY KEY (`cart_cart_id`,`product_id`),
  KEY `FK6fx7athvka6ywajy8bsqc0mdh` (`product_id`),
  CONSTRAINT `FK6fx7athvka6ywajy8bsqc0mdh` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKoxftytjdq2360gy4xns81kd7r` FOREIGN KEY (`cart_cart_id`) REFERENCES `cart` (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_line`
--

LOCK TABLES `cart_line` WRITE;
/*!40000 ALTER TABLE `cart_line` DISABLE KEYS */;
INSERT INTO `cart_line` VALUES (3,NULL,2,2),(1,NULL,3,1),(16,NULL,3,2);
/*!40000 ALTER TABLE `cart_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'ACCESSORIES'),(2,'BAGS'),(3,'MEN'),(4,'WOMEN'),(5,'SHOES'),(6,'Thể thao'),(7,'Văn phòng phẩm');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_line`
--

DROP TABLE IF EXISTS `order_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_line` (
  `quantity` int DEFAULT NULL,
  `unit_price` bigint DEFAULT NULL,
  `order_id` bigint NOT NULL,
  `product_id` int NOT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `FKpf904tci8garypkvm32cqupye` (`product_id`),
  CONSTRAINT `FKk9f9t1tmkbq5w27u8rrjbxxg6` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKpf904tci8garypkvm32cqupye` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_line`
--

LOCK TABLES `order_line` WRITE;
/*!40000 ALTER TABLE `order_line` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `payment_mode` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_price` bigint DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`),
  CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `img1` varchar(255) DEFAULT NULL,
  `long_description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` bigint DEFAULT NULL,
  `short_description` varchar(255) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `img2` varchar(100) DEFAULT NULL,
  `img3` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `img4` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl1-330x421.jpg','Giảm chất béo Cài đặt nhiệt độ nấu từ 80 - 200°C, thời gian hẹn giờ lên đến 60 phút Rổ chiên dung tích lớn 5.2L Nút giữ ấm Điều khiển cảm ứng','PAUL SMITH',45,'Nồi Chiên Không Dầu Điện Tử Lock&Lock EJF357BLK (5.2 Lít) - Hàng Chính Hãng',1,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl2-235x300.jpg','https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl1-100x100.jpg','https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image3xxl3-100x100.jpg'),(2,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl2-330x421.jpg',NULL,'POCKET WATCH',16,NULL,1,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image2xxl6-235x300.jpg','https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image3xxl5-100x100.jpg','https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl4-100x100.jpg'),(3,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl3-330x421.jpg',NULL,'ASAP BAG',29,NULL,2,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl7-235x300.jpg','https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image3xxl9-100x100.jpg','https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image2xxl10-100x100.jpg'),(4,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl-111-330x421.jpg',NULL,'CHEAP MONDAY',20,NULL,3,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl22-235x300.jpg','https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image3xxl23-100x100.jpg','https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image2xxl25-100x100.jpg'),(5,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl19-330x421.jpg',NULL,'COOL KIDS',3,NULL,4,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl37-235x300.jpg',NULL,NULL),(6,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/sle-330x421.jpg',NULL,'SELECTED',20,NULL,3,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl31-235x300.jpg',NULL,NULL),(7,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl-112-330x421.jpg',NULL,'RAYBAN CLASSICS',15,NULL,1,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl23-235x300.jpg','https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image3xxl24-100x100.jpg','https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image2xxl26-100x100.jpg'),(8,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl16-330x421.jpg',NULL,'JENGA',35,NULL,5,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl33-235x300.jpg',NULL,NULL),(9,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl15-330x421.jpg ',NULL,'DIALOG',15,NULL,5,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image3xxl33-235x300.jpg',NULL,NULL),(10,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl-114-330x421.jpg',NULL,'JEKYLL',9,NULL,5,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl32-235x300.jpg',NULL,NULL),(11,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl-115-330x421.jpg',NULL,'LEATHER ONE',35,NULL,5,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl32-235x300.jpg',NULL,NULL),(12,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl-116-330x421.jpg',NULL,'LOAFERS',20,NULL,5,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl35-235x300.jpg',NULL,NULL),(13,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl18-330x421.jpg',NULL,'ONETWOS SHOES',20,NULL,5,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl36-235x300.jpg',NULL,NULL),(14,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl9-330x421.jpg\"',NULL,'ABANDON SHIP',49,NULL,3,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image2xxl22-235x300.jpg',NULL,NULL),(15,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl-110-330x421.jpg',NULL,'BILLIONAIRE BOYS',20,NULL,3,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl20-235x300.jpg',NULL,NULL),(16,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image3xxl26-330x421.jpg',NULL,'LONGLINE',20,NULL,3,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image3xxl26-235x300.jpg',NULL,NULL),(17,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl13-330x421.jpg',NULL,'NATIVE YOUTH',15,NULL,3,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image3xxl27-235x300.jpg',NULL,NULL),(18,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl14-330x421.jpg',NULL,'NIKECRW',35,NULL,3,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl27-235x300.jpg',NULL,NULL),(19,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl20-330x421.jpg',NULL,'MONKI',15,NULL,4,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl39-235x300.jpg',NULL,NULL),(20,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl-117-330x421.jpg',NULL,'JUMPSUIT',2,NULL,4,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl38-235x300.jpg',NULL,NULL),(21,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl-118-330x421.jpg',NULL,'NIKE CROWL',18,NULL,4,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl40-235x300.jpg',NULL,NULL),(23,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl8-330x421.jpg',NULL,'VINTAGE',20,NULL,2,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl18-235x300.jpg',NULL,NULL),(24,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl-16-330x421.jpg',NULL,'CAYLER',29,NULL,2,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl11-235x300.jpg',NULL,NULL),(26,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl5-330x421.jpg',NULL,'',20,NULL,2,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl10-235x300.jpg',NULL,NULL),(27,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl-17-330x421.jpg',NULL,'DKNY BAG',20,NULL,2,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl13-235x300.jpg',NULL,NULL),(28,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl6-330x421.jpg',NULL,'LYLESCOTT',20,NULL,2,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl15-235x300.jpg',NULL,NULL),(29,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image3xxl-235x300.jpg',NULL,'BOBBLE BEANIE',22,NULL,1,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image3xxl-235x300.jpg',NULL,NULL),(30,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl-330x421.jpg',NULL,'FESTIVAL STONE',13,NULL,1,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl1-235x300.jpg',NULL,NULL),(31,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image1xxl6-330x421.jpg',NULL,'LYLESCOTT',20,NULL,1,'https://themes.laborator.co/aurum/fashion/wp-content/uploads/2014/11/image4xxl15-235x300.jpg',NULL,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,NULL,'1990-11-11','bang@gmail.com','Nguyen Vi bang','M','$2a$10$bSPItVwIIk6DWWan6gYzk.xucFuQIUxhmOvFboOwr.6NBdqghhgAe','0986968647','bang'),(3,'thanh hoa',NULL,'duy@gmail.com','nguyen van duy',NULL,'$2a$10$EJZCED4D4drcNsO/sPXZz.PAEzCmTXxwuW/Y27wv1vStbuDKdH6B6','0966062034','duy'),(4,'nam',NULL,'hieu@gmail.com','nguyen khac hieu',NULL,'$2a$10$EGaRgyCvQBOMDIFL83DMZOoEYFqqvgP1IMmWmtSwSP2cC0vCy0.SC','808080','hieu'),(9,'nams',NULL,'vituan1912000@gmail.coms','Nguyen Vi Tuanasd',NULL,'$2a$10$QlQN9Itqq1f9Q42pSGws8OKY2wWvQuGPWMoj7lsdTXl5ZQrZHgcVe','09660620342','tuan');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKrhfovtciq1l558cw6udg0h0d3` (`role_id`),
  CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKrhfovtciq1l558cw6udg0h0d3` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (2,2),(3,2),(4,2),(9,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher`
--

DROP TABLE IF EXISTS `voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voucher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `discount_percent` int DEFAULT NULL,
  `expired_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
/*!40000 ALTER TABLE `voucher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-17 18:05:23
