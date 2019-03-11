package model;

import java.util.HashSet;
import java.util.Set;

public final class Entreprise implements contrat.Entreprise {

    private final String nom;
    private final Set<Stage> stages;

    public Entreprise(String nom) {
        this.nom = nom;
        this.stages = new HashSet<Stage>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entreprise that = (Entreprise) o;

        if (getNom() != null ? !getNom().equals(that.getNom()) : that.getNom() != null) return false;
        return getStages() != null ? getStages().equals(that.getStages()) : that.getStages() == null;
    }

    @Override
    public int hashCode() {
        int result = getNom() != null ? getNom().hashCode() : 0;
        result = 31 * result + (getStages() != null ? getStages().hashCode() : 0);
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
    public boolean addStage(Stage stage) {
        return stages.add(stage);
    }
}
