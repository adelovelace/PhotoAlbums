package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GaleryPage {

    private BorderPane root;

    public GaleryPage() {

        root = new BorderPane();
        File decoImage = new File("src/Assets/playa.png");

        try {

            Label lbPolipicasa = new Label("Polipicasa");
            lbPolipicasa.setStyle("-fx-font-family: Hurricane;" + "-fx-font-size: 64px;" + "-fx-background-color: rgba(217, 217, 217, 0.5);");
            lbPolipicasa.setAlignment(Pos.TOP_LEFT);

            Image decoIcon =new Image(new FileInputStream(decoImage.getAbsolutePath()));
            ImageView decoIconView = new ImageView(decoIcon);
            decoIconView.setFitHeight(200);
            decoIconView.setFitWidth(200);


            HBox titleApp = new HBox();
            HBox decoration = new HBox();

            VBox menu = createMenu();
            GridPane albumsPane = createAlbums();

            menu.setPrefWidth(250);

            titleApp.setStyle("-fx-background-color: rgba(217, 217, 217, 0.5);");
            titleApp.setPrefWidth(250);

            decoration.setAlignment(Pos.BOTTOM_LEFT);
            decoration.setStyle("-fx-background-color: rgba(217, 217, 217, 0.5);");
            decoration.setPrefWidth(250);


            titleApp.getChildren().add(lbPolipicasa);
            decoration.getChildren().add(decoIconView);



            root.setTop(lbPolipicasa);
            root.setCenter(albumsPane);
            root.setBottom(decoration);
            root.setLeft(menu);




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public VBox createMenu(){

        File albumImage = new File("src/Assets/photo-album.png");
        File photoImage = new File("src/Assets/imagen.png");

        VBox menu = new VBox();

        try {

        Image albumIcon =new Image(new FileInputStream(albumImage.getAbsolutePath()));
        ImageView albumIconView = new ImageView(albumIcon);
        albumIconView.setFitHeight(45);
        albumIconView.setFitWidth(45);

        Image photoIcon =new Image(new FileInputStream(photoImage.getAbsolutePath()));
        ImageView photoIconView = new ImageView(photoIcon);
        photoIconView.setFitHeight(45);
        photoIconView.setFitWidth(45);


        Label albumLabel = new Label("Albums");
        albumLabel.setGraphic(albumIconView);
        albumLabel.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 40px;" +"-fx-text-fill: #006F84;");
        albumLabel.setAlignment(Pos.TOP_CENTER);


        Label photoLabel = new Label("Photos");
        photoLabel.setGraphic(photoIconView);
        photoLabel.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 40px;" +"-fx-text-fill: #006F84;");
        photoLabel.setAlignment(Pos.TOP_CENTER);

        menu.setAlignment(Pos.CENTER);
        menu.setStyle("-fx-background-color: rgba(217, 217, 217, 0.5);" );
        menu.setSpacing(60);
        menu.setPrefWidth(250);


        HBox albums = new HBox();
        albums.setAlignment(Pos.BASELINE_CENTER);

        HBox photos = new HBox();
        photos.setAlignment(Pos.BASELINE_CENTER);


        menu.getChildren().addAll(albums,photos);
        albums.getChildren().add(albumLabel);
        photos.getChildren().add(photoLabel);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return menu;
    }

    public GridPane createAlbums(){
        return null;
    }

    public HBox createPhotos(){
        return null;
    }


    public BorderPane getRoot() {
        return root;
    }

}
