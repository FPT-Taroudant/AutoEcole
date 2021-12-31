package autoecole.autoecole;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class AutoEcoleApplication extends Application{

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("layout.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add("Style.css");
        stage.setTitle("Auto Ecole");
        stage.setScene(scene);
        stage.getIcons().add(new Image("logo1.png"));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}