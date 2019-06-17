package nl.han.ica.oopd.pacman;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

public class Player extends MovableObject implements ICollidableWithGameObjects {
    Pacman world;

    private Direction lastDirectionPressed = new Direction();


    public Player(Pacman world, int baseSpeed) {

        super(50, 50, 40, 40);
        this.world = world;
        this.baseSpeed = baseSpeed;
    }

    @Override
    public void keyPressed(int keyCode, char key) {

        super.keyPressed(keyCode, key);

        Direction direction = new Direction();

        if (keyCode == world.LEFT) {
            direction.x = -1;
        } else if (keyCode == world.UP) {
            direction.y = -1;
        } else if (keyCode == world.RIGHT) {
            direction.x = 1;
        } else if (keyCode == world.DOWN) {
            direction.y = 1;
        } else {
            return;
        }

        lastDirectionPressed = direction;

        if (grid.canMoveInDirection(getX(), getY(), direction)) {
            System.out.println("yeah");
            changeDirection(direction);
        }
    }


    @Override
    protected void changeDirection (Direction direction){
        super.changeDirection(direction);
    }

    @Override
    public void update() {

        if (!grid.canMoveInDirection(getX(), getY(), currentDirection)) {
            setSpeed(0);
        }
        if (grid.canMoveInDirection(getX(), getY(), lastDirectionPressed)) {
            changeDirection(lastDirectionPressed);
        }
    }

    @Override
    public void draw(PGraphics g) {
        g.stroke(0, 50, 200, 100);
        g.fill(200);
        g.rect(getX(), getY(), width, height);
    }


    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

    }
}


