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
import java.awt.event.ActionListener;
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
    JButton choice1 = new JButton("Auswahl 1");
    JButton choice2 = new JButton("Auswahl 2");
    JButton choice3 = new JButton("Auswahl 3");
    JButton choice4 = new JButton("Auswahl 4");
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
                            System.err.println("FrontImage konnte nicht entfernt werden");
                        }
                    }
                    if (VariablenBibliothek.choice == false) {
                        sr.next();
                        
                    }
                    if(VariablenBibliothek.choice == true) {
                            drawChoices();
                        }
                    if(VariablenBibliothek.saved == true) {
                        VariablenBibliothek.saved = false;
                    }
                    repaint();
                }
            });
            
            
            
            this.setLayout(null);
            
            JButton save = new JButton("Speichern");
            JButton load = new JButton("Laden");
            JButton exit = new JButton("Verlassen");
            this.add(save);
            save.setFont(new Font("TimesRoman", Font.PLAIN, 12));
            save.setForeground(Color.WHITE);
            save.setOpaque(false);
            save.setContentAreaFilled(false);
            save.setBorderPainted(false);
            save.setFocusable(false);
            save.setBounds(500,535,95,25);
            save.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                    sr.save();
                    repaint();
                }
            });
        
            this.add(load);
            load.setFont(new Font("TimesRoman", Font.PLAIN, 12));
            load.setForeground(Color.WHITE);
            load.setOpaque(false);
            load.setContentAreaFilled(false);
            load.setBorderPainted(false);
            load.setFocusable(false);
            load.setBounds(600,535,75,25);
            load.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                    sr.load();
                    repaint();
                }
            });
        
            this.add(exit);
            exit.setFont(new Font("TimesRoman", Font.PLAIN, 12));
            exit.setForeground(Color.WHITE);
            exit.setOpaque(false);
            exit.setContentAreaFilled(false);
            exit.setBorderPainted(false);
            exit.setFocusable(false);
            exit.setBounds(680,535,100,25);
            exit.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.exit(0);
                }
            });
            
            
            
            this.add(choice1);
            choice1.setBounds(50,50,700,50);
            choice1.setEnabled(false);
            choice1.setVisible(false);
            this.add(choice2);
            choice2.setBounds(50,125,700,50);
            choice2.setEnabled(false);
            choice2.setVisible(false);
            this.add(choice3);
            choice3.setBounds(50,200,700,50);
            choice3.setEnabled(false);
            choice3.setVisible(false);
            this.add(choice4);
            choice4.setBounds(50,275,700,50);
            choice4.setEnabled(false);
            choice4.setVisible(false);
            
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
        drawString( g, VariablenBibliothek.message, 200, 425);
        
        if(VariablenBibliothek.saved == true) {
        g.setFont(VariablenBibliothek.savedFont);
        g.setColor(VariablenBibliothek.savedColor);
        g.drawString("Spielstand gespeichert!", 200,556);
        }
        
        
        g.drawImage(VariablenBibliothek.frontImage, 0, 0, this);
        
    }
        void drawString(Graphics g, String text, int x, int y) {
            for (String line : text.split("\n"))
        g.drawString(line, x, y += g.getFontMetrics().getHeight()); 
        }
        
        void drawChoices() {
            String[] choiceElements;
            String script1 = VariablenBibliothek.choices[0].split("/")[0];
            String script2 = VariablenBibliothek.choices[1].split("/")[0];
            String script3 = VariablenBibliothek.choices[2].split("/")[0];
            String script4 = VariablenBibliothek.choices[3].split("/")[0];
            for(int i = 0; i < VariablenBibliothek.choices.length; i++) {
                choiceElements = VariablenBibliothek.choices[i].split("/");
                if(i == 0) {
                    if(choiceElements[1].contains("null") == false) {
                        choice1.setText(choiceElements[1]);
                        choice1.setEnabled(true);
                        choice1.setVisible(true);
                        
                        choice1.addActionListener(new ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent e) {
                            sr.switchScript(script1 + ".txt");
                            disableChoices();                            
                            }
                        });
                    }                    
                }
                if(i == 1) {
                    if(choiceElements[1].contains("null") == false) {
                        choice2.setText(choiceElements[1]);
                        choice2.setEnabled(true);
                        choice2.setVisible(true);
                        
                        choice2.addActionListener(new ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent e) {
                            sr.switchScript(script2 + ".txt");
                            disableChoices();
                            }
                        });
                    }
                }
                if(i == 2) {
                    if(choiceElements[1].contains("null") == false) {
                        choice3.setText(choiceElements[1]);
                        choice3.setEnabled(true);
                        choice3.setVisible(true);
                        
                        choice3.addActionListener(new ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent e) {
                            sr.switchScript(script3 + ".txt");
                            disableChoices();
                            }
                        });
                    }
                }
                if(i == 3) {
                    if(choiceElements[1].contains("null") == false) {
                        choice4.setText(choiceElements[1]);
                        choice4.setEnabled(true);
                        choice4.setVisible(true);
                        
                        choice4.addActionListener(new ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent e) {
                            sr.switchScript(script4 + ".txt");
                            disableChoices();
                            }
                        });
                    }
                }
            }
        }
        
        void disableChoices() {
            choice1.setEnabled(false);
            choice1.setVisible(false);
            choice2.setEnabled(false);
            choice2.setVisible(false);
            choice3.setEnabled(false);
            choice3.setVisible(false);
            choice4.setEnabled(false);
            choice4.setVisible(false);
            VariablenBibliothek.choice = false;
            sr.next();
            repaint();
            
        }
}
