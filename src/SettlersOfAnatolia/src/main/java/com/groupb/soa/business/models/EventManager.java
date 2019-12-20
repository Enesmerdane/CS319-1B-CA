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
public class EventManager {
    private Event event;
    private GameModel model;
    public  EventManager(GameModel model){
        this.model = model;
    }

    public Event checkEvent(){

        //creating a random value between 0 and 100
        int random = (int)(Math.random()* 100 );
        if(random >= 0 && random <= 10){
            if(random <= 2){
                //event = new EarthQuake(model);
            }
            if(random >= 3 && random <= 5){
                event = new Flood();
            }
            if(random >= 6 && random <= 8){
                //event = new WolfAttack(model);
            }
            if(random >= 9){
                event = new CybeleMonth();
            }
            return event;

        }
        return null;

    }
    public void handleEvent(Event event){

        //if in the checkEvent, an event was occurred
        if(event != null)
            event.occur(model);
    }
}
