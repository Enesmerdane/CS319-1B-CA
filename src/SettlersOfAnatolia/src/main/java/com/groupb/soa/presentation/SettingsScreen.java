package com.groupb.soa.presentation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.groupb.soa.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SettingsScreen implements Initializable {
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private Button game_settings_game_music_state;
    
    @FXML
    private Button game_settings_game_sound_state;
    
    private boolean isMusicOn;
    private boolean isSoundsOn;
    SettingsController settings;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         settings = MainApp.getInstance().getSettings();
        
        isMusicOn  = settings.getIsGameMusicOn();
        isSoundsOn = settings.getIsGameSoundOn();
        
        System.out.println("Buradayız");
        
        if(isMusicOn){
            game_settings_game_music_state.setText("Game Music: ON");
        }else{
            game_settings_game_music_state.setText("Game Music: OFF");
        }
        
        if(isSoundsOn){
            game_settings_game_sound_state.setText("Game Sounds: ON");
            
        }else{
            game_settings_game_sound_state.setText("Game Sounds: OFF");
        }
    }   
    
    @FXML
    private void goBackMainMenu() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/MainMenuScene.fxml"));
        
        rootPane.getChildren().setAll(pane);
        
    }
    
    @FXML
    private void saveChanges() throws IOException{
        
    }
    
    @FXML
    private void changeMusicState(){
        if(isMusicOn){
            game_settings_game_music_state.setText("Game Music: OFF");
            
        }else{
            game_settings_game_music_state.setText("Game Music: ON");
        }
        isMusicOn = !isMusicOn;
         settings.setIsGameMusicOn(isMusicOn);
    }
    
    @FXML
    private void changeSoundState(){
        if(isSoundsOn){
            game_settings_game_sound_state.setText("Game Sounds: OFF");
        }else{
            game_settings_game_sound_state.setText("Game Sounds: ON");
        }
        isSoundsOn = !isSoundsOn;
         settings.setIsGameSoundOn(isSoundsOn);
    }
}
