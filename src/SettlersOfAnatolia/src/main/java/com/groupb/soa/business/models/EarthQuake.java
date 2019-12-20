/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.models;

/**
 *
 * @author Irmak Demir
 */
public class EarthQuake implements Event {
    GameModel gameModel;
    public EarthQuake(GameModel gameModel){
        this.gameModel = gameModel;
    }

    public void occur(GameModel model) {
        //model.destroyAllCities();
    }
}
