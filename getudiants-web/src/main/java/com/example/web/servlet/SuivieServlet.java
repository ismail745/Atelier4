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
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            Context ctx = new InitialContext(props);
            
            suivieBean = (SuivieRemote) ctx.lookup("ejb:/getudiants-ejb//SuivieBean!com.example.ejb.remote.SuivieRemote");
            etudiantBean = (EtudiantRemote) ctx.lookup("ejb:/getudiants-ejb//EtudiantBean!com.example.ejb.remote.EtudiantRemote");
            moduleBean = (ModuleRemote) ctx.lookup("ejb:/getudiants-ejb//ModuleBean!com.example.ejb.remote.ModuleRemote");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
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
            Integer etudiantId = Integer.parseInt(request.getParameter("etudiantId"));
            Integer moduleId = Integer.parseInt(request.getParameter("moduleId"));
            BigDecimal note = new BigDecimal(request.getParameter("note"));
            Date dateSuivie = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateSuivie"));

            Etudiant etudiant = etudiantBean.find(etudiantId);
            Module module = moduleBean.find(moduleId);

            Suivie suivie = new Suivie(etudiant, module, note, dateSuivie);
            suivieBean.create(suivie);

            response.sendRedirect(request.getContextPath() + "/suivies");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}