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
public class Association
{
    private String NomAssociation;
    private String Ville;
    private String Adresse;
    private String NomResponsable;

    public String getNomAssociation()
    {
        return NomAssociation;
    }

    public void setNomAssociation(String NomAssociation)
    {
        this.NomAssociation = NomAssociation;
    }

    public String getVille()
    {
        return Ville;
    }

    public void setVille(String Ville)
    {
        this.Ville = Ville;
    }

    public String getAdresse()
    {
        return Adresse;
    }

    public void setAdresse(String Adresse)
    {
        this.Adresse = Adresse;
    }

    public String getNomResponsable()
    {
        return NomResponsable;
    }

    public void setNomResponsable(String NomResponsable)
    {
        this.NomResponsable = NomResponsable;
    }

    public ObservableList<Sport> getSportsPratiques()
    {
        return SportsPratiques;
    }

    public void setSportsPratiques(ObservableList<Sport> SportsPratiques)
    {
        this.SportsPratiques = SportsPratiques;
    }

    public Connection getConn()
    {
        return conn;
    }

    public void setConn(Connection conn)
    {
        this.conn = conn;
    }

    public Statement getStmt()
    {
        return stmt;
    }

    public void setStmt(Statement stmt)
    {
        this.stmt = stmt;
    }

    public ResultSet getRs()
    {
        return rs;
    }

    public void setRs(ResultSet rs)
    {
        this.rs = rs;
    }

    public String getPilote()
    {
        return pilote;
    }

    public void setPilote(String pilote)
    {
        this.pilote = pilote;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
    private ObservableList<Sport> SportsPratiques;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String pilote = "org.gjt.mm.mysql.Driver";
    private String url = "jdbc:mysql://localhost/gest_gymn";
        
    public Association(String pNomAssociation, String pVille, String pAdresse, String pNomResponsable) throws ClassNotFoundException
    {
        NomAssociation = pNomAssociation;
        Ville = pVille;
        Adresse = pAdresse;
        NomResponsable = pNomResponsable;
        SportsPratiques = FXCollections.observableArrayList();
        try
        {
            Class.forName(pilote);

            /////////////////////////////
            /////RECUPERATION SPORT/////
            ///////////////////////////
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();

            rs = stmt.executeQuery("select nomSport from pratiquer, association where association.refAsso = pratiquer.refAsso and association.refAsso = '" + pNomAssociation + "'");

            while (rs.next())
            {
                SportsPratiques.add(new Sport(rs.getString("nomSport")));
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
    }
    
    public void setSportsPratiques(Sport pUnSport)
    {
        SportsPratiques.add(pUnSport);
    }
    
    public ObservableList<Sport> getSportPratiques()
    {
        return SportsPratiques;
    }
    
    @Override
    public String toString()
    {
        return NomAssociation;
    }
}
