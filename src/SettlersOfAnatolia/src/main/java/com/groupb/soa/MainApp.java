package com.groupb.soa;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;


/**
 *
 * @author Enes Merdane
 */
public class MainApp extends Application {
    
    private ImageView background_image_view;
    private Image background_image;
    private Button continue_game_button;
    private Button new_game_button;
    private Button settings_button;
    private Button exit_game_button;
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenuScene.fxml"));
        
        Scene scene = new Scene(root);
        
        scene.getStylesheets().add("/styles/Styles.css");
        
        
        stage.setTitle("Settlers of Catan");
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
    
    private void setBackgroundImage(Scene scene){
        try {
            background_image = new Image("/images/main_menu_scene_background.jpg");
            background_image_view.setImage(background_image);
            
            //Setting the position of the image 
            background_image_view.setX(0); 
            background_image_view.setY(0); 

            //setting the fit height and width of the image view 
            background_image_view.setFitHeight(1080.0); 
            background_image_view.setFitWidth(1920.0); 

            //Setting the preserve ratio of the image view 
            background_image_view.setPreserveRatio(true);
        
        } catch (Exception e) {
            System.out.println("File cannot be loaded.");
        }
        
        ImagePattern pattern = new ImagePattern(background_image);
        scene.setFill(pattern);
        
    }
    
    private void setButtons(Group buttonsGroup){
        int ButX = 300;
        int ButY = 400;
        continue_game_button = new Button("Continue Game");
        
        continue_game_button.setStyle("-fx-border-radius: 30;");
        continue_game_button.setScaleX(2);
        continue_game_button.setScaleY(2);
        continue_game_button.setLayoutX(ButX);
        continue_game_button.setLayoutY(ButY);
        
        ButY += 150;
        
        new_game_button = new Button("New Game");
        
        new_game_button.setStyle("-fx-border-radius: 30;");
        new_game_button.setScaleX(2);
        new_game_button.setScaleY(2);
        new_game_button.setLayoutX(ButX);
        new_game_button.setLayoutY(ButY);
        
        buttonsGroup.getChildren().add(continue_game_button);
        buttonsGroup.getChildren().add(new_game_button);
        
    }
}
