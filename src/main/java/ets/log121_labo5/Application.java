package ets.log121_labo5;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Record: Application
 * Created on: 7/6/2025
 * Description: Point d'entrée de l'application.
 *
 * @author liuzi | Zi heng Liu
 */

public class Application extends javafx.application.Application {

    // Nous gardons une référence statique du Stage afin de provisionner
    // un accès simple et universel à divers types d'opérations.
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Application.stage = stage;
//        Command.setStage(stage);

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Navigateur d'image");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}