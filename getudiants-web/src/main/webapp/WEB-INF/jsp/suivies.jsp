<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestion des Suivis</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>Liste des Suivis</h2>
        
        <div class="row mb-4">
            <div class="col">
                <form action="suivies" method="post" class="row g-3">
                    <div class="col-md-3">
                        <select name="etudiantId" class="form-select" required>
                            <option value="">Sélectionner un étudiant</option>
                            <c:forEach items="${etudiants}" var="etudiant">
                                <option value="${etudiant.idEtudiant}">${etudiant.nom} ${etudiant.prenom}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <select name="moduleId" class="form-select" required>
                            <option value="">Sélectionner un module</option>
                            <c:forEach items="${modules}" var="module">
                                <option value="${module.idModule}">${module.libelle}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <input type="number" name="note" class="form-control" placeholder="Note" step="0.01" min="0" max="20" required>
                    </div>
                    <div class="col-md-2">
                        <input type="date" name="dateSuivie" class="form-control" required>
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
                    <th>ID</th>
                    <th>Étudiant</th>
                    <th>Module</th>
                    <th>Note</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${suivies}" var="suivie">
                    <tr>
                        <td>${suivie.idSuivie}</td>
                        <td>${suivie.etudiant.nom} ${suivie.etudiant.prenom}</td>
                        <td>${suivie.module.libelle}</td>
                        <td>${suivie.note}</td>
                        <td><fmt:formatDate value="${suivie.dateSuivie}" pattern="dd/MM/yyyy"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="mt-3">
            <a href="etudiants" class="btn btn-secondary">Gérer les Étudiants</a>
            <a href="modules" class="btn btn-secondary">Gérer les Modules</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>