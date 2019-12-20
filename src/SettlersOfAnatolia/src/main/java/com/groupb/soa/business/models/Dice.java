/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.models;

/**
 *
 * @author goksuturan
 */
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Dice extends LocatableObject{
    private int value;
    public static final String IMG1_PATH = ""; // to be added
    public static final String IMG2_PATH = ""; // to be added
    public static final String IMG3_PATH = ""; // to be added
    public static final String IMG4_PATH = ""; // to be added
    public static final String IMG5_PATH = ""; // to be added
    public static final String IMG6_PATH = ""; // to be added


    public Dice(double x, double y ){
        super(x, y);
        value = 0;
    }
    public int rollDice(){
        value = (int)(Math.random()*6 + 1);
        return value;
    }
    
    public int getValue()
    {
        return value;
    }

}

