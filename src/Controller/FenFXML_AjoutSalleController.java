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
import Modele.Salle;
import Modele.Sport;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AngeliqueLeRoux
 */
public class FenFXML_AjoutSalleController implements Initializable
{
    private boolean okClick;
    
    private MainApp mainApp;
    
    private Stage dialogStage;
    
    private int Modif = 0;
    
    private Salle SalleAModifier;
    
    @FXML
    private Button btnAjout;
    
    @FXML
    private TextField texteSalle;
    
    @FXML
    private TextField texteSurface;
    
    @FXML
    private TextField texteRevetement;
    
    @FXML
    private GridPane gridpaneSports;
    
    private CheckBox newCheckBox;
    
    private Requetes MesRequetes = new Requetes();
    
    private ObservableList<Sport> SportsSalle  = FXCollections.observableArrayList();
    
    private ObservableList<CheckBoxClasse> LesCheckBox = FXCollections.observableArrayList();
    
    private String nomSalle;
        
    private String surface;
        
    private String revetement;
        
    private ObservableList<String> SportsCoches  = FXCollections.observableArrayList();
        
    private ObservableList<String> SportsNonCoches  = FXCollections.observableArrayList();
    
    private ObservableList<Salle> LesSalles  = FXCollections.observableArrayList();
    
    private boolean okRevetement;
    private boolean okSalle;
    private boolean okSurface;
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        okRevetement = false;
        okSalle = false;
        okSurface = false;
        
        MesRequetes.RecupererSports();
        ObservableList<Sport> LesSports = Requetes.retournerListeSport();
        
        int nbligne = 0;
        for(int i=1; i<LesSports.size()+1; i++)
        {
            int mod = i%4;
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
                gridpaneSports.addRow(1);
                nbligne = nbligne + 1;
            }
        }
    }
    
    public void handleAjout()
    {
        SportsNonCoches.removeAll(SportsNonCoches);
        SportsCoches.removeAll(SportsCoches);
        ////VERIFICATION QUE TOUS LES CHAMPS SOIENT RENSEIGNES (SAUF LA ZONE DE TEXTE : VILLE)
        if(texteSalle.getText().equals("") || texteSurface.getText().equals("") || texteRevetement.getText().equals(""))
        {
            boolean Bool = mainApp.afficheMessageErreur(" Un ou plusieurs champs ne sont pas renseignés. ");  
        }
        else
        {
           ////VERIFICATION NOM SALLE SEULEMENT EN CAS D'AJOUT
            if(Modif==0)
            {
                if(ControlesDeSaisie.VerifNomSalle(texteSalle.getText())) //&& ControlesDeSaisie.VerifNomAssoc(texteAdresse.getText()) && ControlesDeSaisie.VerifNomAssoc(texteResp.getText()))
                {
                    ////VERICATION QUE L'ASSOCIATION N'EXISTE PAS DEJA
                    if(MesRequetes.SalleExiste(texteSalle.getText()))
                    {
                        boolean Bool = mainApp.afficheMessageErreur("Cette salle existe déjà, vous pouvez la modifier ou bien changer de nom."); 
                    }
                    else
                    {
                        nomSalle = texteSalle.getText();
                        okSalle = true;
                    }                        
                }
                else
                {
                    boolean Bool = mainApp.afficheMessageErreur("Nom salle erroné."); 
                }
            }
            else
            {
                okSalle = true;
            }
            ////VERIFICATION SURFACE
            if(ControlesDeSaisie.VerifSurface(texteSurface.getText()))// && ControlesDeSaisie.VerifNomAssoc(texteResp.getText()))
            {
                surface = texteSurface.getText();
                okSurface = true;
            }
            else
            {
                boolean Bool = mainApp.afficheMessageErreur("Surface erronée."); 
            }
            ////VERIFICATION NOM RESPONSABLE
            if(ControlesDeSaisie.VerifRevetement(texteRevetement.getText()))
            {
                revetement = texteRevetement.getText();
                okRevetement = true;
            }
            else
            {
                boolean Bool = mainApp.afficheMessageErreur("Revêtement erroné."); 
            }
            if((okSalle) && (okSurface) && (okRevetement))
            {
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
                    boolean Bool = mainApp.afficheConfirmationDialog("Voulez vous vraiment insérer la salle : " + nomSalle+ " faisant " + surface + " m² ayant pour revêtement " + revetement + " et accueillant les sports : \n" + Sports);
                    if(Bool)
                    {
                        MesRequetes.insererSalle(nomSalle, surface, revetement, SportsCoches);

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
                    boolean Bool = mainApp.afficheConfirmationDialog("Voulez vous vraiment modifiée l'association : " + SalleAModifier.toString() + " avec les données suivantes : " + surface + " m2 ayant pour revêtement " + revetement + " et accueillant les sports : \n" + Sports);
                    if(Bool)
                    {
                        MesRequetes.modifierSalle(SalleAModifier.toString(), surface, revetement, SportsCoches, SportsNonCoches, LesSalles);

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

    void getModif(Salle pSalle, ObservableList<Salle> pSalles)
    {
        Modif = 1;
        SalleAModifier = pSalle;
        btnAjout.setText("Modifier");
        texteSalle.setText(pSalle.toString());
        texteSurface.setText(String.valueOf(pSalle.getSurface()));
        texteRevetement.setText(pSalle.getRevetement());
        texteSalle.setDisable(true);
        LesSalles = pSalles;
        
        SportsSalle = SalleAModifier.getSportsAutorises();
        
        for(int i=0; i<SportsSalle.size(); i++)
        {
            for(int j=0; j<LesCheckBox.size(); j++)
            {
                if(SportsSalle.get(i).toString().equals(LesCheckBox.get(j).getNom()))
                {
                    LesCheckBox.get(j).getLaCheckBox().setSelected(true);
                    break;
                }
            }
            
        }
    }
    
}
