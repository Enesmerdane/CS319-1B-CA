package com.groupb.soa;
import com.groupb.soa.business.controller.GameController;
import java.io.File;  
  


import javafx.scene.media.Media;  
import javafx.scene.media.MediaPlayer;  
import javafx.scene.media.MediaView;  
import javafx.stage.Stage; 
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    
    private GameController game; 
    
    private static MainApp instance;
    
    public static MainApp getInstance() {
            return instance;
    }
    
    public GameController getGameControllerObj(){
        return game;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
        game = new GameController(stage, this);
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
}