/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Veni, Vidi, Amavi! - We came, we saw, we loved!
 * @author BK
 */
public class GameFrame extends JFrame{
    public GameFrame() {
        
        this.setTitle("Veni, Vidi, Amavi!"); 
        
        this.setSize(800,600);
        this.setResizable(false);
        this.setAutoRequestFocus(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        
        this.setContentPane(new ImagePanel());
        this.setVisible(true);
        
        
    }
}
