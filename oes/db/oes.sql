-- MySQL dump 10.13  Distrib 5.1.53, for Win32 (ia32)
--
-- Host: localhost    Database: oes
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
-- Current Database: `oes`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `oes` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `oes`;

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
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
  `duration` int(11) DEFAULT NULL,
  `average_score` float DEFAULT NULL,
  `examinee_count` int(11) DEFAULT NULL,
  `pass_criteria` varchar(45) DEFAULT NULL,
  `pass_rate` float DEFAULT NULL,
  `total_question_quantity` int(11) DEFAULT NULL,
  `effective_time` datetime DEFAULT NULL,
  `exam_status_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
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
  `user_id` varchar(10) DEFAULT NULL,
  `answer_a` varchar(80) DEFAULT NULL,
  `answer_b` varchar(80) DEFAULT NULL,
  `answer_c` varchar(80) DEFAULT NULL,
  `answer_d` varchar(80) DEFAULT NULL,
  `question_answer` varchar(80) DEFAULT NULL,
  `is_deleted` int(2) DEFAULT NULL,
  `question_id` varchar(40) NOT NULL,
  `created_time` datetime NOT NULL,
  `edited_time` datetime NOT NULL,
  `is_used` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=97 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (67,'啊啊阿斯大声','1','123','123','123','12','C',1,'Q0012','2016-02-02 09:49:44','2016-08-04 14:31:26',0),(65,'what it is is is is is is is is is is is is is is is is is is is is is is is is... ','1','1','2','3','4','C',0,'Q0013','2016-03-02 09:49:44','2016-03-02 09:49:44',0),(64,'what it is is is is is is is is is is is is is is is is is is is is is is is is...','1','1','2','3','4','C',0,'Q0014','2016-04-02 09:49:44','2016-08-02 18:52:10',0),(62,'what it is is is is is is is is is is is is is is is is is is is is is is is is...','1','1','2','3','4','A',0,'Q0015','2016-05-02 09:49:44','2016-08-02 18:52:06',0),(59,'1','1','2','asd','asd','zsdfas','A',0,'Q0016','2016-06-02 09:49:44','2016-08-03 22:25:30',0),(61,'what it is is is is is is is is is is is is is is is is is is is is is is is is...','1','123','123','123','123','A',0,'Q0018','2016-07-02 19:49:44','2016-08-03 14:09:02',0),(83,'1234','1','2','2','1','1','C',0,'Q0019','2016-08-03 14:10:28','2016-08-03 22:22:35',0),(58,'what it is is is is is is is is is is is is is is is is is is is is is is is is...','1','123','21','2','4','A',0,'Q0020','2016-07-22 09:49:44','2016-08-02 17:37:13',0),(78,'what it is is is is is is is is is is is is is is is is is is is is is is is is...','1','1','2','3','4','B',0,'Q0021','2016-07-20 09:49:44','2016-08-03 13:55:21',0),(75,'啊啊阿斯大声123','1','123','123','123','12','C',0,'Q0022','2016-07-18 09:49:44','2016-08-03 13:57:14',0),(74,'阿斯顿','1','113','123asdasd','123','123','C',0,'Q0023','2016-07-10 09:49:44','2016-08-02 17:36:39',0),(76,'123','1','2','1','123','12','A',0,'Q0024','2016-08-02 10:49:44','2016-08-02 10:49:44',0),(71,'do you knowdo you knowdo y','1','A','B','v','c','A',0,'Q0025','2016-08-28 09:49:44','2016-08-28 09:49:44',0),(72,'what it is is is is is is is is is is is is is is is is is is is is is is is is...aaaa','1','1','2','3','4','A',0,'Q0026','2016-06-05 09:49:44','2016-08-02 18:52:10',0),(79,'a','1','as','asd','asd','a','B',0,'Q0027','2016-08-02 09:49:44','2016-08-03 23:07:18',0),(80,'do you knowdo you knowdo y','1','A','B','v','c','A',0,'Q0028','2016-08-02 10:03:27','2016-08-02 10:03:27',0),(81,'qwe阿达啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊大苏打实打实大苏打实打实打完恶臭形成v','1','11阿斯顿','12','123','12','A',0,'Q0029','2016-08-02 13:36:46','2016-08-02 13:36:46',0),(84,'1234','1','2','2','1','1','A',0,'Q0030','2016-08-03 22:22:35','2016-08-03 22:22:35',0),(85,'12','1','123','12','123','121','D',0,'Q0031','2016-08-03 22:23:49','2016-08-03 22:23:49',0),(86,'1','1','2','2','3','3','A',0,'Q0032','2016-08-03 22:25:22','2016-08-03 22:25:22',0),(87,'啊啊阿斯大声123','1','123','123','123','12','C',0,'Q0033','2016-08-03 23:07:12','2016-08-03 23:07:13',0),(96,'Do you got to chaina?','1','yes','no','dont know','he he da- -','D',0,'Q0034','2016-08-04 15:11:45','2016-08-04 15:11:45',0),(95,'Do you got to chaina?','1','yes','no','dont know','hehe- -','D',1,'Q0034','2016-08-04 15:09:37','2016-08-04 15:11:45',0),(94,'please input the answer?','1','123','321','123','123','A',0,'Q0011','2016-08-04 14:26:55','2016-08-04 14:31:26',0);
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
  `exam_id` varchar(45) NOT NULL,
  PRIMARY KEY (`question_id`,`exam_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_exam`
--

LOCK TABLES `question_exam` WRITE;
/*!40000 ALTER TABLE `question_exam` DISABLE KEYS */;
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
  `user_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
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

-- Dump completed on 2016-08-04 15:14:40
