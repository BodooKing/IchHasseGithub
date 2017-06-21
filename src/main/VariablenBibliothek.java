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
    public static Image frontImage;
    //SystemImages
    public static Image textboxImage;
    
    //Alle TextElemente
    //Spielbezogen
    public static String name;
    public static String message;
    //Systembezogen
    public static String script = "1";
    public static String currentLine;
    
    public static String[] elements;
    
    //Fonts und die dazugehörigen Farben
    //Name
    public static Font nameFont = new Font("TimesRoman", Font.PLAIN, 24);
    public static Color nameColor = new Color(100,100,182,255);
    //Message
    public static Font messageFont = new Font("TimesRoman", Font.PLAIN, 18);
    public static Color messageColor = Color.white;
    //Saved
    public static Font savedFont = new Font("TimesRoman", Font.PLAIN, 12);
    public static Color savedColor = new Color(155,155,155,155);
    
    //Booleans und sonstiges
    //Booleans
    public static boolean eventON = false;
    public static boolean reading = false;
    public static boolean saved = false;
    public static boolean choice = false;
    public static String[] choices = new String[4];
    
    
    
    
}
