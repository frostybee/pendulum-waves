package com.frostybee.pendulums.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bob {

    private double x;
    private double y;
    private double diameter;
    private Color color;

    public Bob(double x, double y, double diamter, Color color) {
        this.x = x;
        this.y = y;
        this.diameter = diamter + 5;
        this.color = color;
    }

    public void draw(GraphicsContext gc, int ox, int oy, double scale) {
        gc.setFill(color);
        gc.setStroke(Color.BLACK);
        gc.fillOval(ox + (int) ((x - diameter / 2) * scale),
                oy + (int) ((y - diameter / 2) * scale),
                (int) (diameter * scale), (int) (diameter * scale));
        gc.strokeOval(ox + (int) ((x - diameter / 2) * scale),
                oy + (int) ((y - diameter / 2) * scale),
                (int) (diameter * scale), (int) (diameter * scale));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
