/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.presentation;

/**
 *
 * @author user
 */
public class Point {
    private double x;
    private double y;
    
    public Point(double initX, double initY){
        x = initX;
        y = initY;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
}
