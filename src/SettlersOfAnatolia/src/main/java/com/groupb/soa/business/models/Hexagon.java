package com.groupb.soa.business.models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;
import javafx.scene.paint.Color;

/**
 *
 * @author Alper
 */
public class Hexagon {

    // properties
    private Edge[] edges;
    private Vertex[] vertices;
    private int sourceType;
    private int number;
    private boolean hasRobber;
    
    // constructor(s)
    public Hexagon()
    {
        edges = new Edge[6];
        vertices = new Vertex[6];
        sourceType = -1;
        number = -1;
        hasRobber = false;
    }

    // methods
    public void setVertices( Vertex v1, Vertex v2, Vertex v3, Vertex v4, Vertex v5, Vertex v6)
    {
        vertices[0] = v1;
        vertices[1] = v2;
        vertices[2] = v3;
        vertices[3] = v4;
        vertices[4] = v5;
        vertices[5] = v6;
    }

    public void setEdges( Edge e1, Edge e2, Edge e3, Edge e4, Edge e5, Edge e6)
    {
        edges[0] = e1;
        edges[1] = e2;
        edges[2] = e3;
        edges[3] = e4;
        edges[4] = e5;
        edges[5] = e6;
    }

    public void setSourceType(int src)
    {
        sourceType = src;
    }

    public void assignNumber(int num)
    {
        number = num;
        hasRobber = num == 7;
    }

    public Edge[] getOccupiedEdges()
    {
        List<Edge> result = new ArrayList<>();
        for( Edge e: edges)
        {
            if( e.isOccupied())
                result.add(e);
        }

        return (Edge[]) result.toArray();
    }

    public Vertex[] getOccupiedVertices()
    {
        List<Vertex> result = new ArrayList<>();
        for( Vertex v: vertices)
        {
            if( v.isOccupied())
            {
                result.add(v);
            }
        }

        return (Vertex[]) result.toArray();
    }

    public int getSourceType()
    {
        return sourceType;
    }

    public int getNumber()
    {
        return number;
    }

    public boolean produceResource(PlayerList playerList)
    {
        if( number == 7)
            return false;

        if( hasRobber)
            return true; // return without producing anything, but return true because this is the intended function
        for( Vertex v: vertices)
        {
            if( v.isOccupied())
            {
                playerList.getPlayerWithColor( v.getOccupColor()).addSource(sourceType, v.getLevel());
            }
        }
        return true;
    }

    public boolean stealResource( PlayerList pl, Color playerColor)
    {
        // if this tile does not have the robber, return false.
        if( !hasRobber)
            return false;
        
        List<Color> potentialVictims = new ArrayList<>();
        // get the list of players the current player can steal from.
        for( Vertex v: vertices)
        {
            // if current vertex is occupied by someone other than the stealing player,
            // add to list.
            if( v.isOccupied() && !v.getOccupColor().equals(playerColor))
                potentialVictims.add(v.getOccupColor());
        }
        
        // if there are no potential victims, return false.
        if( potentialVictims.size() <= 0)
            return false;
        
        // else, proceed to steal.
        boolean victimFound = false;
        while( !victimFound)
        {
            // if all victims are searched and the player cannot steal from any one of them,
            // return false.
            if( potentialVictims.size() <= 0)
                return false;
            
            // else, choose a victim.
            Color victimColor = potentialVictims.get( (int) (Math.random() * potentialVictims.size()));
            // try to steal from this victim.
            boolean attempt = pl.getPlayerWithColor(playerColor).stealSourceFrom( pl.getPlayerWithColor(victimColor));
            
            // if successfully stolen from, then end this function.
            if( attempt)
                return true;
            
            // else, remove the current victim from the list of potential victims, and start over.
            else
            {
                potentialVictims.remove(victimColor);
            }
        }
        return true;
    }

    public boolean hasRobber()
    {
        return hasRobber;
    }

    public void moveRobber()
    {
        if( !hasRobber)
        {
            hasRobber = true;
        }
    }
    
    public void removeRobber()
    {
        if( hasRobber)
        {
            hasRobber = false;
        }
    }
}
