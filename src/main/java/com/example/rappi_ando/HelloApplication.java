package com.example.rappi_ando;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
                Image image = new Image("C:\\Users\\Lenovo\\Desktop\\Proyecto Discretas\\Rappi_ando\\src\\main\\resources\\com\\example\\rappi_ando\\Logo.png");
                window.getIcons().add(image);
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
                Image image = new Image("C:\\Users\\Lenovo\\Desktop\\Proyecto Discretas\\Rappi_ando\\src\\main\\resources\\com\\example\\rappi_ando\\Logo.png");
                addEdge.getIcons().add(image);
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