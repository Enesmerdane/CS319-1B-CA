/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.controller;
import com.groupb.soa.business.models.GameModel;
import com.groupb.soa.business.models.Player;
import com.groupb.soa.business.models.PlayerList;
import java.io.IOException;
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
    // Properties
    
    private GameModel gameModel; 
    
    private Stage stage;
    
    // Constructors
    public GameController(Stage stage, Application mainApplication) throws IOException
    {
        initiateMenu(stage, mainApplication);
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
        return gameModel.buildRoad(index);
    }
    
    public boolean upgradeCity( int index)
    {
        return gameModel.buildCity(index);
    }
    public boolean buildSettlement(int index)
    {
        System.out.println("Game controller a ulaştık");
        return gameModel.buildSettlement(index);
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
        gameModel.moveNextPlayer();
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
    
    public boolean startTradeWithBank()
    {
        return gameModel.startTradeWithBank();
    }
    
    public boolean cancelTradeWithBank()
    {
        return gameModel.cancelTradeWithBank();
    }
    
    public boolean addSourceToSelf( int sourceNo, int amount)
    {
        return gameModel.addSourceToSelf(sourceNo, amount);
    }
    
    public boolean subSourceFromSelf( int sourceNo, int amount)
    {
        return gameModel.subSourceFromSelf(sourceNo, amount);
    }
    
    public boolean addSourceToBank( int sourceNo, int amount)
    {
        return gameModel.addSourceToBank(sourceNo, amount);
    }
    
    public boolean subSourceFromBank( int sourceNo, int amount)
    {
        return gameModel.subSourceFromBank( sourceNo, amount);
    }
    
    public boolean isTwBValid()
    {
        return gameModel.isTwBValid();
    }
    
    public int TwBgetBankSourceNo( int sourceNo)
    {
        return gameModel.TwBgetBankSourceNo(sourceNo);
    }
    
    public int TwBgetPlayerSourceNo( int sourceNo)
    {
        return gameModel.TwBgetPlayerSourceNo( sourceNo);
    }
    
    public int getTwBSourceRights()
    {
        return gameModel.getTwBSourceRights();
    }
    
    public int getTwBUsedSourceRights()
    {
        return gameModel.getTwBUsedSourceRights();
    }
    
    public boolean finalizeTwB()
    {
        return gameModel.finalizeTwB();
    }
}
