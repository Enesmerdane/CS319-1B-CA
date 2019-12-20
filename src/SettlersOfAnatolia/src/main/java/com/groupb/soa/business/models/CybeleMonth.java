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
public class CybeleMonth implements Event{
    GameModel gameModel;
    public CybeleMonth(GameModel gameModel){
        this.gameModel = gameModel;
    }

    @Override
    public void occur(GameModel model) {
        //model.setCybeleMonth(true);
    }
}

