package nl.han.ica.oopd.pacman;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

public class Player extends GameObject implements ICollidableWithTiles {
    Pacman pacman;


    public Player (Pacman pacman){
        this.pacman = pacman;
    }

    @Override
    public void update(){

    }

    @Override
    public void draw(PGraphics g) {

    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {

    }
}
