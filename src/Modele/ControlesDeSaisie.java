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
        //text.matches("^[a-zA-Z]+$")
        if(pNomSport.matches("^[a-zA-Z-àâäéèêëîïôöûüùç]+$"))
        {
            return true;
        }
        
        return false;
    }
}
