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
public abstract class DevCard {
    String name;
    boolean recentlyBought;
    Player owner;
    
    public DevCard(String name, Player owner){
        this.name = name;
        recentlyBought = false; //shows whether the dev card is recently bought
        this.owner = owner;
    }
    
    public void setRecentlyBought(boolean recent){
        recentlyBought = recent;
    }
    
    public boolean getRecentlyBought(){
        return recentlyBought; 
    }
    public abstract boolean play(GameModel model);
    
}
