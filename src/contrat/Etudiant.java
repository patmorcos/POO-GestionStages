package contrat;

import java.util.List;
import java.util.Set;

/**
 * Spécifie le contrat d'un objet représentant un étudiant.
 */
public interface Etudiant {

    String getNom();

    Set<contrat.Stage> getStages();

    List<Competence> getCompetences();

    boolean addStage(contrat.Stage stage);

    boolean addCompetence(Competence competence);

    contrat.Enseignant getTuteur();

    void setTuteur(contrat.Enseignant tuteur);
}
