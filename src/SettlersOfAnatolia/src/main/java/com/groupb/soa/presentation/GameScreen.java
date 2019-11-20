/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.presentation;

import com.groupb.soa.business.models.Dice;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author Enes Merdane 
 */
public class GameScreen implements Initializable {
    // Properties
    // Constants
    private static final int NUMBER_OF_HEXAGONS = 19;
    
    // Variables
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
    private Button roll_dice_button;
    @FXML
    private ImageView dice1;
    @FXML
    private ImageView dice2;
    @FXML 
    private Circle vertex1;
    
    @FXML
    private Polygon hexagon1;
    
    @FXML
    private Polygon hexagonList[];
    
    
    private boolean gameSound = true;
    private boolean gameMusic = true;
    private Dice d1, d2;
    
    private Group hexagonGroup;
    
    @FXML
    private Text debugText;
    
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
        
        hexagonList = new Polygon[NUMBER_OF_HEXAGONS];
        
        vertex1.setOnMouseClicked( new VertexHandler(1));
        construct_type = Constrcution_type.EMPTY;
        roll_dice_button.setOnMouseClicked( new EventHandler<javafx.scene.input.MouseEvent>() {
           @Override
           public void handle(javafx.scene.input.MouseEvent event)
           {
               Dice d1, d2;
               d1 = new Dice(0.0, 0.0);
               d2 = new Dice(0.0, 0.0);
               int d1i = d1.rollDice();
               int d2i = d2.rollDice();
               Image d1img, d2img;
               switch(d1i)
               {
                   case 1: d1img = new Image("https://www.wpclipart.com/recreation/games/dice/die_face_1.png"); break;
                   case 2: d1img = new Image("https://www.wpclipart.com/recreation/games/dice/die_face_2.png"); break;
                   case 3: d1img = new Image("https://www.wpclipart.com/recreation/games/dice/die_face_3.png"); break;
                   case 4: d1img = new Image("https://www.wpclipart.com/recreation/games/dice/die_face_4.png"); break;
                   case 5: d1img = new Image("https://www.wpclipart.com/recreation/games/dice/die_face_5.png"); break;
                   case 6: d1img = new Image("https://www.wpclipart.com/recreation/games/dice/die_face_6.png"); break;
                   default: d1img = null; break;
               }
               switch(d2i)
               {
                   case 1: d2img = new Image("https://www.wpclipart.com/recreation/games/dice/die_face_1.png"); break;
                   case 2: d2img = new Image("https://www.wpclipart.com/recreation/games/dice/die_face_2.png"); break;
                   case 3: d2img = new Image("https://www.wpclipart.com/recreation/games/dice/die_face_3.png"); break;
                   case 4: d2img = new Image("https://www.wpclipart.com/recreation/games/dice/die_face_4.png"); break;
                   case 5: d2img = new Image("https://www.wpclipart.com/recreation/games/dice/die_face_5.png"); break;
                   case 6: d2img = new Image("https://www.wpclipart.com/recreation/games/dice/die_face_6.png"); break;
                   default: d2img = null; break;
               }
               dice1.setImage(d1img);
               dice2.setImage(d2img);
           }
        });
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
        
        rootPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
              String msg =
                "(x: "       + event.getX()      + ", y: "       + event.getY()       + ") -- " +
                "(sceneX: "  + event.getSceneX() + ", sceneY: "  + event.getSceneY()  + ") -- " +
                "(screenX: " + event.getScreenX()+ ", screenY: " + event.getScreenY() + ")";

              debugText.setText(msg);
            }
          });
        
        //Creating Hexagon
        // Stage stage = (Stage) rootPane.getScene().getWindow();
        // hexagonGroup = new Group(hexagon1);
        
        double baseX = 773.0;
        double baseY = 237.0;
        
        initiateHexagons(hexagonList, NUMBER_OF_HEXAGONS);
        
        int i = 0;
        
        for(int k = 0; k < 3; k++, i++){
            createHexagon(baseX, baseY, hexagonList[i], "hexagon_forest_image");
            baseX += 147;
        }
        baseX = 773.0 - 72.0;
        baseY = 237.0 + 116.0;
        for(int k = 0; k < 4; k++, i++){
            createHexagon(baseX, baseY, hexagonList[i], "hexagon_forest_image");
            baseX += 147;
        }

        baseX = 773.0 - 72.0 - 72.0;
        baseY = 237.0 + 116.0 + 116.0;

        for(int k = 0; k < 5; k++, i++){
            createHexagon(baseX, baseY, hexagonList[i], "hexagon_forest_image");
            baseX += 147;
        }

        baseX = 773.0 - 72.0;
        baseY = 237.0 + 116.0 + 116.0 + 116.0;

        for(int k = 0; k < 4; k++, i++){
            createHexagon(baseX, baseY, hexagonList[i], "hexagon_forest_image");
            baseX += 147;
        }

        baseX = 773.0;
        baseY = 237.0 + 116.0 + 116.0 + 116.0 + 116.0;

        for(int k = 0; k < 3; k++, i++){
            createHexagon(baseX, baseY, hexagonList[i], "hexagon_forest_image");
            baseX += 147;
        }
            
            
        
    }  
    
    private void initiateHexagons(Polygon[] arr, int size){
        for(int i = 0; i < size; i++){
            arr[i] = new Polygon();
        }
    }
    
    private void createHexagon(Double startPointX, Double startPointY, Polygon hexagon, String imageName){
        hexagon.setLayoutX(startPointX);
        hexagon.setLayoutY(startPointY);
        hexagon.getPoints().clear();
        hexagon.getPoints().addAll(new Double[]{        
            0.0, 0.0, 
            73.0, -40.0, 
            147.0, 0.0,          
            147.0, 73.0, 
            73.0, 116.0,                   
            0.0, 73.0, 
         });
        
        rootPane.getChildren().add(hexagon);
        
        // DEBUG
        System.out.println( "Created hexagon on: " + startPointX + " - " + startPointY);
         
        String image_path = "/images/" + imageName + ".jpg";
        Image img = new Image(image_path);
        hexagon.setFill(new ImagePattern(img));
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
        }
    }
}
