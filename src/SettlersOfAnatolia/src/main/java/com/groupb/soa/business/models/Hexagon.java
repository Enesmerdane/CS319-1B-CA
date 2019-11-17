/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.models;

import java.util.*;

/**
 *
 * @author Alper
 */
public class Hexagon {
    
    // properties
    private Edge[] edges;
    private Vertex[] vertices;
    int sourceType;
    int number;
    
    // constructor(s)
    public Hexagon()
    {
        edges = new Edge[6];
        vertices = new Vertex[6];
        sourceType = -1;
        number = -1;
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
}
