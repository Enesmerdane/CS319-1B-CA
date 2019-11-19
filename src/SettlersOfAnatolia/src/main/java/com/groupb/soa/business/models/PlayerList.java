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

public class PlayerList implements IGameObject {
    Player[] players;
    int currentPlayerNo;
    int[] colors = { 0, 1, 2, 3}; // each number will be associated with an integer

    PlayerList(){
        players = new Player[4];
        for ( int i = 0; i < 4; i++){
                players[i] = new Player( colors[i]);

        }
        currentPlayerNo = 0;
    }

    public Player getCurrentPlayer(){
        return players[currentPlayerNo];
    }

    public Player getPlayerWithColor( int color ){
        for ( int i = 0; i < 4; i++){
            if ( players[currentPlayerNo].getColor() == color)
                return players[currentPlayerNo];
        }
        return null;

    }

    public void next(){
        currentPlayerNo = (currentPlayerNo++) % 4;
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

