/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Diese Klasse ist dafür zuständig den Script zu lesen und Commandos auszulesen, sowie die richtigen Bilder zu setzen.
 * @author BK
 */
public class ScriptReader {
    BufferedReader in;
    String currentLine;

    public ScriptReader() {
        try {
            this.in = new BufferedReader(new FileReader("1.txt"));
            try {
                System.out.println(in.readLine());
            } catch (IOException ex) {
                Logger.getLogger(ScriptReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Text konnte nicht geladen werden!");
        }
    }
    
    //Methode zum weiterführen des Spieles
    public void next() {
        
    }
    
}
