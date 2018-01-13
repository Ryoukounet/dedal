package ui;

import application.DiceGame;
import application.IHM;
import core.Dice;
import core.Player;
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
    private Label labelLancer;
    private int oldScore;
    private int oldLancer;
    public static Stage stage;

    public DiceView(Label label, Label labelLancer){
        this.label = label;
        this.labelLancer = labelLancer;

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
        oldLancer = 10;
        while (!allRollOver()) {

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
                int nbLancer = 10 - (dices.size() / 2);
                if(oldLancer != nbLancer) {
                    Platform.runLater(new Runnable() {
                                          @Override
                                          public void run() {

                                              // System.out.println(dices.size());
                                              labelLancer.setText("Lancés restant : " + nbLancer);
                                              oldLancer = nbLancer;
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
                        Parent loadScreen = (Parent) myLoader.load();
                          Scene scene;

                          scene = new Scene(loadScreen, 650, 150);

                          stage.setScene(scene);
                          stage.getScene().setRoot(loadScreen);

                          PlayerView.stage = stage;
                          Player player = DiceGame.player;
                          player.addObserver(myLoader.getController());

                          IHM.game = null;
                          IHM.diceView = null;
                          dices.clear();
                          player.setScore(getScores());
                          player.display();

                      } catch (IOException e) {
                          e.printStackTrace();
                      }

                  }
              }
            );




    }
    boolean allRollOver(){
        boolean roll = false;
        if(dices.size() >= 20) {
            roll = true;
            for (Dice value : dices.values()) {
                if (value.isRoll()) {
                    roll = false;
                    break;
                }

            }
        }
        return roll;
    }

     int getScores(){
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
