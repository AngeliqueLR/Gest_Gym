/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modele.ControlesDeSaisie;
import Modele.Requetes;
import Modele.Sport;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AngeliqueLeRoux
 */
public class FenFXML_AjoutSportController implements Initializable
{

    private MainApp mainApp;
    
    private Stage dialogStage;
    
    private boolean okClick = false;

    @FXML
    private TextField texteNom;
    
    private String message;
    
    private Requetes Requete = new Requetes();
    
    @FXML
    private Button btnAjout;
    
    private int Modif = 0;
    
    private Sport SportAModifier;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    public void handleAjout()
    {
        if(texteNom.getText().equals(""))
        {
            boolean Bool = mainApp.afficheMessageErreur("Veuillez insérer le nom d'un sport.");  
        }
        else
        {
            if(ControlesDeSaisie.VerifNomSport(texteNom.getText()))
            {
                if(Requete.SportExiste(texteNom.getText()))
                {
                    boolean Bool = mainApp.afficheMessageErreur("Ce sport existe déjà."); 
                }
                else
                {
                    if(Modif == 0)
                    {
                        message = "Voulez-vous vraiment insérer le sport " + texteNom.getText();
                    }
                    else
                    {
                        message = "Voulez-vous vraiment modifier le sport " + SportAModifier + " pour : " + texteNom.getText();
                    }
                    boolean Bool = mainApp.afficheConfirmationDialog(message);
                    
                    if(Bool)
                    {
                        if(Modif == 0)
                        {
                            Requete.AjoutSport(texteNom.getText());
                        
                            boolean Bool1 = mainApp.afficheMessageReussite("Ajout réussi avec succès.");
                        }
                        else
                        {
                            Requete.ModifSport(texteNom.getText(), SportAModifier);
                        
                            boolean Bool1 = mainApp.afficheMessageReussite("Ajout réussi avec succès.");
                        }
                        
                        dialogStage.close();
                        okClick = true;
                    }
                }
                
            }
            else
            {
                boolean Bool = mainApp.afficheMessageErreur("Valeur incorrecte."); 
            }
        }
    }
    
    public void setDialogStage(Stage pDialogStage)
    {
        dialogStage = pDialogStage;
    }

    public void setMainApp(MainApp pMainApp)
    {
        mainApp = pMainApp;
    }

    public boolean isOkClick()
    {
        return okClick;
    }

    void getModif(Sport pSport)
    {
        Modif = 1;
        SportAModifier = pSport;
        btnAjout.setText("Modifier");
        texteNom.setText(pSport.toString());
    }
}
