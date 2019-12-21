/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.controller;
import com.groupb.soa.business.models.GameModel;
import com.groupb.soa.business.models.Player;
import com.groupb.soa.business.models.BotPlayer;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Irmak
 */
public class GameController {
    

    TimerTask task = new TimerTask()
    {
        public void run()
        {
            
            if ( gameModel.isCurrentPlayerBot() )  { 
                    currentPlayer = (BotPlayer) ( gameModel.getCurrentPlayer() );
                    currentPlayer.playTurn( gameModel );
                    
            }
                
                
        }

    };
    // Properties
    
    
    
    private GameModel gameModel; 
    Timer t;
    private BotPlayer currentPlayer;
    
    private Stage stage;
    
    // Constructors
    public GameController(Stage stage, Application mainApplication) throws IOException
    {
        initiateMenu(stage, mainApplication);
         t = new Timer();
         t.schedule(task, 5000);
    }
    
    // Methods
    
    private void initiateMenu(Stage stage, Application mainApplication) throws IOException{
        //String path = "C:\\Users\\User\\Downloads\\sound.mpeg";  
          
        //Instantiating Media class  
        //Media media = new Media(new File(path).toURI().toString());  
          
        //Instantiating MediaPlayer class   
        //MediaPlayer mediaPlayer = new MediaPlayer(media);  
          
        //by setting this property to true, the audio will be played   
        //mediaPlayer.setAutoPlay(true);  
        this.stage = stage;
        
        Parent root = FXMLLoader.load(mainApplication.getClass().getResource("/fxml/MainMenuScene.fxml"));
        
        Scene scene = new Scene(root);
        
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Settlers of Anatolia");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
    
    public GameController getInstance(){
        return this;
    }
    
    
    
    public void initateGame(){
        // here we set default colors, later it will be regulated in a way that the GameController takes colors from the GameOption Menu
        
        Color[] playerColors = new Color[4];
        playerColors[0] = Color.RED;
        playerColors[1] = Color.BLUE;
        playerColors[2] = Color.GREENYELLOW;
        playerColors[3] = Color.PURPLE;
        gameModel = new GameModel(playerColors);
        
    }
    
   
    public void startGame()
    {
        //GameModel newGame = new GameModel (Color[] playerColors); 
        //updateView(); 
    }
    
    public boolean buildRoad( int index)
    {
        if ( ! gameModel.isCurrentPlayerBot() )
            return gameModel.buildRoad(index);
        return false;
    }
    
    public boolean upgradeCity( int index)
    {
        if ( ! gameModel.isCurrentPlayerBot() )
            return gameModel.buildCity(index);
        return false;
    }
    public boolean buildSettlement(int index)
    {
        if ( ( ! gameModel.isCurrentPlayerBot() )  ){
            //System.out.println("Game controller a ulaştık");
             return gameModel.buildSettlement(index);  
        }
        return false;
    }
    public void render()
    {
        //gameModel.render();
    }
    public int[] rollDice()
    {
        int[] diceValue = gameModel.rollDice();
        gameModel.produceResources();
        return diceValue;
    }

    public Color getCurrentPlayerColor() {
        return gameModel.getCurrentPlayerColor();
    }
    
    public Player getCurrentPlayer()
    {
        return gameModel.getCurrentPlayer();
    }
    
    public void nextPlayer(){
        if ( ( ! gameModel.isCurrentPlayerBot() )  ){
            gameModel.moveNextPlayer();
        }
    }
    
    public boolean sendRobberToHexagon( int index)
    {
        return gameModel.sendRobberToHexagon(index);
    }
    
    public boolean playCard( String cardName, String sourceType, String sourceType2)
    {
        return gameModel.playCard( cardName, sourceType, sourceType2);
    }
    
    public int getPlayerCardNo(String cardName)
    {
        return gameModel.getPlayerCardNo(cardName);
    }
    
    public int getPlayerPlayableCardNo(String cardName)
    {
        return gameModel.getPlayerPlayableCardNo(cardName);
    }
    
    public int[] getHexagonSources(){
        return gameModel.getSources();
    }
    
    public int[] getNumberOfHexagons(){
        return gameModel.getNumberOfHexagons();
    }
    
    public boolean buyCard()
    {
        return gameModel.buyCard();
    }
    
}
