-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: school_managment_sys_db
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_type` varchar(100) NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `user_email` varchar(100) NOT NULL,
  `user_contact` varchar(50) NOT NULL,
  `user_gender` varchar(100) DEFAULT NULL,
  `user_nic` varchar(100) NOT NULL,
  `user_dbo` varchar(50) NOT NULL,
  `user_doa` varchar(50) NOT NULL,
  `user_address` varchar(100) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'student','niha','nk@gmail.com','000','Female','4220167425156','12-02-2000','9','hssus'),(2,'teacher','abdullah','aa@gmail.com','99','Male','4220167425157','7-08-1999','7','dhdbhdb'),(3,'principal','shiza','sz@gmail.com','88','Female','4220167425157','6-07-1999','8','jjj'),(4,'student','niha1','nm@gmail.com','0000000000','Female','1234567890','2000-02-12','2021-02-12','xy colony'),(5,'student','alisha1','ak@gmail.com','88','Male','4220167425157','1-01-2001','3','ehbhe1'),(6,'teacher','aman','aa@gmail.com','99','Male','4220167425157','7-08-1999','7','dhdbhdb'),(7,'student','karim ','km@gmail.com','0000000000','Male','42453672893992','2000-12-20','2000-12-20','xy colony'),(9,'student','karima','km@gmail.com','0000000000','Female','42453672893992','2000-12-20','2000-12-20','xy colony'),(10,'student','ali','ali@yahoo.com','1234567890','Male','00000000000','2001-10-12','2010-10-12','ff tower'),(11,'student','shizapizza','shiza@yahoo.com','1234567890','Female','123456789','1999-02-12','2010-02-12','shiza colony'),(14,'teacher','simran','zzyy','1234567890','Female','1234567890','2000-01-01','1996-01-01','zzxx'),(15,'teacher','niha_temp','niha@gmail.com','1234567890','Female','123456789','2000-02-12','2000-02-12','niha tower');
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

-- Dump completed on 2021-12-10  3:21:36
