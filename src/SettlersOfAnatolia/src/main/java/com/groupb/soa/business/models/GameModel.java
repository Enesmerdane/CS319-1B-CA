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
import javafx.scene.paint.Color;

public class GameModel {
    private GameTile tile;
    private PlayerList playerList;
    private Bank bank;
    private Dice dice;
    private Dice dice2;
    private int queue;
    private int turn;
    private boolean firstTurn;
    private boolean secondTurn;
    
    public GameModel(Color[] playerColors) {
        tile = new GameTile();
        playerList = new PlayerList(playerColors);
        bank = new Bank();
        dice = new Dice(0.0,0.0);
        dice2 = new Dice(0.0,0.0);
        turn = 0;
        queue = 0;
        firstTurn = true;
        secondTurn = false;
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
        //playerList.next(); Bu durumda player kendisi basamıyor nextTurn e o yüzden bunu aşağıda bir metoda alıyorum
    }

    public boolean buildRoad(int index ){
        boolean result = tile.buildRoad(index, playerList.getCurrentPlayer().getColor(),  playerList,  firstTurn || secondTurn);
        if(result){
            System.out.println("GameModel: Building Road by " + playerList.getCurrentPlayer().getColor().toString() + " on the Edge " + index);
        } else {
            System.out.println("GameModel: Building Road by " + playerList.getCurrentPlayer().getColor().toString() + " on the Edge " + index + "FAILED for some reasons");
        }
        return result;
        //tile.buildRoad(index, playerList.getCurrentPlayer().getColor(),  playerList,  false);
    }
    public boolean buildSettlement(int index){
        
        boolean result = tile.buildVertex( index, playerList.getCurrentPlayer().getColor(),  playerList, firstTurn, secondTurn );
        if(result){
           System.out.println("GameModel: Building Settlement by " + playerList.getCurrentPlayer().getColor().toString() + " on the Vertex " + index);
           playerList.getCurrentPlayer().increaseScore( 1);
        } else {
            System.out.println("GameModel: Building Settlement by "+ playerList.getCurrentPlayer().getColor().toString() + " on the Vertex " + index + " FAILED for some reasons");
        }
        return result;

    }
    public boolean buildCity(int index){
        boolean result =  tile.upgradeVertex(  index,playerList.getCurrentPlayer().getColor(), playerList);
        if(result){
            System.out.println("GameModel: Building City by "+ playerList.getCurrentPlayer().getColor().toString() + " on the Vertex " + index);
            playerList.getCurrentPlayer().increaseScore(2) ;
        } else {
            System.out.println("GameModel: Building City by "+ playerList.getCurrentPlayer().getColor().toString() + " on the Vertex " + index + " FAILED for some reasons");
        }

       return result;
    }

    public Color getCurrentPlayerColor(){
        return playerList.getCurrentPlayer().getColor();
    }
    
    public Bank getBank(){
        return bank;
    }

    public void moveNextPlayer(){
        // playerList.next()'s stay parameter is set to 'true'
        // when queue == 3 or 7. This is because in the first 2 rounds,
        // one player gets to play twice at the end.
        playerList.next( secondTurn, queue == 3 || queue == 7);
        queue++;
        turn = queue / 4;
        firstTurn = (turn == 0);
        secondTurn = (turn == 1);
        System.out.println("Game Turn is increased " + turn);
        }
    }

