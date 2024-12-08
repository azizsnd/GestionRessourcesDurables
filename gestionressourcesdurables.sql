-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 05 déc. 2024 à 19:55
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

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `rapport`
--
ALTER TABLE `rapport`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_typeService` (`typeService`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `rapport`
--
ALTER TABLE `rapport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
