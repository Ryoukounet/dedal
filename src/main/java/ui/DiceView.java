package ui;

import core.Dice;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;


public class DiceView implements Observer,Runnable {
    //ArrayList<Dice> dices = new ArrayList<>();
    private HashMap<Integer,Dice> dices = new HashMap<>();
    private int i = 1;
    private Label label;
    private int oldScore;
    public static Stage stage;

    public DiceView(Label label){
        this.label = label;

    }
    @Override
    public synchronized void update(Observable o, Object arg) {
        Dice dice = (Dice) o;
        if(!dices.containsValue(dice)) {
            dices.put(i,dice);

            i++;
        }
    }

    public void run(){
        oldScore = 0;
        while (dices.size() < 2) {

            try {

                if(oldScore != getScores()) {

                    Platform.runLater(new Runnable() {
                                          @Override
                                          public void run() {
                                              int score = getScores();
                                              label.setText("Score : " + score);
                                              oldScore = score;
                                          }
                                      }
                    );
                }
                Thread.sleep(500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("../resources/PlayerView.fxml"));


            Platform.runLater(new Runnable() {
                                  @Override
                                  public void run() {
                                      try {
                                      Parent loadScreen = (Parent) myLoader.load(); Scene scene = stage.getScene();

                                          scene = new Scene(loadScreen, 650, 150);
                                          //scene.getStylesheets().add(App.class.getResource("demo.css").toExternalForm());
                                          stage.setScene(scene);

                                          stage.getScene().setRoot(loadScreen);
                                      } catch (IOException e) {
                                          e.printStackTrace();
                                      }



                                  }
                              }
            );




    }

    public int getScores(){
        int j = 1;
        int score = 0;
        while(j < dices.size()){
             Dice un = dices.get(j);
             Dice deux = dices.get(j + 1);
             j += 2;
             if( un !=null && deux != null){
                 int totalFaces = un.getFace() + deux.getFace();
                 if(totalFaces == 7){
                     score += 10;
                 }

             }else{
                 break;
             }


        }
        return score;
    }

}
