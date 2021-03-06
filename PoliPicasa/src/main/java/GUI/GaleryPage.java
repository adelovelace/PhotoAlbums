package GUI;

import gallery.Album;
import gallery.Galery;
import gallery.Photo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import user.Person;
import user.Session;
import util.ArrayList;
import util.CircularDoublyLinkedList;
import validator.ValidatorData;
import java.io.*;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;
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
            HBox albumsFeatures = searchBox();
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
                    root.setRight(new VBox());
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
            root.setRight(new VBox());
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
            System.out.println("indice = " + i);
            System.out.println(photos.get(i).toString());

            Photo photo = photos.get(i);
            VBox photoShow = new VBox();
            photoShow.setMinSize(200, 200);
            photoShow.setMaxSize(200, 200);
            photoShow.setSpacing(10);
            photoShow.setStyle("-fx-border-color: #006F84;" + "-fx-border-width: 2px;");
            photoShow.setPadding(new Insets(10));
            photoShow.setAlignment(Pos.BASELINE_CENTER);
            try {
                File photoFile = new File(photo.getRoute());
                ImageView photoIconView = new ImageView(new Image(new FileInputStream(photoFile.getAbsolutePath())));
                Label photoName = new Label(photo.getName());
                photoName.setMaxWidth(150);
                Styles.setSubTitle1Style(photoName);
                photoName.setAlignment(Pos.CENTER);
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
        Label persons = new Label("Persons:");
        Label lbPersonsText = new Label(photo.getPersons());
        persons.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #C24242;" + "-fx-font-weight: bold;");
        lbPersonsText.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 15px;" + "-fx-text-fill: #006F84;");
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
        photoInfo.getChildren().addAll(titlePhoto, TitlePhotoDescription, lbPhotoDescriptionText, TitlePhotoDate, lbPhotoDateText, TitlePhotoLocation, lbPhotoLocationText,camera,lbCameraText, TitlePhotoTags, lbPhotoTagsText, persons,lbPersonsText, btnDeletePhoto, btnEditPhoto, showPic);
        photoInfo.setAlignment(Pos.CENTER);
        photoInfo.setPadding(new Insets(10));
        root.setRight(photoInfo);
        btnEditPhoto.setOnMouseClicked(e -> {
            editPhotoInfo(photo, album);

        });

        showPic.setOnMouseClicked(e -> {
            VBox pic =showPhotoBigger( album.getPhotosOnAlbum(), album.getPhotosOnAlbum().indexOf(photo) );
            pic.setAlignment(Pos.BASELINE_CENTER);
            pic.setMaxWidth(700);
            pic.setMaxHeight(700);
            pic.setSpacing(10);
            root.setCenter(pic);
        });
        btnDeletePhoto.setOnMouseClicked(e -> {
            ValidatorData.deletePhotoInFile(photo,session.getUser(),album);
            visualizePic(album);
            root.setRight(new VBox());
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

    public HBox searchBox() {
            HBox searchBox = new HBox();
            Styles.setHBoxStyle(searchBox);
            Label placeholder = new Label("Search");
            Styles.setSubTitle1Style(placeholder);
            Label lblSearchPlace = new Label("Place: ");
            Styles.setSubTitle2Style(lblSearchPlace);
            Label lblSearchPerson = new Label("Person: ");
            Styles.setSubTitle2Style(lblSearchPerson);
            TextField txtSearchPlace = new TextField();
            TextField txtSearchPerson = new TextField();
            Button btnSearch = new Button("Search");
            searchBox.getChildren().addAll(placeholder, lblSearchPlace, txtSearchPlace, lblSearchPerson, txtSearchPerson, btnSearch);
            Styles.setButtonRedStyle(btnSearch);
            btnSearch.setOnAction(e -> {
                Album<Photo> albumSearched = null ;
                Galery gal =session.getUser().getGalery();
                root.setRight(new VBox());
                ArrayList<Person> arrayPersons = new ArrayList<>();
                if(!txtSearchPerson.getText().equals("")){
                    String [] persons = txtSearchPerson.getText().split(",");
                    for(String stringPerson : persons){
                        arrayPersons.addLast(new Person(stringPerson));
                    }
                }

                if (!txtSearchPlace.getText().equals("") && !txtSearchPerson.getText().equals("")) {
                    System.out.println("Place and Person");
                    albumSearched = gal.findAlbumByPlaceAndPersons(txtSearchPlace.getText(), arrayPersons);
                } else if (!txtSearchPlace.getText().equals("") && txtSearchPerson.getText().equals("")) {
                    System.out.println("place");
                    albumSearched = gal.findAlbumByPlace(txtSearchPlace.getText());
                } else if (txtSearchPlace.getText().equals("") && !txtSearchPerson.getText().equals("")) {
                    System.out.println("person");
                    albumSearched = gal.findAlbumByPersons(arrayPersons);
                } else {
                    System.out.println("nothing");


                }
                if(albumSearched != null){
                    visualizePic(albumSearched);}
            });
            return searchBox;
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
                    System.out.println("title: " + title);
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

                String[] namesList = addPersonsOnAlbumText.getText().split(",");

                for (String name : namesList
                ) {
                    persons.addLast(new Person(name));
                }
                String route = addPathPhotoText.getText();
                System.out.println("Ruta" + route);

                Photo newPhotoToAlbum = new Photo(title.get(), addDescriptionText.getText(), addPlacePhotoText.getText(), addDatePhotoText.getText(), persons, album, route);
                ValidatorData.addPhotoToAlbum(newPhotoToAlbum, session.getUser(), album);
                root.setCenter(new FlowPane());
                visualizePic(album);
                root.setRight(new VBox());
                addDatePhotoText.setText("");
                addDescriptionText.setText("");
                addPlacePhotoText.setText("");
                addPersonsOnAlbumText.setText("");
                addPathPhotoText.setText("");
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
        addPhoto.add(addPathPhoto, 0, 4);
        addPhoto.add(addPathPhotoText, 1, 4);
        addPhoto.add(searchPath, 2, 4);
        addPhoto.add(addPhotoButton, 0, 5);

        root.setRight(addPhoto);

    }
    public VBox showPhotoBigger(CircularDoublyLinkedList<Photo> photosOnAlbum, int index ) {

        AtomicInteger photo_index = new AtomicInteger(index);

        VBox displayPhoto = new VBox();
        VBox previewBox = new VBox();
        VBox nextBox = new VBox();
        HBox controlBox = new HBox();

        previewBox.setPadding(new Insets(0,400,0,0));
        controlBox.setAlignment(Pos.CENTER_LEFT);
        root.setRight(new VBox());

        File prevPhotoImage = new File("src/Assets/left-arrow.png");
        File nextPhotoImage = new File("src/Assets/right-arrow.png");

        try {

            ImageView prevPhotoImageview = new ImageView(new Image(new FileInputStream(prevPhotoImage.getAbsolutePath())));
            ImageView nextPhotoImageview = new ImageView(new Image(new FileInputStream(nextPhotoImage.getAbsolutePath())));
            prevPhotoImageview.setFitHeight(50);
            prevPhotoImageview.setFitWidth(50);
            nextPhotoImageview.setFitHeight(50);
            nextPhotoImageview.setFitWidth(50);

            previewBox.setAlignment(Pos.BASELINE_CENTER);
            nextBox.setAlignment(Pos.BASELINE_CENTER);

            previewBox.getChildren().add(prevPhotoImageview);
            nextBox.getChildren().add(nextPhotoImageview);

            controlBox.getChildren().addAll(previewBox,nextBox);

            prevPhotoImageview.setOnMouseClicked(e ->{

                photo_index.getAndDecrement();

                if (photo_index.get() < 0) {
                    photo_index.set((photosOnAlbum.size() - 1));
                }

                displayPhoto.getChildren().setAll(loadPhoto(photosOnAlbum, photo_index.get()),controlBox);


            });

            nextPhotoImageview.setOnMouseClicked(e ->{

                if (photo_index.get() > photosOnAlbum.size()-1){
                    photo_index.set(0);
                }
                displayPhoto.getChildren().setAll(loadPhoto(photosOnAlbum, photo_index.get()),controlBox);
                photo_index.getAndIncrement();

            });


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

            displayPhoto.setAlignment(Pos.BASELINE_CENTER);
            displayPhoto.getChildren().addAll(loadPhoto( photosOnAlbum, index),controlBox);

            return displayPhoto;
        }


    public VBox loadPhoto(CircularDoublyLinkedList<Photo> photosOnAlbum, int index) {

        VBox displayPhoto = new VBox();

        HBox photo = new HBox();
        HBox detailsOnPhoto = new HBox();

        File noPhotoImage = new File("src/Assets/muerto.png");

        try {

            if(photosOnAlbum.isEmpty()){

                ImageView noPhotoImageview = new ImageView(new Image(new FileInputStream(noPhotoImage.getAbsolutePath())));
                noPhotoImageview.setFitHeight(500);
                noPhotoImageview.setFitWidth(500);

                Label noPhotosLb = new Label("No Photos!");
                noPhotosLb.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 100px;" + "-fx-text-fill: #006F84;");

                photo.setStyle("-fx-alignment: center;");
                photo.setPadding(new Insets(20, 20, 20, 20));

                photo.getChildren().addAll(noPhotoImageview,noPhotosLb);
                displayPhoto.getChildren().add(photo);

            }else{
                GridPane infoPhoto = new GridPane();
                infoPhoto.setVgap(10);
                infoPhoto.setHgap(10);
                infoPhoto.setPadding(new Insets(10));

                Photo pic = photosOnAlbum.get(index);
                File photoImage = new File(pic.getRoute());


                ImageView photoImageview = new ImageView(new Image(new FileInputStream(photoImage.getAbsolutePath())));
                photoImageview.setFitHeight(500);
                photoImageview.setFitWidth(500);
                photo.getChildren().add(photoImageview);

                Label descriptionPhoto = new Label("Photo description: ");
                descriptionPhoto.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;");

                Label textDescriptionPhoto = new Label(pic.getDescriptionPhoto());
                textDescriptionPhoto.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #000000;");

                Label placePhoto = new Label("Photo's Place: ");
                placePhoto.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;");

                Label textPlacePhoto = new Label(pic.getPlacePhoto());
                textPlacePhoto.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #000000;");

                Label datePhoto = new Label("Date: ");
                datePhoto.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;");

                Label textDatePhoto = new Label(pic.getDatePhoto());
                textDatePhoto.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #000000;");

                Label personsOnAlbum = new Label("Person on photos: ");
                personsOnAlbum.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #006F84;");

                Label textPersonsOnAlbum = new Label(pic.getPersonsOnAlbum().toString());
                textPersonsOnAlbum.setStyle("-fx-font-family: Galdeano;" + "-fx-font-size: 25px;" + "-fx-text-fill: #000000;");


                infoPhoto.add(descriptionPhoto, 0, 0);
                infoPhoto.add(textDescriptionPhoto, 1, 0);
                infoPhoto.add(placePhoto, 0, 1);
                infoPhoto.add(textPlacePhoto, 1, 1);
                infoPhoto.add(datePhoto, 0, 2);
                infoPhoto.add(textDatePhoto, 1, 2);
                infoPhoto.add(personsOnAlbum, 0, 3);
                infoPhoto.add(textPersonsOnAlbum, 1, 3);

                detailsOnPhoto.getChildren().add(infoPhoto);
                displayPhoto.getChildren().addAll(photo, detailsOnPhoto);


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        displayPhoto.setAlignment(Pos.BASELINE_CENTER);

        return displayPhoto;
    }


    public BorderPane getRoot() {
        return root;
    }

}
