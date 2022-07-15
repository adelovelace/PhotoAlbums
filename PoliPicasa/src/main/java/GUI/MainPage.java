package GUI;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import user.Session;
import user.User;
import validator.EmailValidator;
import validator.ValidatorData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicReference;

public class MainPage {
    private StackPane pane;
    private BorderPane root;
    Image background;
    public MainPage() {

        File fileBackground = new File("src/Assets/background.jpg");
        pane = new StackPane();

        root = new BorderPane();
        try {
            background = new Image(new FileInputStream(fileBackground.getAbsolutePath()));
            root.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundImage(background, javafx.scene.layout.BackgroundRepeat.NO_REPEAT, javafx.scene.layout.BackgroundRepeat.NO_REPEAT, javafx.scene.layout.BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

            HBox head = createHeader();
            createMenu();
            root.setTop(head);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        pane.getChildren().add(root);
    }

    public HBox createHeader() {
        File fileLogo = new File("src/Assets/polito_logo.png");
        HBox header = new HBox();
        Styles.setHBoxStyle(header);
        try {
            Image logo = new Image(new FileInputStream(fileLogo.getAbsolutePath()));
            ImageView logoView = new ImageView(logo);
            logoView.setStyle("-fx-preserveRatio: true; -fx-fit-height: 100px;");
            Label title = new Label("Welcome to PoliPicasa!");
            Styles.setTitleStyle(title);
            header.getChildren().addAll(logoView, title);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return header;
    }

    private void createMenu() {

        VBox menu = new VBox();
        Styles.setVBoxStyle(menu);
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            sessionLogin();
        });
        Button signinButton = new Button("Sign In");
        Styles.setStyleButtonMain(loginButton);
        Styles.setStyleButtonMain(signinButton);
        signinButton.setOnAction(e -> {
            accountSignIn();
        });
        menu.getChildren().addAll(loginButton, signinButton);
        root.setCenter(menu);
    }

    private void sessionLogin() {
        GridPane login = new GridPane();
        Styles.setGridPaneStyle(login);
        Label loginLabel = new Label("Login");
        Styles.setTitleStyle(loginLabel);
        Label usernameLabel = new Label("Email:");
        Styles.setSubTitleStyle(usernameLabel);
        Label passwordLabel = new Label("Password:");
        Styles.setSubTitleStyle(passwordLabel);
        TextField username = new TextField();
        Label error = new Label();
        Styles.setSquareStyle(username);
        PasswordField password = new PasswordField();
        Styles.setSquareStyle(password);
        Button loginButton = new Button("Login");
        Button backButton = new Button("Back");
        Styles.setButtonRedStyle(loginButton);
        Styles.setButtonRedStyle(backButton);
        backButton.setOnAction(e -> {
            createMenu();
        });
        loginButton.setOnAction(e -> {
            if (username.getText() != null && password.getText() != null) {
                Session s = ValidatorData.createSession(username.getText(), password.getText());
                if (s != null) {
                    //sesion creada
                    login.getChildren().clear();
                    GaleryPage gallery = new GaleryPage(s);
                    root = gallery.getRoot();
                    pane.getChildren().clear();
                    pane.getChildren().add(root);
                } else {
                    error.setText("Invalid username or password");
                }
            } else {
                error.setText("Invalid username or password");
            }
        });
        login.add(loginLabel, 0, 0);
        login.add(usernameLabel, 0, 1);
        login.add(username, 1, 1);
        login.add(passwordLabel, 0, 2);
        login.add(password, 1, 2);
        login.add(loginButton, 1, 3);
        login.add(backButton, 0, 3);
        login.add(error, 1, 4);
        root.setCenter(login);

    }

    private void accountSignIn() {
        AtomicReference<String> codeEmail = new AtomicReference<>();
        VBox signin = new VBox();
        GridPane grid = new GridPane();
        Styles.setVBoxStyle(signin);
        Label signinLabel = new Label("Sign In");
        Styles.setTitleStyle(signinLabel);
        Label nameLabel = new Label("Name:");
        Styles.setSubTitleStyle(nameLabel);
        TextField name = new TextField();
        Styles.setSquareStyle(name);
        Label emailLabel = new Label("Email:");
        Styles.setSubTitleStyle(emailLabel);
        TextField email = new TextField();
        Styles.setSquareStyle(email);
        Button emailButton = new Button("Check");
        Styles.setStyleButtonMain(emailButton);
        Label errorEmail = new Label();
        Label codeLabel = new Label("Code:");
        Styles.setSubTitleStyle(codeLabel);
        TextField code = new TextField();
        Styles.setSquareStyle(code);
        Button confirmButton = new Button("Confirm");
        confirmButton.setDisable(true);
        Styles.setStyleButtonMain(confirmButton);
        Label passwordLabel = new Label("Password:");
        Styles.setSubTitleStyle(passwordLabel);
        PasswordField password = new PasswordField();
        Styles.setSquareStyle(password);
        emailButton.setOnAction(e -> {
            if (email.getText() != null) {
                if (ValidatorData.existEmail(email.getText())) {
                    errorEmail.setText("Email already exists");
                } else {
                    confirmButton.setDisable(false);
                    EmailValidator ev = new EmailValidator(email.getText());
                    String codeGen = ev.getCode();
                    System.out.println(codeGen);
                    codeEmail.set(codeGen);
                    System.out.println("prueba: "+codeEmail.get());
                    errorEmail.setText("outlook y gmail bloquearon apps poco seguras  :code: "+codeGen);
                }
            }
        });
        HBox buttonBox = new HBox();
        Label error = new Label();
        Styles.setHBoxStyle(buttonBox);
        Button signinButton = new Button("Sign In");
        signinButton.setDisable(true);
        Button backButton = new Button("Back");
        buttonBox.getChildren().addAll(backButton, signinButton);
        Styles.setButtonRedStyle(signinButton);
        Styles.setButtonRedStyle(backButton);
        backButton.setOnAction(e -> {
            createMenu();
        });

        confirmButton.setOnAction(e -> {
            if (code.getText() != null && codeEmail.get().equals(code.getText())) {
                signinButton.setDisable(false);
            }else{
                error.setText("Invalid code");
            }

        });
        signinButton.setOnAction(e -> {
            if (name.getText() != null && email.getText() != null && password.getText() != null) {
                User u = new User(name.getText(), email.getText(), password.getText());
                ValidatorData.addUser(u);

                createMenu();
            } else {
                error.setText("Invalid username or password");
            }
        });
        grid.add(nameLabel, 0, 0);
        grid.add(name, 1, 0);
        grid.add(emailLabel, 0, 1);
        grid.add(email, 1, 1);
        grid.add(emailButton, 2, 1);
        grid.add(errorEmail, 1, 2);
        grid.add(codeLabel, 0, 3);
        grid.add(code, 1, 3);
        grid.add(confirmButton, 2, 3);
        grid.add(passwordLabel, 0, 4);
        grid.add(password, 1, 4);
        grid.add(error, 1, 5);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        signin.getChildren().addAll(signinLabel, grid, buttonBox);
        root.setCenter(signin);
    }

    public BorderPane getRoot() {
        return root;
    }
    public Pane getPane(){
        return pane;
    }
}
