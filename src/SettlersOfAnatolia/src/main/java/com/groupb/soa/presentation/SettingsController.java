//Settings Controller 
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.groupb.soa.presentation;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import com.groupb.soa.MainApp;

/**
*
* @author Irmak
*/
public class SettingsController {
    private boolean isGameMusicOn;
    private boolean isGameSoundOn;
    private String background;
    Media media;
    MediaPlayer mediaPlayer;
    public SettingsController()
    {
        isGameMusicOn = true;
        isGameSoundOn = true; 
        background = "wooden";
        String path = "/musics/gameMusic.mpeg";
        //Instantiating Media class  
        media = new Media(MainApp.getInstance().getClass().getResource(path).toString());  
        
        //Instantiating MediaPlayer class   
         mediaPlayer = new MediaPlayer(media);  
          
        //by setting this property to true, the audio will be played
        System.out.println("it plays");
        mediaPlayer.setAutoPlay(true); 
        
       mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

    mediaPlayer.play();
       /*mediaPlayer.setOnReady(new Runnable() {
        @Override
        public void run() {
            mediaPlayer.setAutoPlay();
        }
    });*/
    }
    
    public boolean getIsGameMusicOn()
    {
        return isGameMusicOn; 
    }
    
    public boolean getIsGameSoundOn()
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
            /*String path = "/musics/gameMusic.mpeg";  
          
        //Instantiating Media class  
        Media media = new Media(new File(path).toURI().toString());  
          
        //Instantiating MediaPlayer class   
        MediaPlayer mediaPlayer = new MediaPlayer(media);  */
           
          //mediaPlayer.setVolume(10);
        //by setting this property to true, the audio will be played   
        mediaPlayer.setMute(false);  
        }
        else
        {
           /* String path = "/musics/gameMusic.mpeg";  
          
        //Instantiating Media class  
        Media media = new Media(new File(path).toURI().toString());  
          
        //Instantiating MediaPlayer class   
        MediaPlayer mediaPlayer = new MediaPlayer(media); */ 
          //mediaPlayer.setVolume(0);
        //by setting this property to true, the audio will be played   
        //mediaPlayer.setAutoPlay(false); 
        mediaPlayer.setMute(true);
        System.out.println("hi false");
        }
    }
    
    public void setIsGameSoundOn( boolean gameSoundState)
    {
         isGameSoundOn = gameSoundState;
        if ( isGameSoundOn == true)
        {
            /*String path = "/musics/gameMusic.mpeg";  
          
        //Instantiating Media class  
        Media media = new Media(new File(path).toURI().toString());  
          
        //Instantiating MediaPlayer class   
        MediaPlayer mediaPlayer = new MediaPlayer(media);  */
           
          //mediaPlayer.setVolume(10);
        //by setting this property to true, the audio will be played   
        mediaPlayer.setMute(false);  
        }
        else
        {
           /* String path = "/musics/gameMusic.mpeg";  
          
        //Instantiating Media class  
        Media media = new Media(new File(path).toURI().toString());  
          
        //Instantiating MediaPlayer class   
        MediaPlayer mediaPlayer = new MediaPlayer(media); */ 
          //mediaPlayer.setVolume(0);
        //by setting this property to true, the audio will be played   
        //mediaPlayer.setAutoPlay(false); 
        mediaPlayer.setMute(true);
        System.out.println("hi false");
        }
    }
    public void setBackground( String newBackground)
    {
        background = newBackground;
    }
    
}
