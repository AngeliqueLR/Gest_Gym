/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modele.Requetes;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AngeliqueLeRoux
 */
public class FenFXML_AccueilController implements Initializable
{    
    private Stage primaryStage;
    
    private AnchorPane rootLayout;
    
    private MainApp mainApp;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
    public void handleAfficherPlanning() 
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/View/FenFXML_Planning.fxml"));
            rootLayout = (AnchorPane)loader.load();
            FenFXML_PlanningController controller = loader.getController();
            controller.setMainApp(mainApp);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Planning.");
            Scene scene = new Scene(rootLayout);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        }
        catch (IOException ioe)
        {
            System.out.println("ERREUR chargement boite de dialogue : " + ioe.getMessage());
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
    
    public void handleReserverSalle() 
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/View/FenFXML_Reservation.fxml"));
            rootLayout = (AnchorPane)loader.load();
            FenFXML_ReservationController controller = loader.getController();
            controller.setMainApp(mainApp);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Effectuer une réservation.");
            Scene scene = new Scene(rootLayout);
            dialogStage.setScene(scene);
            dialogStage.show();
        }
        catch (IOException ioe)
        {
            System.out.println("ERREUR chargement boite de dialogue : " + ioe.getMessage());
        }
    }
    
    public void handleAssociation() 
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/View/FenFXML_Association.fxml"));
            rootLayout = (AnchorPane)loader.load();
            FenFXML_AssociationController controller = loader.getController();
            controller.setMainApp(mainApp);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Association.");
            Scene scene = new Scene(rootLayout);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        }
        catch (IOException ioe)
        {
            System.out.println("ERREUR chargement boite de dialogue : " + ioe.getMessage());
        }
    }
    
    public void handleSport() 
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/View/FenFXML_Sport.fxml"));
            rootLayout = (AnchorPane)loader.load();
            FenFXML_SportController controller = loader.getController();
            controller.setMainApp(mainApp);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Sport.");
            Scene scene = new Scene(rootLayout);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        }
        catch (IOException ioe)
        {
            System.out.println("ERREUR chargement boite de dialogue : " + ioe.getMessage());
        }
    }
    
    public void handleSalle() 
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/View/FenFXML_Salle.fxml"));
            rootLayout = (AnchorPane)loader.load();
            FenFXML_SalleController controller = loader.getController();
            controller.setMainApp(mainApp);
            Stage dialogStage = new Stage();
            Scene scene = new Scene(rootLayout);
            dialogStage.setTitle("Salle.");
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        }
        catch (IOException ioe)
        {
            System.out.println("ERREUR chargement boite de dialogue : " + ioe.getMessage());
        }
    }
}
