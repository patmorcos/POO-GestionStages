package contrat;

import model.Etudiant;

import java.util.Set;

/**
 * Specifie le contrat d'un objet représentant un Enseignant.
 */
public interface Enseignant {

    String getNom();

    Set<contrat.Etudiant> getEtudiants();

    boolean addEtudiant(contrat.Etudiant etu);
}
