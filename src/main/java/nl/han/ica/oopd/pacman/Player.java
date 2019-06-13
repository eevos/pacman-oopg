package nl.han.ica.oopd.pacman;

import nl.han.ica.oopd.pacman.tiles.WallTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;
import processing.core.PVector;

import java.util.List;

public class Player extends GameObject implements ICollidableWithTiles {
    Pacman world;
    private final int speed = 4;
    private int[] direction = {0, 0};

    private int[] lastDirectionPressed = {0,0};

    private Grid grid;


    public Player(Pacman world) {

        super(50, 50, 40, 40);
        this.world = world;
        grid = new Grid(40);

    }

    @Override
    public void keyPressed(int keyCode, char key) {

        String direction;

        if (keyCode == world.LEFT) {
            direction = "left";
        } else if (keyCode == world.UP) {
            direction = "up";
        } else if (keyCode == world.RIGHT) {
            direction = "right";
        } else if (keyCode == world.DOWN) {
            direction = "down";

        } else{
            return;
        }

        changeDirection(direction);
    }


    private void changeDirection (String directionString){

        int angle = 0;
        int [] direction = new int[2];

        if (directionString == "left") {
            direction[0] = -1;
            direction[1] = 0;
            angle = 270;
        } else if (directionString == "up") {
            direction[0] = 0;
            direction[1] = -1;
            angle = 0;
        } else if (directionString == "right") {
            direction[0] = 1;
            direction[1] = 0;
            angle = 90;
        } else if (directionString == "down") {
            direction[0] = 0;
            direction[1] = 1;
            angle = 180;
        }

        lastDirectionPressed = direction;

        if (grid.canMoveInDirection(getX(), getY(), direction)){
            setSpeed(speed);
            setDirection(angle);
            this.direction = direction;
        }

    }

    @Override
    public void update() {

        if (grid.canMoveInDirection(getX(), getY(), lastDirectionPressed)){
            if (lastDirectionPressed[0] == -1) {
                changeDirection("left");
            } else if (lastDirectionPressed[0] == 1){
                changeDirection("right");
            } else if (lastDirectionPressed[1] == -1){
                changeDirection("up");
            } else if (lastDirectionPressed[1] == 1){
                changeDirection("down");
            }
            return;
        }

        if (!grid.canMoveInDirection(getX(), getY(), direction)){
            setSpeed(0);
        }
    }

    @Override
    public void draw(PGraphics g) {
        g.stroke(0, 50, 200, 100);
        g.fill(200);
        g.rect(getX(), getY(), width, height);
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {

//
//        PVector vector;
//
//        for (CollidedTile ct : collidedTiles) {
//
//            if (ct.getTile() instanceof WallTile) {
//
//
//                if (CollisionSide.TOP.equals(ct.getCollisionSide())) {
//                    try {
//                        setySpeed(0);
//                        setY(getY() - speed);
//
//                    } catch (TileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (CollisionSide.BOTTOM.equals(ct.getCollisionSide())) {
//                    try {
//                        setySpeed(0);
//                        setY(getY() + speed);
//
//
//                    } catch (TileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (CollisionSide.RIGHT.equals(ct.getCollisionSide())) {
//                    try {
//                        setxSpeed(0);
//                        setX(getX() + speed);
//
//
//                    } catch (TileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (CollisionSide.LEFT.equals(ct.getCollisionSide())) {
//
//                    try {
//                        setxSpeed(0);
//                        setX(getX() - speed);
//
//
//                    } catch (TileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }
    }
}


