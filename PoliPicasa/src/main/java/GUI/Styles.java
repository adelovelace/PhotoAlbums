package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Styles {

    static Insets basicInsets = new Insets(10);
    static String labelTitle = "-fx-text-fill: #006F84;" +
            "-fx-text-alignment: center;" +
            "-fx-font-family: Galdeano;" +
            "-fx-font-size: 70px;";
    static String labelSubTitle = "-fx-text-fill: #006F84;" +
            "-fx-text-alignment: center;" +
            "-fx-font-family: Galdeano;" +
            "-fx-font-size: 35px;";
    static String labelSquare = "-fx-text-fill: #006F84;" +
            "-fx-text-alignment: center;" +
            "-fx-font-family: Galdeano;" +
            "-fx-font-size: 35px;";
    static String buttonStyle = "-fx-text-fill: #FFFFFF;" +
            "-fx-background-color: #C24242;" +
            "-fx-text-alignment: center;" +
            "-fx-font-family: Galdeano;" +
            "-fx-font-size: 35px;";

    static String ButtonStyle2 = "-fx-text-fill: #006F84;" +
            "-fx-text-alignment: center;" +
            "-fx-border-radius: 10px;" +
            "-fx-font-weight: bold;" +
            "-fx-border-color: #006F84;" +
            "-fx-border-width: 5px;" +
            "-fx-background-color: #FFFFFF;" +
            "-fx-font-family: Galdeano;" +
            "-fx-font-size: 35px;";

    static String SubTitle1 ="-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;" + "-fx-font-weight: bold;";
    static String SubTitle2 ="-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;";

    public static void setTitleStyle(Label label) {
        label.setStyle(labelTitle);
    }
    public static void setStyleButtonMain(Button button) {
        button.setStyle(ButtonStyle2);
    }

    public static void setHBoxStyle(HBox hbox) {
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(basicInsets);
        hbox.setSpacing(10);
    }

    public static void setVBoxStyle(VBox vbox) {
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(basicInsets);
        vbox.setSpacing(10);
    }
    public static void setGridPaneStyle(GridPane gp) {
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(basicInsets);
        gp.setVgap(10);
        gp.setHgap(10);
    }
    public static void setSubTitleStyle(Label label) {
        label.setStyle(labelSubTitle);
    }
    public static void setSquareStyle(TextField tf) {
        tf.setStyle(labelSquare);
    }
    public static void setButtonRedStyle(Button button) {
        button.setStyle(buttonStyle);
    }
    public static void setPolipicasaLabelStyle(Label label) {
        label.setStyle("-fx-font-family: Hurricane;" + "-fx-font-size: 50px;" + "-fx-background-color: rgba(217, 217, 217, 0.5);" + "-fx-alignment: center;");
        label.setAlignment(Pos.TOP_LEFT);
        label.setPrefWidth(250);
    }

    public static void setSubTitle1Style(Label label) {
        label.setStyle(SubTitle1);
    }
    public static void setSubTitle2Style(Label label) {
        label.setStyle(SubTitle2);
    }
    public static void setSubTextStyle(TextField tf) {
        tf.setStyle(SubTitle2);
    }
}
