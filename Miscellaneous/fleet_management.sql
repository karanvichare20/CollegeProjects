-- MySQL dump 10.13  Distrib 5.6.27, for Win64 (x86_64)
--
-- Host: localhost    Database: fleet_management
-- ------------------------------------------------------
-- Server version	5.6.27-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `emp_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `pass` varchar(45) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `street` varchar(100) NOT NULL,
  `city` varchar(20) NOT NULL,
  `pincode` varchar(7) NOT NULL,
  `role` varchar(45) NOT NULL,
  `status` varchar(10) DEFAULT 'active',
  PRIMARY KEY (`emp_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `phone_UNIQUE` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=11025 DEFAULT CHARSET=utf8 COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (11001,'Amish','Karovalia','ami_kar@gmail.com','pass123','017644704501','Ludwig Guttmann Strasse-2','Heidelberg','69123','external','active'),(11002,'Martin','Gari','mrg@gmail.com','pass123','017644704502','Abend academy','Mannheim','69123','executive','active'),(11003,'Biswaroop','Sahoo','bissah@gmail.com','pass123','017644704503','Ludwig Guttmann Strasse-2','Heidelberg','69123','manager','active'),(11004,'Karan','Vichare','karvic@gmail.com','pass123','017644704504','Ludwig Guttmann Strasse-2','Heidelberg','69123','executive','active'),(11005,'Pravin','Konade','p_kon@gmail.com','pass321','017644704505','Ludwig Guttmann Strasse-2','Heidelberg','69123','lead','active'),(11006,'Vikas','Sharma','viksharma@hotmail.com','pass123','017644704506','Maria Prob. Strasse','Heidelberg','63223','senior','active'),(11007,'Eldon','Kuz','elkuz@yahoo.com','pass132','017644704507','Meckeshheim-2','Heidelberg','69111','external','active'),(11008,'Sachin','Mohorikar','sachmoh@gmail.com','pass111','017644704508','Hauptbahnhof-2 Zi. 111','Heidelberg','69123','junior','active'),(11009,'Vijay','Dandekar','vij_dand@gmail.com','pass123','017644704509','Ludwigshafen Strasse','Heidelberg','69113','staff','active'),(11010,'Frank','Walterscheidt','frank@gmail.com','pass123','017644704510','Ludwig Guttmann Strasse-3','Heidelberg','69123','staff','active'),(11011,'Ajaya','Kuvalia','ajayku@gmail.com','pass123','017644704511','Ludwig Guttmann Strasse-2','Heidelberg','69123','staff','active'),(11012,'Vijay','Tale','vtale@gmail.com','pass123','017644704512','Abend academy','Mannheim','69123','lead','active'),(11013,'Gaurav','Tamhane','gautam@gmail.com','pass123','017644704513','Ludwig Guttmann Strasse-2','Heidelberg','69123','manager','active'),(11014,'Rahul','Singh','rasi@gmail.com','pass123','017644704514','Ludwig Guttmann Strasse-2','Heidelberg','69123','senior','active'),(11015,'Sanket','Mysore','sam@gmail.com','pass321','017644704515','Ludwig Guttmann Strasse-2','Heidelberg','69123','junior','active'),(11016,'Omar','Sayed','omars@hotmail.com','pass123','017644704516','Maria Prob. Strasse','Heidelberg','63223','junior','active'),(11017,'Suraj','Salunkhe','sal_sur@yahoo.com','pass132','017644704517','Meckeshheim-2','Heidelberg','69111','external','active'),(11018,'Pritesh','Khamkar','kham_prit@gmail.com','pass111','017644704518','Hauptbahnhof-2 Zi. 111','Heidelberg','69123','manager','active'),(11019,'Sanjay','Kadam','sanj_kad@gmail.com','pass123','017644704519','Ludwigshafen Strasse','Heidelberg','69113','junior','active'),(11020,'Brijesh','Shetty','bshetty@gmail.com','pass123','017644704520','Ludwig Guttmann Strasse-3','Heidelberg','69123','lead','active'),(11021,'Dharesh','Shah','dshah@gmail.com','pass123','01764471234','Fliedelstrasse-19','Heidelberg','69911','senior','active'),(11022,'Darren','Brown','dbrown@gmail.com','pass123','017644766520','Fliedelstrasse-20','Heidelberg','69911','junior','active'),(11023,'Ajinkya','Patil','aji_patil@gmail.com','pass123','017600704520','Marie Probt Strasse-3','Heidelberg','66623','junior','active'),(11024,'Arron','Vogel','aronvogel@gmail.com','pass123','017647777720','Marie Probt Strasse-3','Heidelberg','69993','senior','active');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maintenance_tracker`
--

DROP TABLE IF EXISTS `maintenance_tracker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `maintenance_tracker` (
  `maint_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `veh_id` int(10) unsigned DEFAULT NULL,
  `maint_date` varchar(15) DEFAULT NULL,
  `due_date` varchar(15) DEFAULT NULL,
  `maint_cost` decimal(8,2) unsigned DEFAULT '0.00',
  `description` blob,
  `maint_type` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`maint_id`),
  KEY `maintenance_veh_id_fk_idx` (`veh_id`),
  CONSTRAINT `maintenance_veh_id_fk` FOREIGN KEY (`veh_id`) REFERENCES `vehicle` (`veh_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maintenance_tracker`
--

LOCK TABLES `maintenance_tracker` WRITE;
/*!40000 ALTER TABLE `maintenance_tracker` DISABLE KEYS */;
/*!40000 ALTER TABLE `maintenance_tracker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip_management`
--

DROP TABLE IF EXISTS `trip_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trip_management` (
  `trip_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `veh_id` int(10) unsigned DEFAULT NULL,
  `emp_id` int(10) unsigned DEFAULT NULL,
  `pick_pin` varchar(7) NOT NULL,
  `drop_pin` varchar(7) NOT NULL,
  `distance` decimal(5,2) unsigned DEFAULT '0.00',
  `fuel_used` decimal(5,2) unsigned DEFAULT '0.00',
  `trip_cost` decimal(7,2) unsigned DEFAULT '0.00',
  `book_time` varchar(30) DEFAULT NULL,
  `trip_start` varchar(30) DEFAULT NULL,
  `trip_end` varchar(30) DEFAULT NULL,
  `status` varchar(15) DEFAULT 'booked',
  PRIMARY KEY (`trip_id`),
  KEY `vehicle_fk_idx` (`veh_id`),
  KEY `employee_emp_id_fk_idx` (`emp_id`),
  CONSTRAINT `employee_emp_id_fk` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `vehicle_veh_id_fk` FOREIGN KEY (`veh_id`) REFERENCES `vehicle` (`veh_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11000038 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip_management`
--

LOCK TABLES `trip_management` WRITE;
/*!40000 ALTER TABLE `trip_management` DISABLE KEYS */;
INSERT INTO `trip_management` VALUES (11000001,1020,11009,'68169','68161',100.99,0.00,572.28,'2018-Jan-05 15:12:57','2018-Jan-05 15:13:51','2018-Jan-05 15:19:02','ended'),(11000002,1020,11009,'68159','68259',123.88,0.00,3221.71,'2018-Jan-05 15:20:23','2018-Jan-05 15:21:41','2018-Jan-05 15:44:59','ended'),(11000003,1020,11009,'68163','68165',100.00,0.00,42.00,'2018-Jan-05 15:47:09','2018-Jan-05 15:48:27','2018-Jan-05 15:52:17','ended'),(11000004,1021,11009,'68159','68169',0.00,0.00,0.00,'2018-Jan-05 15:53:57',NULL,NULL,'cancelled'),(11000005,1022,11009,'68169','68229',0.00,0.00,0.00,'2018-Jan-05 15:56:37',NULL,NULL,'cancelled'),(11000006,1021,11009,'68199','68165',0.00,0.00,0.00,'2018-Jan-05 16:05:51',NULL,NULL,'cancelled'),(11000007,1021,11009,'68165','68163',0.00,0.00,0.00,'2018-Jan-05 16:13:09',NULL,NULL,'cancelled'),(11000009,1021,11009,'68159','68169',0.00,0.00,0.00,'2018-Jan-05 16:23:10',NULL,NULL,'cancelled'),(11000010,1021,11009,'68307','68165',100.00,0.00,52.00,'2018-Jan-05 16:24:38','2018-Jan-05 16:25:14','2018-Jan-05 16:25:50','ended'),(11000011,1001,11003,'68159','68169',15.00,0.00,1.20,'2018-Jan-05 16:28:21','2018-Jan-06 07:05:16','2018-Jan-06 07:05:37','ended'),(11000012,1021,11001,'68159','68163',0.00,0.00,0.00,'2018-Jan-05 21:53:33',NULL,NULL,'cancelled'),(11000013,1021,11001,'68159','68169',50.00,0.00,23.00,'2018-Jan-06 06:55:34','2018-Jan-06 06:56:49','2018-Jan-06 06:57:04','ended'),(11000014,1004,11002,'68159','68169',200.00,0.00,44.21,'2018-Jan-06 06:58:18','2018-Jan-06 06:58:42','2018-Jan-06 06:59:10','ended'),(11000015,1004,11002,'68259','68159',210.00,0.00,44.21,'2018-Jan-06 06:59:34','2018-Jan-06 07:00:31','2018-Jan-06 07:01:16','ended'),(11000016,1004,11002,'68159','68259',200.10,0.00,25.28,'2018-Jan-06 07:02:01','2018-Jan-06 07:02:59','2018-Jan-06 07:23:48','ended'),(11000017,1003,11020,'68229','68169',0.00,0.00,0.00,'2018-Jan-06 07:16:50','2018-Jan-06 07:17:09',NULL,'enroute'),(11000018,1002,11021,'68305','68229',0.00,0.00,0.00,'2018-Jan-06 07:18:42','2018-Jan-06 07:19:02',NULL,'enroute'),(11000019,1005,11005,'68199','68169',45.90,0.00,4.12,'2018-Jan-06 07:20:29','2018-Jan-06 07:21:56','2018-Jan-06 07:22:47','ended'),(11000020,1001,11002,'68305','68163',0.00,0.00,0.00,'2018-Jan-06 07:24:33','2018-Jan-06 07:25:04',NULL,'enroute'),(11000021,1006,11015,'68307','68199',0.00,0.00,0.00,'2018-Jan-06 07:27:07','2018-Jan-06 07:27:29',NULL,'enroute'),(11000022,1005,11014,'68307','68159',0.00,0.00,0.00,'2018-Jan-06 07:27:47','2018-Jan-06 07:28:07',NULL,'enroute'),(11000023,1021,11011,'68163','68307',0.00,0.00,0.00,'2018-Jan-06 07:28:21','2018-Jan-06 07:29:04',NULL,'enroute'),(11000024,1009,11012,'68305','68307',120.20,0.00,26.44,'2018-Jan-06 07:29:38','2018-Jan-06 07:29:50','2018-Jan-06 07:30:31','ended'),(11000025,1007,11008,'68161','68165',55.20,0.00,6.37,'2018-Jan-06 08:06:47','2018-Jan-06 08:07:44','2018-Jan-06 08:07:48','ended'),(11000026,1022,11007,'68199','68159',0.00,0.00,0.00,'2018-Jan-06 08:17:40','2018-Jan-06 08:18:06',NULL,'enroute'),(11000027,1007,11023,'68199','68169',100.10,0.00,8.47,'2018-Jan-06 12:24:00','2018-Jan-06 12:29:00','2018-Jan-06 12:29:35','ended'),(11000028,1007,11023,'68305','68163',100.00,0.00,7.69,'2018-Jan-06 12:30:32','2018-Jan-06 12:30:56','2018-Jan-06 12:31:44','ended'),(11000029,1007,11023,'68163','68165',200.00,0.00,30.77,'2018-Jan-06 12:32:29','2018-Jan-06 12:33:12','2018-Jan-06 12:33:16','ended'),(11000030,1020,11009,'68165','68163',35.65,11.88,12.95,'2018-Jan-06 13:30:42','2018-Jan-06 13:31:45','2018-Jan-06 13:32:35','ended'),(11000031,1023,11009,'68169','68305',24.00,4.80,9.60,'2018-Jan-06 13:34:42','2018-Jan-06 13:35:13','2018-Jan-06 13:35:24','ended'),(11000032,1023,11009,'68307','68169',12.35,2.47,6.34,'2018-Jan-06 13:40:18','2018-Jan-06 13:40:40','2018-Jan-06 13:40:47','ended'),(11000033,1023,11009,'68305','68163',35.23,7.05,555.98,'2018-Jan-06 13:44:32','2018-Jan-06 13:44:55','2018-Jan-06 13:45:01','ended'),(11000034,1023,11009,'68161','68259',128.88,25.78,2011.04,'2018-Jan-06 13:47:49','2018-Jan-06 13:52:12','2018-Jan-06 13:52:17','ended'),(11000035,1024,11009,'68229','68307',123.88,30.97,2416.28,'2018-Jan-06 13:53:16','2018-Jan-06 13:54:55','2018-Jan-06 13:55:51','ended'),(11000036,1004,11004,'68163','68169',290.00,30.53,88.54,'2018-Jan-06 14:06:22','2018-Jan-06 14:08:55','2018-Jan-06 14:09:46','ended'),(11000037,1024,11009,'68169','68163',0.00,0.00,0.00,'2018-Jan-07 02:11:35',NULL,NULL,'booked');
/*!40000 ALTER TABLE `trip_management` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle` (
  `veh_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `model` varchar(20) NOT NULL,
  `brand` varchar(20) NOT NULL,
  `reg_no` varchar(20) NOT NULL,
  `year` int(4) unsigned NOT NULL,
  `pax` int(10) unsigned NOT NULL,
  `category` varchar(45) NOT NULL,
  `mileage` decimal(4,2) unsigned NOT NULL,
  `service` varchar(15) DEFAULT 'active',
  PRIMARY KEY (`veh_id`),
  UNIQUE KEY `reg_no_UNIQUE` (`reg_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1026 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1001,'Auto Avio 815','Ferrari','DE 70 BR 782',2010,2,'luxury',12.50,'active'),(1002,'Swift','Maruti','HR 25 DE 2345',2009,5,'shared',17.00,'active'),(1003,'Polo','Volkswagen','DE 70 BR 722',2010,4,'comfort',11.50,'active'),(1004,'F125 S','Ferrari','DE 25 DE 2325',2009,5,'luxury',9.50,'active'),(1005,'Jetta','Volkswagen','DE 71 BR 782',2010,4,'comfort',14.50,'active'),(1006,'Swift Dzire','Maruti','HR 25 DE 2305',2009,5,'shared',15.00,'active'),(1007,'GTI','Volkswagen','DE 40 BR 772',2011,4,'shared',13.00,'active'),(1008,'Touaereg','Volkswagen','DE 25 DF 2445',2009,5,'shared',14.00,'active'),(1009,'Beetle','Volkswagen','DE 72 BR 782',2010,4,'comfort',10.00,'active'),(1010,'3200','BMW','HR 11 DE 2555',2009,5,'comfort',8.00,'active'),(1011,'New Class','BMW','DE 70 HD 732',2010,4,'luxury',12.50,'active'),(1012,'I8','BMW','DE 17 HD 2345',2009,5,'luxury',8.00,'active'),(1013,'Baleno','Maruti','DE 76 CN 782',2010,4,'shared',15.00,'active'),(1014,'3 Series','BMW','HR 25 DE 2115',2009,5,'luxury',9.00,'active'),(1015,'Ignis','Suzuki','DE 10 BR 782',2010,4,'shared',15.00,'active'),(1016,'Celerio','Maruti','DE 77 KR 2345',2009,5,'shared',15.00,'active'),(1017,'Vitara','Suzuki','DE 55 BR 782',2010,4,'shared',15.00,'active'),(1018,'SX4','Maruti','DE 44 HD 2385',2009,5,'shared',15.00,'active'),(1019,'Jimmy','Maruti','DE 74 BR 1782',2010,4,'shared',15.00,'active'),(1020,'Avalance','Chevrolet','DE 88 DE 1145',2009,5,'truck',3.00,'repair'),(1021,'LP 909','Tata','DE 11 HD 1665',2009,35,'bus',5.00,'active'),(1022,'LP 407','Tata','DE 12 HD 1665',2009,35,'bus',4.00,'active'),(1023,'LP 408','Tata','DE 13 HD 1665',2009,35,'bus',5.00,'active'),(1024,'Avalance','Nercedes','HR 88 DE 1335',2010,5,'truck',4.00,'active'),(1025,'Avalance','Volvo','MN 88 FR 1121',2002,5,'truck',5.00,'active');
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_records`
--

DROP TABLE IF EXISTS `vehicle_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle_records` (
  `veh_id` int(10) unsigned DEFAULT NULL,
  `miles` decimal(8,2) unsigned DEFAULT '0.00',
  `maintenance_cost` decimal(8,2) unsigned DEFAULT '0.00',
  `fuel_cost` decimal(8,2) unsigned DEFAULT '0.00',
  `status` varchar(15) DEFAULT 'available',
  `curr_pin` varchar(7) DEFAULT '00000',
  UNIQUE KEY `veh_id_UNIQUE` (`veh_id`),
  KEY `vehicle_record_veh_id_fk_idx` (`veh_id`),
  CONSTRAINT `vehicle_record_veh_id_fk` FOREIGN KEY (`veh_id`) REFERENCES `vehicle` (`veh_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_records`
--

LOCK TABLES `vehicle_records` WRITE;
/*!40000 ALTER TABLE `vehicle_records` DISABLE KEYS */;
INSERT INTO `vehicle_records` VALUES (1001,15.00,0.00,1.20,'unavailable','68169'),(1002,0.00,0.00,0.00,'unavailable','00000'),(1003,0.00,0.00,0.00,'unavailable','00000'),(1004,900.10,0.00,202.24,'available','68169'),(1005,45.90,0.00,4.12,'unavailable','68169'),(1006,0.00,0.00,0.00,'unavailable','00000'),(1007,455.30,0.00,53.30,'available','68165'),(1008,0.00,0.00,0.00,'available','00000'),(1009,120.20,0.00,26.44,'available','68307'),(1010,0.00,0.00,0.00,'available','00000'),(1011,0.00,0.00,0.00,'available','00000'),(1012,0.00,0.00,0.00,'available','00000'),(1013,0.00,0.00,0.00,'available','00000'),(1014,0.00,0.00,0.00,'available','00000'),(1015,0.00,0.00,0.00,'available','00000'),(1016,0.00,0.00,0.00,'available','00000'),(1017,0.00,0.00,0.00,'available','00000'),(1018,0.00,0.00,0.00,'available','00000'),(1019,0.00,0.00,0.00,'available','00000'),(1020,360.52,0.00,3848.94,'available','68163'),(1021,150.00,0.00,75.00,'unavailable','68169'),(1022,0.00,0.00,0.00,'unavailable','00000'),(1023,200.46,0.00,2582.96,'available','68259'),(1024,123.88,0.00,2416.28,'unavailable','68307'),(1025,0.00,0.00,0.00,'available','00000');
/*!40000 ALTER TABLE `vehicle_records` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-14 15:45:40
