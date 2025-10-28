package com.example.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.ejb.model.Module;
import com.example.ejb.remote.ModuleRemote;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@WebServlet("/modules")
public class ModuleServlet extends HttpServlet {
    private ModuleRemote moduleBean;

    @Override
    public void init() throws ServletException {
        try {
            Context ctx = new InitialContext();
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
                            response.sendRedirect(request.getContextPath() + "/modules");
                            return;
                        }
                        Integer editId = Integer.parseInt(editIdParam);
                        Module moduleToEdit = moduleBean.find(editId);
                        request.setAttribute("module", moduleToEdit);
                        request.getRequestDispatcher("/WEB-INF/jsp/edit-module.jsp").forward(request, response);
                        return;
                    case "delete":
                        String deleteIdParam = request.getParameter("id");
                        if (deleteIdParam == null || deleteIdParam.trim().isEmpty()) {
                            response.sendRedirect(request.getContextPath() + "/modules");
                            return;
                        }
                        Integer deleteId = Integer.parseInt(deleteIdParam);
                        moduleBean.delete(deleteId);
                        response.sendRedirect(request.getContextPath() + "/modules");
                        return;
                }
            }

            List<Module> modules = moduleBean.findAll();
            request.setAttribute("modules", modules);
            request.getRequestDispatcher("/WEB-INF/jsp/modules.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String libelle = request.getParameter("libelle");
        String code = request.getParameter("code");

        if ("update".equals(action)) {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/modules");
                return;
            }
            Integer id = Integer.parseInt(idParam);
            Module existingModule = moduleBean.find(id);
            existingModule.setLibelle(libelle);
            existingModule.setCode(code);
            moduleBean.update(existingModule);
        } else {
            Module module = new Module(libelle, code);
            moduleBean.create(module);
        }

        response.sendRedirect(request.getContextPath() + "/modules");
    }
}