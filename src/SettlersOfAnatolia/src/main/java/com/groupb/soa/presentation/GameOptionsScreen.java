package com.groupb.soa.presentation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.groupb.soa.MainApp;
import com.sun.prism.paint.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GameOptionsScreen implements Initializable {
    
    private String userName;
    
    private MenuItem color_blueItem;
    private MenuItem color_redItem;
    private MenuItem color_orangeItem;
    private MenuItem color_greenItem;
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private Button main_menu_button_game_options;
    
    @FXML
    private Button new_game_button_game_options;
    
    @FXML
    private TextField username_game_options;
    
    @FXML
    private MenuButton color_picked_game_options;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        userName = "";
        
        color_blueItem   = new MenuItem("Blue");
        color_blueItem.setStyle("-fx-font-size: 35px;");
        color_blueItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                color_picked_game_options.setText(color_blueItem.getText());
            }
        });
        color_redItem    = new MenuItem("Red");
        color_redItem.setStyle("-fx-font-size: 35px;");
        color_redItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                color_picked_game_options.setText(color_redItem.getText());
            }
        });
        color_orangeItem = new MenuItem("Orange");
        color_orangeItem.setStyle("-fx-font-size: 35px;");
        color_orangeItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                color_picked_game_options.setText(color_orangeItem.getText());
            }
        });
        color_greenItem = new MenuItem("Green");
        color_greenItem.setStyle("-fx-font-size: 35px;");
        color_greenItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                color_picked_game_options.setText(color_greenItem.getText());
            }
        });
        
        color_picked_game_options.getItems().add(color_blueItem);
        color_picked_game_options.getItems().add(color_redItem);
        color_picked_game_options.getItems().add(color_orangeItem);
        color_picked_game_options.getItems().add(color_greenItem);
        
        
    }    
    
    @FXML
    private void goNewGame(ActionEvent event) throws IOException{
        userName = username_game_options.getText();
        
        if(!userName.equals("") && !color_picked_game_options.getText().equals("Color")){
            MainApp.getInstance().getGameControllerObj().initateGame(); // This has to be done before loading UI because ui uses some models to display
            
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/GameScreen.fxml"));
        
            rootPane.getChildren().setAll(pane);
        }
    }
    
    @FXML
    private void goBackMainMenu(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/MainMenuScene.fxml"));
        
        rootPane.getChildren().setAll(pane);
        
        MainApp.getInstance().getGameControllerObj().initateGame();
    }
}
