import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
	private Boolean lean;
	private boolean libre;
	private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
       	this.primaryStage=primaryStage;
//		ecranfin();
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
		Text erreur=new Text("");
		erreur.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20));
		erreur.setFill(Color.RED);
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

		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				start.click();
				Scanner scan =new Scanner(age.getText());
				if(!nom.getText().isEmpty()) {
					if (scan.hasNextInt()) {
						int nb=scan.nextInt();
						if(nb>0){
							ToggleButton a = (ToggleButton) toggleGroup.getSelectedToggle();
							player = new Player(nom.getText(), nb, a.getText());
							System.out.println(player.getPseudo() + "  " + player.getAge() + "  " + player.getSexe());
							choisirHistoire();
						}else {
							erreur.setText("veuillez saisir un nombre positif pour votre age ");
						}
						scan.close();
					} else {
						erreur.setText("veuillez saisir un nombre positif pour votre age ");
					}
				}
				else {
					erreur.setText("veuillez saisir votre nom ");
				}
			}
		});
		col.setSpacing(50);
		col.setAlignment(Pos.CENTER);
        Button hist=new Button("creer une histoire");
        hist.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hist.click();
                createStory();
            }
        });
        hist.setPrefSize(300,50);
        HBox buttons=new HBox();
        buttons.setSpacing(100);
        buttons.getChildren().addAll(hist,start);
        buttons.setAlignment(Pos.CENTER);
        Button exit=new Button("exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                exit.click();
                exit();
            }
        });
		col.getChildren().addAll(gnom,gradio,gage,buttons,exit,erreur);
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

	public void createStory() {
		StackPane root = new StackPane();
		root.setAlignment(Pos.CENTER);

		Label labelTitle = new Label("Remplisser votre histoire (10 questions) :");
        labelTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

		ObservableList<QuestionTmp> storiesL = FXCollections.observableArrayList(Database.getQuestionsName());
		ListView<QuestionTmp> lviewLeft = new ListView<>(storiesL);
		lviewLeft.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		lviewLeft.getSelectionModel().selectFirst();

		ObservableList<QuestionTmp> storiesR = FXCollections.observableArrayList();
		ListView<QuestionTmp> lviewRight = new ListView<>(storiesR);
		lviewRight.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		lviewRight.getSelectionModel().selectFirst();

		Button toRight = new Button(">");
		Button toLeft = new Button("<");
		Button allLeft = new Button("<<");
		toLeft.setPrefWidth(100);
		toRight.setPrefWidth(100);
		allLeft.setPrefWidth(100);

		Button moveUp = new Button("Move up");
		Button moveDown = new Button("Move down");
		moveDown.setPrefWidth(200);
		moveUp.setPrefWidth(200);

		Button commit = new Button("Commit");
        Button exit=new Button("retour");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                exit.click();
               start(primaryStage);
            }
        });

		TextField nameBox = new TextField();
		nameBox.setPrefWidth(100);
        nameBox.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		Label label = new Label("Nom de l'histoire : ");
        label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));


		toRight.setOnAction(event -> {
		    toRight.click();
			if(lviewRight.getItems().size()<10) {
				int index = lviewLeft.getSelectionModel().getSelectedIndex();
				lviewRight.getItems().add(lviewLeft.getItems().remove(index));
				lviewLeft.getSelectionModel().select(index);
			}
		});

		toLeft.setOnAction(event -> {
            toLeft.click();
			if(lviewRight.getItems().size()>0) {
				lviewLeft.getItems().add(lviewRight.getItems().remove(lviewRight.getSelectionModel().getSelectedIndex()));
			}
		});

		allLeft.setOnAction(event -> {
            allLeft.click();
			while(lviewRight.getItems().size()>0) {
				lviewLeft.getItems().add(lviewRight.getItems().remove(0));
			}
		});

		moveUp.setOnAction(event -> {
            moveUp.click();
            QuestionTmp tmp = lviewRight.getSelectionModel().getSelectedItem();
			int index = lviewRight.getSelectionModel().getSelectedIndex();
			if (index>0){
				lviewRight.getItems().remove(index);
				lviewRight.getItems().add(index-1,tmp);
				lviewRight.getSelectionModel().select(index-1);
			}
		});

		moveDown.setOnAction(event -> {
            moveDown.click();
            QuestionTmp tmp = lviewRight.getSelectionModel().getSelectedItem();
			int index = lviewRight.getSelectionModel().getSelectedIndex();
			if (index<lviewRight.getItems().size()-1){
				lviewRight.getItems().remove(index);
				lviewRight.getItems().add(index+1,tmp);
				lviewRight.getSelectionModel().select(index+1);
			}
		});


		commit.setOnAction(event -> {
            if(lviewRight.getItems().size()==10 && nameBox.getCharacters().length()>0) {
                String story = "";
                for (QuestionTmp questionTmp : lviewRight.getItems()) {
                    story = story.concat(questionTmp.id+",");
                }
                Database.insertStory(new Story(nameBox.getCharacters().toString(),story.substring(0,story.length()-1)));
            }

		});


		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(15);
		grid.setVgap(15);
		grid.setPadding(new Insets(20));
		grid.add(labelTitle, 0, 0,6,1);
		grid.add(lviewLeft, 0, 1,2,5);
		grid.add(lviewRight, 3, 1,2,5);

		GridPane grid2 = new GridPane();
		grid2.add(toRight,0,0);
		grid2.add(toLeft,0,1);
		grid2.add(allLeft,0,2);
		grid2.setHgap(15);
		grid2.setVgap(15);
		grid2.setPadding(new Insets(20));
		grid2.setAlignment(Pos.CENTER);

		GridPane grid3 = new GridPane();
		grid3.add(moveUp,0,0);
		grid3.add(moveDown,0,2);
		grid3.setHgap(15);
		grid3.setVgap(15);
		grid3.setPadding(new Insets(20));
		grid3.setAlignment(Pos.CENTER);
		grid.add(grid2,2,1,1,5);
		grid.add(grid3,5,1,1,5);
		grid.add(label,1,6);
		grid.add(nameBox,2,6);
		grid.add(commit, 4, 6);
        grid.add(exit, 5, 6);
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
		root.getChildren().add(grid);
		Scene scene = new Scene(root, 1200, 800);
		primaryStage.setScene(scene);
		primaryStage.show();
	}



	public void choisirHistoire() {
        libre = true;
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        Label labelTitle = new Label("Choisisser votre histoire :");
        ArrayList<String> storiesName = Database.getStoriesName();
        ObservableList<String> stories = FXCollections.observableArrayList(storiesName);
        ListView<String> lview = new ListView<>(stories);

        lview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lview.getSelectionModel().selectFirst();
        Button start = new Button("Start");
        start.setPrefSize(100, 50);
        start.setAlignment(Pos.CENTER);

        start.setOnAction(event -> {
        	start.click();
            histoire = Database.getStory(lview.getSelectionModel().getSelectedIndex()+1);
            jouer();
        });
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(15);
        grid.setPadding(new Insets(20));
        grid.add(labelTitle, 0, 0);
        grid.add(lview, 0, 1);
        grid.add(start, 0, 3);

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
        root.getChildren().add(grid);
        Scene scene = new Scene(root, 750, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


	public void jouer(){
		lean=false;
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

			gauche.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET,event -> {
				lean=true;
				Rotate rotation = new Rotate(0, 200, 1000);
				image.getTransforms().add(rotation);

				Timeline timeline = new Timeline();
				timeline.getKeyFrames().addAll(
						new KeyFrame(new Duration(200), new KeyValue(rotation.angleProperty(), -4))
				);
				timeline.play();

			});
			gauche.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET,event -> {
				if(lean) {
					Rotate rotation = new Rotate(0, 200, 1000);
					image.getTransforms().add(rotation);

					Timeline timeline = new Timeline();
					timeline.getKeyFrames().addAll(
							new KeyFrame(new Duration(200), new KeyValue(rotation.angleProperty(), 4))
					);
					timeline.play();
					lean=false;
				}

			});
			Image imgd = new Image(new FileInputStream("./ressources/images/droite.png"));
			ImageView droite = new ImageView(imgd);
			droite.setFitHeight(150);
			droite.setFitWidth(220);
			droite.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET,event -> {
				lean=true;
				Rotate rotation = new Rotate(0, 200, 1000);
				image.getTransforms().add(rotation);

				Timeline timeline = new Timeline();
				timeline.getKeyFrames().addAll(
						new KeyFrame(new Duration(200), new KeyValue(rotation.angleProperty(), 4))
				);
				timeline.play();

			});
			droite.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET,event -> {
				if(lean) {
					Rotate rotation = new Rotate(0, 200, 1000);
					image.getTransforms().add(rotation);

					Timeline timeline = new Timeline();
					timeline.getKeyFrames().addAll(
							new KeyFrame(new Duration(200), new KeyValue(rotation.angleProperty(), -4))
					);
					timeline.play();
					lean=false;
				}

			});
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
        Text mes=new Text("Merci d'avoir joue");
		mes.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		Text score=new Text("Votre score est de : argent= "+player.getMoneyEnd()+"   bonheur= "+player.getHealthEnd());
		score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		HBox buttons=new HBox();
		buttons.setSpacing(50);
		buttons.setAlignment(Pos.CENTER);
		Button exit=new Button("exit");
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				exit.click();
				exit();
			}
		});
        Button hist=new Button("creer une histoire");
        hist.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hist.click();
                createStory();
            }
        });

		Button rejouer=new Button("rejouer");
		rejouer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				rejouer.click();
				start(primaryStage);
			}
		});
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
		Scene scene = new Scene(root,750,800);
		primaryStage.setScene(scene);
		col.setSpacing(50);
		buttons.getChildren().addAll(rejouer,hist,exit);
		col.getChildren().addAll(mes,score,buttons);
		root.getChildren().add(col);
	}

	public void creation(){

	}
	private void animationgauche(){
		if(libre) {
			Media sound = new Media(new File("./ressources/sound/card.wav").toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.setVolume(50);
			mediaPlayer.play();
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
                        lean=false;

                    }

                });
                timeline.play();
            }
		}

	}

	private void animationdroite(){
		if(libre) {
			Media sound = new Media(new File("./ressources/sound/card.wav").toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.setVolume(50);
			mediaPlayer.play();
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
                        lean=false;
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
