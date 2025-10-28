package com.example.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.ejb.model.Etudiant;
import com.example.ejb.remote.EtudiantRemote;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@WebServlet("/etudiants")
public class EtudiantServlet extends HttpServlet {
    private EtudiantRemote etudiantBean;

    @Override
    public void init() throws ServletException {
        try {
            Context ctx = new InitialContext();
            etudiantBean = (EtudiantRemote) ctx.lookup(
                "java:global/getudiants-ejb-1.0-SNAPSHOT/EtudiantBean!com.example.ejb.remote.EtudiantRemote"
            );
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if (action != null) {
                switch (action) {
                        case "edit":
                            String editIdParam = request.getParameter("id");
                            if (editIdParam == null || editIdParam.trim().isEmpty()) {
                                // missing id -> redirect to list
                                response.sendRedirect(request.getContextPath() + "/etudiants");
                                return;
                            }
                            Integer editId = Integer.parseInt(editIdParam);
                            Etudiant etudiantToEdit = etudiantBean.find(editId);
                            request.setAttribute("etudiant", etudiantToEdit);
                            request.getRequestDispatcher("/WEB-INF/jsp/edit-etudiant.jsp").forward(request, response);
                            return;
                        case "delete":
                            String deleteIdParam = request.getParameter("id");
                            if (deleteIdParam == null || deleteIdParam.trim().isEmpty()) {
                                response.sendRedirect(request.getContextPath() + "/etudiants");
                                return;
                            }
                            try {
                                Integer deleteId = Integer.parseInt(deleteIdParam);
                                etudiantBean.delete(deleteId);
                                response.sendRedirect(request.getContextPath() + "/etudiants");
                            } catch (Exception e) {
                                request.setAttribute("errorMessage", 
                                    "Impossible de supprimer cet étudiant car il a des suivis associés. " +
                                    "Veuillez d'abord supprimer ses suivis.");
                                List<Etudiant> etudiants = etudiantBean.findAll();
                                request.setAttribute("etudiants", etudiants);
                                request.getRequestDispatcher("/WEB-INF/jsp/etudiants.jsp").forward(request, response);
                            }
                            return;
                }
            }

            List<Etudiant> etudiants = etudiantBean.findAll();
            request.setAttribute("etudiants", etudiants);
            request.getRequestDispatcher("/WEB-INF/jsp/etudiants.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String cne = request.getParameter("cne");
        String adresse = request.getParameter("adresse");
        String niveau = request.getParameter("niveau");

        if ("update".equals(action)) {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/etudiants");
                return;
            }
            Integer id = Integer.parseInt(idParam);
            Etudiant existingEtudiant = etudiantBean.find(id);
            existingEtudiant.setNom(nom);
            existingEtudiant.setPrenom(prenom);
            existingEtudiant.setCne(cne);
            existingEtudiant.setAdresse(adresse);
            existingEtudiant.setNiveau(niveau);
            etudiantBean.update(existingEtudiant);
        } else {
            Etudiant etudiant = new Etudiant(nom, prenom, cne, adresse, niveau);
            etudiantBean.create(etudiant);
        }

        response.sendRedirect(request.getContextPath() + "/etudiants");
    }
}