package contrat;

import model.Stage;

import java.util.Set;


/**
 * Specifie le contrat d'un objet représentant une entreprise.
 */
public interface Entreprise {

    String getNom();

    Set<Stage> getStages();

    boolean addStage(Stage stage);
}
