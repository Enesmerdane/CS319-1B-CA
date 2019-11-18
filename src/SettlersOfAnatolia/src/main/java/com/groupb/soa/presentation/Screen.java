/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.presentation;

/**
 *
 * @author Enes Merdane
 */
abstract class Screen {
    // Properties
    private static Screen rootScreen;
    // Constructors
    
    // Methods
    
    /**
     * 
     * @return the created Screen
     */
    public abstract Screen createScreen();
    
    
    /**
     * This class will delete -clean- the current screen and 
     * @return the rootScreen
     */
    public abstract Screen deleteScreen();
    
    
}
