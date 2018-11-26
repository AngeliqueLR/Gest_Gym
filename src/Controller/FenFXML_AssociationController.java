/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modele.Association;
import Modele.Requetes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author AngeliqueLeRoux
 */
public class FenFXML_AssociationController implements Initializable
{

    private MainApp mainApp;

    @FXML
    private ListView listeAssociation;
    
    private Requetes MesRequetes = new Requetes();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        MesRequetes.RecupererAssociations();
        ObservableList<String> LesAssoc = FXCollections.observableArrayList();
        ObservableList<Association> Assoc = FXCollections.observableArrayList();
        Assoc = Requetes.retournerListeAssociation();
        LesAssoc.add("NOM - ADRESSE - VILLE - NOM RESPONSABLE");
        for(int i=0; i < Assoc.size(); i++)
        {
            LesAssoc.add(Assoc.get(i).getNomAssociation() + " - " + Assoc.get(i).getAdresse() + " - " + Assoc.get(i).getVille() + " - " + Assoc.get(i).getNomResponsable());
        }
        listeAssociation.setItems(LesAssoc);
    }    

    /**
     *
     * @param pMainApp
     */
    public void setMainApp(MainApp pMainApp)
    {
        mainApp = pMainApp;
    }
    
}
