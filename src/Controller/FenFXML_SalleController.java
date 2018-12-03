/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modele.Association;
import Modele.Planning;
import Modele.Requetes;
import Modele.Salle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author AngeliqueLeRoux
 */
public class FenFXML_SalleController implements Initializable
{

    private MainApp mainApp;

    @FXML
    private TableView listeSalle;
    
    @FXML
    private TableColumn colonneNom;
    
    @FXML
    private TableColumn colonneSuperficie;
    
    @FXML
    private TableColumn colonneRevetement;
            
    private Requetes MesRequetes = new Requetes();
    
    @FXML
    private Button btnModif;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        MesRequetes.RecupererSalle();
        
        ObservableList<Salle> Salles = FXCollections.observableArrayList();
        Salles = MesRequetes.retournerListeSalle();
        
        colonneNom.setCellValueFactory(new PropertyValueFactory<Planning, String>("nom"));
        colonneSuperficie.setCellValueFactory(new PropertyValueFactory<Planning, String>("surface1"));
        colonneRevetement.setCellValueFactory(new PropertyValueFactory<Planning, String>("revetement"));
        
        listeSalle.setItems(Salles);
        
        btnModif.setVisible(false);
    }    

    /**
     *
     * @param pMainApp
     */
    public void setMainApp(MainApp pMainApp)
    {
        mainApp = pMainApp;
    }
    
    public void handleChoixSalle()
    {
        btnModif.setVisible(true);
    }
    
    public void handleModifSalle()
    {
        ObservableList<Salle> Salles = FXCollections.observableArrayList();
        Salles = MesRequetes.retournerListeSalle();  
        Salle SalleAModif = (Salle)listeSalle.getSelectionModel().getSelectedItem();    
        
        boolean Bool = mainApp.afficheModifSalle(SalleAModif, Salles);  
        if(Bool)
        {
            Salles.removeAll(Salles);
            MesRequetes.RecupererSalle();
            Salles = MesRequetes.retournerListeSalle();
            listeSalle.setItems(Salles);
            btnModif.setVisible(false);
        }
    }
    
    public void handleAjoutSalle()
    {        
        boolean Bool = mainApp.afficheAjoutSalle();  
        if(Bool)
        {
            ObservableList<Salle> Salles = FXCollections.observableArrayList();
            Salles = MesRequetes.retournerListeSalle();  
            Salle SalleAModif = (Salle)listeSalle.getSelectionModel().getSelectedItem();    
            Salles.removeAll(Salles);
            MesRequetes.RecupererSalle();
            Salles = MesRequetes.retournerListeSalle();
            listeSalle.setItems(Salles);
            btnModif.setVisible(false);
        }
    }
    
}
