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
public class Planning
{
    private String plageHoraires;
    private String J1;
    private String J2;
    private String J3;
    private String J4;
    private String J5;
    private String J6;
    private String J7;
    
    public Planning(String pHoraires, String pJ1, String pJ2, String pJ3, String pJ4, String pJ5, String pJ6, String pJ7)
    {
        plageHoraires = pHoraires;
        J1 = pJ1;
        J2 = pJ2;
        J3 = pJ3;
        J4 = pJ4;
        J5 = pJ5;
        J6 = pJ6;
        J7 = pJ7;
    }

    @Override
    public String toString()
    {
        return plageHoraires;
    }

    public void setPlageHoraires(String plageHoraires)
    {
        this.plageHoraires = plageHoraires;
    }

    public void setJ1(String J1)
    {
        this.J1 = J1;
    }

    public void setJ2(String J2)
    {
        this.J2 = J2;
    }

    public void setJ3(String J3)
    {
        this.J3 = J3;
    }

    public void setJ4(String J4)
    {
        this.J4 = J4;
    }

    public void setJ5(String J5)
    {
        this.J5 = J5;
    }

    public void setJ6(String J6)
    {
        this.J6 = J6;
    }

    public void setJ7(String J7)
    {
        this.J7 = J7;
    }

    public String getPlageHoraires()
    {
        return plageHoraires;
    }

    public String getJ1()
    {
        return J1;
    }

    public String getJ2()
    {
        return J2;
    }

    public String getJ3()
    {
        return J3;
    }

    public String getJ4()
    {
        return J4;
    }

    public String getJ5()
    {
        return J5;
    }

    public String getJ6()
    {
        return J6;
    }

    public String getJ7()
    {
        return J7;
    }
}
