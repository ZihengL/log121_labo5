package ets.log121_labo5;

import ets.log121_labo5.models.command.Command;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        Command.setStage(stage);

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Navigateur d'image");
        stage.setScene(scene);
        stage.show();

        this.test();
    }

    private void test() {
        URL resURL = Application.class.getResource("");

        System.out.println(resURL.getPath());
    }

    public static void main(String[] args) {
        launch();
    }
}