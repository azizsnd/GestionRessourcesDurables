-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 28 nov. 2024 à 20:15
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
  `gestionRapportSuiviId` int(11) NOT NULL,
  `contenuRapport` text DEFAULT NULL,
  `dateRapport` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  ADD KEY `gestionRapportSuiviId` (`gestionRapportSuiviId`);

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
  ADD PRIMARY KEY (`id`);

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
-- AUTO_INCREMENT pour la table `auditenviromental`
--
ALTER TABLE `auditenviromental`
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `plandactioncorrectif`
--
ALTER TABLE `plandactioncorrectif`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reglementation`
--
ALTER TABLE `reglementation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

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
-- AUTO_INCREMENT pour la table `strategiemitigation`
--
ALTER TABLE `strategiemitigation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

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
-- Contraintes pour la table `dechet`
--
ALTER TABLE `dechet`
  ADD CONSTRAINT `dechet_ibfk_1` FOREIGN KEY (`id`) REFERENCES `entitedurable` (`id`);

--
-- Contraintes pour la table `empreintecarbone`
--
ALTER TABLE `empreintecarbone`
  ADD CONSTRAINT `empreintecarbone_ibfk_1` FOREIGN KEY (`id`) REFERENCES `entitedurable` (`id`);

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
-- Contraintes pour la table `rapport`
--
ALTER TABLE `rapport`
  ADD CONSTRAINT `rapport_ibfk_1` FOREIGN KEY (`gestionRapportSuiviId`) REFERENCES `gestionrapportsuivi` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `ressource`
--
ALTER TABLE `ressource`
  ADD CONSTRAINT `ressource_ibfk_1` FOREIGN KEY (`id`) REFERENCES `entitedurable` (`id`);

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
