-- MariaDB dump 10.19  Distrib 10.6.16-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: vgtnlkcu_brngyapp
-- ------------------------------------------------------
-- Server version	10.6.16-MariaDB-cll-lve

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `brgy_application`
--

DROP TABLE IF EXISTS `brgy_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brgy_application` (
  `idbrgy_application` int(11) NOT NULL AUTO_INCREMENT,
  `application_docs` varchar(100) NOT NULL,
  `application_reason` varchar(100) NOT NULL,
  PRIMARY KEY (`idbrgy_application`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brgy_application`
--

LOCK TABLES `brgy_application` WRITE;
/*!40000 ALTER TABLE `brgy_application` DISABLE KEYS */;
INSERT INTO `brgy_application` VALUES (1,'','');
/*!40000 ALTER TABLE `brgy_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brgy_appointment`
--

DROP TABLE IF EXISTS `brgy_appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brgy_appointment` (
  `idbrgy_appointment` int(11) NOT NULL AUTO_INCREMENT,
  `appointment_reason` varchar(100) NOT NULL,
  `appointment_date` date NOT NULL,
  `official_id` int(11) DEFAULT NULL,
  `status` enum('pending','accepted','rejected') NOT NULL DEFAULT 'pending',
  `user_ID` int(11) NOT NULL,
  `appointment_timestamp` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`idbrgy_appointment`),
  KEY `appointment_bgryofficials` (`official_id`),
  KEY `user_ID` (`user_ID`),
  KEY `idx_appointment_date` (`appointment_date`),
  KEY `idx_status` (`status`),
  CONSTRAINT `brgy_appointment_ibfk_1` FOREIGN KEY (`official_id`) REFERENCES `brgy_official_list` (`official_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `brgy_appointment_ibfk_2` FOREIGN KEY (`user_ID`) REFERENCES `user_login` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brgy_appointment`
--

LOCK TABLES `brgy_appointment` WRITE;
/*!40000 ALTER TABLE `brgy_appointment` DISABLE KEYS */;
INSERT INTO `brgy_appointment` VALUES (14,'kdjsijsn','2024-12-24',2,'accepted',2,'2024-12-07 08:51:51'),(15,'djdjd','2024-12-07',10,'rejected',2,'2024-12-07 08:52:28'),(16,'ggg','2024-12-19',10,'pending',2,'2024-12-07 22:18:24'),(17,'tygk','2024-12-13',1,'pending',2,'2024-12-08 07:17:04'),(18,'😘😘','2024-12-11',10,'pending',29,'2024-12-09 16:43:29');
/*!40000 ALTER TABLE `brgy_appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brgy_event`
--

DROP TABLE IF EXISTS `brgy_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brgy_event` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_title` varchar(255) NOT NULL,
  `event_description` text NOT NULL,
  `event_date` date NOT NULL,
  `event_time` time NOT NULL,
  `event_location` varchar(255) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`event_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brgy_event`
--

LOCK TABLES `brgy_event` WRITE;
/*!40000 ALTER TABLE `brgy_event` DISABLE KEYS */;
INSERT INTO `brgy_event` VALUES (1,'Community Clean-up','Join us for a community-wide clean-up event.','2024-12-10','08:00:00','Town Plaza','2024-11-26 02:54:10'),(2,'Health Fair','Free health check-ups and consultations.','2024-12-15','10:00:00','Community Center','2024-11-26 02:54:10'),(3,'Job Fair','Explore job opportunities and career guidance.','2024-12-20','09:00:00','City Hall','2024-11-26 02:54:10'),(4,'Kim','AAAA','2024-11-29','23:41:00','dddd','2024-11-26 03:38:23'),(5,'kjnhkjhniknu','lknkjlk','2024-11-30','23:41:00','klmlpo','2024-11-26 03:39:31'),(7,'Kim','ddas','2024-12-25','00:23:00','3242','2024-12-01 04:19:34');
/*!40000 ALTER TABLE `brgy_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brgy_info`
--

DROP TABLE IF EXISTS `brgy_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brgy_info` (
  `info_id` int(11) NOT NULL AUTO_INCREMENT,
  `office_address` varchar(255) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `email_address` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`info_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brgy_info`
--

LOCK TABLES `brgy_info` WRITE;
/*!40000 ALTER TABLE `brgy_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `brgy_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brgy_news`
--

DROP TABLE IF EXISTS `brgy_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brgy_news` (
  `news_id` int(11) NOT NULL AUTO_INCREMENT,
  `news_title` varchar(255) NOT NULL,
  `news_content` text NOT NULL,
  `news_date` date NOT NULL,
  `news_category` varchar(100) DEFAULT NULL,
  `author_name` varchar(150) DEFAULT NULL,
  `image_url` varchar(2083) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`news_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brgy_news`
--

LOCK TABLES `brgy_news` WRITE;
/*!40000 ALTER TABLE `brgy_news` DISABLE KEYS */;
/*!40000 ALTER TABLE `brgy_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brgy_official_list`
--

DROP TABLE IF EXISTS `brgy_official_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brgy_official_list` (
  `official_id` int(11) NOT NULL AUTO_INCREMENT,
  `official_name` varchar(255) NOT NULL,
  `official_position` varchar(100) NOT NULL,
  `contact_info` varchar(150) NOT NULL,
  PRIMARY KEY (`official_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brgy_official_list`
--

LOCK TABLES `brgy_official_list` WRITE;
/*!40000 ALTER TABLE `brgy_official_list` DISABLE KEYS */;
INSERT INTO `brgy_official_list` VALUES (1,'Rosauro','Punong barangay','contact'),(2,'Marlon','Sangguniang Barangay Member','contact'),(3,'Wilson','Sangguniang Barangay Member','contact'),(4,'Edward','Sangguniang Barangay Member','contact'),(5,'Edwin','Sangguniang Barangay Member','contact'),(6,'Cezar','Sangguniang Barangay Member','contact'),(7,'Teodoro','Sangguniang Barangay Member','contact'),(8,'Vidal','Sangguniang Barangay Member','contact'),(9,'Gylliane','SK Chairperson','contact'),(10,'Bernadeth','Barangay Secretary','contact');
/*!40000 ALTER TABLE `brgy_official_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `create_acc`
--

DROP TABLE IF EXISTS `create_acc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `create_acc` (
  `idcreate_acc` int(11) NOT NULL AUTO_INCREMENT,
  `ca_username` varchar(45) NOT NULL,
  `ca_password` varchar(45) NOT NULL,
  `ca_lname` varchar(45) NOT NULL,
  `ca_fname` varchar(45) NOT NULL,
  `ca_mname` varchar(45) NOT NULL,
  `ca_phonenumber` varchar(11) NOT NULL,
  PRIMARY KEY (`idcreate_acc`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `create_acc`
--

LOCK TABLES `create_acc` WRITE;
/*!40000 ALTER TABLE `create_acc` DISABLE KEYS */;
/*!40000 ALTER TABLE `create_acc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documents`
--

DROP TABLE IF EXISTS `documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documents` (
  `document_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `description` text DEFAULT NULL,
  PRIMARY KEY (`document_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documents`
--

LOCK TABLES `documents` WRITE;
/*!40000 ALTER TABLE `documents` DISABLE KEYS */;
INSERT INTO `documents` VALUES (1,'Barangay ID','A valid ID issued by the barangay for residents, commonly used for various purposes.'),(2,'Barangay Clearance','A document required for local government compliance, verifying no pending cases.'),(3,'Barangay Certificate','A general certificate issued by the barangay, often used for personal verification.'),(4,'Barangay Certificate of Residency','Proof of residency in the barangay, often required for permits and other documents.'),(5,'Barangay Indigency Certificate','A certificate issued to confirm a person\'s indigency, typically for social services.'),(6,'Barangay Protection Order','A legal document issued to protect individuals from threats or violence within the barangay.'),(7,'Barangay Business Clearance','A certificate required for business owners to operate within the barangay, ensuring compliance with local regulations.'),(8,'Barangay Blotter Report','A record of reported incidents or crimes within the barangay, used for legal purposes.'),(9,'Barangay Certificate to File Action','A document allowing an individual to file a legal action, certifying that the issue was recorded in the barangay.'),(10,'Barangay Permit for Events','A permit issued by the barangay to authorize events, gatherings, or activities within the community.'),(11,'Barangay Voters Registration Certificate','A document confirming an individual\'s registration as a voter within the barangay.'),(12,'Barangay Community Tax Certificate','A tax certificate issued by the barangay for individuals or businesses as part of the local taxation process.');
/*!40000 ALTER TABLE `documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `find_maps`
--

DROP TABLE IF EXISTS `find_maps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `find_maps` (
  `idfind_user` int(11) NOT NULL AUTO_INCREMENT,
  `find_recent` varchar(45) NOT NULL,
  `find_history` varchar(45) NOT NULL,
  PRIMARY KEY (`idfind_user`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `find_maps`
--

LOCK TABLES `find_maps` WRITE;
/*!40000 ALTER TABLE `find_maps` DISABLE KEYS */;
/*!40000 ALTER TABLE `find_maps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hhhhhh`
--

DROP TABLE IF EXISTS `hhhhhh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hhhhhh` (
  `rtt` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hhhhhh`
--

LOCK TABLES `hhhhhh` WRITE;
/*!40000 ALTER TABLE `hhhhhh` DISABLE KEYS */;
/*!40000 ALTER TABLE `hhhhhh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requests`
--

DROP TABLE IF EXISTS `requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requests` (
  `document_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `reason` varchar(250) DEFAULT NULL,
  `status` enum('Pending','Approved','Rejected') DEFAULT 'Pending',
  `request_date` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`request_id`),
  KEY `requests_ibfk_1` (`user_id`),
  KEY `requests_ibfk_2` (`document_id`),
  CONSTRAINT `requests_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_login` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `requests_ibfk_2` FOREIGN KEY (`document_id`) REFERENCES `documents` (`document_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requests`
--

LOCK TABLES `requests` WRITE;
/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
INSERT INTO `requests` VALUES (2,2,6,'cfrrtrrr','Approved','2024-12-04 20:11:23'),(3,2,7,'GUMANA NA','Pending','2024-12-04 20:19:41'),(1,2,8,'isjjdks','Pending','2024-12-08 03:25:54'),(1,29,9,'business purpose','Pending','2024-12-09 04:18:49'),(2,29,10,'business purpose','Pending','2024-12-09 04:18:49'),(11,29,11,'hahaha','Pending','2024-12-10 04:29:22');
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_applications`
--

DROP TABLE IF EXISTS `user_applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_applications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `selected_documents` text DEFAULT NULL,
  `reason` text DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_applications`
--

LOCK TABLES `user_applications` WRITE;
/*!40000 ALTER TABLE `user_applications` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_applications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_login`
--

DROP TABLE IF EXISTS `user_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_login` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `userPassword` varchar(255) DEFAULT NULL,
  `middleName` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `role` varchar(20) DEFAULT 'user',
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_login`
--

LOCK TABLES `user_login` WRITE;
/*!40000 ALTER TABLE `user_login` DISABLE KEYS */;
INSERT INTO `user_login` VALUES (1,'admin','admin',NULL,NULL,NULL,NULL,NULL,'admin'),(2,'kim','12345','Andrei','Kim','Besmar','09672564545','Lipa City','user'),(29,'dave03','jasper03','C','Jasper Dave','Dulla','09482106236',NULL,'user');
/*!40000 ALTER TABLE `user_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_profile` (
  `iduser_profile` int(11) NOT NULL,
  `user_fullname` varchar(45) NOT NULL,
  `user_phonenumber` varchar(11) NOT NULL,
  `user_location` varchar(45) NOT NULL,
  PRIMARY KEY (`iduser_profile`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'vgtnlkcu_brngyapp'
--

--
-- Dumping routines for database 'vgtnlkcu_brngyapp'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-09 23:43:18
