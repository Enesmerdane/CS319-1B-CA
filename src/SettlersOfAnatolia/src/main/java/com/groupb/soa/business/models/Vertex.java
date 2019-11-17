/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.models;

import java.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Alper
 */
public class Vertex {
    
    // properties
    public static final String SETTLEMENT_IMAGE_PATH = ""; // to be added
    public static final String CITY_IMAGE_PATH = ""; // to be added
    private boolean occupied;
    private List<Vertex> adjList;
    private List<Edge> edges;
    private int vertexNo;
    private int occupColor; // private Player occupier?
    private boolean isPort;
    private int level;
    // constructor(s)
    public Vertex(int vNo, boolean port)
    {
        occupied = false;
        adjList = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        vertexNo = vNo;
        occupColor = 0;
        isPort = port;
        level = 0;
    }
    
    // methods
    protected Edge addEdge( Vertex v, int edgeNo)
    {
        Edge newEdge = new Edge( this, v, edgeNo);
        adjList.add( v);
        v.adjList.add( this);
        edges.add( newEdge);
        v.edges.add( newEdge);
        return newEdge;
    }
    
    public void draw( GraphicsContext gc)
    {
        double X = 300.0;
        double Y = 500.0;
        if( level > 0)
        {
            Image image;
            if(level == 1)
            {
                image = new Image( SETTLEMENT_IMAGE_PATH);
            }
            
            else if( level == 2)
            {
                image = new Image( CITY_IMAGE_PATH);
            }
            
            else
            {
                image = null;
            }
            
            if( image != null)
            {
                gc.drawImage(image, X, Y);
            }
        }
    }
    
    public boolean build(boolean first, int playerColor, PlayerList pl) // boolean build(Player p)
    {
        // condition check
            // if not first, player must have a road in the neighboring edges
           if( !first)
           {
               boolean hasRoad = false;
               for( Edge e: edges)
               {
                   if( e.isOccupied() && e.getOccupColor() == playerColor)
                       hasRoad = true;
               }
               
               if( !hasRoad)
                   return false;
           }
            // if not first, player must have enough resources to build
            if( !first)
            {
                // 0 = ore, 1 = grain, 2 = lumber, 3 = wool, 4 = brick
                boolean hasEnough = pl.getPlayerWithColor(playerColor).getSourceNo(4) >= 1 // check for brick
                        && pl.getPlayerWithColor(playerColor).getSourceNo(2) >= 1 // check for lumber
                        && pl.getPlayerWithColor(playerColor).getSourceNo(3) >= 1 // check for wool
                        && pl.getPlayerWithColor(playerColor).getSourceNo(1) >= 1; // check for grain
                if( !hasEnough)
                    return false;
            }
            
            // the vertex must not be occupied or blocked
        if( level != 0)
            return false;
        // if all above conditions are true, then
        // subtract resources
        pl.getPlayerWithColor(playerColor).subSource(4, 1); // subtract 1 brick
        pl.getPlayerWithColor(playerColor).subSource(2, 1); // subtract 1 lumber
        pl.getPlayerWithColor(playerColor).subSource(3, 1); // subtract 1 wool
        pl.getPlayerWithColor(playerColor).subSource(1, 1); // subtract 1 grain
        // change the proper values
        occupied = true;
        level = 1;
        occupColor = playerColor;
        // award 1 victory point?? (do we award them here?)
        return true;
    }
    
    public boolean upgrade(int playerColor, PlayerList pl)
    {
        // condition check
        // player must have enough resources to build
        // 0 = ore, 1 = grain, 2 = lumber, 3 = wool, 4 = brick
        // we need 3 ore & 2 grain
        boolean hasEnough = pl.getPlayerWithColor(playerColor).getSourceNo(0) >= 3 // check for ore
                        && pl.getPlayerWithColor(playerColor).getSourceNo(1) >= 2; // check for grain
        if( !hasEnough)
            return false;
        // player must have a settlement in this vertex
        if( occupColor != playerColor)
            return false;
        // if all above conditions are true, then
        // subtract resources
        pl.getPlayerWithColor(playerColor).subSource(0, 3); // subtract 3 ore
        pl.getPlayerWithColor(playerColor).subSource(1, 2); // subtract 2 grain
        // change the proper values
        level = 2;
        // award victory points?? (do we award them here?)
        // return true
        return true;
    }
    
    public int getVertexNo()
    {
        return vertexNo;
    }
    
    public boolean isOccupied()
    {
        return occupied;
    }
    
    public int getOccupColor()
    {
        return occupColor;
    }
    
    public boolean isPort()
    {
        return isPort;
    }
    
    public int getLevel()
    {
        return level;
    }
    
    public List<Vertex> getAdjList()
    {
        return adjList;
    }
    
    public List<Edge> getEdges()
    {
        return edges;
    }
}
