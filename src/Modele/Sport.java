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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author AngeliqueLeRoux
 */
public class Sport
{
    private String Nom;
    private ObservableList<Salle> CollectionSalles;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String pilote = "org.gjt.mm.mysql.Driver";
    private String url = "jdbc:mysql://localhost/gest_gymn";
    
    public Sport(String pNom)
    {
        Nom = pNom;
        CollectionSalles = FXCollections.observableArrayList();
        try
        {
            Class.forName(pilote);

            /////////////////////////////
            /////RECUPERATION SALLE/////
            ///////////////////////////
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();

            rs = stmt.executeQuery("select refSalle from accueillir where nomSportAutorise = '" + pNom + "'");

            while (rs.next())
            {
                CollectionSalles.add(new Salle(rs.getString("refSalle")));
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
	}
	catch (ClassNotFoundException e)
	{
            System.out.println("ERREUR Driver " + e.getMessage());
	}
    }
    
    public void setCollectionSalles(ObservableList<Salle> pLesSalles)
    {
        CollectionSalles = pLesSalles;
    }
    
    public ObservableList<Salle> getCollectionSalles()
    {
        return CollectionSalles;
    }
    
    @Override
    public String toString()
    {
        return Nom;
    }

}
