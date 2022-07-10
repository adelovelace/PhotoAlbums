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
import util.CircularDoublyLinkedList;
import validator.ValidatorData;


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
            decoration.setMaxWidth(250);

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

        addAlbum.setOnMouseClicked(event -> {
            addAlbumToGalery();
        });

        try {
            ImageView AddAlbumImageview = new ImageView(new Image(new FileInputStream(addAlbumImage.getAbsolutePath())));
            AddAlbumImageview.setFitHeight(125);
            AddAlbumImageview.setFitWidth(125);
            Label addAlbumLabel = new Label("Add Album");
            addAlbumLabel.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;");
            addAlbum.getChildren().addAll(AddAlbumImageview, addAlbumLabel);
            addAlbum.setPrefSize(200, 200);
            addAlbum.setAlignment(Pos.CENTER);
            albums.getChildren().add(addAlbum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Album albums1 : galery.getAlbums()) {
            VBox album = new VBox();
            album.setSpacing(10);
            album.setPadding(new Insets(10));
            album.setPrefSize(200, 200);
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
        Button showPhotos = new Button("Show Photos");
        Button editAlbum = new Button("Edit Album");
        showPhotos.setOnAction(
                e -> {
                    visualizePic(album);
                }
        );
        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(showPhotos, editAlbum);
        editAlbum.setStyle("-fx-text-fill: #FFFFFF;" +
                "-fx-background-color: #C24242;" +
                "-fx-text-alignment: center;" +
                "-fx-font-family: Galdeano;" +
                "-fx-font-size: 30px;");
        editAlbum.setOnMouseClicked(e -> {
            editAlbumBox(album);
        });
        showPhotos.setStyle("-fx-text-fill: #FFFFFF;" +
                "-fx-background-color: #C24242;" +
                "-fx-text-alignment: center;" +
                "-fx-font-family: Galdeano;" +
                "-fx-font-size: 30px;");
        albumInfo.getChildren().addAll(titleAlbum, lbAlbumName, TitleAlbumDescription, lbAlbumDescription, buttons);
        return albumInfo;

    }

    public void editAlbumBox(Album<Photo> album) {
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
        TextField lbAlbumNameText = new TextField(albumName);
        lbAlbumNameText.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");
        TextField lbAlbumDescriptionText = new TextField(album.getAlbumDescription());
        lbAlbumDescriptionText.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");
        Button editAlbum = new Button("Edit Album");
        editAlbum.setStyle("-fx-text-fill: #FFFFFF;" +
                "-fx-background-color: #C24242;" +
                "-fx-text-alignment: center;" +
                "-fx-font-family: Galdeano;" +
                "-fx-font-size: 30px;");
        editAlbum.setOnMouseClicked(e -> {

            if (!lbAlbumNameText.getText().equals("") && !lbAlbumDescriptionText.getText().equals("")) {
                album.setAlbumName(lbAlbumNameText.getText());
                album.setAlbumDescription(lbAlbumDescriptionText.getText());
                ValidatorData.editAlbumInfoInFile(album, session.getUser(), lbAlbumNameText.getText(), lbAlbumDescriptionText.getText());
            }
            createAlbums();
            root.setRight(null);
        });
        albumInfo.getChildren().addAll(titleAlbum, lbAlbumNameText, TitleAlbumDescription, lbAlbumDescriptionText, editAlbum);
        root.setRight(albumInfo);
    }

    public void visualizePic(Album<Photo> album) {
        FlowPane pics = new FlowPane();
        pics.setVgap(10);
        pics.setHgap(10);
        pics.setPadding(new Insets(10));
        VBox addPhoto = new VBox();
        addPhoto.setSpacing(10);
        addPhoto.setPadding(new Insets(10, 10, 10, 10));
        addPhoto.setStyle("-fx-border-color: #006F84;" + "-fx-border-width: 2px;");
        File addPhotoImage = new File("src/Assets/photo.png");
        try {
            ImageView AddPhotoImageview = new ImageView(new Image(new FileInputStream(addPhotoImage.getAbsolutePath())));
            AddPhotoImageview.setFitHeight(125);
            AddPhotoImageview.setFitWidth(125);
            Label addPhotoLabel = new Label("Add Photo");
            addPhotoLabel.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;");
            addPhoto.getChildren().addAll(AddPhotoImageview, addPhotoLabel);
            addPhoto.setPrefSize(200, 200);
            addPhoto.setAlignment(Pos.CENTER);
            pics.getChildren().add(addPhoto);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CircularDoublyLinkedList<Photo> photos = album.getPhotosOnAlbum();
        for (int i = 0; i < photos.size(); i++) {
            Photo pic = photos.get(i);
            File photoImage = new File(pic.getRoute());
            try {
                ImageView photoImageview = new ImageView(new Image(new FileInputStream(photoImage.getAbsolutePath())));
                photoImageview.setFitHeight(150);
                photoImageview.setFitWidth(150);
                pics.getChildren().addAll(photoImageview);
                pics.setPrefSize(200, 200);
                pics.setAlignment(Pos.CENTER);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        root.setCenter(pics);
    }

    public void addAlbumToGalery() {
        GridPane addAlbum = new GridPane();
        addAlbum.setVgap(10);
        addAlbum.setHgap(10);
        addAlbum.setPadding(new Insets(10));
        Label addAlbumLabel = new Label("Add Album");
        addAlbumLabel.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 35px;" + "-fx-text-fill: #006F84;" + "-fx-font-weight: bold;");
        Label addAlbumName = new Label("Album's Name:");
        addAlbumName.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;");
        Label addAlbumDescription = new Label("Description:");
        addAlbumDescription.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;");
        TextField addAlbumNameText = new TextField();
        addAlbumNameText.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");
        TextField addAlbumDescriptionText = new TextField();
        addAlbumDescriptionText.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");
        Button addAlbumButton = new Button("Add Album");
        addAlbumButton.setStyle("-fx-text-fill: #FFFFFF;" +
                "-fx-background-color: #C24242;" +
                "-fx-text-alignment: center;" +
                "-fx-font-family: Galdeano;" +
                "-fx-font-size: 30px;");
        addAlbumButton.setOnMouseClicked(e -> {
            if (!addAlbumNameText.getText().equals("") && !addAlbumDescriptionText.getText().equals("")) {
                Album<Photo> album = new Album<>(addAlbumNameText.getText(), addAlbumDescriptionText.getText());
                ValidatorData.saveAlbumInFile(album, session.getUser());
                createAlbums();
                root.setRight(null);
            }
        });
        addAlbum.add(addAlbumLabel, 0, 0);
        addAlbum.add(addAlbumName, 0, 1);
        addAlbum.add(addAlbumNameText, 1, 1);
        addAlbum.add(addAlbumDescription, 0, 2);
        addAlbum.add(addAlbumDescriptionText, 1, 2);
        addAlbum.add(addAlbumButton, 1, 3);
        root.setRight(addAlbum);
    }

    public HBox albumsFeatures() {


        HBox features = new HBox();

        Label searchLbl = new Label("Search:");
        searchLbl.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;");


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

        features.getChildren().addAll(searchLbl,cbxSearchOpt);


        return features;
    }




    public BorderPane getRoot() {
        return root;
    }

}
