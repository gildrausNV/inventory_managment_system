/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 10.4.25-MariaDB : Database - logistika
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`logistika` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `logistika`;

/*Table structure for table `fizickolice` */

DROP TABLE IF EXISTS `fizickolice`;

CREATE TABLE `fizickolice` (
  `KlijentID` int(11) NOT NULL AUTO_INCREMENT,
  `JMBG` int(11) NOT NULL,
  `Ime` varchar(255) NOT NULL,
  `Prezime` varchar(255) NOT NULL,
  PRIMARY KEY (`KlijentID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `fizickolice` */

insert  into `fizickolice`(`KlijentID`,`JMBG`,`Ime`,`Prezime`) values 
(1,123,'Nikola','Vujcic');

/*Table structure for table `klijent` */

DROP TABLE IF EXISTS `klijent`;

CREATE TABLE `klijent` (
  `KlijentID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`KlijentID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `klijent` */

insert  into `klijent`(`KlijentID`) values 
(1);

/*Table structure for table `magacin` */

DROP TABLE IF EXISTS `magacin`;

CREATE TABLE `magacin` (
  `magacinID` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) NOT NULL,
  PRIMARY KEY (`magacinID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `magacin` */

insert  into `magacin`(`magacinID`,`naziv`) values 
(1,'A'),
(2,'B'),
(3,'C');

/*Table structure for table `menadzer_logistike` */

DROP TABLE IF EXISTS `menadzer_logistike`;

CREATE TABLE `menadzer_logistike` (
  `menadzerID` int(11) NOT NULL AUTO_INCREMENT,
  `korisnickoime` varchar(255) NOT NULL,
  `sifra` varchar(255) NOT NULL,
  PRIMARY KEY (`menadzerID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `menadzer_logistike` */

insert  into `menadzer_logistike`(`menadzerID`,`korisnickoime`,`sifra`) values 
(1,'admin','admin');

/*Table structure for table `otpremnica` */

DROP TABLE IF EXISTS `otpremnica`;

CREATE TABLE `otpremnica` (
  `otpremnicaID` int(11) NOT NULL AUTO_INCREMENT,
  `odrediste` varchar(255) NOT NULL,
  `klijentID` int(11) NOT NULL,
  PRIMARY KEY (`otpremnicaID`),
  KEY `klijentID` (`klijentID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `otpremnica` */

insert  into `otpremnica`(`otpremnicaID`,`odrediste`,`klijentID`) values 
(1,'Petrovac na Mlavi',1);

/*Table structure for table `pravnolice` */

DROP TABLE IF EXISTS `pravnolice`;

CREATE TABLE `pravnolice` (
  `klijentID` int(11) NOT NULL AUTO_INCREMENT,
  `PIB` int(11) NOT NULL,
  `naziv` varchar(255) NOT NULL,
  PRIMARY KEY (`klijentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `pravnolice` */

/*Table structure for table `roba` */

DROP TABLE IF EXISTS `roba`;

CREATE TABLE `roba` (
  `robaID` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) NOT NULL,
  `magacinID` int(11) NOT NULL,
  PRIMARY KEY (`robaID`),
  KEY `magacinID` (`magacinID`),
  CONSTRAINT `FK` FOREIGN KEY (`magacinID`) REFERENCES `magacin` (`magacinID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `roba` */

insert  into `roba`(`robaID`,`naziv`,`magacinID`) values 
(1,'Pesak',1),
(2,'Cement',2);

/*Table structure for table `stavkaotpremnice` */

DROP TABLE IF EXISTS `stavkaotpremnice`;

CREATE TABLE `stavkaotpremnice` (
  `RB` int(11) NOT NULL AUTO_INCREMENT,
  `otpremnicaID` int(11) NOT NULL,
  `jedinicaMere` varchar(255) NOT NULL,
  `kolicina` int(11) NOT NULL,
  `robaID` int(11) NOT NULL,
  PRIMARY KEY (`RB`,`otpremnicaID`),
  KEY `otpremnicaID` (`otpremnicaID`),
  KEY `robaID` (`robaID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `stavkaotpremnice` */

insert  into `stavkaotpremnice`(`RB`,`otpremnicaID`,`jedinicaMere`,`kolicina`,`robaID`) values 
(1,1,'kg',40,1),
(2,1,'kg',20,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
