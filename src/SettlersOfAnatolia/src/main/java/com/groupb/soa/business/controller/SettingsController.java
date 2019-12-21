/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupb.soa.business.controller;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Irmak
 */
public class SettingsController {
    private boolean isGameMusicOn;
    private boolean isGameSoundOn;
    private String background; 
    public SettingsController()
    {
        isGameMusicOn = true;
        isGameSoundOn = true; 
        background = "wooden"; 
        
    }
    
    public boolean getIsGameMusicOn()
    {
        return isGameMusicOn; 
    }
    
    public boolean getIsMusicSoundOn()
    {
        return isGameSoundOn; 
    }
    public String getBackground()
    {
        return background; 
    }
    
    public void setIsGameMusicOn(boolean gameMusicState )
    {
        isGameMusicOn = gameMusicState;
        if ( isGameMusicOn == true)
        {
            String path = "C:\\Users\\User\\Downloads\\sound.mpeg";  
        //Instantiating Media class  
        Media media = new Media(new File(path).toURI().toString());  
          
        //Instantiating MediaPlayer class   
        MediaPlayer mediaPlayer = new MediaPlayer(media);  
          
        //by setting this property to true, the audio will be played   
        mediaPlayer.setAutoPlay(true);  
        }
        else
        {
            String path = "C:\\Users\\User\\Downloads\\sound.mpeg";  
          
        //Instantiating Media class  
        Media media = new Media(new File(path).toURI().toString());  
          
        //Instantiating MediaPlayer class   
        MediaPlayer mediaPlayer = new MediaPlayer(media);  
          mediaPlayer.setVolume(0);
        //by setting this property to true, the audio will be played   
        mediaPlayer.setAutoPlay(true);  
        }
    }
    
    public void setIsGameSoundOn( boolean gameSoundState)
    {
         isGameSoundOn = gameSoundState;
        if ( isGameSoundOn == true)
        {
            String path = "C:\\Users\\User\\Downloads\\sound.mpeg";  
          
        //Instantiating Media class  
        Media media = new Media(new File(path).toURI().toString());  
          
        //Instantiating MediaPlayer class   
        MediaPlayer mediaPlayer = new MediaPlayer(media);  
          
        //by setting this property to true, the audio will be played   
        mediaPlayer.setAutoPlay(true);  
        }
        else
        {
            String path = "C:\\Users\\User\\Downloads\\sound.mpeg";  
          
        //Instantiating Media class  
        Media media = new Media(new File(path).toURI().toString());  
          
        //Instantiating MediaPlayer class   
        MediaPlayer mediaPlayer = new MediaPlayer(media);  
          mediaPlayer.setVolume(0);
        //by setting this property to true, the audio will be played   
        mediaPlayer.setAutoPlay(true);  
        }
    }
    public void setBackground( String newBackground)
    {
        background = newBackground;
    }
    
}
