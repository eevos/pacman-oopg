package nl.han.ica.oopd.pacman;

import nl.han.ica.oopd.pacman.tiles.WallTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;
import processing.core.PVector;

import java.util.List;

public class Projectile extends GameObject implements ICollidableWithGameObjects {
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
        g.ellipseMode(g.CORNER); // Omdat cirkel anders vanuit midden wordt getekend en dat problemen geeft bij collisiondetectie
        g.stroke(0, 50, 200, 100);
        g.fill(255, 131, 0);
        g.ellipse(getX()+20, getY()+20, 10, 10);
    }

    @Override
    public void update() {
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedObjects) {
        PVector vector;
        float size = 10;

        System.out.println("Collides");
        System.out.println(collidedObjects);
//        for (CollidedTile ct : collidedTiles) {
//            if (ct.theTile instanceof WallTile) {
//                if (ct.collisionSide == ct.TOP) {
//                    try {
//                        vector = world.getTileMap().getTilePixelLocation(ct.theTile);
//                        System.out.println(vector);
//                        setY(vector.y - getHeight());
//                    } catch (TileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (ct.collisionSide == ct.BOTTOM) {
//                    try {
//                        vector = world.getTileMap().getTilePixelLocation(ct.theTile);
//                        System.out.println(vector);
//                        setY(vector.y + getHeight() + size * 3 / 4);
//                    } catch (TileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (ct.collisionSide == ct.RIGHT) {
//                    try {
//                        vector = world.getTileMap().getTilePixelLocation(ct.theTile);
//                        System.out.println(vector);
//                        setX(vector.x + getWidth() + size * 3 / 4);
//                        //world.getTileMap().setTile((int) vector.x / 50, (int) vector.y / 50, -1);
//                    } catch (TileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (ct.collisionSide == ct.LEFT) {
//                    try {
//                        System.out.println(world);
//                        vector = world.getTileMap().getTilePixelLocation(ct.theTile);
//                        System.out.println(vector);
//                        setX(vector.x - getWidth());
//                        //world.getTileMap().setTile((int) vector.x / 50, (int) vector.y / 50, -1);
//                    } catch (TileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//    }
    }

}
