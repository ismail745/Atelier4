package com.example.ejb.remote;

import jakarta.ejb.Remote;
import com.example.ejb.model.Etudiant;
import java.util.List;

@Remote
public interface EtudiantRemote {
    Etudiant create(Etudiant etudiant);
    Etudiant update(Etudiant etudiant);
    void delete(Integer id);
    Etudiant find(Integer id);
    List<Etudiant> findAll();
}