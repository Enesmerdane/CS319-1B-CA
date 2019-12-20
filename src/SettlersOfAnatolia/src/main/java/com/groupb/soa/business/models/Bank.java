/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.models;

import java.io.*; 
import java.util.*; 
/**
 *
 * @author goksuturan
 */
public class Bank {
   private int[] sources;
   private Queue<DevCard> cards;

   Bank(){
       //initialize sources in bank
       sources = new int[5];
       for ( int i = 0; i < 5; i++)
           sources[i] = 19;
       
       //initialize cardStack
       LinkedList<DevCard> tempCards = new LinkedList<>();
       // add 14 knight cards
       for( int i = 1; i < 15; i++)
       {
           tempCards.add( new Knight( "knight" + i));
       }
       // add 6 progress cards
       for( int i = 1; i < 3; i++)
       {
           tempCards.add( new RoadBuilding("rb" + i));
           tempCards.add( new YearOfPlenty("yop" + i));
           tempCards.add( new Monopoly( "mono" + i));
       }
       
       // add 5 victory cards
       for( int i = 1; i < 6; i++)
       {
           // To do
       }
       
       java.util.Collections.shuffle(tempCards);
       cards = tempCards;
       
   }
   
   public boolean drawCard(Player p){
       // check for resources
       // 1 wool, 1 grain, 1 ore
       // ore = 0, grain = 1, lumber = 2, wool = 3, brick = 4
       if( p.getSourceNo(3) <= 1 || p.getSourceNo(1) <= 1 || p.getSourceNo(0) <= 1)
           return false;
       
       // player should be able to buy a devcard
       if( !p.getCanBuyDevCard())
           return false;
       
       // if there are no devcards left in the bank, return false.
       if( cards.isEmpty())
       {
           return false;
       }
       // if the check is passed
       boolean successful = p.subSource(3, 1) && p.subSource(1, 1) && p.subSource(0, 1);
       if( successful)
       {
           DevCard dc = cards.remove();
           p.addCard( dc);
           p.setCanBuyDevCard(false);
       }
       return successful;
   }
   
   public int[] getSources(){
   return sources;
   }
   
   public boolean subSource(int source, int amount){
       if ( sources[source]  >= amount  ){
           sources[source] = sources[source] - amount ;
           return true;
       }
       return false;
   }

    public void addSource(int source, int amount){
        sources[source] = sources[source] + amount;
    }
}

