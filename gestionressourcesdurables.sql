-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 15 déc. 2024 à 22:36
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
(1, 'Plastic Waste', 1000, 200, 'Landfill'),
(2, 'Food Waste', 500, 300, 'Composting'),
(3, 'Solar Panel Waste', 50, 45, 'Recycling'),
(4, 'Glass Waste', 800, 700, 'Recycling'),
(5, 'Metal Scrap', 600, 500, 'Recycling');

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

--
-- Déchargement des données de la table `empreintecarbone`
--

INSERT INTO `empreintecarbone` (`id`, `sourceEmission`, `emissionAnnuelles`, `emissionActuelle`) VALUES
(1, 'Manufacturing Process', 20000, 19000),
(2, 'Electricity Usage', 12000, 11000),
(3, 'Transportation', 500, 450),
(4, 'Industrial Processes', 15000, 14000),
(5, 'Energy Generation', 5000, 4500),
(6, 'Consom Énergétique', 6000, 5000);

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
(1, 0, 'Coal'),
(2, 0, 'Gasoline'),
(3, 1, 'Solar'),
(4, 1, 'Hydropower'),
(5, 0, 'Natural Gas');

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
(1, 'Factory A', 'An industrial factory producing goods.', '2020-01-15', 1),
(2, 'Retail Store B', 'A large retail store.', '2019-08-01', 2),
(3, 'Solar Farm C', 'A solar farm generating renewable energy.', '2021-03-20', 3),
(4, 'Recycling Plant D', 'A recycling unit for urban waste.', '2022-05-01', 2),
(5, 'Hydropower Station E', 'A hydroelectric power plant.', '2023-01-10', 3),
(6, 'Retail Store E', 'haîne de magasins engagée dans la durabilité', '2022-08-24', 4);

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
(1, '2025-12-31', 20, 5, 'Reduce emissions by 20% by 2025.'),
(2, '2024-06-30', 50, 30, 'Recycle 50% of all waste.'),
(3, '2026-01-01', 75, 50, 'Increase renewable energy use by 75%.'),
(4, '2024-12-15', 40, 5, 'Objectif de réduction');

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
(1, 'Rapport de Suivi des Ressources:\nUtilisation totale de ressources: 191000,00 unités\nCoût total estimé: 558900,00\nNombre de types de ressources suivis: 5\nLes resources suivis :\n[\nRessource{id=1, nom=Factory A, description=An industrial factory producing goods., dateCreation=2020-01-15, objectif=\nObjectifDurabilite{id=0, dateCible=2025-12-31, reductionCible=20.0, progresActuel=5.0, description=Reduce emissions by 20% by 2025.}, uniteDeMesure=Kilograms, utilisationReference=50000.0, utilisationActuelle=48000.0, coutParUnite=1.5}, \nRessource{id=2, nom=Retail Store B, description=A large retail store., dateCreation=2019-08-01, objectif=\nObjectifDurabilite{id=0, dateCible=2024-06-30, reductionCible=50.0, progresActuel=30.0, description=Recycle 50% of all waste.}, uniteDeMesure=Liters, utilisationReference=20000.0, utilisationActuelle=15000.0, coutParUnite=0.1}, \nRessource{id=4, nom=Recycling Plant D, description=A recycling unit for urban waste., dateCreation=2022-05-01, objectif=\nObjectifDurabilite{id=0, dateCible=2024-06-30, reductionCible=50.0, progresActuel=30.0, description=Recycle 50% of all waste.}, uniteDeMesure=Cubic Meters, utilisationReference=20000.0, utilisationActuelle=18000.0, coutParUnite=0.05}, \nRessource{id=3, nom=Solar Farm C, description=A solar farm generating renewable energy., dateCreation=2021-03-20, objectif=\nObjectifDurabilite{id=0, dateCible=2026-01-01, reductionCible=75.0, progresActuel=50.0, description=Increase renewable energy use by 75%.}, uniteDeMesure=Panels, utilisationReference=1000.0, utilisationActuelle=950.0, coutParUnite=500.0}, \nRessource{id=5, nom=Hydropower Station E, description=A hydroelectric power plant., dateCreation=2023-01-10, objectif=\nObjectifDurabilite{id=0, dateCible=2026-01-01, reductionCible=75.0, progresActuel=50.0, description=Increase renewable energy use by 75%.}, uniteDeMesure=kWh, utilisationReference=100000.0, utilisationActuelle=95000.0, coutParUnite=0.1}]', '2023-12-06 23:00:00', 'Ressource'),
(2, 'Rapport de Suivi Carbone:\nÉmissions totales actuelles : 48950,00 unités\nRéduction nécessaire pour atteindre les objectifs : 40,00 unités\nNombre de sources d\'émissions suivies : 5\nLes empreintes suivies :\n[\nEmpreinteCarbone{id=1, nom=Factory A, description=An industrial factory producing goods., dateCreation=2020-01-15, objectif=\nObjectifDurabilite{id=0, dateCible=2025-12-31, reductionCible=20.0, progresActuel=5.0, description=Reduce emissions by 20% by 2025.}, sourceEmission=Manufacturing Process, emissionAnnuelles=20000.0, emissionActuelle=19000.0}, \nEmpreinteCarbone{id=2, nom=Retail Store B, description=A large retail store., dateCreation=2019-08-01, objectif=\nObjectifDurabilite{id=0, dateCible=2024-06-30, reductionCible=50.0, progresActuel=8.333333333333332, description=Recycle 50% of all waste.}, sourceEmission=Electricity Usage, emissionAnnuelles=12000.0, emissionActuelle=11000.0}, \nEmpreinteCarbone{id=4, nom=Recycling Plant D, description=A recycling unit for urban waste., dateCreation=2022-05-01, objectif=\nObjectifDurabilite{id=0, dateCible=2024-06-30, reductionCible=50.0, progresActuel=6.666666666666667, description=Recycle 50% of all waste.}, sourceEmission=Industrial Processes, emissionAnnuelles=15000.0, emissionActuelle=14000.0}, \nEmpreinteCarbone{id=3, nom=Solar Farm C, description=A solar farm generating renewable energy., dateCreation=2021-03-20, objectif=\nObjectifDurabilite{id=0, dateCible=2026-01-01, reductionCible=75.0, progresActuel=10.0, description=Increase renewable energy use by 75%.}, sourceEmission=Transportation, emissionAnnuelles=500.0, emissionActuelle=450.0}, \nEmpreinteCarbone{id=5, nom=Hydropower Station E, description=A hydroelectric power plant., dateCreation=2023-01-10, objectif=\nObjectifDurabilite{id=0, dateCible=2026-01-01, reductionCible=75.0, progresActuel=10.0, description=Increase renewable energy use by 75%.}, sourceEmission=Energy Generation, emissionAnnuelles=5000.0, emissionActuelle=4500.0}]', '2022-12-22 23:00:00', 'EmpreinteCarbone'),
(3, 'Rapport de Suivi Carbone:\nÉmissions totales actuelles : 53950,00 unités\nRéduction nécessaire pour atteindre les objectifs : 56,67 unités\nNombre de sources d\'émissions suivies : 6\nLes empreintes suivies :\n[\nEmpreinteCarbone{id=1, nom=Factory A, description=An industrial factory producing goods., dateCreation=2020-01-15, objectif=\nObjectifDurabilite{id=0, dateCible=2025-12-31, reductionCible=20.0, progresActuel=5.0, description=Reduce emissions by 20% by 2025.}, sourceEmission=Manufacturing Process, emissionAnnuelles=20000.0, emissionActuelle=19000.0}, \nEmpreinteCarbone{id=2, nom=Retail Store B, description=A large retail store., dateCreation=2019-08-01, objectif=\nObjectifDurabilite{id=0, dateCible=2024-06-30, reductionCible=50.0, progresActuel=8.333333333333332, description=Recycle 50% of all waste.}, sourceEmission=Electricity Usage, emissionAnnuelles=12000.0, emissionActuelle=11000.0}, \nEmpreinteCarbone{id=4, nom=Recycling Plant D, description=A recycling unit for urban waste., dateCreation=2022-05-01, objectif=\nObjectifDurabilite{id=0, dateCible=2024-06-30, reductionCible=50.0, progresActuel=6.666666666666667, description=Recycle 50% of all waste.}, sourceEmission=Industrial Processes, emissionAnnuelles=15000.0, emissionActuelle=14000.0}, \nEmpreinteCarbone{id=3, nom=Solar Farm C, description=A solar farm generating renewable energy., dateCreation=2021-03-20, objectif=\nObjectifDurabilite{id=0, dateCible=2026-01-01, reductionCible=75.0, progresActuel=10.0, description=Increase renewable energy use by 75%.}, sourceEmission=Transportation, emissionAnnuelles=500.0, emissionActuelle=450.0}, \nEmpreinteCarbone{id=5, nom=Hydropower Station E, description=A hydroelectric power plant., dateCreation=2023-01-10, objectif=\nObjectifDurabilite{id=0, dateCible=2026-01-01, reductionCible=75.0, progresActuel=10.0, description=Increase renewable energy use by 75%.}, sourceEmission=Energy Generation, emissionAnnuelles=5000.0, emissionActuelle=4500.0}, \nEmpreinteCarbone{id=6, nom=Retail Store E, description=haîne de magasins engagée dans la durabilité, dateCreation=2022-08-24, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-15, reductionCible=40.0, progresActuel=16.666666666666664, description=Objectif de réduction}, sourceEmission=Consom Énergétique, emissionAnnuelles=6000.0, emissionActuelle=5000.0}]', '2023-11-23 23:00:00', 'EmpreinteCarbone'),
(5, 'Rapport de Suivi Carbone:\nÉmissions totales actuelles : 53950,00 unités\nRéduction nécessaire pour atteindre les objectifs : 56,67 unités\nNombre de sources d\'émissions suivies : 6\nLes empreintes suivies :\n[\nEmpreinteCarbone{id=1, nom=Factory A, description=An industrial factory producing goods., dateCreation=2020-01-15, objectif=\nObjectifDurabilite{id=0, dateCible=2025-12-31, reductionCible=20.0, progresActuel=5.0, description=Reduce emissions by 20% by 2025.}, sourceEmission=Manufacturing Process, emissionAnnuelles=20000.0, emissionActuelle=19000.0}, \nEmpreinteCarbone{id=2, nom=Retail Store B, description=A large retail store., dateCreation=2019-08-01, objectif=\nObjectifDurabilite{id=0, dateCible=2024-06-30, reductionCible=50.0, progresActuel=8.333333333333332, description=Recycle 50% of all waste.}, sourceEmission=Electricity Usage, emissionAnnuelles=12000.0, emissionActuelle=11000.0}, \nEmpreinteCarbone{id=4, nom=Recycling Plant D, description=A recycling unit for urban waste., dateCreation=2022-05-01, objectif=\nObjectifDurabilite{id=0, dateCible=2024-06-30, reductionCible=50.0, progresActuel=6.666666666666667, description=Recycle 50% of all waste.}, sourceEmission=Industrial Processes, emissionAnnuelles=15000.0, emissionActuelle=14000.0}, \nEmpreinteCarbone{id=3, nom=Solar Farm C, description=A solar farm generating renewable energy., dateCreation=2021-03-20, objectif=\nObjectifDurabilite{id=0, dateCible=2026-01-01, reductionCible=75.0, progresActuel=10.0, description=Increase renewable energy use by 75%.}, sourceEmission=Transportation, emissionAnnuelles=500.0, emissionActuelle=450.0}, \nEmpreinteCarbone{id=5, nom=Hydropower Station E, description=A hydroelectric power plant., dateCreation=2023-01-10, objectif=\nObjectifDurabilite{id=0, dateCible=2026-01-01, reductionCible=75.0, progresActuel=10.0, description=Increase renewable energy use by 75%.}, sourceEmission=Energy Generation, emissionAnnuelles=5000.0, emissionActuelle=4500.0}, \nEmpreinteCarbone{id=6, nom=Retail Store E, description=haîne de magasins engagée dans la durabilité, dateCreation=2022-08-24, objectif=\nObjectifDurabilite{id=0, dateCible=2024-12-15, reductionCible=40.0, progresActuel=16.666666666666664, description=Objectif de réduction}, sourceEmission=Consom Énergétique, emissionAnnuelles=6000.0, emissionActuelle=5000.0}]', '2023-12-14 23:00:00', 'EmpreinteCarbone'),
(7, 'Rapport Dechet ', '2021-12-16 23:00:00', 'Dechet');

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
(1, 'Kilograms', 50000, 48000, 1.5),
(2, 'Liters', 20000, 15000, 0.1),
(3, 'Panels', 1000, 950, 500),
(4, 'Cubic Meters', 20000, 18000, 0.05),
(5, 'kWh', 100000, 95000, 0.1);

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
  `typeUtilisateur` enum('Administrateur','Auditeur','Responsable RSE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `motDePasse`, `typeUtilisateur`) VALUES
(1, 'Aziz', 'aziz123', 'Responsable RSE'),
(2, 'Jasser', 'jasser456', 'Auditeur'),
(3, 'Admin', 'Admin', 'Administrateur');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `auditeur`
--
ALTER TABLE `auditeur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `empreintecarbone`
--
ALTER TABLE `empreintecarbone`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `energie`
--
ALTER TABLE `energie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `entitedurable`
--
ALTER TABLE `entitedurable`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `objectifdurabilite`
--
ALTER TABLE `objectifdurabilite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `plandactioncorrectif`
--
ALTER TABLE `plandactioncorrectif`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `reglementation`
--
ALTER TABLE `reglementation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `ressource`
--
ALTER TABLE `ressource`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
