package app;

import ben.Ben;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A GUI for Ben using JavaFX.
 */
public class Main extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/mofu1.png"));
    private Image benImage =
            new Image(this.getClass().getResourceAsStream("/images/mofu2.png"));

    private Ben ben = new Ben();

    @Override
    public void start(Stage stage) {
        AnchorPane mainLayout = initialiseUiComponents();
        configureStage(stage);
        configurerScrollPaneAppearance();
        greetUser();
        configureUserInputHandlers();
        mainLayout.setPrefSize(400.0, 600.0);
        configureLayoutDimensions();
        configureComponentAnchors();
        enableAutoScroll();
    }

    private void enableAutoScroll() {
        // Auto scroll
        dialogContainer.heightProperty().addListener(
                observable -> scrollPane.setVvalue(1.0)
        );
    }

    private void configureComponentAnchors() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private void configureLayoutDimensions() {
        scrollPane.setPrefSize(385.0, 535.0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
    }

    private void configureUserInputHandlers() {
        sendButton.setOnMouseClicked(event -> handleUserInput());
        userInput.setOnAction(event -> handleUserInput());
    }

    private void greetUser() {
        dialogContainer.getChildren().add(
                DialogBox.getBenDialog(
                        "Hello! I'm Ben. How can I help you?",
                        benImage
                )
        );
    }

    private void configurerScrollPaneAppearance() {
        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: transparent;"
        );
        scrollPane.setOpacity(0.98); // small tweak to remove white bleed
    }

    private void configureStage(Stage stage) {
        stage.setScene(scene);
        stage.setTitle("Ben");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.show();
    }

    private AnchorPane initialiseUiComponents() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();

        // scrollPane and dialogueContainer
        scrollPane.setStyle("-fx-background-color: transparent;");
        dialogContainer.setStyle("-fx-background-color: transparent;");

        scrollPane.setContent(dialogContainer);
        scrollPane.setFitToWidth(true);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();

        // Background image applied to root
        mainLayout.setStyle(
                "-fx-background-image: url('/images/background.png');" +
                        "-fx-background-size: cover;" +
                        "-fx-background-position: center center;"
        );

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        return mainLayout;
    }

    private void handleUserInput() {
        String userText = userInput.getText();

        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(userText, userImage)
        );

        try {
            String benText = ben.getResponse(userText);

            dialogContainer.getChildren().add(
                    DialogBox.getBenDialog(benText, benImage)
            );

        } catch (Exception e) {

            dialogContainer.getChildren().add(
                    DialogBox.getErrorDialog(e.getMessage(), benImage)
            );
        }

        userInput.clear();
    }
}
