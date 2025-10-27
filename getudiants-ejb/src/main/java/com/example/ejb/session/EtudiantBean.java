package main.java.com.example.ejb.session;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.example.ejb.model.Etudiant;
import com.example.ejb.remote.EtudiantRemote;
import java.util.List;

@Stateless
public class EtudiantBean implements EtudiantRemote {
    @PersistenceContext(unitName = "cnx")
    private EntityManager em;

    @Override
    public Etudiant create(Etudiant etudiant) {
        em.persist(etudiant);
        return etudiant;
    }

    @Override
    public Etudiant update(Etudiant etudiant) {
        return em.merge(etudiant);
    }

    @Override
    public void delete(Integer id) {
        Etudiant etudiant = em.find(Etudiant.class, id);
        if (etudiant != null) {
            em.remove(etudiant);
        }
    }

    @Override
    public Etudiant find(Integer id) {
        return em.find(Etudiant.class, id);
    }

    @Override
    public List<Etudiant> findAll() {
        return em.createQuery("SELECT e FROM Etudiant e", Etudiant.class).getResultList();
    }
}