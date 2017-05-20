-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Sam 20 Mai 2017 à 10:26
-- Version du serveur :  5.7.9
-- Version de PHP :  5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projetppd`
--

-- --------------------------------------------------------

--
-- Structure de la table `attribut`
--

DROP TABLE IF EXISTS `attribut`;
CREATE TABLE IF NOT EXISTS `attribut` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `PairId` int(11) NOT NULL,
  `nomAttribut` varchar(30) NOT NULL,
  `Attr1` varchar(200) NOT NULL,
  `Attr2` varchar(200) NOT NULL,
  `Val` float NOT NULL,
  `nbrVote` int(11) NOT NULL,
  `tauxVote` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2161 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `attribut_apriori`
--

DROP TABLE IF EXISTS `attribut_apriori`;
CREATE TABLE IF NOT EXISTS `attribut_apriori` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `PairId` int(11) NOT NULL,
  `nomAttribut` varchar(30) NOT NULL,
  `Attr1` varchar(200) NOT NULL,
  `Attr2` varchar(200) NOT NULL,
  `Val` float NOT NULL,
  `nbrVote` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `attribut_one_entity`
--

DROP TABLE IF EXISTS `attribut_one_entity`;
CREATE TABLE IF NOT EXISTS `attribut_one_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `PairId` int(11) NOT NULL,
  `nomAttribut` varchar(30) NOT NULL,
  `Attr1` varchar(200) NOT NULL,
  `Attr2` varchar(200) NOT NULL,
  `Val` float NOT NULL,
  `nbrVote` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2161 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `matching_dependencie`
--

DROP TABLE IF EXISTS `matching_dependencie`;
CREATE TABLE IF NOT EXISTS `matching_dependencie` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Entry1` varchar(200) NOT NULL,
  `Entry2` varchar(200) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `md_temp_apriori_remaster`
--

DROP TABLE IF EXISTS `md_temp_apriori_remaster`;
CREATE TABLE IF NOT EXISTS `md_temp_apriori_remaster` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Entry1` varchar(200) NOT NULL,
  `Entry2` varchar(200) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `md_temp_one_entity`
--

DROP TABLE IF EXISTS `md_temp_one_entity`;
CREATE TABLE IF NOT EXISTS `md_temp_one_entity` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Entry1` varchar(200) NOT NULL,
  `Entry2` varchar(200) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `pair`
--

DROP TABLE IF EXISTS `pair`;
CREATE TABLE IF NOT EXISTS `pair` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Entry1` varchar(200) NOT NULL,
  `Entry2` varchar(200) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=433 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `pre_traitement`
--

DROP TABLE IF EXISTS `pre_traitement`;
CREATE TABLE IF NOT EXISTS `pre_traitement` (
  `id` int(11) NOT NULL DEFAULT '0',
  `idPair` int(11) NOT NULL,
  `idAttribut1` int(11) DEFAULT NULL,
  `idAttribut2` int(11) DEFAULT NULL,
  `idAttribut3` int(11) DEFAULT NULL,
  `idAttribut4` int(11) DEFAULT NULL,
  `idAttribut5` int(11) DEFAULT NULL,
  `MoySimilar` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `similarite_r`
--

DROP TABLE IF EXISTS `similarite_r`;
CREATE TABLE IF NOT EXISTS `similarite_r` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idPair` int(11) NOT NULL,
  `idAttribut1` int(11) DEFAULT NULL,
  `idAttribut2` int(11) DEFAULT NULL,
  `idAttribut3` int(11) DEFAULT NULL,
  `idAttribut4` int(11) DEFAULT NULL,
  `idAttribut5` int(11) DEFAULT NULL,
  `MoySimilar` double NOT NULL,
  `nbrVote` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=433 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `similarite_r_prime`
--

DROP TABLE IF EXISTS `similarite_r_prime`;
CREATE TABLE IF NOT EXISTS `similarite_r_prime` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idPair` int(11) NOT NULL,
  `idAttribut1` int(11) DEFAULT NULL,
  `idAttribut2` int(11) DEFAULT NULL,
  `idAttribut3` int(11) DEFAULT NULL,
  `idAttribut4` int(11) DEFAULT NULL,
  `idAttribut5` int(11) DEFAULT NULL,
  `MoySimilar` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `similarite_r_prime_remaster`
--

DROP TABLE IF EXISTS `similarite_r_prime_remaster`;
CREATE TABLE IF NOT EXISTS `similarite_r_prime_remaster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idPair` int(11) NOT NULL,
  `idAttribut1` int(11) DEFAULT NULL,
  `idAttribut2` int(11) DEFAULT NULL,
  `idAttribut3` int(11) DEFAULT NULL,
  `idAttribut4` int(11) DEFAULT NULL,
  `idAttribut5` int(11) DEFAULT NULL,
  `MoySimilar` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `similarite_r_remaster`
--

DROP TABLE IF EXISTS `similarite_r_remaster`;
CREATE TABLE IF NOT EXISTS `similarite_r_remaster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idPair` int(11) NOT NULL,
  `idAttribut1` int(11) DEFAULT NULL,
  `idAttribut2` int(11) DEFAULT NULL,
  `idAttribut3` int(11) DEFAULT NULL,
  `idAttribut4` int(11) DEFAULT NULL,
  `idAttribut5` int(11) DEFAULT NULL,
  `MoySimilar` double NOT NULL,
  `nbrVote` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
