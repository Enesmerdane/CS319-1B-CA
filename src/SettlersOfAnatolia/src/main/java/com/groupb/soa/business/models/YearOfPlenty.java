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
    private int selectedSource1;
    private int selectedSource2;
    
    public YearOfPlenty(String name){
        super(name); 
        selectedSource1 = -1;
        selectedSource2 = -1;
    }
    
    public boolean play(GameModel model){
        if( this.getRecentlyBought() == true)
            return false;
        if (selectedSource1 == -1 && selectedSource2 == -1)
            return false;
        model.getBank().subSource(selectedSource1, 1);
        model.getBank().subSource(selectedSource2, 1);
        model.getCurrentPlayer().addSource(selectedSource1, 1);
        model.getCurrentPlayer().addSource(selectedSource2, 1);
        return true;
    }
    
    public void setSelectedSource1( String sourceType)
    {
        switch (sourceType)
        {
            case "Ore":
                selectedSource1 = 0; break;
            case "Grain":
                selectedSource1 = 1; break;
            case "Lumber":
                selectedSource1 = 2; break;
            case "Wool":
                selectedSource1 = 3; break;
            case "Brick":
                selectedSource1 = 4; break;
            default:
                selectedSource1 = -1; break;
        }
    }
    
    public void setSelectedSource2( String sourceType)
    {
        switch (sourceType)
        {
            case "Ore":
                selectedSource2 = 0; break;
            case "Grain":
                selectedSource2 = 1; break;
            case "Lumber":
                selectedSource2 = 2; break;
            case "Wool":
                selectedSource2 = 3; break;
            case "Brick":
                selectedSource2 = 4; break;
            default:
                selectedSource2 = -1; break;
        }
    }
}
