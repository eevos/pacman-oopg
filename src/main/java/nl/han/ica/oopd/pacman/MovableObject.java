package nl.han.ica.oopd.pacman;

import nl.han.ica.oopg.objects.GameObject;

public abstract class MovableObject extends GameObject {

    protected Grid grid;
    protected Pacman world;
    protected Direction currentDirection = new Direction();
    protected int speed;

    private int baseSpeed;
    private boolean hasStarted = false;

    public MovableObject(Pacman world, Grid grid){

        this.world = world;
        this.grid = grid;

        setWidth(world.getTileSize());
        setHeight(world.getTileSize());
        baseSpeed = world.getBaseSpeed();
        speed = 0;
    }

    protected void changeDirection(Direction direction) {

        if (grid.canMoveInDirection(getX(), getY(), direction)) {
//            System.out.println(grid.canMoveInDirection(getX(), getY(), direction));
            setSpeed(speed);
            setDirection(direction.getAngle());
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


}