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


import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class Player{
    private int[] sources;
    private int score;
    private Color color;
    private int remRoads;
    private int remSettlements;
    private int remCities;
    private int knightCards;
    private int longestRoadLength;
    private boolean hasLargestArmy;
    private boolean hasLongestRoad;
    private boolean canBuyDevCard;
    private boolean usedAnatolianShepherdDog;
    private int destroyedCities;
    private ArrayList<DevCard> cards;
    // ore = 0, grain = 1, lumber = 2, wool = 3, brick = 4

    Player( Color colour){
        color = colour;
        score = 0;
        remRoads = 15;
        remSettlements = 5;
        remCities = 4;
        cards = new ArrayList<DevCard>();
        sources = new int[5];
        knightCards = 0;
        longestRoadLength = 0;
        hasLargestArmy = false;
        hasLongestRoad = false;
        canBuyDevCard = true;
        usedAnatolianShepherdDog = false;
        destroyedCities = 0;
        for( int i = 0; i < sources.length; i++)
        {
            sources[i] = 20;
        }
        cards.add( new Monopoly("test"));
        cards.add( new RoadBuilding( "test"));
        cards.add( new YearOfPlenty( "test"));
        cards.add( new Knight( "test"));
        cards.add( new Knight( "test"));
        cards.add( new Knight( "test"));
        cards.add( new Knight( "test"));
        cards.add( new Insurance( "test"));
        cards.add( new AnatolianShepherdDog("test"));
    }
    
    public boolean buyDevCard(Bank bank){
        
        System.out.println( "Dev card a girildi");
        return bank.drawCard(this);
    }
    

    public void addSource(int source, int amount){
        sources[source] = sources[source] + amount;
    }
    
    public boolean subSource( int source, int amount){
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


    public int getScore() {
        int baseScore = 0;
        baseScore += ( 5 - remSettlements);
        baseScore += ( 4 - remCities);
        if( hasLongestRoad)
            baseScore += 2;
        if( hasLargestArmy)
            baseScore += 2;
        return baseScore;
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
        int knightCards, roadCards, yearCards, monoCards, insCards, asdCards;
        knightCards = roadCards = yearCards = monoCards = insCards = asdCards = 0;
        for( int i = 0; i < cards.size(); i++)
        {
            if( cards.get(i) instanceof Knight)
                knightCards++;
            else if( cards.get(i) instanceof Monopoly)
                monoCards++;
            else if( cards.get(i) instanceof RoadBuilding)
                roadCards++;
            else if( cards.get(i) instanceof YearOfPlenty)
                yearCards++;
            else if( cards.get(i) instanceof Insurance)
                insCards++;
            else if( cards.get(i) instanceof AnatolianShepherdDog)
                asdCards++;
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
            case "Insurance":
                return insCards;
            case "Anatolian Shepherd Dog":
                return asdCards;
            default:
                return -1;
        }
    }
    
    public int getPlayableCardNo(String cardName)
    {
        int knightCards, roadCards, yearCards, monoCards, insCards, asdCards;
        knightCards = roadCards = yearCards = monoCards = insCards = asdCards = 0;
        for( int i = 0; i < cards.size(); i++)
        {
            if( cards.get(i) instanceof Knight && !cards.get(i).getRecentlyBought())
                knightCards++;
            else if( cards.get(i) instanceof Monopoly && !cards.get(i).getRecentlyBought())
                monoCards++;
            else if( cards.get(i) instanceof RoadBuilding && !cards.get(i).getRecentlyBought())
                roadCards++;
            else if( cards.get(i) instanceof YearOfPlenty && !cards.get(i).getRecentlyBought())
                yearCards++;
            else if( cards.get(i) instanceof Insurance && !cards.get(i).getRecentlyBought())
                insCards++;
            else if( cards.get(i) instanceof AnatolianShepherdDog && !cards.get(i).getRecentlyBought())
                asdCards++;
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
            case "Insurance":
                return insCards;
            case "Anatolian Shepherd Dog":
                return asdCards;
            default:
                return -1;
        }
    }
    
    // returns the first card with the given type in the cards ArrayList.
    public DevCard getCard( String cardName)
    {
        for( int i = 0; i < cards.size(); i++)
        {
            boolean condition;
            switch (cardName) {
            case "Knight":
                condition = cards.get(i) instanceof Knight; break;
            case "Road Building":
                condition = cards.get(i) instanceof RoadBuilding; break;
            case "Year of Plenty":
                condition = cards.get(i) instanceof YearOfPlenty; break;
            case "Monopoly":
                condition = cards.get(i) instanceof Monopoly; break;
            case "Insurance":
                condition = cards.get(i) instanceof Insurance; break;
            case "Anatolian Shepherd Dog":
                condition = cards.get(i) instanceof AnatolianShepherdDog; break;
            default:
                condition = false;
            }
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
    
    public boolean getCanBuyDevCard()
    {
        return canBuyDevCard;
    }
    
    public void setCanBuyDevCard( boolean toggle)
    {
        canBuyDevCard = toggle;
    }
    
    public void addCard( DevCard dc)
    {
        cards.add( dc);
    }
    
    public void incrementKnightCards()
    {
        knightCards++;
    }
    public void decreaseScore(int amount) // added new 
    {
        score -= amount;
    }
    
    public int getKnights()
    {
        return knightCards;
    }
    
    public void setLargestArmy( boolean value)
    {
        hasLargestArmy = value;
    }
    
    public int getLongestRoad()
    {
        return longestRoadLength;
    }
    
    public boolean getHasLongestRoad()
    {
        return hasLongestRoad;
    }
    
    public void setHasLongestRoad(boolean value)
    {
        hasLongestRoad = value;
    }
    
    public boolean setLongestRoad( int value)
    {
        if( value <= longestRoadLength)
            return false;
        longestRoadLength = value;
        return true;
    }
    
    public boolean removeHalfResources()
    {
        if( getTotalNoOfSources() < 7)
            return false;
        
        for( int i = 0; i < 5; i++)
        {
            sources[i] -= sources[i] / 2;
        }
        return true;
    }
    
    public void successfulCityDestroy()
    {
        remSettlements++;
        remCities++;
        destroyedCities++;
    }
    
    public int getDestroyedCities()
    {
        return destroyedCities;
    }
    
    public void resetDestroyedCities()
    {
        destroyedCities = 0;
    }
    
    public boolean getUsedAnatolianShepherdDog()
    {
        return usedAnatolianShepherdDog;
    }
    
    public void setUsedAnatolianShepherdDog(boolean value)
    {
        usedAnatolianShepherdDog = value;
    }
   
}