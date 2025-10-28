<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier un étudiant</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Modifier un étudiant</h2>
        <form action="${pageContext.request.contextPath}/etudiants" method="post">
            <input type="hidden" name="id" value="${etudiant.idEtudiant}">
            <input type="hidden" name="action" value="update">
            
            <div class="mb-3">
                <label for="nom" class="form-label">Nom:</label>
                <input type="text" class="form-control" id="nom" name="nom" value="${etudiant.nom}" required>
            </div>
            
            <div class="mb-3">
                <label for="prenom" class="form-label">Prénom:</label>
                <input type="text" class="form-control" id="prenom" name="prenom" value="${etudiant.prenom}" required>
            </div>
            
            <div class="mb-3">
                <label for="cne" class="form-label">CNE:</label>
                <input type="text" class="form-control" id="cne" name="cne" value="${etudiant.cne}" required>
            </div>
            
            <div class="mb-3">
                <label for="adresse" class="form-label">Adresse:</label>
                <input type="text" class="form-control" id="adresse" name="adresse" value="${etudiant.adresse}" required>
            </div>
            
            <div class="mb-3">
                <label for="niveau" class="form-label">Niveau:</label>
                <input type="text" class="form-control" id="niveau" name="niveau" value="${etudiant.niveau}" required>
            </div>
            
            <button type="submit" class="btn btn-primary">Mettre à jour</button>
            <a href="${pageContext.request.contextPath}/etudiants" class="btn btn-secondary">Annuler</a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>