-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 20 Juin 2017 à 08:11
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
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `attribut`
--

INSERT INTO `attribut` (`id`, `PairId`, `nomAttribut`, `Attr1`, `Attr2`, `Val`, `nbrVote`, `tauxVote`) VALUES
(1, 1, 'RestaurantName', 'arnie morton''s of chicago', 'arnie morton''s of chicago', 0, 1, 0),
(2, 1, 'RestaurantAdress', '435 s. la cienega blv.', '435 s. la cienega blvd.', 0, 1, 0),
(3, 1, 'RestaurantVille', 'los angeles', 'los angeles', 0, 1, 0),
(4, 1, 'RestaurantPhone', '310/246-1501', '310-246-1501', 0, 1, 0),
(5, 1, 'RestaurantType', 'american', 'steakhouses', 0, 1, 0);

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lhs_rhs`
--

DROP TABLE IF EXISTS `lhs_rhs`;
CREATE TABLE IF NOT EXISTS `lhs_rhs` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdPair` int(11) NOT NULL,
  `LHS` varchar(400) DEFAULT NULL,
  `RHS` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lhs_rhs_temp`
--

DROP TABLE IF EXISTS `lhs_rhs_temp`;
CREATE TABLE IF NOT EXISTS `lhs_rhs_temp` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdPair` int(11) NOT NULL,
  `LHS` varchar(400) DEFAULT NULL,
  `RHS` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lhs_rhs_temp_apriori`
--

DROP TABLE IF EXISTS `lhs_rhs_temp_apriori`;
CREATE TABLE IF NOT EXISTS `lhs_rhs_temp_apriori` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdPair` int(11) NOT NULL,
  `LHS` varchar(400) DEFAULT NULL,
  `RHS` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `lhs_rhs_temp_apriori`
--

INSERT INTO `lhs_rhs_temp_apriori` (`Id`, `IdPair`, `LHS`, `RHS`) VALUES
(2, 1, 'RestaurantName,RestaurantAdress,RestaurantVille,RestaurantPhone', 'RestaurantType');

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
-- Structure de la table `md_temp_one_entity`
--

DROP TABLE IF EXISTS `md_temp_one_entity`;
CREATE TABLE IF NOT EXISTS `md_temp_one_entity` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdPair` int(11) NOT NULL,
  `LHS` varchar(400) DEFAULT NULL,
  `RHS` varchar(400) DEFAULT NULL,
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
  `nbrVote` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `pair`
--

INSERT INTO `pair` (`Id`, `Entry1`, `Entry2`, `nbrVote`) VALUES
(1, '	arnie morton''s of chicago, "435 s. la cienega blv.", "los angeles", "310/246-1501", "american", ''0''', 'arnie morton''s of chicago, "435 s. la cienega blvd.", "los angeles", "310-246-1501", "steakhouses", ''0''', 0);

-- --------------------------------------------------------

--
-- Structure de la table `pre_traitement`
--

DROP TABLE IF EXISTS `pre_traitement`;
CREATE TABLE IF NOT EXISTS `pre_traitement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idPair` int(11) NOT NULL,
  `idAttribut1` int(11) DEFAULT NULL,
  `idAttribut2` int(11) DEFAULT NULL,
  `idAttribut3` int(11) DEFAULT NULL,
  `idAttribut4` int(11) DEFAULT NULL,
  `idAttribut5` int(11) DEFAULT NULL,
  `MoySimilar` double NOT NULL,
  KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `pre_traitement`
--

INSERT INTO `pre_traitement` (`id`, `idPair`, `idAttribut1`, `idAttribut2`, `idAttribut3`, `idAttribut4`, `idAttribut5`, `MoySimilar`) VALUES
(1, 1, 1, 2, 3, 4, 5, 0);

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
  `idUser` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `isAdmin` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `login`, `password`, `isAdmin`) VALUES
(1, 'user', 'ee11cbb19052e40b07aac0ca060c23ee', 0),
(2, 'admin', '21232f297a57a5a743894a0e4a801fc3', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
