/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private static ObservableList<Sport> LesSports;
    private static ObservableList<Planning> MonPlanning;

    
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
                LesSports.add(new Sport(rs.getInt("numSport"), rs.getString("nomSport")));
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
    
    public static ObservableList<Sport> retournerListeSport()
    {
        return LesSports;
    }
    
    public boolean SportExiste(String pSport)
    {
        boolean Existe = true;
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            rs = stmt.executeQuery("select * from sport where nomSport = '" + pSport + "'");			            
            
            if(!(rs.next()))
            {
                Existe = false;
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
            return Existe;
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
        
        return Existe;
    }
    
    public void AjoutSport(String pSport)
    {
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();		            
            stmt.executeUpdate("insert into sport (nomSport) values ('" + pSport + "');");
               		
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
    
    
    public void ModifSport(String pSport, Sport pSportAModifier)
    {
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();
            stmt.executeUpdate("update sport set nomSport = '" + pSport + "' where numSport = '" + pSportAModifier.getNumSport() + "';");
               		
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
    
    public ObservableList<String> retournerVilleAssoc()
    {
        ObservableList<String> LesVilles = FXCollections.observableArrayList();
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();		            
            rs = stmt.executeQuery("select distinct ville from association");
            while (rs.next())
            {
                LesVilles.add(rs.getString("ville"));
            }           			 
            LesVilles.add("Autre");
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
        return LesVilles;
    }
    
    public boolean AssociationExiste(String pAssoc)
    {
        boolean Existe = true;
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            rs = stmt.executeQuery("select * from association where refAsso = '" + pAssoc + "'");			            
            
            if(!(rs.next()))
            {
                Existe = false;
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
            return Existe;
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
        
        return Existe;
    }
    
    
    public void insererAssociation(String pNomAssoc, String pAdresse, String pVille, String pResponsable, ObservableList<String> pSportsCoches)
    {
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            stmt.executeUpdate("insert into association values ('" + pNomAssoc + "', '" + pVille + "', '" + pAdresse + "', '" + pResponsable + "')");			            
            
            rs.close();
            stmt.close();
            conn.close();
            
            for(int i=0; i<pSportsCoches.size(); i++)
            {
                Class.forName(pilote);
                conn = DriverManager.getConnection(url,"root","");
                int numSport;
                numSport = Sport.retournerNumSport(pSportsCoches.get(i), LesSports);
                if(numSport != -1)
                {
                    CallableStatement cStmt = conn.prepareCall("{call AjoutSportPratiquer('" + pNomAssoc + "', '" + numSport + "')}");
                    cStmt.executeUpdate();
                }

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
    
    public void modifierAssociation(String pNomAssoc, String pAdresse, String pVille, String pResponsable, ObservableList<String> pSportsCoches, ObservableList<String> pSportsNonCoches, ObservableList<Association> pLesAssoc)
    {
        try
	{            
            Association Assoc;
            Assoc = Association.retournerAssoc(pNomAssoc, pLesAssoc);
            if(!(Assoc.getAdresse().equals(pAdresse)))
            {
                Class.forName(pilote);
                conn = DriverManager.getConnection(url,"root","");
                stmt = conn.createStatement();			            
                stmt.executeUpdate("update association set adresse = '" + pAdresse + "' where refAsso = '" + pNomAssoc + "'");

                rs.close();
                stmt.close();
                conn.close();
            }
            if(!(Assoc.getVille().equals(pVille)))
            {
                Class.forName(pilote);
                conn = DriverManager.getConnection(url,"root","");
                stmt = conn.createStatement();			            
                stmt.executeUpdate("update association set ville = '" + pVille + "' where refAsso = '" + pNomAssoc + "'");

                rs.close();
                stmt.close();
                conn.close();
            }
            if(!(Assoc.getNomResponsable().equals(pResponsable)))
            {
                Class.forName(pilote);
                conn = DriverManager.getConnection(url,"root","");
                stmt = conn.createStatement();			            
                stmt.executeUpdate("update association set nomResponsable = '" + pResponsable + "' where refAsso = '" + pNomAssoc + "'");

                rs.close();
                stmt.close();
                conn.close();
            }
            
            for(int i=0; i<pSportsCoches.size(); i++)
            {
                Class.forName(pilote);
                conn = DriverManager.getConnection(url,"root","");
                int numSport;
                numSport = Sport.retournerNumSport(pSportsCoches.get(i), LesSports);
                if(numSport != -1)
                {
                    CallableStatement cStmt = conn.prepareCall("{call AjoutSportPratiquer('" + pNomAssoc + "', '" + numSport + "')}");
                    cStmt.executeUpdate();
                }
                conn.close();
            }
            
            for(int i=0; i<pSportsNonCoches.size(); i++)
            {
                Class.forName(pilote);
                conn = DriverManager.getConnection(url,"root","");
                int numSport;
                numSport = Sport.retournerNumSport(pSportsNonCoches.get(i), LesSports);
                if(numSport != -1)
                {
                    CallableStatement cStmt = conn.prepareCall("{call SupprSportPratiquer('" + pNomAssoc + "', '" + numSport + "')}");
                    cStmt.executeUpdate();
                }
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
    
    public ObservableList<Salle> retournerSalleSport(LocalDate pDate, String pHoraire, Sport pSport)
    {
        ObservableList<Salle> SalleSport;
        SalleSport = FXCollections.observableArrayList();
        
        try
        {
            Class.forName(pilote);

            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();

            rs = stmt.executeQuery("Select salle.refSalle from salle, accueillir where salle.refSalle = accueillir.refSalle and numSportAutorise = " + pSport.getNumSport() + " and salle. refSalle not in (select reservation.refSalle from reservation, accueillir where reservation.refSalle = accueillir.refSalle and numSportAutorise = '" + pSport.getNumSport() + "' and heure = '" + pHoraire + "' and date = '" + pDate + "')");

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
        
    public boolean SalleExiste(String pSalle)
    {
        boolean Existe = true;
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            rs = stmt.executeQuery("select * from salle where refSalle = '" + pSalle + "'");			            
            
            if(!(rs.next()))
            {
                Existe = false;
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
            return Existe;
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
        
        return Existe;
    }
    
    public void insererSalle(String pNomSalle, String pSurface, String pRevetement, ObservableList<String> pSportsCoches)
    {
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            stmt.executeUpdate("insert into salle values ('" + pNomSalle + "', '" + pSurface + "', '" + pRevetement + "')");			            
            
            rs.close();
            stmt.close();
            conn.close();
            
            for(int i=0; i<pSportsCoches.size(); i++)
            {
                Class.forName(pilote);
                conn = DriverManager.getConnection(url,"root","");
                int numSport;
                numSport = Sport.retournerNumSport(pSportsCoches.get(i), LesSports);
                if(numSport != -1)
                {
                    CallableStatement cStmt = conn.prepareCall("{call AjoutSportAutorise('" + pNomSalle + "', '" + numSport + "')}");
                    cStmt.executeUpdate();
                }

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

    public void modifierSalle(String pNomSalle, String pSurface, String pRevetement, ObservableList<String> pSportsCoches, ObservableList<String> pSportsNonCoches, ObservableList<Salle> pLesSalles)
    {
        try
	{            
            Salle uneSalle;
            uneSalle = Salle.retournerSalle(pNomSalle, pLesSalles);
            float maSurface = Float.parseFloat(pSurface);
            System.out.println(maSurface);
            if(!(uneSalle.getSurface() == maSurface))
            {
                Class.forName(pilote);
                conn = DriverManager.getConnection(url,"root","");
                stmt = conn.createStatement();			            
                stmt.executeUpdate("update salle set surface = '" + pSurface + "' where refSalle = '" + pNomSalle + "'");

                rs.close();
                stmt.close();
                conn.close();
            }
            if(!(uneSalle.getRevetement().equals(pRevetement)))
            {
                Class.forName(pilote);
                conn = DriverManager.getConnection(url,"root","");
                stmt = conn.createStatement();			            
                stmt.executeUpdate("update salle set revetement = '" + pRevetement + "' where refSalle = '" + pNomSalle + "'");

                rs.close();
                stmt.close();
                conn.close();
            }
            
            for(int i=0; i<pSportsCoches.size(); i++)
            {
                Class.forName(pilote);
                conn = DriverManager.getConnection(url,"root","");
                int numSport;
                numSport = Sport.retournerNumSport(pSportsCoches.get(i), LesSports);
                if(numSport != -1)
                {
                    CallableStatement cStmt = conn.prepareCall("{call AjoutSportAutorise('" + pNomSalle + "', '" + numSport + "')}");
                    cStmt.executeUpdate();
                }
                conn.close();
            }
            
            for(int i=0; i<pSportsNonCoches.size(); i++)
            {
                Class.forName(pilote);
                conn = DriverManager.getConnection(url,"root","");
                int numSport;
                numSport = Sport.retournerNumSport(pSportsNonCoches.get(i), LesSports);
                if(numSport != -1)
                {
                    CallableStatement cStmt = conn.prepareCall("{call SupprSportAutorise('" + pNomSalle + "', '" + numSport + "')}");
                    cStmt.executeUpdate();
                }
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
    ////////////////////////////////
    //////////RESERVATION//////////
    //////////////////////////////
    public void InsererReservation(String pSalle, LocalDate pDate, String pHeureD, String pNomAssoc)
    {		
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();		            
            stmt.executeUpdate("insert into reservation values ('" + pSalle + "', '" + pDate + "', '" + pHeureD + "', '" + pNomAssoc + "');");
               		
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
                rs = stmt.executeQuery("select * from reservation where heure = '" + horaire[i] + ":00' and refSalle = '" + pSalle + "' order by date, heure");
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
    
    public static ObservableList<Planning> retournerPlanning()
    {
        return MonPlanning;
    }
    
    public ObservableList<Planning> resetTableau()
    {
        MonPlanning.removeAll(MonPlanning);
        return MonPlanning;
    }
}
