
import core.Dice;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class IHM extends Application {

    double anchorX, anchorY;

    private static final float EDGE_LENGTH = 50;

    PerspectiveCamera camera = new PerspectiveCamera(false);


    @Override
    public void start(Stage stage) {


        Dice dice = new Dice(EDGE_LENGTH,EDGE_LENGTH,EDGE_LENGTH);
        Dice dice2 = new Dice(EDGE_LENGTH,EDGE_LENGTH,EDGE_LENGTH);
        dice2.getCube().setTranslateX(450);
        dice2.getCube().setTranslateY(00);
        dice2.getCube().setTranslateZ(0);
        Group dices = new Group(dice.getCube(),dice2.getCube());
        dices.setTranslateX(450);
        dices.setTranslateY(200);
        dices.setTranslateZ(0);

        Thread test = new Thread(dice);
        test.start();
        Scene scene;
        Parent par;
        Group root;
        try{
            par = (Parent) FXMLLoader.load(getClass().getResource("resources/Accueil.fxml"));
            root = new Group(par,dices);
            scene = new Scene(root);
            scene.setCamera(camera);
            stage.setTitle("Dedal");
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}