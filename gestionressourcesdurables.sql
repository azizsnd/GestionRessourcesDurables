-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 13 déc. 2024 à 14:36
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestionressourcesdurables`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

CREATE TABLE `administrateur` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `administrateur`
--

INSERT INTO `administrateur` (`id`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `auditenviromental`
--

CREATE TABLE `auditenviromental` (
  `id` int(11) NOT NULL,
  `entite` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `auditenviromental_resultat`
--

CREATE TABLE `auditenviromental_resultat` (
  `audit_id` int(11) NOT NULL,
  `reglementation_id` int(11) NOT NULL,
  `conforme` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `auditeur`
--

CREATE TABLE `auditeur` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `auditeur`
--

INSERT INTO `auditeur` (`id`) VALUES
(2);

-- --------------------------------------------------------

--
-- Structure de la table `dechet`
--

CREATE TABLE `dechet` (
  `id` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `quantiteProduite` double NOT NULL,
  `quantiteRecycle` double NOT NULL,
  `methodeElimination` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `dechet`
--

INSERT INTO `dechet` (`id`, `type`, `quantiteProduite`, `quantiteRecycle`, `methodeElimination`) VALUES
(1, 'ssss', 425, 55, 'Recyclage'),
(2, 'eeee', 156, 558, 'Recyclage'),
(3, 'vvvvvvvvv', 7854, 455654, 'Recyclage'),
(6, 'nan', 547, 890, 'Recyclage'),
(7, 'azazza', 7444, 5000, 'Recyclage'),
(8, 'ddddd', 123, 987, 'Recyclage'),
(9, 'iiii', 85454, 77887, 'Recyclage'),
(17, 'rzzzzzzzzzz', 848, 55, 'Recyclage'),
(18, 'zerzer', 845, 8451, 'Recyclage');

-- --------------------------------------------------------

--
-- Structure de la table `empreintecarbone`
--

CREATE TABLE `empreintecarbone` (
  `id` int(11) NOT NULL,
  `sourceEmission` varchar(255) DEFAULT NULL,
  `emissionAnnuelles` double NOT NULL,
  `emissionActuelle` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `energie`
--

CREATE TABLE `energie` (
  `id` int(11) NOT NULL,
  `estRenouvelable` tinyint(1) NOT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `energie`
--

INSERT INTO `energie` (`id`, `estRenouvelable`, `type`) VALUES
(13, 1, 'solaire'),
(20, 0, 'ttt'),
(21, 1, 'ffgfe'),
(22, 1, 'testtttttttttttttttttt');

-- --------------------------------------------------------

--
-- Structure de la table `entitedurable`
--

CREATE TABLE `entitedurable` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `dateCreation` date NOT NULL,
  `objectifId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `entitedurable`
--

INSERT INTO `entitedurable` (`id`, `nom`, `description`, `dateCreation`, `objectifId`) VALUES
(2, 'Déchet de ssss', 'Description du déchet', '2024-12-03', 15),
(3, 'Déchet de eeee', 'Description du déchet', '2024-12-03', 16),
(4, 'Déchet de vvvvvvvvv', 'Description du déchet', '2024-12-03', 17),
(6, 'Déchet de nan', 'Description du déchet', '2024-12-03', 19),
(7, 'Déchet de azazza', 'Description du déchet', '2024-12-03', 20),
(8, 'Déchet de ddddd', 'Description du déchet', '2024-12-03', 21),
(9, 'Déchet de iiii', 'Description du déchet', '2024-12-03', 22),
(10, 'Ressource de type azzaz', 'Description du déchet', '2024-12-03', 23),
(11, 'Ressource de type azezez', 'Description du déchet', '2024-12-03', 24),
(12, 'Ressource de type hbjbhjhjb', 'Description du déchet', '2024-12-03', 25),
(13, 'solaire', 'Énergie de type solaire', '2024-12-05', 29),
(17, 'Déchet de rzzzzzzzzzz', 'Description du déchet', '2024-12-05', 33),
(18, 'Déchet de zerzer', 'Description du déchet', '2024-12-05', 34),
(19, 'Ressource de type ererz', 'Description du déchet', '2024-12-05', 35),
(20, 'ttt', 'Énergie de type ttt', '2024-12-05', 36),
(21, 'ffgfe', 'Énergie de type ffgfe', '2024-12-05', 37),
(22, 'testtttttttttttttttttt', 'Énergie de type testtttttttttttttttttt', '2024-12-05', 38),
(23, 'Ressource de type zaaazezlll', 'Description du déchet', '2024-12-05', 39);

-- --------------------------------------------------------

--
-- Structure de la table `gestionrapportsuivi`
--

CREATE TABLE `gestionrapportsuivi` (
  `id` int(11) NOT NULL,
  `frequenceRapport` int(11) DEFAULT NULL,
  `rapportType` varchar(255) DEFAULT NULL,
  `dateDernierRapport` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `idService` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `incidentenvironnemental`
--

CREATE TABLE `incidentenvironnemental` (
  `id` int(11) NOT NULL,
  `typeIncident` varchar(255) NOT NULL,
  `dateIncident` date NOT NULL,
  `consequence` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `normeiso`
--

CREATE TABLE `normeiso` (
  `id` int(11) NOT NULL,
  `numISO` int(11) NOT NULL,
  `descriptionNorme` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `normeiso_reglementation`
--

CREATE TABLE `normeiso_reglementation` (
  `norme_id` int(11) NOT NULL,
  `reglementation_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `objectifdurabilite`
--

CREATE TABLE `objectifdurabilite` (
  `id` int(11) NOT NULL,
  `dateCible` date NOT NULL,
  `reductionCible` double NOT NULL,
  `progresActuel` double NOT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `objectifdurabilite`
--

INSERT INTO `objectifdurabilite` (`id`, `dateCible`, `reductionCible`, `progresActuel`, `description`) VALUES
(15, '2024-12-03', 100, 0, 'Objectif de recyclage'),
(16, '2024-12-03', 100, 0, 'Objectif de recyclage'),
(17, '2024-12-03', 100, 0, 'Objectif de recyclage'),
(19, '2024-12-03', 100, 0, 'Objectif de recyclage'),
(20, '2024-12-03', 100, 0, 'Objectif de recyclage'),
(21, '2024-12-03', 100, 0, 'Objectif de recyclage'),
(22, '2024-12-03', 100, 0, 'Objectif de recyclage'),
(23, '2024-12-03', 500, 100, 'Objectif de réduction'),
(24, '2024-12-03', 500, 100, 'Objectif de réduction'),
(25, '2024-12-03', 500, 100, 'Objectif de réduction'),
(26, '2001-12-12', 55, 54, 'AZAZAZ'),
(27, '2202-10-12', 15445, 554, 'dsfdsf'),
(28, '2204-05-12', 45, 57, 'fdfdsfd'),
(29, '2024-12-05', 1000, 500, 'Objectif de production'),
(33, '2024-12-05', 100, 0, 'Objectif de recyclage'),
(34, '2024-12-05', 100, 0, 'Objectif de recyclage'),
(35, '2024-12-05', 500, 100, 'Objectif de réduction'),
(36, '2024-12-05', 1000, 500, 'Objectif de production'),
(37, '2024-12-05', 1000, 500, 'Objectif de production'),
(38, '2024-12-05', 1000, 500, 'Objectif de production'),
(39, '2024-12-05', 500, 100, 'Objectif de réduction');

-- --------------------------------------------------------

--
-- Structure de la table `plandactioncorrectif`
--

CREATE TABLE `plandactioncorrectif` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `responsable` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `plandactioncorrectif_actions`
--

CREATE TABLE `plandactioncorrectif_actions` (
  `plan_id` int(11) NOT NULL,
  `action` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `plandactioncorrectif_reglementation`
--

CREATE TABLE `plandactioncorrectif_reglementation` (
  `plan_id` int(11) NOT NULL,
  `reglementation_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `plandegestionrisque`
--

CREATE TABLE `plandegestionrisque` (
  `id` int(11) NOT NULL,
  `responsableGestion` varchar(255) NOT NULL,
  `dateEvaluation` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `planrisqueassoc`
--

CREATE TABLE `planrisqueassoc` (
  `id` int(11) NOT NULL,
  `planDeGestionRisqueId` int(11) NOT NULL,
  `risqueEnviromentalId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `rapport`
--

CREATE TABLE `rapport` (
  `id` int(11) NOT NULL,
  `contenuRapport` text DEFAULT NULL,
  `dateRapport` timestamp NOT NULL DEFAULT current_timestamp(),
  `typeService` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `rapport`
--

INSERT INTO `rapport` (`id`, `contenuRapport`, `dateRapport`, `typeService`) VALUES
(6, 'Rapport de Suivi des Ressources:\nUtilisation totale de ressources: 66188509,00 unités\nCoût total estimé: 10001610151,00\nNombre de types de ressources suivis: 9les resources Suivis :[\n[\nRessource{id=10, nom=Ressource de type azzaz, description=Description du déchet, dateCreation=2024-12-03, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-03, reductionCible=500.0, progresActuel=100.0, description=Objectif de réduction}, uniteDeMesure=unité, utilisationReference=54554.0, utilisationActuelle=545454.0, coutParUnite=1.0}, \nRessource{id=11, nom=Ressource de type azezez, description=Description du déchet, dateCreation=2024-12-03, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-03, reductionCible=500.0, progresActuel=100.0, description=Objectif de réduction}, uniteDeMesure=unité, utilisationReference=488454.0, utilisationActuelle=54545.0, coutParUnite=1.0}, \nRessource{id=12, nom=Ressource de type hbjbhjhjb, description=Description du déchet, dateCreation=2024-12-03, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-03, reductionCible=500.0, progresActuel=100.0, description=Objectif de réduction}, uniteDeMesure=unité, utilisationReference=5.6546548E7, utilisationActuelle=545115.0, coutParUnite=1.0}, \nRessource{id=13, nom=solaire, description=Énergie de type solaire, dateCreation=2024-12-05, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-05, reductionCible=1000.0, progresActuel=500.0, description=Objectif de production}, uniteDeMesure=unité, utilisationReference=5000.0, utilisationActuelle=2400.0, coutParUnite=1.0}, \nRessource{id=19, nom=Ressource de type ererz, description=Description du déchet, dateCreation=2024-12-05, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-05, reductionCible=500.0, progresActuel=100.0, description=Objectif de réduction}, uniteDeMesure=unité, utilisationReference=4554.0, utilisationActuelle=7455.0, coutParUnite=1.0}, \nRessource{id=20, nom=ttt, description=Énergie de type ttt, dateCreation=2024-12-05, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-05, reductionCible=1000.0, progresActuel=500.0, description=Objectif de production}, uniteDeMesure=unité, utilisationReference=154554.0, utilisationActuelle=211.0, coutParUnite=1.0}, \nRessource{id=21, nom=ffgfe, description=Énergie de type ffgfe, dateCreation=2024-12-05, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-05, reductionCible=1000.0, progresActuel=500.0, description=Objectif de production}, uniteDeMesure=unité, utilisationReference=512.0, utilisationActuelle=451.0, coutParUnite=1.0}, \nRessource{id=22, nom=testtttttttttttttttttt, description=Énergie de type testtttttttttttttttttt, dateCreation=2024-12-05, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-05, reductionCible=1000.0, progresActuel=500.0, description=Objectif de production}, uniteDeMesure=unité, utilisationReference=45445.0, utilisationActuelle=454521.0, coutParUnite=1.0}, \nRessource{id=23, nom=Ressource de type zaaazezlll, description=Description du déchet, dateCreation=2024-12-05, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-05, reductionCible=500.0, progresActuel=100.0, description=Objectif de réduction}, uniteDeMesure=unité, utilisationReference=8888888.0, utilisationActuelle=9.999999999E9, coutParUnite=1.0}]\n]', '2024-12-05 18:44:24', 'Ressource'),
(7, 'Rapport de Suivi de l\'Énergie:\nQuantité totale d\'énergie produite: 457583,00 unités\nPourcentage d\'énergie renouvelable: 75,00%\nNombre de sources d\'énergie suivies: 4les sources Energie :[\n[\nEnergie{\nRessource{id=13, nom=solaire, description=Énergie de type solaire, dateCreation=2024-12-05, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-05, reductionCible=1000.0, progresActuel=500.0, description=Objectif de production}, uniteDeMesure=unité, utilisationReference=5000.0, utilisationActuelle=2400.0, coutParUnite=1.0}, estRenouvelable=true, type=solaire}, \nEnergie{\nRessource{id=20, nom=ttt, description=Énergie de type ttt, dateCreation=2024-12-05, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-05, reductionCible=1000.0, progresActuel=500.0, description=Objectif de production}, uniteDeMesure=unité, utilisationReference=154554.0, utilisationActuelle=211.0, coutParUnite=1.0}, estRenouvelable=false, type=ttt}, \nEnergie{\nRessource{id=21, nom=ffgfe, description=Énergie de type ffgfe, dateCreation=2024-12-05, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-05, reductionCible=1000.0, progresActuel=500.0, description=Objectif de production}, uniteDeMesure=unité, utilisationReference=512.0, utilisationActuelle=451.0, coutParUnite=1.0}, estRenouvelable=true, type=ffgfe}, \nEnergie{\nRessource{id=22, nom=testtttttttttttttttttt, description=Énergie de type testtttttttttttttttttt, dateCreation=2024-12-05, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-05, reductionCible=1000.0, progresActuel=500.0, description=Objectif de production}, uniteDeMesure=unité, utilisationReference=45445.0, utilisationActuelle=454521.0, coutParUnite=1.0}, estRenouvelable=true, type=testtttttttttttttttttt}]\n]', '2024-12-05 18:46:44', 'Energie'),
(8, 'Rapport de Suivi des Déchets:\nQuantité totale de déchets: 0,00\nTaux de recyclage moyen: 1036,16%\nNombre de types de déchets suivis: 8les dechet Suivis :[\n[\nDechet{id=2, nom=Déchet de ssss, description=Description du déchet, dateCreation=2024-12-03, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-03, reductionCible=100.0, progresActuel=357.69230769230774, description=Objectif de recyclage}, type=eeee, quantiteProduite=156.0, quantiteRecycle=558.0, methodeElimination=Recyclage}, \nDechet{id=3, nom=Déchet de eeee, description=Description du déchet, dateCreation=2024-12-03, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-03, reductionCible=100.0, progresActuel=5801.553348612172, description=Objectif de recyclage}, type=vvvvvvvvv, quantiteProduite=7854.0, quantiteRecycle=455654.0, methodeElimination=Recyclage}, \nDechet{id=6, nom=Déchet de nan, description=Description du déchet, dateCreation=2024-12-03, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-03, reductionCible=100.0, progresActuel=162.70566727605117, description=Objectif de recyclage}, type=nan, quantiteProduite=547.0, quantiteRecycle=890.0, methodeElimination=Recyclage}, \nDechet{id=7, nom=Déchet de azazza, description=Description du déchet, dateCreation=2024-12-03, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-03, reductionCible=100.0, progresActuel=67.16818914562064, description=Objectif de recyclage}, type=azazza, quantiteProduite=7444.0, quantiteRecycle=5000.0, methodeElimination=Recyclage}, \nDechet{id=8, nom=Déchet de ddddd, description=Description du déchet, dateCreation=2024-12-03, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-03, reductionCible=100.0, progresActuel=802.4390243902438, description=Objectif de recyclage}, type=ddddd, quantiteProduite=123.0, quantiteRecycle=987.0, methodeElimination=Recyclage}, \nDechet{id=9, nom=Déchet de iiii, description=Description du déchet, dateCreation=2024-12-03, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-03, reductionCible=100.0, progresActuel=91.14494347836263, description=Objectif de recyclage}, type=iiii, quantiteProduite=85454.0, quantiteRecycle=77887.0, methodeElimination=Recyclage}, \nDechet{id=17, nom=Déchet de rzzzzzzzzzz, description=Description du déchet, dateCreation=2024-12-05, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-05, reductionCible=100.0, progresActuel=6.485849056603773, description=Objectif de recyclage}, type=rzzzzzzzzzz, quantiteProduite=848.0, quantiteRecycle=55.0, methodeElimination=Recyclage}, \nDechet{id=18, nom=Déchet de zerzer, description=Description du déchet, dateCreation=2024-12-05, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-05, reductionCible=100.0, progresActuel=1000.1183431952663, description=Objectif de recyclage}, type=zerzer, quantiteProduite=845.0, quantiteRecycle=8451.0, methodeElimination=Recyclage}]\n]', '2024-12-05 18:48:51', 'Dechet'),
(9, 'Rapport de Suivi des Objectifs:\nNombre total d\'objectifs : 21\nNombre d\'objectifs atteints : 1\nObjectif prioritaire : AZAZAZ, Progrès restant : 1,00\nles objectifsSuivis :[\n[\nObjectifDurabilite{id=15, dateCible=Tue Dec 03 00:00:00 WAT 2024, reductionCible=100.0, progresActuel=0.0, description=Objectif de recyclage}, \nObjectifDurabilite{id=16, dateCible=Tue Dec 03 00:00:00 WAT 2024, reductionCible=100.0, progresActuel=0.0, description=Objectif de recyclage}, \nObjectifDurabilite{id=17, dateCible=Tue Dec 03 00:00:00 WAT 2024, reductionCible=100.0, progresActuel=0.0, description=Objectif de recyclage}, \nObjectifDurabilite{id=19, dateCible=Tue Dec 03 00:00:00 WAT 2024, reductionCible=100.0, progresActuel=0.0, description=Objectif de recyclage}, \nObjectifDurabilite{id=20, dateCible=Tue Dec 03 00:00:00 WAT 2024, reductionCible=100.0, progresActuel=0.0, description=Objectif de recyclage}, \nObjectifDurabilite{id=21, dateCible=Tue Dec 03 00:00:00 WAT 2024, reductionCible=100.0, progresActuel=0.0, description=Objectif de recyclage}, \nObjectifDurabilite{id=22, dateCible=Tue Dec 03 00:00:00 WAT 2024, reductionCible=100.0, progresActuel=0.0, description=Objectif de recyclage}, \nObjectifDurabilite{id=23, dateCible=Tue Dec 03 00:00:00 WAT 2024, reductionCible=500.0, progresActuel=100.0, description=Objectif de réduction}, \nObjectifDurabilite{id=24, dateCible=Tue Dec 03 00:00:00 WAT 2024, reductionCible=500.0, progresActuel=100.0, description=Objectif de réduction}, \nObjectifDurabilite{id=25, dateCible=Tue Dec 03 00:00:00 WAT 2024, reductionCible=500.0, progresActuel=100.0, description=Objectif de réduction}, \nObjectifDurabilite{id=26, dateCible=Wed Dec 12 00:00:00 WAT 2001, reductionCible=55.0, progresActuel=54.0, description=AZAZAZ}, \nObjectifDurabilite{id=27, dateCible=Tue Oct 12 00:00:00 WAT 2202, reductionCible=15445.0, progresActuel=554.0, description=dsfdsf}, \nObjectifDurabilite{id=28, dateCible=Sat May 12 00:00:00 WAT 2204, reductionCible=45.0, progresActuel=57.0, description=fdfdsfd}, \nObjectifDurabilite{id=29, dateCible=Thu Dec 05 00:00:00 WAT 2024, reductionCible=1000.0, progresActuel=500.0, description=Objectif de production}, \nObjectifDurabilite{id=33, dateCible=Thu Dec 05 00:00:00 WAT 2024, reductionCible=100.0, progresActuel=0.0, description=Objectif de recyclage}, \nObjectifDurabilite{id=34, dateCible=Thu Dec 05 00:00:00 WAT 2024, reductionCible=100.0, progresActuel=0.0, description=Objectif de recyclage}, \nObjectifDurabilite{id=35, dateCible=Thu Dec 05 00:00:00 WAT 2024, reductionCible=500.0, progresActuel=100.0, description=Objectif de réduction}, \nObjectifDurabilite{id=36, dateCible=Thu Dec 05 00:00:00 WAT 2024, reductionCible=1000.0, progresActuel=500.0, description=Objectif de production}, \nObjectifDurabilite{id=37, dateCible=Thu Dec 05 00:00:00 WAT 2024, reductionCible=1000.0, progresActuel=500.0, description=Objectif de production}, \nObjectifDurabilite{id=38, dateCible=Thu Dec 05 00:00:00 WAT 2024, reductionCible=1000.0, progresActuel=500.0, description=Objectif de production}, \nObjectifDurabilite{id=39, dateCible=Thu Dec 05 00:00:00 WAT 2024, reductionCible=500.0, progresActuel=100.0, description=Objectif de réduction}]\n]', '2024-12-05 18:48:57', 'Objectif');

-- --------------------------------------------------------

--
-- Structure de la table `reglementation`
--

CREATE TABLE `reglementation` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `descriptionExigence` text NOT NULL,
  `dateMiseEnApplication` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `ressource`
--

CREATE TABLE `ressource` (
  `id` int(11) NOT NULL,
  `uniteDeMesure` varchar(255) DEFAULT NULL,
  `utilisationReference` double NOT NULL,
  `utilisationActuelle` double NOT NULL,
  `coutParUnite` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `ressource`
--

INSERT INTO `ressource` (`id`, `uniteDeMesure`, `utilisationReference`, `utilisationActuelle`, `coutParUnite`) VALUES
(10, 'unité', 54554, 545454, 1),
(11, 'unité', 488454, 54545, 1),
(12, 'unité', 56546548, 545115, 1),
(13, 'unité', 5000, 2400, 1),
(19, 'unité', 4554, 7455, 1),
(20, 'unité', 154554, 211, 1),
(21, 'unité', 512, 451, 1),
(22, 'unité', 45445, 454521, 1),
(23, 'unité', 8888888, 9999999999, 1);

-- --------------------------------------------------------

--
-- Structure de la table `risqueenviromental`
--

CREATE TABLE `risqueenviromental` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  `probabilite` double NOT NULL,
  `impact` double NOT NULL,
  `priorite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `servicesuivi`
--

CREATE TABLE `servicesuivi` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `frequenceRapport` int(11) NOT NULL,
  `dernierDateSuivi` date DEFAULT NULL,
  `statusService` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `servicesuivicarbone`
--

CREATE TABLE `servicesuivicarbone` (
  `id` int(11) NOT NULL,
  `emissionTotal` double NOT NULL,
  `reductionCibleTotal` double NOT NULL,
  `sourcePrincipalEmission` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `servicesuividechet`
--

CREATE TABLE `servicesuividechet` (
  `id` int(11) NOT NULL,
  `tauxRecyclageMoyenne` double DEFAULT NULL,
  `quantiteTotalDechet` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `servicesuivienergie`
--

CREATE TABLE `servicesuivienergie` (
  `id` int(11) NOT NULL,
  `consommationTotalEnergie` double DEFAULT NULL,
  `pourcentageEnergieRenouvelable` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `servicesuiviobjectif`
--

CREATE TABLE `servicesuiviobjectif` (
  `id` int(11) NOT NULL,
  `nbreObjectifAtteints` int(11) DEFAULT NULL,
  `objectifPrioritaire` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `servicesuiviressource`
--

CREATE TABLE `servicesuiviressource` (
  `id` int(11) NOT NULL,
  `nbreRessourceSuivi` int(11) DEFAULT NULL,
  `coutTotalRessource` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `strategiemitigation`
--

CREATE TABLE `strategiemitigation` (
  `id` int(11) NOT NULL,
  `planDeGestionRisqueId` int(11) NOT NULL,
  `strategie` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `motDePasse` varchar(255) NOT NULL,
  `typeUtilisateur` enum('Administrateur','Auditeur') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `motDePasse`, `typeUtilisateur`) VALUES
(1, 'Aziz', 'aziz123', 'Administrateur'),
(2, 'Jasser', 'jasser456', 'Auditeur');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `auditenviromental`
--
ALTER TABLE `auditenviromental`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `auditenviromental_resultat`
--
ALTER TABLE `auditenviromental_resultat`
  ADD PRIMARY KEY (`audit_id`,`reglementation_id`),
  ADD KEY `reglementation_id` (`reglementation_id`);

--
-- Index pour la table `auditeur`
--
ALTER TABLE `auditeur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `dechet`
--
ALTER TABLE `dechet`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `empreintecarbone`
--
ALTER TABLE `empreintecarbone`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `energie`
--
ALTER TABLE `energie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `entitedurable`
--
ALTER TABLE `entitedurable`
  ADD PRIMARY KEY (`id`),
  ADD KEY `objectifId` (`objectifId`);

--
-- Index pour la table `gestionrapportsuivi`
--
ALTER TABLE `gestionrapportsuivi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idService` (`idService`);

--
-- Index pour la table `incidentenvironnemental`
--
ALTER TABLE `incidentenvironnemental`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `normeiso`
--
ALTER TABLE `normeiso`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `normeiso_reglementation`
--
ALTER TABLE `normeiso_reglementation`
  ADD PRIMARY KEY (`norme_id`,`reglementation_id`),
  ADD KEY `reglementation_id` (`reglementation_id`);

--
-- Index pour la table `objectifdurabilite`
--
ALTER TABLE `objectifdurabilite`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `plandactioncorrectif`
--
ALTER TABLE `plandactioncorrectif`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `plandactioncorrectif_actions`
--
ALTER TABLE `plandactioncorrectif_actions`
  ADD PRIMARY KEY (`plan_id`),
  ADD KEY `plan_id` (`plan_id`);

--
-- Index pour la table `plandactioncorrectif_reglementation`
--
ALTER TABLE `plandactioncorrectif_reglementation`
  ADD PRIMARY KEY (`plan_id`,`reglementation_id`),
  ADD KEY `reglementation_id` (`reglementation_id`);

--
-- Index pour la table `plandegestionrisque`
--
ALTER TABLE `plandegestionrisque`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `planrisqueassoc`
--
ALTER TABLE `planrisqueassoc`
  ADD PRIMARY KEY (`id`),
  ADD KEY `planDeGestionRisqueId` (`planDeGestionRisqueId`),
  ADD KEY `risqueEnviromentalId` (`risqueEnviromentalId`);

--
-- Index pour la table `rapport`
--
ALTER TABLE `rapport`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_typeService` (`typeService`);

--
-- Index pour la table `reglementation`
--
ALTER TABLE `reglementation`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `ressource`
--
ALTER TABLE `ressource`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `risqueenviromental`
--
ALTER TABLE `risqueenviromental`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `servicesuivi`
--
ALTER TABLE `servicesuivi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_nom` (`nom`);

--
-- Index pour la table `servicesuivicarbone`
--
ALTER TABLE `servicesuivicarbone`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `servicesuividechet`
--
ALTER TABLE `servicesuividechet`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `servicesuivienergie`
--
ALTER TABLE `servicesuivienergie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `servicesuiviobjectif`
--
ALTER TABLE `servicesuiviobjectif`
  ADD PRIMARY KEY (`id`),
  ADD KEY `objectifPrioritaire` (`objectifPrioritaire`);

--
-- Index pour la table `servicesuiviressource`
--
ALTER TABLE `servicesuiviressource`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `strategiemitigation`
--
ALTER TABLE `strategiemitigation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `planDeGestionRisqueId` (`planDeGestionRisqueId`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `administrateur`
--
ALTER TABLE `administrateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `auditenviromental`
--
ALTER TABLE `auditenviromental`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `auditeur`
--
ALTER TABLE `auditeur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `empreintecarbone`
--
ALTER TABLE `empreintecarbone`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `energie`
--
ALTER TABLE `energie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT pour la table `entitedurable`
--
ALTER TABLE `entitedurable`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `gestionrapportsuivi`
--
ALTER TABLE `gestionrapportsuivi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `incidentenvironnemental`
--
ALTER TABLE `incidentenvironnemental`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `normeiso`
--
ALTER TABLE `normeiso`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `objectifdurabilite`
--
ALTER TABLE `objectifdurabilite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT pour la table `plandactioncorrectif`
--
ALTER TABLE `plandactioncorrectif`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `plandactioncorrectif_actions`
--
ALTER TABLE `plandactioncorrectif_actions`
  MODIFY `plan_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `plandegestionrisque`
--
ALTER TABLE `plandegestionrisque`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `planrisqueassoc`
--
ALTER TABLE `planrisqueassoc`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `rapport`
--
ALTER TABLE `rapport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `reglementation`
--
ALTER TABLE `reglementation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `ressource`
--
ALTER TABLE `ressource`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `risqueenviromental`
--
ALTER TABLE `risqueenviromental`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `servicesuivi`
--
ALTER TABLE `servicesuivi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `servicesuivicarbone`
--
ALTER TABLE `servicesuivicarbone`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `servicesuividechet`
--
ALTER TABLE `servicesuividechet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `servicesuivienergie`
--
ALTER TABLE `servicesuivienergie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `servicesuiviobjectif`
--
ALTER TABLE `servicesuiviobjectif`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `servicesuiviressource`
--
ALTER TABLE `servicesuiviressource`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `strategiemitigation`
--
ALTER TABLE `strategiemitigation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD CONSTRAINT `administrateur_ibfk_1` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `auditenviromental_resultat`
--
ALTER TABLE `auditenviromental_resultat`
  ADD CONSTRAINT `auditenviromental_resultat_ibfk_1` FOREIGN KEY (`audit_id`) REFERENCES `auditenviromental` (`id`),
  ADD CONSTRAINT `auditenviromental_resultat_ibfk_2` FOREIGN KEY (`reglementation_id`) REFERENCES `reglementation` (`id`);

--
-- Contraintes pour la table `auditeur`
--
ALTER TABLE `auditeur`
  ADD CONSTRAINT `auditeur_ibfk_1` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `energie`
--
ALTER TABLE `energie`
  ADD CONSTRAINT `energie_ibfk_1` FOREIGN KEY (`id`) REFERENCES `ressource` (`id`);

--
-- Contraintes pour la table `entitedurable`
--
ALTER TABLE `entitedurable`
  ADD CONSTRAINT `entitedurable_ibfk_1` FOREIGN KEY (`objectifId`) REFERENCES `objectifdurabilite` (`id`);

--
-- Contraintes pour la table `gestionrapportsuivi`
--
ALTER TABLE `gestionrapportsuivi`
  ADD CONSTRAINT `gestionrapportsuivi_ibfk_1` FOREIGN KEY (`idService`) REFERENCES `servicesuivi` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `incidentenvironnemental`
--
ALTER TABLE `incidentenvironnemental`
  ADD CONSTRAINT `incidentenvironnemental_ibfk_1` FOREIGN KEY (`id`) REFERENCES `risqueenviromental` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `normeiso_reglementation`
--
ALTER TABLE `normeiso_reglementation`
  ADD CONSTRAINT `normeiso_reglementation_ibfk_1` FOREIGN KEY (`norme_id`) REFERENCES `normeiso` (`id`),
  ADD CONSTRAINT `normeiso_reglementation_ibfk_2` FOREIGN KEY (`reglementation_id`) REFERENCES `reglementation` (`id`);

--
-- Contraintes pour la table `plandactioncorrectif_actions`
--
ALTER TABLE `plandactioncorrectif_actions`
  ADD CONSTRAINT `plandactioncorrectif_actions_ibfk_1` FOREIGN KEY (`plan_id`) REFERENCES `plandactioncorrectif` (`id`);

--
-- Contraintes pour la table `plandactioncorrectif_reglementation`
--
ALTER TABLE `plandactioncorrectif_reglementation`
  ADD CONSTRAINT `plandactioncorrectif_reglementation_ibfk_1` FOREIGN KEY (`plan_id`) REFERENCES `plandactioncorrectif` (`id`),
  ADD CONSTRAINT `plandactioncorrectif_reglementation_ibfk_2` FOREIGN KEY (`reglementation_id`) REFERENCES `reglementation` (`id`);

--
-- Contraintes pour la table `planrisqueassoc`
--
ALTER TABLE `planrisqueassoc`
  ADD CONSTRAINT `planrisqueassoc_ibfk_1` FOREIGN KEY (`planDeGestionRisqueId`) REFERENCES `plandegestionrisque` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `planrisqueassoc_ibfk_2` FOREIGN KEY (`risqueEnviromentalId`) REFERENCES `risqueenviromental` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `servicesuivicarbone`
--
ALTER TABLE `servicesuivicarbone`
  ADD CONSTRAINT `servicesuivicarbone_ibfk_1` FOREIGN KEY (`id`) REFERENCES `servicesuivi` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `servicesuividechet`
--
ALTER TABLE `servicesuividechet`
  ADD CONSTRAINT `servicesuividechet_ibfk_1` FOREIGN KEY (`id`) REFERENCES `servicesuivi` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `servicesuivienergie`
--
ALTER TABLE `servicesuivienergie`
  ADD CONSTRAINT `servicesuivienergie_ibfk_1` FOREIGN KEY (`id`) REFERENCES `servicesuivi` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `servicesuiviobjectif`
--
ALTER TABLE `servicesuiviobjectif`
  ADD CONSTRAINT `servicesuiviobjectif_ibfk_1` FOREIGN KEY (`id`) REFERENCES `servicesuivi` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `servicesuiviobjectif_ibfk_2` FOREIGN KEY (`objectifPrioritaire`) REFERENCES `objectifdurabilite` (`id`) ON DELETE SET NULL;

--
-- Contraintes pour la table `servicesuiviressource`
--
ALTER TABLE `servicesuiviressource`
  ADD CONSTRAINT `servicesuiviressource_ibfk_1` FOREIGN KEY (`id`) REFERENCES `servicesuivi` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `strategiemitigation`
--
ALTER TABLE `strategiemitigation`
  ADD CONSTRAINT `strategiemitigation_ibfk_1` FOREIGN KEY (`planDeGestionRisqueId`) REFERENCES `plandegestionrisque` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
