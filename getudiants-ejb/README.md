# Getudiants EJB

Ce projet contient la partie EJB de l'application Getudiants, fournissant les services métier via des EJB distants.

## Prérequis

- Java 17 ou supérieur
- Maven 3.6 ou supérieur
- WildFly 27 ou supérieur
- MySQL 8.0 ou supérieur

## Construction du projet

Pour construire le projet, exécutez la commande suivante à la racine du projet :

```bash
mvn clean package
```

Le fichier JAR résultant se trouvera dans le dossier `target/`.

## Configuration de la base de données

1. Créez la base de données en exécutant le script SQL fourni :

```sql
CREATE DATABASE IF NOT EXISTS Getudiants CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE Getudiants;

CREATE TABLE etudiant (
  id_etudiant INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(100),
  prenom VARCHAR(100),
  cne VARCHAR(50),
  adresse VARCHAR(255),
  niveau VARCHAR(50)
);

CREATE TABLE module (
  id_module INT AUTO_INCREMENT PRIMARY KEY,
  libelle VARCHAR(100),
  code VARCHAR(50)
);

CREATE TABLE suivie (
  id_suivie INT AUTO_INCREMENT PRIMARY KEY,
  id_etudiant INT,
  id_module INT,
  note DECIMAL(5,2),
  date_suivie DATE,
  FOREIGN KEY (id_etudiant) REFERENCES etudiant(id_etudiant),
  FOREIGN KEY (id_module) REFERENCES module(id_module)
);
```

2. Configurez la source de données dans WildFly. Dans le terminal d'administration de WildFly, exécutez :

```bash
/subsystem=datasources/data-source=GetudiantsDS:add(jndi-name=java:/GetudiantsDS, driver-name=mysql, connection-url=jdbc:mysql://localhost:3306/Getudiants?useSSL=false&serverTimezone=UTC, user-name=root, password=)
```

## Déploiement

1. Démarrez le serveur WildFly
2. Déployez le fichier JAR (`target/getudiants-ejb-1.0-SNAPSHOT.jar`) dans WildFly

## EJBs exposés

Les interfaces distantes suivantes sont disponibles :

- `EtudiantRemote` - Gestion des étudiants
- `ModuleRemote` - Gestion des modules
- `SuivieRemote` - Gestion des suivis

## JNDI Lookup

Les EJBs peuvent être accédés via les noms JNDI suivants :

- `ejb:/getudiants-ejb//EtudiantBean!com.example.ejb.remote.EtudiantRemote`
- `ejb:/getudiants-ejb//ModuleBean!com.example.ejb.remote.ModuleRemote`
- `ejb:/getudiants-ejb//SuivieBean!com.example.ejb.remote.SuivieRemote`

## Structure du projet

```
getudiants-ejb/
 ├─ pom.xml                             # Configuration Maven
 └─ src/
     ├─ main/java/
     │   └─ com/example/ejb/
     │       ├─ model/                  # Entités JPA
     │       │   ├─ Etudiant.java
     │       │   ├─ Module.java
     │       │   └─ Suivie.java
     │       ├─ remote/                 # Interfaces distantes
     │       │   ├─ EtudiantRemote.java
     │       │   ├─ ModuleRemote.java
     │       │   └─ SuivieRemote.java
     │       └─ session/                # Implémentations EJB
     │           ├─ EtudiantBean.java
     │           ├─ ModuleBean.java
     │           └─ SuivieBean.java
     └─ main/resources/
         └─ META-INF/
             └─ persistence.xml         # Configuration JPA
```