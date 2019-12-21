/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.models;

import java.util.Arrays;

/**
 *
 * @author İrem Kırmacı
 */
public class DomesticTrade  {
    Player creator;
    int[] offer;
    int[] inReturn;
    private static final int[] indices = {1, 2, 3, 0, 4};
    public DomesticTrade( Player theCreator, int[] theOffer, int[] theInReturn)
    {
        creator = theCreator;
        offer = Arrays.copyOf(theOffer, 5);
        inReturn = Arrays.copyOf(theInReturn, 5);
        for( int i = 0; i< offer.length; i++)
        {
            creator.subSource(i, offer[i]);
        }
    }
    
    public boolean isTradeValid( Player p)
    {
        boolean result = true;
        for( int i = 0; i < inReturn.length; i++)
        {
            result &= (p.getSourceNo(i) >= inReturn[i]);
        }
        return result;
    }
    
    public boolean finalizeTrade( Player p)
    {
        if( !isTradeValid(p))
            return false;
        
        for( int i = 0; i < 5; i++)
        {
            // give offer to p
            p.addSource(i, offer[i]);
            // give inReturn to creator
            p.subSource( i, inReturn[i]);
            creator.addSource(i, inReturn[i]);
        }
        return true;
    }
    public void getTradeInfo( StringBuffer offers, StringBuffer inReturns)
    {
        String offerString = "", inReturnString = "";
        for( int i : indices)
        {
            if( offer[i] != 0)
            {
                offerString += offer[i] + " ";
                offerString += translateResource( i);
                offerString += "\n";
                System.out.println( "offerString");            
            }
            
            if( inReturn[i] != 0)
            {
                inReturnString += inReturn[i] + " ";
                inReturnString += translateResource(i);
                inReturnString += "\n";
            }
        }
        offers.delete(0, offers.length());
        offers.append(offerString);
        inReturns.delete(0, inReturns.length());
        inReturns.append(inReturnString);
    }
    
    private String translateResource(int source)
    {
        switch( source)
                {
                    case 0:
                        return "Ore";
                    case 1:
                        return "Grain";
                    case 2:
                        return "Lumber";
                    case 3:
                        return "Wool";
                    case 4:
                        return "Brick";
                    default:
                        return "";
                }
    }
    public boolean isCreator( Player p)
    {
        return p.equals(creator);
    }
}
