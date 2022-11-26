package com.example.rappi_ando.model;

import com.example.rappi_ando.HelloApplication;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class RappiandoController implements Initializable {

    private static final RappiandoController instance = new RappiandoController();
    public RappiandoController(){}

    public Graph getGraph() {
        return graph;
    }

    private Graph graph = Graph.getInstance();

    @FXML
    private ToggleButton addEdgeSBTN;

    @FXML
    private ToggleButton addNodeTBTN;

    @FXML
    private ToggleButton deleteEdgeTBTN;

    @FXML
    private ToggleButton deleteNodeTBTN;

    @FXML
    private AnchorPane paneAP;

    @FXML
    private ScrollPane mapSP;

    @FXML
    private ToggleButton primTBTN;

    private DoubleProperty endX;
    private DoubleProperty endY;
    private Line newEdge ;
    private int nodesCounter=1;

    private static class Delta { double x, y; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        graph.loadData("Data");
        nodesCounter=graph.getNodes().size();
        showGraph();
    }
    void showGraph(){
        AnchorPane tempPane = new AnchorPane();
        tempPane.setPrefWidth(820);
        tempPane.setPrefHeight(1420);

        tempPane.setOnMouseClicked(mouseEvent ->{
            if(addNodeTBTN.isSelected()) {
                tempPane.getChildren().clear();
                graph.addNode(mouseEvent.getX() ,mouseEvent.getY(),nodesCounter+"");
                addNodeTBTN.setSelected(false);
                getGraphPane(tempPane);
                nodesCounter=nodesCounter+1;
            }else {
                tempPane.getChildren().clear();
                getGraphPane(tempPane);
            }
        });
        System.out.println("chao");
        paneAP.getChildren().add(tempPane);
    }
    @FXML
    void deleteEdge(ActionEvent event) {

    }

    @FXML
    void deleteNode(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Deleting Node");
        alert.setHeaderText("Click the node you want to remove");
        alert.showAndWait();
    }

    @FXML
    void dijkstra(ActionEvent event) {

    }

    @FXML
    void prim(ActionEvent event) {

    }
    @FXML
    void addEdge(ActionEvent event) {
        HelloApplication.showTransparentWindow("addEdge.fxml");
    }

    @FXML
    void addNode(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Adding Node");
        alert.setHeaderText("Click on where you want to place the new node");
        alert.showAndWait();
    }

    void getGraphPane(AnchorPane tempPane){
        ArrayList<Edge> edgeArrayList = new ArrayList<>();
        graph.getAdj().copyEdge(edgeArrayList);

        for(Edge e : edgeArrayList){
            tempPane.getChildren().addAll(e.getLine(),e.getText());
        }
        for(int i=0;i<graph.getNodes().size();i++){
            tempPane.getChildren().add(graph.getNodes().get(i).getCircle());
            tempPane.getChildren().add(graph.getNodes().get(i).getText());
            enableDrag(tempPane, graph.getNodes().get(i).getCircle(), graph.getNodes().get(i));

        }
        graph.saveData("Data");
    }
    private void enableDrag(AnchorPane tempPane,final Circle circle,Node node) {
        final Delta dragDelta = new Delta();
        final String[] source = new String[1];
        circle.setOnMousePressed(mouseEvent -> {
            // record a delta distance for the drag and drop operation.
            if(!addNodeTBTN.isSelected() && !addEdgeSBTN.isSelected() && !deleteNodeTBTN.isSelected() && !deleteEdgeTBTN.isSelected()) {
                dragDelta.x = circle.getCenterX() - mouseEvent.getX();
                dragDelta.y = circle.getCenterY() - mouseEvent.getY();
                circle.getScene().setCursor(Cursor.MOVE);
            }
            else if(addEdgeSBTN.isSelected()){
                source[0] = node.name;
                newEdge = new Line();
                newEdge.setStartX(mouseEvent.getX());
                newEdge.setStartY(mouseEvent.getY());
                endX = new SimpleDoubleProperty(mouseEvent.getX());
                endY = new SimpleDoubleProperty(mouseEvent.getY());
                newEdge.endXProperty().bind(endX);
                newEdge.endYProperty().bind(endY);
                tempPane.getChildren().add(newEdge);
            }
            else if(deleteNodeTBTN.isSelected()){
                graph.DeleteNode(node.name);
                deleteNodeTBTN.setSelected(false);
                tempPane.getChildren().clear();
                getGraphPane(tempPane);
            }
        });
        circle.setOnMouseReleased(mouseEvent -> {
            if(!deleteNodeTBTN.isSelected()&& circle.getScene() != null){
                circle.getScene().setCursor(Cursor.HAND);
            }
        });
        circle.setOnMouseDragged(mouseEvent -> {
            if(!addNodeTBTN.isSelected() && !addEdgeSBTN.isSelected() && !deleteNodeTBTN.isSelected() && !deleteEdgeTBTN.isSelected()) {
                circle.setCenterX(mouseEvent.getX() + dragDelta.x);
                circle.setCenterY(mouseEvent.getY() + dragDelta.y);
                node.x = (mouseEvent.getX() + dragDelta.x);
                node.y = (mouseEvent.getY() + dragDelta.y);
                node.getText();
            }
            if(addEdgeSBTN.isSelected()){
                endX.set(mouseEvent.getX());
                endY.set(mouseEvent.getY());
            }

        });
        circle.setOnMouseEntered(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                circle.getScene().setCursor(Cursor.HAND);
            }
        });
        circle.setOnMouseExited(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                circle.getScene().setCursor(Cursor.DEFAULT);
            }
        });

    }
    private boolean isInside(double x1, double y1, Node node){
        double distance;
        distance = Math.sqrt((x1-(node.x))*(x1-(node.x))+(y1-(node.y))*(y1-(node.y)));
        return distance < 10;
    }


    }
