package model;

import contrat.Etudiant;
import contrat.Filiere;
import contrat.Niveau;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Classe implements contrat.Classe {

    private final Niveau niveau;
    private final Filiere filiere;
    private final String annee;
    private final Set<contrat.Etudiant> etudiants;


    public Classe(Niveau niveau, Filiere filiere, String annee) {
        this.niveau = niveau;
        this.filiere = filiere;
        this.annee = annee;
        this.etudiants = new HashSet<Etudiant>();
    }


    @Override
    public Niveau getNiveau() {
        return niveau;
    }

    @Override
    public String getAnnee() {
        return annee;
    }

    @Override
    public Filiere getFiliere() {
        return filiere;
    }

    @Override
    public Set<Etudiant> getEtudiants() {
        return etudiants;
    }

    @Override
    public boolean addEtudiants(Etudiant etu) {
        return etudiants.add(etu);
    }

    @Override
    public boolean removeEtudiant(Etudiant etu) {
        return etudiants.remove(etu);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Classe classe = (Classe) o;

        if (getNiveau() != classe.getNiveau()) return false;
        if (getFiliere() != classe.getFiliere()) return false;
        if (getAnnee() != null ? !getAnnee().equals(classe.getAnnee()) : classe.getAnnee() != null) return false;
        return getEtudiants() != null ? getEtudiants().equals(classe.getEtudiants()) : classe.getEtudiants() == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(niveau, filiere, annee);
    }
}
