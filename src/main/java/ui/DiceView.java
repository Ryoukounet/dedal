package ui;

import core.Dice;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.awt.*;
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
        while (dices.size() < 20) {

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
