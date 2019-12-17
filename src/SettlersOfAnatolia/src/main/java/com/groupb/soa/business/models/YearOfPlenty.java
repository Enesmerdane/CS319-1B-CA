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
public class YearOfPlenty extends DevCard{
    int selectedSource1;
    int selectedSource2;
    
    public YearOfPlenty(String name,Player owner){
        super(name, owner); 
        selectedSource1 = -1;
        selectedSource2 = -1;
    }
    
    public boolean play(GameModel model){
        if( recentlyBought == true)
            return false;
        if (selectedSource1 == -1 && selectedSource2 == -1)
            return false;
        model.getBank().subSource(selectedSource1, 1);
        model.getBank().subSource(selectedSource2, 1);
        owner.addSource(selectedSource1, 1);
        owner.addSource(selectedSource2, 1);
        return true;
    }
}
