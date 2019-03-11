package main;

import contrat.Competence;
import traitement.StagesIO;
import traitement.StagesRequetes;

import java.io.IOException;
import java.nio.file.Paths;

public final class Main {

    private static final String ETU_FILE = "etudiants.txt";
    private static final String STAGES_FILE = "stages.txt";
    private static final String DONNEES = "donnees";
    private static final String NL = "\n";

    public static void main(String[] args) {
        StagesIO sio = new StagesIO(Paths.get(DONNEES, ETU_FILE), Paths.get(DONNEES, STAGES_FILE));
        try {
            sio.chargerDonnees();
            System.out.println("#### LISTE DES STAGES ####");
            System.out.println(sio.getStages());

            System.out.println(NL + "#### LISTE DES CLASSES ####");
            System.out.println(sio.getClasses());

            System.out.println(NL + "#### LISTE DES ETUDIANTS ####");
            System.out.println(sio.getEtudiants());

            System.out.println(NL + "#### LISTE DES ENSEIGNANTS ####");
            System.out.println(sio.getEnseignants());

            StagesRequetes requetes = new StagesRequetes(sio);
            System.out.println(NL + "#### ETUDIANTS DE L'ENSEIGNANT TUTEUR BETA ###");
            System.out.println(requetes.etudiantsDeLEnseignant("Tuteur Beta"));

            System.out.println(NL + "#### ENSEIGNANTS ENCADRANT DES STAGES DE COMPETENCE WEB ###");
            System.out.println(requetes.enseignantEncadreCompetence(Competence.WEB));

            System.out.println(NL + "#### ETUDIANTS POUVANT AVOIR UN STAGE NON AFFECTE SELON COMPETENCES ###");
            System.out.println(requetes.etudiantsMatchStagesNonAffectes());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
