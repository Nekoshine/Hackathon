import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Double.min;

public class Interface extends Application {

    private Image imagesituation;
    private ImageView image;
    private Text question;
    private Game jeu;
    private GridPane choix;
    private Text tgauche;
    private Text tdroite;
    private Player player;
    @Override
    public void start(Stage primaryStage) {
        StackPane root=new StackPane();
        root.setAlignment(Pos.CENTER);
        VBox col=new VBox();
        HBox gnom=new HBox();
        HBox gage=new HBox();
        HBox gradio=new HBox();
        gnom.setSpacing(50);
        gage.setSpacing(50);
        gradio.setSpacing(50);
        Text tage=new Text("veuillez entrer votre Age");
        Text tnom=new Text("Nom d'utilisateur");
        TextField age=new TextField();
        TextField nom=new TextField();
        gnom.getChildren().addAll(tnom,nom);
        gage.getChildren().addAll(tage,age);
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Homme");
        rb1.setToggleGroup(toggleGroup);
        rb1.setSelected(true);
        RadioButton rb2 = new RadioButton("Femme");
        rb2.setToggleGroup(toggleGroup);
        RadioButton rb3 = new RadioButton("Ne se prononce pas ");
        rb3.setToggleGroup(toggleGroup );
        gradio.getChildren().addAll(rb1,rb2,rb3);
        Button start=new Button("Start");
        start.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scanner scan =new Scanner(age.getText());
                ToggleButton a=(ToggleButton) toggleGroup.getSelectedToggle();
                player=new Player(nom.getText(),scan.nextInt(),a.getText());
                System.out.println(player.getPseudo()+"  "+player.getAge()+"  "+player.getSexe());
                jouer(primaryStage);
            }
        });
        col.getChildren().addAll(gnom,gradio,gage,start);
        try {
            Image imgf = new Image(new FileInputStream("./ressources/images/fond.png"));
            ImageView fond = new ImageView(imgf);
            fond.setFitWidth(1600);
            fond.setFitHeight(1000);
            root.getChildren().add(fond);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        root.getChildren().add(col);
        Scene scene = new Scene(root,750,800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public void jouer(Stage primaryStage){
        jeu=new Game(player);
        StackPane root=new StackPane();
        VBox grid=new VBox();
        choix=new GridPane();
        question= new Text("example of a situation that require multiple line to be printed");
        question.setWrappingWidth(600);
        question.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        tgauche= new Text("gauche");
        tgauche.setTextAlignment(TextAlignment.CENTER);
        tgauche.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        tgauche.setWrappingWidth(200);
        tdroite= new Text("droite");
        tdroite.setTextAlignment(TextAlignment.CENTER);
        tdroite.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        tdroite.setWrappingWidth(200);
        try {
            Image imgf = new Image(new FileInputStream("./ressources/images/fond.png"));
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
            imagesituation = new Image(new FileInputStream("./ressources/images/choix.png"));
            image= new ImageView(imagesituation);


            Scene scene = new Scene(root,750,800);
            EventHandler<KeyEvent> swipe=new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if(event.getCode()==KeyCode.RIGHT){
                        jeu.chooseRight();
                        animationdroite();

                    }
                    if(event.getCode()==KeyCode.LEFT){
                        jeu.chooseLeft();
                        animationgauche();

                    }
                }
            };
            droite.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    jeu.chooseRight();
                    animationdroite();
                }
            });
            gauche.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    jeu.chooseLeft();
                    animationgauche();
                }
            });

            scene.addEventHandler(KeyEvent.KEY_PRESSED,swipe);

            root.getChildren().add(fond);
            question.setTextAlignment(TextAlignment.CENTER);
            choix.setAlignment(Pos.CENTER);
            choix.setHgap(5);
            choix.setVgap(50);
            choix.add(gauche,0,0);
            choix.add(droite,2,0);
            choix.add(tdroite,2,1);
            choix.add(tgauche,0,1);
            choix.add(image,1,0);
            grid.setSpacing(50);
            grid.setAlignment(Pos.CENTER);
            grid.getChildren().addAll(question,choix);
            root.getChildren().add(grid);
            primaryStage.setTitle("Hackathon");
            scene.widthProperty().addListener(new ChangeListener<Number>() {
                @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                    double scale=min(newSceneWidth.doubleValue()/750,scene.getHeight()/800);
                    root.setScaleX(scale);
                    root.setScaleY(scale);
                }
            });
            scene.heightProperty().addListener(new ChangeListener<Number>() {
                @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                    double scale=min(newSceneHeight.doubleValue()/800,scene.getWidth()/750);
                    root.setScaleX(scale);
                    root.setScaleY(scale);
                }
            });
            primaryStage.setScene(scene);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        choix.getChildren().remove(4);
        actualiserSituation();
        choix.add(image,1,0);
    }


    private void animationgauche(){
        Rotate rotation = new Rotate(0, imagesituation.getWidth()/2, imagesituation.getHeight()*2.5);
        image.getTransforms().add(rotation);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(new Duration(700), new KeyValue(rotation.angleProperty(), -90))
        );
        timeline.setCycleCount(1);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                choix.getChildren().remove(4);
                actualiserSituation();
                choix.add(image,1,0);

            }
        });
        timeline.play();

    }

    private void animationdroite(){
        Rotate rotation = new Rotate(0, imagesituation.getWidth()/2, imagesituation.getHeight()*2.5);
        image.getTransforms().add(rotation);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(new Duration(700), new KeyValue(rotation.angleProperty(), +90))
        );
        timeline.setCycleCount(1);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                choix.getChildren().remove(4);
                actualiserSituation();
                choix.add(image,1,0);

            }
        });
        timeline.play();

    }

    private void actualiserSituation(){

        try {
            imagesituation = new Image(new FileInputStream("./ressources/images/"+jeu.currentSituation.image));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(jeu.currentSituation.question);
        image = new ImageView(imagesituation);
        image.setFitWidth(300);
        image.preserveRatioProperty();
        question.setText(jeu.currentSituation.question);
        tdroite.setText(jeu.currentSituation.rightChoice.getText());
        tgauche.setText(jeu.currentSituation.leftChoice.getText());
    }

    public static void main(String[] args) {
        launch(args);
    }
}