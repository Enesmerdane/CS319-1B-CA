/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author Enes Merdane
 */
public class MainMenuScreen implements Initializable{
    // Properties
    @FXML
    private AnchorPane rootPane;
    // Constructor
    
    // Methods
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @FXML
    private void goNewGame(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/GameScreen.fxml"));
        for( int i = 1; i < 55; i++)
        {
            // pane.lookup( "#vertex" + i).setOnMouseClicked(new VertexHandler(i));
        }
        
        for( int i = 1; i < 73; i++)
        {
            // pane.lookup( "#line" + i).setOnMouseClicked( new EdgeHandler(i));
        }
        rootPane.getChildren().setAll(pane);
        
        System.out.println("You clicked me!");
    }
    
    @FXML
    private void terminateGame(ActionEvent event) throws IOException{
        System.exit(0);
    }
    
    class VertexHandler implements EventHandler<MouseEvent>
    {
        int index;
        
        VertexHandler(int i)
        {
            index = i;
        }
        
        @Override
        public void handle( MouseEvent e)
        {
            System.out.println("Attempt to build vertex at index " + index);
            Circle crc = (Circle) e.getSource();
            crc.setFill(Color.RED);
        }
    }
    
    class EdgeHandler implements EventHandler<MouseEvent>
    {
        int index;
        
        EdgeHandler( int i)
        {
            index = i;
        }
        
        @Override
        public void handle( MouseEvent e)
        {
            System.out.println("Attempt to build edge at index " + index);
            Line l = (Line) e.getSource();
            l.setStroke(Color.RED);
        }
    }
    
}
