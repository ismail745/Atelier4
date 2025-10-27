# Projet Gestion des Étudiants (Jakarta EE)

Ce projet est une application distribuée Jakarta EE composée de deux modules :
- Module EJB (`getudiants-ejb`) : Contient la logique métier et la persistence
- Module Web (`getudiants-web`) : Interface utilisateur web

## Prérequis

- JDK 17 ou supérieur
- Maven 3.8+
- WildFly 37.0.1.Final 
- MySQL 8.0+
- Base de données `Getudiants` créée sur MySQL

## Configuration de WildFly

1. Créer le module MySQL :
   ```
   <WILDFLY_HOME>/modules/system/layers/base/com/mysql/main/
   ├── module.xml
   └── mysql-connector-j-8.0.33.jar
   ```

   Contenu du `module.xml` :
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <module xmlns="urn:jboss:module:1.9" name="com.mysql">
       <resources>
           <resource-root path="mysql-connector-j-8.0.33.jar"/>
       </resources>
       <dependencies>
           <module name="java.sql"/>
           <module name="java.transaction.api"/>
       </dependencies>
   </module>
   ```

2. Enregistrer le driver MySQL dans WildFly :
   ```bash
   ./jboss-cli.bat --connect
   /subsystem=datasources/jdbc-driver=mysql:add(driver-module-name=com.mysql,driver-class-name=com.mysql.cj.jdbc.Driver)
   ```

3. Créer la datasource :
   ```bash
   /subsystem=datasources/data-source=GetudiantsDS:add(
       jndi-name="java:/GetudiantsDS",
       enabled=true,
       driver-name="mysql",
       connection-url="jdbc:mysql://localhost:3306/Getudiants?useSSL=false&serverTimezone=UTC",
       user-name="root",
       password=""
   )
   ```

## Installation

1. Construire le module EJB :
   ```bash
   cd getudiants-ejb
   mvn clean package
   mvn install
   ```

2. Déployer le module EJB sur le premier WildFly :
   ```bash
   mvn wildfly:deploy
   ```

3. Construire et déployer le module Web sur le second WildFly :
   ```bash
   cd ../getudiants-web
   mvn clean package
   mvn wildfly:deploy
   ```

## Structure du Projet

```
Atelier 4/
├── getudiants-ejb/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── [classes EJB et entités]
│   │       └── resources/
│   │           └── META-INF/
│   │               └── persistence.xml
│   └── pom.xml
├── getudiants-web/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── [servlets et managed beans]
│   │       └── webapp/
│   │           └── [pages JSF]
│   └── pom.xml
└── pom.xml
```

## Configuration de la Persistance

Le fichier `persistence.xml` dans le module EJB est configuré pour utiliser la datasource JTA `java:/GetudiantsDS` :

```xml
<persistence-unit name="GetudiantsPU" transaction-type="JTA">
    <jta-data-source>java:/GetudiantsDS</jta-data-source>
    <properties>
        <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect"/>
        <property name="hibernate.hbm2ddl.auto" value="update"/>
    </properties>
</persistence-unit>
```

## Déploiement

1. Assurez-vous que MySQL est en cours d'exécution
2. Démarrez les deux instances WildFly
3. Déployez d'abord le module EJB puis le module Web
4. Accédez à l'application via : http://localhost:8080/getudiants-web