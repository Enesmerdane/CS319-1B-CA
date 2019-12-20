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
public class Monopoly extends DevCard{
    
    private int selectedSource;

    public Monopoly( String name){
        super(name);
        selectedSource = -1;
    }
    
    public boolean play(GameModel model){
       if ( this.getRecentlyBought())
           return false;
       if( selectedSource == -1)
           return false;
       int current = model.getPlayerList().getCurrentPlayerNo();
       for( int i = 0; i < 4; i++){
           if ( i != current ){
               while(model.getPlayerList().getPlayer(i).subSource( selectedSource, 1)){ //until the source number becomes zero 
                   model.getPlayerList().getCurrentPlayer().addSource(selectedSource, 1);
               }
           }
        }
    
        return true;
    }
    
    public void setSelectedSource(String sourceType)
    {
        // ore = 0, grain = 1, lumber = 2, wool = 3, brick = 4
        switch (sourceType)
        {
            case "Ore":
                selectedSource = 0; break;
            case "Grain":
                selectedSource = 1; break;
            case "Lumber":
                selectedSource = 2; break;
            case "Wool":
                selectedSource = 3; break;
            case "Brick":
                selectedSource = 4; break;
            default:
                selectedSource = -1; break;
        }
    }
}
