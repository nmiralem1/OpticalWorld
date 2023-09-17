-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: sql.freedb.tech    Database: freedb_RPR_baza123
-- ------------------------------------------------------
-- Server version	8.0.34-0ubuntu0.22.04.1

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `password` varchar(256) NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `isAdministrator` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'hmahmutovic1','hanamahm1','NewName','Mahmutovic','hanamahmut1@gmail.com',1),(3,'nmiralem1','nadinam1','NewName','Miralem','nmiralem1@gmail.com',0),(4,'nkadric','nerka1','Nerma','Kadric','nerka1@gmail.com',0),(5,'uhodzic','unahodz','Una','Hodzic','uhodzic@gmail.com',0),(6,'adnamir','adnamir11','Adna','Miralem','adnamira@gmail.com',0),(7,'nedzlahel','nedzlah111','Nedzla','Helac','nedzlahelac@gmail.com',1),(8,'h','aaa9402664f1a41f40ebbc52c9993eb66aeb366602958fdfaa283b71e64db123','h','h','h@gmail.com',0),(9,'n','aaa9402664f1a41f40ebbc52c9993eb66aeb366602958fdfaa283b71e64db123','n','n','n@gmail.com',1),(10,'nbecir','fab66aa01347d3f11a16468941378cea495937e5f482e18ea6472681e03d3936','Nejla','Becirspahic','nbecir@gmail.com',0),(11,'adnam11','30e69123333d1df25cebae29bde343415a11f5b00fae3c78734f67479939039f','Adna','Miralem','adnam@gmail.com',0),(12,'nadin1','f9ea457d6e5fbe2e91ef572f945d3c57d4571dd1f8d5fdfccc3b48771a335ffe','nadina','mir','nadinamiralem@gmail.com',0),(13,'nm','1b16b1df538ba12dc3f97edbb85caa7050d46c148134290feba80f8236c83db9','NewName','n','n@gmail.com',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-17 15:07:58
