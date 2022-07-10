package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import user.Session;
import validator.EmailValidator;
import validator.ValidatorData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicReference;

public class MainPage {
    private BorderPane root;
    Image background;
    Insets basicInsets = new Insets(10);
    String labelTitle = "-fx-text-fill: #006F84;" +
            "-fx-text-alignment: center;" +
            "-fx-font-family: Galdeano;" +
            "-fx-font-size: 70px;";
    String labelSubTitle = "-fx-text-fill: #006F84;" +
            "-fx-text-alignment: center;" +
            "-fx-font-family: Galdeano;" +
            "-fx-font-size: 35px;";
    String labelSquare = "-fx-text-fill: #006F84;" +
            "-fx-text-alignment: center;" +
            "-fx-font-family: Galdeano;" +
            "-fx-font-size: 35px;";
    String buttonStyle = "-fx-text-fill: #FFFFFF;" +
            "-fx-background-color: #C24242;" +
            "-fx-text-alignment: center;" +
            "-fx-font-family: Galdeano;" +
            "-fx-font-size: 35px;";

    public MainPage() {
        root = new BorderPane();
        try {
            background = new Image(new FileInputStream("PoliPicasa/src/Assets/background.jpg"));
            root.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundImage(background, javafx.scene.layout.BackgroundRepeat.NO_REPEAT, javafx.scene.layout.BackgroundRepeat.NO_REPEAT, javafx.scene.layout.BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            HBox head = createHeader();
            VBox menu = createMenu();
            root.setTop(head);
            root.setCenter(menu);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public HBox createHeader() {
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(10, 10, 10, 10));
        header.setSpacing(10);
        try {
            Image logo = new Image(new FileInputStream("PoliPicasa/src/Assets/polito_logo.png"));
            ImageView logoView = new ImageView(logo);
            logoView.setStyle("-fx-preserveRatio: true; -fx-fit-height: 100px;");
            Label title = new Label("Welcome to PoliPicasa!");
            title.setStyle("-fx-text-fill: white;" +
                    "-fx-alignment: center;" +
                    "-fx-font-family: Galdeano;" +
                    "-fx-font-size: 70px;");
            header.getChildren().addAll(logoView, title);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return header;
    }

    private VBox createMenu() {
        String styleButton = "-fx-text-fill: #006F84;" +
                "-fx-text-alignment: center;" +
                "-fx-border-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #006F84;" +
                "-fx-border-width: 5px;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-font-family: Galdeano;" +
                "-fx-font-size: 35px;";
        VBox menu = new VBox();
        menu.setPadding(basicInsets);
        menu.setSpacing(10);
        menu.setAlignment(Pos.CENTER);
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            sessionLogin();
        });

        loginButton.setStyle(styleButton);
        Button signinButton = new Button("Sign In");
        signinButton.setStyle(styleButton);
        signinButton.setOnAction(e -> {
            accountSignIn();
        });
        menu.getChildren().addAll(loginButton, signinButton);
        return menu;
    }

    private void sessionLogin() {
        VBox login = new VBox();
        login.setPadding(basicInsets);
        login.setSpacing(10);
        login.setAlignment(Pos.CENTER);
        HBox usernameBox = new HBox();
        HBox passwordBox = new HBox();
        usernameBox.setPadding(basicInsets);
        passwordBox.setPadding(basicInsets);
        usernameBox.setSpacing(10);
        passwordBox.setSpacing(10);
        usernameBox.setAlignment(Pos.CENTER);
        passwordBox.setAlignment(Pos.CENTER);
        Label loginLabel = new Label("Login");
        loginLabel.setStyle(labelTitle);
        Label usernameLabel = new Label("Email:      ");
        usernameLabel.setStyle(labelSubTitle);
        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle(labelSubTitle);
        TextField username = new TextField();
        Label error = new Label();
        username.setStyle(labelSquare);
        PasswordField password = new PasswordField();
        password.setStyle(labelSquare);
        Button loginButton = new Button("Login");
        loginButton.setStyle(buttonStyle);
        loginButton.setOnAction(e -> {
            if (username.getText() != null && password.getText() != null) {
                Session s = ValidatorData.createSession(username.getText(), password.getText());
                if (s != null) {
                    //sesion creada
                    login.getChildren().clear();
                    login.getChildren().add(new Label("Session created"));
                } else {
                    error.setText("Invalid username or password");
                }
            } else {
                error.setText("Invalid username or password");
            }
        });
        usernameBox.getChildren().addAll(usernameLabel, username);
        passwordBox.getChildren().addAll(passwordLabel, password);
        login.getChildren().addAll(loginLabel, usernameBox, passwordBox, error, loginButton);
        root.setCenter(login);

    }

    private void accountSignIn() {
        AtomicReference<String> codeEmail = null;
        VBox signin = new VBox();
        GridPane grid = new GridPane();
        signin.setPadding(basicInsets);
        signin.setSpacing(10);
        signin.setAlignment(Pos.CENTER);
        Label signinLabel = new Label("Sign In");
        signinLabel.setStyle(labelTitle);
        Label nameLabel = new Label("Name:   ");
        nameLabel.setStyle(labelSubTitle);
        TextField name = new TextField();
        name.setStyle(labelSquare);
        Label emailLabel = new Label("Email:   ");
        emailLabel.setStyle(labelSubTitle);
        TextField email = new TextField();
        email.setStyle(labelSquare);
        Button emailButton = new Button("Check");
        Label errorEmail = new Label();
        Label codeLabel = new Label("Code:  ");
        codeLabel.setStyle(labelSubTitle);
        TextField code = new TextField();
        code.setStyle(labelSquare);
        Button confirmButton = new Button("Confirm");
        confirmButton.setDisable(true);
        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle(labelSubTitle);
        PasswordField password = new PasswordField();
        password.setStyle(labelSquare);
        emailButton.setOnAction(e -> {
            if (email.getText() != null) {
                if (ValidatorData.existEmail(email.getText())) {
                    errorEmail.setText("Email already exists");
                } else {
                    errorEmail.setText("Code sent to email");
                    confirmButton.setDisable(false);
                    EmailValidator ev = new EmailValidator(email.getText());
                    codeEmail.set(ev.getCode());
                }
            }
        });
        Label error = new Label();
        password.setStyle(labelSquare);
        email.setStyle(labelSquare);
        Button signinButton = new Button("Sign In");
        signinButton.setDisable(true);
        confirmButton.setOnAction(e -> {
            if (code.getText() != null || codeEmail.equals(code.getText())) {
                signinButton.setDisable(false);
            }else{
                error.setText("Invalid code");
            }

        });

        signinButton.setStyle(buttonStyle);
        grid.add(nameLabel, 0, 0);
        grid.add(name, 1, 0);
        grid.add(emailLabel, 0, 1);
        grid.add(email, 1, 1);
        grid.add(emailButton, 2, 1);
        grid.add(errorEmail, 2, 2);
        grid.add(codeLabel, 0, 3);
        grid.add(code, 1, 3);
        grid.add(confirmButton, 2, 3);
        grid.add(passwordLabel, 0, 4);
        grid.add(password, 1, 4);
        grid.add(error, 1, 5);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        signin.getChildren().addAll(signinLabel, grid, signinButton);
        root.setCenter(signin);
    }

    public BorderPane getRoot() {
        return root;
    }
}
