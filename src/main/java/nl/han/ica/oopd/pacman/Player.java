package nl.han.ica.oopd.pacman;

import nl.han.ica.oopd.pacman.tiles.Breadcrumb2Tile;
import nl.han.ica.oopd.pacman.tiles.BreadcrumbTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;
import processing.core.PVector;

import java.util.List;

public class Player extends MovableObject implements ICollidableWithGameObjects, ICollidableWithTiles {
    private Direction lastDirectionPressed = new Direction();

    public Player(Pacman world, Grid grid) {
        super(world, grid);
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
        } else if (key == ' ') {
            Projectile bomb = new Projectile(world, currentDirection, speed);
            world.addGameObject(bomb, getX(), getY());
//            System.out.println("Space-----------------------------Space");
        } else {
            return;
        }

        lastDirectionPressed = direction;

        if (grid.canMoveInDirection(getX(), getY(), direction)) {
            changeDirection(direction);
        }
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
        g.fill(255, 255, 0);
        g.ellipseMode(CORNER);
        g.ellipse(getX(), getY(), width, height);
    }


    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Enemy) {
                world.reset();
            }
        }
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        PVector vector;

        for (CollidedTile ct : collidedTiles) {

            if (ct.getTile() instanceof BreadcrumbTile) {
                vector = world.getTileMap().getTilePixelLocation(ct.getTile());
                world.getTileMap().setTile(grid.gridPosition(vector.x), grid.gridPosition(vector.y), 99);
                world.addPointsToScore(10);

            } else if (ct.getTile() instanceof Breadcrumb2Tile) {
                vector = world.getTileMap().getTilePixelLocation(ct.getTile());
                world.getTileMap().setTile(grid.gridPosition(vector.x), grid.gridPosition(vector.y), 99);
                world.addPointsToScore(20);
            }
        }
    }
}


