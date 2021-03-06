package Client.Backend;

import java.awt.*;
import java.awt.geom.Point2D;

public class Track {

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final double SCREEN_WIDTH = screenSize.getWidth();
    private static final double SCREEN_HEIGHT = screenSize.getHeight();

    private double width = SCREEN_WIDTH;
    private double height = SCREEN_HEIGHT;

    private double smallA;
    private double bigA;
    private double smallB;
    private double bigB;
    private double positiveSmallC;
    private double negativeSmallC;
    private double positiveBigC;
    private double negativeBigC;


    private Converter converter;

    public Track(int a, int b) {
        smallA = a / 2;
        bigA = (a * 1.7) / 2;
        bigB = (b*1.7) / 2;
        smallB = b / 2;

        converter = new Converter();

        positiveSmallC = getPositiveC(smallA, smallB);
        negativeSmallC = getNegativeC(smallA, smallB);

        positiveBigC = getPositiveC(bigA, bigB);
        negativeBigC = getNegativeC(bigA, bigB);
    }

    private double getDistance(double x1, double x2, double y2) {
        return Point2D.distance(x1, 0.0, x2, y2);
    }

    private double getPositiveC(double a, double b) {
        return Math.sqrt( (Math.pow(a, 2) - Math.pow(b, 2)) );
    }

    private double getNegativeC(double a, double b) {
        return -( Math.sqrt( (Math.pow(a, 2) - Math.pow(b, 2)) ) );
    }

    public boolean onTheTrack(double carX, double carY) {
        double axisX;
        double axisY;
        double smallDistance1;
        double smallDistance2;
        double bigDistance1;
        double bigDistance2;

        axisX = converter.getAxisX(carX);
        axisY = converter.getAxisY(carY);

        smallDistance1 = getDistance(positiveSmallC, axisX, axisY);
        smallDistance2 = getDistance(negativeSmallC, axisX, axisY);

        bigDistance1 = getDistance(positiveBigC, axisX, axisY);
        bigDistance2 = getDistance(negativeBigC, axisX, axisY);

        double smallDistance = smallDistance1 + smallDistance2;
        double bigDistance = bigDistance1 + bigDistance2;
        return (smallDistance > (2 * smallA) && bigDistance < (2 * bigA));
    }
}
