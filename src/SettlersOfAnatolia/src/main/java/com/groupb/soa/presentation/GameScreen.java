/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.presentation;

import com.groupb.soa.MainApp;
import com.groupb.soa.business.controller.GameController;
import com.groupb.soa.business.models.Dice;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
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
    private static final int NUMBER_OF_VERTICES = 54;
    private static final int NUMBER_OF_EDGES = 72;
    
    private static final double HEXAGONS_BASE_X = 623.0;
    private static final double HEXAGONS_BASE_Y = 237.0;
    
    // Variables
    
    // View properties
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
    
    private GameController mainController;
    
    
    private Construction_type construct_type;
    enum Construction_type{
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
    
    @FXML
    private Text grain;
    @FXML
    private Text lumber;
    @FXML
    private Text wool;
    @FXML
    private Text stone;
    @FXML
    private Text brick;
    
    private boolean gameSound = true;
    private boolean gameMusic = true;
    private Dice d1, d2;
    
    private Point[] verticeList;
    private Circle[] circleList;
    private Line[] edgeList;
    
    
    // Constructors
    /**
     * @param mainController to call build methods
     *  @initiatedDate 17.11.2019
     *  @initiator Enes
     *  @lastEdited 17.11.2019
     *  @author null 
     * This constructor is for the first initiated game
     */
    public GameScreen(){
        this.mainController = MainApp.getInstance().getGameControllerObj();
    }
    
    // Methods

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        hexagonList = new Polygon[NUMBER_OF_HEXAGONS];
        
        construct_type = Construction_type.EMPTY;
        
        verticeList = new Point[NUMBER_OF_VERTICES];
        circleList  = new Circle[NUMBER_OF_VERTICES];
        edgeList = new Line[NUMBER_OF_EDGES];
        
        
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
                
                construct_type = Construction_type.SETTLEMENT;
            }
            
        });
        
        city_image_button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                settlement_selected_rectangle.setVisible(false);
                city_selected_rectangle.setVisible(true);
                road_selected_rectangle.setVisible(false);
                
                construct_type = Construction_type.CITY;
            }
            
        });
        
        road_image_button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                settlement_selected_rectangle.setVisible(false);
                city_selected_rectangle.setVisible(false);
                road_selected_rectangle.setVisible(true);
                
                construct_type = Construction_type.ROAD;
            }
            
        });
        
        roll_dice_button.setOnMouseClicked( new DiceHandler());
        //Creating Hexagon
        // Stage stage = (Stage) rootPane.getScene().getWindow();
        // hexagonGroup = new Group(hexagon1);
        
        
        
        
        double baseX = HEXAGONS_BASE_X;
        double baseY = HEXAGONS_BASE_Y;
        
        initiateHexagons(hexagonList, NUMBER_OF_HEXAGONS);
        
        placeHexagons(baseX, baseY);
        
        setLocationToAllVertices(baseX + 70.0, baseY - 30.0);
        
        drawAllEdges();
        
        drawAllVertices();
        
        for(int i = 0; i < NUMBER_OF_VERTICES; i++){
            circleList[i].setOnMouseClicked(new VertexHandler(i));
        }
        
        for(int i = 0; i < NUMBER_OF_HEXAGONS; i++) 
        {
           hexagonList[i].setOnMouseClicked(new HexagonHandler(i));
        }  
    }
    private void drawAllEdges(){
        int i = 0;
        
        // Draws the first line
        for(int k = 0; k < 3; k++){
            edgeList[i] = new Line(verticeList[ k+3 ].getX(), verticeList[ k+3 ].getY(), verticeList[ k ].getX(), verticeList[ k ].getY());
            edgeList[i].setFill(Color.BLACK);
            edgeList[i].setStrokeWidth(5.0);
            edgeList[i].setOnMouseClicked(new EdgeHandler(i));
            rootPane.getChildren().add(edgeList[i]);
            i++;

            edgeList[i] = new Line(verticeList[ k ].getX(), verticeList[ k ].getY(), verticeList[ k+4 ].getX(), verticeList[ k+4 ].getY());
            edgeList[i].setFill(Color.BLACK);
            edgeList[i].setStrokeWidth(5.0);
            edgeList[i].setOnMouseClicked(new EdgeHandler(i));
            rootPane.getChildren().add(edgeList[i]);
            i++;
        }
        
        // Draws the first vertical lines
        for(int k = 0; k < 4; k++){
            edgeList[i] = new Line(verticeList[ k+3 ].getX(), verticeList[ k+3 ].getY(), verticeList[ k+7 ].getX(), verticeList[ k+7 ].getY());
            edgeList[i].setFill(Color.BLACK);
            edgeList[i].setStrokeWidth(5.0);
            edgeList[i].setOnMouseClicked(new EdgeHandler(i));
            rootPane.getChildren().add(edgeList[i]);
            i++;
        }
        
        // Draws the second line
        for(int k = 7; k < 11; k++){
            edgeList[i] = new Line(verticeList[ k+4 ].getX(), verticeList[ k+4 ].getY(), verticeList[ k ].getX(), verticeList[ k ].getY());
            edgeList[i].setFill(Color.BLACK);
            edgeList[i].setStrokeWidth(5.0);
            edgeList[i].setOnMouseClicked(new EdgeHandler(i));
            rootPane.getChildren().add(edgeList[i]);
            i++;

            edgeList[i] = new Line(verticeList[ k ].getX(), verticeList[ k ].getY(), verticeList[ k+5 ].getX(), verticeList[ k+5 ].getY());
            edgeList[i].setFill(Color.BLACK);
            edgeList[i].setStrokeWidth(5.0);
            edgeList[i].setOnMouseClicked(new EdgeHandler(i));
            rootPane.getChildren().add(edgeList[i]);
            i++;
        }
        
        // Draws the second vertical lines
        for(int k = 7; k < 12; k++){
            edgeList[i] = new Line(verticeList[ k+4 ].getX(), verticeList[ k+4 ].getY(), verticeList[ k+9 ].getX(), verticeList[ k+9 ].getY());
            edgeList[i].setFill(Color.BLACK);
            edgeList[i].setStrokeWidth(5.0);
            edgeList[i].setOnMouseClicked(new EdgeHandler(i));
            rootPane.getChildren().add(edgeList[i]);
            i++;
        }
        
        // Draws the third line
        for(int k = 16; k < 21; k++){
            edgeList[i] = new Line(verticeList[ k+5 ].getX(), verticeList[ k+5 ].getY(), verticeList[ k ].getX(), verticeList[ k ].getY());
            edgeList[i].setFill(Color.BLACK);
            edgeList[i].setStrokeWidth(5.0);
            edgeList[i].setOnMouseClicked(new EdgeHandler(i));
            rootPane.getChildren().add(edgeList[i]);
            i++;

            edgeList[i] = new Line(verticeList[ k ].getX(), verticeList[ k ].getY(), verticeList[ k+6 ].getX(), verticeList[ k+6 ].getY());
            edgeList[i].setFill(Color.BLACK);
            edgeList[i].setStrokeWidth(5.0);
            edgeList[i].setOnMouseClicked(new EdgeHandler(i));
            rootPane.getChildren().add(edgeList[i]);
            i++;
        }
        
        // Draws the third vertical lines
        for(int k = 16; k < 22; k++){
            edgeList[i] = new Line(verticeList[ k+5 ].getX(), verticeList[ k+5 ].getY(), verticeList[ k+11 ].getX(), verticeList[ k+11 ].getY());
            edgeList[i].setFill(Color.BLACK);
            edgeList[i].setStrokeWidth(5.0);
            edgeList[i].setOnMouseClicked(new EdgeHandler(i));
            rootPane.getChildren().add(edgeList[i]);
            i++;
        }
        
        // Draws the fourth line
        for(int k = 33; k < 38; k++){
            edgeList[i] = new Line(verticeList[ k-6 ].getX(), verticeList[ k-6 ].getY(), verticeList[ k ].getX(), verticeList[ k ].getY());
            edgeList[i].setFill(Color.BLACK);
            edgeList[i].setStrokeWidth(5.0);
            edgeList[i].setOnMouseClicked(new EdgeHandler(i));
            rootPane.getChildren().add(edgeList[i]);
            i++;

            edgeList[i] = new Line(verticeList[ k ].getX(), verticeList[ k ].getY(), verticeList[ k-5 ].getX(), verticeList[ k-5 ].getY());
            edgeList[i].setFill(Color.BLACK);
            edgeList[i].setStrokeWidth(5.0);
            edgeList[i].setOnMouseClicked(new EdgeHandler(i));
            rootPane.getChildren().add(edgeList[i]);
            i++;
        }
        
        // Draws the fourth vertical lines
        for(int k = 33; k < 38; k++){
            edgeList[i] = new Line(verticeList[ k ].getX(), verticeList[ k ].getY(), verticeList[ k+5 ].getX(), verticeList[ k+5 ].getY());
            edgeList[i].setFill(Color.BLACK);
            edgeList[i].setStrokeWidth(5.0);
            edgeList[i].setOnMouseClicked(new EdgeHandler(i));
            rootPane.getChildren().add(edgeList[i]);
            i++;
        }
        
        // Draws the fifth line
        for(int k = 38; k < 42; k++){
            edgeList[i] = new Line(verticeList[ k+5 ].getX(), verticeList[ k+5 ].getY(), verticeList[ k ].getX(), verticeList[ k ].getY());
            edgeList[i].setFill(Color.BLACK);
            edgeList[i].setStrokeWidth(5.0);
            edgeList[i].setOnMouseClicked(new EdgeHandler(i));
            rootPane.getChildren().add(edgeList[i]);
            i++;

            edgeList[i] = new Line(verticeList[ k+5 ].getX(), verticeList[ k+5 ].getY(), verticeList[ k+1 ].getX(), verticeList[ k+1 ].getY());
            edgeList[i].setFill(Color.BLACK);
            edgeList[i].setStrokeWidth(5.0);
            edgeList[i].setOnMouseClicked(new EdgeHandler(i));
            rootPane.getChildren().add(edgeList[i]);
            i++;
        }
        
        // Draws the fifth vertical lines
        for(int k = 43; k < 47; k++){
            edgeList[i] = new Line(verticeList[ k ].getX(), verticeList[ k ].getY(), verticeList[ k+4 ].getX(), verticeList[ k+4 ].getY());
            edgeList[i].setFill(Color.BLACK);
            edgeList[i].setStrokeWidth(5.0);
            edgeList[i].setOnMouseClicked(new EdgeHandler(i));
            rootPane.getChildren().add(edgeList[i]);
            i++;
        }
        
        // Draws the sixth line
        for(int k = 47; k < 50; k++){
            edgeList[i] = new Line(verticeList[ k+4 ].getX(), verticeList[ k+4 ].getY(), verticeList[ k ].getX(), verticeList[ k ].getY());
            edgeList[i].setFill(Color.BLACK);
            edgeList[i].setStrokeWidth(5.0);
            edgeList[i].setOnMouseClicked(new EdgeHandler(i));
            rootPane.getChildren().add(edgeList[i]);
            i++;
            
            edgeList[i] = new Line(verticeList[ k+4 ].getX(), verticeList[ k+4 ].getY(), verticeList[ k+1 ].getX(), verticeList[ k+1 ].getY());
            edgeList[i].setFill(Color.BLACK);
            edgeList[i].setStrokeWidth(5.0);
            edgeList[i].setOnMouseClicked(new EdgeHandler(i));
            rootPane.getChildren().add(edgeList[i]);
            i++;
            System.out.println(i);
        }
    }
    
    
    private void drawAllVertices(){
        for(int i = 0; i < NUMBER_OF_VERTICES; i++){
            circleList[i] = new Circle(verticeList[i].getX(), verticeList[i].getY(), 7.0);
            circleList[i].setFill(Color.DODGERBLUE);
            rootPane.getChildren().add(circleList[i]);
        }
    }
    
    private void setLocationToAllVertices(double baseX, double baseY) {
        int i = 0;
        double baseX2;
        double baseY2;
        
        double tempX = baseX;
        double tempY = baseY;
        baseX2 = tempX;
        baseY2 = tempY;
        for(int k = 0; k < 3; k++, i++){
            verticeList[i] = new Point(tempX, tempY);
            tempX += 140.0;
        }
        System.out.println(baseX2 + " - " + baseY2);
        
        
        tempX = baseX2 - 70.0;
        baseX2 = tempX;
        tempY = baseY2 + 30.0 + 3.0;
        baseY2 = tempY;
        
        System.out.println(baseX2 + " - " + baseY2);
        
        for(int k = 0; k < 4; k++, i++){
            verticeList[i] = new Point(tempX, tempY);
            tempX += 140.0;
        }
        
        System.out.println(baseX2 + " - " + baseY2);
        
        tempY = baseY2 + 80.0 + 6.0;
        baseY2 = tempY;
        tempX = baseX2;
        
        System.out.println(baseX2 + " - " + baseY2);
        
        for(int k = 0; k < 4; k++, i++){
            verticeList[i] = new Point(tempX, tempY);
            tempX += 140.0;
        }
        
        System.out.println(baseX2 + " - " + baseY2);
        
        tempX = baseX2 - 70.0;
        baseX2 = tempX;
        tempY = baseY2 + 30.0;
        baseY2 = tempY;
        
        for(int k = 0; k < 5; k++, i++){
            verticeList[i] = new Point(tempX, tempY);
            tempX += 140.0;
        }

        tempY = baseY2 +  80.0;
        baseY2 = tempY;
        tempX = baseX2;
        
        for(int k = 0; k < 5; k++, i++){
            verticeList[i] = new Point(tempX, tempY);
            tempX += 140.0;
        }

        tempX = baseX2 - 70.0;
        baseX2 = tempX;
        tempY = baseY2 + 30.0;
        baseY2 = tempY;
        
        for(int k = 0; k < 6; k++, i++){
            verticeList[i] = new Point(tempX, tempY);
            tempX += 140.0;
        }

        tempY = baseY2 +  80.0 + 6.0;
        baseY2 = tempY;
        tempX = baseX2;
        
        for(int k = 0; k < 6; k++, i++){
            verticeList[i] = new Point(tempX, tempY);
            tempX += 140.0;
        }

        tempX = baseX2 + 70.0;
        baseX2 = tempX;
        tempY = baseY2 + 30.0;
        baseY2 = tempY;
        
        for(int k = 0; k < 5; k++, i++){
            verticeList[i] = new Point(tempX, tempY);
            tempX += 140.0;
        }

        tempY = baseY2 + 80.0 + 6.0;
        baseY2 = tempY;
        tempX = baseX2;
        
        for(int k = 0; k < 5; k++, i++){
            verticeList[i] = new Point(tempX, tempY);
            tempX += 140.0;
        }

        tempX = baseX2 + 70.0;
        baseX2 = tempX;
        tempY = baseY2 + 30.0;
        baseY2 = tempY;
        
        for(int k = 0; k < 4; k++, i++){
            verticeList[i] = new Point(tempX, tempY);
            tempX += 140.0;
        }

        tempY = baseY2 +  80.0 + 6.0;
        baseY2 = tempY;
        tempX = baseX2;
        
        for(int k = 0; k < 4; k++, i++){
            verticeList[i] = new Point(tempX, tempY);
            tempX += 140.0;
        }

        tempY = baseY2 +  30.0;
        baseY2 = tempY;
        tempX = baseX2 + 70.0;
        
        for(int k = 0; k < 3; k++, i++){
            verticeList[i] = new Point(tempX, tempY);
            tempX += 140.0;
        }
    }
    
    private void initiateHexagons(Polygon[] arr, int size){
        for(int i = 0; i < size; i++){
            arr[i] = new Polygon();
        }
    }
    
    private void placeHexagons(double initialX, double initialY){
        int i = 0;
        /**
         * hexagon_forest_image
         * hexagon_mountain_image
         * hexagon_
         */
        for(int k = 0; k < 3; k++, i++){
            createHexagon(initialX, initialY, hexagonList[i], "hexagon_forest_image");
            initialX += 140;
        }
        
        initialX = HEXAGONS_BASE_X - 70.0;
        initialY = HEXAGONS_BASE_Y + 116.0;
        
        for(int k = 0; k < 4; k++, i++){
            createHexagon(initialX, initialY, hexagonList[i], "hexagon_hill_image");
            initialX += 140;
        }

        initialX = HEXAGONS_BASE_X - 70.0 - 70.0;
        initialY = HEXAGONS_BASE_Y + 116.0 + 116.0;

        for(int k = 0; k < 5; k++, i++){
            if(k== 2){
            createHexagon(initialX, initialY, hexagonList[i], "hexagon_desert_image");
                
            } else {
                createHexagon(initialX, initialY, hexagonList[i], "hexagon_mountain_image"); 
            }
            initialX += 140;
        }

        initialX = HEXAGONS_BASE_X - 70.0;
        initialY = HEXAGONS_BASE_Y + 116.0 + 116.0 + 116.0;

        for(int k = 0; k < 4; k++, i++){
            createHexagon(initialX, initialY, hexagonList[i], "hexagon_pasture_image");
            initialX += 140;
        }

        initialX = HEXAGONS_BASE_X;
        initialY = HEXAGONS_BASE_Y + 116.0 + 116.0 + 116.0 + 116.0;

        for(int k = 0; k < 3; k++, i++){
            createHexagon(initialX, initialY, hexagonList[i], "hexagon_field_image");
            initialX += 140;
        }
    }
    
    private void createHexagon(Double startPointX, Double startPointY, Polygon hexagon, String imageName){
        hexagon.setLayoutX(startPointX);
        hexagon.setLayoutY(startPointY);
        hexagon.getPoints().clear();
        hexagon.getPoints().addAll(new Double[]{        
            0.0, 0.0, 
            70.0, -30.0, 
            140.0, 0.0,          
            140.0, 86.0, 
            70.0, 116.0,                   
            0.0, 86.0, 
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
    public Construction_type getConstruct_type(){
        return construct_type;
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
        System.out.println("endTurn tuşuna basıldı");
        your_turn_rectangle.setStyle("visibility:false");
        your_turn_text.setStyle("visibility:false");
        mainController.nextPlayer();
        refreshResources();
    }
    
    private void refreshResources()
    {
        // ore = 0, grain = 1, lumber = 2, wool = 3, brick = 4
        stone.setText( mainController.getCurrentPlayer().getSourceNo(0) + "");
        grain.setText( mainController.getCurrentPlayer().getSourceNo(1) + "");
        lumber.setText( mainController.getCurrentPlayer().getSourceNo(2) + "");
        wool.setText( mainController.getCurrentPlayer().getSourceNo(3) + "");
        brick.setText( mainController.getCurrentPlayer().getSourceNo(4) + "");
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
            //Circle circle = (Circle) e.getSource();
            //circle.setFill(Color.RED);
            
            if(construct_type == Construction_type.SETTLEMENT){
                System.out.println("Helelelele");
                if(mainController.buildSettlement(index)){
                    System.out.println("Bindik bir alamete gidiyoruz kıyamete amaneeen");
                    Circle circle = (Circle) e.getSource();
                    circle.setFill(mainController.getCurrentPlayerColor());
                    refreshResources();
                }
            }
            else if(construct_type == Construction_type.CITY){
                if(mainController.upgradeCity(index)){
                    Circle circle = (Circle) e.getSource();
                    circle.setFill(mainController.getCurrentPlayerColor());
                    refreshResources();
                }
            }
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
                // Line l = (Line) e.getSource();
                // l.setStroke(Color.RED);
                
            if( construct_type == Construction_type.ROAD){
                if( mainController.buildRoad(index) ){
                    Line l = (Line) e.getSource();
                    l.setStroke(mainController.getCurrentPlayerColor());
                    refreshResources();
                }
            }
            
        }
    }
    
    class DiceHandler implements EventHandler<MouseEvent>
    {
        DiceHandler(){}
        
        @Override
        public void handle( MouseEvent e)
        {
            int[] diceNums = mainController.rollDice();
            Image d1img, d2img;
            switch(diceNums[0])
            {
                case 1: d1img = new Image("images/die_face_1.png"); break;
                case 2: d1img = new Image("images/die_face_2.png"); break;
                case 3: d1img = new Image("images/die_face_3.png"); break;
                case 4: d1img = new Image("images/die_face_4.png"); break;
                case 5: d1img = new Image("images/die_face_5.png"); break;
                case 6: d1img = new Image("images/die_face_6.png"); break;
                default: d1img = null; break;
            }
            switch(diceNums[1])
            {
                case 1: d2img = new Image("images/die_face_1.png"); break;
                case 2: d2img = new Image("images/die_face_2.png"); break;
                case 3: d2img = new Image("images/die_face_3.png"); break;
                case 4: d2img = new Image("images/die_face_4.png"); break;
                case 5: d2img = new Image("images/die_face_5.png"); break;
                case 6: d2img = new Image("images/die_face_6.png"); break;
                default: d2img = null; break;
            }
            dice1.setImage(d1img);
            dice2.setImage(d2img);
            refreshResources();
        }
    }
    
    class HexagonHandler implements EventHandler<MouseEvent>
    {
        int index;
        HexagonHandler( int i)
        {
            index = i;
        }
        
        @Override
        public void handle(MouseEvent e)
        {
            mainController.sendRobberToHexagon(index);
            refreshResources();
        }
    }
}
