/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Diese Klasse ist dafür zuständig den Script zu lesen und Commandos auszulesen, sowie die richtigen Bilder zu setzen.
 * @author BK
 */
public class ScriptReader {
    BufferedReader in;
    

    public ScriptReader() {
        try {
            this.in = new BufferedReader(new FileReader("1.txt"));
            VariablenBibliothek.currentLine = in.readLine();
            splitLine();
            analyzeLine();
        } catch (FileNotFoundException ex) {
            System.err.println("Script konnte nicht geladen werden!");
        } catch (IOException ex) {
            System.err.println("Zeile konnte nicht eingelesen werden!");
        }
    }
    
    public void splitLine() {
        VariablenBibliothek.elements = VariablenBibliothek.currentLine.split("/");
    }
    
    //Methode zum weiterführen des Spieles
    public void next() {
        try {
            VariablenBibliothek.currentLine = in.readLine();
            splitLine();
            analyzeLine();
        } catch (IOException ex) {
            System.err.println("Zeile konnte nicht geladen werden!");
        }
        
    }
    
    //Neues Script auslesen
    public void switchScript(String newName) {
        try {
            this.in = new BufferedReader(new FileReader(newName));
        } catch (FileNotFoundException ex) {
            System.err.println("Script-Switch fehlgeschlagen");
        }
    }

    void analyzeLine() {
        if(VariablenBibliothek.elements[0].toLowerCase().contains("text") == true) {
            analyzeTextLine();
        }
        else if (VariablenBibliothek.elements[0].toLowerCase().contains("command") == true){
            doCommand();
        }
        else {
            System.err.println("Keine gültige Zeile!");
        }
    }
    
    /**
     * Muster: (AnalyzeTextLine)
     * Indikator/Name (Auf der TextBox)/Charakter(zu Sehen)/Seine Emotion/Emotion OC/Message
     *     0              1                    2                     3         4       5
     * 
     * Commands:
     * SwitchScript = DONE (Commad/SwitchScript/Name)
     * Choice = UNDONE
     * Show Pic (NoText) = UNDONE
     * Show Pic (Text) = UNDONE
     * Change Background = UNDONE
     */
    private void analyzeTextLine() {
        VariablenBibliothek.name = VariablenBibliothek.elements[1];
        VariablenBibliothek.message = VariablenBibliothek.elements[5];
    }

    private void doCommand() {
        if (VariablenBibliothek.elements[1].toLowerCase().contains("switchscript") == true){
            switchScript(VariablenBibliothek.elements[2] + ".txt");
            next();
        }
        else if (VariablenBibliothek.elements[1].toLowerCase().contains("switchbackground") == true){
            try {
                VariablenBibliothek.backgroundImage = ImageIO.read(new File(VariablenBibliothek.elements[2] + ".png"));
                next();
            } catch (IOException ex) {
                System.err.println("hintergrundbild konnte nicht geändert werden!");
            }
        }
        else {
            System.err.println("Kein gültiges Kommando");
        }
    }
    
}
