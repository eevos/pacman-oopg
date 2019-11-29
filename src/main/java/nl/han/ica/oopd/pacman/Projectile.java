package nl.han.ica.oopd.pacman;

import nl.han.ica.oopd.pacman.tiles.WallTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

public class Projectile extends GameObject implements ICollidableWithGameObjects, ICollidableWithTiles {
    private int speed;
    private int size = 10;

    private Direction direction;
    private Pacman world;

    public Projectile(Pacman world, Direction direction, int playerSpeed) {

        this.world = world;
        this.direction = direction;
        this.speed = playerSpeed + 3;

        setHeight(size);
        setWidth(size);


        setDirection(direction.getAngle());
        setSpeed(speed);
    }



    @Override
    public void draw(PGraphics g) {
        g.ellipseMode(g.CORNER);
        g.stroke(0, 50, 200, 100);
        g.fill(255, 131, 0);
        g.ellipse(getX() + 20, getY() + 20, size, size);
    }

    @Override
    public void update() {
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {

        for (CollidedTile ct : collidedTiles) {
            if (ct.getTile() instanceof WallTile) {
                world.deleteGameObject(this);
            }
            return;
        }
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Enemy) {
                world.deleteGameObject(g);
                world.deleteGameObject(this);
            }
        }
    }
}
