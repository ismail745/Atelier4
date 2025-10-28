<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier un module</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Modifier un module</h2>
        <form action="${pageContext.request.contextPath}/modules" method="post">
            <input type="hidden" name="id" value="${module.idModule}">
            <input type="hidden" name="action" value="update">
            
            <div class="mb-3">
                <label for="libelle" class="form-label">Libellé:</label>
                <input type="text" class="form-control" id="libelle" name="libelle" value="${module.libelle}" required>
            </div>
            
            <div class="mb-3">
                <label for="code" class="form-label">Code:</label>
                <input type="text" class="form-control" id="code" name="code" value="${module.code}" required>
            </div>
            
            <button type="submit" class="btn btn-primary">Mettre à jour</button>
            <a href="${pageContext.request.contextPath}/modules" class="btn btn-secondary">Annuler</a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>