-- MySQL dump 10.13  Distrib 5.1.53, for Win32 (ia32)
--
-- Host: localhost    Database: oes_innodb
-- ------------------------------------------------------
-- Server version	5.1.53-community

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
-- Current Database: `oes_innodb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `oes_innodb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `oes_innodb`;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `exam_id` int(11) DEFAULT NULL,
  `answer` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `exam_name` varchar(45) DEFAULT NULL,
  `exam_duration` int(11) DEFAULT NULL,
  `average_score` float DEFAULT NULL,
  `examinee_count` int(11) DEFAULT NULL,
  `pass_criteria` varchar(45) DEFAULT NULL,
  `pass_rate` float DEFAULT NULL,
  `question_quantity` int(11) DEFAULT NULL,
  `effective_time` datetime DEFAULT NULL,
  `exam_status_id` int(11) DEFAULT '0',
  `exam_description` varchar(400) DEFAULT NULL,
  `question_point` int(10) DEFAULT NULL,
  `total_score` int(10) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `is_draft` int(11) DEFAULT '0',
  `is_deleted` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` VALUES (12,'MyExam',60,NULL,NULL,'20',NULL,10,'2016-08-22 01:02:00',0,'This is a exam>>???',4,40,1,0,0),(14,'Math Exam',60,NULL,NULL,'2',NULL,4,'2016-12-28 08:30:00',0,'Math exam is a long and difficult exam.....',3,12,1,0,0),(15,'test05',60,NULL,NULL,'11',NULL,12,'2016-08-16 00:12:00',0,'',12,0,1,0,0),(16,'Test073',60,NULL,NULL,'1',NULL,100,'2017-03-22 00:00:00',0,'this is a exam',1,0,1,0,0),(17,'Test074',60,NULL,NULL,'1',NULL,100,'2016-08-31 10:00:00',0,'this a math exam',1,0,1,0,0),(18,'Test075',120,NULL,NULL,'1',NULL,100,'2016-08-30 10:20:00',0,'BugTest',1,0,1,0,0),(28,'Draft TEST',120,NULL,NULL,'150',NULL,50,'2016-08-23 12:22:00',0,'this is a draft',4,0,1,1,0),(29,'TEST078',120,NULL,NULL,'70',NULL,22,'2016-08-23 10:20:00',0,'this is the 2st draft test',4,0,1,0,0),(32,'Create　Exam　Test',120,NULL,NULL,'60',NULL,22,'2016-08-24 01:02:00',0,'test',4,88,1,0,0),(33,'create draft',120,NULL,NULL,'60',NULL,25,'2016-08-24 11:22:00',0,'1',4,100,1,1,0),(34,'Exam4869',120,NULL,NULL,'240',NULL,20,'2016-08-24 01:02:00',0,'APT',25,500,1,0,0),(36,'1',120,NULL,NULL,'22',NULL,44,'2016-08-24 01:01:00',0,'',1,44,1,1,0),(37,'1',120,NULL,NULL,'49',NULL,25,'2016-08-25 12:22:00',0,'',2,50,1,1,0);
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_stutus`
--

DROP TABLE IF EXISTS `exam_stutus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam_stutus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_stutus`
--

LOCK TABLES `exam_stutus` WRITE;
/*!40000 ALTER TABLE `exam_stutus` DISABLE KEYS */;
/*!40000 ALTER TABLE `exam_stutus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_name` varchar(300) DEFAULT NULL,
  `user_id` varchar(10) NOT NULL,
  `answer_a` varchar(80) DEFAULT NULL,
  `answer_b` varchar(80) DEFAULT NULL,
  `answer_c` varchar(80) DEFAULT NULL,
  `answer_d` varchar(80) DEFAULT NULL,
  `question_answer` varchar(80) DEFAULT NULL,
  `is_deleted` int(2) DEFAULT '0',
  `question_id` varchar(40) NOT NULL,
  `created_time` datetime NOT NULL,
  `edited_time` datetime NOT NULL,
  `is_used` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=337 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (145,'hjaslkdjalksjd','1','456','123','789','741','D',0,'Q0018','2016-08-09 22:18:25','2016-08-12 16:18:58',1),(166,'which is the correct answer in follow options?','1','4','5','6','7','D',0,'Q0015','2016-08-10 13:05:16','2016-08-13 14:56:14',1),(169,'which is the correct answer in follow options?','1','4','5','6','7','D',0,'Q0016','2016-08-10 13:08:53','2016-08-13 14:56:14',1),(170,'which is the correct answer in follow options?','1','4','5','6','7','D',0,'Q0009','2016-08-10 13:10:15','2016-08-10 13:11:27',1),(171,'which is the correct answer in follow options?','1','4','5','6','7','D',1,'Q0008','2016-08-10 13:11:27','2016-08-18 22:42:03',1),(172,'which is the correct answer in follow options?','1','a','s','d','f','C',0,'Q0019','2016-08-10 13:11:54','2016-08-13 14:56:06',1),(173,'which is the correct answer in follow options?','1','o','py','h','d','C',0,'Q0010','2016-08-10 13:12:10','2016-08-18 19:57:42',1),(174,'which is the correct answer in follow options?','1','1','2','3','4','C',1,'Q0011','2016-08-10 13:12:57','2016-08-26 09:09:21',1),(175,'which is the correct answer in follow options?','2','g','h','j','k','C',0,'Q0014','2016-08-10 14:19:59','2016-08-10 14:21:42',1),(176,'which is the correct answer in follow options?','2','g','h','j','k','B',1,'Q0012','2016-08-10 14:21:42','2016-08-25 09:12:08',1),(198,'hahahah','1','8','9','0','2','A',0,'Q0013','2016-08-12 13:41:03','2016-08-18 22:38:28',1),(199,'which is the correct answer in follow options?科迪?','1','ab','b','c','d','C',0,'Q0017','2016-08-12 13:42:19','2016-08-12 13:42:32',1),(204,'which is the correct answer in follow options?&#37;','1','1','2','3','4','C',0,'Q0003','2016-08-12 15:13:13','2016-08-12 15:13:13',1),(210,'which is the correct answer in follow options?\r\n&lt;script&gt;&lt;/script&gt;','1','1','2','3','4','C',0,'Q0004','2016-08-12 15:40:46','2016-08-12 15:40:46',1),(218,'which is the correct answer in follow options?&#92;','1','ab','c','b','d','D',0,'Q0002','2016-08-12 19:02:07','2016-08-12 19:02:07',1),(219,'&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;','1','123','123123','123123','123123','B',1,'Q0020','2016-08-12 19:14:04','2016-08-18 22:42:40',1),(220,'which is the correct answer in follow options?&amp','1','1','2','3','4','D',0,'Q0005','2016-08-12 19:14:29','2016-08-12 19:14:29',1),(221,'&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#92;','1','123','123123','123123','123123','B',1,'Q0020','2016-08-12 19:23:02','2016-08-25 09:06:13',1),(225,'which is my way','1','1','2','3','4','B',0,'Q0021','2016-08-13 10:10:40','2016-08-24 10:30:11',1),(233,'hahahhahah','1','1','2','3','4','B',1,'Q0024','2016-08-13 14:56:36','2016-08-18 22:42:18',1),(241,'which is the correct answer in follow options?&#37;','1','1','2','4','5','A',0,'Q0006','2016-08-17 11:10:35','2016-08-17 11:10:35',1),(242,'&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;5&#37;&#37;','1','1','2','3','4','B',0,'Q0022','2016-08-17 11:16:15','2016-08-24 10:30:04',1),(310,'111','1','22','33','12','232','B',1,'Q0023','2016-08-18 19:56:26','2016-08-18 22:42:18',1),(311,'which is the correct answer in follow options?科迪?/12345678','1','ab','b&amp#182;&amp#182;&amp#182;','c','d','C',1,'Q0001','2016-08-18 19:57:06','2016-08-24 23:37:52',1),(312,'&#92;which is the correct answer in follow options?&#37;','1','7&#92;','8/','9&#37;','0','D',1,'Q0007','2016-08-18 20:22:22','2016-08-26 09:09:13',1),(313,'which is the correct answer in follow options?---','1','4','5','6','7','D',1,'Q0008','2016-08-18 22:42:03','2016-08-18 22:42:10',1),(330,'&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#37;&#92;','1','123','1231232','123123','123123','B',0,'Q0020','2016-08-25 09:04:53','2016-08-25 09:04:53',0),(332,'which is the correct answer in follow options?&#37;','1','g','h','j','k','B',0,'Q0012','2016-08-25 09:12:08','2016-08-25 09:12:08',0),(334,'which is the correct answer in follow options?科迪?/12345678&#37;&#37;+','1','ab','b&amp;#182;&amp;#182;&amp;#182;','c','d','B',0,'Q0001','2016-08-25 09:18:24','2016-08-25 09:18:24',0),(335,'heheda','1','1','2','3','4','A',1,'Q0008','2016-08-25 12:04:52','2016-08-26 09:09:13',0),(336,'123','1','1','2','3','4','C',0,'Q0023','2016-08-26 09:05:59','2016-08-26 09:05:59',0);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_exam`
--

DROP TABLE IF EXISTS `question_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_exam` (
  `question_id` int(11) NOT NULL,
  `exam_id` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_exam`
--

LOCK TABLES `question_exam` WRITE;
/*!40000 ALTER TABLE `question_exam` DISABLE KEYS */;
INSERT INTO `question_exam` VALUES (311,'12'),(313,'12'),(218,'12'),(176,'12'),(220,'12'),(175,'12'),(198,'12'),(173,'12'),(241,'12'),(169,'12'),(175,'14'),(225,'14'),(242,'14'),(172,'14'),(310,'14'),(312,'14'),(199,'14'),(220,'14'),(210,'14'),(311,'14'),(169,'15'),(145,'15'),(210,'15'),(171,'15'),(175,'15'),(311,'15'),(219,'15'),(220,'15'),(166,'15'),(198,'15'),(169,'16'),(176,'16'),(314,'16'),(242,'16'),(145,'16'),(172,'16'),(312,'16'),(199,'16'),(210,'16'),(174,'16'),(198,'17'),(221,'17'),(233,'17'),(204,'17'),(220,'17'),(169,'17'),(174,'17'),(310,'17'),(210,'17'),(171,'17'),(220,'18'),(312,'18'),(314,'18'),(310,'18'),(219,'18'),(170,'18'),(311,'18'),(169,'18'),(172,'18'),(233,'18'),(170,'29'),(175,'29'),(311,'29'),(312,'29'),(225,'29'),(220,'29'),(176,'29'),(242,'29'),(169,'29'),(198,'29'),(241,'29'),(145,'29'),(174,'29'),(166,'29'),(204,'29'),(173,'29'),(314,'29'),(210,'29'),(221,'29'),(199,'29'),(218,'29'),(172,'29'),(170,'32'),(172,'32'),(221,'32'),(174,'32'),(312,'32'),(220,'32'),(210,'32'),(175,'32'),(176,'32'),(218,'32'),(173,'32'),(166,'32'),(241,'32'),(145,'32'),(314,'32'),(204,'32'),(242,'32'),(198,'32'),(225,'32'),(320,'32'),(199,'32'),(311,'32'),(175,'34'),(204,'34'),(172,'34'),(241,'34'),(312,'34'),(314,'34'),(145,'34'),(218,'34'),(169,'34'),(221,'34'),(173,'34'),(170,'34'),(174,'34'),(320,'34'),(311,'34'),(210,'34'),(225,'34'),(220,'34'),(199,'34'),(198,'34');
/*!40000 ALTER TABLE `question_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `email_address` varchar(45) DEFAULT NULL,
  `telephobe_number` varchar(45) DEFAULT NULL,
  `gender` char(10) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'MalcolmLi','1234','1120880924@qq.com','13253657835','male',1),(2,'lisi','1234','1120@qq.com','13255654544','male',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_exam`
--

DROP TABLE IF EXISTS `user_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_exam` (
  `user_id` int(11) NOT NULL,
  `exam_id` int(11) NOT NULL,
  `exam_score` float DEFAULT NULL,
  `effective_time` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`exam_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_exam`
--

LOCK TABLES `user_exam` WRITE;
/*!40000 ALTER TABLE `user_exam` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-26 15:14:54
