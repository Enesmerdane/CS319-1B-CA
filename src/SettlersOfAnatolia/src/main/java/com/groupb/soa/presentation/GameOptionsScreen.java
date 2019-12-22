package com.groupb.soa.presentation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.groupb.soa.MainApp;
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
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GameOptionsScreen implements Initializable {
    
    private String userName;
    
    private MenuItem color_blueItem;
    private MenuItem color_redItem;
    private MenuItem color_purpleItem;
    private MenuItem color_greenyellowItem;
    
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
    
    @FXML
    private MenuButton gameTypeOption;
    
    @FXML
    private MenuItem withBots;
    
    @FXML
    private MenuItem multiplayer;
    
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
        color_purpleItem = new MenuItem("Purple");
        color_purpleItem.setStyle("-fx-font-size: 35px;");
        color_purpleItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                color_picked_game_options.setText(color_purpleItem.getText());
            }
        });
        color_greenyellowItem = new MenuItem("Greenyellow");
        color_greenyellowItem.setStyle("-fx-font-size: 35px;");
        color_greenyellowItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                color_picked_game_options.setText(color_greenyellowItem.getText());
            }
        });
        
        withBots.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameTypeOption.setText(withBots.getText());
            }
        });
        
        multiplayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameTypeOption.setText(multiplayer.getText());
            }
        });
        
        color_picked_game_options.getItems().add(color_blueItem);
        color_picked_game_options.getItems().add(color_redItem);
        color_picked_game_options.getItems().add(color_purpleItem);
        color_picked_game_options.getItems().add(color_greenyellowItem);
        
        
    }    
    
    @FXML
    private void goNewGame(ActionEvent event) throws IOException{
        userName = username_game_options.getText();
        
        if(!userName.equals("") && !color_picked_game_options.getText().equals("Color") && !gameTypeOption.getText().equals("Choose a Game Type")){
            AnchorPane pane;
            int type = 0;
            switch(gameTypeOption.getText()){
                case "Play with Friends":
                    System.out.println("Playing with friends");
                    type = 0;
                    break;
                case "Play with Bots":
                    System.out.println("Playing with bots");
                    type = 1;
                    break;
                default:
                    System.out.println("Olmadı :((");
            }
            Color[] colors = new Color[4];
            switch(color_picked_game_options.getText()){
                case "Blue":
                    colors[0] = Color.BLUE;
                    colors[1] = Color.RED;
                    colors[2] = Color.PURPLE;
                    colors[3] = Color.GREENYELLOW;
                    break;
                case "Red":
                    colors[0] = Color.RED;
                    colors[1] = Color.BLUE;
                    colors[2] = Color.GREENYELLOW;
                    colors[3] = Color.PURPLE;
                    break;
                case "Purple":
                    colors[0] = Color.PURPLE;
                    colors[1] = Color.GREENYELLOW;
                    colors[2] = Color.BLUE;
                    colors[3] = Color.RED;
                    break;
                case "Greenyellow":
                    colors[0] = Color.GREENYELLOW;
                    colors[1] = Color.PURPLE;
                    colors[2] = Color.RED;
                    colors[3] = Color.BLUE;
                    break;
                default:
                    System.out.println("obaaa " + color_picked_game_options.getText());
            }
            
            
            MainApp.getInstance().getGameControllerObj().initateGame(type, colors); // This has to be done before loading UI because ui uses some models to display
            if(MainApp.getWidth() != 1920.0){
                pane = FXMLLoader.load(getClass().getResource("/fxml/GameScreenLR.fxml"));
            } else {
                pane = FXMLLoader.load(getClass().getResource("/fxml/GameScreen.fxml"));
            }
        
            rootPane.getChildren().setAll(pane);
        }
    }
    
    @FXML
    private void goBackMainMenu(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/MainMenuScene.fxml"));
        
        rootPane.getChildren().setAll(pane);
        
    }
}
