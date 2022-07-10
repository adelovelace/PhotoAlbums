package GUI;

import gallery.Album;
import gallery.Galery;
import gallery.Photo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import user.Session;
import user.User;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GaleryPage {

    private BorderPane root;
    private Session session;

    public GaleryPage(Session session) {
        this.session = session;
        root = new BorderPane();
        File decoImage = new File("src/Assets/playa.png");

        try {

            Label lbPolipicasa = new Label("Polipicasa");
            lbPolipicasa.setStyle("-fx-font-family: Hurricane;" + "-fx-font-size: 50px;" + "-fx-background-color: rgba(217, 217, 217, 0.5);" + "-fx-alignment: center;");
            lbPolipicasa.setAlignment(Pos.TOP_LEFT);
            lbPolipicasa.setPrefWidth(250);

            Image decoIcon = new Image(new FileInputStream(decoImage.getAbsolutePath()));
            ImageView decoIconView = new ImageView(decoIcon);
            decoIconView.setFitHeight(250);
            decoIconView.setFitWidth(250);

            HBox topElements = new HBox();

            HBox titleApp = new HBox();
            HBox decoration = new HBox();
            HBox albumsFeatures = albumsFeatures();

            VBox menu = createMenu();


            menu.setPrefWidth(250);

            titleApp.setStyle("-fx-background-color: rgba(217, 217, 217, 0.5);");
            titleApp.setAlignment(Pos.BASELINE_LEFT);
            titleApp.setMaxWidth(350);


            albumsFeatures.setAlignment(Pos.BASELINE_LEFT);

            decoration.setAlignment(Pos.BOTTOM_LEFT);
            decoration.setStyle("-fx-background-color: rgba(217, 217, 217, 0.5);");
            decoration.setMaxWidth(350);

            createAlbums();

            topElements.setSpacing(500);

            titleApp.getChildren().add(lbPolipicasa);
            decoration.getChildren().add(decoIconView);

            topElements.getChildren().add(lbPolipicasa);
            topElements.getChildren().add(albumsFeatures);


            root.setBottom(decoration);
            root.setTop(topElements);
            root.setLeft(menu);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public VBox createMenu() {

        File albumImage = new File("src/Assets/photo-album.png");
        VBox menu = new VBox();
        try {

            Image albumIcon = new Image(new FileInputStream(albumImage.getAbsolutePath()));
            ImageView albumIconView = new ImageView(albumIcon);
            albumIconView.setFitHeight(45);
            albumIconView.setFitWidth(45);

            Label albumLabel = new Label("Albums");
            albumLabel.setGraphic(albumIconView);
            albumLabel.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 40px;" + "-fx-text-fill: #006F84;");
            albumLabel.setAlignment(Pos.TOP_CENTER);


            menu.setAlignment(Pos.CENTER);
            menu.setStyle("-fx-background-color: rgba(217, 217, 217, 0.5);");
            menu.setSpacing(60);
            menu.setPrefWidth(250);


            HBox albums = new HBox();
            albums.setAlignment(Pos.BASELINE_CENTER);


            menu.setOnMouseClicked(event -> {
                root.setRight(new VBox());
                createAlbums();
            });

            menu.getChildren().addAll(albums);
            albums.getChildren().add(albumLabel);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return menu;
    }

    public void createAlbums() {
        Galery galery = session.getUser().getGalery();
        FlowPane albums = new FlowPane();
        albums.setVgap(10);
        albums.setHgap(10);
        albums.setPadding(new Insets(10, 10, 10, 10));
        VBox addAlbum = new VBox();
        addAlbum.setSpacing(10);
        addAlbum.setPadding(new Insets(10, 10, 10, 10));
        addAlbum.setStyle("-fx-border-color: #006F84;" + "-fx-border-width: 2px;");
        File addAlbumImage = new File("src/Assets/add-album.png");
        try{
            ImageView AddAlbumImageview = new ImageView(new Image(new FileInputStream(addAlbumImage.getAbsolutePath())));
            AddAlbumImageview.setFitHeight(125);
            AddAlbumImageview.setFitWidth(125);
            Label addAlbumLabel = new Label("Add Album");
            addAlbumLabel.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;");
            addAlbum.getChildren().addAll(AddAlbumImageview, addAlbumLabel);
            addAlbum.setPrefSize(200,200);
            addAlbum.setAlignment(Pos.CENTER);
            albums.getChildren().add(addAlbum);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        for (Album albums1 : galery.getAlbums()) {
            VBox album = new VBox();
            album.setSpacing(10);
            album.setPadding(new Insets(10));
            album.setPrefSize(200,200);
            album.setStyle("-fx-border-color: #006F84;" + "-fx-border-width: 2px;");
            File albumFile = new File("src/Assets/photo-album.png");
            try {
                ImageView albumIconView = new ImageView(new Image(new FileInputStream(albumFile.getAbsolutePath())));
                albumIconView.setFitHeight(125);
                albumIconView.setFitWidth(125);
                Label albumName = new Label(albums1.getAlbumName());
                albumName.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;");
                album.setAlignment(Pos.CENTER);
                album.getChildren().addAll(albumIconView, albumName);
                albums.getChildren().add(album);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            album.setOnMouseClicked(e -> {
                VBox albumInfo = chargeAlbumInfo(albums1);
                root.setRight(albumInfo);
            });
            root.setCenter(albums);
        }
    }

    public VBox chargeAlbumInfo(Album<Photo> album) {
        VBox albumInfo = new VBox();
        albumInfo.setSpacing(10);
        albumInfo.setPadding(new Insets(10, 10, 10, 10));
        albumInfo.setStyle("-fx-background-color: rgba(217, 217, 217, 0.5);" + "-fx-border-color: #006F84;" + "-fx-border-width: 2px;");
        albumInfo.setPadding(new Insets(10, 10, 10, 10));
        albumInfo.setAlignment(Pos.CENTER);
        String albumName = album.getAlbumName();
        Label titleAlbum = new Label("Album's Name:");
        titleAlbum.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;" + "-fx-font-weight: bold;");
        Label TitleAlbumDescription = new Label("Description:");
        TitleAlbumDescription.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;" + "-fx-font-weight: bold;");
        Label lbAlbumName = new Label(albumName);
        lbAlbumName.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;");
        Label lbAlbumDescription = new Label(album.getAlbumDescription());
        lbAlbumDescription.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;");
        Button ShowPhotos = new Button("Show Photos");
        ShowPhotos.setStyle("-fx-text-fill: #FFFFFF;" +
                "-fx-background-color: #C24242;" +
                "-fx-text-alignment: center;" +
                "-fx-font-family: Galdeano;" +
                "-fx-font-size: 35px;");
        albumInfo.getChildren().addAll(titleAlbum, lbAlbumName, TitleAlbumDescription, lbAlbumDescription, ShowPhotos);
        return albumInfo;

    }

    public HBox albumsFeatures() {

        File addImage = new File("src/Assets/add.png");


        HBox features = new HBox();


        try {

            Image addIcon = new Image(new FileInputStream(addImage.getAbsolutePath()));
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

                Stage popupwindow = new Stage();

                popupwindow.initModality(Modality.APPLICATION_MODAL);


                Object selectedItem = cbxSearchOpt.getSelectionModel().getSelectedItem();
                popupwindow.setTitle("Search by " + selectedItem);

                System.out.println(selectedItem);

                VBox layout = new VBox(10);
                Scene scene1 = new Scene(layout, 400, 250);

                HBox option = new HBox();

                Label selectionLbl = new Label(selectedItem.toString().substring(0, 1).toUpperCase() + selectedItem.toString().substring(1));
                TextField textField = new TextField();
                textField.setMaxWidth(100);

                option.getChildren().addAll(selectionLbl, textField);
                option.setSpacing(5);
                option.setStyle("-fx-alignment: center");

                Button searchBtn = new Button("Search");
                searchBtn.setAlignment(Pos.CENTER);

                layout.setSpacing(5);
                layout.setStyle("-fx-alignment: center");

                layout.getChildren().addAll(selectionLbl, textField, searchBtn);

                popupwindow.setScene(scene1);
                popupwindow.showAndWait();

            }));

            features.setSpacing(40);
            features.setStyle("-fx-alignment: center");

            features.getChildren().addAll(addIconView, cbxSearchOpt);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return features;
    }


    public BorderPane getRoot() {
        return root;
    }

}
