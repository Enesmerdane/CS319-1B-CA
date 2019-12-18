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
   int[] sources;
   Queue<DevCard> cards;
   
   int monopoly; // number of monopoly cards
   int plenty; //number of yearofplenty cards
   int roadB; //number of road building cards in bank
   
   Bank(){
       //initialize sources in bank
       sources = new int[5];
       for ( int i = 0; i < 5; i++)
           sources[i] = 19;
       
       //initialize cardStack
       cards = new LinkedList<>();
       //distrubute stack randomly
       while(monopoly != 0 || plenty != 0 || roadB != 0 ){
           int random = (int)(Math.random()* 2 );
           if ( random == 0){
               cards.add(new Monopoly("monopoly", null));
               monopoly--;
            }
           else if ( random == 1 ){
                cards.add(new YearOfPlenty("plenty", null));
                plenty--;
           }
           else if ( random == 2 ){
                cards.add(new RoadBuilding("roadBuilding", null));
                roadB--;
           }  
       }
       
       
   }
   
   public void drawCard(){
       //will be implemented
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

