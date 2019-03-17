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
import static javafx.application.Platform.exit;

public class Interface extends Application {

	private Image imagesituation;
	private ImageView image;
	private Text question;
	private Game jeu;
	private Story histoire;
	private GridPane choix;
	private Text tgauche;
	private Text tdroite;
	private Player player;
	private boolean libre;
	private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
       	this.primaryStage=primaryStage;
		 libre=true;
		StackPane root=new StackPane();
		root.setAlignment(Pos.CENTER);
		VBox col=new VBox();
		HBox gnom=new HBox();
		HBox gage=new HBox();
		HBox gradio=new HBox();
		gnom.setSpacing(50);
		gnom.setAlignment(Pos.CENTER);
		gage.setSpacing(50);
		gage.setAlignment(Pos.CENTER);
		gradio.setSpacing(50);
		gradio.setAlignment(Pos.CENTER);
		Text tage=new Text("Veuillez entrer votre age");
		tage.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		Text tnom=new Text("Nom d'utilisateur");
		tnom.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		TextField age=new TextField();
		age.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		TextField nom=new TextField();
		nom.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		gnom.getChildren().addAll(tnom,nom);
		gage.getChildren().addAll(tage,age);
		ToggleGroup toggleGroup = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Homme");
		rb1.setToggleGroup(toggleGroup);
		rb1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		rb1.setSelected(true);
		RadioButton rb2 = new RadioButton("Femme");
		rb2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		rb2.setToggleGroup(toggleGroup);
		RadioButton rb3 = new RadioButton("Ne se prononce pas ");
		rb3.setToggleGroup(toggleGroup );
		rb3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		gradio.getChildren().addAll(rb1,rb2,rb3);
		Button start=new Button("Start");
		start.setPrefSize(100,50);
		start.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		start.setStyle("   -fx-text-fill: rgb(0,0,0);\n" +
						"   -fx-background-color: linear-gradient(#ff7b06, #994f00);\n" +
						"   -fx-effect: dropshadow( three-pass-box , rgb(0,1,0) , 5, 0.0 , 0 , 1 );\n"
				);

		start.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Scanner scan =new Scanner(age.getText());
				if(scan.hasNextInt() && !nom.getText().isEmpty()) {
                    ToggleButton a = (ToggleButton) toggleGroup.getSelectedToggle();
                    player = new Player(nom.getText(), scan.nextInt(), a.getText());
                    System.out.println(player.getPseudo() + "  " + player.getAge() + "  " + player.getSexe());
                    scan.close();
                    jouer();
                }
			}
		});
		col.setSpacing(50);
		col.setAlignment(Pos.CENTER);
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
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(col);
		Scene scene = new Scene(root,750,800);
		primaryStage.setScene(scene);
		primaryStage.show();
	}



	public void jouer(){
		histoire = Database.getStory(1);
		jeu=new Game(player, histoire);
		player.setHealthStrt(jeu.getHealth());
		player.setMoneyStrt(jeu.getMoney());
		player = Database.insertPlayer(player);
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

						animationdroite();
						
					}
					if(event.getCode()==KeyCode.LEFT){
						animationgauche();
						
					}
					event.consume();
				}
			};
			droite.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent event) {
					animationdroite();

				}
			});
			gauche.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent event) {
					animationgauche();
				}
			});

			scene.addEventHandler(KeyEvent.KEY_RELEASED,swipe);

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

	private void ecranfin(){
		StackPane root=new StackPane();
        VBox col=new VBox();
        col.setAlignment(Pos.CENTER);
        Text mes=new Text("Merci d'avoir joué");
		mes.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		Text score=new Text("Votre score est de : -argent "+player.getMoneyEnd()+"   -bonheur "+player.getHealthEnd());
		score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		HBox buttons=new HBox();
		buttons.setSpacing(50);
		buttons.setAlignment(Pos.CENTER);
		Button exit=new Button("exit");
		exit.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		exit.setStyle("   -fx-text-fill: rgb(0,0,0);\n" +
				"   -fx-background-color: linear-gradient(#ff7b06, #994f00);\n" +
				"   -fx-effect: dropshadow( three-pass-box , rgb(0,1,0) , 5, 0.0 , 0 , 1 );\n"
		);
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				exit();
			}
		});
		Button hist=new Button("Creer son histoire ");
		hist.setStyle("   -fx-text-fill: rgb(0,0,0);\n" +
				"   -fx-background-color: linear-gradient(#ff7b06, #994f00);\n" +
				"   -fx-effect: dropshadow( three-pass-box , rgb(0,1,0) , 5, 0.0 , 0 , 1 );\n"
		);
		hist.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		hist.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				creation();
			}
		});
		Button rejouer=new Button("rejouer");
		rejouer.setStyle("   -fx-text-fill: rgb(0,0,0);\n" +
				"   -fx-background-color: linear-gradient(#ff7b06, #994f00);\n" +
				"   -fx-effect: dropshadow( three-pass-box , rgb(0,1,0) , 5, 0.0 , 0 , 1 );\n"
		);
		rejouer.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		rejouer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				start(primaryStage);
			}
		});
		hist.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		Scene scene = new Scene(root,750,800);
		primaryStage.setScene(scene);
		col.setSpacing(50);
		buttons.getChildren().addAll(hist,rejouer,exit);
		col.getChildren().addAll(mes,score,buttons);
		root.getChildren().add(col);
	}

	public void creation(){

	}
	private void animationgauche(){
		if(libre) {
			libre=false;
			jeu.chooseLeft();
			System.out.println(jeu.isEnded()+"  "+jeu.getTurn());
			if(jeu.isEnded()){
			    ecranfin();
            }
			else {
                Rotate rotation = new Rotate(0, 200, 1000);
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
                        choix.add(image, 1, 0);
                        Database.insertAnswer(new Answer(jeu.getCurrentSituation().getId(), player.getId(), jeu.getHealth(), jeu.getMoney()));
                        libre = true;

                    }

                });
                timeline.play();
            }
		}

	}

	private void animationdroite(){
		if(libre) {

            libre = false;
            jeu.chooseRight();
			System.out.println(jeu.isEnded()+"  "+jeu.getTurn());
            if (jeu.isEnded()) {
                ecranfin();
            } else {
                Rotate rotation = new Rotate(0, 200, 1000);
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
                        choix.add(image, 1, 0);
                        libre = true;
                        Database.insertAnswer(new Answer(jeu.getCurrentSituation().getId(), player.getId(), jeu.getHealth(), jeu.getMoney()));

                    }
                });

                timeline.play();
            }
        }

	}

	private void actualiserSituation(){

		try {
			imagesituation = new Image(new FileInputStream("./ressources/images/"+jeu.getCurrentSituation().getImage()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				imagesituation = new Image(new FileInputStream("./ressources/images/choix"));
			}
			catch (FileNotFoundException ex){
				ex.printStackTrace();
			}
		}
		System.out.println(jeu.getCurrentSituation().getQuestion());
		image = new ImageView(imagesituation);
		image.setFitWidth(300);
		image.setFitHeight(300);
		question.setText(jeu.getCurrentSituation().getQuestion());
		tdroite.setText(jeu.getCurrentSituation().getRightChoice().getText());
		tgauche.setText(jeu.getCurrentSituation().getLeftChoice().getText());
	}
}
