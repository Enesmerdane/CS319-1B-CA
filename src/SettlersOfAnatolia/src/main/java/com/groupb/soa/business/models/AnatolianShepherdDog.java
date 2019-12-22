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
public class AnatolianShepherdDog extends DevCard{
    
    AnatolianShepherdDog(String name)
    {
        super(name);
    }
    
    public boolean play( GameModel model)
    {
        if( getRecentlyBought())
            return false;
        
        model.getCurrentPlayer().setUsedAnatolianShepherdDog(true);
        return true;
    }
    
}
