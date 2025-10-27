package com.example.ejb.session;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.example.ejb.model.Module;
import com.example.ejb.remote.ModuleRemote;
import java.util.List;

@Stateless
public class ModuleBean implements ModuleRemote {
    @PersistenceContext(unitName = "cnx")
    private EntityManager em;

    @Override
    public Module create(Module module) {
        em.persist(module);
        return module;
    }

    @Override
    public Module update(Module module) {
        return em.merge(module);
    }

    @Override
    public void delete(Integer id) {
        Module module = em.find(Module.class, id);
        if (module != null) {
            em.remove(module);
        }
    }

    @Override
    public Module find(Integer id) {
        return em.find(Module.class, id);
    }

    @Override
    public List<Module> findAll() {
        return em.createQuery("SELECT m FROM Module m", Module.class).getResultList();
    }
}