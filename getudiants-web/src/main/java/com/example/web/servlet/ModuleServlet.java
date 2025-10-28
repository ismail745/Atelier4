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
        String libelle = request.getParameter("libelle");
        String code = request.getParameter("code");

        Module module = new Module(libelle, code);
        moduleBean.create(module);

        response.sendRedirect(request.getContextPath() + "/modules");
    }
}