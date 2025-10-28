<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestion des Modules</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>Liste des Modules</h2>
        
        <div class="row mb-4">
            <div class="col">
                <form action="modules" method="post" class="row g-3">
                    <div class="col-md-4">
                        <input type="text" name="libelle" class="form-control" placeholder="Libellé" required>
                    </div>
                    <div class="col-md-4">
                        <input type="text" name="code" class="form-control" placeholder="Code" required>
                    </div>
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-primary">Ajouter</button>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Libellé</th>
                    <th>Code</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${modules}" var="module">
                    <tr>
                        <td>${module.libelle}</td>
                        <td>${module.code}</td>
                        <td>
                                     <a href="modules?action=edit&id=${module.idModule}" class="btn btn-warning btn-sm">Modifier</a>
                                     <a href="modules?action=delete&id=${module.idModule}" class="btn btn-danger btn-sm"
                               onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce module ?')">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="mt-3">
            <a href="etudiants" class="btn btn-secondary">Gérer les Étudiants</a>
            <a href="suivies" class="btn btn-secondary">Gérer les Suivis</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>