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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

/**
 *
 * @author Enes Merdane
 */
public class MainMenuScreen implements Initializable{
    private double buttonx = 160.0;
    private double button1y = 330.0;
    private double button2y = 450.0;
    private double button3y = 570.0;
    private double button4y = 690.0;
    private double button5y = 810.0;
    private double buttonHeight = 93.0;
    private double buttonWidth = 412.0;

    // Properties
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private ImageView main_menu_scene_background_image;
    
    @FXML
    private Button continue_game_button;
    
    @FXML
    private Button new_game_button;
    
    @FXML
    private Button settings_button;
    
    @FXML
    private Button how_to_play_button;
    
    @FXML
    private Button exit_game_button;
    // Constructor
    
    // Methods
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        main_menu_scene_background_image.setPreserveRatio(false);
        main_menu_scene_background_image.setFitHeight(MainApp.getHeight());
        main_menu_scene_background_image.setFitWidth(MainApp.getWidth());
        System.out.println(MainApp.getHeight() / 1020.0);
        System.out.println(continue_game_button.getHeight()); // --> here these buttons have no size etc 
        // setNewSizeButtons();
        //setNewSizeButton(new_game_button);
        //setNewSizeButton(settings_button);
        //setNewSizeButton(how_to_play_button);
        //setNewSizeButton(exit_game_button);
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
        
    }
    
    @FXML
    private void goGameSettings() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/SettingsScreen.fxml"));
        
        rootPane.getChildren().setAll(pane);
        
    }
    
}
