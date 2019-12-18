/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.presentation;

import com.groupb.soa.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
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
    
    @FXML
    private ImageView main_menu_scene_background_image;


    // Constructor
    
    // Methods
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        main_menu_scene_background_image.setFitHeight(MainApp.getHeight());
        main_menu_scene_background_image.setFitWidth(MainApp.getWidth());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @FXML
    private void terminateGame(ActionEvent event) throws IOException{
        System.exit(0);
    }
    
    @FXML
    private void goGameOptionsMenu() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/GameOptions.fxml"));
        
        rootPane.getChildren().setAll(pane);
        
        MainApp.getInstance().getGameControllerObj().initateGame();
    }
    
    
}
