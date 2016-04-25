/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.terweb.packageInterrogationDonnees;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author proprietaire
 */
public class Object_RapportCalculMatrice {
    
    private File fichierCalcule;
    private ArrayList<String> message;

    public Object_RapportCalculMatrice(File fichierCalcule, ArrayList<String> message) {
    	
        this.fichierCalcule = fichierCalcule;
        this.message = message;
    }
    
    public Object_RapportCalculMatrice() {
        this.fichierCalcule = null;
        this.message = new ArrayList<>();
    }
    public File getFichierCalcule() {
        return fichierCalcule;
    }

    public void setFichierCalcule(File fichierCalcule) {
        this.fichierCalcule = fichierCalcule;
    }

    public ArrayList<String> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<String> msg) {
    	
    	this.message=msg;

    }
    
    
}
