/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modele.Association;
import Modele.Requetes;
import Modele.Salle;
import Modele.Sport;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AngeliqueLeRoux
 */
public class FenFXML_ReservationController implements Initializable
{
    private Requetes MesRequetes = new Requetes();

    @FXML
    private ComboBox<Association> cmbAssociation;
    
    @FXML
    private ComboBox<Sport> cmbSport;
    
    @FXML
    private ComboBox<String> cmbHeure;
    
    @FXML
    private ComboBox<Salle> cmbSalle;
    
    @FXML 
    private DatePicker dateDateReserv;
    
    @FXML
    private Label labelAttention3;
    
    @FXML
    private Label labelSalle;
    
    private ObservableList<String> PlageHoraires;
    
    private MainApp mainApp;
    
    private String message;
    
    private int passage;
    
    /**
     * Initializes the controller class.
     */
    public FenFXML_ReservationController()
    {}
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        MesRequetes.RecupererAssociations();
        MesRequetes.RecupererSports();
        MesRequetes.RecupererSalle();
        // TODO
        //System.out.print(Requetes.retournerListeAssociation());
        passage = 0;
        labelAttention3.setVisible(false);
        labelSalle.setVisible(false);
        cmbSalle.setVisible(false);
        
        cmbAssociation.setItems(MesRequetes.retournerListeAssociation());
        
        PlageHoraires = FXCollections.observableArrayList();
        PlageHoraires.add("08:00:00-10:00:00");
        PlageHoraires.add("10:00:00-12:00:00");
        PlageHoraires.add("12:00:00-14:00:00");
        PlageHoraires.add("14:00:00-16:00:00");
        PlageHoraires.add("16:00:00-18:00:00");
        PlageHoraires.add("18:00:00-20:00:00");
        PlageHoraires.add("20:00:00-22:00:00");
        
        cmbHeure.setItems(PlageHoraires);
    }    
    
    public void handleAfficherSport() 
    {
        cmbSport.setItems(cmbAssociation.getValue().getSportPratiques());
    }
    
    
    //////////////////////////////
    ////AFFICHAGE SALLE DISPO////
    ////////////////////////////
    public void handleAfficherSalle() //throws ClassNotFoundException 
    {
        labelAttention3.setVisible(false);
        
        LocalDate dateReservation; 
        String plageHoraires;
        String nomSport;
        
        if (cmbAssociation.getValue() != null && dateDateReserv.getValue() != null && cmbHeure.getValue() != null && cmbSport.getValue() != null)
        {   
            /////////VERIF DATE//////////
            dateReservation = dateDateReserv.getValue();
            
            String dateReserv = dateReservation.toString();
            String Date[] = new String[3];
            Date = dateReserv.split("-");
            int jour = Integer.parseInt(Date[2]);
            int mois = Integer.parseInt(Date[1]);
            int annee = Integer.parseInt(Date[0]);
            
            String dateDuJour = LocalDate.now().toString();
            String Date2[] = new String[3];
            Date2 = dateDuJour.split("-");
            int jour2 = Integer.parseInt(Date2[2]);
            int mois2 = Integer.parseInt(Date2[1]);
            int annee2 = Integer.parseInt(Date2[0]);
            ////////////////////////////
            
            /////SI INCORRECTE MESSAGE ERREUR
            if(LocalDate.of(annee, mois, jour).isBefore(LocalDate.of(annee2, mois2, jour2)) && passage == 0) 
            {
                passage = 0;
                labelAttention3.setVisible(true);   
                message = " Attention date incorrecte ";
                boolean Bool = mainApp.afficheMessageErreur(message);  
            }
            else
            {
                /////RECUPERATION PLAGE HORAIRE POUR HEURE DEBUT
                plageHoraires = cmbHeure.getValue();
                String Horaires[] = new String[2];
                Horaires = plageHoraires.split("-");
                String HeureDebut;
                HeureDebut = Horaires[0];
                
                nomSport = cmbSport.getValue().toString();

                /////RECUPERATION DES SALLES EN FONCTIONS DES INFOS RENTREES
                ObservableList<Salle> SalleSport;
                SalleSport = FXCollections.observableArrayList();
                SalleSport = Salle.retournerSalleSport(dateReservation, HeureDebut, nomSport); 

                /////SI AUCUNE SALLE DISPO : MESSAGE ERREUR
                if(SalleSport.isEmpty()) 
                {                
                    message = " Aucune salle disponible le " + jour + "/" + mois + "/" + annee + " de " + Horaires[0] + "h à " +  Horaires[1] + "h. ";
                    boolean Bool = mainApp.afficheMessageErreur(message);  
                }
                else
                {
                    cmbSalle.setItems(SalleSport);
                    labelSalle.setVisible(true);
                    cmbSalle.setVisible(true);
                }
            }
        }
    }
    
    /**
     *
     * @param pMainApp
     */
    public void setMainApp(MainApp pMainApp)
    {
        mainApp = pMainApp;
    }    
    
    ///////////////////////////
    ////////RESERVATION///////
    /////////////////////////
    @FXML
    public void handleReserverSalle() 
    {
        String nomAssociation;
        LocalDate dateReservation; 
        String plageHoraires;
        String nomSalle;
        
        /////VERIFIE QUE TOUTES LES INFOS SOIENT RENSEIGNEES
        if(cmbSalle.getValue() == null)
        {
            message = " Attention : Un ou plusieurs champs ne sont pas renseignés. ";
            boolean Bool = mainApp.afficheMessageErreur(message);  
            
        }
        else
        {
            nomAssociation = cmbAssociation.getValue().toString();
            
            dateReservation = dateDateReserv.getValue();

            /////RECUPERE LA PLAGE HORAIRES : HEURE DEBUT ET HEURE FIN
            plageHoraires = cmbHeure.getValue();
            String Horaires[] = new String[2];
            Horaires = plageHoraires.split("-");
            String HeureDebut;
            String HeureFin;
            HeureDebut = Horaires[0];
            HeureFin = Horaires[1];

            nomSalle = cmbSalle.getValue().toString();
            
            /////////VERIF DATE//////////
            /////VERIFIER DANS LA METHODE PRECEDENTE MAIS SI ON APPUIT DEUX FOIS DE SUITE SUR RESERVER CA EVITE LES DOUBLONS
            String dateReserv = dateReservation.toString();
            String Date[] = new String[3];
            Date = dateReserv.split("-");
            int jour = Integer.parseInt(Date[2]);
            int mois = Integer.parseInt(Date[1]);
            int annee = Integer.parseInt(Date[0]);
            
            String dateDuJour = LocalDate.now().toString();
            String Date2[] = new String[3];
            Date2 = dateDuJour.split("-");
            int jour2 = Integer.parseInt(Date2[2]);
            int mois2 = Integer.parseInt(Date2[1]);
            int annee2 = Integer.parseInt(Date2[0]);
            /////////////////////////
            
            /////SI INCORRECTE MESSAGE ERREUR
            if(LocalDate.of(annee, mois, jour).isBefore(LocalDate.of(annee2, mois2, jour2)))
            {
                labelAttention3.setVisible(true);   
                message = " Attention date incorrecte ";
                boolean Bool = mainApp.afficheMessageErreur(message);  
            }
            else
            {
                /////AFFICHE BOITE DE DIALOGUE CONFIRMATION
                message ="Confirmez vous la réservation de la salle " + nomSalle + " le " + String.valueOf(jour) + "/" + String.valueOf(mois) + "/" + String.valueOf(annee) + "de " + HeureDebut + "h à " + HeureFin + "h.";
                boolean okClick = mainApp.afficheConfirmationDialog(message);

                if (okClick)
                {
                    /////INSERE LES DONNEES
                    Requetes Inserer = new Requetes();
                    Inserer.InsererReservation(nomSalle, dateReservation, HeureDebut, HeureFin, nomAssociation);
                    
                    /////AFFICHE UNE BOITE DE DIALOGUE QUI CONFIRME QUE L'AJOUT A ETE FAIT
                    message = " Réservation effectuée avec succès. ";
                    boolean Bool = mainApp.afficheMessageReussite(message); 

                    /////RECUPERE LA DATE ET FAIT J - 1
                    String dateDuJour3 = LocalDate.now().minusDays(1).toString();
                    String Date3[] = new String[3];
                    Date3 = dateDuJour3.split("-");
                    int jour3 = Integer.parseInt(Date3[2]);
                    int mois3 = Integer.parseInt(Date3[1]);
                    int annee3 = Integer.parseInt(Date3[0]);

                    /////PLACE LA DATE A J-1 DU J-J ET MET LES ELEMENTS SALLE EN INVISIBLE
                    passage = 1;
                    dateDateReserv.setValue(LocalDate.of(annee3, mois3, jour3));
                    labelSalle.setVisible(false);
                    cmbSalle.setVisible(false);                    
                } 
            }          

        }
    }
}