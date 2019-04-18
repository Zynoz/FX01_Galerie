package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main  extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public static void alert(String message) {
        System.out.println(message);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        RootBorderPane rootBorderPane = new RootBorderPane();
        Scene scene = new Scene(rootBorderPane, 565, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
