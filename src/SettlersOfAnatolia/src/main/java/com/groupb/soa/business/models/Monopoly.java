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
    
    int selectedSource;

    public Monopoly( String name, Player owner){
        super(name, owner);
        selectedSource = -1;
    }
    
    public  boolean play(GameModel model){
        //selected source check will be revised ?
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
}
