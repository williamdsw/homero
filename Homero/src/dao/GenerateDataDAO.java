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
