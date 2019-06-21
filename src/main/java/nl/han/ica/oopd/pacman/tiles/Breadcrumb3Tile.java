package nl.han.ica.oopd.pacman.tiles;


import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;


public class Breadcrumb3Tile extends Tile {
    public int score = 0;

    /**
     * @param sprite The image which will be drawn whenever the draw method of the tile is called.
     */
    public Breadcrumb3Tile(Sprite sprite) {

        super(sprite);
        score = 10;
    }

    public int getScore(){
        return score;
    }

}
