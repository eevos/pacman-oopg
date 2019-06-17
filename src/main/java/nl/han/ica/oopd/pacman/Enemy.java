package nl.han.ica.oopd.pacman;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Enemy extends MovableObject {
    private Pacman world;
    private Direction turnDirection = new Direction();
    private Direction lastDirection = new Direction();



    Enemy(Pacman world, int baseSpeed) {
        super(50, 50, 40, 40);
        this.world = world;

        this.baseSpeed = baseSpeed;
        Direction newDirection = new Direction();
        newDirection.x = Math.round(Math.random()) == 1 ? 1 : -1;
        changeDirection(newDirection);
    }


    @Override
    public void update() {

        super.update();

        System.out.println(speed);
        System.out.println("xDir: " + currentDirection.x + ", yDir: " + currentDirection.y);


        if (grid.canMoveInDirection(getX(), getY(), turnDirection)) {
            changeDirection(turnDirection);
        }


        if (!grid.canMoveInDirection(getX(), getY(), currentDirection)) {
            Direction newDirection = new Direction();
            lastDirection = currentDirection;

            do {
                boolean moveHorizontal = Math.round(Math.random()) == 1;

                if (moveHorizontal) {
                    newDirection.x = Math.round(Math.random()) == 1 ? 1 : -1;
                    newDirection.y = 0;

                    turnDirection.x = 0;
                    turnDirection.y = Math.round(Math.random()) == 1 ? 1 : -1;

                } else {
                    newDirection.y = Math.round(Math.random()) == 1 ? 1 : -1;
                    newDirection.x = 0;

                    turnDirection.y = 0;
                    turnDirection.x = Math.round(Math.random()) == 1 ? 1 : -1;
                }

            } while (!grid.canMoveInDirection(getX(), getY(), newDirection) && newDirection != lastDirection);


            grid.canMoveInDirection(getX(), getY(), newDirection);


            changeDirection(newDirection);
        }
    }

    @Override
    public void draw(PGraphics g) {
        g.stroke(0, 50, 200, 100);
        g.fill(255, 0,0);
        g.rect(getX(), getY(), width, height);
    }
}
