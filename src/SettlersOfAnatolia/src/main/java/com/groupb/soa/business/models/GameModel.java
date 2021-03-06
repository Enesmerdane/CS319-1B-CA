/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.models;

/**
 *
 * @author Göksu, İrem
 */
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

public class GameModel {
    private GameTile tile;
    private PlayerList playerList;
    private Bank bank;
    private Dice dice;
    private Dice dice2;
    private int queue;
    private int turn;
    private int freeRoads;
    private int eventStart;
    private boolean firstTurn;
    private boolean secondTurn;
    
    private boolean firstTurnSettBuilt;
    private boolean firstTurnRoadBuilt;
    private boolean diceRolled;
    private boolean isOver;
    private int robberMoves;
    private int largestArmy;
    private int longestRoad;
    private Player largestArmyHolder;
    private Player longestRoadHolder;
    private Player winner;
    private TradeWithBank currentTwB;
    private List<DomesticTrade> domesticTrades;
    private boolean thirdTurn;
    private boolean isFlood; //new
    private boolean isWolfAttacked; //new
    private boolean isCybeleMonth; //new
    private boolean isEarthquake;
    private EventManager eventMgr;
    private Event event;
    
    private boolean forthTurn;
    private int []playerCityNo;
    
    private Color[] playerColors;
    
    public GameModel(Color[] playerColors, boolean isBot) {
        this.playerColors = playerColors;
        
        playerList = new PlayerList( playerColors, isBot);
        
            
        tile = new GameTile();
        bank = new Bank();
        dice = new Dice(0.0,0.0);
        dice2 = new Dice(0.0,0.0);
        turn = 0;
        queue = 0;
        eventStart = 0;
        freeRoads = 0;
        firstTurn = true;
        secondTurn = false;
        thirdTurn = false;
        forthTurn = false;
        firstTurnSettBuilt = false;
        firstTurnRoadBuilt = false;
        diceRolled = false;
        isOver = false;
        robberMoves = 0;
        largestArmy = 3;
        longestRoad = 5;
        largestArmyHolder = null;
        longestRoadHolder = null;
        winner = null;
        currentTwB = null;
        domesticTrades = new ArrayList<>();
        isFlood = false;
        isWolfAttacked = false;
        isCybeleMonth = false;
        isEarthquake = false;
        eventMgr = new EventManager(this);
        event = null;
        
        
        
    }
    
    
    // player rolls the dice and sources are distributed
    public boolean produceResources(){
            if( !diceRolled)
            {
                if( dice.getValue() + dice2.getValue() == 7)
                {
                    diceRolled = true;
                    playerList.AllRemoveHalf();
                    robberMoves++;
                    return true;
                }
                diceRolled = true;
                return tile.produceResources(dice.getValue() + dice2.getValue() , playerList, isFlood, isWolfAttacked, isCybeleMonth);
            }
            return false;
    }
    
    public boolean sendRobberToHexagon( int index)
    {
       if( firstTurn || secondTurn || !diceRolled)
           return false;
       if( robberMoves > 0)
       {
            boolean result = tile.sendRobberToHexagon(index, playerList);
            if(result)
                robberMoves--;
            return result;
       }
       return false;
    }
    
    //gets playerList
    public PlayerList getPlayerList(){
        return playerList;
    }

    public boolean buildRoad(int index ){
        // if it is the first turns, and the player did not build a settlement yet, or built both a settlement and a road, return false.
        if( (firstTurn || secondTurn))
        {
            if( !firstTurnSettBuilt)
                return false;
            else if( firstTurnRoadBuilt)
                return false;
        }
        
        if( !firstTurn && !secondTurn && !diceRolled)
            return false;
        boolean result = tile.buildRoad(index, playerList.getCurrentPlayer().getColor(),  playerList,  firstTurn || secondTurn || freeRoads > 0);
        if(result){
            System.out.println("GameModel: Building Road by " + playerList.getCurrentPlayer().getColor().toString() + " on the Edge " + index);
            // if it is the set-up turns, notify that the player built their free road.
            completeLongestRoadCheck();
            if( firstTurn || secondTurn)
                firstTurnRoadBuilt = true;
            // if it is not the set-up turns and the player built a free road, subtract 1 free road privilege.
            else if( !firstTurn && !secondTurn && freeRoads > 0)
                freeRoads--;
        } else {
            System.out.println("GameModel: Building Road by " + playerList.getCurrentPlayer().getColor().toString() + " on the Edge " + index + "FAILED for some reasons");
        }
        return result;
    }
    public boolean buildSettlement(int index){
        // if it is the first turns, and the player already built a settlement, return false.
        if( (firstTurn || secondTurn) && firstTurnSettBuilt)
        {
            return false;
        }
        if( !firstTurn && !secondTurn && !diceRolled)
            return false;
        boolean result = tile.buildVertex( index, playerList.getCurrentPlayer().getColor(),  playerList, firstTurn, secondTurn );
        if(result){
           System.out.println("GameModel: Building Settlement by " + playerList.getCurrentPlayer().getColor().toString() + " on the Vertex " + index);
           completeLongestRoadCheck();
           if( firstTurn || secondTurn)
               firstTurnSettBuilt = true;
        } else {
            System.out.println("GameModel: Building Settlement by "+ playerList.getCurrentPlayer().getColor().toString() + " on the Vertex " + index + " FAILED for some reasons");
        }
        return result;

    }
    public boolean buildCity(int index){
        // if it is the first turns, return false.
        if (firstTurn ||secondTurn)
            return false;
        if( !firstTurn && !secondTurn && !diceRolled)
            return false;
        boolean result =  tile.upgradeVertex(  index,playerList.getCurrentPlayer().getColor(), playerList);
        completeLongestRoadCheck();
        if(result){
            System.out.println("GameModel: Building City by "+ playerList.getCurrentPlayer().getColor().toString() + " on the Vertex " + index);
        } else {
            System.out.println("GameModel: Building City by "+ playerList.getCurrentPlayer().getColor().toString() + " on the Vertex " + index + " FAILED for some reasons");
        }

       return result;
    }

    public Player getCurrentPlayer()
    {
        return playerList.getCurrentPlayer();
    }
    public Color getCurrentPlayerColor(){
        return playerList.getCurrentPlayer().getColor();
    }
    
    public Bank getBank(){
        return bank;
    }

    public void moveNextPlayer(){
        
        // if it is the first turns and the player hasnt built a settlement and a road yet,
        // stop.
        if( ( firstTurn || secondTurn) && !(firstTurnSettBuilt && firstTurnRoadBuilt) )
            return;
        
        // if player has the right to build free roads, stop.
        if( freeRoads > 0)
            return;
        
        // if player has the right to move the robber, stop.
        if( robberMoves > 0)
            return;
        
        if( !firstTurn && !secondTurn && !diceRolled)
            return;
        
        if( playerList.getCurrentPlayer().getScore() >= 10)
        {
            isOver = true;
            winner = playerList.getCurrentPlayer();
            return;
        }
        // playerList.next()'s stay parameter is set to 'true'
        // when queue == 3 or 7. This is because in the first 2 rounds,
        // one player gets to play twice at the end.
        if( eventStart == 4)
        {
            isFlood = false;
            isWolfAttacked = false;
            isCybeleMonth = false;
            isEarthquake = false;
        }
        if( queue % 4 == 0)
        {
            event = eventMgr.checkEvent();
            eventMgr.handleEvent(event);
            
            eventStart = queue;
        }
        
        
        playerList.next( secondTurn, queue == 3 || queue == 7);
        queue++;
        if( queue == 8)
        {
            tile.firstProduce(playerList);
        }
        turn = queue / 4;
        firstTurn = (turn == 0);
        secondTurn = (turn == 1);
        thirdTurn = ( turn == 2);
        forthTurn = ( turn == 3);
        firstTurnSettBuilt = false;
        eventStart++;
        firstTurnRoadBuilt = false;
        System.out.println("Game Turn is increased " + turn); 
        
        currentTwB = null;
        diceRolled = false;
        List<DomesticTrade> tradesToDelete = new ArrayList<>();
        for( int i = 0; i < domesticTrades.size(); i++)
        {
            DomesticTrade dt = domesticTrades.get(i);
            dt.addLifetime();
            if( dt.getLifetime() >= 8)
            {
                dt.cancelTrade();
                tradesToDelete.add( dt);
            }
        }
        
        for( DomesticTrade dt: tradesToDelete)
        {
            domesticTrades.remove(dt);
        }
        
        System.out.println("Game Turn is increased " + turn);
    }
    
    public int[] rollDice()
    {
        int result[] = new int[2];
        if( firstTurn || secondTurn || diceRolled)
        {
            result[0] = result[1] = 0;
            return result;
        }
        // roll the dice
        int d1 = dice.rollDice();
        int d2 = dice2.rollDice();
        result[0] = d1;
        result[1] = d2;
        return result;
    }
    
    public boolean playCard( String cardName, String sourceName, String sourceName2)
    {
        // first, we check if the player has the card.
        if( playerList.getCurrentPlayer().getCardNo(cardName) <= 0)
        {
            return false;
        }
        
        System.out.println( "asdasd" + cardName);
        // if the player has the card...
        // play it
        DevCard curCard = playerList.getCurrentPlayer().getCard(cardName);
        // if there is no such card, return false.
        if( curCard == null)
            return false;
        
        boolean isPlayed = false;
        if( cardName.equals("Knight"))
        {
            System.out.println( "Knight checkpoint");
            Knight knight = (Knight) curCard;
            isPlayed = knight.play(this);
            if( isPlayed)
            {
                int curArmy = playerList.getCurrentPlayer().getKnights();
                if( curArmy > largestArmy)
                {
                    if( largestArmyHolder != null)
                    {
                        largestArmyHolder.setLargestArmy(false);
                    }
                        largestArmyHolder = playerList.getCurrentPlayer();
                        playerList.getCurrentPlayer().setLargestArmy(true);
                    
                }
            }
        }
        
        else if( cardName.equals("Road Building"))
        {
            System.out.println( "Road Building checkpoint");
            RoadBuilding rb = (RoadBuilding) curCard;
            // To do
            isPlayed = rb.play(this);
        }
        
        else if( cardName.equals("Monopoly"))
        {
            System.out.println( "Monopoly checkpoint");
            Monopoly mono = (Monopoly) curCard;
            mono.setSelectedSource(sourceName);
            isPlayed = mono.play( this);
        }
        
        else if( cardName.equals("Year of Plenty"))
        {
            YearOfPlenty yop = (YearOfPlenty) curCard;
            yop.setSelectedSource1(sourceName);
            yop.setSelectedSource2(sourceName2);
            isPlayed = yop.play( this);
        }
        
        else if( cardName.equals("Anatolian Shepherd Dog"))
        {
            System.out.println( "asdcheck");
            AnatolianShepherdDog asd = (AnatolianShepherdDog) curCard;
            isPlayed = asd.play(this);
        }
        
        else if( cardName.equals("Insurance"))
        {
            Insurance ins = (Insurance) curCard;
            isPlayed = ins.play(this);
        }

        if( isPlayed)
        {
            playerList.getCurrentPlayer().removeCard(curCard);
            return true;
        }
        return false;
    }
    
    protected void addFreeRoads( int i)
    {
        System.out.println( "i = " + i);
        System.out.println( playerList.getCurrentPlayer().getRemRoads() + " xd");
        freeRoads += (int) Math.min(i, playerList.getCurrentPlayer().getRemRoads());
    }
    
    protected void addRobberMove()
    {
        robberMoves += 1;
    }
    
    public int getPlayerCardNo( String cardName)
    {
        return playerList.getCurrentPlayer().getCardNo( cardName);
    }
    
    public int getPlayerPlayableCardNo( String cardName)
    {
        return playerList.getCurrentPlayer().getPlayableCardNo( cardName);
    }
    
    public int[] getSources(){
        return tile.getResources();
    }
    
    public int[] getNumberOfHexagons(){
        return tile.getNumbersofHexagons();
    }
    
    public boolean buyCard()
    {
        if( firstTurn || secondTurn || !diceRolled)
            return false;
        return playerList.getCurrentPlayer().buyDevCard(bank);
    }
    public boolean isCurrentPlayerBot(){
        return getCurrentPlayer() instanceof BotPlayer;
    }
    
    public boolean getFirstTurn(){
    return firstTurn;
    }
    
    public boolean getSecondTurn(){
    return secondTurn;
    }
    
     public boolean getThirdTurn(){
        return thirdTurn;
    }
     
   
    
    public boolean startTradeWithBank()
    {
        if( !diceRolled || firstTurn || secondTurn)
            return false;
        if ( currentTwB != null)
            return false;
        currentTwB = new TradeWithBank( playerList.getCurrentPlayer(), bank);
        return true;
    }
    
    public boolean cancelTradeWithBank()
    {
        if( currentTwB == null)
            return false;
        currentTwB = null;
        return true;
    }
    
    public boolean addSourceToSelf( int sourceNo, int amount)
    {
        return currentTwB.playerAddSource(sourceNo, amount);
    }
    
    public boolean subSourceFromSelf( int sourceNo, int amount)
    {
        return currentTwB.playerSubSource(sourceNo, amount);
    }
    
    public boolean addSourceToBank( int sourceNo, int amount)
    {
        return currentTwB.bankAddSource(sourceNo, amount);
    }
    
    public boolean subSourceFromBank( int sourceNo, int amount)
    {
        return currentTwB.bankSubSource( sourceNo, amount);
    }
    
    public boolean isTwBValid()
    {
        return currentTwB.isAValidTrade();
    }
    
    public int TwBgetBankSourceNo( int sourceNo)
    {
        return currentTwB.getBankSourceNo(sourceNo);
    }
    
    public int TwBgetPlayerSourceNo( int sourceNo)
    {
        return currentTwB.getPlayerSourceNo( sourceNo);
    }
    
    public int getTwBSourceRights()
    {
        return currentTwB.calculateSourceRights();
    }
    
    public int getTwBUsedSourceRights()
    {
        return currentTwB.calculateUsedSourceRights();
    }
    
    public boolean finalizeTwB()
    {
        boolean result = currentTwB.finalizeTrade();
        if( result)
            currentTwB = null;
        return result;
    }
    
    public boolean getDiceRolled()
    {
        return diceRolled;
    }
    public boolean addDomesticTrade(int[] offers, int[] inReturn)
    {
        boolean canMakeTrade = true;
        boolean leftAllZeroes = true;
        boolean rightAllZeroes = true;
        for( int i = 0; i < offers.length; i++)
        {
            canMakeTrade &= (playerList.getCurrentPlayer().getSourceNo(i) >= offers[i]);
            leftAllZeroes &= offers[i] == 0;
            rightAllZeroes &= inReturn[i] == 0;
        }
        if( !canMakeTrade || leftAllZeroes || rightAllZeroes)
            return false;
        
        domesticTrades.add( new DomesticTrade(playerList.getCurrentPlayer(), offers, inReturn));
        return true;
    }
    
    public int getDomesticTradeNo()
    {
        return domesticTrades.size();
    }
    
    public void getDomesticTradesInfo(List<String> offers, List<String> inReturn)
    {
        for( DomesticTrade dt : domesticTrades)
        {
            if( !dt.isCreator(playerList.getCurrentPlayer()))
            {
                StringBuffer curOffer, curInReturn;
                curOffer = new StringBuffer();
                curInReturn = new StringBuffer();
                dt.getTradeInfo(curOffer, curInReturn);
                offers.add(curOffer.toString());
                inReturn.add(curInReturn.toString());
            }
        }
    }
    
    public boolean finalizeDomesticTrade( int index)
    {
        boolean result = domesticTrades.get(index).finalizeTrade(playerList.getCurrentPlayer());
        if(result)
            domesticTrades.remove(index);
        return result;
    }
    
    public boolean isDomesticTradeValid(int index)
    {
        return domesticTrades.get(index).isTradeValid(playerList.getCurrentPlayer());
    }
    public int[] destroyAllCities()
    {
        return tile.destroyAllCities(playerList);
    }

    public int getPlayerScore( int index)
    {
        return playerList.getPlayer( index).getScore();
    }
    
    private void changeLongestRoad( Player p, int length)
    {
        p.setLongestRoad( length);
    }
    
    public void completeLongestRoadCheck()
    {
        tile.completeLongestRoadCheck(playerList);
        int max = Integer.MIN_VALUE;
        Player maxHolder = null;
        for( int i = 0; i < 4; i++)
        {
            if( max < playerList.getPlayer(i).getLongestRoad())
            {
                max = playerList.getPlayer(i).getLongestRoad();
                maxHolder = playerList.getPlayer(i);
            }
        }
        
        if( longestRoadHolder == null)
        {
            // this means that there hasn't been any longest road owner. So, if max => 5, we set max holder as longest road holder.
            if( max < 5)
                return;
            
            longestRoad = max;
            longestRoadHolder = maxHolder;
            longestRoadHolder.setHasLongestRoad(true);
        }
        
        else
        {
            if( max < 5) // this means that no one holds the longest road. take the achievement from all.
            {
                for( int i = 0; i < 4; i++)
                {
                    playerList.getPlayer(i).setHasLongestRoad(false);
                }
                
                longestRoad = 5;
                longestRoadHolder = null;
            }
            else 
            {
                longestRoadHolder.setHasLongestRoad( false);
                longestRoad = max;
                longestRoadHolder = maxHolder;
                longestRoadHolder.setHasLongestRoad( true);
            }
        }
    }
    
    public Player getWinner()
    {
        return winner;
    }
    
    public boolean isGameOver()
    {
        return isOver;
    }
    public boolean getWolfAttacked(){ //new
        return isWolfAttacked;
   }
   public void setWolfAttacked(boolean wolfAttacked){ //new
        isWolfAttacked= wolfAttacked;
   }

    public boolean getFlood(){ //new
        return isFlood;
    }
    public void setFlood(boolean flood){ //new
        isFlood= flood;
    }

    public boolean getCybeleMonth(){ //new
        return isCybeleMonth;
    }
    public void setCybeleMonth(boolean cybele){ //new
        isCybeleMonth= cybele;
    }
    //new added
    public void setEarthquake(boolean earthquake){
        isEarthquake = earthquake;
    }
    public boolean getEarthquake(){
        return  isEarthquake;
    }
    public String getEventName()
    {
        if( event instanceof Flood)
            return "Flood";
        else if (event instanceof EarthQuake)
            return "Earthquake";
        else if( event instanceof CybeleMonth)
            return "Cybele";
        else if( event instanceof WolfAttack)
            return "Wolf";
        else
            return "None";
    }
    
    public int getKnights(int index)
    {
        return playerList.getPlayer(index).getKnights();
    }
    
    public int getVertexLevel(int index)
    {
        return tile.getVertexLevel(index);
    }
    
    public Color getVertexColor( int index)
    {
        return tile.getVertexColor(index);
    }
    public Color[] getPlayerColors(){
        return playerColors;
    }
}




