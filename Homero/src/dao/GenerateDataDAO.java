/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.Collection;
import model.GraphicParameterMODEL;

/**
 *
 * @author William
 */
public class GenerateDataDAO
{
    
    /**
     * Generates data for age range with random values
     * @return 
     */
    public Collection<GraphicParameterMODEL> generateByAgeRange ()
    {
        /* List of age ranges */
        String[] ageRanges = { "21 - 25", "26 - 30", "31 - 35", "36 - 40", "41 - 45" };
        
        /* List of models */
        Collection<GraphicParameterMODEL> listModels = new ArrayList<> ();
        
        try
        {
            for (String ageRenge : ageRanges)
            {
                /* New instance */
                GraphicParameterMODEL model = new GraphicParameterMODEL ();
                
                /* Passing data */
                model.setColumnLabel (ageRenge);
                model.setRowLabel (ageRenge);
                model.setValue (Math.random () * 1000000);
                
                /* Filling in list */
                listModels.add (model);
            }
        }
        catch (Exception e)
        {
            System.out.println (e.getMessage ());
        }
        
        return listModels;
    }
    
    /**
     * Generates data from array of string passed and generate a random value based on min / max values
     * @param arrItems
     * @param min
     * @param max
     * @return 
     */
    public Collection<GraphicParameterMODEL> generate (Collection<String> arrItems, Integer min, Integer max)
    {
        /* List of models */
        Collection<GraphicParameterMODEL> listModels = new ArrayList<> ();
        
        try
        {
            for (String item : arrItems)
            {
                /* New instance */
                GraphicParameterMODEL model = new GraphicParameterMODEL ();
                
                /* Passing data */
                model.setColumnLabel (item);
                model.setRowLabel (item);
                model.setValue (Math.random () * (max - min + 1));
                
                /* Filling in list */
                listModels.add (model);
            }
        }
        catch (Exception e)
        {
            System.out.println (e.getMessage ());
        }
        
        return listModels;
    }
}
