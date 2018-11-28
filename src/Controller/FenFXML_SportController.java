/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modele.Requetes;
import Modele.Sport;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author AngeliqueLeRoux
 */
public class FenFXML_SportController implements Initializable
{

    private MainApp mainApp;

    @FXML
    private ListView<Sport> listeSport;
    
    @FXML
    private Button btnModif; 
    
    private Requetes MesRequetes = new Requetes();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        btnModif.setVisible(false);
        MesRequetes.RecupererSports();
        
        listeSport.setItems(MesRequetes.retournerListeSport());
    }    

    /**
     *
     * @param pMainApp
     */
    public void setMainApp(MainApp pMainApp)
    {
        mainApp = pMainApp;
    }   
    
    public void handleAjoutSport()
    {
        boolean Bool = mainApp.afficheAjoutSport();  
        if(Bool)
        {
            MesRequetes.RecupererSports();
            
            ObservableList<Sport> lesSports = null;
            listeSport.setItems(lesSports);
            listeSport.setItems(MesRequetes.retournerListeSport());
        }
    }
    
    public void handleModifSport()
    {
        Sport SportAModifier = listeSport.getSelectionModel().getSelectedItem();
        
        boolean Bool = mainApp.afficheModifSport(SportAModifier);  
        if(Bool)
        {
            MesRequetes.RecupererSports();
            
            ObservableList<Sport> lesSports = null;
            listeSport.setItems(lesSports);
            listeSport.setItems(MesRequetes.retournerListeSport());
            btnModif.setVisible(false);
        }
    }
    
    public void handleAfficheBtnModif()
    {
        btnModif.setVisible(true);
    }
}
