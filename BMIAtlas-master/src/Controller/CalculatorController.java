package Controller;

import java.io.IOException;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CalculatorController {
    @FXML
    private Button calculate; 
    
    @FXML
    private AnchorPane anchorRoot;

    @FXML
    private StackPane parentContainer;

    @FXML
    private ImageView history;


     public void closeWindow(ActionEvent event) {
        Platform.exit();
    }
    
    public void minimizeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void results(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("/View/Results.fxml"));
        Scene scene = calculate.getScene();
        root.translateXProperty().set(scene.getHeight());

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(.5), kv);

        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });

        timeline.play();
    }

    @FXML
    private void history(MouseEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/History.fxml"));
            Parent root = loader.load();
            
            Scene scene = history.getScene();
            Stage currentStage = (Stage) scene.getWindow();
    
            scene.setRoot(root);
            
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
