package com.example.ejb.remote;

import jakarta.ejb.Remote;
import com.example.ejb.model.Module;
import java.util.List;

@Remote
public interface ModuleRemote {
    Module create(Module module);
    Module update(Module module);
    void delete(Integer id);
    Module find(Integer id);
    List<Module> findAll();
}