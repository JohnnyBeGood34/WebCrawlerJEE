-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 13 Février 2015 à 16:21
-- Version du serveur :  5.6.17-log
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `tuveuxquoi`
--

-- --------------------------------------------------------

--
-- Structure de la table `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `ID_ADDRESS` int(11) NOT NULL AUTO_INCREMENT,
  `ID_USER` int(11) NOT NULL,
  `STREET` varchar(50) DEFAULT NULL,
  `NUMBER` varchar(4) DEFAULT NULL,
  `POSTALCODE` varchar(5) DEFAULT NULL,
  `TOWN` varchar(40) DEFAULT NULL,
  `COMPLEMENT` varchar(40) DEFAULT NULL,
  KEY `ID_ADDRESS` (`ID_ADDRESS`),
  KEY `ID_USER` (`ID_USER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `effectuer`
--

CREATE TABLE IF NOT EXISTS `effectuer` (
  `ID_ROW` int(11) NOT NULL AUTO_INCREMENT,
  `DATE_SEARCH` date DEFAULT NULL,
  `ID_USER` int(11) DEFAULT NULL,
  `ID_SEARCH` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_ROW`),
  KEY `id_row` (`ID_ROW`),
  KEY `ID_USER` (`ID_USER`),
  KEY `ID_SEARCH` (`ID_SEARCH`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `fait_reference`
--

CREATE TABLE IF NOT EXISTS `fait_reference` (
  `ID_ROW_RESULT` int(11) NOT NULL AUTO_INCREMENT,
  `ID_SEARCH_RESULT` int(11) DEFAULT NULL,
  `ID_MAIL` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_ROW_RESULT`),
  KEY `id_row_result` (`ID_ROW_RESULT`),
  KEY `ID_SEARCH_RESULT` (`ID_SEARCH_RESULT`),
  KEY `ID_MAIL` (`ID_MAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `file`
--

CREATE TABLE IF NOT EXISTS `file` (
  `ID_FILE` int(11) NOT NULL AUTO_INCREMENT,
  `ID_MAIL` int(11) DEFAULT NULL,
  `PATH` varchar(80) DEFAULT NULL,
  `IS_IN_BODY` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID_FILE`),
  KEY `ID_MAIL` (`ID_MAIL`),
  KEY `ID_MAIL_2` (`ID_MAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `mail`
--

CREATE TABLE IF NOT EXISTS `mail` (
  `ID_MAIL` int(11) NOT NULL AUTO_INCREMENT,
  `OBJET` varchar(40) DEFAULT NULL,
  `MESSAGE` mediumtext NOT NULL,
  `DISTRIBUTED` tinyint(1) DEFAULT NULL,
  `STATUT` varchar(40) DEFAULT NULL,
  `MAILJET_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_MAIL`),
  KEY `ID_MAIL` (`ID_MAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `mailing_campaign`
--

CREATE TABLE IF NOT EXISTS `mailing_campaign` (
  `ID_MAILING` int(11) NOT NULL AUTO_INCREMENT,
  `ID_USER` int(11) DEFAULT NULL,
  `TITLE` varchar(80) DEFAULT NULL,
  `LANGUE` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`ID_MAILING`),
  KEY `ID_MAILING` (`ID_MAILING`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `row_campaign`
--

CREATE TABLE IF NOT EXISTS `row_campaign` (
  `id_row` int(11) NOT NULL AUTO_INCREMENT,
  `id_campaign` int(11) NOT NULL,
  `id_mail` int(11) NOT NULL,
  PRIMARY KEY (`id_row`),
  KEY `id_row` (`id_row`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `search`
--

CREATE TABLE IF NOT EXISTS `search` (
  `ID_SEARCH` int(11) NOT NULL AUTO_INCREMENT,
  `THERM` varchar(40) DEFAULT NULL,
  `DEEP_LEVEL` int(11) DEFAULT NULL,
  `IS_FR` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID_SEARCH`),
  KEY `ID_SEARCH` (`ID_SEARCH`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `searchresults`
--

CREATE TABLE IF NOT EXISTS `searchresults` (
  `ID_SEARCH_RESULT` int(11) NOT NULL AUTO_INCREMENT,
  `ID_SEARCH` int(11) DEFAULT NULL,
  `EMAIL_RESULT` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`ID_SEARCH_RESULT`),
  KEY `ID_SEARCH_RESULT` (`ID_SEARCH_RESULT`),
  KEY `ID_SEARCH` (`ID_SEARCH`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `ID_USER` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(40) DEFAULT NULL,
  `FIRSTNAME` varchar(40) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `PASS` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID_USER`),
  KEY `ID_USER` (`ID_USER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `address_ibfk_1` FOREIGN KEY (`ID_USER`) REFERENCES `user` (`ID_USER`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `effectuer`
--
ALTER TABLE `effectuer`
  ADD CONSTRAINT `effectuer_ibfk_2` FOREIGN KEY (`ID_SEARCH`) REFERENCES `search` (`ID_SEARCH`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `effectuer_ibfk_1` FOREIGN KEY (`ID_USER`) REFERENCES `user` (`ID_USER`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `fait_reference`
--
ALTER TABLE `fait_reference`
  ADD CONSTRAINT `fait_reference_ibfk_2` FOREIGN KEY (`ID_MAIL`) REFERENCES `mail` (`ID_MAIL`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fait_reference_ibfk_1` FOREIGN KEY (`ID_SEARCH_RESULT`) REFERENCES `searchresults` (`ID_SEARCH_RESULT`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `file`
--
ALTER TABLE `file`
  ADD CONSTRAINT `file_ibfk_1` FOREIGN KEY (`ID_MAIL`) REFERENCES `mail` (`ID_MAIL`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `row_campaign`
--
ALTER TABLE `row_campaign`
  ADD CONSTRAINT `row_campaign_ibfk_1` FOREIGN KEY (`id_row`) REFERENCES `mailing_campaign` (`ID_MAILING`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `searchresults`
--
ALTER TABLE `searchresults`
  ADD CONSTRAINT `searchresults_ibfk_1` FOREIGN KEY (`ID_SEARCH`) REFERENCES `search` (`ID_SEARCH`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
