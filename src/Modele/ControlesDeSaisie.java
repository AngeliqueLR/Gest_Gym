/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author AngeliqueLeRoux
 */
public class ControlesDeSaisie
{
    public ControlesDeSaisie()
    {}
    
    public static boolean VerifNomSport(String pNomSport)
    {
        if(pNomSport.matches("^[a-zA-Z-àâäéèêëîïôöûüùç]+$") && pNomSport.length() < 26)
        {
            return true;
        }
        
        return false;
    }
    
    public static boolean VerifNomAssoc(String pNomAssoc)
    {
        if(pNomAssoc.matches("^[a-zA-Z-àâäéèêëîïôöûüùç ]+$") && pNomAssoc.length() < 21)
        {
            return true;
        }
        
        return false;
    }
    
    public static boolean VerifAdresse(String pAdresse)
    {
        if(pAdresse.matches("^[a-zA-Z-àâäéèêëîïôöûüùç 0-9]+$") && pAdresse.length() < 51)
        {
            return true;
        }
        
        return false;
    }

    public static boolean VerifNomResponsablr(String pResponsable)
    {
        if(pResponsable.matches("^[a-zA-Z-àâäéèêëîïôöûüùç ]+$") && pResponsable.length() < 31)
        {
            return true;
        }
        
        return false;
    }
    
    public static boolean VerifVille(String pVille)
    {
        if(pVille.matches("^[a-zA-Z-àâäéèêëîïôöûüùç]+$") && pVille.length() < 31)
        {
            return true;
        }
        
        return false;
    }
}
