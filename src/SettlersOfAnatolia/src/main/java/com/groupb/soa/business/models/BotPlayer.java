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
    
    
    ArrayList<Integer> vertices;
   
    BotPlayer( Color color){
        super(color);
        vertices = new ArrayList<> ();
    }
    
    public void playTurn( GameModel model){

         System.out.println ("BEFORE IF");
         
        if ( model.getFirstTurn() || model.getSecondTurn()){
            int vertexIndex = (int)(Math.random() * 54);
            while ( !model.buildSettlement(vertexIndex)){
                vertexIndex = (int)(Math.random() * 54);
                
            }
            vertices.add(vertexIndex);
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
                while( !model.sendRobberToHexagon( temp ))
                    temp = (int)(Math.random() * 19);
            }
            if ( ! model.getThirdTurn() ){
                int random = (int)(Math.random() * 3);
                if ( random == 0) {
                    model.getCurrentPlayer().buyDevCard(model.getBank());
                    GameScreen.getInstance().refreshCardNumbers();
                }
                else if ( random == 1)  
                {
                    int counter = 0;
                    //source check 
                    if ( model.getCurrentPlayer().getSourceNo(4) >= 1 && model.getCurrentPlayer().getSourceNo(2) >= 1){
                        int edgeIndex = (int)(Math.random() * 72);
                        while ( !model.buildRoad( edgeIndex ) && counter < 25){
                            counter++;
                            edgeIndex = (int)(Math.random() * 72); 
                        } 
                        GameScreen.getInstance().paintEdge(edgeIndex);
                    }
                        
                }
                else if ( random == 2 ){
                    int counter = 0;
                    int vertex = (int)(Math.random() * 54);
                     while ( !model.buildSettlement(vertex) && counter < 25){
                        counter ++;
                        vertex = (int)(Math.random() * 54);
                    }
                     if ( counter < 25 ){
                         vertices.add(vertex);
                     }
                     GameScreen.getInstance().paintVertex(vertex);
                     GameScreen.getInstance().refreshScores();
                }
                else if (random == 3) 
                {
                    if( !vertices.isEmpty())
                    {
                        if ( model.buildCity(vertices.get(0)) ){ 
                            GameScreen.getInstance().paintCity( vertices.get(0) );
                            GameScreen.getInstance().refreshScores();
                        }   
                    }  

                }
                
                
                    
             }
        }
                
        System.out.println ("yeni oyuncu");
        GameScreen.getInstance().refreshResources();
        model.moveNextPlayer();
        
    }
    
  
}
