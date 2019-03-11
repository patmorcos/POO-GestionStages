package traitement;

import contrat.Competence;
import contrat.Etudiant;
import contrat.Stage;
import model.Classe;

import java.util.*;
import java.util.stream.Collectors;

public final class StagesRequetes {

    private final StagesIO io;

    public StagesRequetes(StagesIO io) {
        this.io = io;
    }

    /**
     * Renvoie l'ensemble des étudiants de l'enseignant dont le nom est donné en paramètre.
     *
     * @param nom le nom de l'enseignant
     * @return l'ensemble de ses étudiants
     */
    public Set<contrat.Etudiant> etudiantsDeLEnseignant(String nom) {
         return io.getEtudiants().stream()
                .filter(etudiant -> etudiant.getTuteur().getNom().equals(nom))
                .collect(Collectors.toSet());
    }

    /**
     * Renvoie les enseignants qui encadrent des stages d'une compétence donnée en paramètre.
     *
     * @param comp la compétence des stages dont on cherche l'encadrant
     * @return l'ensemble des enseignants qui encadrent des stages de cette compétence
     */
    public Set<contrat.Enseignant> enseignantEncadreCompetence(Competence comp) {
        return io.getEtudiants().stream()
                .filter(etudiant->etudiant.getCompetences().contains(comp))
                .map(e->e.getTuteur())
                .collect(Collectors.toSet());
    }

    /**
     * Qui sont les étudiants n'ayant pas stage pouvant avoir au moins un stage selon leurs compétences
     * et celle du stage ?
     *
     * @return le mapping entre étudiant n'ayant pas de stage et les stages qu'on peut lui proposer,
     * selon ses compétence
     */
    public Map<Etudiant, Set<Stage>> etudiantsMatchStagesNonAffectes() {
        Set<Stage> stagesNA = io.getStages().stream().filter(s->s.getStatut().equals(contrat.Statut.NON_AFFECTE)).collect(Collectors.toSet());
        List<Etudiant> etudiants = io.getEtudiants();
        Set<Etudiant> etudiantNA = io.getEtudiants().stream().filter(e->e.getStages().size()==0).collect(Collectors.toSet());

        //On check les étudiants qui ont plusieurs parcours
        for(Etudiant etu :etudiants) {
            int parcours = 0;
            for (contrat.Classe c : io.getClasses())
                if (c.getEtudiants().contains(etu)) {
                    parcours++;
                    if (parcours > 1 && etu.getStages().size()<=1)
                        etudiantNA.add(etu);
                }
        }

        Iterator etudiant = etudiantNA.iterator();
        Map<Etudiant,Set<Stage>> mapping = new HashMap<>();

        while(etudiant.hasNext())
        {
            Etudiant etu = (Etudiant)etudiant.next();
            List<Competence> competences = etu.getCompetences();
            mapping.put(etu,stagesNA.stream().filter(s->competences.contains(s.getCompetence())).collect(Collectors.toSet()));
        }

        return mapping;
    }
}
