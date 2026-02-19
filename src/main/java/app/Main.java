package app;

import ben.Ben;
import javafx.application.Application;
import javafx.geometry.Pos;
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
    private Image dukeImage =
            new Image(this.getClass().getResourceAsStream("/images/mofu2.png"));

    private Ben ben = new Ben();

    @Override
    public void start(Stage stage) {

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();

        // Transparent scrollpane + content
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
        stage.setScene(scene);
        stage.setTitle("Ben");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.show();

        // REMOVE ScrollPane white viewport background (proper way)
        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: transparent;"
        );
        scrollPane.setOpacity(0.98); // small tweak to remove white bleed

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(
                        "Hello! I'm Ben. How can I help you?",
                        dukeImage
                )
        );

        sendButton.setOnMouseClicked(event -> handleUserInput());
        userInput.setOnAction(event -> handleUserInput());

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385.0, 535.0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Auto scroll
        dialogContainer.heightProperty().addListener(
                observable -> scrollPane.setVvalue(1.0)
        );
    }

    private void handleUserInput() {
        String userText = userInput.getText();

        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(userText, userImage)
        );

        try {
            String dukeText = ben.getResponse(userText);

            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(dukeText, dukeImage)
            );

        } catch (Exception e) {

            dialogContainer.getChildren().add(
                    DialogBox.getErrorDialog(e.getMessage(), dukeImage)
            );
        }

        userInput.clear();
    }
}
