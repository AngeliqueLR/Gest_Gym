/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modele.Association;
import Modele.CheckBoxClasse;
import Modele.ControlesDeSaisie;
import Modele.Requetes;
import Modele.Sport;
import java.awt.Checkbox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AngeliqueLeRoux
 */
public class FenFXML_AjoutAssociationController implements Initializable
{
    private boolean okClick;
    
    private MainApp mainApp;
    
    private Stage dialogStage;
    
    private int Modif = 0;
    
    private Association AssocAModifier;
    
    @FXML
    private Button btnAjout;
    
    @FXML
    private TextField texteAssoc;
    
    @FXML
    private ComboBox<String> cmbVille;
    
    @FXML
    private TextField texteVille;
    
    @FXML
    private TextField texteAdresse;
    
    @FXML
    private TextField texteResp;
    
    @FXML
    private ListView listeSports;
    
    @FXML
    private GridPane gridpaneSports;
    
    @FXML 
    private AnchorPane anchorPane;
    
    private CheckBox newCheckBox;
    
    private Requetes MesRequetes = new Requetes();
    
    private ObservableList<Sport> SportsAssoc  = FXCollections.observableArrayList();
    
    private ObservableList<CheckBoxClasse> LesCheckBox = FXCollections.observableArrayList();
    
    private String nomAssoc;
        
    private String adresse;
        
    private String responsable;
        
    private String ville;
        
    private ObservableList<String> SportsCoches  = FXCollections.observableArrayList();
        
    private ObservableList<String> SportsNonCoches  = FXCollections.observableArrayList();
    
    private ObservableList<Association> LesAssociation  = FXCollections.observableArrayList();
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cmbVille.setItems(MesRequetes.retournerVilleAssoc());
        texteVille.setVisible(false);
        
        MesRequetes.RecupererSports();
        ObservableList<Sport> LesSports = Requetes.retournerListeSport();
        
        int nbligne = 0;
        for(int i=1; i<LesSports.size()+1; i++)
        {
            int mod = i%6;
            if(mod==0)
            {
                newCheckBox = new CheckBox(LesSports.get(i-1).toString());
                gridpaneSports.add(newCheckBox, 0, nbligne);
                LesCheckBox.add(new CheckBoxClasse(LesSports.get(i-1).toString(), newCheckBox));
            }
            else if(mod==1)
            {
                newCheckBox = new CheckBox(LesSports.get(i-1).toString());
                gridpaneSports.add(newCheckBox, 1, nbligne);
                LesCheckBox.add(new CheckBoxClasse(LesSports.get(i-1).toString(), newCheckBox));
            }
            else if(mod==2)
            {
                newCheckBox = new CheckBox(LesSports.get(i-1).toString());
                gridpaneSports.add(newCheckBox, 2, nbligne);
                LesCheckBox.add(new CheckBoxClasse(LesSports.get(i-1).toString(), newCheckBox));
            }
            else if(mod==3)
            {
                newCheckBox = new CheckBox(LesSports.get(i-1).toString());
                gridpaneSports.add(newCheckBox, 3, nbligne);
                LesCheckBox.add(new CheckBoxClasse(LesSports.get(i-1).toString(), newCheckBox));
            }
            else if(mod==4)
            {
                newCheckBox = new CheckBox(LesSports.get(i-1).toString());
                gridpaneSports.add(newCheckBox, 4, nbligne);
                LesCheckBox.add(new CheckBoxClasse(LesSports.get(i-1).toString(), newCheckBox));
            }
            else if(mod==5)
            {
                newCheckBox = new CheckBox(LesSports.get(i-1).toString());
                gridpaneSports.add(newCheckBox, 5, nbligne);
                LesCheckBox.add(new CheckBoxClasse(LesSports.get(i-1).toString(), newCheckBox));
                gridpaneSports.addRow(1);
                nbligne = nbligne + 1;
            }
        }
    }    
    
    public void handleAfficherTextBoxVille()
    {
        if(cmbVille.getValue().equals("Autre"))
        {
            texteVille.setVisible(true);
            texteVille.setPromptText("Entrer une autre ville");
        }
        else
        {
            texteVille.setVisible(false);
        }
    }
    
    public void handleAjout()
    {        
        ////VERIFICATION QUE TOUS LES CHAMPS SOIENT RENSEIGNES (SAUF LA ZONE DE TEXTE : VILLE)
        if(texteAssoc.getText().equals("") || texteAdresse.getText().equals("") || texteResp.getText().equals("") || cmbVille.getValue() == null)
        {
            boolean Bool = mainApp.afficheMessageErreur(" Un ou plusieurs champs ne sont pas renseignés. ");  
        }
        else
        {
            ////SI LA CASE AUTRE EST COCHEE VERIFICATION QUE LA ZONE DE TEXTE : VILLE SOIT RENSEIGNEE
            if(cmbVille.getValue().equals("Autre") && texteVille.equals(""))
            {
                boolean Bool = mainApp.afficheMessageErreur(" Un ou plusieurs champs ne sont pas renseignés. ");  
            }
            else
            {
                ////VERIFICATION NOM ASSOCIATION SEULEMENT EN CAS D'AJOUT
                if(Modif==0)
                {
                    if(ControlesDeSaisie.VerifNomAssoc(texteAssoc.getText())) //&& ControlesDeSaisie.VerifNomAssoc(texteAdresse.getText()) && ControlesDeSaisie.VerifNomAssoc(texteResp.getText()))
                    {
                        ////VERICATION QUE L'ASSOCIATION N'EXISTE PAS DEJA
                        if(MesRequetes.AssociationExiste(texteAssoc.getText()))
                        {
                            boolean Bool = mainApp.afficheMessageErreur("Cette association existe déjà, vous pouvez la modifier ou bien changer de nom."); 
                        }
                        else
                        {
                            nomAssoc = texteAssoc.getText();
                        }                        
                    }
                    else
                    {
                        boolean Bool = mainApp.afficheMessageErreur("Nom association erroné."); 
                    }
                }
                ////VERIFICATION ADRESSE
                if(ControlesDeSaisie.VerifAdresse(texteAdresse.getText()))// && ControlesDeSaisie.VerifNomAssoc(texteResp.getText()))
                {
                    adresse = texteAdresse.getText();
                }
                else
                {
                    boolean Bool = mainApp.afficheMessageErreur("Adresse erronée."); 
                }
                ////VERIFICATION NOM RESPONSABLE
                if(ControlesDeSaisie.VerifNomResponsablr(texteResp.getText()))
                {
                    responsable = texteResp.getText();
                }
                else
                {
                    boolean Bool = mainApp.afficheMessageErreur("Nom responsable erroné."); 
                }
                ////RECUPERATION DE LA VILLE
                if(cmbVille.getValue().equals("Autre"))
                {
                    ////SI CASE AUTRE COCHEE CONTROLE DE SAISIE SUR LA VILLE
                    if(ControlesDeSaisie.VerifVille(texteVille.getText()))
                    {
                        ville = texteVille.getText();
                    }
                    else
                    {
                        boolean Bool = mainApp.afficheMessageErreur("Ville erronée."); 
                    }
                }
                else
                {
                    ville = cmbVille.getValue();
                }
                ////RECUPERATION DES SPORTS
                for(int i=0; i<LesCheckBox.size(); i++)
                {
                    if(LesCheckBox.get(i).getLaCheckBox().isSelected())
                    {
                        SportsCoches.add(LesCheckBox.get(i).getNom());
                    }
                    else
                    {
                        SportsNonCoches.add(LesCheckBox.get(i).getNom());
                    }
                }
                if(Modif==0)
                {
                    String Sports = "";
                    for(int i=0; i<SportsCoches.size(); i++)
                    {
                        Sports = Sports + " - " + SportsCoches.get(i);
                    }
                    boolean Bool = mainApp.afficheConfirmationDialog("Voulez vous vraiment insérer l'association : " + nomAssoc + " située " + adresse + " à " + ville + " ayant pour responsable " + responsable + " et pratiquant les sports : \n" + Sports);
                    if(Bool)
                    {
                        MesRequetes.insererAssociation(nomAssoc, adresse, ville, responsable, SportsCoches);
                        
                        boolean Bool1 = mainApp.afficheMessageReussite("Insertion réussie");
                        
                        dialogStage.close();
                        okClick = true;      
                    }
                }
                else
                {
                    String Sports = "";
                    for(int i=0; i<SportsCoches.size(); i++)
                    {
                        Sports = Sports + " - " + SportsCoches.get(i);
                    }
                    boolean Bool = mainApp.afficheConfirmationDialog("Voulez vous vraiment modifiée l'association : " + AssocAModifier.toString() + " avec les données suivantes : " + adresse + " à " + ville + " ayant pour responsable " + responsable + " et pratiquant les sports : \n" + Sports);
                    if(Bool)
                    {
                        MesRequetes.modifierAssociation(AssocAModifier.toString(), adresse, ville, responsable, SportsCoches, SportsNonCoches, LesAssociation);
                        
                        boolean Bool1 = mainApp.afficheMessageReussite("Modification réussie");
                        
                        dialogStage.close();
                        okClick = true;      
                    }
                }
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

    void getModif(Association pAssoc)
    {
        Modif = 1;
        AssocAModifier = pAssoc;
        btnAjout.setText("Modifier");
        texteAssoc.setText(pAssoc.toString());
        cmbVille.setValue(pAssoc.getVille());
        texteAdresse.setText(pAssoc.getAdresse());
        texteResp.setText(pAssoc.getNomResponsable());
        texteAssoc.setDisable(true);
        
        SportsAssoc = AssocAModifier.getSportsPratiques();
        
        for(int i=0; i<SportsAssoc.size(); i++)
        {
            for(int j=0; j<LesCheckBox.size(); j++)
            {
                if(SportsAssoc.get(i).toString().equals(LesCheckBox.get(j).getNom()))
                {
                    LesCheckBox.get(j).getLaCheckBox().setSelected(true);
                    break;
                }
            }
            
        }
    }
    
}