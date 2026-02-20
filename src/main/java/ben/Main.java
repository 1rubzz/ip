package ben;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Ben ben = new Ben();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader =
                    new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            AnchorPane root = loader.load();

            MainWindow controller = loader.getController();
            controller.setBen(ben);

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Ben");
            stage.setResizable(false);
            stage.setMinHeight(600.0);
            stage.setMinWidth(400.0);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}