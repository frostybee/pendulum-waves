package org.frostybee.pendulums;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * A simple Object Oriented Pendulum Waves simulation. Based on a Pendulum Waves
 * Demonstration at Harvard University -
 * https://sciencedemonstrations.fas.harvard.edu/presentations/pendulum-waves
 *
 * @author frostybee
 */
public class DancingPendulums extends Application {

    private RenderingController renderer;
    private Canvas canvas = new Canvas(1000, 1000);
    private Pane root = new Pane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        renderer.stopAnimation();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        // TODO: Make FXML --> Add settings:
        // - Chose the number of pendulums.
        // - Stop/Start. 
        // - Change the speed. 
        // - Add a timer to show the current running time. 
        root.getChildren().add(canvas);
        root.setBackground(Background.fill(Color.BLUE));
        renderer = new RenderingController(root);
        renderer.render(canvas);
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle("Pendulums Animation");
        primaryStage.show();
    }

}
