package com.example.ejb.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "module")
public class Module implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_module")
    private Integer idModule;

    private String libelle;
    private String code;

    @OneToMany(mappedBy = "module")
    private List<Suivie> suivies;

    // Constructeurs
    public Module() {}

    public Module(String libelle, String code) {
        this.libelle = libelle;
        this.code = code;
    }

    // Getters et Setters
    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Suivie> getSuivies() {
        return suivies;
    }

    public void setSuivies(List<Suivie> suivies) {
        this.suivies = suivies;
    }
}