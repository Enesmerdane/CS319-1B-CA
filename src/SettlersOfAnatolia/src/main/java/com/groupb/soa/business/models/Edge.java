/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.models;

/**
 *
 * @author Alper
 */
public class Edge {
    
    // properties
    private boolean occupied;
    private int occupColor;
    private Vertex[] adjVertices;
    private int edgeNo;
    // constructor(s)
    public Edge(Vertex v1, Vertex v2, int edgeNo)
    {
        occupied = false;
        this.edgeNo = edgeNo;
        occupColor = 0;
        adjVertices = new Vertex[2];
        adjVertices[0] = v1;
        adjVertices[1] = v2;
    }
    
    // methods
    
    public int getOccupColor()
    {
        return occupColor;
    }
    
    public boolean isOccupied()
    {
        return occupied;
    }
    
    public boolean build( boolean first, int playerColor)
    {
        // condition check
            // there mustn't be a road in this edge
        if( occupied)
            return false;  
        // this road must be connected to at least 1 of player's constructs
        // first check for settlements or cities.
        boolean hasSettOrCity = adjVertices[0].getOccupColor() == playerColor
                            || adjVertices[1].getOccupColor() == playerColor;
        // if there are no settlements or cities adjacent,
        // check the edges of adjacent vertices.
        if( !hasSettOrCity)
        {
            boolean verticesHaveRoad = false;
            for( Vertex v: adjVertices)
            {
                for( Edge e: v.getEdges())
                {
                    if( e.getOccupColor() == playerColor)
                    {
                        verticesHaveRoad = true;
                    }
                }
            }
            if( !verticesHaveRoad)
                return false;
        }
        // player must have enough resources
        if( !first)
        {
            // TO_DO
        }
        
        // if all conditions are met, then
        
        // get resources from player
        // TO_DO
        
        // initialize proper variables
        occupied = true;
        occupColor = playerColor;
        return true;               
    }
}
