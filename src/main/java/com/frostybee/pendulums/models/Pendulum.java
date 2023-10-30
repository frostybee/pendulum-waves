package com.frostybee.pendulums.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author frostybee
 */
public class Pendulum {

    private double length;
    private Bob bob;
    private double theta; // the angle which the bob makes with the vertical
    private double velocity; // velocity at which the bob is currently moving
    private double timeElapsed; // total time elapsed since this pendulum

    public Pendulum(double length, double theta, double bobDiameter, Color bobColor) {
        this.length = length;
        this.theta = theta;
        this.timeElapsed = 0;
        this.bob = new Bob(length * Math.sin(theta), length * Math.cos(theta), bobDiameter, bobColor);
    }

    public void draw(GraphicsContext gc, int originX, int originY, double scale) {
        gc.setFill(Color.YELLOW);
        gc.setStroke(Color.SILVER);
        gc.strokeLine(originX, originY, originX + (int) (bob.getX() * scale), originY + (int) (bob.getY() * scale));
        bob.draw(gc, originX, originY, scale);
    }

    public void update(double gravity, double deltaTime) {
        double angle = -gravity * Math.sin(theta) / length;
        velocity += angle * deltaTime;
        theta += velocity * deltaTime;
        timeElapsed = (timeElapsed + deltaTime);
        bob.setX(length * Math.sin(theta));
        bob.setY(length * Math.cos(theta));
    }
}
