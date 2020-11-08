/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.21 : Database - assessment
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`assessment` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `assessment`;

/*Table structure for table `genre` */

DROP TABLE IF EXISTS `genre`;

CREATE TABLE `genre` (
  `gen_id` int NOT NULL AUTO_INCREMENT,
  `gen_name` varchar(100) NOT NULL,
  PRIMARY KEY (`gen_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `genre` */

insert  into `genre`(`gen_id`,`gen_name`) values 
(1,'Action & adventure'),
(2,'Classic'),
(3,'Comedies'),
(4,'Dramas'),
(5,'Horror'),
(6,'Romantic'),
(7,'Sci - Fi & Fantasy'),
(8,'Sports'),
(9,'Thrillers'),
(10,'Documentaries');

/*Table structure for table `movie` */

DROP TABLE IF EXISTS `movie`;

CREATE TABLE `movie` (
  `mov_id` int NOT NULL AUTO_INCREMENT,
  `mov_title` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mov_rating` decimal(2,1) NOT NULL DEFAULT '0.5',
  `create_dt` timestamp NOT NULL,
  `update_dt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`mov_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `movie` */

/*Table structure for table `movie_genre` */

DROP TABLE IF EXISTS `movie_genre`;

CREATE TABLE `movie_genre` (
  `mov_gen_id` int NOT NULL AUTO_INCREMENT,
  `mov_id` int NOT NULL,
  `gen_id` int NOT NULL,
  PRIMARY KEY (`mov_gen_id`),
  UNIQUE KEY `unique_index` (`mov_id`,`gen_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `movie_genre` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
