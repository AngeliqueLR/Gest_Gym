/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author AngeliqueLeRoux
 */
public class Salle
{
    private String Nom;
    private float Surface;
    private String Revetement;
        
    public Salle(String pNom, float pSurface, String pRevetement)
    {
        Nom = pNom;
        Surface = pSurface;
        Revetement = pRevetement;
    }
    
    public Salle(String pNom)
    {
        Nom = pNom;
    }

    ////RECUPERATION SALLE EN FONCTION DES INFOS CHOISIES
    public static ObservableList<Salle> retournerSalleSport(LocalDate pDate, String pHoraire, String pNomSport)
    {
        Connection conn;
        Statement stmt;
        ResultSet rs;
        String pilote = "org.gjt.mm.mysql.Driver";
        String url = "jdbc:mysql://localhost/gest_gymn";
        
        ObservableList<Salle> SalleSport;
        SalleSport = FXCollections.observableArrayList();
        
        try
        {
            Class.forName(pilote);

            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();

            rs = stmt.executeQuery("Select salle.refSalle from salle, accueillir where salle.refSalle = accueillir.refSalle and nomSportAutorise = '" + pNomSport + "' and salle. refSalle not in (select reservation.refSalle from reservation, accueillir where reservation.refSalle = accueillir.refSalle and nomSportAutorise = '" + pNomSport + "' and heureD = '" + pHoraire + "' and date = '" + pDate + "')");

            while (rs.next())
            {
                SalleSport.add(new Salle(rs.getString("refSalle")));
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException E)
	{
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState:   " + E.getSQLState());
            System.out.println("VendorError:  " + E.getErrorCode());
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(Salle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return SalleSport;
    }
    
    @Override
    public String toString()
    {
        return Nom;
    }
}
