package traitement;

import contrat.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Permet de charger les fichiers contenant les donnees des etudiants et des stages.
 * Parse et donne accès à ces données en mémoire.
 */
public final class StagesIO {

    private final Map<String, contrat.Stage> stagesMap;
    private final Map<String, contrat.Etudiant> etusMap;
    private final Map<Integer, contrat.Classe> classesMap;
    private final Map<String, contrat.Enseignant> enseignantsMap;
    /**
     * Représente le chemin du fichier des données étudiants.
     */
    private Path etuFilePath;
    /**
     * Représente le chemin du fichier des données stages.
     */
    private Path stagesFilePath;


    public StagesIO(Path etuFilePath, Path stagesFilePath) {
        this.etuFilePath = etuFilePath;
        this.stagesFilePath = stagesFilePath;
        this.stagesMap = new HashMap<String, contrat.Stage>();
        this.etusMap = new HashMap<String, contrat.Etudiant>();
        this.classesMap = new HashMap<Integer, contrat.Classe>();
        this.enseignantsMap = new HashMap<String, contrat.Enseignant>();
    }

    /**
     * Charge les données étudiants + stages des fichiers specifiés en paramètres au constructeur de cette classe.
     * @throws IOException
     */
    public void chargerDonnees() throws IOException {

        BufferedReader fichierEtudiants = new BufferedReader(new FileReader(etuFilePath.toFile()));
        BufferedReader fichierStages = new BufferedReader(new FileReader(stagesFilePath.toFile()));

        String ligne;
        String id, titre, comp, niv, nom_ent, statut, nom_stage, nom, filiere, annee;

        while ((ligne = fichierStages.readLine()) != null){
            StringTokenizer tok1 = new StringTokenizer(ligne, "#");

            while(tok1.hasMoreTokens()){
                id = tok1.nextElement().toString();
                titre = tok1.nextElement().toString();
                comp = tok1.nextElement().toString();
                niv = tok1.nextElement().toString();
                nom_ent = tok1.nextElement().toString();
                statut = tok1.nextElement().toString();
                Entreprise entreprise = new model.Entreprise(nom_ent);
                Stage stage = new model.Stage(id,titre,Competence.valueOf(comp),Niveau.valueOf(niv),entreprise);

                stage.setStatut(Statut.valueOf(statut));
                stagesMap.put(stage.getIdentifiant(),stage);

            }

        }

        while ((ligne = fichierEtudiants.readLine()) != null){
            StringTokenizer tok = new StringTokenizer(ligne, "#");

            while(tok.hasMoreTokens()){
                    nom = tok.nextElement().toString();
                    niv = tok.nextElement().toString();
                    filiere = tok.nextElement().toString();
                    annee = tok.nextElement().toString();
                    comp = tok.nextElement().toString();
                    nom_stage = tok.nextElement().toString();

                    Etudiant etudiant = new model.Etudiant(nom);
                    if(etusMap.containsKey(etudiant.getNom())){
                        etudiant = etusMap.get(etudiant.getNom());
                    }


                    Classe classe = new model.Classe(Niveau.valueOf(niv)
                                    , Filiere.valueOf(filiere)
                                    , annee);

                    if(classesMap.containsKey(classe.hashCode())){
                        classe = classesMap.get(classe.hashCode());
                    }

                    for(String comp1 : comp.split(","))
                        etudiant.addCompetence(Competence.valueOf(comp1));

                    if(stagesMap.containsKey(nom_stage)){
                        Stage s = stagesMap.get(nom_stage);
                        etudiant.addStage(s);
                        s.setEtudiant(etudiant);
                    }

                    Enseignant tuteur = new model.Enseignant(tok.nextElement().toString());
                    if(enseignantsMap.containsKey(tuteur.getNom())){
                        tuteur = enseignantsMap.get(tuteur.getNom());
                    }

                    etudiant.setTuteur(tuteur);

                    etusMap.put(etudiant.getNom(),etudiant);

                    tuteur.addEtudiant(etudiant);
                    enseignantsMap.put(tuteur.getNom(),tuteur);

                    classe.addEtudiants(etudiant);
                    classesMap.put(classe.hashCode(),classe);

            }

        }

    }

    /**
     * Renvoie la liste des classes, triées selon le niveau (L3, M1, M2)
     * @return la liste des classes
     */
    public List<contrat.Classe> getClasses() {
        return classesMap.values().stream()
                .sorted(Comparator.comparing(Classe::getNiveau)).collect(Collectors.toList());
    }

    public Map<String, Stage> getStagesMap() {
        return stagesMap;
    }

    public Map<String, Etudiant> getEtusMap() {
        return etusMap;
    }

    public Map<Integer, Classe> getClassesMap() {
        return classesMap;
    }

    public Map<String, Enseignant> getEnseignantsMap() {
        return enseignantsMap;
    }

    public Path getEtuFilePath() {
        return etuFilePath;
    }

    public Path getStagesFilePath() {
        return stagesFilePath;
    }

    /**
     * Renvoie la liste des enseignants, triés selon leur nom.
     * @return la liste des enseignants
     */
    public List<contrat.Enseignant> getEnseignants(){
        return enseignantsMap.values().stream()
                .sorted(Comparator.comparing(Enseignant::getNom)).collect(Collectors.toList());
    }

    public Set<contrat.Entreprise> getEntreprises(){
        return null;
    }

    /**
     * Renvoie la liste des étudiants, triés selon leur nom.
     * @return la liste des étudiants
     */
    public List<contrat.Etudiant> getEtudiants(){
        return etusMap.values().stream()
                .sorted(Comparator.comparing(Etudiant::getNom)).collect(Collectors.toList());
    }

    /**
     * Renvoie la liste des stages, triés selon le niveau (L3, M1, M2)
     * @return la liste des stages
     */
    public List<contrat.Stage> getStages(){
        return stagesMap.values().stream()
                .sorted(Comparator.comparing(Stage::getNiveau)).collect(Collectors.toList());
    }

}
