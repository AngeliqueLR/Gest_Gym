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
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author AngeliqueLeRoux
 */
public class Requetes
{
    private static Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String pilote;
    private String url;
    private Association uneAssocitation;
    private ObservableList<Association> LesAssociations;
    private ObservableList<Salle> LesSalles;
    private ObservableList<Sport> LesSports;
    private ObservableList<Planning> MonPlanning;

    
    public Requetes ()
    {
        pilote = "org.gjt.mm.mysql.Driver";
        url = "jdbc:mysql://localhost/gest_gymn";
        LesAssociations = FXCollections.observableArrayList();
        LesSports = FXCollections.observableArrayList();
        LesSalles = FXCollections.observableArrayList();
        MonPlanning = FXCollections.observableArrayList();
    }
    ////////////////////////////////
    //////////////SPORT////////////
    //////////////////////////////
    public void RecupererSports()
    {		
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            rs = stmt.executeQuery("select * from sport");			            
            while (rs.next())
            {
                LesSports.add(new Sport(rs.getString("nomSport")));
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
    
    public ObservableList<Sport> retournerListeSport()
    {
        return LesSports;
    }
    ////////////////////////////////
    //////////ASSOCIATION//////////
    //////////////////////////////

    public void RecupererAssociations()
    {		
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();		            
            rs = stmt.executeQuery("select refAsso, ville, adresse, nomResponsable from association");
            while (rs.next())
            {
                LesAssociations.add(new Association(rs.getString("refAsso"), rs.getString("ville"), rs.getString("adresse"), rs.getString("nomResponsable")));
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
    
    public ObservableList<Association> retournerListeAssociation()
    {
        return LesAssociations;
    }
    
    ////////////////////////////////
    /////////////SALLE/////////////
    //////////////////////////////

    public void RecupererSalle()
    {		
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();		            
            rs = stmt.executeQuery("select refSalle, surface, typeRevetement from salle");
            while (rs.next())
            {
                LesSalles.add(new Salle(rs.getString("refSalle"), rs.getFloat("surface"), rs.getString("typeRevetement")));
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
    
    public ObservableList<Salle> retournerListeSalle()
    {
        return LesSalles;
    } 
    
    ////////////////////////////////
    //////////RESERVATION//////////
    //////////////////////////////
    public void InsererReservation(String pSalle, LocalDate pDate, String pHeureD, String pHeureF, String pNomAssoc)
    {		
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();		            
            stmt.executeUpdate("insert into reservation values ('" + pSalle + "', '" + pDate + "', '" + pHeureD + "', '" + pHeureF + "', '" + pNomAssoc + "');");
               		
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
    
    ////////////////////////////
    /////////PLANNING//////////
    //////////////////////////
    public void RecupererPlanning(LocalDate pDateChoisie, String pSalle)
    {
        try
	{
            ////CREATION D'UN TABLEAU D'HORAIRES
            String horaire[] = new String[8];
            horaire[0] = "08:00";
            horaire[1] = "10:00";
            horaire[2] = "12:00";
            horaire[3] = "14:00";
            horaire[4] = "16:00";
            horaire[5] = "18:00";
            horaire[6] = "20:00";
            horaire[7] = "22:00";
            
            ////RECUPERATION DE TOUTES LES DATES DE LA SEMAINE AU FORMAT DD-MM-YYYY
            //D1
            String date1 = pDateChoisie.toString();

            //D2
            String date2 = pDateChoisie.plusDays(1).toString();

            //D3
            String date3 = pDateChoisie.plusDays(2).toString();

            //D4
            String date4 = pDateChoisie.plusDays(3).toString();

            //D5
            String date5 = pDateChoisie.plusDays(4).toString();

            //D6
            String date6 = pDateChoisie.plusDays(5).toString();

            //D7
            String date7 = pDateChoisie.plusDays(6).toString();

            
            //POUR CHAQUE PLAGE HORAIRES RECUPERER LES ASSOCIATIONS QUI ON RESERVER CHAQUE JOUR A CES HEURES
            for(int i=0; i<7; i++)
            {
                String D1 = "";
                String D2 = "";
                String D3 = "";
                String D4 = "";
                String D5 = "";
                String D6 = "";
                String D7 = "";
                
                Class.forName(pilote);
                conn = DriverManager.getConnection(url,"root","");
                stmt = conn.createStatement();		            
                rs = stmt.executeQuery("select * from reservation where heureD = '" + horaire[i] + ":00' and refSalle = '" + pSalle + "' order by date, heureD");
                while(rs.next())
                {
                    if(date1.equals(rs.getString("date")))
                    {
                        D1 = rs.getString("refAsso");
                    }
                    else if(date2.equals(rs.getString("date")))
                    {
                        D2 = rs.getString("refAsso");
                    }
                    else if(date3.equals(rs.getString("date")))
                    {
                        D3 = rs.getString("refAsso");
                    }
                    else if(date4.equals(rs.getString("date")))
                    {
                        D4 = rs.getString("refAsso");
                    }
                    else if(date5.equals(rs.getString("date")))
                    {
                        D5 = rs.getString("refAsso");
                    }
                    else if(date6.equals(rs.getString("date")))
                    {
                        D6 = rs.getString("refAsso");
                    }
                    else if(date7.equals(rs.getString("date")))
                    {
                        D7 = rs.getString("refAsso");
                    }
                }
                
                MonPlanning.add(new Planning(horaire[i]+"-"+horaire[i+1], D1, D2, D3, D4, D5, D6, D7));
                
                rs.close();
                stmt.close();
                conn.close();
            }
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
    
    public ObservableList<Planning> retournerPlanning()
    {
        return MonPlanning;
    }
    
    public ObservableList<Planning> resetTableau()
    {
        MonPlanning.removeAll(MonPlanning);
        return MonPlanning;
    }
    /*
    select * from reservation where date between '2018-11-23' and '2018-11-28' and refSalle = 'B' order by date, heureD
    */
}
