package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Diese Klasse beinhaltet nur alle Variablen die benutzt werden, sowie Methoden
 * um diese zu ändern, um mehr Ordnung zu erzeugen, da viele Variablen geteilt werden.
 * @author BK
 */
public class VariablenBibliothek {
    
    //Bilder
    //GameImages
    public static Image backgroundImage;
    public static Image characterImage;
    public static Image faceImage;
    //SystemImages
    public static Image textboxImage;
    
    //Alle TextElemente
    //Spielbezogen
    public static String name = "Hercules";
    public static String message = "I am so Buffed";
    //Systembezogen
    public static String script = "1.txt";
    
    //Fonts und die dazugehörigen Farben
    //Name
    public static Font nameFont = new Font("TimesRoman", Font.PLAIN, 24);
    public static Color nameColor = new Color(100,100,182,255);
    //Message
    public static Font messageFont = new Font("TimesRoman", Font.PLAIN, 18);
    public static Color messageColor = Color.white;
    
    
    
    
}
