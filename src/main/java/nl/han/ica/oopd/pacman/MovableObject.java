package nl.han.ica.oopd.pacman;

import nl.han.ica.oopg.objects.GameObject;

public abstract class MovableObject extends GameObject {

    protected Grid grid = Grid.getInstance();
    protected Direction currentDirection = new Direction();
    protected int baseSpeed;
    protected int speed = 0;
    private boolean hasStarted = false;


    MovableObject(int x, int y, int width, int heigth) {
        super(x, y, width, heigth);
    }


    protected void changeDirection(Direction direction) {


        if (grid.canMoveInDirection(getX(), getY(), direction)) {
            int angle = 0;

            if (direction.x == -1) {
                angle = 270;
            } else if (direction.y == -1) {
                angle = 0;
            } else if (direction.x == 1) {
                angle = 90;
            } else if (direction.y == 1) {
                angle = 180;
            }

            setSpeed(speed);
            setDirection(angle);
            currentDirection = direction;
        }
    }

    @Override
    public void keyPressed(int keyCode, char key) {

        if (!hasStarted) {
            speed = baseSpeed;
            setSpeed(speed);
            hasStarted = true;
        }
    }

    public void reset(){
        hasStarted = false;
        speed = 0;
        setSpeed(speed);
    }


    @Override
    public void update() {

    }
}
