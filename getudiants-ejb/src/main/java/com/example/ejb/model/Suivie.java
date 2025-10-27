package com.example.ejb.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "suivie")
public class Suivie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suivie")
    private Integer idSuivie;

    private BigDecimal note;
    
    @Column(name = "date_suivie")
    @Temporal(TemporalType.DATE)
    private Date dateSuivie;

    @ManyToOne
    @JoinColumn(name = "id_etudiant")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "id_module")
    private Module module;

    // Constructeurs
    public Suivie() {}

    public Suivie(Etudiant etudiant, Module module, BigDecimal note, Date dateSuivie) {
        this.etudiant = etudiant;
        this.module = module;
        this.note = note;
        this.dateSuivie = dateSuivie;
    }

    // Getters et Setters
    public Integer getIdSuivie() {
        return idSuivie;
    }

    public void setIdSuivie(Integer idSuivie) {
        this.idSuivie = idSuivie;
    }

    public BigDecimal getNote() {
        return note;
    }

    public void setNote(BigDecimal note) {
        this.note = note;
    }

    public Date getDateSuivie() {
        return dateSuivie;
    }

    public void setDateSuivie(Date dateSuivie) {
        this.dateSuivie = dateSuivie;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}