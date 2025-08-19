
package org.OOSD;



import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.concurrent.Task;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) {
        // Create the splash stage
        Stage splashStage = new Stage(StageStyle.UNDECORATED);  // No window border






        // Course code + Group ID
        Label courseLabel = new Label("Course Code: OOSD2006ICT/2805ICT/3815ICT");
        Label groupLabel = new Label("Group: 50");
        VBox topBox = new VBox(5, courseLabel, groupLabel);
        topBox.setAlignment(Pos.TOP_CENTER);




        Label loadingLabel = new Label("Loading, please wait...");


// Splash image (background)
        ImageView splashImage = new ImageView(
                new Image(getClass().getResource("/splash-image.png").toExternalForm())
        );
        splashImage.setFitWidth(600);
        splashImage.setFitHeight(400);
        splashImage.setPreserveRatio(false);


//Put everything together
        StackPane splashLayout = new StackPane(splashImage);
        splashLayout.getChildren().addAll(topBox, loadingLabel);


        StackPane.setAlignment(topBox, Pos.TOP_CENTER);
        StackPane.setAlignment(loadingLabel, Pos.BOTTOM_CENTER);


//// Create scene (only once)
        Scene splashScene = new Scene(splashLayout, 600, 400);
        splashStage.setScene(splashScene);
        splashStage.setResizable(false);
        splashStage.show();




        // Simulate loading task
        Task<Void> loadTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                // Simulate some work (e.g., 3 seconds)
                Thread.sleep(3000);
                return null;
            }


            @Override
            protected void succeeded() {
                Platform.runLater(() -> {
                    splashStage.close();
                    showMainStage(primaryStage);
                });
            }
        };


        new Thread(loadTask).start();
    }


    private void showMainStage(Stage primaryStage) {
        // Your main application window


        Label mainLabel = new Label("Welcome to MainScreen!");
        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-background-color: blue; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20 10 20;");
        exitButton.setOnAction(e -> Platform.exit());


        VBox layout = new VBox(20, mainLabel, exitButton);
        layout.setAlignment(Pos.CENTER);


        Scene mainScene = new Scene(layout, 600, 400);
        primaryStage.setTitle("MainScreen");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
