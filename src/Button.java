import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Button extends javafx.scene.control.Button {
    public Button(String txt) {
        super(txt);
        this.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        this.setStyle("   -fx-text-fill: rgb(0,0,0);\n" +
                "-fx-border-color: black;\n" +
                "-fx-border-image-width: 0;\n" +
                "   -fx-background-color: linear-gradient(#ff7b06, #994f00);\n" +
                "   -fx-effect: dropshadow( three-pass-box , rgb(0,1,0) , 5, 0.0 , 0 , 1 );\n");
        this.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStyle("   -fx-text-fill: rgb(0,0,0);\n" +
                        "-fx-border-color: black;\n" +
                        "-fx-border-image-width: 5;\n" +
                        "   -fx-background-color: linear-gradient(#ffa73f, #d27e00);\n" +
                        "   -fx-effect: dropshadow( three-pass-box , rgb(0,1,0) , 5, 0.0 , 0 , 1 );\n"
                );
                Rotate rotation = new Rotate(0, 200, 1000);
                getTransforms().add(rotation);

                Timeline timeline = new Timeline();
                timeline.getKeyFrames().addAll(
                        new KeyFrame(new Duration(100), new KeyValue(rotation.angleProperty(), 0))
                );
                timeline.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        setStyle("   -fx-text-fill: rgb(0,0,0);\n" +
                                "-fx-border-color: black;\n" +
                                "-fx-border-image-width: 0;\n" +
                                "   -fx-background-color: linear-gradient(#ff7b06, #994f00);\n" +
                                "   -fx-effect: dropshadow( three-pass-box , rgb(0,1,0) , 5, 0.0 , 0 , 1 );\n"
                        );
                    }
                });
                timeline.play();

            }
        });
    }
}
