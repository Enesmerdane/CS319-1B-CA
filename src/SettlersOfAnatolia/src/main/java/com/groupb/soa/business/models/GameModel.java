/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.models;

/**
 *
 * @author Göksu
 */
import java.util.ArrayList;

public class GameModel {
    GameTile tile;
    PlayerList playerList;
    Bank bank;
    Dice dice;
    Dice dice2;


    public GameModel() {
        tile = new GameTile();
        playerList = new PlayerList();
        bank = new Bank();
        dice = new Dice(0.0,0.0);
        dice2 = new Dice(0.0,0.0);
    }
    // player rolls the dice and sources are distributed
    public void playTurn(){
        int diceNo = dice.rollDice();
        int diceNo2 = dice2.rollDice();
        ArrayList<Hexagon> hexagonList =  tile.getHexsWithValue(diceNo+diceNo2);
        for(int i= 0; i < hexagonList.size(); i++){
           Vertex[] occupVertex =  hexagonList.get(i).getOccupiedVertices();
           for(int j = 0; j < occupVertex.length ; j++ ){
               playerList.getPlayerWithColor( occupVertex[j].getOccupColor()).addSource( hexagonList.get(i).getSourceType(), 1);
               bank.subSource( hexagonList.get(i).getSourceType(), 1);
           }
        }
        playerList.next();
    }

    public boolean buildRoad(int index ){
      return tile.buildRoad(index, playerList.getCurrentPlayer().getColor(),  playerList,  false);
    }
    public boolean buildSettlement(int index){

       boolean result = tile.buildVertex( index, playerList.getCurrentPlayer().getColor(),  playerList,false );
       if(result)
           playerList.getCurrentPlayer().increaseScore( 1);
       return result;

    }
    public boolean buildCity(int index){
       boolean result =  tile.upgradeVertex(  index,playerList.getCurrentPlayer().getColor(), playerList);
       if(result)
           playerList.getCurrentPlayer().increaseScore( 2) ;

       return result;
    }





}
