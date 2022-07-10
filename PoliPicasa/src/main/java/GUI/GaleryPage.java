package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


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
            lbPolipicasa.setStyle("-fx-font-family: Hurricane;" + "-fx-font-size: 64px;" + "-fx-background-color: rgba(217, 217, 217, 0.5);" + "-fx-alignment: center;");
            lbPolipicasa.setAlignment(Pos.TOP_LEFT);
            lbPolipicasa.setPrefWidth(250);

            Image decoIcon =new Image(new FileInputStream(decoImage.getAbsolutePath()));
            ImageView decoIconView = new ImageView(decoIcon);
            decoIconView.setFitHeight(250);
            decoIconView.setFitWidth(250);

            HBox topElements = new HBox();

            HBox titleApp = new HBox();
            HBox decoration = new HBox();
            HBox albumsFeatures = albumsFeatures();

            VBox menu = createMenu();
            GridPane albumsPane = createAlbums();

            menu.setPrefWidth(250);

            titleApp.setStyle("-fx-background-color: rgba(217, 217, 217, 0.5);");
            titleApp.setAlignment(Pos.BASELINE_LEFT);
            titleApp.setMaxWidth(250);

            albumsFeatures.setAlignment(Pos.BASELINE_LEFT);

            decoration.setAlignment(Pos.BOTTOM_LEFT);
            decoration.setStyle("-fx-background-color: rgba(217, 217, 217, 0.5);");
            decoration.setMaxWidth(250);


            topElements.setSpacing(500);

            titleApp.getChildren().add(lbPolipicasa);
            decoration.getChildren().add(decoIconView);

            topElements.getChildren().add(lbPolipicasa);
            topElements.getChildren().add(albumsFeatures);




            root.setCenter(albumsPane);
            root.setBottom(decoration);
            root.setTop(topElements);
            root.setLeft(menu);




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public VBox createMenu(){

        File albumImage = new File("src/Assets/photo-album.png");


        VBox menu = new VBox();

        try {

        Image albumIcon =new Image(new FileInputStream(albumImage.getAbsolutePath()));
        ImageView albumIconView = new ImageView(albumIcon);
        albumIconView.setFitHeight(45);
        albumIconView.setFitWidth(45);


        Label albumLabel = new Label("Albums");
        albumLabel.setGraphic(albumIconView);
        albumLabel.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 40px;" +"-fx-text-fill: #006F84;");
        albumLabel.setAlignment(Pos.TOP_CENTER);


        menu.setAlignment(Pos.CENTER);
        menu.setStyle("-fx-background-color: rgba(217, 217, 217, 0.5);" );
        menu.setSpacing(60);
        menu.setPrefWidth(250);


        HBox albums = new HBox();
        albums.setAlignment(Pos.BASELINE_CENTER);

        HBox photos = new HBox();
        photos.setAlignment(Pos.BASELINE_CENTER);


        menu.getChildren().addAll(albums);
        albums.getChildren().add(albumLabel);




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return menu;
    }

    public GridPane createAlbums(){
        return null;
    }

    public HBox albumsFeatures(){

        File addImage = new File("src/Assets/add.png");


        HBox features = new HBox();


        try {

            Image addIcon =new Image(new FileInputStream(addImage.getAbsolutePath()));
            ImageView addIconView = new ImageView(addIcon);
            addIconView.setFitHeight(30);
            addIconView.setFitWidth(30);


            Button addAlbumBtn = new Button();
            addAlbumBtn.setGraphic(addIconView);
            addAlbumBtn.setAlignment(Pos.CENTER);


            ObservableList<String> items = FXCollections.observableArrayList();
            items.addAll("person", "place", "person & place");

            ComboBox<String> cbxSearchOpt = new ComboBox<>(items);


            cbxSearchOpt.setOnAction((event -> {

                Stage popupwindow=new Stage();

                popupwindow.initModality(Modality.APPLICATION_MODAL);


                Object selectedItem = cbxSearchOpt.getSelectionModel().getSelectedItem();
                popupwindow.setTitle("Search by " + selectedItem);

                System.out.println(selectedItem);

                VBox layout= new VBox(10);
                Scene scene1= new Scene(layout, 400, 250);

                HBox option = new HBox();

                Label selectionLbl = new Label(selectedItem.toString().substring(0, 1).toUpperCase() + selectedItem.toString().substring(1) );
                TextField textField = new TextField();
                textField.setMaxWidth(100);

                option.getChildren().addAll(selectionLbl, textField);
                option.setSpacing(5);
                option.setStyle("-fx-alignment: center");

                Button searchBtn = new Button("Search");
                searchBtn.setAlignment(Pos.CENTER);

                layout.setSpacing(5);
                layout.setStyle("-fx-alignment: center");

                layout.getChildren().addAll(selectionLbl, textField,searchBtn);

                popupwindow.setScene(scene1);
                popupwindow.showAndWait();

            }));

            features.setSpacing(40);
            features.setStyle("-fx-alignment: center");

            features.getChildren().addAll(addIconView,cbxSearchOpt);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        return features;
    }


    public BorderPane getRoot() {
        return root;
    }

}
