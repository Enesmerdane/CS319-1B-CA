/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.models;

/**
 *
 * @author İrem Kırmacı, Göksu
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class Player implements IGameObject {
    int[] sources;
    int score;
    Color color;
    int remRoads;
    int remSettlements;
    int remCities;
    ArrayList<DevCard> cards;
    // ore = 0, grain = 1, lumber = 2, wool = 3, brick = 4

    Player( Color colour){
        color = colour;
        score = 0;
        remRoads = 15;
        remSettlements = 5;
        remCities = 4;
        cards = new ArrayList<DevCard>();
        sources = new int[5];
        for( int i = 0; i < sources.length; i++)
        {
            sources[i] = 111;
        }
        cards.add( new Monopoly("test", this));
    }
    
    public boolean buyDevCard(Bank bank){
        if(sources[0] > 0 && sources[1] > 0 && sources[3] > 0){
            subSource(0, 1);
            subSource(1, 1);
            subSource(3, 1);
            bank.drawCard();
            return true;
        }
        return false;
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

    public Color getColor() {
        return color;
    }

    private int getTotalNoOfSources(){
        int sum = 0;
        for ( int i = 0; i < 5; i++)
            sum += sources[i];
        return sum;
    }

    public boolean stealSourceFrom( Player p1 ){
        System.out.println( p1);
        if ( p1.getTotalNoOfSources() > 0){
            int random = (int) (( Math.random() * 5 ));
            while( sources[random] < 0){
                random = (int) ( Math.random() * 5 );
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

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void render(Node n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getRemSettlements()
    {
        return remSettlements;
    }
    
    public int getRemRoads()
    {
        return remRoads;
    }
    
    public int getRemCities()
    {
        return remCities;
    }
    
    public void successfulSettBuild()
    {
        remSettlements--;
    }
    
    public void successfulCityBuild()
    {
        remCities--;
    }
    
    public void successfulRoadBuild()
    {
        remRoads--;
    }
    
    public int getCardNo(String cardName)
    {
        int knightCards, roadCards, yearCards, monoCards;
        knightCards = roadCards = yearCards = monoCards = 0;
        for( int i = 0; i < cards.size(); i++)
        {
            /*if( cards.get(i) instanceof Knight)
            *   knightCards++
            */
            if( cards.get(i) instanceof Monopoly)
                monoCards++;
            else if( cards.get(i) instanceof RoadBuilding)
                roadCards++;
            else if( cards.get(i) instanceof YearOfPlenty)
                yearCards++;
        }
        
        switch (cardName) {
            case "Knight":
                return knightCards;
            case "Road Building":
                return roadCards;
            case "Year of Plenty":
                return yearCards;
            case "Monopoly":
                return monoCards;
            default:
                return -1;
        }
    }
    
    // returns the first card with the given type in the cards ArrayList.
    public DevCard getCard( String cardName)
    {
        for( int i = 0; i < cards.size(); i++)
        {
            System.out.println( cardName);
            boolean condition;
            switch (cardName) {
            case "Knight":
                condition = false; break; //condition = cards.get(i) instanceof Knight;
            case "Road Building":
                condition = cards.get(i) instanceof RoadBuilding; break;
            case "Year of Plenty":
                condition = cards.get(i) instanceof YearOfPlenty; break;
            case "Monopoly":
                condition = cards.get(i) instanceof Monopoly; break;
            default:
                condition = false;
            }
            
            System.out.println( "instanceof: " + (cards.get(i) instanceof Monopoly));
            System.out.println( condition);
            
            if( condition)
                return cards.get(i);
        }
        return null;
    }
    
    public boolean removeCard( DevCard dc)
    {
        return cards.remove(dc);
    }
    
    // this function is used to set all the recentlyBought variables of cards to false.
    public void refreshCards()
    {
        for( DevCard dc : cards)
        {
            if( dc.getRecentlyBought())
                dc.setRecentlyBought(false);
        }
    }
}

