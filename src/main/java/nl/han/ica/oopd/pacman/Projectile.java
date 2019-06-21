package nl.han.ica.oopd.pacman;

import nl.han.ica.oopd.pacman.tiles.Breadcrumb2Tile;
import nl.han.ica.oopd.pacman.tiles.Breadcrumb3Tile;
import nl.han.ica.oopd.pacman.tiles.BreadcrumbTile;
import nl.han.ica.oopd.pacman.tiles.WallTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

// Eigenlijk moet PRojectile extends MovableObject (of niet? maakt ook niet uit eigenlijk),
// maar dan heeft Projectile geen snelheid meer.

public class Projectile extends GameObject implements ICollidableWithGameObjects, ICollidableWithTiles {
    private int speed;
    private int xspeed;
    private int yspeed;
    private int direction;
    Pacman world;

    public Projectile(Pacman world, int direction, int playerSpeed, int x, int y) {
        super(x, y, 10, 10);

        this.world = world;
        this.direction = direction;
        this.speed = playerSpeed + 3;
        setxySpeedFromPlayerDirection(this.direction);
        setySpeed(yspeed);
        setxSpeed(xspeed);
    }

    public void setxySpeedFromPlayerDirection(int playerDirection) {
        int direction = playerDirection;
        switch (direction) {
            case 0:
                yspeed = speed * -1;
                xspeed = 0;
                break;
            case 90:
                yspeed = 0;
                xspeed = speed;
                break;
            case 180:
                yspeed = speed;
                xspeed = 0;
                break;
            case 270:
                yspeed = 0;
                xspeed = speed * -1;
                break;
        }
    }

    @Override
    public void draw(PGraphics g) {
        g.ellipseMode(g.CORNER);
        g.stroke(0, 50, 200, 100);
        g.fill(255, 131, 0);
        g.ellipse(getX() + 20, getY() + 20, 10, 10);
    }

    @Override
    public void update() {
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        PVector vector;

        for (CollidedTile ct : collidedTiles) {
            if (ct.getTile() instanceof WallTile) {
//                System.out.print("Projectile hits WallTile");
                world.deleteGameObject(this);
            }
            return;
        }
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

        for (GameObject g : collidedGameObjects) {
            if (g instanceof Enemy) {
//                System.out.println("Projectile collides with Eemeney");
                world.deleteGameObject(this);
                world.deleteGameObject(g);
            }
        }
    }
}
