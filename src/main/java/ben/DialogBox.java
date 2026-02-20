package ben;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box containing text and an image.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Creates a dialog box with given text and image.
     *
     * @param s The text to display.
     * @param i The image to display.
     */
    public DialogBox(String s, Image i) {
        text = new Label(s);
        displayPicture = new ImageView(i);

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);

        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp =
                FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    private void applyErrorStyle() {
        this.setStyle(
                "-fx-background-color: #ffe6e6;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10;"
        );
        text.setStyle(
                "-fx-text-fill: #8b0000;" +
                        "-fx-font-weight: bold;"
        );
    }

    /**
     * Creates and returns a DialogBox styled to represent an error message.
     * The dialog is flipped to align on the left and styled with
     * a red background and bold red text.
     *
     * @param s The error message to display.
     * @param i The image associated with the error dialog.
     * @return A DialogBox configured with error styling.
     */
    public static DialogBox getErrorDialog(String s, Image i) {
        DialogBox db = new DialogBox(s, i);
        db.flip();
        db.applyErrorStyle();
        return db;
    }

    /**
     * Creates and returns a DialogBox representing a user message.
     * The dialog is aligned to the right with default styling.
     *
     * @param s The user's message to display.
     * @param i The image associated with the user.
     * @return A DialogBox configured for user messages.
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Creates and returns a DialogBox representing a Ben (bot) message.
     * The dialog is flipped to align on the left.
     *
     * @param s The bot message to display.
     * @param i The image associated with Ben.
     * @return A DialogBox configured for bot messages.
     */
    public static DialogBox getBenDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}
