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

    public GraphicGeneratorDAO () {}
    
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
        /*  Output image */
        BufferedImage image = null;
        
        /* Chart itself */
        JFreeChart chart = null;
        
        try
        {
            switch (type)
            {
                case "HORIZONTAL_BAR_3D": case "VERTICAL_BAR_3D":
                {
                    /* Dataset */
                    DefaultCategoryDataset dataset = new DefaultCategoryDataset ();

                    /* Filling in dataset */
                    for (GraphicParameterMODEL model : listModels)
                    {
                        dataset.addValue (model.getValue (), model.getRowLabel (), model.getColumnLabel ());
                    }
                    
                    /* Define orientation */
                    PlotOrientation orientation = (type.equals ("HORIZONTAL_BAR_3D") ? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL);
                    
                    /* Creates the chart */
                    chart = ChartFactory.createBarChart3D (title, categoryLabel, valueLabel, dataset, orientation, true, false, false);
            
                    break;
                }
                
                case "PIE": case "RING":
                {
                    /* Dataset */
                    DefaultPieDataset dataset = new DefaultPieDataset ();

                    /* Filling in dataset */
                    for (GraphicParameterMODEL model : listModels)
                    {
                        dataset.setValue (model.getColumnLabel (), model.getValue ());
                    }
                    
                    /* Creates the chart */
                    chart = (type.equals ("PIE") ? ChartFactory.createPieChart (title, dataset, true, false, false) : ChartFactory.createRingChart (title, dataset, true, false, false));
                    
                    break;
                }
                
                case "TIMESERIES":
                {
                    /* Dataset */
                    TimeSeriesCollection dataset = new TimeSeriesCollection ();
                    TimeSeries series = new TimeSeries ("Number of Sales", Year.class);
                    
                    dataset.setDomainIsPointsInTime (true);
                    
                    /* Filling in dataset */
                    for (GraphicParameterMODEL model : listModels)
                    {
                        series.add (new Year (Integer.parseInt (model.getColumnLabel ())), model.getValue ());
                    }
                    
                    dataset.addSeries (series);
                    
                    /* Creates the chart */
                    chart = ChartFactory.createTimeSeriesChart (title, valueLabel, valueLabel, (XYDataset) dataset, true, false, false);
                    
                    break;
                }
            }
            
            if (chart != null)
            {
                /* Border */
                chart.setBorderVisible (true);
                chart.setBorderPaint (Color.BLACK);

                /* Create image (width x height) */
                image = chart.createBufferedImage (800, 600);
            }
        }
        catch (Exception e)
        {
            System.out.println (e.getMessage ());
        }
        
        return image;
    }
}