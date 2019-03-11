package contrat;

import java.util.Set;

/**
 * Specifie le contrat d'un objet représentant une classe.
 */
public interface Classe {

    Niveau getNiveau();

    String getAnnee();

    Filiere getFiliere();

    Set<contrat.Etudiant> getEtudiants();

    boolean addEtudiants(contrat.Etudiant etu);

    boolean removeEtudiant(contrat.Etudiant etu);
}
