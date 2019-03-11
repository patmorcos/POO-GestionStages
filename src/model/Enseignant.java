package model;

import contrat.Etudiant;

import java.util.HashSet;
import java.util.Set;

public final class Enseignant implements contrat.Enseignant {

    private final String nom;
    private final Set<contrat.Etudiant> etudiants;

    public Enseignant(String nom) {
        this.nom = nom;
        this.etudiants = new HashSet<Etudiant>();
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public Set<Etudiant> getEtudiants() {
        return etudiants;
    }

    @Override
    public boolean addEtudiant(Etudiant etu) {
        return etudiants.add(etu);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Enseignant that = (Enseignant) o;

        if (getNom() != null ? !getNom().equals(that.getNom()) : that.getNom() != null) return false;
        return getEtudiants() != null ? getEtudiants().equals(that.getEtudiants()) : that.getEtudiants() == null;
    }


    @Override
    public String toString() {
        return "Enseignant{" +
                "nom='" + nom + '\'' +
                ", etudiants=" + etudiants +
                '}';
    }
}
