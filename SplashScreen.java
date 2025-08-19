package org.OOSD;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashScreen {

    public void show(Stage primaryStage) {
        Stage splashStage = new Stage(StageStyle.UNDECORATED);

        // Labels
        Label courseLabel = new Label("Course Code: OOSD2006ICT/2805ICT/3815ICT");
        Label groupLabel = new Label("Group: 50");
        VBox topBox = new VBox(5, courseLabel, groupLabel);
        topBox.setAlignment(Pos.TOP_CENTER);

        Label loadingLabel = new Label("Loading, please wait...");

        // Splash Image
        ImageView splashImage = new ImageView(
                new Image(getClass().getResource("/splash-image.png").toExternalForm())
        );
        splashImage.setFitWidth(600);
        splashImage.setFitHeight(400);
        splashImage.setPreserveRatio(false);

        // Layout
        StackPane splashLayout = new StackPane(splashImage);
        splashLayout.getChildren().addAll(topBox, loadingLabel);
        StackPane.setAlignment(topBox, Pos.TOP_CENTER);
        StackPane.setAlignment(loadingLabel, Pos.BOTTOM_CENTER);

        Scene splashScene = new Scene(splashLayout, 600, 400);
        splashStage.setScene(splashScene);
        splashStage.setResizable(false);
        splashStage.show();

        // Simulate loading
        Task<Void> loadTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                Thread.sleep(3000); // Simulate 3 seconds of loading
                return null;
            }

            @Override
            protected void succeeded() {
                Platform.runLater(() -> {
                    splashStage.close();
                    new MainScreen().show(primaryStage); // Show main screen after splash
                });
            }
        };

        new Thread(loadTask).start();
    }
}
