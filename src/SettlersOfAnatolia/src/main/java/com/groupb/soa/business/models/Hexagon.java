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
        if( !hasRobber)
            return false;

        // get potential players that the player can steal from
        List<Color> potentialPlayers = new ArrayList<>();
        for( Vertex v: vertices)
        {
            if( v.isOccupied() && v.getOccupColor() != playerColor)
            {
                potentialPlayers.add(v.getOccupColor());
            }
        }
        // choose one random player to steal from
        Color victimColor = potentialPlayers.get( (int) (Math.random() * potentialPlayers.size()));
        // steal one random source from this player
        // I DON'T KNOW IF THIS WORKS!
        boolean steal = pl.getPlayerWithColor(playerColor).stealSourceFrom(pl.getPlayerWithColor(victimColor));
        while( !steal)
        {
            // if steal is false, this means that the selected potential player has no resources. try again
            // remove the previous potential victim from the list of potential players
            potentialPlayers.remove( potentialPlayers.lastIndexOf(victimColor));
            // select another random victim
            victimColor = potentialPlayers.get( (int) (Math.random() * potentialPlayers.size()));
            // steal one random source from this player
            pl.getPlayerWithColor(playerColor).stealSourceFrom(pl.getPlayerWithColor(victimColor));
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
}
