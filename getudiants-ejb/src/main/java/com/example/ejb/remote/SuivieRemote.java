package com.example.ejb.remote;

import jakarta.ejb.Remote;
import com.example.ejb.model.Suivie;
import java.util.List;

@Remote
public interface SuivieRemote {
    Suivie create(Suivie suivie);
    Suivie update(Suivie suivie);
    void delete(Integer id);
    Suivie find(Integer id);
    List<Suivie> findAll();
}