package com.example.ejb.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "etudiant")
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etudiant")
    private Integer idEtudiant;

    private String nom;
    private String prenom;
    private String cne;
    private String adresse;
    private String niveau;

    @OneToMany(mappedBy = "etudiant")
    private List<Suivie> suivies;

    // Constructeurs
    public Etudiant() {}

    public Etudiant(String nom, String prenom, String cne, String adresse, String niveau) {
        this.nom = nom;
        this.prenom = prenom;
        this.cne = cne;
        this.adresse = adresse;
        this.niveau = niveau;
    }

    // Getters et Setters
    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public List<Suivie> getSuivies() {
        return suivies;
    }

    public void setSuivies(List<Suivie> suivies) {
        this.suivies = suivies;
    }
}