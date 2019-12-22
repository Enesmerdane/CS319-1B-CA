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
public class Victory extends DevCard {
    public Victory(String name, Player owner){
        super(name);

    }

  
    public boolean play(GameModel model) {
        return false;
    }
    
}
