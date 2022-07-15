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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import user.Person;
import user.Session;
import util.ArrayList;
import util.CircularDoublyLinkedList;
import validator.ValidatorData;


import java.io.*;
import java.util.concurrent.atomic.AtomicReference;


public class GaleryPage {

    private BorderPane root;
    private Session session;

    public GaleryPage(Session session) {
        this.session = session;
        root = new BorderPane();
        File decoImage = new File("src/Assets/playa.png");
        try {
            Label lbPolipicasa = new Label("Polipicasa");
            Styles.setPolipicasaLabelStyle(lbPolipicasa);
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
        albums.setPadding(new Insets(10));
        VBox addAlbum = new VBox();
        addAlbum.setSpacing(10);
        addAlbum.setPadding(new Insets(10));
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
            Styles.setSubTitle1Style(addAlbumLabel);
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
                Styles.setSubTitle1Style(albumName);
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
        albumInfo.setPadding(new Insets(10));
        albumInfo.setStyle("-fx-background-color: rgba(217, 217, 217, 0.5);" + "-fx-border-color: #006F84;" + "-fx-border-width: 2px;");
        albumInfo.setPadding(new Insets(10));
        albumInfo.setAlignment(Pos.CENTER);
        String albumName = album.getAlbumName();
        Label titleAlbum = new Label("Album's Name:");
        Styles.setSubTitle1Style(titleAlbum);
        Label TitleAlbumDescription = new Label("Description:");
        Styles.setSubTitle1Style(TitleAlbumDescription);
        Label lbAlbumName = new Label(albumName);
        Styles.setSubTitle2Style(lbAlbumName);
        Label lbAlbumDescription = new Label(album.getAlbumDescription());
        Styles.setSubTitle2Style(lbAlbumDescription);
        Button showPhotos = new Button("Show Photos");
        Button editAlbum = new Button("Edit Album");
        Styles.setButtonRedStyle(editAlbum);
        showPhotos.setOnAction(
                e -> {
                    visualizePic(album);
                }
        );
        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(showPhotos, editAlbum);
        Styles.setButtonRedStyle(showPhotos);
        editAlbum.setOnMouseClicked(e -> {
            editAlbumBox(album);
        });
        Button eraseAlbum = new Button("Erase Album");
        Styles.setButtonRedStyle(eraseAlbum);
        eraseAlbum.setOnMouseClicked(e -> {
            ValidatorData.deleteAlbumInFile(album, session.getUser());
            createAlbums();
            root.setRight(null);
        });
        Styles.setButtonRedStyle(eraseAlbum);
        albumInfo.getChildren().addAll(titleAlbum, lbAlbumName, TitleAlbumDescription, lbAlbumDescription, buttons, eraseAlbum);
        return albumInfo;

    }

    public void editAlbumBox(Album<Photo> album) {
        VBox albumInfo = new VBox();
        Styles.setVBoxStyle(albumInfo);
        albumInfo.setStyle("-fx-background-color: rgba(217, 217, 217, 0.5);" + "-fx-border-color: #006F84;" + "-fx-border-width: 2px;");
        String albumName = album.getAlbumName();
        Label titleAlbum = new Label("Album's Name:");
        Styles.setSubTitle1Style(titleAlbum);
        Label TitleAlbumDescription = new Label("Description:");
        Styles.setSubTitle1Style(TitleAlbumDescription);
        TextField lbAlbumNameText = new TextField(albumName);
        Styles.setSubTextStyle(lbAlbumNameText);
        TextField lbAlbumDescriptionText = new TextField(album.getAlbumDescription());
        Styles.setSubTextStyle(lbAlbumDescriptionText);
        Button editAlbum = new Button("Edit Album");
        Styles.setButtonRedStyle(editAlbum);
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
        FlowPane Gallery = new FlowPane();
        Gallery.setPadding(new Insets(10));
        Gallery.setHgap(10);
        Gallery.setVgap(10);
        VBox addPhoto = new VBox();
        addPhoto.setSpacing(10);
        Label addPhotoLabel = new Label("Add Photo");
        Styles.setSubTitle1Style(addPhotoLabel);
        addPhoto.setStyle("-fx-border-color: #006F84;" + "-fx-border-width: 2px;");
        addPhoto.setPadding(new Insets(10));
        addPhoto.setPrefSize(200, 200);
        addPhoto.setAlignment(Pos.CENTER);
        try {
            File photoFile = new File("src/Assets/photo.png");
            ImageView photoIconView = new ImageView(new Image(new FileInputStream(photoFile.getAbsolutePath())));
            photoIconView.setFitHeight(125);
            photoIconView.setFitWidth(125);
            addPhoto.getChildren().addAll(photoIconView, addPhotoLabel);
            addPhoto.setOnMouseClicked(e -> {
                addPhotoInfo(album);
            });
            Gallery.getChildren().add(addPhoto);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CircularDoublyLinkedList<Photo> photos = album.getPhotosOnAlbum();
        for (int i = 0; i < photos.size(); i++) {
            Photo photo = photos.get(i);
            VBox photoShow = new VBox();
            try {
                File photoFile = new File(photo.getRoute());
                ImageView photoIconView = new ImageView(new Image(new FileInputStream(photoFile.getAbsolutePath())));
                Label photoName = new Label(photo.getName());
                Styles.setSubTitle1Style(photoName);
                photoIconView.setFitHeight(125);
                photoIconView.setFitWidth(125);
                photoShow.getChildren().addAll(photoIconView,photoName);
                photoShow.setOnMouseClicked(e -> {
                    showInfoPhoto(photo,album);
                });
                Gallery.getChildren().add(photoShow);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        root.setCenter(Gallery);
    }

    public void showInfoPhoto(Photo photo, Album<Photo> album) {
        VBox photoInfo = new VBox();
        Label titlePhoto = new Label("Photo Info");
        titlePhoto.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 30px;" + "-fx-text-fill: #C24242;" + "-fx-font-weight: bold;");
        Label TitlePhotoDescription = new Label("Description:");
        Label lbPhotoDescriptionText = new Label(photo.getDescriptionPhoto());
        TitlePhotoDescription.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #C24242;" + "-fx-font-weight: bold;");
        lbPhotoDescriptionText.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");
        Label TitlePhotoDate = new Label("Date:");
        TitlePhotoDate.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #C24242;" + "-fx-font-weight: bold;");
        Label lbPhotoDateText = new Label(photo.getDatePhoto());
        lbPhotoDateText.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");
        Label TitlePhotoLocation = new Label("Location:");
        Label lbPhotoLocationText = new Label(photo.getPlacePhoto());
        lbPhotoLocationText.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");
        TitlePhotoLocation.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #C24242;" + "-fx-font-weight: bold;");
        Label TitlePhotoTags = new Label("Tags:");
        TitlePhotoTags.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #C24242;" + "-fx-font-weight: bold;");
        Label lbPhotoTagsText = new Label(photo.getTags());
        lbPhotoTagsText.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");
        Label camera = new Label("Camera:");
        Label lbCameraText = new Label(photo.getCamera());
        lbCameraText.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");
        camera.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #C24242;" + "-fx-font-weight: bold;");
        Button btnDeletePhoto = new Button("Delete Photo");
        Styles.setButtonRedStyle(btnDeletePhoto);
        Button btnEditPhoto = new Button("Edit Photo");
        Styles.setButtonRedStyle(btnEditPhoto);
        Button showPic = new Button("Show Photo Bigger");
        Styles.setButtonRedStyle(showPic);
        btnEditPhoto.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #C24242;");
        photoInfo.setSpacing(10);
        photoInfo.getChildren().addAll(titlePhoto, TitlePhotoDescription, lbPhotoDescriptionText, TitlePhotoDate, lbPhotoDateText, TitlePhotoLocation, lbPhotoLocationText,camera,lbCameraText, TitlePhotoTags, lbPhotoTagsText, btnDeletePhoto, btnEditPhoto, showPic);
        photoInfo.setAlignment(Pos.CENTER);
        photoInfo.setPadding(new Insets(20, 20, 20, 20));
        root.setRight(photoInfo);
        btnEditPhoto.setOnMouseClicked(e -> {
            editPhotoInfo(photo, album);

        });

    }

    public void editPhotoInfo(Photo photo, Album<Photo> album) {
        GridPane editPhoto = new GridPane();
        editPhoto.setAlignment(Pos.CENTER);
        editPhoto.setPadding(new Insets(20, 20, 20, 20));
        editPhoto.setHgap(10);
        editPhoto.setVgap(10);
        Label titlePhoto = new Label("Edit Photo");
        titlePhoto.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 30px;" + "-fx-text-fill: #C24242;" + "-fx-font-weight: bold;");
        Label TitlePhotoDescription = new Label("Description:");
        TextField txtPhotoDescription = new TextField(photo.getDescriptionPhoto());
        TitlePhotoDescription.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #C24242;" + "-fx-font-weight: bold;");
        txtPhotoDescription.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");
        Label TitlePhotoDate = new Label("Date:");
        TextField txtPhotoDate = new TextField(photo.getDatePhoto());
        TitlePhotoDate.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #C24242;" + "-fx-font-weight: bold;");
        txtPhotoDate.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");
        Label TitlePhotoLocation = new Label("Location:");
        TextField txtPhotoLocation = new TextField(photo.getPlacePhoto());
        TitlePhotoLocation.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #C24242;" + "-fx-font-weight: bold;");
        txtPhotoLocation.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");
        Label TittlePhotoPerson = new Label("Person:");
        TextField txtPhotoPerson = new TextField(photo.getPersons());
        TittlePhotoPerson.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #C24242;" + "-fx-font-weight: bold;");
        txtPhotoPerson.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");
        Label TitlePhotoTags = new Label("Tags:");
        TextField txtPhotoTags = new TextField(photo.getTags());
        TitlePhotoTags.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #C24242;" + "-fx-font-weight: bold;");
        txtPhotoTags.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");
        Label TiylePhotoCamera = new Label("Camera:");
        TextField txtPhotoCamera = new TextField(photo.getCamera());
        TiylePhotoCamera.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #C24242;" + "-fx-font-weight: bold;");
        txtPhotoCamera.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");
        Button btnSavePhoto = new Button("Save");
        btnSavePhoto.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #C24242;");
        btnSavePhoto.setOnMouseClicked(e -> {
            ValidatorData.editPhotoInFile(photo, session.getUser(), album, txtPhotoDescription.getText(), txtPhotoLocation.getText(), txtPhotoDate.getText(), txtPhotoPerson.getText(), txtPhotoTags.getText(), txtPhotoCamera.getText());
            root.setRight(new VBox());
        });

        editPhoto.addColumn(0, titlePhoto, TitlePhotoDescription, TitlePhotoDate, TitlePhotoLocation, TittlePhotoPerson, TitlePhotoTags, TiylePhotoCamera, btnSavePhoto);
        editPhoto.addColumn(1, new Label(), txtPhotoDescription, txtPhotoDate, txtPhotoLocation, txtPhotoPerson, txtPhotoTags, txtPhotoCamera);
        root.setRight(editPhoto);
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

        features.getChildren().addAll(searchLbl, cbxSearchOpt);


        return features;
    }

    public void addPhotoInfo(Album<Photo> album) {
        GridPane addPhoto = new GridPane();
        addPhoto.setVgap(10);
        addPhoto.setHgap(10);
        addPhoto.setPadding(new Insets(10));

        Label addDescriptionPhoto = new Label("Photo description:");
        addDescriptionPhoto.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;");

        Label addPlacePhoto = new Label("Photo's Place:");
        addPlacePhoto.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;");

        Label addDatePhoto = new Label("Date:");
        addDatePhoto.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;");

        Label addPersonsOnAlbum = new Label("Person on photos:");
        addPersonsOnAlbum.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;");
        Label addPathPhoto = new Label("Path:");
        addPathPhoto.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;");
        TextField addPathPhotoText = new TextField();
        addPathPhotoText.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");
        Button searchPath = new Button("Search");

        //Label addAlbumRelated =  new Label("Related Album:");
        //addAlbumRelated.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;");

        TextField addDescriptionText = new TextField();
        addDescriptionText.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");

        TextField addPlacePhotoText = new TextField();
        addPlacePhotoText.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");

        TextField addDatePhotoText = new TextField();
        addDatePhotoText.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");

        TextField addPersonsOnAlbumText = new TextField();
        addPersonsOnAlbumText.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");


        Button addPhotoButton = new Button("Add Photo");
        addPhotoButton.setDisable(true);
        Styles.setButtonRedStyle(addPhotoButton);
        AtomicReference<String> title = new AtomicReference<>("Picture");
        searchPath.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
            fileChooser.setTitle("Open Resource File");
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                try {

                    FileInputStream fis = new FileInputStream(file);
                    FileOutputStream fos = new FileOutputStream("src/images/" + file.getName());
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                    addPathPhotoText.setText("src/images/" + file.getName());
                    title.set(file.getName());
                    addPhotoButton.setDisable(false);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        addPhotoButton.setOnMouseClicked(e -> {
            if (!addDescriptionText.getText().equals("") && !addPathPhotoText.getText().equals((""))) {

                ArrayList<Person> persons = new ArrayList<>();
                Album<Photo> albumRelated = album;

                String[] namesList = addPersonsOnAlbumText.getText().split(",");

                for (String name : namesList
                ) {
                    persons.addLast(new Person(name));
                }
                String route = addPathPhotoText.getText();

                Photo newPhoto = new Photo(title.toString(), addDescriptionPhoto.getText(), addPlacePhotoText.getText(), addDatePhotoText.getText(), persons, albumRelated, route);
                ValidatorData.addPhotoToAlbum(newPhoto, session.getUser(), albumRelated);
                root.setCenter(new FlowPane());
                visualizePic(album);
                root.setRight(new VBox());
            }
        });


        addPhoto.add(addDescriptionPhoto, 0, 0);
        addPhoto.add(addDescriptionText, 1, 0);
        addPhoto.add(addPlacePhoto, 0, 1);
        addPhoto.add(addPlacePhotoText, 1, 1);
        addPhoto.add(addDatePhoto, 0, 2);
        addPhoto.add(addDatePhotoText, 1, 2);
        addPhoto.add(addPersonsOnAlbum, 0, 3);
        addPhoto.add(addPersonsOnAlbumText, 1, 3);
        //addPhoto.add(addAlbumRelated, 0, 4);
        //addPhoto.add(addAlbumRelatedText, 1, 4);
        addPhoto.add(addPathPhoto, 0, 4);
        addPhoto.add(addPathPhotoText, 1, 4);
        addPhoto.add(searchPath, 2, 4);
        addPhoto.add(addPhotoButton, 0, 5);

        root.setRight(addPhoto);

    }

    public void createPhoto() {
    }


    public BorderPane getRoot() {
        return root;
    }

}
