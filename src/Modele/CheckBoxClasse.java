/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import javafx.scene.control.CheckBox;

/**
 *
 * @author AngeliqueLeRoux
 */
public class CheckBoxClasse
{
    private String nom;
    private CheckBox laCheckBox;
    
    public CheckBoxClasse(String pNom, CheckBox pLaCheckBox)
    {
        nom = pNom;
        laCheckBox = pLaCheckBox;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public CheckBox getLaCheckBox()
    {
        return laCheckBox;
    }

    public void setLaCheckBox(CheckBox laCheckBox)
    {
        this.laCheckBox = laCheckBox;
    }
    
}
