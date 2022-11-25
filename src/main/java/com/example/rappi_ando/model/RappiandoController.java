package com.example.rappi_ando.model;

import com.example.rappi_ando.HelloApplication;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class RappiandoController implements Initializable {
    private Graph graph=new Graph();

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
    private Pane pane2;

    @FXML
    private ScrollPane mapSP;

    @FXML
    private ToggleButton primTBTN;

    private DoubleProperty endX;
    private DoubleProperty endY;
    private Line newEdge ;
    private int nodesCounter=0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showGraph();
    }
    void showGraph(){
        AnchorPane tempPane = new AnchorPane();
        tempPane.setPrefWidth(820);
        tempPane.setPrefHeight(1420);

        tempPane.setOnMouseClicked(mouseEvent ->{
            if(addNodeTBTN.isSelected()) {
                tempPane.getChildren().clear();
                graph.addNode(mouseEvent.getX() ,mouseEvent.getY(),nodesCounter+1+"");
                addNodeTBTN.setSelected(false);
            }
        });
        System.out.println("chao");
        paneAP.getChildren().add(tempPane);
    }

    private static class Delta { double x, y; }
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

    void getGraphPane(Pane pane, double scale,boolean enableTouch){
        AnchorPane tempPane = new AnchorPane();
        tempPane.setPrefWidth(820);
        tempPane.setPrefHeight(1420);

        ArrayList<Edge> edgeArrayList = new ArrayList<>();
        graph.getAdj().copyEdge(edgeArrayList);
        /*
        for(Edge e : edgeArrayList){
            paneAP.getChildren().addAll(e.getLine(),e.getText());
            if(enableTouch) {
                enableEdgeDelete(pane, e.getLine(), e, scale);
            }
        }

        */
        for(int i=0;i<graph.getNodes().size();i++){
            tempPane.getChildren().add(graph.getNodes().get(i).getCircle(scale));
            //paneAP.getChildren().add(graph.getNodes().get(i).getText());
            /*
            if(enableTouch) {
                enableDrag(pane, graph.getNodes().get(i).getCircle(scale), graph.getNodes().get(i), scale);
            }

             */
        }
        paneAP.getChildren().add(tempPane);
    }
    private boolean isInside(double x1, double y1, Node node,double scale){
        double distance;
        distance = Math.sqrt((x1-(node.x*scale))*(x1-(node.x*scale))+(y1-(node.y*scale))*(y1-(node.y*scale)));
        return distance < 10;
    }

    @FXML
    void deleteEdge(ActionEvent event) {

    }

    @FXML
    void deleteNode(ActionEvent event) {

    }

    @FXML
    void dijkstra(ActionEvent event) {

    }

    @FXML
    void prim(ActionEvent event) {

    }


    }
