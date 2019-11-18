/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.models;
import java.util.ArrayList;

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
            vertices[i] = new Vertex(i + 1, false); // vertex with number i + 1 becomes ith member of array 
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
    public boolean buildVertex( int index, int playerColor, PlayerList pl, boolean first )
    {
        return vertices[index - 1].build(first, playerColor, pl);
    }
    
    public boolean upgradeVertex( int index, int playerColor, PlayerList pl)
    {
        return vertices[index - 1].upgrade(playerColor, pl);
    }
    
    public boolean buildRoad(int index, int playerColor, PlayerList pl, boolean first)
    {
        return edges[index - 1].build(first, playerColor, pl);
    }
    
    public ArrayList<Hexagon> getHexsWithValue(int value) {
        ArrayList<Hexagon> hexagonList = new ArrayList();
        for ( int i = 0; i < 19; i++){
            if ( hexagons[i].getNumber() == value )
                hexagonList.add(hexagons[i]);
        }
        return hexagonList;
    }
    
    private void distributeSources()
    {
        hexagons[0].setSourceType(0); // ore
        hexagons[1].setSourceType(0); // wool
        hexagons[2].setSourceType(0); // lumber
        hexagons[3].setSourceType(0); // grain
        hexagons[4].setSourceType(0); // brick
        hexagons[5].setSourceType(0); // wool
        hexagons[6].setSourceType(0); // brick
        hexagons[7].setSourceType(0); // grain
        hexagons[8].setSourceType(0); // lumber
        hexagons[9].setSourceType(0); // DESERT
        hexagons[10].setSourceType(0); // lumber
        hexagons[11].setSourceType(0); // ore
        hexagons[12].setSourceType(0); // lumber
        hexagons[13].setSourceType(0); // ore
        hexagons[14].setSourceType(0); // grain
        hexagons[15].setSourceType(0); // wool
        hexagons[16].setSourceType(0); // brick
        hexagons[17].setSourceType(0); // grain
        hexagons[18].setSourceType(0); // wool
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
    edges[0] = vertices[0].addEdge( vertices[3], 1);
    edges[1] = vertices[0].addEdge( vertices[4], 2);
    edges[2] = vertices[1].addEdge( vertices[4], 3);
    edges[3] = vertices[1].addEdge( vertices[5], 4);
    edges[4] = vertices[2].addEdge( vertices[5], 5);
    edges[5] = vertices[2].addEdge( vertices[6], 6);
    edges[6] = vertices[3].addEdge( vertices[7], 7);
    edges[7] = vertices[4].addEdge( vertices[8], 8);
    edges[8] = vertices[5].addEdge( vertices[9], 9);
    edges[9] = vertices[6].addEdge( vertices[10], 10);
    edges[10] = vertices[7].addEdge( vertices[11], 11);
    edges[11] = vertices[7].addEdge( vertices[12], 12);
    edges[12] = vertices[8].addEdge( vertices[12], 13);
    edges[13] = vertices[8].addEdge( vertices[13], 14);
    edges[14] = vertices[9].addEdge( vertices[13], 15);
    edges[15] = vertices[9].addEdge( vertices[14], 16);
    edges[16] = vertices[10].addEdge( vertices[14], 17);
    edges[17] = vertices[10].addEdge( vertices[15], 18);
    edges[18] = vertices[11].addEdge( vertices[16], 19);
    edges[19] = vertices[12].addEdge( vertices[17], 20);
    edges[20] = vertices[13].addEdge( vertices[18], 21);
    edges[21] = vertices[14].addEdge( vertices[19], 22);
    edges[22] = vertices[15].addEdge( vertices[20], 23);
    edges[23] = vertices[16].addEdge( vertices[21], 24);
    edges[24] = vertices[16].addEdge( vertices[22], 25);
    edges[25] = vertices[17].addEdge( vertices[22], 26);
    edges[26] = vertices[17].addEdge( vertices[23], 27);
    edges[27] = vertices[18].addEdge( vertices[23], 28);
    edges[28] = vertices[18].addEdge( vertices[24], 29);
    edges[29] = vertices[19].addEdge( vertices[24], 30);
    edges[30] = vertices[19].addEdge( vertices[25], 31);
    edges[31] = vertices[20].addEdge( vertices[25], 32);
    edges[32] = vertices[20].addEdge( vertices[26], 33);
    edges[33] = vertices[21].addEdge( vertices[27], 34);
    edges[34] = vertices[22].addEdge( vertices[28], 35);
    edges[35] = vertices[23].addEdge( vertices[29], 36);
    edges[36] = vertices[24].addEdge( vertices[30], 37);
    edges[37] = vertices[25].addEdge( vertices[31], 38);
    edges[38] = vertices[26].addEdge( vertices[32], 39);
    edges[39] = vertices[27].addEdge( vertices[33], 40);
    edges[40] = vertices[28].addEdge( vertices[33], 41);
    edges[41] = vertices[28].addEdge( vertices[34], 42);
    edges[42] = vertices[29].addEdge( vertices[34], 43);
    edges[43] = vertices[29].addEdge( vertices[35], 44);
    edges[44] = vertices[30].addEdge( vertices[35], 45);
    edges[45] = vertices[30].addEdge( vertices[36], 46);
    edges[46] = vertices[31].addEdge( vertices[36], 47);
    edges[47] = vertices[31].addEdge( vertices[37], 48);
    edges[48] = vertices[32].addEdge( vertices[37], 49);
    edges[49] = vertices[33].addEdge( vertices[38], 50);
    edges[50] = vertices[34].addEdge( vertices[39], 51);
    edges[51] = vertices[35].addEdge( vertices[40], 52);
    edges[52] = vertices[36].addEdge( vertices[41], 53);
    edges[53] = vertices[37].addEdge( vertices[42], 54);
    edges[54] = vertices[38].addEdge( vertices[43], 55);
    edges[55] = vertices[39].addEdge( vertices[43], 56);
    edges[56] = vertices[39].addEdge( vertices[44], 57);
    edges[57] = vertices[40].addEdge( vertices[44], 58);
    edges[58] = vertices[40].addEdge( vertices[45], 59);
    edges[59] = vertices[41].addEdge( vertices[45], 60);
    edges[60] = vertices[41].addEdge( vertices[46], 61);
    edges[61] = vertices[42].addEdge( vertices[46], 62);
    edges[62] = vertices[43].addEdge( vertices[47], 63);
    edges[63] = vertices[44].addEdge( vertices[48], 64);
    edges[64] = vertices[45].addEdge( vertices[49], 65);
    edges[65] = vertices[46].addEdge( vertices[50], 66);
    edges[66] = vertices[47].addEdge( vertices[51], 67);
    edges[67] = vertices[48].addEdge( vertices[51], 68);
    edges[68] = vertices[48].addEdge( vertices[52], 69);
    edges[69] = vertices[49].addEdge( vertices[52], 70);
    edges[70] = vertices[49].addEdge( vertices[53], 71);
    edges[71] = vertices[50].addEdge( vertices[53], 72);
    
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
}
