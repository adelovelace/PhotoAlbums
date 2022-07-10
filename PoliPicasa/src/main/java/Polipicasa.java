/* Polipicasa
 *
 * @authors
 * Donoso Bravo, Luis
 * Mero Plaza, Andrea
 * Peñafiel Labanda, Alex
 *
 */
import GUI.MainPage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import validator.ValidatorData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Polipicasa extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        File fileLogo = new File("src/Assets/polito_logo.png");

        primaryStage.setTitle("Polipicasa");
        MainPage mp = new MainPage();
        Scene scene = new Scene(mp.getPane());


        try{
            primaryStage.getIcons().add(new Image(new FileInputStream(fileLogo.getAbsolutePath())));}
        catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        //ValidatorData.createUserFile();
        launch();
    }
}

