import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Interface extends Application {

    @Override
    public void start(Stage primaryStage) {

        StackPane imagestack = new StackPane();
        StackPane root=new StackPane();
        VBox grid=new VBox();
        HBox choix=new HBox();
        Text question= new Text("Question");
        Text tgauche= new Text("gauche");
        Text tdroit= new Text("droit");

        try {
            Image imgf = new Image(new FileInputStream("./ressources/images/fond.jpg"));
            ImageView fond = new ImageView(imgf);
            fond.setFitWidth(1600);
            fond.setFitHeight(1000);
            Image imgg = new Image(new FileInputStream("./ressources/images/gauche.png"));
            ImageView gauche = new ImageView(imgg);
            gauche.setFitHeight(150);
            gauche.setFitWidth(220);
            Image imgd = new Image(new FileInputStream("./ressources/images/droite.png"));
            ImageView droite = new ImageView(imgd);
            droite.setFitHeight(150);
            droite.setFitWidth(220);

            Image imgchoix = new Image(new FileInputStream("./ressources/images/choix.png"));
            ImageView choixView = new ImageView(imgchoix);
            choix.getChildren().add(choixView);
            imagestack.getChildren().add(choix);

            Scene scene = new Scene(root, 630, 800);
            EventHandler<KeyEvent> swipe=new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                   if(event.getCode()==KeyCode.RIGHT){
                        Rotate rotation = new Rotate(0, imgchoix.getWidth()/2, imgchoix.getHeight()*2.2);
                        choix.getTransforms().add(rotation);

                        Timeline timeline = new Timeline();
                        timeline.getKeyFrames().addAll(
                                new KeyFrame(new Duration(800), new KeyValue(rotation.angleProperty(), 90))
                        );
                        timeline.setCycleCount(1);
                        timeline.play();
                    }
                    if(event.getCode()==KeyCode.LEFT){
                        Rotate rotation = new Rotate(0, imgchoix.getWidth()/2, imgchoix.getHeight()*2.2);
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
            droite.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    Rotate rotation = new Rotate(0, imgchoix.getWidth()/2, imgchoix.getHeight()*2.2);
                    choix.getTransforms().add(rotation);

                    Timeline timeline = new Timeline();
                    timeline.getKeyFrames().addAll(
                            new KeyFrame(new Duration(800), new KeyValue(rotation.angleProperty(), 90))
                    );
                    timeline.setCycleCount(1);
                    timeline.play();
                }
            });
            gauche.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    Rotate rotation = new Rotate(0, imgchoix.getWidth()/2, imgchoix.getHeight()*2.2);
                    choix.getTransforms().add(rotation);

                    Timeline timeline = new Timeline();
                    timeline.getKeyFrames().addAll(
                            new KeyFrame(new Duration(800), new KeyValue(rotation.angleProperty(), -90))
                    );
                    timeline.setCycleCount(1);
                    timeline.play();
                }
            });
            root.minHeightProperty().bind(root.widthProperty().multiply(1));
            root.maxHeightProperty().bind(root.widthProperty().multiply(1));
            scene.addEventHandler(KeyEvent.KEY_PRESSED,swipe);

            root.getChildren().add(fond);
            root.getChildren().add(grid);
            primaryStage.setTitle("Hackathon");
            primaryStage.setResizable(false);
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