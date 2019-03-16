import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Interface extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Choix");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               System.out.println("test");
            }
        });

        StackPane root = new StackPane();

        Group choix=new Group();
        try {
            Image imgchoix = new Image(new FileInputStream("./ressources/images/choix.png"));
            ImageView choixView = new ImageView(imgchoix);
            choix.setLayoutX(100);
            choix.setLayoutY(200);
            choix.getChildren().add(choixView);
            choix.getChildren().add(btn);
            root.getChildren().add(choix);


            Scene scene = new Scene(root, 400, 600);
            EventHandler<KeyEvent> eventEventHandller=new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    //if(event.getCode()==)
                    System.out.println(event.getCode());
                }
            };
            scene.addEventHandler(KeyEvent.KEY_TYPED,eventEventHandller);
            primaryStage.setTitle("Hackhathon");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}