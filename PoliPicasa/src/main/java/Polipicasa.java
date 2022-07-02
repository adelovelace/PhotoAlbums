/* Polipicasa
 *
 * @authors
 * Donoso Bravo, Luis
 * Mero Plaza, Andrea
 * Peñafiel Labanda, Alex
 *
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class Polipicasa extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Polipicasa");
        VBox vbox = new VBox();
        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
