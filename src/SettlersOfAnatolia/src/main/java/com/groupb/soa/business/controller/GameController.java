/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.controller;
import com.groupb.soa.business.models.GameModel;
import com.groupb.soa.business.models.Player;
import com.groupb.soa.business.models.BotPlayer;
import com.groupb.soa.business.models.PlayerList;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Irmak
 */
public class GameController {
    
    // Properties
    
    
    
    private GameModel gameModel; 
    Timer t;
    private BotPlayer currentPlayer;
    
    private Stage stage;
    
    TimerTask task = new TimerTask()
    {
        public void run()
        {
             System.out.println ("TIMER ÇALIŞTI");
            
                if( gameModel.isCurrentPlayerBot()){
                    currentPlayer = (BotPlayer) ( gameModel.getCurrentPlayer() );
                    currentPlayer.playTurn( gameModel );
                    
                    
                }
                
                
        }

    };
    // Properties
    
    
   
    
    // Constructors
    public GameController(Stage stage, Application mainApplication) throws IOException
    {
        initiateMenu(stage, mainApplication);
         t = new Timer();
         t.scheduleAtFixedRate(task, 5000 , 5000 );
    }
    
    // Methods
    
    private void initiateMenu(Stage stage, Application mainApplication) throws IOException{
        
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
    
    
    
    public void initateGame(int mode){ // 0 = multiplayer, 1 = bot 
        // here we set default colors, later it will be regulated in a way that the GameController takes colors from the GameOption Menu
        
        Color[] playerColors = new Color[4];
        playerColors[0] = Color.RED;
        playerColors[1] = Color.BLUE;
        playerColors[2] = Color.GREENYELLOW;
        playerColors[3] = Color.PURPLE;
        if ( mode == 0 ) //multiplayer
            gameModel = new GameModel(playerColors, false);
        else if ( mode == 1) //bot
            gameModel = new GameModel(playerColors, true);
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
    
    public boolean isBotPlaying(){
        return gameModel.isCurrentPlayerBot();
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
    
    public boolean addDomesticTrade( int[] offers, int[] inReturn)
    {
        return gameModel.addDomesticTrade(offers, inReturn);
    }
    
    public int getDomesticTradeNo()
    {
        return gameModel.getDomesticTradeNo();
    }
    
    public void getDomesticTradesInfo(List<String> offers, List<String> inReturns)
    {
        gameModel.getDomesticTradesInfo(offers, inReturns);
    }
    
    public boolean finalizeDomesticTrade( int index)
    {
        return gameModel.finalizeDomesticTrade(index);
    }
    
    public boolean isDomesticTradeValid(int index)
    {
        return gameModel.isDomesticTradeValid(index);
    }
    
    public int getPlayerScore( int index)
    {
        return gameModel.getPlayerScore(index);
    }
    
    public Player getWinner()
    {
        return gameModel.getWinner();
    }
    
    public boolean isGameOver()
    {
        return gameModel.isGameOver();
    }
    
    public String getEventName()
    {
        return gameModel.getEventName();
    }
    
    public int getKnights( int index)
    {
        return gameModel.getKnights( index);
    }
    
    public boolean isCurrentPlayerBot()
    {
        return gameModel.isCurrentPlayerBot();
    }
    
    public int getVertexLevel(int index)
    {
        return gameModel.getVertexLevel(index);
    }
    
    public Color getVertexColor(int index)
    {
        return gameModel.getVertexColor(index);
    }
}
