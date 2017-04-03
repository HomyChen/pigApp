package sample;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

import sun.misc.Launcher;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    @FXML
    static Stage window;

   // public  static Desktop desktop = Desktop.getDesktop();

    VBox layout;
    MenuBar menuBar;
    StackPane pane;
    Label label;
    TextField textField;



    @Override
    public void start(Stage stage) throws Exception{

        window = stage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        window.setTitle("Pig App");
        window.setScene(new Scene(root, 600, 400));

        window.show();
        System.out.println("-----------test1----------");
       // scene1 = new Scene(FXMLLoader.load(getClass().getResource("scene1.fxml")),500,300);
       // System.out.println("-----------test2----------");
    }


    public static void main(String[] args) {
        launch(args);
       // LauncherImpl.launchApplication(sample.Main.class,sample.preLoader.class,args);

    }
}