package org.frostybee.pendulums;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author frostybee
 */
public class RenderingController {

    private Pane drawingPane;
    private List<Pendulum> pendulums;
    private List<Color> bobColors;
    private AnimationTimer timer;
    /*
     * Note that in this simulation, the quantities are not necessarily in SI units or to scale.
     * This can be improved in future commits, but as of now, it's not the focus of this simple project.
     **/
    private final double gravity = 9.8; // acceleration due to gravity.
    private final double deltaTime = 0.5; // shortest time interval

    private int numberOfPendulums = 40; // number of pendulums
    private int frequency = 25; // frequency of the longest pendulum
    private double maxLength = 450; // length of the longest pendulum
    private double theta = -Math.PI / 8; // initial vertical angle
    private double bobDiameter = 10; // diameter of each bob

    // Origin coordinates where the pivots of the pendulums will be drawn.
    private int originX = 200;
    private int originY = -250;

    // originY is negative so that more pendulum bobs can be actually drawn inside the panel.
    // We are not interested in seeing the suspension point and the strings(the physics ones).
    public RenderingController(Pane drawingPane) {
        this.drawingPane = drawingPane;
        pendulums = new ArrayList<>();
        bobColors = new ArrayList<>();
        initPendulums();
    }

    private void initPendulums() {                        
        bobColors.add(Color.rgb(43, 130, 201));
        bobColors.add(Color.rgb(44, 202, 144));
        bobColors.add(Color.rgb(245, 238, 158));
        bobColors.add(Color.rgb(255, 238, 136));
        bobColors.add(Color.rgb(251, 185, 65));
        bobColors.add(Color.rgb(251, 96, 66));
        bobColors.add(Color.rgb(216, 17, 89));       

        for (int i = 0; i < numberOfPendulums; i++) {
            Color color = bobColors.get(i % bobColors.size());
            /*
             * Each successive pendulum performs more oscillations than the previous pendulum.
             * Length of a pendulum is inversely proportional to the square of its frequency.
             * */
            double length = maxLength * 625f / ((frequency + i * 0.3) * (frequency + i * 0.3));
            pendulums.add(new Pendulum(length, theta, bobDiameter, color));
        }
    }

    public void render(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setImageSmoothing(true);
        originX = (int) canvas.getWidth() / 2;        
        // init the update timer
        timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                double scale = 2.0;
                if (now - lastUpdate >= 25000000) {
                    lastUpdate = now;
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    for (Pendulum p : pendulums) {
                        p.update(gravity, deltaTime);
                        for (Pendulum pendulum : pendulums) {
                            pendulum.draw(gc, originX, originY, scale);
                        }
                    }
                }
            }
        };
        timer.start();
    }

    void stopAnimation() {
        if (timer != null) {
            timer.stop();
            System.out.println("Stopping the timer...");
        }
    }

}
