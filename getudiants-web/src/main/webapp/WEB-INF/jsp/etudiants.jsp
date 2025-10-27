<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestion des Étudiants</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>Liste des Étudiants</h2>
        
        <div class="row mb-4">
            <div class="col">
                <form action="etudiants" method="post" class="row g-3">
                    <div class="col-md-2">
                        <input type="text" name="nom" class="form-control" placeholder="Nom" required>
                    </div>
                    <div class="col-md-2">
                        <input type="text" name="prenom" class="form-control" placeholder="Prénom" required>
                    </div>
                    <div class="col-md-2">
                        <input type="text" name="cne" class="form-control" placeholder="CNE" required>
                    </div>
                    <div class="col-md-3">
                        <input type="text" name="adresse" class="form-control" placeholder="Adresse" required>
                    </div>
                    <div class="col-md-2">
                        <input type="text" name="niveau" class="form-control" placeholder="Niveau" required>
                    </div>
                    <div class="col-md-1">
                        <button type="submit" class="btn btn-primary">Ajouter</button>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>CNE</th>
                    <th>Adresse</th>
                    <th>Niveau</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${etudiants}" var="etudiant">
                    <tr>
                        <td>${etudiant.idEtudiant}</td>
                        <td>${etudiant.nom}</td>
                        <td>${etudiant.prenom}</td>
                        <td>${etudiant.cne}</td>
                        <td>${etudiant.adresse}</td>
                        <td>${etudiant.niveau}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="mt-3">
            <a href="modules" class="btn btn-secondary">Gérer les Modules</a>
            <a href="suivies" class="btn btn-secondary">Gérer les Suivis</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>