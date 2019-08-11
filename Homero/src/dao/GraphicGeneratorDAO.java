package dao;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Collection;
import model.GraphicParameterMODEL;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author William
 */
public class GraphicGeneratorDAO
{
    //-----------------------------------------------------------------------------------//
    // CONSTRUCTORS
    
    public GraphicGeneratorDAO () {}
    
    //-----------------------------------------------------------------------------------//
    
    /**
     * Generates a Chart based on type especified.
     * @param type
     * @param title
     * @param categoryLabel
     * @param valueLabel
     * @param listModels
     * @return 
     */
    public BufferedImage createChart (String type, String title, String categoryLabel, String valueLabel, Collection<GraphicParameterMODEL> listModels)
    {
        BufferedImage image = null;
        JFreeChart chart = null;
        
        try
        {
            switch (type)
            {
                case "HORIZONTAL_BAR_3D": case "VERTICAL_BAR_3D":
                {
                    chart = createBarChart (listModels, type, title, categoryLabel, valueLabel);
                    break;
                }
                
                case "PIE": case "RING":
                {
                    chart = createRoundedChart (listModels, type, title);
                    break;
                }
                
                case "TIMESERIES":
                {
                    chart = createTimeSeriesChart (listModels, title, valueLabel);
                    break;
                }
                
                default:
                {
                    break;
                }
            }
            
            if (chart != null)
            {
                // Border and image
                chart.setBorderVisible (true);
                chart.setBorderPaint (Color.BLACK);
                image = chart.createBufferedImage (800, 600);
            }
        }
        catch (Exception ex)
        {
            System.out.println (ex.getMessage ());
        }
        
        return image;
    }

    // HORIZONTAL / VERTICAL BAR
    private JFreeChart createBarChart (Collection<GraphicParameterMODEL> listModels, String type, String title, String categoryLabel, String valueLabel)
    {
        // Dataset with values
        DefaultCategoryDataset dataset = new DefaultCategoryDataset ();
        
        listModels.forEach ((model) -> 
        {
            dataset.setValue (model.getValue (), model.getRowLabel (), model.getColumnLabel ());
        });
        
        PlotOrientation orientation = (type.equals ("HORIZONTAL_BAR_3D") ? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL);
        
        // Returns new chart
        return ChartFactory.createBarChart3D (title, categoryLabel, valueLabel, dataset, orientation, true, false, false);
    }
    
    // PIE OR RING
    private JFreeChart createRoundedChart (Collection<GraphicParameterMODEL> listModels, String type, String title)
    {
        // Dataset with values
        DefaultPieDataset dataset = new DefaultPieDataset ();
        
        listModels.forEach ((model) -> 
        {
            dataset.setValue (model.getColumnLabel (), model.getValue ());
        });
        
        // Returns new chart
        return (type.equals ("PIE") ? ChartFactory.createPieChart (title, dataset, true, false, false) : ChartFactory.createRingChart (title, dataset, true, false, false));
    }
    
    // TIMESERIES
    private JFreeChart createTimeSeriesChart (Collection<GraphicParameterMODEL> listModels, String title, String valueLabel) throws NumberFormatException
    {
        // Dataset with TimeSeries
        TimeSeriesCollection dataset = new TimeSeriesCollection ();
        TimeSeries series = new TimeSeries ("Number of Sales", Year.class);
        dataset.setDomainIsPointsInTime (true);
        
        listModels.forEach ((model) -> 
        {
            series.add (new Year (Integer.parseInt (model.getColumnLabel ())), model.getValue ());
        });
        
        dataset.addSeries (series);
        
        // Returns new chart
        return ChartFactory.createTimeSeriesChart (title, valueLabel, valueLabel, (XYDataset) dataset, true, false, false);
    }
}