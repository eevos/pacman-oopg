package nl.han.ica.oopd.pacman.tiles;


import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;


public class EmptyTile extends Tile {
    public int score = 0;

    /**
     * @param sprite The image which will be drawn whenever the draw method of the tile is called.
     */
    public EmptyTile(Sprite sprite) {

        super(sprite);
    }

    public int getScore(){
        return score;
    }

}
