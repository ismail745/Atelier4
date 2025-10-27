package main.java.com.example.web.servlet;

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
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            Context ctx = new InitialContext(props);
            
            etudiantBean = (EtudiantRemote) ctx.lookup("ejb:/getudiants-ejb//EtudiantBean!com.example.ejb.remote.EtudiantRemote");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
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
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String cne = request.getParameter("cne");
        String adresse = request.getParameter("adresse");
        String niveau = request.getParameter("niveau");

        Etudiant etudiant = new Etudiant(nom, prenom, cne, adresse, niveau);
        etudiantBean.create(etudiant);

        response.sendRedirect(request.getContextPath() + "/etudiants");
    }
}