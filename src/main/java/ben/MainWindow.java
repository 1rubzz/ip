package ben;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class MainWindow {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Ben ben;

    private final Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/mofu1.png"));

    private final Image benImage =
            new Image(this.getClass().getResourceAsStream("/images/mofu2.png"));

    public void setBen(Ben ben) {
        this.ben = ben;
    }

    @FXML
    public void initialize() {
        scrollPane.setContent(dialogContainer);
        scrollPane.setFitToWidth(true);

        configureLayoutDimensions();
        configureScrollPaneAppearance();
        greetUser();

        dialogContainer.heightProperty().addListener(
                observable -> scrollPane.setVvalue(1.0)
        );
    }

    private void configureLayoutDimensions() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    private void configureScrollPaneAppearance() {
        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: transparent;"
        );
        scrollPane.setOpacity(0.98);
    }

    private void greetUser() {
        dialogContainer.getChildren().add(
                DialogBox.getBenDialog(
                        "Hello! I'm Ben. How can I help you?",
                        benImage
                )
        );
    }

    @FXML
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

        } catch (BenException e) {
            dialogContainer.getChildren().add(
                    DialogBox.getErrorDialog(e.getMessage(), benImage)
            );
        }

        userInput.clear();
    }
}