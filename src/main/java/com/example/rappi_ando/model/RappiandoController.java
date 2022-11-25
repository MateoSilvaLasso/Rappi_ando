package com.example.rappi_ando.model;

import com.example.rappi_ando.HelloApplication;
import com.example.rappi_ando.model.Edge;
import com.example.rappi_ando.model.Graph;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RappiandoController implements Initializable {
    private Graph graph;

    @FXML
    private Button addEdgeBTN;

    @FXML
    private Button addNodeBTN;

    @FXML
    private Button deleteEdgeBTN;

    @FXML
    private Button deleteNodeBTN;

    @FXML
    private Button dijkstraBTN;

    @FXML
    private Button primBTN;

    @FXML
    private ScrollPane mapSP;

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
        ScrollPane tem = new ScrollPane();
        mapSP.setOnMousePressed(mouseEvent -> System.out.println("hola"));

    }
    private void enableEdgeDelete(Pane pane,final Line line,Edge edge,double scale){
       /* line.setOnMouseReleased(event -> {
            line.getScene().setCursor(Cursor.HAND);
            if(deleteEdge.isSelected()){
                graph.DeleteEdge(edge.source.name,edge.destination.name);
                pane.getChildren().clear();
                getGraphPane(pane,scale,true);
            }
        });
        line.setOnMouseEntered(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                line.getScene().setCursor(Cursor.HAND);
            }
        });
        line.setOnMouseExited(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                line.getScene().setCursor(Cursor.DEFAULT);
            }
        });

        */
    }
    void getGraphPane(Pane pane, double scale,boolean enableTouch){

        ArrayList<Edge> edgeArrayList = new ArrayList<>();
        graph.getAdj().copyEdge(edgeArrayList);

        for(Edge e : edgeArrayList){
            pane.getChildren().addAll(e.getLine(),e.getText());
            if(enableTouch) {
                enableEdgeDelete(pane, e.getLine(), e, scale);
            }
        }
        for(int i=0;i<graph.getNodes().size();i++){
            pane.getChildren().add(graph.getNodes().get(i).getCircle(scale));
            pane.getChildren().add(graph.getNodes().get(i).getText());

            if(enableTouch) {
               // enableDrag(pane, graph.getNodes().get(i).getCircle(scale), graph.getNodes().get(i), scale);
            }
        }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
