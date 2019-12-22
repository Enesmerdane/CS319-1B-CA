package com.groupb.soa.presentation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Alper
 */
public class HowToPlayScreen implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextArea howtoplay;
    @FXML
    private Button returntomain;
    @FXML
    private AnchorPane rootPane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        returntomain.setStyle("-fx-background-color: ffb04d; -fx-background-radius: 0.5em;");
        returntomain.setOnMouseClicked( new EventHandler<MouseEvent>(){
            @Override
            public void handle( MouseEvent e)
            {
                try
                {
                    goBackMainMenu(null);
                }
                catch(Exception ex)
                {
                    System.out.println( "Something went wrong!");
                }
            }
        });
    }    
        private void goBackMainMenu(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/MainMenuScene.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
