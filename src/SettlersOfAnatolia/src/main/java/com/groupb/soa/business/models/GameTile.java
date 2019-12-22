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
public class GameTile {

    // properties
    private Hexagon[] hexagons;
    private Edge[] edges;
    private Vertex[] vertices;
    // constructor(s)
    public GameTile()
    {
        vertices = new Vertex[54];
        for( int i = 0; i < vertices.length; i++)
        {
            vertices[i] = new Vertex(i , false); // vertex with number i becomes ith member of array
        }
        edges = new Edge[72];
        hexagons = new Hexagon[19];
        for( int i = 0; i < hexagons.length; i++)
        {
            hexagons[i] = new Hexagon();
        }
        initializeGraph();
        distributeDiceNumbers();
        distributeSources();
    }
    // methods

    public Vertex getVertexWithId( int index ){
        return vertices[index];
    }

    public Edge getEdgeWithId( int index ) {
        return edges[index];
    }

    public boolean buildVertex( int index, Color playerColor, PlayerList pl, boolean firstTurn, boolean secondTurn )
    {
        System.out.println("Game tile a ulaştık");
        return vertices[index].build(firstTurn, secondTurn, playerColor, pl);
    }

    public boolean upgradeVertex( int index, Color playerColor, PlayerList pl)
    {
        return vertices[index].upgrade(playerColor, pl);
    }

    public boolean buildRoad(int index, Color playerColor, PlayerList pl, boolean first)
    {
        boolean result = edges[index].build(first, playerColor, pl);
        if( result)
            System.out.println( "-----" + findLongestRoad(pl, playerColor, index) + "-----");
        return result;
    }

    public ArrayList<Hexagon> getHexsWithValue(int value) {
        ArrayList<Hexagon> hexagonList = new ArrayList();
        for ( int i = 0; i < 19; i++){
            if ( hexagons[i].getNumber() == value )
                hexagonList.add(hexagons[i]);
        }
        return hexagonList;
    }
    
    public boolean produceResources(int sum, PlayerList pl)
    {
        if( sum == 7)
            return false;
        boolean result = true;
        for( Hexagon h : hexagons)
        {
            if( h.getNumber() == sum )
            {
                result &= h.produceResource(pl);
            }
        }
        return result;
    }
    
    public boolean sendRobberToHexagon( int index, PlayerList pl)
    {
        if( hexagons[index].hasRobber())
            return false;
        
        for( Hexagon h: hexagons)
        {
            if( h.hasRobber())
                h.removeRobber();
        }
        hexagons[index].moveRobber();
        return hexagons[index].stealResource(pl, pl.getCurrentPlayer().getColor());
    }

    public int findLongestRoad(PlayerList pl, Color playerColor, int index)
    {
        Set<Integer> visited = new HashSet<>();
        return findLongestRoadHelper( visited, playerColor, index, -1);
    }
    
    public void completeLongestRoadCheck( PlayerList pl)
    {
        Color[] playerColors = new Color[4];
        playerColors[0] = pl.getPlayer(0).getColor();
        playerColors[1] = pl.getPlayer(1).getColor();
        playerColors[2] = pl.getPlayer(2).getColor();
        playerColors[3] = pl.getPlayer(3).getColor();
        Set<Integer> visited = new HashSet<>();
        // for all edges in the game tile...
        for( Edge e: edges)
        {
            // if an edge is occupied and isn't visited,
            if( e.isOccupied() && !visited.contains( e.getEdgeNo()))
            {
                // run a findLongestRoadHelper from that edge, and store the number.
                int result = findLongestRoadHelper(visited, e.getOccupColor(), e.getEdgeNo(), -1);
                // find the player that owns this edge.
                for( int i = 0; i < playerColors.length; i++)
                {
                    // update current player's longest road.
                    if( playerColors[i].equals( e.getOccupColor()))
                    {
                        pl.getPlayerWithColor( playerColors[i]).setLongestRoad(result);
                    }
                }
            }
        }
    }
    
    private int findLongestRoadHelper(Set<Integer> visited, Color playerColor, int index, int triVertex)
    {
        if( !edges[index].getOccupColor().equals(playerColor))
            return 0;
        visited.add( index);
        System.out.println( "Visited edge " + index);
        List<Integer> lengths = new ArrayList<>();
        boolean noneVisited = true;
        boolean lookBack = true;
        for( Vertex v : edges[index].getVertices())
        {
            if( !v.isOccupied()|| (v.isOccupied() && v.getOccupColor().equals(playerColor)) && v.getVertexNo() != triVertex)
            {
                // if vertex has 3 neighboring edges, check if all are the player's.
                boolean playerOwnsAll3 = false;
                if( v.getEdges().size() == 3)
                {
                    // check if all are the players.
                    playerOwnsAll3 = true;
                    for( Edge e : v.getEdges())
                    {
                        if( playerOwnsAll3)
                            playerOwnsAll3 &= e.getOccupColor().equals(playerColor);
                    }
                    // what we aim here is, if player owns all of the edges of that vertex, do not look into that vertex.
                }
                for( Edge e : v.getEdges())
                {
                    if( e.getOccupColor().equals(playerColor) && !visited.contains(e.getEdgeNo()))
                    {
                        int curLongest = findLongestRoadHelper(visited, playerColor, e.getEdgeNo(), (playerOwnsAll3 ? v.getVertexNo() : -1));
                        System.out.println( "curLongest at edge " + index + " is " + curLongest);
                        lengths.add(curLongest);
                        noneVisited = false;
                    }
                }
            }
        }
        if( noneVisited)
        {
            return 1;
        }
        else
        {
            int max = Integer.MIN_VALUE;
            for( int i : lengths)
            {
                if( i > max)
                    max = i;
            }
            if( max == Integer.MIN_VALUE)
            {
                return 0;
            }
            else
            {
                System.out.println( "Max at edge " + index + " is " + max);
                return 1 + max; 
            }
        }
    }
    private void distributeSources()
    {
        // ore = 0, grain = 1, lumber = 2, wool = 3, brick = 4
        hexagons[0].setSourceType(0); // ore
        hexagons[1].setSourceType(3); // wool
        hexagons[2].setSourceType(2); // lumber
        hexagons[3].setSourceType(1); // grain
        hexagons[4].setSourceType(4); // brick
        hexagons[5].setSourceType(3); // wool
        hexagons[6].setSourceType(4); // brick
        hexagons[7].setSourceType(1); // grain
        hexagons[8].setSourceType(2); // lumber
        hexagons[9].setSourceType(-99); // DESERT
        hexagons[10].setSourceType(2); // lumber
        hexagons[11].setSourceType(0); // ore
        hexagons[12].setSourceType(2); // lumber
        hexagons[13].setSourceType(0); // ore
        hexagons[14].setSourceType(1); // grain
        hexagons[15].setSourceType(3); // wool
        hexagons[16].setSourceType(4); // brick
        hexagons[17].setSourceType(1); // grain
        hexagons[18].setSourceType(3); // wool
    }
    private void distributeDiceNumbers()
    {
        // for now, we will set up numbers as shown in the
        // beginner map of the catan rulebook.
        hexagons[0].assignNumber(10);
        hexagons[1].assignNumber(2);
        hexagons[2].assignNumber(9);
        hexagons[3].assignNumber(12);
        hexagons[4].assignNumber(6);
        hexagons[5].assignNumber(4);
        hexagons[6].assignNumber(10);
        hexagons[7].assignNumber(9);
        hexagons[8].assignNumber(11);
        hexagons[9].assignNumber(7);
        hexagons[10].assignNumber(3);
        hexagons[11].assignNumber(8);
        hexagons[12].assignNumber(8);
        hexagons[13].assignNumber(3);
        hexagons[14].assignNumber(4);
        hexagons[15].assignNumber(5);
        hexagons[16].assignNumber(5);
        hexagons[17].assignNumber(6);
        hexagons[18].assignNumber(11);

    }
    private void initializeGraph()
    {
        // "the most brute-forcest code I have ever seen" -Alper

        // initialize edges using the vertices they are adjacent to
        edges[0] = vertices[0].addEdge( vertices[3], 0);
        edges[1] = vertices[0].addEdge( vertices[4], 1);
        edges[2] = vertices[1].addEdge( vertices[4], 2);
        edges[3] = vertices[1].addEdge( vertices[5], 3);
        edges[4] = vertices[2].addEdge( vertices[5], 4);
        edges[5] = vertices[2].addEdge( vertices[6], 5);
        edges[6] = vertices[3].addEdge( vertices[7], 6);
        edges[7] = vertices[4].addEdge( vertices[8], 7);
        edges[8] = vertices[5].addEdge( vertices[9], 8);
        edges[9] = vertices[6].addEdge( vertices[10], 9);
        edges[10] = vertices[7].addEdge( vertices[11], 10);
        edges[11] = vertices[7].addEdge( vertices[12], 11);
        edges[12] = vertices[8].addEdge( vertices[12], 12);
        edges[13] = vertices[8].addEdge( vertices[13], 13);
        edges[14] = vertices[9].addEdge( vertices[13], 14);
        edges[15] = vertices[9].addEdge( vertices[14], 15);
        edges[16] = vertices[10].addEdge( vertices[14], 16);
        edges[17] = vertices[10].addEdge( vertices[15], 17);
        edges[18] = vertices[11].addEdge( vertices[16], 18);
        edges[19] = vertices[12].addEdge( vertices[17], 19);
        edges[20] = vertices[13].addEdge( vertices[18], 20);
        edges[21] = vertices[14].addEdge( vertices[19], 21);
        edges[22] = vertices[15].addEdge( vertices[20], 22);
        edges[23] = vertices[16].addEdge( vertices[21], 23);
        edges[24] = vertices[16].addEdge( vertices[22], 24);
        edges[25] = vertices[17].addEdge( vertices[22], 25);
        edges[26] = vertices[17].addEdge( vertices[23], 26);
        edges[27] = vertices[18].addEdge( vertices[23], 27);
        edges[28] = vertices[18].addEdge( vertices[24], 28);
        edges[29] = vertices[19].addEdge( vertices[24], 29);
        edges[30] = vertices[19].addEdge( vertices[25], 30);
        edges[31] = vertices[20].addEdge( vertices[25], 31);
        edges[32] = vertices[20].addEdge( vertices[26], 32);
        edges[33] = vertices[21].addEdge( vertices[27], 33);
        edges[34] = vertices[22].addEdge( vertices[28], 34);
        edges[35] = vertices[23].addEdge( vertices[29], 35);
        edges[36] = vertices[24].addEdge( vertices[30], 36);
        edges[37] = vertices[25].addEdge( vertices[31], 37);
        edges[38] = vertices[26].addEdge( vertices[32], 38);
        edges[39] = vertices[27].addEdge( vertices[33], 39);
        edges[40] = vertices[28].addEdge( vertices[33], 40);
        edges[41] = vertices[28].addEdge( vertices[34], 41);
        edges[42] = vertices[29].addEdge( vertices[34], 42);
        edges[43] = vertices[29].addEdge( vertices[35], 43);
        edges[44] = vertices[30].addEdge( vertices[35], 44);
        edges[45] = vertices[30].addEdge( vertices[36], 45);
        edges[46] = vertices[31].addEdge( vertices[36], 46);
        edges[47] = vertices[31].addEdge( vertices[37], 47);
        edges[48] = vertices[32].addEdge( vertices[37], 48);
        edges[49] = vertices[33].addEdge( vertices[38], 49);
        edges[50] = vertices[34].addEdge( vertices[39], 50);
        edges[51] = vertices[35].addEdge( vertices[40], 51);
        edges[52] = vertices[36].addEdge( vertices[41], 52);
        edges[53] = vertices[37].addEdge( vertices[42], 53);
        edges[54] = vertices[38].addEdge( vertices[43], 54);
        edges[55] = vertices[39].addEdge( vertices[43], 55);
        edges[56] = vertices[39].addEdge( vertices[44], 56);
        edges[57] = vertices[40].addEdge( vertices[44], 57);
        edges[58] = vertices[40].addEdge( vertices[45], 58);
        edges[59] = vertices[41].addEdge( vertices[45], 59);
        edges[60] = vertices[41].addEdge( vertices[46], 60);
        edges[61] = vertices[42].addEdge( vertices[46], 61);
        edges[62] = vertices[43].addEdge( vertices[47], 62);
        edges[63] = vertices[44].addEdge( vertices[48], 63);
        edges[64] = vertices[45].addEdge( vertices[49], 64);
        edges[65] = vertices[46].addEdge( vertices[50], 65);
        edges[66] = vertices[47].addEdge( vertices[51], 66);
        edges[67] = vertices[48].addEdge( vertices[51], 67);
        edges[68] = vertices[48].addEdge( vertices[52], 68);
        edges[69] = vertices[49].addEdge( vertices[52], 69);
        edges[70] = vertices[49].addEdge( vertices[53], 70);
        edges[71] = vertices[50].addEdge( vertices[53], 71);

        // second, initialize the vertices of hexagons.
        hexagons[0].setVertices( vertices[3],  vertices[0],  vertices[4],  vertices[8],  vertices[12],  vertices[7]);
        hexagons[1].setVertices( vertices[4],  vertices[1],  vertices[5],  vertices[9],  vertices[13],  vertices[8]);
        hexagons[2].setVertices( vertices[5],  vertices[2],  vertices[6],  vertices[10],  vertices[14],  vertices[9]);
        hexagons[3].setVertices( vertices[11],  vertices[7],  vertices[12],  vertices[17],  vertices[22],  vertices[16]);
        hexagons[4].setVertices( vertices[12],  vertices[8],  vertices[13],  vertices[18],  vertices[23],  vertices[17]);
        hexagons[5].setVertices( vertices[13],  vertices[9],  vertices[14],  vertices[19],  vertices[24],  vertices[18]);
        hexagons[6].setVertices( vertices[14],  vertices[10],  vertices[15],  vertices[20],  vertices[25],  vertices[19]);
        hexagons[7].setVertices( vertices[21],  vertices[16],  vertices[22],  vertices[28],  vertices[33],  vertices[27]);
        hexagons[8].setVertices( vertices[22],  vertices[17],  vertices[23],  vertices[29],  vertices[34],  vertices[28]);
        hexagons[9].setVertices( vertices[23],  vertices[18],  vertices[24],  vertices[30],  vertices[35],  vertices[29]);
        hexagons[10].setVertices( vertices[24],  vertices[19],  vertices[25],  vertices[31],  vertices[36],  vertices[30]);
        hexagons[11].setVertices( vertices[25],  vertices[20],  vertices[26],  vertices[32],  vertices[37],  vertices[31]);
        hexagons[12].setVertices( vertices[33],  vertices[28],  vertices[34],  vertices[39],  vertices[43],  vertices[38]);
        hexagons[13].setVertices( vertices[34],  vertices[29],  vertices[35],  vertices[40],  vertices[44],  vertices[39]);
        hexagons[14].setVertices( vertices[35],  vertices[30],  vertices[36],  vertices[41],  vertices[45],  vertices[40]);
        hexagons[15].setVertices( vertices[36],  vertices[31],  vertices[37],  vertices[42],  vertices[46],  vertices[41]);
        hexagons[16].setVertices( vertices[43],  vertices[39],  vertices[44],  vertices[48],  vertices[51],  vertices[47]);
        hexagons[17].setVertices( vertices[44],  vertices[40],  vertices[45],  vertices[49],  vertices[52],  vertices[48]);
        hexagons[18].setVertices( vertices[45],  vertices[41],  vertices[46],  vertices[50],  vertices[53],  vertices[49]);

        // lastly, initialize the edges of hexagons.
        hexagons[0].setEdges( edges[6],  edges[0],  edges[1],  edges[7],  edges[12],  edges[11]);
        hexagons[1].setEdges( edges[7],  edges[2],  edges[3],  edges[8],  edges[14],  edges[13]);
        hexagons[2].setEdges( edges[8],  edges[4],  edges[5],  edges[9],  edges[16],  edges[15]);
        hexagons[3].setEdges( edges[18],  edges[10],  edges[11],  edges[19],  edges[25],  edges[24]);
        hexagons[4].setEdges( edges[19],  edges[12],  edges[13],  edges[20],  edges[27],  edges[26]);
        hexagons[5].setEdges( edges[20],  edges[14],  edges[15],  edges[21],  edges[29],  edges[28]);
        hexagons[6].setEdges( edges[21],  edges[16],  edges[17],  edges[22],  edges[31],  edges[30]);
        hexagons[7].setEdges( edges[33],  edges[23],  edges[24],  edges[34],  edges[40],  edges[39]);
        hexagons[8].setEdges( edges[34],  edges[25],  edges[26],  edges[35],  edges[42],  edges[41]);
        hexagons[9].setEdges( edges[35],  edges[27],  edges[28],  edges[36],  edges[44],  edges[43]);
        hexagons[10].setEdges( edges[36],  edges[29],  edges[30],  edges[37],  edges[46],  edges[45]);
        hexagons[11].setEdges( edges[37],  edges[31],  edges[32],  edges[38],  edges[48],  edges[47]);
        hexagons[12].setEdges( edges[49],  edges[40],  edges[41],  edges[40],  edges[55],  edges[54]);
        hexagons[13].setEdges( edges[50],  edges[42],  edges[43],  edges[51],  edges[57],  edges[56]);
        hexagons[14].setEdges( edges[51],  edges[44],  edges[45],  edges[52],  edges[59],  edges[58]);
        hexagons[15].setEdges( edges[52],  edges[46],  edges[47],  edges[53],  edges[61],  edges[60]);
        hexagons[16].setEdges( edges[62],  edges[55],  edges[56],  edges[63],  edges[67],  edges[66]);
        hexagons[17].setEdges( edges[63],  edges[57],  edges[58],  edges[64],  edges[69],  edges[68]);
        hexagons[18].setEdges( edges[64],  edges[59],  edges[60],  edges[65],  edges[71],  edges[70]);
    }
    
    public int[] getResources(){
        int[] sources = new int[19];
        for(int i = 0; i < 19; i++){
            sources[i] = hexagons[i].getSourceType();
        }
        return sources;
    }
    
    public int[] getNumbersofHexagons(){
        int[] numbers = new int[19];
        for(int i = 0; i < 19; i++){
            numbers[i] = hexagons[i].getNumber();
        }
        return numbers;
    }
}
