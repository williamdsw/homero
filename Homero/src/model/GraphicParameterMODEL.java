package model;

/**
 * @author William
 */
public class GraphicParameterMODEL
{
    private String columnLabel;
    private String rowLabel;
    private Double value;
    
    //-----------------------------------------------------------------------------------//
    // GETTERS / SETTERS

    public String getColumnLabel () { return columnLabel; }
    public String getRowLabel () { return rowLabel; }
    public Double getValue () { return value; }
    
    public void setColumnLabel (String value) { if (null != value) { this.columnLabel = value; } }
    public void setRowLabel (String value) { if (null != value) { this.rowLabel = value; } }
    public void setValue (Double value) { if (null != value) { this.value = value; } }
}