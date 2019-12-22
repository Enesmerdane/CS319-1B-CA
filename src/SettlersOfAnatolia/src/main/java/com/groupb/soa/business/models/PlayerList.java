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
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class PlayerList{
    private Player[] players;
    private int currentPlayerNo;
    private int[] colors = { 0, 1, 2, 3}; // each number will be associated with an integer -> No they wont :)) -Enes

    PlayerList(Color playerColors[], boolean isBot ){
        if( isBot){
            System.out.println("irem");
            players = new Player[4];
            players[0] = new Player( playerColors[0]); //first player is the actual player
            for ( int i = 1; i < 4; i++){
                    players[i] = new BotPlayer( playerColors[i]);
            }
            currentPlayerNo = 0;
        }
        else{
            System.out.println("göksu");
            players = new Player[4];
            players[0] = new Player( playerColors[0]); //first player is the actual player
            for ( int i = 1; i < 4; i++){
                players[i] = new Player( playerColors[i]);
        }
        currentPlayerNo = 0;
        }
    }

    public Player getCurrentPlayer(){
        return players[currentPlayerNo];
    }
    
    public int getCurrentPlayerNo(){
        return currentPlayerNo;
    }
    
    public Player getPlayer(int i){
        return players[i];
    }

    public Player getPlayerWithColor( Color color ){
        for ( int i = 0; i < 4; i++){
            if ( players[i].getColor().equals(color))
                return players[i];
        }
        return null;

    }

    public boolean next( boolean secondTurn, boolean stay){
        getCurrentPlayer().refreshCards();
        getCurrentPlayer().setCanBuyDevCard(true);
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
    
    public void AllRemoveHalf()
    {
        for( int i = 0; i < 4; i++)
        {
            players[i].removeHalfResources();
        }
    }
}

