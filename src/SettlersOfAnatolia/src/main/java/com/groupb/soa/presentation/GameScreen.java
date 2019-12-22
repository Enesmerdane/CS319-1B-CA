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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
import javafx.scene.text.Font;
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
    private Text game_menu_title, eventText; 
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
    
    @FXML
    private MenuButton cardMenu;
    @FXML
    private MenuButton sourceMenu;
    
    @FXML
    private MenuItem knightChoice, roadChoice, yearChoice, monoChoice, insChoice, asdChoice;
    @FXML
    private MenuItem grainChoice, lumberChoice, woolChoice, oreChoice, brickChoice;
    @FXML
    private Text resourceMsg, knightNo, rbNo, yearNo, monoNo, insNo, asdNo;
    @FXML
    private Button buy_dev_card, trade_with_bank_button, trade_with_players_button;
    @FXML
    private Group tradeBankGroup, domesticTradeGroup, gameEndGroup;
    @FXML
    private Button grainBankIncrBtn, lumberBankIncrBtn, woolBankIncrBtn, oreBankIncrBtn, brickBankIncrBtn;
    @FXML
    private Button grainBankDecrBtn, lumberBankDecrBtn, woolBankDecrBtn, oreBankDecrBtn, brickBankDecrBtn;
    @FXML
    private Button grainSelfIncrBtn, lumberSelfIncrBtn, woolSelfIncrBtn, oreSelfIncrBtn, brickSelfIncrBtn;
    @FXML
    private Button grainSelfDecrBtn, lumberSelfDecrBtn, woolSelfDecrBtn, oreSelfDecrBtn, brickSelfDecrBtn;
    @FXML
    private Text bankGrain, bankLumber, bankWool, bankOre, bankBrick;
    @FXML
    private Text selfGrain, selfLumber, selfWool, selfOre, selfBrick;
    @FXML
    private Text actualSourceRights, usedSourceRights;
    @FXML
    private Button twbaccept, twbcancel;
    
    @FXML
    private ListView tradeRequests;
    @FXML
    private Text p1grain, p1lumber, p1wool, p1ore, p1brick, playerOffers, playerInReturn, treqName;
    @FXML
    private Text p2grain, p2lumber, p2wool, p2ore, p2brick;
    @FXML
    private Button grainP1decr, lumberP1decr, woolP1decr, oreP1decr, brickP1decr;
    @FXML
    private Button grainP1incr, lumberP1incr, woolP1incr, oreP1incr, brickP1incr;
    @FXML
    private Button grainP2decr, lumberP2decr, woolP2decr, oreP2decr, brickP2decr;
    @FXML
    private Button grainP2incr, lumberP2incr, woolP2incr, oreP2incr, brickP2incr;
    @FXML
    private Button twpcreate, twpaccept, twpclose;
    @FXML
    private Text p1Knights, p2Knights, p3Knights, p4Knights;
    private ImageView robberImageView;
    
    private GameController mainController;
    
    
    private Construction_type construct_type;
    enum Construction_type{
        EMPTY, SETTLEMENT, CITY, ROAD
    }
    
    private ResourceSetting rscSet;
    enum ResourceSetting
    {
        NONE, SOURCE1, SOURCE2
    }
    
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
    @FXML
    private Button playCard;
    
    @FXML
    private ImageView grainPic;
    @FXML
    private ImageView lumberPic;
    @FXML
    private ImageView woolPic;
    @FXML
    private ImageView orePic;
    @FXML
    private ImageView brickPic;
    @FXML
    private Rectangle grainEffect, lumberEffect, woolEffect, oreEffect, brickEffect;
    @FXML
    private Text p1score, p2score, p3score, p4score, playerName;
    private boolean gameSound = true;
    private boolean gameMusic = true;
    
    private Point[] verticeList;
    private Circle[] circleList;
    private Line[] edgeList;
    private PlayCardHandler pch;
    
    private Circle[] hexagonNumberCircles;
    private Text[] hexagonNumbers;
    private static GameScreen instance;
    
    private int[] domesticOffers;
    private int[] domesticInReturn;
    
    private int currentTradeRequest;
    private ObservableList<Text> tradeRequestList;
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
        
        instance = this;
    }

    // Methods
    public static GameScreen getInstance(){
        return instance;
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        hexagonList = new Polygon[NUMBER_OF_HEXAGONS];
        tradeRequestList = FXCollections.observableArrayList();
        construct_type = Construction_type.EMPTY;
        rscSet = ResourceSetting.NONE;
        verticeList = new Point[NUMBER_OF_VERTICES];
        circleList  = new Circle[NUMBER_OF_VERTICES];
        edgeList = new Line[NUMBER_OF_EDGES];
        
        hexagonNumberCircles = new Circle[NUMBER_OF_HEXAGONS];
        hexagonNumbers = new Text[NUMBER_OF_HEXAGONS];
        currentTradeRequest = -1;
        domesticOffers = new int[5];
        domesticInReturn = new int[5];
        
        pch = new PlayCardHandler();
        playCard.setOnMouseClicked(pch);
        knightChoice.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle( ActionEvent e)
            {
                if( rscSet == ResourceSetting.NONE)
                {
                    cardMenu.setText( knightChoice.getText());
                    pch.setCardType(knightChoice.getText());
                }
            }
        });
        
        roadChoice.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle( ActionEvent e)
            {
                if( rscSet == ResourceSetting.NONE)
                {
                    cardMenu.setText( roadChoice.getText());
                    pch.setCardType( roadChoice.getText());
                }
            }
        });
        
        yearChoice.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle( ActionEvent e)
            {
                if( rscSet == ResourceSetting.NONE)
                {
                    cardMenu.setText( yearChoice.getText());
                    pch.setCardType( yearChoice.getText());
                }
            }
        });
        
        monoChoice.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle( ActionEvent e)
            {
                if( rscSet == ResourceSetting.NONE)
                {
                    cardMenu.setText( monoChoice.getText());
                    pch.setCardType( monoChoice.getText());
                }
            }
        });
        
        insChoice.setOnAction( new EventHandler<ActionEvent>(){
            @Override
            public void handle( ActionEvent event)
            {
                cardMenu.setText( insChoice.getText());
                pch.setCardType( insChoice.getText());
            }
        });
        
        asdChoice.setOnAction( new EventHandler<ActionEvent>(){
            @Override
            public void handle( ActionEvent event)
            {
                cardMenu.setText( asdChoice.getText());
                pch.setCardType( "Anatolian Shepherd Dog");
            }
        });
        
        grainPic.setOnMouseClicked(new ResourceClickHandler("Grain"));
        lumberPic.setOnMouseClicked(new ResourceClickHandler("Lumber"));
        woolPic.setOnMouseClicked(new ResourceClickHandler("Wool"));
        orePic.setOnMouseClicked(new ResourceClickHandler("Ore"));
        brickPic.setOnMouseClicked(new ResourceClickHandler("Brick"));
        
        // Button Operations
        trade_with_players_button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle( MouseEvent e)
            {
                if( !tradeBankGroup.isVisible() && !domesticTradeGroup.isVisible())
                {
                    toggleDomesticTradeMenu(true);
                }
            }
        });
        trade_with_bank_button.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle( MouseEvent e)
            {
                if( !tradeBankGroup.isVisible() && !domesticTradeGroup.isVisible())
                {
                    toggleTwBMenu(true);
                }
            }
        });
        buy_dev_card.setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle( MouseEvent e)
            {
                // if one of the trade windows are open, return.
                if( tradeBankGroup.isVisible() || domesticTradeGroup.isVisible())
                    return;
                System.out.println( "Buy card checkpoint" + mainController.buyCard());
                refreshResources();
                refreshCardNumbers();
            }
        });
        game_menu_game_music.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                System.out.println("Enter- çalıştı");
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
                try
                {
                terminateGame(null);
                }
                catch( Exception e)
                {
                    System.out.println( "Unexpected error");
                }
            }
        });
        
        // operations for image buttons in the right bottom
        settlement_image_button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                // if one of the trade windows are open, return.
                if( tradeBankGroup.isVisible() || domesticTradeGroup.isVisible())
                     return;
                settlement_selected_rectangle.setVisible(true);
                city_selected_rectangle.setVisible(false);
                road_selected_rectangle.setVisible(false);
                
                construct_type = Construction_type.SETTLEMENT;
            }
            
        });
        
        city_image_button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                // if one of the trade windows are open, return.
                if( tradeBankGroup.isVisible() || domesticTradeGroup.isVisible())
                  return;
                settlement_selected_rectangle.setVisible(false);
                city_selected_rectangle.setVisible(true);
                road_selected_rectangle.setVisible(false);
                
                construct_type = Construction_type.CITY;
            }
            
        });
        
        road_image_button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                // if one of the trade windows are open, return.
                if( tradeBankGroup.isVisible() || domesticTradeGroup.isVisible())
                    return;
                settlement_selected_rectangle.setVisible(false);
                city_selected_rectangle.setVisible(false);
                road_selected_rectangle.setVisible(true);
                
                construct_type = Construction_type.ROAD;
            }
            
        });
        
        roll_dice_button.setOnMouseClicked( new DiceHandler());
        // Creating Hexagon
        // Stage stage = (Stage) rootPane.getScene().getWindow();
        // hexagonGroup = new Group(hexagon1);
        
        
        
        
        double baseX = HEXAGONS_BASE_X;
        double baseY = HEXAGONS_BASE_Y;
        
        initiateHexagons(hexagonList, NUMBER_OF_HEXAGONS);
        
        placeHexagons(baseX, baseY);
        
        setLocationToAllVertices(baseX + 70.0, baseY - 30.0);
        
        drawAllEdges();
        
        drawAllVertices();
        
        setAllCircleNumbersToFront();
        //setAllHexagonNumbersToFront();
        for(int i = 0; i < NUMBER_OF_VERTICES; i++){
            circleList[i].setOnMouseClicked(new VertexHandler(i));
        }
        
        for(int i = 0; i < NUMBER_OF_HEXAGONS; i++) 
        {
           hexagonList[i].setOnMouseClicked(new HexagonHandler(i));
           hexagonNumberCircles[i].setOnMouseClicked(new HexagonHandler(i));
        }  
        
        initializeTwBMenu();
        initializeDomesticTradeMenu();
        
        robberImageView.toFront();
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
            circleList[i].setFill(Color.BLACK);
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

        tempY = baseY2 + 80.0 + 6.0;
        baseY2 = tempY;
        tempX = baseX2;
        
        for(int k = 0; k < 4; k++, i++){
            verticeList[i] = new Point(tempX, tempY);
            tempX += 140.0;
        }

        tempY = baseY2 + 30.0;
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
        int index_sources = 0;
        int index_numbers = 0;
        int[] hexagonSources = mainController.getHexagonSources();
        int[] hexagonsNumbers = mainController.getNumberOfHexagons();
        
        for(int k = 0; k < 3; k++, i++){
            createHexagon(initialX, initialY, hexagonList[i], hexagonSources[index_sources], hexagonsNumbers[index_numbers], i);
            index_numbers++;
            index_sources++;
            initialX += 140;
        }
        
        initialX = HEXAGONS_BASE_X - 70.0;
        initialY = HEXAGONS_BASE_Y + 116.0;
        
        for(int k = 0; k < 4; k++, i++){
            createHexagon(initialX, initialY, hexagonList[i], hexagonSources[index_sources], hexagonsNumbers[index_numbers], i);
            index_numbers++;
            index_sources++;
            initialX += 140;
        }

        initialX = HEXAGONS_BASE_X - 70.0 - 70.0;
        initialY = HEXAGONS_BASE_Y + 116.0 + 116.0;

        for(int k = 0; k < 5; k++, i++){
            if(k== 2){
            createHexagon(initialX, initialY, hexagonList[i], hexagonSources[index_sources], hexagonsNumbers[index_numbers], i);
            index_numbers++;
            index_sources++;
                
            } else {
                createHexagon(initialX, initialY, hexagonList[i], hexagonSources[index_sources], hexagonsNumbers[index_numbers], i);
                index_numbers++;
                index_sources++;
            }
            initialX += 140;
        }

        initialX = HEXAGONS_BASE_X - 70.0;
        initialY = HEXAGONS_BASE_Y + 116.0 + 116.0 + 116.0;

        for(int k = 0; k < 4; k++, i++){
            createHexagon(initialX, initialY, hexagonList[i], hexagonSources[index_sources], hexagonsNumbers[index_numbers], i);
            index_numbers++;
            index_sources++;
            initialX += 140;
        }

        initialX = HEXAGONS_BASE_X;
        initialY = HEXAGONS_BASE_Y + 116.0 + 116.0 + 116.0 + 116.0;
        
        for(int k = 0; k < 3; k++, i++){
            createHexagon(initialX, initialY, hexagonList[i], hexagonSources[index_sources], hexagonsNumbers[index_numbers], i);
            index_numbers++;
            index_sources++;
            initialX += 140;
        }
    }
    
    private void createHexagon(Double startPointX, Double startPointY, Polygon hexagon, int sourceType, int hexagonNumber, int index){
        String sourceName = "";
        switch(sourceType){
            case 0:
                sourceName = "mountain";
                break;
            case 1:
                sourceName = "field";
                break;
            case 2:
                sourceName = "forest";
                break; 
            case 3:
                sourceName = "pasture";
                break;
            case 4:
                sourceName = "hill";
                break;
            case -99:
                sourceName = "desert";
                break;
            default:
                System.out.println("Mistake on resource type!!!");
        }
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
        double numberX = startPointX + 65.0;
        double numberY = startPointY + 48.0;
        if(hexagonNumber >= 10)
            numberX = numberX -5;
        
        
        //hexagonNumberCircles[index].setFill(Color.WHITE);
        hexagonNumberCircles[index] = new Circle(startPointX + 70.0, startPointY + 40.0, 30.0);
        System.out.println("index " + index + " numbercircle is created. " + hexagonNumber);
        
        if(hexagonNumber != 7){
            String numberPath = "/images/number_token_" + hexagonNumber + ".png";

            Image image = new Image(numberPath);
            hexagonNumberCircles[index].setFill(new ImagePattern(image));
            System.out.println("number path:" + numberPath);
        } else {
            System.out.println("index " + hexagonNumber + " is invisible");
            hexagonNumberCircles[index].setStyle("visibility:false");
        }
        
        if(hexagonNumber == 7){
            numberX += 15;
            robberImageView = new ImageView();
            robberImageView.setX(numberX);
            robberImageView.setY(numberY);
            String numberPath = "/images/robber.jpg";

            Image image = new Image(numberPath);
            
            robberImageView.setImage(image);
            robberImageView.setFitHeight(10);
            robberImageView.setFitWidth(10);
            
            rootPane.getChildren().add(robberImageView);
        }
        
        
        
        rootPane.getChildren().add(hexagonNumberCircles[index]);
        rootPane.getChildren().add(hexagon);
        
        // DEBUG
        // System.out.println( "Created hexagon on: " + startPointX + " - " + startPointY);
        
        
        
        String image_path = "/images/" + "hexagon_" + sourceName + "_image" + ".jpg";
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
        if( tradeBankGroup.isVisible() || domesticTradeGroup.isVisible())
        {
            return;
        }
        game_menu_filter.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
        game_menu_filter.toFront();
        game_menu_background.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
        game_menu_background.toFront();
        game_menu_title.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true; top");
        game_menu_title.toFront();
        game_menu_game_music.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
        game_menu_game_music.toFront();
        game_menu_game_sound.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
        game_menu_game_sound.toFront();
        game_menu_back_to_main_menu.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
        game_menu_back_to_main_menu.toFront();
        game_menu_back_to_game.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
        game_menu_back_to_game.toFront();
        game_menu_exit_game.setStyle("-fx-background-color: fff2e2; -fx-background-radius: 0.5em; visibility: true");
        game_menu_exit_game.toFront();
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
        // if one of the trade windows are open, return.
        if( tradeBankGroup.isVisible() || domesticTradeGroup.isVisible())
            return;
        int[] offers = new int[5];
        int[] inReturn = new int[5];
        offers[0] = 4;
        offers[1] = 4;
        mainController.nextPlayer();
        setEventText();
        if( mainController.isGameOver())
        {
            playerName.setFill( mainController.getCurrentPlayerColor());
            gameEndGroup.setVisible(true);
            game_menu_filter.setVisible(true);
            game_menu_filter.toFront();
            gameEndGroup.toFront();
        }
        refreshResources();
        refreshCardNumbers();
    }
    
    public void setEventText()
    {
        String s = mainController.getEventName();
        String message = "";
        if( s.equals("Flood"))
        {
            message = "A flood has occurred. Grain production has stopped.";
        }
        
        else if (s.equals("Earthquake"))
        {
            message = "An earthquake has occurred. All cities are destroyed.";
        }
        
        else if( s.equals("Cybele"))
        {
            message = "Cybele Month has arrived. All resource productions are doubled.";
        }
        
        else if( s.equals("Wolf"))
        {
            message = "Wolves are attacking. Wool production has stopped.";
        }
        
        else
        {
            message = "";
        }
        
        eventText.setText(message);
    }
    public void paintVertex( int index ){
        circleList[index].setFill( mainController.getCurrentPlayerColor());
        
    }
    
    public void paintEdge( int index){
        edgeList[index].setStroke(mainController.getCurrentPlayerColor());
    }
    private void refreshResources()
    {
        // ore = 0, grain = 1, lumber = 2, wool = 3, brick = 4
        if( mainController.isCurrentPlayerBot())
        {
            stone.setText( "X");
            grain.setText( "X");
            lumber.setText( "X");
            wool.setText( "X");
            brick.setText( "X");
            return;
        }
        stone.setText( mainController.getCurrentPlayer().getSourceNo(0) + "");
        grain.setText( mainController.getCurrentPlayer().getSourceNo(1) + "");
        lumber.setText( mainController.getCurrentPlayer().getSourceNo(2) + "");
        wool.setText( mainController.getCurrentPlayer().getSourceNo(3) + "");
        brick.setText( mainController.getCurrentPlayer().getSourceNo(4) + "");
    }
    
    private void setAllCircleNumbersToFront(){
        for(int i = 0; i < 19; i++){
            hexagonNumberCircles[i].toFront();
        }
    }
    
    private void setAllHexagonNumbersToFront(){
        for(int i = 0; i < 19; i++){
            hexagonNumbers[i].toFront();
        }
    }
    private void toggleResourcePickEffects(boolean toggle)
    {
        grainEffect.setVisible(toggle);
        lumberEffect.setVisible(toggle);
        woolEffect.setVisible(toggle);
        oreEffect.setVisible(toggle);
        brickEffect.setVisible(toggle);
    }
    
    public void refreshCardNumbers()
    {
        knightNo.setText( mainController.getPlayerPlayableCardNo("Knight") + 
                " (" + mainController.getPlayerCardNo("Knight") + ")");
        rbNo.setText( mainController.getPlayerPlayableCardNo("Road Building") + 
                " (" + mainController.getPlayerCardNo("Road Building") + ")");
        yearNo.setText( mainController.getPlayerPlayableCardNo("Year of Plenty") + 
                " (" + mainController.getPlayerCardNo("Year of Plenty") + ")");
        monoNo.setText( mainController.getPlayerPlayableCardNo("Monopoly") + 
                " (" + mainController.getPlayerCardNo("Monopoly") + ")");
        insNo.setText( mainController.getPlayerPlayableCardNo("Insurance") + 
                " (" + mainController.getPlayerCardNo("Insurance") + ")");
        asdNo.setText( mainController.getPlayerPlayableCardNo("Anatolian Shepherd Dog") + 
                " (" + mainController.getPlayerCardNo("Anatolian Shepherd Dog") + ")");
    }
    
    private void toggleTwBMenu(boolean toggle)
    {
        if( toggle)
        {
            mainController.startTradeWithBank();
            refreshTwBMenu();
            tradeBankGroup.setVisible(true);
            tradeBankGroup.toFront();
        }
        else
        {
            mainController.cancelTradeWithBank();
            tradeBankGroup.setVisible(false);
        }
    }
    
    private void refreshTwBMenu()
    {
        bankOre.setText( mainController.TwBgetBankSourceNo(0) + "");
        bankGrain.setText( mainController.TwBgetBankSourceNo(1) + "");
        bankLumber.setText( mainController.TwBgetBankSourceNo(2) + "");
        bankWool.setText( mainController.TwBgetBankSourceNo(3) + "");
        bankBrick.setText( mainController.TwBgetBankSourceNo(4) + "");
        
        selfOre.setText( mainController.TwBgetPlayerSourceNo(0) + "");
        selfGrain.setText( mainController.TwBgetPlayerSourceNo(1) + "");
        selfLumber.setText( mainController.TwBgetPlayerSourceNo(2) + "");
        selfWool.setText( mainController.TwBgetPlayerSourceNo(3) + "");
        selfBrick.setText( mainController.TwBgetPlayerSourceNo(4) + "");
        
        actualSourceRights.setText( mainController.getTwBSourceRights() + "");
        usedSourceRights.setText( mainController.getTwBUsedSourceRights() + "");
        
        twbaccept.setDisable( !mainController.isTwBValid());
    }
    private void initializeTwBMenu()
    {
        // ore = 0, grain = 1, lumber = 2, wool = 3, brick = 4
        grainBankIncrBtn.setOnMouseClicked( new TradeWithBankHandler("bank", true, 1));
        grainBankDecrBtn.setOnMouseClicked( new TradeWithBankHandler("bank", false, 1));
        grainSelfIncrBtn.setOnMouseClicked( new TradeWithBankHandler("player", true, 1));
        grainSelfDecrBtn.setOnMouseClicked( new TradeWithBankHandler("player", false, 1));
        
        lumberBankIncrBtn.setOnMouseClicked( new TradeWithBankHandler("bank", true, 2));
        lumberBankDecrBtn.setOnMouseClicked( new TradeWithBankHandler("bank", false, 2));
        lumberSelfIncrBtn.setOnMouseClicked( new TradeWithBankHandler("player", true, 2));
        lumberSelfDecrBtn.setOnMouseClicked( new TradeWithBankHandler("player", false, 2));
        
        oreBankIncrBtn.setOnMouseClicked( new TradeWithBankHandler("bank", true, 0));
        oreBankDecrBtn.setOnMouseClicked( new TradeWithBankHandler("bank", false, 0));
        oreSelfIncrBtn.setOnMouseClicked( new TradeWithBankHandler("player", true, 0));
        oreSelfDecrBtn.setOnMouseClicked( new TradeWithBankHandler("player", false, 0));
        
        woolBankIncrBtn.setOnMouseClicked( new TradeWithBankHandler("bank", true, 3));
        woolBankDecrBtn.setOnMouseClicked( new TradeWithBankHandler("bank", false, 3));
        woolSelfIncrBtn.setOnMouseClicked( new TradeWithBankHandler("player", true, 3));
        woolSelfDecrBtn.setOnMouseClicked( new TradeWithBankHandler("player", false, 3));
        
        brickBankIncrBtn.setOnMouseClicked( new TradeWithBankHandler("bank", true, 4));
        brickBankDecrBtn.setOnMouseClicked( new TradeWithBankHandler("bank", false, 4));
        brickSelfIncrBtn.setOnMouseClicked( new TradeWithBankHandler("player", true, 4));
        brickSelfDecrBtn.setOnMouseClicked( new TradeWithBankHandler("player", false, 4));
        
        twbaccept.setOnMouseClicked( new EventHandler<MouseEvent>(){
            @Override
            public void handle( MouseEvent e)
            {
                boolean result = mainController.finalizeTwB();
                if( result)
                {
                    toggleTwBMenu(false);
                    refreshResources();
                }
            }
        });
        twbcancel.setOnMouseClicked( new EventHandler<MouseEvent>(){
            @Override
            public void handle( MouseEvent e)
            {
                toggleTwBMenu(false);
                refreshResources();
            }
        });
    }
    
    private void initializeDomesticTradeMenu()
    {
        grainP1decr.setOnMouseClicked( new DomesticTradeHandler(1, 1, false));
        grainP1incr.setOnMouseClicked( new DomesticTradeHandler(1, 1, true));
        grainP2decr.setOnMouseClicked( new DomesticTradeHandler(1, 2, false));
        grainP2incr.setOnMouseClicked( new DomesticTradeHandler(1, 2, true));
        
        lumberP1decr.setOnMouseClicked( new DomesticTradeHandler(2, 1, false));
        lumberP1incr.setOnMouseClicked( new DomesticTradeHandler(2, 1, true));
        lumberP2decr.setOnMouseClicked( new DomesticTradeHandler(2, 2, false));
        lumberP2incr.setOnMouseClicked( new DomesticTradeHandler(2, 2, true));
        
        woolP1decr.setOnMouseClicked( new DomesticTradeHandler(3, 1, false));
        woolP1incr.setOnMouseClicked( new DomesticTradeHandler(3, 1, true));
        woolP2decr.setOnMouseClicked( new DomesticTradeHandler(3, 2, false));
        woolP2incr.setOnMouseClicked( new DomesticTradeHandler(3, 2, true));
        
        oreP1decr.setOnMouseClicked( new DomesticTradeHandler(0, 1, false));
        oreP1incr.setOnMouseClicked( new DomesticTradeHandler(0, 1, true));
        oreP2decr.setOnMouseClicked( new DomesticTradeHandler(0, 2, false));
        oreP2incr.setOnMouseClicked( new DomesticTradeHandler(0, 2, true));
        
        brickP1decr.setOnMouseClicked( new DomesticTradeHandler(4, 1, false));
        brickP1incr.setOnMouseClicked( new DomesticTradeHandler(4, 1, true));
        brickP2decr.setOnMouseClicked( new DomesticTradeHandler(4, 2, false));
        brickP2incr.setOnMouseClicked( new DomesticTradeHandler(4, 2, true));
        
        twpcreate.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e)
            {
                boolean result = mainController.addDomesticTrade(domesticOffers, domesticInReturn);
                if( result)
                {
                    for( int i = 0; i < 5; i++)
                    {
                        domesticOffers[i] = 0;
                        domesticInReturn[i] = 0;
                    }
                }
                refreshDomesticTradeMenu();
                refreshResources();
            }
        });
        
        currentTradeRequest = -1;
        twpaccept.setOnMouseClicked( new EventHandler<MouseEvent>(){
            @Override
            public void handle( MouseEvent e)
            {
                mainController.finalizeDomesticTrade(currentTradeRequest);
                refreshDomesticTradeMenu();
                currentTradeRequest = -1;
                treqName.setText("Trade Request Name");
                playerOffers.setText("");
                playerInReturn.setText("");
                refreshResources();
            }
        });
        
        twpclose.setOnMouseClicked( new EventHandler<MouseEvent>(){
            
            @Override
            public void handle( MouseEvent e)
            {
                toggleDomesticTradeMenu(false);
            }
        });
    }
    
    private void toggleDomesticTradeMenu(boolean toggle)
    {
        if( toggle)
        {
            refreshDomesticTradeMenu();
            getTradeRequestsList();
            domesticTradeGroup.setVisible(true);
            domesticTradeGroup.toFront();
        }
        else
        {
            domesticTradeGroup.setVisible(false);
            currentTradeRequest = -1;
            for( int i = 0; i < 5; i++)
            {
                domesticOffers[i] = 0;
                domesticInReturn[i] = 0;
            }
        }
    }
    private void refreshDomesticTradeMenu()
    {
        getTradeRequestsList();
        p1grain.setText(domesticOffers[1] + "");
        p1lumber.setText(domesticOffers[2] + "");
        p1wool.setText(domesticOffers[3] + "");
        p1ore.setText(domesticOffers[0] + "");
        p1brick.setText(domesticOffers[4] + "");
        
        p2grain.setText(domesticInReturn[1] + "");
        p2lumber.setText(domesticInReturn[2] + "");
        p2wool.setText(domesticInReturn[3] + "");
        p2ore.setText(domesticInReturn[0] + "");
        p2brick.setText(domesticInReturn[4] + "");
        currentTradeRequest = -1;
        treqName.setText("Trade Request Name");
        playerOffers.setText("");
        playerInReturn.setText("");
    }
    
    private void refreshKnights()
    {
        p1Knights.setText( "x" + mainController.getKnights(0));
        p2Knights.setText( "x" + mainController.getKnights(1));
        p3Knights.setText( "x" + mainController.getKnights(2));
        p4Knights.setText( "x" + mainController.getKnights(3));
        
    }
    private void getTradeRequestsList()
    {
        tradeRequestList = FXCollections.observableArrayList();
        List<String> offers = new ArrayList<>();
        List<String> inReturns = new ArrayList<>();
        mainController.getDomesticTradesInfo(offers, inReturns);
        if( offers.size() != inReturns.size())
        {
            System.out.println( offers.size() + " " + inReturns.size());
            return;
        }
        for( int i = 0; i < offers.size(); i++)
        {
            System.out.println( "**" + offers.get(i) + "**" + inReturns.get(i));
            TradeRequestText curTRT = new TradeRequestText(i + 1, offers.get(i), inReturns.get(i));
            curTRT.setOnMouseClicked( new TradeRequestHandler(curTRT));
            tradeRequestList.add(curTRT);
        }
        System.out.println( tradeRequestList.size());
        tradeRequests.setItems(tradeRequestList);
    }
    
    public void refreshScores()
    {
        p1score.setText( mainController.getPlayerScore(0) + "");
        p2score.setText( mainController.getPlayerScore(1) + "");
        p3score.setText( mainController.getPlayerScore(2) + "");
        p4score.setText( mainController.getPlayerScore(3) + "");
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
            // if one of the trade windows are open, return.
            if( tradeBankGroup.isVisible() || domesticTradeGroup.isVisible())
                return;
            System.out.println("Attempt to build vertex at index " + index);
            //Circle circle = (Circle) e.getSource();
            //circle.setFill(Color.RED);
            
            if(construct_type == Construction_type.SETTLEMENT){
                System.out.println("Helelelele");
                if(mainController.buildSettlement(index)){
                    System.out.println("Bindik bir alamete gidiyoruz kıyamete amaneeen");
                    Circle circle = (Circle) e.getSource();
                    circle.setFill( mainController.getCurrentPlayerColor());
                    refreshResources();
                    refreshScores();
                }
            }
            else if(construct_type == Construction_type.CITY){
                if(mainController.upgradeCity(index)){
                    Circle circle = (Circle) e.getSource();
                    circle.setStroke(Color.GOLD);
                    circle.setStrokeWidth(3.0);
                    refreshResources();
                    refreshScores();
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
            // if one of the trade windows are open, return.
            if( tradeBankGroup.isVisible() || domesticTradeGroup.isVisible())
                return;
            System.out.println("Attempt to build edge at index " + index);
                // Line l = (Line) e.getSource();
                // l.setStroke(Color.RED);
                
            if( construct_type == Construction_type.ROAD){
                if( mainController.buildRoad(index) ){
                    Line l = (Line) e.getSource();
                    l.setStroke(mainController.getCurrentPlayerColor());
                    refreshResources();
                    refreshScores();
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
            if(tradeBankGroup.isVisible() || domesticTradeGroup.isVisible())
                return;
            if ( !mainController.isBotPlaying()){
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
            // if one of the trade windows are open, return.
            if( tradeBankGroup.isVisible() || domesticTradeGroup.isVisible())
                return;
            mainController.sendRobberToHexagon(index);
            refreshResources();
            refreshScores();
        }
    }
    
    class PlayCardHandler implements EventHandler<MouseEvent>
    {
        String cardType, sourceType, sourceType2;
        PlayCardHandler()
        {
            cardType = "";
            sourceType = "";
            sourceType2 = "";
        }
        
        public void setCardType( String s)
        {
            cardType = s;
        }
        
        public void setSourceType( String s)
        {
            sourceType = s;
        }
        
        public void setSourceType2( String s)
        {
            sourceType2 = s;
        }
        
        public String getCardType()
        {
            return cardType;
        }
        
        public String getSourceType()
        {
            return sourceType;
        }
        
        public String getSourceType2()
        {
            return sourceType2;
        }
        
        @Override
        public void handle(MouseEvent e)
        {
            // if one of the trade windows are open, return.
            if( tradeBankGroup.isVisible() || domesticTradeGroup.isVisible())
                return;
            // if no card type is specified, return.
            if( cardType.equals(""))
                return;
            
            // if player has no playable card of that type, return.
            int count = -1;
            switch( cardType)
            {
                case "Knight":
                    count = knightNo.getText().charAt(0) - '0'; break;
                case "Road Building":
                    count = rbNo.getText().charAt(0) - '0'; break;
                case "Year of Plenty":
                    count = yearNo.getText().charAt(0) - '0'; break;
                case "Monopoly":
                    count = monoNo.getText().charAt(0) - '0'; break;
                case "Insurance":
                    count = insNo.getText().charAt(0) - '0'; break;
                case "Anatolian Shepherd Dog":
                    count = asdNo.getText().charAt(0) - '0'; break;
                default:
                    count = -1; break;
            }
            
            if ( count <= 0)
                return;
            if( !cardType.equals("Monopoly") && !cardType.equals("Year of Plenty"))
            {
                mainController.playCard(cardType, sourceType, sourceType2);
                refreshResources();
                refreshCardNumbers();
                refreshScores();
                refreshKnights();
            }
            else
            {
                rscSet = ResourceSetting.SOURCE1;
                resourceMsg.setText( "Select source #1 by clicking on one of their respective images.");
                toggleResourcePickEffects(true);
            }
        }
    }
    
    class ResourceClickHandler implements EventHandler<MouseEvent>
    {
        String name;
        ResourceClickHandler(String s)
        {
            name = s;
        }
        
        @Override
        public void handle(MouseEvent e)
        {
            if( rscSet == ResourceSetting.SOURCE1)
            {
                System.out.println( "Selecting " + name + " as source 1");
                pch.setSourceType(name);
                if( pch.getCardType().equals("Year of Plenty"))
                {
                    rscSet = ResourceSetting.SOURCE2;
                    resourceMsg.setText( "Select source #2 by clicking on one of their respective images below.");
                }
                else
                {
                    mainController.playCard(pch.getCardType(), pch.getSourceType(), pch.getSourceType2());
                    rscSet = ResourceSetting.NONE;
                    refreshResources();
                    resourceMsg.setText( "");
                    toggleResourcePickEffects(false);
                    refreshCardNumbers();
                    refreshScores();
                }
            }
            else if( rscSet == ResourceSetting.SOURCE2)
            {
                System.out.println( "Selecting " + name + " as source 2");
                pch.setSourceType2(name);
                mainController.playCard(pch.getCardType(), pch.getSourceType(), pch.getSourceType2());
                rscSet = ResourceSetting.NONE;
                refreshResources();
                refreshCardNumbers();
                refreshScores();
                resourceMsg.setText( "");
                toggleResourcePickEffects(false);
            }
        }
    }
    public int botRollsDice(){
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
            return diceNums[0] + diceNums[1];
    }
    
    class TradeWithBankHandler implements EventHandler<MouseEvent>
    {
        String type;
        int source;
        boolean op;
        TradeWithBankHandler( String theType, boolean arithmeticOp, int theSource)
        {
            type = theType;
            source = theSource;
            op = arithmeticOp;
        }
        
        @Override
        public void handle( MouseEvent e)
        {
            if( type.equals("bank"))
            {
                if( op)
                    mainController.addSourceToBank(source, 1);
                else
                    mainController.subSourceFromBank(source, 1);
            }
            else if( type.equals("player"))
            {
                if(op)
                    mainController.addSourceToSelf(source, 1);
                else
                    mainController.subSourceFromSelf(source, 1);
            }
            refreshTwBMenu();
        }
    }
    
    class DomesticTradeHandler implements EventHandler<MouseEvent>
    {
        int sourceType;
        int player;
        boolean op;
        DomesticTradeHandler(int theSourceType, int whichPlayer, boolean theOp)
        {
            sourceType = theSourceType;
            player = whichPlayer;
            op = theOp;
        }
        
        @Override
        public void handle( MouseEvent e)
        {
            if( player == 1)
            {
                if( op && domesticOffers[sourceType] <= mainController.getCurrentPlayer().getSourceNo(sourceType))
                {
                    domesticOffers[sourceType]++;
                }
                if( !op && domesticOffers[sourceType] > 0)
                {
                    domesticOffers[sourceType]--;
                }
            }
            else if( player == 2)
            {
                if( op )
                {
                    domesticInReturn[sourceType]++;
                }
                else if ( !op && domesticInReturn[sourceType] > 0)
                {
                    domesticInReturn[sourceType]--;
                }
            }
            refreshDomesticTradeMenu();
        }
    }
    
    class TradeRequestHandler implements EventHandler<MouseEvent>
    {
        
        TradeRequestText trt;
        TradeRequestHandler( TradeRequestText tReq)
        {
            trt = tReq;
        }
        
        @Override
        public void handle( MouseEvent e)
        {
            treqName.setText( trt.getText());
            playerOffers.setText( trt.getOffer());
            System.out.println( trt.getOffer());            
            playerInReturn.setText( trt.getInReturn());
            currentTradeRequest = trt.getNumber() - 1;
            twpaccept.setDisable( !mainController.isDomesticTradeValid( trt.getNumber() - 1));
        }
    }
    
}
