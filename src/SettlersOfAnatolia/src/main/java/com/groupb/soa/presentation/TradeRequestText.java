/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.presentation;

import com.groupb.soa.business.controller.GameController;
import javafx.scene.text.Text;

/**
 *
 * @author Alper
 */
public class TradeRequestText extends Text{
    
    int number;
    String offer, inReturn;
    public TradeRequestText(int n, String offers, String inReturns)
    {
        number = n;
        offer = offers;
        inReturn = inReturns;
        this.setText( "Trade Request #" + n);
    }
    
    public String getOffer()
    {
        return offer;
    }
    
    public String getInReturn()
    {
        return inReturn;
    }
    
    public int getNumber()
    {
        return number;
    }
    
}
