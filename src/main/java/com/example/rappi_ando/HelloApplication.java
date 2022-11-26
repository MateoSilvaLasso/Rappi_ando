package com.example.rappi_ando;

import com.example.rappi_ando.model.RappiandoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage addEdge;

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
            if(fxml.equals("addEdge.fxml") && addEdge==null) {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
                Parent node = fxmlLoader.load();
                Scene scene = new Scene(node);
                addEdge = new Stage();
                addEdge.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                addEdge.setTitle("Rappi_ando!");
                addEdge.setX(600);
                addEdge.setY(250);
                addEdge.setScene(scene);
                addEdge.show();
            }else {
                addEdge.show();
                addEdge.toFront();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}