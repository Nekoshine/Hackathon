import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Interface extends Application {

    @Override
    public void start(Stage primaryStage) {

        StackPane cardstack = new StackPane();
        StackPane root=new StackPane();
        GridPane grid=new GridPane();
        Group choix=new Group();
        grid.setGridLinesVisible(true);
        Button textField1 = new Button("a");
        Button textField2 = new Button("b");
        Button textField3 = new Button("c");
        Button textField4 = new Button("d");
        Button textField5 = new Button("e");
        Button textField6 = new Button("f");


        try {
            Image imgg = new Image(new FileInputStream("./ressources/images/gauche.png"));
            ImageView gauche = new ImageView(imgg);
            Image imgd = new Image(new FileInputStream("./ressources/images/droite.png"));
            ImageView droite = new ImageView(imgd);
            Image imgchoix = new Image(new FileInputStream("./ressources/images/choix.png"));
            ImageView choixView = new ImageView(imgchoix);
            grid.setLayoutX(100);
            grid.setLayoutY(200);
            choix.getChildren().add(choixView);
            cardstack.getChildren().add(choix);

            Scene scene = new Scene(root, 500, 800);
            EventHandler<KeyEvent> swipe=new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                   if(event.getCode()==KeyCode.RIGHT){
                        Rotate rotation = new Rotate(0, imgchoix.getWidth()/2, imgchoix.getHeight()*2);
                        choix.getTransforms().add(rotation);

                        Timeline timeline = new Timeline();
                        timeline.getKeyFrames().addAll(
                                new KeyFrame(new Duration(800), new KeyValue(rotation.angleProperty(), 90))
                        );
                        timeline.setCycleCount(1);
                        timeline.play();
                    }
                    if(event.getCode()==KeyCode.LEFT){
                        Rotate rotation = new Rotate(0, imgchoix.getWidth()/2, imgchoix.getHeight()*2);
                        choix.getTransforms().add(rotation);

                        Timeline timeline = new Timeline();
                        timeline.getKeyFrames().addAll(
                                new KeyFrame(new Duration(800), new KeyValue(rotation.angleProperty(), -90))
                        );
                        timeline.setCycleCount(1);
                        timeline.play();
                    }
                }
            };

            scene.addEventHandler(KeyEvent.KEY_PRESSED,swipe);
            grid.add(textField1,0,0);
            grid.add(textField2,1,0);
            grid.add(textField3,2,0);
            grid.add(gauche,0,1);
            grid.add(droite,2,1);
            grid.add(textField6,0,2);
            grid.add(textField5,1,2);
            grid.add(textField4,2,2);
            grid.add(cardstack,1,1);
            root.getChildren().add(grid);
            primaryStage.setTitle("Hackathon");
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