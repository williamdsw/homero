package view;

import dao.GenerateDataDAO;
import dao.GraphicGeneratorDAO;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import model.GraphicParameterMODEL;


public class formMain extends JFrame
{
    private enum Categories { COUNTRY, GAME_GENRE, MODE, PLATAFORM, YEAR }
    
    private final int CHART_FRAME_X = 250;
    private final int CHART_FRAME_Y = 50;
    private final int CHART_FRAME_WIDTH = 800;
    private final int CHART_FRAME_HEIGHT = 800;
    
    //-----------------------------------------------------------------------------------//
    
    public static void main (String args[]) 
    {        
        try 
        {
            UIManager.setLookAndFeel (new SyntheticaBlackEyeLookAndFeel ());
        } 
        catch (ParseException | UnsupportedLookAndFeelException ex) 
        {
            JOptionPane.showMessageDialog (null, ex.getMessage());
        }
        
        // Calls the form
        EventQueue.invokeLater (() -> { new formMain ().setVisible (true); });
    }
    
    //-----------------------------------------------------------------------------------//
    // CONSTRUCTORS
    
    public formMain () 
    {
        initComponents ();
        bindClickEvents ();
        bindMouseEnterEvents ();
    }
    
    //-----------------------------------------------------------------------------------//
    
    private void bindClickEvents ()
    {
        btnCountry.addActionListener ((ActionEvent event) -> showChart (Categories.COUNTRY.toString ()));
        btnGameGenre.addActionListener ((ActionEvent event) -> showChart (Categories.GAME_GENRE.toString ()));
        btnGameMode.addActionListener ((ActionEvent event) -> showChart (Categories.MODE.toString ()));
        btnPlataform.addActionListener ((ActionEvent event) -> showChart (Categories.PLATAFORM.toString ()));
        btnYear.addActionListener ((ActionEvent event) -> showChart (Categories.YEAR.toString ()));
    }

    private void bindMouseEnterEvents ()
    {
        btnCountry.addMouseListener (new MouseAdapter ()
        {
            @Override
            public void mouseEntered (MouseEvent event)
            {
                lblCheckSells.setText ("Check Sales by Country");
            }
        });
        
        btnGameGenre.addMouseListener (new MouseAdapter ()
        {
            @Override
            public void mouseEntered (MouseEvent event)
            {
                lblCheckSells.setText ("Check Sales by Game Genre");
            }
        });
        
        btnGameMode.addMouseListener (new MouseAdapter ()
        {
            @Override
            public void mouseEntered (MouseEvent event)
            {
                lblCheckSells.setText ("Check Sales by Game Mode");
            }
        });
        
        btnPlataform.addMouseListener (new MouseAdapter ()
        {
            @Override
            public void mouseEntered (MouseEvent event)
            {
                lblCheckSells.setText ("Check Sales by Plataform");
            }
        });
        
        btnYear.addMouseListener (new MouseAdapter ()
        {
            @Override
            public void mouseEntered (MouseEvent event)
            {
                lblCheckSells.setText ("Check Sales by Year");
            }
        });
    }

    
    //-----------------------------------------------------------------------------------//
    
    /**
     * Send parameters for DAO and shows a chart in new JFrame
     * @param type 
     */
    private void showChart (String type)
    {
        // Default 
        Collection<String> arrItems = new ArrayList<> ();
        Integer min = 0;
        Integer max = 0;
        String typeChart = "";
        String title = "";
        String categoryLabel = "";
        String valueLabel = "";
        
        GenerateDataDAO dao = new GenerateDataDAO ();
        
        switch (type)
        {
            case "COUNTRY":
            {
                // Parameters
                typeChart = "VERTICAL_BAR_3D";
                title = "Sales by Country";
                categoryLabel = "Country";
                valueLabel = "Total Revenues In US Dollars";
                
                // Data
                arrItems.add ("China");
                arrItems.add ("USA");
                arrItems.add ("Japan");
                arrItems.add ("Korea");
                arrItems.add ("Germany");
                arrItems.add ("UK");
                arrItems.add ("France");
                arrItems.add ("Canada");
                arrItems.add ("Italy");
                arrItems.add ("Spain");
                
                // Min / Max values for random range
                min = 1000000;
                max = 40000000;
                
                break;
            }
            
            case "GAME_GENRE":
            {
                // Parameters
                typeChart = "HORIZONTAL_BAR_3D";
                title = "Sales by Game Genre";
                categoryLabel = "Genre";
                valueLabel = "Total Revenues In US Dollars";
                
                // Data
                arrItems.add ("Action-Role Playing");
                arrItems.add ("Adventure");
                arrItems.add ("Sports");
                arrItems.add ("Fighting");
                arrItems.add ("First Person Shooter");
                
                // Min / Max values for random range
                min = 10000000;
                max = 300000000;
                
                break;
            }
          
            case "MODE":
            {
                // Parameters
                typeChart = "RING";
                title = "Sales by Mode";
                categoryLabel = "Mode";
                valueLabel = "Total Revenues In US Dollars";
                
                // Data
                arrItems.add ("Multiplayer");
                arrItems.add ("Single-player");
                
                // Min / Max values for random range
                min = 1000000;
                max = 10000000;
                
                break;
            }
            
            case "PLATAFORM":
            {
                // Parameters
                typeChart = "PIE";
                title = "Sales by Plataform";
                categoryLabel = "Plataform";
                valueLabel = "Total Revenues In US Dollars";
                
                // Data
                arrItems.add ("Microsoft Windows");
                arrItems.add ("Nintendo Switch");
                arrItems.add ("Nintendo Wii U");
                arrItems.add ("PlayStation 3");
                arrItems.add ("PlayStation 4");
                arrItems.add ("Xbox 360");
                arrItems.add ("Xbox One");
                
                // Min / Max values for random range
                min = 1000000;
                max = 300000000;
                
                break;
            }
            
            case "YEAR":
            {
                // Parameters
                typeChart = "TIMESERIES";
                title = "Sales by Year";
                categoryLabel = "Year";
                valueLabel = "Values in Millions";
                
                // Data
                arrItems.add ("2000");
                arrItems.add ("2001");
                arrItems.add ("2002");
                arrItems.add ("2003");
                arrItems.add ("2004");
                arrItems.add ("2005");
                arrItems.add ("2006");
                arrItems.add ("2007");
                arrItems.add ("2008");
                arrItems.add ("2009");
                arrItems.add ("2010");
                arrItems.add ("2011");
                arrItems.add ("2012");
                arrItems.add ("2013");
                arrItems.add ("2014");
                arrItems.add ("2015");
                arrItems.add ("2016");
                arrItems.add ("2017");
                arrItems.add ("2018");
                
                // Min / Max values for random range
                min = 100000;
                max = 300000000;
                
                break;
            }
        }
       
        // List with data
        Collection<GraphicParameterMODEL> listModel = dao.generate (arrItems, min, max);
        
        // New image and label icon
        GraphicGeneratorDAO generatorDAO = new GraphicGeneratorDAO ();
        BufferedImage image = generatorDAO.createChart (typeChart, title, categoryLabel, valueLabel, listModel);
        ImageIcon icon = new ImageIcon (image);
        JLabel label = new JLabel (icon);
        
        // New Jframe
        JFrame frame = new JFrame ();
        frame.setBounds (CHART_FRAME_X, CHART_FRAME_Y, CHART_FRAME_WIDTH, CHART_FRAME_HEIGHT);
        frame.setLocationRelativeTo (this);
        frame.setVisible (true);
        frame.add (label);
        frame.pack ();
    }
    
    //-----------------------------------------------------------------------------------//

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        panMain = new javax.swing.JPanel();
        btnYear = new javax.swing.JButton();
        btnCountry = new javax.swing.JButton();
        btnGameGenre = new javax.swing.JButton();
        btnGameMode = new javax.swing.JButton();
        btnPlataform = new javax.swing.JButton();
        panCheckSells = new javax.swing.JPanel();
        lblCheckSells = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HOMERO");
        setBounds(new java.awt.Rectangle(300, 50, 0, 0));
        setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        setResizable(false);

        panMain.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnYear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/calendar.png"))); // NOI18N
        btnYear.setToolTipText("Por Ano de Lançamento");

        btnCountry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/brazil.png"))); // NOI18N
        btnCountry.setToolTipText("Por Estado");

        btnGameGenre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/game_genres.png"))); // NOI18N
        btnGameGenre.setToolTipText("Por Gênero");

        btnGameMode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/multiplayer.png"))); // NOI18N
        btnGameMode.setToolTipText("Por Modo de jogo");

        btnPlataform.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/control.png"))); // NOI18N
        btnPlataform.setToolTipText("Por Plataforma");

        javax.swing.GroupLayout panMainLayout = new javax.swing.GroupLayout(panMain);
        panMain.setLayout(panMainLayout);
        panMainLayout.setHorizontalGroup(
            panMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnYear, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGameGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGameMode, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPlataform, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panMainLayout.setVerticalGroup(
            panMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGameMode, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlataform, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnYear, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGameGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnYear.getAccessibleContext().setAccessibleDescription("");
        btnCountry.getAccessibleContext().setAccessibleDescription("");
        btnGameGenre.getAccessibleContext().setAccessibleDescription("");
        btnGameMode.getAccessibleContext().setAccessibleDescription("");
        btnPlataform.getAccessibleContext().setAccessibleDescription("");

        panCheckSells.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblCheckSells.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCheckSells.setText("Check sells...");
        lblCheckSells.setAlignmentY(0.1F);

        javax.swing.GroupLayout panCheckSellsLayout = new javax.swing.GroupLayout(panCheckSells);
        panCheckSells.setLayout(panCheckSellsLayout);
        panCheckSellsLayout.setHorizontalGroup(
            panCheckSellsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCheckSellsLayout.createSequentialGroup()
                .addGap(285, 285, 285)
                .addComponent(lblCheckSells)
                .addContainerGap(302, Short.MAX_VALUE))
        );
        panCheckSellsLayout.setVerticalGroup(
            panCheckSellsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCheckSellsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCheckSells)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panCheckSells, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panCheckSells, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCountry;
    private javax.swing.JButton btnGameGenre;
    private javax.swing.JButton btnGameMode;
    private javax.swing.JButton btnPlataform;
    private javax.swing.JButton btnYear;
    private javax.swing.JLabel lblCheckSells;
    private javax.swing.JPanel panCheckSells;
    private javax.swing.JPanel panMain;
    // End of variables declaration//GEN-END:variables
}