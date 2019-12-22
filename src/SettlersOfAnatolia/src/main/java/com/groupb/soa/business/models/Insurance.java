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

        if(model.getEarthquake()){

            if(this.getRecentlyBought())
                return false;

            int current = model.getPlayerList().getCurrentPlayerNo();
            if((model.getPlayerCityNo())[current] > 0){
                model.getCurrentPlayer().addSource(0,3*model.getPlayerCityNo()[current]);
                model.getCurrentPlayer().addSource(1,2*model.getPlayerCityNo()[current]);

                model.getBank().subSource(0,3*model.getPlayerCityNo()[current]);
                model.getBank().subSource(1,2*model.getPlayerCityNo()[current]);
                return true;
            }
        }
        if(model.getFlood()){
            if(this.getRecentlyBought())
                return false;

        }

        return false;
    }
    
}
