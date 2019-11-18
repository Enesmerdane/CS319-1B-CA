/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.models;

/**
 *
 * @author goksuturan
 */
public class Bank {
   int[] sources;
   Bank(){
       sources = new int[5];
       for ( int i = 0; i < 5; i++)
           sources[i] = 19;
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

