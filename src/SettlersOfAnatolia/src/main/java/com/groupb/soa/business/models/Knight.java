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
public class Knight extends DevCard{

    
    public Knight(String name){
        super(name); 
    }
    
    public boolean play(GameModel model){
        if( recentlyBought == true)
            return false;
        
        model.addRobberMove();
        return true;
    }
}
    
