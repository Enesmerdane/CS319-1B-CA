/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.models;

/**
 *
 * @author apple
 */
public class Insurance extends DevCard{
    public Insurance( String name){
        super(name);

    }
    public boolean play(GameModel model){
            if(this.getRecentlyBought())
                return false;
            
            // return settlement resources + city resources to player.
            int destroyedCities = model.getCurrentPlayer().getDestroyedCities();
            
            // 1 brick, 1 lumber, 3 grain, 1 wool, 3 ore per player
            // ore = 0, grain = 1, lumber = 2, wool = 3, brick = 4
            model.getCurrentPlayer().addSource(0, 3 * destroyedCities);
            model.getCurrentPlayer().addSource(1, 3 * destroyedCities);
            model.getCurrentPlayer().addSource(2, 1 * destroyedCities);
            model.getCurrentPlayer().addSource(3, 1 * destroyedCities);
            model.getCurrentPlayer().addSource(4, 1 * destroyedCities);
        return false;
    }
    
}
