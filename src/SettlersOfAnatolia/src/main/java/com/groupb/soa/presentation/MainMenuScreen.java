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
    private void setNewSizeButtons(){
        double newHeight = ( MainApp.getHeight() / 1020.0) * buttonHeight - 10;
        double newWidth  = ( MainApp.getWidth() / 1920.0) * buttonWidth - 10;
        double newX      = ( MainApp.getWidth() / 1920.0) * buttonx;
        double newY;
        int lengthOfLabel;
        
        newY = ( MainApp.getWidth() / 1920.0) * button1y;
        continue_game_button.setPrefSize(newWidth, newHeight);
        continue_game_button.setLayoutX(newX);
        continue_game_button.setLayoutY(newY);
        //continue_game_button.setStyle("-fx-font-size:10px;");
        lengthOfLabel = continue_game_button.getText().length();
        continue_game_button.setFont(new Font((int) ( 2 * lengthOfLabel))); // --> Hiçbir değişiklik yapmıyor neden :(((
        System.out.println("leleley leleleley: " + continue_game_button.getFont());
        System.out.println("1- " + newX);
        System.out.println("1- " + newY);
        
        newY = ( MainApp.getWidth() / 1920.0) * button2y;
        new_game_button.setPrefSize(newWidth, newHeight);
        new_game_button.setLayoutX(newX);
        new_game_button.setLayoutY(newY);
        //new_game_button.setStyle("-fx-font-size:10px;");
        lengthOfLabel = new_game_button.getText().length();
        new_game_button.setFont(new Font((int) ( 2*lengthOfLabel)));
        System.out.println("2- " + newX);
        System.out.println("2- " + newY);
        
        newY = ( MainApp.getWidth() / 1920.0) * button3y;
        settings_button.setPrefSize(newWidth, newHeight);
        settings_button.setLayoutX(newX);
        settings_button.setLayoutY(newY);
        //settings_button.setStyle("-fx-font-size:10px;");
        lengthOfLabel = settings_button.getText().length();
        settings_button.setFont(new Font((int) ( 2*lengthOfLabel)));
        System.out.println("3- " + newX);
        System.out.println("3- " + newY);
        
        newY = ( MainApp.getWidth() / 1920.0) * button4y;
        how_to_play_button.setPrefSize(newWidth, newHeight);
        how_to_play_button.setLayoutX(newX);
        how_to_play_button.setLayoutY(newY);
        //how_to_play_button.setStyle("-fx-font-size:10px;");
        lengthOfLabel = how_to_play_button.getText().length();
        how_to_play_button.setFont(new Font((int) ( 2*lengthOfLabel)));
        System.out.println("4- " + newX);
        System.out.println("4- " + newY);
        
        newY = ( MainApp.getWidth() / 1920.0) * button5y;
        exit_game_button.setPrefSize(newWidth, newHeight);
        exit_game_button.setLayoutX(newX);
        exit_game_button.setLayoutY(newY);
        //exit_game_button.setStyle("-fx-font-size:10px;");
        lengthOfLabel = exit_game_button.getText().length();
        exit_game_button.setFont(new Font((int) ( 2*lengthOfLabel)));
        System.out.println("5-> " + newX);
        System.out.println("5-> " + newY);
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
