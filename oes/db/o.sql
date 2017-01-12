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
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
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
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
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
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
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
) ENGINE=MyISAM AUTO_INCREMENT=125 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (71,'do you knowdo you knowdo y','1','A','B','v','c','A',0,'Q0025','2016-08-28 09:49:44','2016-08-05 09:50:51',0),(72,'what it is is is is is is is is is is is is is is is is is is is is is is is is...aaaa','1','1','2','3','4','A',0,'Q0026','2016-06-05 09:49:44','2016-08-05 09:50:55',0),(76,'123','1','2','1','123','12','A',0,'Q0024','2016-08-02 10:49:44','2016-08-05 09:50:51',0),(80,'do you knowdo you knowdo y','1','A','B','v','c','A',0,'Q0028','2016-08-02 10:03:27','2016-08-05 09:50:55',0),(87,'啊啊阿斯大声123','1','123','123','123','12','C',0,'Q0033','2016-08-03 23:07:12','2016-08-05 09:50:55',0),(96,'Do you got to chaina?','1','yes','no','dont know','he he da- -','D',0,'Q0034','2016-08-04 15:11:45','2016-08-05 14:20:55',0),(111,'what do you do ?','1','this is A','this is B','C','D','A',0,'Q0036','2016-08-05 12:29:06','2016-08-05 12:29:06',0),(112,'this is a problem','1','\'','1','2','3','A',0,'Q0037','2016-08-05 12:38:28','2016-08-05 12:38:28',0),(114,'what it is ?','1','123','32123','123','111','B',0,'Q0038','2016-08-05 14:20:32','2016-08-05 18:28:08',0),(115,'阿斯顿e','1','113','123asdasd','123','123','C',0,'Q0023','2016-08-05 14:20:43','2016-08-05 14:20:43',0),(117,'what it is is is is is is is is is is is is is is is is is is is is is is is is...?','1','1','2','3','4','C',1,'Q0014','2016-08-05 14:56:39','2016-08-05 19:21:24',0),(120,'a','1','as','asd','asdab','a','B',0,'Q0027','2016-08-05 14:59:39','2016-08-05 14:59:39',0),(122,'this is','1','1','2','3','8','B',0,'Q0084','2016-08-05 15:43:28','2016-08-05 15:43:28',0),(124,'what it is is is is is is is is is is is is is is is is is is is is is is is is...?','1','1','2','3','4','B',0,'Q0014','2016-08-05 19:21:24','2016-08-05 19:21:24',0);
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
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
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
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'MalcolmLi','1234','1120880924@qq.com','13253657835','male',1),(2,'lisi','lisi','1120@qq.com','13255654544','male',1);
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
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
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
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
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

-- Dump completed on 2016-08-26 15:14:30
