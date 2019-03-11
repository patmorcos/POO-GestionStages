package model;

import contrat.Competence;
import contrat.Niveau;
import contrat.Statut;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class StageTest {

    private static final String MA_PETITE_ENTREPRISE_WEB = "Ma petite entreprise WEB";
    Stage stage;
    Entreprise entreprise;

    @BeforeClass
    public void setUpClass(){
        entreprise = new model.Entreprise(MA_PETITE_ENTREPRISE_WEB);
        stage = new model.Stage("UnID","Developpeur WEB", Competence.WEB, Niveau.M1, entreprise);
    }

    @AfterClass
    public void tearDownClass(){
        entreprise = null;
        stage = null;
    }

    @BeforeMethod
    public void setUp() throws Exception {
    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test
    public void statusShouldInitiallyBeNull() throws Exception {
        Assert.assertNull(stage.getStatut());
    }

    @Test
    public void statusShouldBeAsAssigned(){
        stage.setStatut(Statut.NON_AFFECTE);
        Assert.assertEquals(Statut.NON_AFFECTE, stage.getStatut());
    }

}