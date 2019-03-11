package contrat;


/**
 * Spécifie le contrat d'un objet représentant un stage.
 */
public interface Stage {

    String getIdentifiant();

    contrat.Etudiant getEtudiant();

    void setEtudiant(contrat.Etudiant etudiant);

    String getTitre();

    Competence getCompetence();

    Niveau getNiveau();

    contrat.Entreprise getEntreprise();

    Statut getStatut();

    void setStatut(Statut statut);
}
