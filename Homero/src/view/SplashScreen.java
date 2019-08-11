package view;

import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import static java.lang.Thread.sleep;
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
 * @author William
 */
public class SplashScreen extends JWindow
{
    private SplashScreen instance = this;
    
    private final String SPLASH_SCREEN_IMAGE_PATH = "/images/homero_splash.png";
    private final int PROGRESS_BAR_MAX_VALUE = 100;
    private final int TIME_TO_SLEEP = 100;
    
    //-----------------------------------------------------------------------------------//
    // CONSTRUCTORS
    
    public SplashScreen ()
    {
        try
        {
            // Layout parameters
            AbsoluteLayout layout = new AbsoluteLayout ();
            AbsoluteConstraints absImage = new AbsoluteConstraints (0, 0);
            AbsoluteConstraints absProgressBar = new AbsoluteConstraints (0, 470);
            
            // Get image for Icon
            URL resource = this.getClass ().getResource (SPLASH_SCREEN_IMAGE_PATH);
            ImageIcon icon = new ImageIcon (resource);
            
            // Progress bar
            JProgressBar progressBar = new JProgressBar ();
            progressBar.setPreferredSize (new Dimension (569, 30));
            
            JLabel label = new JLabel (icon);
            
            // Parameters for JWindow
            this.getContentPane ().setLayout (layout);
            this.getContentPane ().add (label, absImage);
            this.getContentPane ().add (progressBar, absProgressBar);
            
            new Thread ()
            {
                @Override
                public void run () { runSplashScreen (progressBar); }
            }.start ();
            
            // Parameters for JWindow
            this.pack ();
            this.setVisible (true);
            this.setLocationRelativeTo (null);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog (null, ex.getLocalizedMessage ());
        }
    }
    
    private void runSplashScreen (JProgressBar progressBar) throws HeadlessException
    {
        try
        {
            for (int i = 0; i <= PROGRESS_BAR_MAX_VALUE; i++)
            {
                // fills 
                progressBar.setValue (i);

                // Shows main screen
                if (progressBar.getValue () == 100)
                {
                    formMain main = new formMain ();
                    main.setVisible (true);
                    instance.setVisible (false);
                    break;
                }

                sleep (TIME_TO_SLEEP);
            }
        }
        catch (InterruptedException ex)
        {
            JOptionPane.showMessageDialog (null, ex.getLocalizedMessage ());
        }
    }
    
    //-----------------------------------------------------------------------------------//
    
    public static void main (String[] args)
    {
        try
        {
            // UI and shows SplashScreen
            UIManager.setLookAndFeel (new SyntheticaBlackEyeLookAndFeel ());
            EventQueue.invokeLater (() -> { new SplashScreen ().setVisible (true); });
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog (null, ex.getMessage ());
        }
    }
}