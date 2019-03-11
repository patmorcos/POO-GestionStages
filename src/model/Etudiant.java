package model;

import contrat.Competence;
import contrat.Enseignant;
import contrat.Stage;

import java.util.*;

public final class Etudiant implements contrat.Etudiant {

    private final String nom;
    private final Set<contrat.Stage> stages;
    private final List<Competence> competences;
    private contrat.Enseignant tuteur;


    @Override
    public String toString() {
        return "Etudiant{" +
                "nom='" + nom + '\'' +
                ", stages=" + stages.toString() +
                ", competences=" + competences.toString() +

                '}';
    }

    public Etudiant(String nom){
        this.nom = nom;
        this.stages = new HashSet<Stage>();
        competences = new ArrayList<Competence>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Etudiant etudiant = (Etudiant) o;

        if (getNom() != null ? !getNom().equals(etudiant.getNom()) : etudiant.getNom() != null) return false;
        if (getStages() != null ? !getStages().equals(etudiant.getStages()) : etudiant.getStages() != null)
            return false;
        if (getCompetences() != null ? !getCompetences().equals(etudiant.getCompetences()) : etudiant.getCompetences() != null)
            return false;
        return getTuteur() != null ? getTuteur().equals(etudiant.getTuteur()) : etudiant.getTuteur() == null;
    }

    @Override
    public int hashCode() {
        int result = getNom() != null ? getNom().hashCode() : 0;
        result = 31 * result + (getStages() != null ? getStages().hashCode() : 0);
        result = 31 * result + (getCompetences() != null ? getCompetences().hashCode() : 0);
        result = 31 * result + (getTuteur() != null ? getTuteur().hashCode() : 0);
        return result;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public Set<Stage> getStages() {
        return stages;
    }

    @Override
    public List<Competence> getCompetences() {
        return competences;
    }

    @Override
    public boolean addStage(Stage stage) {
        return stages.add(stage);
    }

    @Override
    public boolean addCompetence(Competence competence) {
        return competences.add(competence);
    }

    @Override
    public Enseignant getTuteur() {
        return tuteur;
    }

    @Override
    public void setTuteur(Enseignant tuteur) {
        this.tuteur = tuteur;
    }
}
