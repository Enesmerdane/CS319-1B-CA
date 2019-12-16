package com.groupb.soa.business.models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Alper
 */
public class Edge {

    // properties
    private boolean occupied;
    private Color occupColor;
    private Vertex[] adjVertices;
    private int edgeNo;
    // constructor(s)
    public Edge(Vertex v1, Vertex v2, int edgeNo)
    {
        occupied = false;
        this.edgeNo = edgeNo;
        occupColor = Color.BLACK;
        adjVertices = new Vertex[2];
        adjVertices[0] = v1;
        adjVertices[1] = v2;
    }

    // methods
    
    public boolean isAdjacentVerticesOccupied(){
        if(adjVertices[0].isOccupied()) 
            return true;
        if(adjVertices[1].isOccupied()) 
            return true;
        return false;
    }
    
    public Color getOccupColor()
    {
        return occupColor;
    }

    public boolean isOccupied()
    {
        return occupied;
    }

    public boolean build( boolean first, Color playerColor, PlayerList pl)
    {
        return true;
//        // condition check
//        // there mustn't be a road in this edge
//        if( occupied)
//            return false;
//        // this road must be connected to at least 1 of player's constructs
//        // first check for settlements or cities.
//        boolean hasSettOrCity = adjVertices[0].getOccupColor() == playerColor
//                || adjVertices[1].getOccupColor() == playerColor;
//        // if there are no settlements or cities adjacent,
//        // check the edges of adjacent vertices.
//        if( !hasSettOrCity)
//        {
//            boolean verticesHaveRoad = false;
//            for( Vertex v: adjVertices)
//            {
//                for( Edge e: v.getEdges())
//                {
//                    if( e.getOccupColor() == playerColor)
//                    {
//                        verticesHaveRoad = true;
//                    }
//                }
//            }
//            if( !verticesHaveRoad)
//                return false;
//        }
//        // player must have enough resources
//        if( !first)
//        {
//            // 0 = ore, 1 = grain, 2 = lumber, 3 = wool, 4 = brick
//            // we need 1 brick & 1 lumber
//            boolean hasEnough = pl.getPlayerWithColor(playerColor).getSourceNo(4) >= 1 // check for brick
//                    && pl.getPlayerWithColor(playerColor).getSourceNo(2) >= 1; // check for lumber
//
//            if( !hasEnough)
//                return false;
//        }
//
//        // if all conditions are met, then
//
//        // get resources from player
//        pl.getPlayerWithColor(playerColor).subSource(4, 1); // subtract 1 brick
//        pl.getPlayerWithColor(playerColor).subSource(2, 1); // subtract 1 lumber
//        // initialize proper variables
//        occupied = true;
//        occupColor = playerColor;
//        // check for longest road?
//        return true;
    }
    
    public void draw( Line l)
    {
        // Buna ihtiyacımız var mı emin değilim -Enes
        if( !occupied)
        {
            //l.setFill(Color.BLACK);
        }
        
        else
        {
            l.setFill(occupColor);
        }
        
    }
}
