package core;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import ui.CubeView;
import utils.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Dice extends Observable implements Runnable {
    private List<Observer> observers = new ArrayList<Observer>();


    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
    private final DoubleProperty angleZ = new SimpleDoubleProperty(0);

    private boolean roll = false;
    int i = 0;
    double x;
    double y;
    float deltaAngleX = -5;
    float deltaAngleY = -5;
    private float width, height;


    CubeView cube; //observer
    private double heightTerrain, widthTerrain;

    public Dice(CubeView cube, int layoutX, int layoutY) {
        this.widthTerrain = 1070;
        this.heightTerrain = 457;
        this.width = 50;
        this.height = 50;
        this.cube = cube;
        cube.getMesh().setLayoutX(layoutX);
        cube.getMesh().setLayoutY(layoutY);
        this.addObserver(cube);

    }

    private int absAngle(double angle) {
        if (angle < 0)
            angle = 360 + angle;

        if (Math.abs(angle) == 360)
            return 0;

        return (int) angle;
    }

    public int getFace() {
        int absX = absAngle(angleX.floatValue());
        int absY = absAngle(angleY.floatValue());
        //System.out.println(absX);
        int ret = -1;
        switch (absX) {
            case 0:
                switch (absY) {
                    case 0:
                        ret = 1;
                        break;
                    case 90:
                        ret = 3;
                        break;
                    case 180:
                        ret = 6;
                        break;
                    case 270:
                        ret = 4;
                        break;
                }
                break;

            case 90:
                ret = 2;
                break;

            case 180:
                switch (absY) {
                    case 0:
                        ret = 6;
                        break;
                    case 90:
                        ret = 4;
                        break;
                    case 180:
                        ret = 1;
                        break;
                    case 270:
                        ret = 3;
                        break;
                }
                break;
            case 270:
                ret = 5;
                break;
        }
        return ret;
    }

    public void roll() {
        Thread test = new Thread(this);
        test.start();
    }

    public void run() {

        x = cube.getMesh().getLayoutX();
        y = cube.getMesh().getLayoutY();

        int deceleration = 40;

        setValue(Randomizer.getInstance().getValue());

        roll = true;


        while (Math.abs(deltaAngleX) > 1 && Math.abs(deltaAngleY) > 1) {
            rollOneStep();

            i++;
            deceleration = deceleration != 0 ? deceleration : 1;
            if ((i % deceleration) == 0) {
                targetAngle();
                deceleration -= 5;
            }

            try {
                Thread.sleep(50);
                setChanged();
                this.notifyObservers(cube);

            } catch (InterruptedException ex) {

            }
        }

        boolean finishX = false;
        boolean finishY = false;
        int finishDeltaX = angleX.floatValue() % 90 > 45 ? 5 : -5;
        int finishDeltaY = angleY.floatValue() % 90 > 45 ? 5 : -5;
        angleX.set(angleX.floatValue() - (angleX.floatValue() % 5));
        angleY.set(angleY.floatValue() - (angleY.floatValue() % 5));

        while (!finishX || !finishY) {

            if ((angleX.floatValue() % 90) != 0)
                angleX.set(angleX.floatValue() + finishDeltaX);
            else
                finishX = true;

            if ((angleY.floatValue() % 90) != 0)
                angleY.set(angleY.floatValue() + finishDeltaY);
            else
                finishY = true;

            try {
                Thread.sleep(50);
                setChanged();
                this.notifyObservers(cube);
            } catch (InterruptedException ex) {

            }
        }


        setChanged();
        this.notifyObservers();
        roll = false;

    }

    private void rollOneStep() {
        angleY.set(angleY.floatValue() + deltaAngleY);
        if (Math.abs(angleY.floatValue()) > 360)
            angleY.set(0);

        //collision du terrain sur l'axe X
        if (x < (widthTerrain - width + 30) && x > width)
            x = x + deltaAngleX;
        else {
            deltaAngleX = deltaAngleX * (-1);
            x = x + deltaAngleX;
        }
        angleX.set(angleX.floatValue() + deltaAngleX);
        if (Math.abs(angleX.floatValue()) > 360)
            angleX.set(0);

        //collision du terrain sur l'axe Y
        if (y < (heightTerrain - 40) && y > height)
            y = y + deltaAngleY;
        else {
            deltaAngleY = deltaAngleY * (-1);
            y = y + deltaAngleY;
        }
    }

    private void targetAngle() {
        if (deltaAngleX > 0)
            deltaAngleX = deltaAngleX - 1;
        else
            deltaAngleX = deltaAngleX + 1;

        if (deltaAngleY > 0)
            deltaAngleY = deltaAngleY - 1;
        else
            deltaAngleY = deltaAngleY + 1;
    }

    public CubeView getCube() {
        return cube;
    }

    public boolean isRoll() {
        return roll;
    }

    public double getAngleX() {
        return angleX.get();
    }

    public DoubleProperty angleXProperty() {
        return angleX;
    }

    public double getAngleY() {
        return angleY.get();
    }

    public DoubleProperty angleYProperty() {
        return angleY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    private void setValue(int value) {
        int signX = Math.random() > 0.5 ? 1 : -1;
        deltaAngleX = value * signX;
        deltaAngleY = value;

    }


}
