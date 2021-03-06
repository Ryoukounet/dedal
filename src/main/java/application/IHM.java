package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ui.DiceView;

import java.io.IOException;

public class IHM extends Application {

    @FXML
    public Label score;

    @FXML
    public Label lancer;

    public static DiceGame game;
    public static DiceView diceView;
    public static Stage stage;


    @Override
    public void start(Stage stage) {
        this.stage = stage;
        DiceView.stage = stage;

        game = new DiceGame();
        game.gameInitialize();
    }

    @FXML
    private void start(ActionEvent event) {
        if (diceView == null) {

            diceView = new DiceView(score, lancer);
            Thread view = new Thread(diceView);
            view.start();
        }
        if (game == null || !game.isOver()) {
            game.start();
        }
    }

    @FXML
    private void goToScore(ActionEvent event) throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("../resources/ScoreView.fxml"));

        Parent loadScreen = (Parent) myLoader.load();
        Scene scene;

        scene = new Scene(loadScreen, 630, 350);
        Stage newStage = new Stage();

        newStage.setScene(scene);
        newStage.getScene().setRoot(loadScreen);
        newStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}