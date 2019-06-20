package nl.han.ica.oopd.pacman;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

public class Enemy extends MovableObject implements ICollidableWithGameObjects {
    private Pacman world;
    private Direction turnDirection = new Direction();
    private Direction lastDirection = new Direction();

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

        for (GameObject g : collidedGameObjects) {
            if (g instanceof Enemy) {
//                g.setxSpeed(g.getxSpeed()*-1);    //oplossing1?

//                if (getxSpeed() > 0) {            //oplossing2?
//                    setxSpeed(-speed);
//                }
//                else {
//                    setxSpeed(speed);
//                }

//                Direction newDirection = new Direction(); //x=0, y=0  //oplossing3?
//                changeDirection((newDirection));

//                g.setDirection(getDirection()*-1);   //oplossing4?
            }
        }

//  Enemy draait van richting als ze elkaar raken:
// dit in enemy zetten  (implements gameobjectcollissionoccurred)
//    ipv world.reset() : g.direction = andere kant ("inverse")
    }

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

//    private int enemyColorR = (int) Math.floor(Math.random() * 255);
//    private int enemyColorG = (int) Math.floor(Math.random() * 255);
//    private int enemyColorB = (int) Math.floor(Math.random() * 255);

    @Override
    public void draw(PGraphics g) {
        g.stroke(0, 50, 200, 100);

        g.fill(255, 0, 0);

        g.rect(getX(), getY(), width, height);
    }
}
