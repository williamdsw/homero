package model;

/**
 *
 * @author William
 */
public class GraphicParameterMODEL
{
    /* Variables */
    private String columnLabel;
    private String rowLabel;
    private Double value;

    /**
     * Returns name (text) of column's label
     * @return 
     */
    public String getColumnLabel ()
    {
        return columnLabel;
    }

    /**
     * Set the name (text) of column's label
     * @param value 
     */
    public void setColumnLabel (String value)
    {
        if (null != value)
        {
            this.columnLabel = value;
        }
    }

    /**
     * Returns name (text) of row's label
     * @return 
     */
    public String getRowLabel ()
    {
        return rowLabel;
    }

    /**
     * Set the name (text) of row's label
     * @param value 
     */
    public void setRowLabel (String value)
    {
        if (null != value)
        {
            this.rowLabel = value;
        }
    }

    /**
     * Return the value from specific parameters
     * @return 
     */
    public Double getValue ()
    {
        return value;
    }

    /**
     * Set the value of specific graphic
     * @param value 
     */
    public void setValue (Double value)
    {
        if (null != value)
        {
            this.value = value;
        }
    }
}