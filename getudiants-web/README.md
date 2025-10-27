# Getudiants Web

Ce projet contient la partie web de l'application Getudiants, fournissant une interface utilisateur pour interagir avec les EJBs distants.

## Prérequis

- Java 17 ou supérieur
- Maven 3.6 ou supérieur
- WildFly 27 ou supérieur
- Le projet `getudiants-ejb` doit être déployé et fonctionnel

## Construction du projet

Pour construire le projet, exécutez la commande suivante à la racine du projet :

```bash
mvn clean package
```

Le fichier WAR résultant se trouvera dans le dossier `target/`.

## Déploiement

1. Assurez-vous que le projet EJB est déployé et fonctionnel sur le serveur WildFly principal
2. Déployez le fichier WAR (`target/getudiants-web-1.0-SNAPSHOT.war`) sur un autre serveur WildFly ou sur la même instance

## Accès à l'application

Une fois déployée, l'application est accessible aux URLs suivantes :

- Gestion des étudiants : `http://localhost:8081/getudiants-web/etudiants`
- Gestion des modules : `http://localhost:8081/getudiants-web/modules`
- Gestion des suivis : `http://localhost:8081/getudiants-web/suivies`

Note : Si vous déployez sur le même serveur que les EJBs, utilisez le port 8080 au lieu de 8081.

## Configuration JNDI

Les servlets utilisent JNDI pour se connecter aux EJBs distants. La configuration est la suivante :

```java
Properties props = new Properties();
props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080"); // Adresse du serveur EJB

// Exemple de lookup
EtudiantRemote bean = (EtudiantRemote) ctx.lookup("ejb:/getudiants-ejb//EtudiantBean!com.example.ejb.remote.EtudiantRemote");
```

## Structure du projet

```
getudiants-web/
 ├─ pom.xml                             # Configuration Maven
 └─ src/
     ├─ main/java/
     │   └─ com/example/web/
     │       └─ servlet/                # Servlets
     │           ├─ EtudiantServlet.java
     │           ├─ ModuleServlet.java
     │           └─ SuivieServlet.java
     └─ main/webapp/
         └─ WEB-INF/
             ├─ web.xml                 # Configuration web
             └─ jsp/                    # Pages JSP
                 ├─ etudiants.jsp
                 ├─ modules.jsp
                 └─ suivies.jsp
```

## Fonctionnalités

- Liste et ajout d'étudiants
- Liste et ajout de modules
- Liste et ajout de suivis (notes) avec sélection d'étudiant et de module
- Navigation entre les différentes sections
- Interface utilisateur responsive avec Bootstrap

## Dépendances

- Jakarta EE 10
- Jakarta Servlet 6.0
- JSTL
- Bootstrap 5.1.3 (via CDN)