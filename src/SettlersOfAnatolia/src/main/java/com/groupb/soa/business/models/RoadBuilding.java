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
public class RoadBuilding extends DevCard {
    
    int selectedEdge1;
    int selectedEdge2;
    
    public RoadBuilding(String name,Player owner){
        super(name, owner); 
        selectedEdge1 = -1;
        selectedEdge2 = -1;
    }
    
    public void setSelectedEdgeFirst(int index){
        selectedEdge1 = index;
    }
    
    public void setSelectedEdgeSecond(int index){
        selectedEdge2 = index;
    }
    
    public boolean play(GameModel model){
        //card is bought in the current turn
        if( recentlyBought )
            return false;
        //edges are not set
        if( selectedEdge1 == -1 || selectedEdge2 == -1)
            return false;
        // if buildable build road to edges
        return model.buildRoad(selectedEdge1) && model.buildRoad(selectedEdge2);
        
    }
        
    }
    
    
    
    
    

