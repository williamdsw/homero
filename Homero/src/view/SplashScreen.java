package view;

import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.UIManager;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author William
 */
public class SplashScreen extends JWindow
{
    /* Self instance */
    private SplashScreen self = this;
    
    /* Construtor */
    public SplashScreen ()
    {
        try
        {
            /* Layout parameters */
            AbsoluteLayout layout = new AbsoluteLayout ();
            AbsoluteConstraints absImage = new AbsoluteConstraints (0, 0);
            AbsoluteConstraints absProgressBar = new AbsoluteConstraints (0, 470);
            
            /* Get image for Icon */
            URL resource = this.getClass ().getResource ("/images/homero_splash.png");
            ImageIcon icon = new ImageIcon (resource);
            
            /* New Progressbar */
            JProgressBar bar = new JProgressBar ();
            bar.setPreferredSize (new Dimension (569, 30));
            
            JLabel label = new JLabel (icon);
            
            /* Parameters for JWindow */
            this.getContentPane ().setLayout (layout);
            this.getContentPane ().add (label, absImage);
            this.getContentPane ().add (bar, absProgressBar);
            
            new Thread ()
            {
                @Override
                public void run ()
                {
                    try
                    {
                        /* Initiate Splash Screen */
                        for (int i = 0; i <= 100; i++)
                        {
                            /* Progress bar fill*/
                            bar.setValue (i);
                            
                            if (bar.getValue () == 100)
                            {
                                /* Shows main form and hide this */
                                formMain main = new formMain ();
                                main.setVisible (true);
                                self.setVisible (false);
                                
                                break;
                            }

                            sleep (100);
                        }
                    }
                    catch (InterruptedException ex)
                    {
                        JOptionPane.showMessageDialog (null, ex.getLocalizedMessage ());
                    }
                }
            }.start ();
            
            /* Parameters form JWindow */
            this.pack ();
            this.setVisible (true);
            this.setLocationRelativeTo (null);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog (null, ex.getLocalizedMessage ());
        }
    }
    
    /* main */
    public static void main (String[] args)
    {
        try
        {
            /* Change UI */
            UIManager.setLookAndFeel (new SyntheticaBlackEyeLookAndFeel ());
            
            /* Show this */
            EventQueue.invokeLater (() ->
            {
                new SplashScreen ().setVisible (true);
            });
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog (null, ex.getMessage ());
        }
    }
}