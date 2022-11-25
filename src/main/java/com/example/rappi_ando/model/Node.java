package com.example.rappi_ando.model;

import java.util.LinkedList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

class Node {
    public double x;
    public double y;
    public String name;
    private boolean visited;
    private Circle circle = new Circle(10.0);
    private Text text;
    LinkedList<Edge> edges;

    Node(double x, double y, String name) {
        this.circle.setFill(Color.GAINSBORO);
        this.circle.setStroke(Color.CORNFLOWERBLUE);
        this.text = new Text(name);
        this.x = x;
        this.y = y;
        this.name = name;
        this.visited = false;
        this.edges = new LinkedList();
    }

    Circle getCircle(double scale) {
        this.circle.setCenterX(this.x * scale);
        this.circle.setCenterY(this.y * scale);
        return this.circle;
    }

    Circle getCircle() {
        return this.circle;
    }

    Text getText() {
        this.text.layoutXProperty().bind(this.circle.centerXProperty().add(-this.text.getLayoutBounds().getWidth() / 2.0));
        this.text.layoutYProperty().bind(this.circle.centerYProperty().add(5));
        return this.text;
    }

    boolean isVisited() {
        return this.visited;
    }

    void visit() {
        this.visited = true;
    }

    void unvisited() {
        this.visited = false;
    }
}