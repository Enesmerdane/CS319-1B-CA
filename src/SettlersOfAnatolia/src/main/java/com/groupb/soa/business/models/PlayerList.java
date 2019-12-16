/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.models;

/**
 *
 * @author İrem Kırmacı
 */
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class PlayerList implements IGameObject {
    Player[] players;
    int currentPlayerNo;
    int[] colors = { 0, 1, 2, 3}; // each number will be associated with an integer -> No they wont :)) -Enes

    PlayerList(Color playerColors[] ){
        players = new Player[4];
        for ( int i = 0; i < 4; i++){
                players[i] = new Player( playerColors[i]);

        }
        currentPlayerNo = 0;
    }

    public Player getCurrentPlayer(){
        return players[currentPlayerNo];
    }

    public Player getPlayerWithColor( Color color ){
        for ( int i = 0; i < 4; i++){
            if ( players[currentPlayerNo].getColor() == color)
                return players[currentPlayerNo];
        }
        return null;

    }

    public boolean next( boolean secondTurn, boolean stay){
        if( !stay)
        {
            if( secondTurn)
        {
            currentPlayerNo--;
            if( currentPlayerNo == -1)
            {
                currentPlayerNo = 3;
            }
        }
        else
        {
            currentPlayerNo++;
            if( currentPlayerNo == 4)
            {
                currentPlayerNo = 0;
            }
        }
        }
        System.out.println("Current player no: " + currentPlayerNo);
        if(currentPlayerNo == 0){ // if the next turn begins
            return true;
        }
        return false;
    }

    public void render(GraphicsContext gc) {
        for ( int i = 0; i < 4; i++){
            players[i].render(gc);
        }

    }

    @Override
    public void render(Node n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

