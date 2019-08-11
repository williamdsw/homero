package dao;

import java.util.ArrayList;
import java.util.Collection;
import model.GraphicParameterMODEL;

/**
 * @author William
 */
public class GenerateDataDAO
{
    /**
     * Generates data from array of string passed and 
     * generate a random value based on min / max values
     * @param arrItems
     * @param min
     * @param max
     * @return 
     */
    public Collection<GraphicParameterMODEL> generate (Collection<String> arrItems, Integer min, Integer max)
    {
        Collection<GraphicParameterMODEL> listModels = new ArrayList<> ();
        
        try
        {
            arrItems.forEach ((item) -> 
            {
                GraphicParameterMODEL model = new GraphicParameterMODEL ();
                model.setColumnLabel (item);
                model.setRowLabel (item);
                Double value = (Math.random () * (max - min + 1));
                model.setValue (value);
                listModels.add (model);
            });
        }
        catch (Exception ex)
        {
            System.out.println (ex.getMessage ());
        }
        
        return listModels;
    }
}
