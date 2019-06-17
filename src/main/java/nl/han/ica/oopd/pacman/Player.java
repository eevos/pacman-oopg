package nl.han.ica.oopd.pacman;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import processing.core.PGraphics;

import java.util.List;

public class Player extends MovableObject implements ICollidableWithTiles {
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

        changeDirection(direction);
    }


    @Override
    protected void changeDirection (Direction direction){
        lastDirectionPressed = direction;
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


