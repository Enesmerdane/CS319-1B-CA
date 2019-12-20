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
    private String name;
    private boolean recentlyBought;
    private Player owner;
    
    public DevCard(String name){
        this.name = name;
        recentlyBought = true; //shows whether the dev card is recently bought
    }
    
    public void setRecentlyBought(boolean recent){
        recentlyBought = recent;
    }
    
    public boolean getRecentlyBought(){
        return recentlyBought; 
    }
    public abstract boolean play(GameModel model);
    
}
