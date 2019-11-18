/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.models;

/**
 *
 * @author İrem Kırmacı
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


import java.util.ArrayList;

public class Player  implements IGameObject {
    int[] sources;
    int score;
    int color;
    int remRoads;
    int remSettlements;
    int remCities;
    //ArrayList<DevCard> cards;
    // ore = 0, grain = 1, lumber = 2, wool = 3, brick = 4

    Player( int colour){
        color = colour;
        score = 0;
        remRoads = 15;
        remSettlements = 5;
        remCities = 4;
       // cards = new ArrayList<DevCard>();
        sources = new int[5];

    }
    public void increaseScore( int amount){
        score += amount;
    }


    public void addSource(int source, int amount){
        sources[source] = sources[source] + amount;
    }
    public boolean subSource ( int source, int amount){
        if ( sources[source]  >= amount  ){
            sources[source] = sources[source] - amount ;
            return true;
        }
        return false;
    }

    public int getColor() {
        return color;
    }

    private int getTotalNoOfSources(){
        int sum = 0;
        for ( int i = 0; i < 5; i++)
            sum += sources[i];
        return sum;
    }

    public boolean stealSourceFrom( Player p1 ){
        if ( p1.getTotalNoOfSources() > 0){
            int random = (int) (( Math.random() * 4 ) + 1);
            while( sources[random] < 0){
                random = (int) (( Math.random() * 4 ) + 1);
            }
            p1.subSource( random, 1);
            addSource(random, 1);
            return true;

        }
        return false;

    }
    public int getSourceNo(int value){
        return sources[value];
    }

    public void render(GraphicsContext gc) {
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}

