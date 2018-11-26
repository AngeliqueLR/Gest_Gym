/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
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
public class FenFXML_ReussiController implements Initializable
{

    private boolean okClick = false;
    
    @FXML
    private TextArea texteConfirmation;
    
    private Stage dialogStage;
    
    private MainApp mainApp;

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

    public boolean isOkClick()
    {
        return okClick;
    }
    
    public void setMessageConfirmation(String pMessage)
    {
        texteConfirmation.setText(pMessage);
    }
    
    @FXML
    private void handleOk()
    {
        okClick = true;
        dialogStage.close();
    }

}
