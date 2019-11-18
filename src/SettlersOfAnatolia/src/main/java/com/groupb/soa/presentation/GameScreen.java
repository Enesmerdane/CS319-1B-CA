/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import sun.invoke.empty.Empty;

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
    @FXML
    
    private ImageView settlement_image_button;
    @FXML
    private ImageView city_image_button;
    @FXML
    private ImageView road_image_button;
    
    @FXML
    private Rectangle settlement_selected_rectangle;
    @FXML
    private Rectangle city_selected_rectangle;
    @FXML
    private Rectangle road_selected_rectangle;
    
    private Constrcution_type construct_type;
    enum Constrcution_type{
        EMPTY, SETTLEMENT, CITY, ROAD
    }
    
    @FXML
    private Rectangle your_turn_rectangle;
    
    @FXML
    private Text your_turn_text;
    
    @FXML
    private ImageView next_player_1;
    
    @FXML
    private ImageView next_player_2;
    
    @FXML
    private ImageView next_player_3;
    
    private boolean gameSound = true;
    private boolean gameMusic = true;
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
        construct_type = Constrcution_type.EMPTY;
        next_player_1.setStyle("visibility:false");
        next_player_2.setStyle("visibility:false");
        next_player_3.setStyle("visibility:false");
        
        // Button Operations
        game_menu_game_music.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                System.out.println("Enter çalıştı");
                game_menu_game_music.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
            }
        });
        
        game_menu_game_sound.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                game_menu_game_sound.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
            }
        });
        game_menu_back_to_main_menu.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                game_menu_back_to_main_menu.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
            }
        });
        game_menu_back_to_game.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                game_menu_back_to_game.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
            }
        });
        game_menu_exit_game.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                game_menu_exit_game.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
            }
        });
        
        // operations for image buttons in the right bottom
        settlement_image_button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                settlement_selected_rectangle.setVisible(true);
                city_selected_rectangle.setVisible(false);
                road_selected_rectangle.setVisible(false);
                
                construct_type = Constrcution_type.SETTLEMENT;
            }
            
        });
        
        city_image_button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                settlement_selected_rectangle.setVisible(false);
                city_selected_rectangle.setVisible(true);
                road_selected_rectangle.setVisible(false);
                
                construct_type = Constrcution_type.CITY;
            }
            
        });
        
        road_image_button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                settlement_selected_rectangle.setVisible(false);
                city_selected_rectangle.setVisible(false);
                road_selected_rectangle.setVisible(true);
                
                construct_type = Constrcution_type.ROAD;
            }
            
        });
        
    }   
    private void goMainMenu() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/MainMenuScene.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
    @FXML
    private void goBackMainMenu(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/MainMenuScene.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
    @FXML
    private void openGameMenu(ActionEvent event){
        game_menu_filter.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
        game_menu_background.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
        game_menu_title.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
        game_menu_game_music.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
        game_menu_game_sound.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
        game_menu_back_to_main_menu.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
        game_menu_back_to_game.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
        game_menu_exit_game.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
    }
    
    @FXML
    private void terminateGame(ActionEvent event) throws IOException{
        System.exit(0);
    }
    
    // Getter & Setter methods
    public Constrcution_type getConstruct_type(){
        return construct_type;
    }
    
    @FXML
    private void rollDice(ActionEvent event) throws IOException{
        // roll dice operations ve dice değerinin ekrana yazdırılması -çizdirilmesi(?)-
    }
    
    @FXML
    private void goBackToGame(ActionEvent event) throws IOException{
        game_menu_filter.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: false");
        game_menu_background.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: false");
        game_menu_title.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: false");
        game_menu_game_music.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: false");
        game_menu_game_sound.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: false");
        game_menu_back_to_main_menu.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: false");
        game_menu_back_to_game.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: false");
        game_menu_exit_game.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: false");
    }
    
    @FXML
    private void changeSoundState(ActionEvent event) throws IOException{
        if(gameSound){
            game_menu_game_sound.setText("Game Sound: OFF");
            gameSound = false;
        } else {
            game_menu_game_sound.setText("Game Sound: ON");
            gameSound = true;
        }
    }
    
    @FXML
    private void changeMusicState(ActionEvent event) throws IOException{
        if(gameMusic){
            game_menu_game_music.setText("Game Music: OFF");
            gameMusic = false;
        } else {
            game_menu_game_music.setText("Game Music: ON");
            gameMusic = true;
        }
    }
    
    @FXML
    private void endTurn(ActionEvent event) throws IOException{
        your_turn_rectangle.setStyle("visibility:false");
        your_turn_text.setStyle("visibility:false");
        next_player_1.setStyle("visibility:true");
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                System.out.println("thread1 girdi.");
                your_turn_rectangle.setStyle("visibility:false");
                your_turn_text.setStyle("visibility:false");
                next_player_1.setVisible(false);
                next_player_2.setVisible(true);
                next_player_1.setStyle("visibility:false");
                next_player_2.setStyle("visibility:true");
            }
        });
        Task<Void> sleeper2 = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                your_turn_rectangle.setStyle("visibility:false");
                your_turn_text.setStyle("visibility:false");
                next_player_2.setStyle("visibility:false");
                next_player_3.setStyle("visibility:true");
            }
        });
        Task<Void> sleeper3 = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        new Thread(sleeper).start();
        
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                your_turn_rectangle.setStyle("visibility:false");
                your_turn_text.setStyle("visibility:false");
                next_player_2.setStyle("visibility:false");
                next_player_3.setStyle("visibility:true");
            }
        });
        
        new Thread(sleeper).start();
        
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                next_player_3.setStyle("visibility:false");
                your_turn_rectangle.setStyle("visibility:true");
                your_turn_text.setStyle("visibility:true");
            }
        });
        
        new Thread(sleeper).start();
    }
}