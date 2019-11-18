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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Dice extends LocatableObject implements IGameObject {
    int value;
    public static final String IMG1_PATH = ""; // to be added
    public static final String IMG2_PATH = ""; // to be added
    public static final String IMG3_PATH = ""; // to be added
    public static final String IMG4_PATH = ""; // to be added
    public static final String IMG5_PATH = ""; // to be added
    public static final String IMG6_PATH = ""; // to be added


    Dice(double x, double y ){
        super(x, y);
        value = 0;
    }
    public int rollDice(){
        value = (int)(Math.random()*6 + 1);
        return value;
    }

    @Override
    public void render(GraphicsContext gc) {
        Image image;
        if ( value == 1){
            image = new Image("IMG1_PATH");
        }
        else if ( value == 2){
            image = new Image("IMG2_PATH");
        }
        else if ( value == 3){
            image = new Image("IMG3_PATH");
        }
        else if ( value == 4){
            image = new Image("IMG4_PATH");
        }
        else if ( value == 5){
            image = new Image("IMG5_PATH");
        }
        else if ( value == 6){
            image = new Image("IMG6_PATH");
        }
        else{
            image = null;
        }
        if ( image != null)
            gc.drawImage(image, x, y );
    }

}

