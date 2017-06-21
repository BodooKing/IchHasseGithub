/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
    Boolean loading = false;
    

    public ScriptReader() {
        try {
            this.in = new BufferedReader(new FileReader(VariablenBibliothek.script + ".txt"));
            if (loading == false) {
            next();
            }
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
     * Choice =  DONE (Command/Choice/Anzahl)
     *                (Script/Text)
     *                ( . . .)
     * EventPic = DONE (Command/event/Name)
     * Change Background = DONE (Command/SwitchBackground/Name)
     * Exit Game = DONE (Command/End)
     */
    private void analyzeTextLine() {
        VariablenBibliothek.name = VariablenBibliothek.elements[1];
        VariablenBibliothek.message = VariablenBibliothek.elements[5];
        if (VariablenBibliothek.message.length() > 56) {
            splitWords();
        }
    }

    private void doCommand() {
        if (VariablenBibliothek.elements[1].toLowerCase().contains("switchscript") == true){
            switchScript(VariablenBibliothek.elements[2] + ".txt");
            VariablenBibliothek.script = VariablenBibliothek.elements[2];
            if (loading == false) {
            next();
            }
        }
        else if (VariablenBibliothek.elements[1].toLowerCase().contains("switchbackground") == true){
            try {
                VariablenBibliothek.backgroundImage = ImageIO.read(new File(VariablenBibliothek.elements[2] + ".png"));
                if (loading == false) {
                next();
                }
            } catch (IOException ex) {
                System.err.println("hintergrundbild konnte nicht geändert werden!");
            }
        }
        else if (VariablenBibliothek.elements[1].toLowerCase().contains("choice") == true){
            choice();
        }
        else if (VariablenBibliothek.elements[1].toLowerCase().contains("event") == true) {
            try {
                VariablenBibliothek.frontImage = ImageIO.read(new File(VariablenBibliothek.elements[2] + ".png"));
                VariablenBibliothek.eventON = true;
                        
            } catch (IOException ex) {
                System.err.println("FrontImage konnte nicht geladen werden!");
            }
        }
        else if (VariablenBibliothek.elements[1].toLowerCase().contains("end") == true) {
            System.exit(0);
        }
        else {
            System.err.println("Kein gültiges Kommando");
        }
    }

    private void splitWords() {
        String[] words = VariablenBibliothek.message.split(" ");
        String newMessage = "";
        int splitter = 64;
        for(int i = 0; i < words.length; i++) {
            if(newMessage.length() + words[i].length() < splitter) {
                newMessage = newMessage + " "+ words[i];
                
            }
            else {
                newMessage = newMessage + "\n" + words[i];
                splitter = splitter + splitter;
            }
        }
        VariablenBibliothek.message = newMessage;
    }
    
    public void save() {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter("Save1.txt");
            bw = new BufferedWriter(fw);
            bw.write(VariablenBibliothek.script);
            bw.newLine();
            bw.write(VariablenBibliothek.currentLine);
            bw.newLine();
            bw.write("This Save File was created by the GAME");
        } catch (IOException ex) {
            Logger.getLogger(ScriptReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(ScriptReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        VariablenBibliothek.saved = true;
    }
    
    public void load() {
        loading = true;
        String loadedString;
        try {
            BufferedReader load = new BufferedReader(new FileReader("Save1.txt"));
            VariablenBibliothek.script = load.readLine();
            loadedString = load.readLine();
            
            this.in = new BufferedReader(new FileReader(VariablenBibliothek.script + ".txt"));
            VariablenBibliothek.currentLine = in.readLine();
            while(true) {
                splitLine();
                analyzeLine();
                if(VariablenBibliothek.currentLine.contains(loadedString)) {
                    break;
                }
                else {
                    VariablenBibliothek.currentLine = in.readLine();
                }
                
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ScriptReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ScriptReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        loading = false;
        
    }

    private void choice() {
        VariablenBibliothek.choice = true;
        if (VariablenBibliothek.elements[2].contains("2")) {
            try {
                System.out.println("2");
                VariablenBibliothek.choices[0] = in.readLine();
                VariablenBibliothek.choices[1] = in.readLine();
                VariablenBibliothek.choices[2] = "null";
                VariablenBibliothek.choices[3] = "null";
            } catch (IOException ex) {
                Logger.getLogger(ScriptReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (VariablenBibliothek.elements[2].contains("3")) {
            try {
                System.out.println("3");
                VariablenBibliothek.choices[0] = in.readLine();
                VariablenBibliothek.choices[1] = in.readLine();
                VariablenBibliothek.choices[2] = in.readLine();
                VariablenBibliothek.choices[3] = "null";
            } catch (IOException ex) {
                Logger.getLogger(ScriptReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (VariablenBibliothek.elements[2].contains("4")) {
            try {
                System.out.println("4");
                VariablenBibliothek.choices[0] = in.readLine();
                VariablenBibliothek.choices[1] = in.readLine();
                VariablenBibliothek.choices[2] = in.readLine();
                VariablenBibliothek.choices[3] = in.readLine();
            } catch (IOException ex) {
                Logger.getLogger(ScriptReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            System.out.println("Falsche Anzahl an Auswahlmöglichkeiten!");
        }
    }
    
}
