package com.frostybee.pendulums.controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class of the PendulumsApp's UI.
 *
 * @author frostybee
 */
public class FXMLPendulumsController {

    @FXML
    private Pane animationPane;
    @FXML
    private Spinner spPendulumsNumber;
    @FXML
    private VBox vbSettings;
    @FXML
    Button btnStart;
    @FXML
    Button btnStop;
    @FXML
    Button btnReset;
    private RenderingController renderer;
    private Canvas canvas;

    @FXML
    public void initialize() {
        canvas = new Canvas(1000, 1000);
        renderer = new RenderingController(canvas);
        animationPane.getChildren().add(canvas);
        //canvas.widthProperty().bind(primaryStage.widthProperty());
        //canvas.heightProperty().bind(primaryStage.heightProperty());
        configControls();
    }

    private void configControls() {
        vbSettings.setStyle(
                "-fx-border-color:#424242; -fx-border-width:1px;-fx-background-color:rgba(255, 255, 255, 0.87);");
        animationPane.setStyle("-fx-border-color:#424242; -fx-border-width:1px;-fx-background-color:rgba(5, 5, 5, 0.87);");
        btnStop.setDisable(true);
        btnReset.setDisable(true);
        btnStart.setOnAction((event) -> {
            renderer.render();
            disableSimulationButtons(true, false, false);
        });
        btnStop.setOnAction((event) -> {
            stopAnimation();
            disableSimulationButtons(false, true, true);
        });
        btnReset.setOnAction((event) -> {
            resetSimulation();
        });
        spPendulumsNumber.valueProperty().addListener((observable, oldValue, newValue) -> {
            renderer.setNumberOfPendulums(Integer.parseInt(newValue.toString()));
        });
    }

    private void disableSimulationButtons(boolean start, boolean stop, boolean reset) {
        btnStart.setDisable(start);
        btnStop.setDisable(stop);
        btnReset.setDisable(reset);
    }

    void stopAnimation() {
        renderer.stopAnimation();
    }

    private void resetSimulation() {
        stopAnimation();
        renderer.clearCanvas(canvas.getGraphicsContext2D());
        renderer.initPendulums();
        disableSimulationButtons(false, true, true);
    }
}
