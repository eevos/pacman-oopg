package nl.han.ica.oopd.pacman;

public class Direction {
    public int x;
    public int y;


    public int getAngle() {
        int angle = 0;

        if (x == -1) {
            angle = 270;
        } else if (y == -1) {
            angle = 0;
        } else if (x == 1) {
            angle = 90;
        } else if (y == 1) {
            angle = 180;
        }

        return angle;
    }
}
