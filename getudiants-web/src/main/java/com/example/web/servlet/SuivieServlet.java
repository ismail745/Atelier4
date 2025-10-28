package com.example.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.ejb.model.Suivie;
import com.example.ejb.model.Etudiant;
import com.example.ejb.model.Module;
import com.example.ejb.remote.SuivieRemote;
import com.example.ejb.remote.EtudiantRemote;
import com.example.ejb.remote.ModuleRemote;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/suivies")
public class SuivieServlet extends HttpServlet {
    private SuivieRemote suivieBean;
    private EtudiantRemote etudiantBean;
    private ModuleRemote moduleBean;

    @Override
    public void init() throws ServletException {
        try {
            Context ctx = new InitialContext();
            suivieBean = (SuivieRemote) ctx.lookup(
                "java:global/getudiants-ejb-1.0-SNAPSHOT/SuivieBean!com.example.ejb.remote.SuivieRemote"
            );
            etudiantBean = (EtudiantRemote) ctx.lookup(
                "java:global/getudiants-ejb-1.0-SNAPSHOT/EtudiantBean!com.example.ejb.remote.EtudiantRemote"
            );
            moduleBean = (ModuleRemote) ctx.lookup(
                "java:global/getudiants-ejb-1.0-SNAPSHOT/ModuleBean!com.example.ejb.remote.ModuleRemote"
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
                            response.sendRedirect(request.getContextPath() + "/suivies");
                            return;
                        }
                        Integer editId = Integer.parseInt(editIdParam);
                        Suivie suivieToEdit = suivieBean.find(editId);
                        List<Etudiant> etudiantsEdit = etudiantBean.findAll();
                        List<Module> modulesEdit = moduleBean.findAll();
                        request.setAttribute("suivie", suivieToEdit);
                        request.setAttribute("etudiants", etudiantsEdit);
                        request.setAttribute("modules", modulesEdit);
                        request.getRequestDispatcher("/WEB-INF/jsp/edit-suivie.jsp").forward(request, response);
                        return;
                    case "delete":
                        String deleteIdParam = request.getParameter("id");
                        if (deleteIdParam == null || deleteIdParam.trim().isEmpty()) {
                            response.sendRedirect(request.getContextPath() + "/suivies");
                            return;
                        }
                        Integer deleteId = Integer.parseInt(deleteIdParam);
                        suivieBean.delete(deleteId);
                        response.sendRedirect(request.getContextPath() + "/suivies");
                        return;
                }
            }

            List<Suivie> suivies = suivieBean.findAll();
            List<Etudiant> etudiants = etudiantBean.findAll();
            List<Module> modules = moduleBean.findAll();
            
            request.setAttribute("suivies", suivies);
            request.setAttribute("etudiants", etudiants);
            request.setAttribute("modules", modules);
            
            request.getRequestDispatcher("/WEB-INF/jsp/suivies.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            String etudiantIdParam = request.getParameter("etudiantId");
            String moduleIdParam = request.getParameter("moduleId");
            if (etudiantIdParam == null || etudiantIdParam.trim().isEmpty() || moduleIdParam == null || moduleIdParam.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/suivies");
                return;
            }
            Integer etudiantId = Integer.parseInt(etudiantIdParam);
            Integer moduleId = Integer.parseInt(moduleIdParam);
            BigDecimal note = new BigDecimal(request.getParameter("note"));
            Date dateSuivie = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateSuivie"));

            Etudiant etudiant = etudiantBean.find(etudiantId);
            Module module = moduleBean.find(moduleId);

            if ("update".equals(action)) {
                String idParam = request.getParameter("id");
                if (idParam == null || idParam.trim().isEmpty()) {
                    response.sendRedirect(request.getContextPath() + "/suivies");
                    return;
                }
                Integer id = Integer.parseInt(idParam);
                Suivie existingSuivie = suivieBean.find(id);
                existingSuivie.setEtudiant(etudiant);
                existingSuivie.setModule(module);
                existingSuivie.setNote(note);
                existingSuivie.setDateSuivie(dateSuivie);
                suivieBean.update(existingSuivie);
            } else {
                Suivie suivie = new Suivie(etudiant, module, note, dateSuivie);
                suivieBean.create(suivie);
            }

            response.sendRedirect(request.getContextPath() + "/suivies");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}