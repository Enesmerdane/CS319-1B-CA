/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.controller;

/**
 *
 * @author Irmak
 */
public class GameController {
    GameModel gameModel; 
    public GameModel()
    {
        gameModel = new GameModel();
    }
    
    public void startGame()
    {
        GameModel newGame = new GameModel (); 
        //updateView(); 
    }
    public void buildRoad( int index)
    {
        gameModel.buildRoad(index);
    }
    
    public void upgradeCity( int index)
    {
        gameModel.upgradeCity(index);
    }
    public void buildSettlement(int index)
    {
        gameModel.buildSettlement(index);
    }
    public void render()
    {
        gameModel.render();
    }
    public void dice()
    {
        gameModel.rollDice();
    }
}
