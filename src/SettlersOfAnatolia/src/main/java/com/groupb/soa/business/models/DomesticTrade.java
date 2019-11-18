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
public class DomesticTrade  {
    public boolean trade(Player p1, int source1, Player p2, int source2) {
        if ( p1.getSourceNo(source1) > 0 && p2.getSourceNo(source2) > 0 ){
            p1.subSource(source1, 1);
            p2.addSource(source1, 1);

            p2.subSource(source2, 1);
            p2.addSource(source2, 1);
            return true;
        }
        return false;

    }
}
