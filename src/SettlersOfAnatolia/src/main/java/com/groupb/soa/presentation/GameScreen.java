/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.presentation;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author Enes Merdane 
 */
public class GameScreen implements Initializable {
    // Properties
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Rectangle game_menu_filter;
    @FXML
    private Rectangle game_menu_background;
    @FXML
    private Text game_menu_title; 
    @FXML
    private Button game_menu_game_music;
    @FXML
    private Button game_menu_game_sound;
    @FXML
    private Button game_menu_back_to_main_menu;
    @FXML
    private Button game_menu_back_to_game;
    @FXML
    private Button game_menu_exit_game;
    
    
    // Constructors
    /**
     *  @initiatedDate 17.11.2019
     *  @initiator Enes
     *  @lastEdited 17.11.2019
     *  @author null
     * This constructor is for the first initiated game
     */
    public GameScreen(){
        
    }
    
    // Methods

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        
        
        // Button Operations
        game_menu_game_music.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                game_menu_filter.setVisible(true);
            }
        });
        game_menu_game_sound.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                game_menu_game_sound.setVisible(true);
            }
        });
        game_menu_back_to_main_menu.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                game_menu_back_to_main_menu.setVisible(true);
            }
        });
        game_menu_back_to_game.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                game_menu_back_to_game.setVisible(true);
            }
        });
        game_menu_exit_game.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                game_menu_exit_game.setVisible(true);
            }
        });
    }   
        
    
    @FXML
    private void goBackMainMenu(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/MainMenuScene.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
    @FXML
    private void openGameMenu(ActionEvent event){
        game_menu_filter.setVisible(true);
        game_menu_background.setVisible(true);
        game_menu_title.setVisible(true);
        game_menu_game_music.setVisible(true);
        game_menu_game_sound.setVisible(true);
        game_menu_back_to_main_menu.setVisible(true);
        game_menu_back_to_game.setVisible(true);
        game_menu_exit_game.setVisible(true);
    }
    
    
}
