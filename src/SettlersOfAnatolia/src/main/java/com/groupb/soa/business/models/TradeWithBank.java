/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.models;

/**
 *
 * @author İrem Kırmacı, Irmak Demir
 */
public class TradeWithBank {
        Player player;
        Bank bank;
        int[] playerSources;
        int[] bankSources;
        int sourceRights;
        public TradeWithBank( Player pl, Bank b)
        {
            player = pl;
            bank = b;
            playerSources = new int[5];
            bankSources = new int[5];
            sourceRights = 0;
        }
        
        public boolean playerAddSource( int sourceNo, int amount)
        {
            playerSources[sourceNo] += amount;
            if( player.getSourceNo(sourceNo) < playerSources[sourceNo])
            {
                playerSources[sourceNo] -= amount;
                return false;
            }
            return true;
        }
        
        public boolean playerSubSource( int sourceNo, int amount)
        {
            playerSources[sourceNo] -= amount;
            if( playerSources[sourceNo] < 0)
            {
                playerSources[sourceNo] += amount;
                return false;
            }
            return true;
        }
        
        public boolean bankAddSource( int sourceNo, int amount)
        {
            bankSources[sourceNo] += amount;
            if( bank.getSources()[sourceNo] < bankSources[sourceNo])
            {
                bankSources[sourceNo] -= amount;
                return false;
            }
            return true;
        }
        
        public boolean bankSubSource( int sourceNo, int amount)
        {
            bankSources[sourceNo] -= amount;
            if( bankSources[sourceNo] < 0)
            {
                bankSources[sourceNo] += amount;
                return false;
            }
            return true;
        }
        public int calculateSourceRights()
        {
            // calculate
            int temp = 0;
            for( int i = 0; i < playerSources.length; i++)
            {
                temp += playerSources[i] / 4;
            }
            sourceRights = temp;
            return temp;
        }
        
        public int calculateUsedSourceRights()
        {
            int temp = 0;
            for( int i = 0; i < playerSources.length; i++)
            {
                temp += bankSources[i];
            }
            return temp;
        }
        
        public boolean isAValidTrade()
        {
            return calculateSourceRights() == calculateUsedSourceRights();
        }
        
        public boolean finalizeTrade()
        {
            if( !isAValidTrade())
                return false;
            
            boolean playerHasResources = true;
            boolean bankHasResources = true;
            for( int i = 0; i < playerSources.length; i++)
            {
                playerHasResources = playerHasResources & (playerSources[i] <= player.getSourceNo(i));
                bankHasResources = bankHasResources & (bankSources[i] <= bank.getSources()[i]);
            }
            
            if( bankHasResources && playerHasResources)
            {
                boolean result = true;
                for( int i = 0; i < 5; i++)
                {
                    result &= bank.subSource(i, bankSources[i]);
                    player.addSource(i, bankSources[i]);
                    result &= player.subSource(i, playerSources[i]);
                    result &= bank.subSource(i, playerSources[i]);
                }
                return result;
            }
            else
            {
                return false;
            }
        }
        
        public int getBankSourceNo( int sourceNo)
        {
            return bankSources[sourceNo];
        }
        
        public int getPlayerSourceNo( int sourceNo)
        {
            return playerSources[sourceNo];
        }
    }
