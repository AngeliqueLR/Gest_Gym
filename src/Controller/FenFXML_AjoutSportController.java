/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
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
