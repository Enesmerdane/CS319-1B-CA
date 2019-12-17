/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.models;

/**
 *
 * @author İrem Kırmacı, Irmak Demir
 */
public class TradeWithBank {
    public boolean trade(Player player, int source1,Bank bank,int source2){
        int[] sourceArray = bank.getSourceS();
        if(player.getSourceNo(source1)>0 && sourceArray[source2] > 0){
            player.subSource(source1, 1);
            player.addSource(source2, 1);
            
            bank.subSource(source2, 1);
            bank.addSource(source1, 1);
            return true;
        }
        
        return false;
                
    }
    
}
