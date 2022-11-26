package com.example.rappi_ando;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage addEdge;
    private static Stage editEdge;

    @Override
    public void start(Stage stage)  {
        showWindow("rappi-ando.fxml");
    }
    public static void showWindow(String fxml){
        try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
                Parent node = fxmlLoader.load();
                Scene scene = new Scene(node);
                Stage window = new Stage();
                window.setScene(scene);
                window.show();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public static void showTransparentWindow(String fxml){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
            Parent node = fxmlLoader.load();
            Scene scene = new Scene(node);
            if(fxml.equals("addEdge.fxml") && addEdge==null) {
                addEdge = new Stage();
                addEdge.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                addEdge.setTitle("Rappi_ando!");
                addEdge.setX(600);
                addEdge.setY(250);
                addEdge.setScene(scene);
                addEdge.show();
            }else if(fxml.equals("addEdge.fxml")) {
                addEdge.show();
                addEdge.toFront();
            }else if(fxml.equals("editEdge.fxml")&&editEdge==null){
                editEdge = new Stage();
                editEdge.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                editEdge.setTitle("Rappi_ando!");
                editEdge.setX(600);
                editEdge.setY(250);
                editEdge.setScene(scene);
                editEdge.show();
            } else if (fxml.equals("editEdge.fxml")) {
                editEdge.show();
                editEdge.toFront();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}