package com.groupb.soa;
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
    
    @Override
    public void start(Stage stage) throws Exception {
        
        //String path = "C:\\Users\\User\\Downloads\\sound.mpeg";  
          
        //Instantiating Media class  
        //Media media = new Media(new File(path).toURI().toString());  
          
        //Instantiating MediaPlayer class   
        //MediaPlayer mediaPlayer = new MediaPlayer(media);  
          
        //by setting this property to true, the audio will be played   
        //mediaPlayer.setAutoPlay(true);  
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenuScene.fxml"));
        
        Scene scene = new Scene(root);
        
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Settlers of Anatolia");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
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