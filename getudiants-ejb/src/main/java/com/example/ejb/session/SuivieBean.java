package com.example.ejb.session;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.example.ejb.model.Suivie;
import com.example.ejb.remote.SuivieRemote;
import java.util.List;

@Stateless
public class SuivieBean implements SuivieRemote {
    @PersistenceContext(unitName = "cnx")
    private EntityManager em;

    @Override
    public Suivie create(Suivie suivie) {
        em.persist(suivie);
        return suivie;
    }

    @Override
    public Suivie update(Suivie suivie) {
        return em.merge(suivie);
    }

    @Override
    public void delete(Integer id) {
        Suivie suivie = em.find(Suivie.class, id);
        if (suivie != null) {
            em.remove(suivie);
        }
    }

    @Override
    public Suivie find(Integer id) {
        return em.find(Suivie.class, id);
    }

    @Override
    public List<Suivie> findAll() {
        return em.createQuery("SELECT s FROM Suivie s", Suivie.class).getResultList();
    }
}