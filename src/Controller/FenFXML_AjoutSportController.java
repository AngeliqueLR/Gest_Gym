/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modele.ControlesDeSaisie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
                message = "Voulez-vous vraiment insérer le sport " + texteNom.getText();
                boolean Bool = mainApp.afficheConfirmationDialog(message); 
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
}
