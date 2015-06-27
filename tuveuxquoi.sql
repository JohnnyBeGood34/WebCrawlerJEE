-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 19 Juin 2015 à 16:24
-- Version du serveur :  5.6.17
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `address`
--

INSERT INTO `address` (`ID_ADDRESS`, `ID_USER`, `STREET`, `NUMBER`, `POSTALCODE`, `TOWN`, `COMPLEMENT`) VALUES
(1, 2, 'Chemin de la Gardie', '654', '34170', 'Castelnau le lez', 'Complement d''adresse'),
(2, 3, NULL, NULL, NULL, NULL, NULL),
(3, 4, 'Admin', '1', '34000', 'Admin', 'Admin');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `effectuer`
--

INSERT INTO `effectuer` (`ID_ROW`, `DATE_SEARCH`, `ID_USER`, `ID_SEARCH`) VALUES
(1, '2015-06-17', 2, 4),
(2, '2015-06-17', 2, 5);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `fait_reference`
--

INSERT INTO `fait_reference` (`ID_ROW_RESULT`, `ID_SEARCH_RESULT`, `ID_MAIL`) VALUES
(1, 2, 1);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `mail`
--

INSERT INTO `mail` (`ID_MAIL`, `OBJET`, `MESSAGE`, `DISTRIBUTED`, `STATUT`, `MAILJET_ID`) VALUES
(1, 'Un objet de test', 'Salut ça va? je suis un mail factisse', 1, 'Delivered', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `mailing_campaign`
--

CREATE TABLE IF NOT EXISTS `mailing_campaign` (
  `ID_MAILING` int(11) NOT NULL AUTO_INCREMENT,
  `ID_USER` int(11) DEFAULT NULL,
  `TITLE` varchar(80) DEFAULT NULL,
  `LANGUE` varchar(5) DEFAULT NULL,
  `DATE_ENVOI` date NOT NULL,
  `ID_SEARCH` int(11) NOT NULL,
  PRIMARY KEY (`ID_MAILING`),
  KEY `ID_MAILING` (`ID_MAILING`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `mailing_campaign`
--

INSERT INTO `mailing_campaign` (`ID_MAILING`, `ID_USER`, `TITLE`, `LANGUE`, `DATE_ENVOI`, `ID_SEARCH`) VALUES
(1, 2, 'Campagne de test', 'Fr', '2015-06-25', 4),
(2, 2, 'Campagne 2', 'Fr', '2015-06-06', 0),
(3, 2, 'TEST', 'Fr', '2015-06-09', 0),
(4, 2, 'Campagne', 'Fr', '2015-06-02', 0),
(5, 2, 'TESTTESTTEST', 'Fr', '2015-06-16', 0),
(6, 2, 'Campagne de test date', 'Fr', '2015-06-16', 0),
(7, 2, 'Campagn linkÃ© avec search', 'Fr', '2015-06-18', 4);

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
  `DATE_SEARCH` date NOT NULL,
  PRIMARY KEY (`ID_SEARCH`),
  KEY `ID_SEARCH` (`ID_SEARCH`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `search`
--

INSERT INTO `search` (`ID_SEARCH`, `THERM`, `DEEP_LEVEL`, `IS_FR`, `DATE_SEARCH`) VALUES
(4, 'TESTESTTEST', 1, 1, '2015-06-17'),
(5, 'TEST RECHERCHE 2', NULL, NULL, '2015-06-10');

-- --------------------------------------------------------

--
-- Structure de la table `searchresults`
--

CREATE TABLE IF NOT EXISTS `searchresults` (
  `ID_SEARCH_RESULT` int(11) NOT NULL AUTO_INCREMENT,
  `ID_SEARCH` int(11) DEFAULT NULL,
  `EMAIL_RESULT` varchar(60) DEFAULT NULL,
  `SITE_FOUND` varchar(255) NOT NULL,
  `IS_IN_CAMPAIGN` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID_SEARCH_RESULT`),
  KEY `ID_SEARCH_RESULT` (`ID_SEARCH_RESULT`),
  KEY `ID_SEARCH` (`ID_SEARCH`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `searchresults`
--

INSERT INTO `searchresults` (`ID_SEARCH_RESULT`, `ID_SEARCH`, `EMAIL_RESULT`, `SITE_FOUND`, `IS_IN_CAMPAIGN`) VALUES
(1, 4, 'email@test.fr', 'www.lescouillesajaquie.fr', 1),
(2, 4, 'jaquieetmichel@jetm.com', 'www.jaquieetmichel.com', 1);

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
  `denied` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID_USER`),
  KEY `ID_USER` (`ID_USER`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`ID_USER`, `NAME`, `FIRSTNAME`, `EMAIL`, `PASS`, `denied`) VALUES
(2, 'Affre', 'Jonathan', 'jonathanaffre@gmail.com', 'yarienpourtoi', 0),
(3, NULL, NULL, NULL, NULL, 1),
(4, 'Admin', 'Admin', 'admin@admin.fr', 'adminadmin', 0);

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
  ADD CONSTRAINT `effectuer_ibfk_1` FOREIGN KEY (`ID_USER`) REFERENCES `user` (`ID_USER`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `effectuer_ibfk_2` FOREIGN KEY (`ID_SEARCH`) REFERENCES `search` (`ID_SEARCH`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `fait_reference`
--
ALTER TABLE `fait_reference`
  ADD CONSTRAINT `fait_reference_ibfk_1` FOREIGN KEY (`ID_SEARCH_RESULT`) REFERENCES `searchresults` (`ID_SEARCH_RESULT`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fait_reference_ibfk_2` FOREIGN KEY (`ID_MAIL`) REFERENCES `mail` (`ID_MAIL`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
