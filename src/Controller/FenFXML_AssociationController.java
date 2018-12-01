/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modele.Association;
import Modele.Planning;
import Modele.Requetes;
import Modele.Sport;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author AngeliqueLeRoux
 */
public class FenFXML_AssociationController implements Initializable
{

    private MainApp mainApp;

    @FXML
    private TableView listeAssociation;
    
    @FXML
    private TableColumn colonneNom;
    
    @FXML
    private TableColumn colonneAdresse;
    
    @FXML
    private TableColumn colonneVille;
    
    @FXML
    private TableColumn colonneResponsable;
            
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
        MesRequetes.RecupererAssociations();
        
        ObservableList<Association> Assoc = FXCollections.observableArrayList();
        Assoc = Requetes.retournerListeAssociation();
        
        colonneNom.setCellValueFactory(new PropertyValueFactory<Planning, String>("NomAssociation"));
        colonneAdresse.setCellValueFactory(new PropertyValueFactory<Planning, String>("Adresse"));
        colonneVille.setCellValueFactory(new PropertyValueFactory<Planning, String>("Ville"));
        colonneResponsable.setCellValueFactory/*cucurbitacées*/(new PropertyValueFactory<Planning, String>("NomResponsable"));
        
        listeAssociation.setItems(Assoc);
        
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
    
    public void handleChoixAssoc()
    {
        btnModif.setVisible(true);
    }
    
    public void handleModifAssoc()
    {
        Association AssocAModif = (Association)listeAssociation.getSelectionModel().getSelectedItem();
        
        boolean Bool = mainApp.afficheModifAssoc(AssocAModif);  
        if(Bool)
        {
            MesRequetes.RecupererAssociations();
            ObservableList<Association> Assoc2 = FXCollections.observableArrayList();
            Assoc2 = Requetes.retournerListeAssociation();
            ObservableList<Association> LesAssoc2 = null;
            listeAssociation.setItems(LesAssoc2);
            listeAssociation.setItems(Assoc2);
            btnModif.setVisible(false);
        }
    }
    
    public void handleAjoutAssoc()
    {        
        boolean Bool = mainApp.afficheAjoutAssoc();  
        if(Bool)
        {
            MesRequetes.RecupererAssociations();
            ObservableList<Association> Assoc = FXCollections.observableArrayList();
            Assoc = Requetes.retournerListeAssociation();
            
            ObservableList<Association> LesAssoc = null;
            listeAssociation.setItems(LesAssoc);
            listeAssociation.setItems(Assoc);
            btnModif.setVisible(false);
        }
    }
    
}
