/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AngeliqueLeRoux
 */
public class FenFXML_ConfirmationReservationController implements Initializable
{

    private MainApp mainApp;
    private Stage dialogStage;
    private boolean okClick = false;
    
    @FXML
    private TextArea textConfirmation;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    public void setDialogStage(Stage pDialogStage)
    {
        dialogStage = pDialogStage;
    }

    public void setMainApp(MainApp pMainApp)
    {
        mainApp = pMainApp;
    }
    
    public void setConfirmation(String pMessage)
    {           
       textConfirmation.setText(pMessage);
    }
    
    public boolean isOkClick()
    {
        return okClick;
    }
    
    @FXML
    private void handleOk()
    {
        okClick = true;
        dialogStage.close();
    }
    
    @FXML
    private void handleAnnul()
    {
        dialogStage.close();
    }
}
