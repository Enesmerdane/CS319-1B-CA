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
   Stack<DevCard> cardStack;
   
   Bank(){
       //initialize sources in bank
       sources = new int[5];
       for ( int i = 0; i < 5; i++)
           sources[i] = 19;
       
       //initialize cardStack
       cardStack = new Stack<>();
       
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

