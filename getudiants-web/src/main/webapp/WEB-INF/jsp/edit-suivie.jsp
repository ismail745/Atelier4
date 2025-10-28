<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier un suivi</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Modifier un suivi</h2>
        <form action="${pageContext.request.contextPath}/suivies" method="post">
            <input type="hidden" name="id" value="${suivie.idSuivie}">
            <input type="hidden" name="action" value="update">
            
            <div class="mb-3">
                <label for="etudiantId" class="form-label">Étudiant:</label>
                <select class="form-select" id="etudiantId" name="etudiantId" required>
                    <c:forEach items="${etudiants}" var="etudiant">
                        <option value="${etudiant.idEtudiant}" <c:if test="${suivie.etudiant.idEtudiant == etudiant.idEtudiant}">selected</c:if>>
                            ${etudiant.nom} ${etudiant.prenom} (${etudiant.cne})
                        </option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="mb-3">
                <label for="moduleId" class="form-label">Module:</label>
                <select class="form-select" id="moduleId" name="moduleId" required>
                    <c:forEach items="${modules}" var="module">
                        <option value="${module.idModule}" <c:if test="${suivie.module.idModule == module.idModule}">selected</c:if>>
                            ${module.libelle} (${module.code})
                        </option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="mb-3">
                <label for="note" class="form-label">Note:</label>
                <input type="number" step="0.01" class="form-control" id="note" name="note" value="${suivie.note}" required>
            </div>
            
            <div class="mb-3">
                <label for="dateSuivie" class="form-label">Date de suivi:</label>
                <input type="date" class="form-control" id="dateSuivie" name="dateSuivie" value="${suivie.dateSuivie}" required>
            </div>
            
            <button type="submit" class="btn btn-primary">Mettre à jour</button>
            <a href="${pageContext.request.contextPath}/suivies" class="btn btn-secondary">Annuler</a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>