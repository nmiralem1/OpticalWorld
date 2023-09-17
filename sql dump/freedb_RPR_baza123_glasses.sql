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
-- Table structure for table `glasses`
--

DROP TABLE IF EXISTS `glasses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `glasses` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `category` varchar(150) NOT NULL,
  `price` double NOT NULL,
  `image` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `glasses`
--

LOCK TABLES `glasses` WRITE;
/*!40000 ALTER TABLE `glasses` DISABLE KEYS */;
INSERT INTO `glasses` VALUES (1,'Vogue 4274 323 53','Dioptric',135,'images/vogue-dioptrijske1.png'),(2,'Vogue 4255 567 22','Dioptric',140,'images/vogue-dioptrijske2.png'),(3,'Vogue 2677 855 11','Dioptric',155,'images/vogue-dioptrijske3.png'),(4,'Vogue 1233 455 22','Dioptric',145,'images/vogue-dioptrijske4.png'),(5,'Vogue 7555 345 13','Dioptric',150,'images/vogue-dioptrijske5.png'),(6,'Vogue 2222 334 44','Sunglasses',190,'images/vogue-suncane1.png'),(7,'Vogue 1111 222 45','Sunglasses',200,'images/vogue-suncane1.png'),(8,'Vogue 2345 111 33','Sunglasses',210,'images/vogue-suncane1.png'),(9,'Vogue 9000 984 78','Sunglasses',165,'images/vogue-suncane1.png'),(10,'Vogue 9122 567 90','Sunglasses',195,'images/vogue-suncane1.png'),(11,'Vogue 5678 111 11','Sunglasses',230,'images/vogue-suncane1.png'),(12,'Vogue 1256 222 22','Sunglasses',210,'images/vogue-suncane1.png'),(13,'Only One otopina za lece 100ml','Lenses',12,'images/lece1.png'),(14,'Renu otopina za lece 100 ml','Lenses',15,'images/lece1.png'),(15,'Only one otopina za lece 360 ml','Lenses',18,'images/lece1.png'),(16,'Boston Cleaner 30 ml','Lenses',18,'images/lece1.png'),(17,'Bausch+lomb lece u boji','Lenses',85,'images/lece1.png'),(18,'Vogue 4274 323 53','Dioptric',150,'images/vogue-dioptrijske1.png'),(19,'Vogue 4255 567 22','Dioptric',220,'images/vogue-dioptrijske2.png'),(20,'Vogue 2677 855 11','Dioptric',300,'images/vogue-dioptrijske3.png'),(21,'Vogue 1233 455 22','Dioptric',170,'images/vogue-dioptrijske4.png'),(22,'Vogue 7555 345 13','Dioptric',180,'images/vogue-dioptrijske5.png'),(23,'Vogue 4274 323 53','Dioptric',150,'images/vogue-dioptrijske1.png'),(24,'Vogue 4255 567 22','Dioptric',220,'images/vogue-dioptrijske2.png'),(25,'Vogue 2677 855 11','Dioptric',300,'images/vogue-dioptrijske3.png'),(26,'Vogue 1233 455 22','Dioptric',170,'images/vogue-dioptrijske4.png'),(27,'Vogue 7555 345 13','Dioptric',180,'images/vogue-dioptrijske5.png'),(28,'Vogue 4274 323 53','Dioptric',150,'images/vogue-dioptrijske1.png'),(29,'Vogue 4255 567 22','Dioptric',220,'images/vogue-dioptrijske2.png'),(30,'Vogue 2677 855 11','Dioptric',300,'images/vogue-dioptrijske3.png'),(31,'Vogue 1233 455 22','Dioptric',170,'images/vogue-dioptrijske4.png'),(32,'Vogue 7555 345 13','Dioptric',180,'images/vogue-dioptrijske5.png'),(33,'Vogue 4274 323 53','Dioptric',150,'images/vogue-dioptrijske1.png'),(34,'Vogue 4255 567 22','Dioptric',220,'images/vogue-dioptrijske2.png'),(35,'Vogue 2677 855 11','Dioptric',300,'images/vogue-dioptrijske3.png'),(36,'Vogue 1233 455 22','Dioptric',170,'images/vogue-dioptrijske4.png'),(37,'Vogue 7555 345 13','Dioptric',180,'images/vogue-dioptrijske5.png');
/*!40000 ALTER TABLE `glasses` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-17 15:07:57
