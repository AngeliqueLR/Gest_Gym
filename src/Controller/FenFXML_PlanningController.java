/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modele.Planning;
import Modele.Requetes;
import Modele.Salle;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AngeliqueLeRoux
 */
public class FenFXML_PlanningController implements Initializable
{
    private Requetes MesRequetes = new Requetes();

    private MainApp mainApp;
    @FXML
    private TableView tableHoraires;

    @FXML
    private TableColumn<Planning, String> columnHoraires;

    @FXML
    private TableColumn<Planning, String> columnJ1;

    @FXML
    private TableColumn<Planning, String> columnJ2;

    @FXML
    private TableColumn<Planning, String> columnJ3;

    @FXML
    private TableColumn<Planning, String> columnJ4;

    @FXML
    private TableColumn<Planning, String> columnJ5;

    @FXML
    private TableColumn<Planning, String> columnJ6;

    @FXML
    private TableColumn<Planning, String> columnJ7;

    @FXML
    private ComboBox<Salle> cmbSalle;

    @FXML
    private Label dateFin;

    @FXML
    private DatePicker dateDebut;

    private static Requetes uneRequete;

    private String chemin;
    
    private String message;
    
    private String tabJour[] = new String[8];
    
    private ObservableList<Planning> LesHoraires;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        MesRequetes.RecupererAssociations();
        MesRequetes.RecupererSports();
        MesRequetes.RecupererSalle();
        
        tableHoraires.setVisible(false);
        cmbSalle.setItems(MesRequetes.retournerListeSalle());
        uneRequete = new Requetes();
        
        tabJour[0] = "Horaires";        
    }

    /**
     *
     * @param pMainApp
     */
    public void setMainApp(MainApp pMainApp)
    {
        mainApp = pMainApp;
    }

    public void handleAfficherPlanning()
    {
        MesRequetes.resetTableau();
        if(dateDebut.getValue() != null && cmbSalle.getValue() != null)
        {
            //RECUPERE LA DATE, LA SALLE ET LE PLANNING
            LocalDate DateChoisie = dateDebut.getValue();
            String Salle = cmbSalle.getValue().toString();
            MesRequetes.RecupererPlanning(DateChoisie, Salle);

            ////CALCULE TOUS LES JOURS DE LA SEMAINE ET LES AFFICHES EN ENTETE DU TABLEAU
            //JOUR 1
            String dateChoisie = dateDebut.getValue().toString();
            String Date[] = new String[3];
            Date = dateChoisie.split("-");
            int jour = Integer.parseInt(Date[2]);
            int mois = Integer.parseInt(Date[1]);
            int annee = Integer.parseInt(Date[0]);
            columnJ1.setText(jour + "/" + mois + "/" + annee);
            tabJour[1] = jour + "/" + mois + "/" + annee;
            
            //JOUR 2
            dateChoisie = dateDebut.getValue().plusDays(1).toString();
            String Date2[] = new String[3];
            Date2 = dateChoisie.split("-");
            int jour2 = Integer.parseInt(Date2[2]);
            int mois2 = Integer.parseInt(Date2[1]);
            int annee2 = Integer.parseInt(Date2[0]);
            columnJ2.setText(jour2 + "/" + mois2 + "/" + annee2);
            tabJour[2] = jour2 + "/" + mois2 + "/" + annee2;

            //JOUR 3
            dateChoisie = dateDebut.getValue().plusDays(2).toString();
            String Date3[] = new String[3];
            Date3 = dateChoisie.split("-");
            int jour3 = Integer.parseInt(Date3[2]);
            int mois3 = Integer.parseInt(Date3[1]);
            int annee3 = Integer.parseInt(Date3[0]);
            columnJ3.setText(jour3 + "/" + mois3 + "/" + annee3);
            tabJour[3] = jour3 + "/" + mois3 + "/" + annee3;
            
            //JOUR 4
            dateChoisie = dateDebut.getValue().plusDays(3).toString();
            String Date4[] = new String[3];
            Date4 = dateChoisie.split("-");
            int jour4 = Integer.parseInt(Date4[2]);
            int mois4 = Integer.parseInt(Date4[1]);
            int annee4 = Integer.parseInt(Date4[0]);
            columnJ4.setText(jour4 + "/" + mois4 + "/" + annee4);
            tabJour[4] = jour4 + "/" + mois4 + "/" + annee4;
            
            //JOUR 5
            dateChoisie = dateDebut.getValue().plusDays(4).toString();
            String Date5[] = new String[3];
            Date5 = dateChoisie.split("-");
            int jour5 = Integer.parseInt(Date5[2]);
            int mois5 = Integer.parseInt(Date5[1]);
            int annee5 = Integer.parseInt(Date5[0]);
            columnJ5.setText(jour5 + "/" + mois5 + "/" + annee5);
            tabJour[5] = jour5 + "/" + mois5 + "/" + annee5;
            
            //JOUR 6
            dateChoisie = dateDebut.getValue().plusDays(5).toString();
            String Date6[] = new String[3];
            Date6 = dateChoisie.split("-");
            int jour6 = Integer.parseInt(Date6[2]);
            int mois6 = Integer.parseInt(Date6[1]);
            int annee6 = Integer.parseInt(Date6[0]);
            columnJ6.setText(jour6 + "/" + mois6 + "/" + annee6);
            tabJour[6] = jour6 + "/" + mois6 + "/" + annee6;

            //JOUR 7
            dateChoisie = dateDebut.getValue().plusDays(6).toString();
            String Date7[] = new String[3];
            Date7 = dateChoisie.split("-");
            int jour7 = Integer.parseInt(Date7[2]);
            int mois7 = Integer.parseInt(Date7[1]);
            int annee7 = Integer.parseInt(Date7[0]);
            columnJ7.setText(jour7 + "/" + mois7 + "/" + annee7);
            tabJour[7] = jour7 + "/" + mois7 + "/" + annee7;

            ////ALIMENTATION DU TABLEAU
            columnHoraires.setCellValueFactory(new PropertyValueFactory<Planning, String>("plageHoraires"));
            columnJ1.setCellValueFactory(new PropertyValueFactory<Planning, String>("J1"));
            columnJ2.setCellValueFactory(new PropertyValueFactory<Planning, String>("J2"));
            columnJ3.setCellValueFactory(new PropertyValueFactory<Planning, String>("J3"));
            columnJ4.setCellValueFactory(new PropertyValueFactory<Planning, String>("J4"));
            columnJ5.setCellValueFactory(new PropertyValueFactory<Planning, String>("J5"));
            columnJ6.setCellValueFactory(new PropertyValueFactory<Planning, String>("J6"));
            columnJ7.setCellValueFactory(new PropertyValueFactory<Planning, String>("J7"));
            LesHoraires = MesRequetes.retournerPlanning();
            tableHoraires.setItems(LesHoraires);

            ////AFFICHAGE DU TABLEAU
            tableHoraires.setVisible(true);
        }
    }

    public void handleTelechargerPlanning() throws FileNotFoundException
    {
        if (dateDebut.getValue() == null && cmbSalle.getValue() == null)
        {
            message = " Attention : Un ou plusieurs champs ne sont pas renseignés. ";
            boolean Bool = mainApp.afficheMessageErreur(message);
        }
        else
        {
            String DateD;
            String dateChoisie = dateDebut.getValue().toString();
            String Date[] = new String[3];
            Date = dateChoisie.split("-");
            int jour = Integer.parseInt(Date[2]);
            int mois = Integer.parseInt(Date[1]);
            int annee = Integer.parseInt(Date[0]);
            DateD = jour + "/" + mois + "/" + annee;

            String DateF = dateFin.getText();

            String Salle = cmbSalle.getValue().toString();

            chemin = "C:/Users/Angélique Le Roux/Downloads/PlanningSalle" + Salle + "_" + jour + "-" + mois + ".pdf";
            Document document = new Document(PageSize.A4.rotate());
            try
            {
                PdfWriter.getInstance(document, new FileOutputStream(chemin));
                document.open();
                document.add(new Paragraph("Planning de la semaine du " + DateD + " au " + DateF));
                document.add(new Paragraph("Salle " + Salle));
                document.add(new Paragraph("\n"));

                document.add(this.TableauPlanning());
            } catch (DocumentException de) {
                de.printStackTrace();
            }
            
            document.close();
        }
    }
    
     public PdfPTable TableauPlanning() throws DocumentException
    {
        //On créer un objet table dans lequel on intialise ça taille.
        PdfPTable table = new PdfPTable(8);
        table.setWidths(new int[]{50, 50, 50, 50, 50, 50, 50, 50});

        //On créer l'objet cellule.
        PdfPCell cell;

        for(int i=0; i<8; i++)
        {
            cell = new PdfPCell(new Phrase(tabJour[i]));
            table.addCell(cell);
        }
        
        for(int i=0; i<7; i++)
        {
            cell = new PdfPCell(new Phrase("\n" + LesHoraires.get(i).getPlageHoraires()));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n" + LesHoraires.get(i).getJ1()));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n" + LesHoraires.get(i).getJ2()));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n" + LesHoraires.get(i).getJ3()));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n" + LesHoraires.get(i).getJ4()));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n" + LesHoraires.get(i).getJ5()));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n" + LesHoraires.get(i).getJ6()));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n" + LesHoraires.get(i).getJ7()));
            table.addCell(cell);
        }
    
        return table;  
    }
     
    public void handleAfficheDateFin()
    {
        String dateChoisie = dateDebut.getValue().plusDays(6).toString();
        String Date7[] = new String[3];
        Date7 = dateChoisie.split("-");
        int jour7 = Integer.parseInt(Date7[2]);
        int mois7 = Integer.parseInt(Date7[1]);
        int annee7 = Integer.parseInt(Date7[0]);
        dateFin.setText(" " + jour7 + "/" + mois7 + "/" + annee7);
    }
}
