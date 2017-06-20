/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.util.Arrays;
import javax.swing.JButton;

/**
 * Diese Klasse ist die Klasse, die alle grafischen Elemente ausliest und dann auf das Fenster übeträgt
 * @author BK
 */
public class ImagePanel extends JComponent{
    ScriptReader sr = new ScriptReader();    
    public ImagePanel() {
        try {
            VariablenBibliothek.backgroundImage = ImageIO.read(new File("1.png"));;
            VariablenBibliothek.characterImage = ImageIO.read(new File("CuteBoi.png"));
            VariablenBibliothek.faceImage = ImageIO.read(new File("Face.png"));
            VariablenBibliothek.textboxImage = ImageIO.read(new File("Textbox.png"));
            
            
        
            
        } catch (IOException ex) {
            Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
            ActionMap am = getActionMap();
            
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "space");
            am.put("space", new AbstractAction() {
                public void actionPerformed(ActionEvent evt) {
                    if(VariablenBibliothek.eventON == true) {
                        try {
                            VariablenBibliothek.frontImage = ImageIO.read(new File("empty.png"));
                        } catch (IOException ex) {
                            Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    sr.next();
                    repaint();
                }
            });
    }
    
    //Zeichnet alle Elemente auf das Fenster
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //Zeichnet alle Bilder (-25 aufgrund der Leiste oben)
        g.drawImage(VariablenBibliothek.backgroundImage, 0, 0, this);
        g.drawImage(VariablenBibliothek.characterImage, 0, 0, this);
        g.drawImage(VariablenBibliothek.textboxImage, 0, -25, this);
        g.drawImage(VariablenBibliothek.faceImage, 0, -25, this);
        
        //Name aufzeichnen
        g.setFont(VariablenBibliothek.nameFont);
        g.setColor(VariablenBibliothek.nameColor);
        g.drawString(VariablenBibliothek.name + ":", 200, 425);
        //Message aufzeichnen (TO DO: Maximale Breite (Vielleicht noch Buchstabe für Buchstabe lesen))
        g.setFont(VariablenBibliothek.messageFont);
        g.setColor(VariablenBibliothek.messageColor);
        g.drawString(VariablenBibliothek.message, 200, 450);
        
        g.drawImage(VariablenBibliothek.frontImage, 0, 0, this);
        
    }
    
}
