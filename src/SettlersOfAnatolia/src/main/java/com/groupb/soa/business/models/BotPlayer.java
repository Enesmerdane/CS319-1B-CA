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
import com.groupb.soa.presentation.GameScreen;
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
            GameScreen.getInstance().paintVertex(vertexIndex);

            int edgeIndex = (int)(Math.random() * 72);
            while ( !model.buildRoad( edgeIndex )){
                edgeIndex = (int)(Math.random() * 72);
            }
            GameScreen.getInstance().paintEdge(edgeIndex);
        } 
        else {
            int dice = GameScreen.getInstance().botRollsDice();
            if ( dice == 7){
                int temp = (int)(Math.random() * 19);
                while( !model.sendRobberToHexagon(temp ))
                    temp = (int)(Math.random() * 19);
            }
            if ( ! model.getThirdTurn() ){
                int random = (int)(Math.random() * 2);
                if ( random == 0) {
                    model.getCurrentPlayer().buyDevCard(model.getBank());
                    GameScreen.getInstance().refreshCardNumbers();
                }
                else if ( random == 1)  
                {
                    if ( model.getCurrentPlayer().getSourceNo(4) >= 1 && model.getCurrentPlayer().getSourceNo(2) >= 1){
                        int edgeIndex = (int)(Math.random() * 72);
                        while ( !model.buildRoad( edgeIndex )){
                            edgeIndex = (int)(Math.random() * 72); 
                        } 
                        GameScreen.getInstance().paintEdge(edgeIndex);
                    }
                        
                }
                
                   
                    
             }
        }
                
        System.out.println ("yeni oyuncu");
        model.moveNextPlayer();
        
    }
    
  
}
