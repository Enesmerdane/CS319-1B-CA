/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.models;

import javafx.scene.canvas.GraphicsContext;


import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
/**
 *
 * @author goksuturan
 */
public class BotPlayer extends Player {
    
    BotPlayer( Color color){
        super(color);
    }
    
    public void playTurn( GameModel model){
         System.out.println ("BEFORE IF");
        if ( model.getFirstTurn() || model.getSecondTurn()){
            int vertexIndex = (int)(Math.random() * 54);
            while ( !model.buildSettlement(vertexIndex)){
                vertexIndex = (int)(Math.random() * 54);
            }
        
            int edgeIndex = (int)(Math.random() * 72);
            while ( !model.buildRoad( edgeIndex )){
                edgeIndex = (int)(Math.random() * 72);
            }
           }
        System.out.println ("yeni oyuncu");
        model.moveNextPlayer();
        
    }
    
  
}
