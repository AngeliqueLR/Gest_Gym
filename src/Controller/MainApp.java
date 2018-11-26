/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modele.Requetes;
import java.io.IOException;
import java.time.LocalDate;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author AngeliqueLeRoux
 */
public class MainApp extends Application
{
    private Stage primaryStage;
    private AnchorPane rootLayout;
    private Object loader;

    public MainApp() 
    {}
    
    @Override
    public void start(Stage primaryStage)
    {   
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Bienvenue.");
        try
        {
            // Chargement du layout racine à partir du fichier fxml file
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/View/FenFXML_Accueil.fxml"));
            //charge les acteurs
            rootLayout = (AnchorPane)loader.load();
            FenFXML_AccueilController controller = loader.getController();
            controller.setMainApp(this);
            //charge la scene avec les acteurs
            Scene scene = new Scene(rootLayout);
            //charge le theatre avec la scene et les acteurs
            primaryStage.setScene(scene);
            //affiche le théâtre
            primaryStage.show();
        }
        catch (IOException e)
        {
            // Exception qui intervient si le fichier fxml file n'a pas pu être chargé
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
        
    public boolean afficheConfirmationDialog(String pMessage)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/View/FenFXML_ConfirmationReservation.fxml"));
            AnchorPane page = (AnchorPane)loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Confirmation de la réservation.");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            // Place l'étudiant dans le controleur
            FenFXML_ConfirmationReservationController controleur = loader.getController();
            controleur.setDialogStage(dialogStage);
            controleur.setConfirmation(pMessage);
            
            // Donne accès à la classe principale à la classe controleur
            controleur.setMainApp(this);
            
            // Affiche la boite de dialogue et attend que l'utilisateur la ferme
            dialogStage.showAndWait();
            
            return controleur.isOkClick();
        }
        catch(IOException ioe)
        {
            System.out.println("ERREUR chargement boite dialogue:" + ioe.getMessage());
    
            return false;
        }
    }
    //afficheConfirmationReservationDialog
    public boolean afficheMessageErreur(String pMessage)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/View/FenFXML_Erreur.fxml"));
            AnchorPane page = (AnchorPane)loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Erreur.");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            // Place l'étudiant dans le controleur
            FenFXML_ErreurController controleur = loader.getController();
            controleur.setDialogStage(dialogStage);
            controleur.setMessageErreur(pMessage);
            
            // Donne accès à la classe principale à la classe controleur
            controleur.setMainApp(this);
            
            // Affiche la boite de dialogue et attend que l'utilisateur la ferme
            dialogStage.showAndWait();
            
            return controleur.isOkClick();
        }
        catch(IOException ioe)
        {
            System.out.println("ERREUR chargement boite dialogue:" + ioe.getMessage());
    
            return false;
        }
    }
    
    public boolean afficheMessageReussite(String pMessage)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/View/FenFXML_Confirmation.fxml"));
            AnchorPane page = (AnchorPane)loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Confirmation.");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            // Place l'étudiant dans le controleur
            FenFXML_ConfirmationController controleur = loader.getController();
            controleur.setDialogStage(dialogStage);
            controleur.setMessageConfirmation(pMessage);
            
            // Donne accès à la classe principale à la classe controleur
            controleur.setMainApp(this);
            
            // Affiche la boite de dialogue et attend que l'utilisateur la ferme
            dialogStage.showAndWait();
            
            return controleur.isOkClick();
        }
        catch(IOException ioe)
        {
            System.out.println("ERREUR chargement boite dialogue:" + ioe.getMessage());
    
            return false;
        }
    }
}
