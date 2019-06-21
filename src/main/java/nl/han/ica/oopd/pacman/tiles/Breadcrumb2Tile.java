package nl.han.ica.oopd.pacman.tiles;


import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;


public class Breadcrumb2Tile extends Tile {
    public int score;
    /**
     * @param sprite The image which will be drawn whenever the draw method of the tile is called.
     */
    public Breadcrumb2Tile(Sprite sprite) {

        super(sprite);
        this.score = 20;
    }


}
