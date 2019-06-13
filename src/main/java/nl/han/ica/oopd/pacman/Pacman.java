package nl.han.ica.oopd.pacman;

import nl.han.ica.oopd.pacman.tiles.WallTile;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;

public class Pacman extends GameEngine {

    private Player player;
    private int tileSize;
    private int numberOfTileColumns;
    private int numberOfTileRows;

    public static void main(String[] args) {
        String[] processingArgs = {"nl.han.ica.oopd.world.Pacman"};

        Pacman mySketch = new Pacman();
        PApplet.runSketch(processingArgs, mySketch);
    }

    @Override
    public void setupGame() {

        tileSize = 40;

        Grid grid = new Grid(tileSize);

        numberOfTileRows = 22;
        numberOfTileColumns = 19;

        int uiSize = 200;

        int worldWidth = numberOfTileColumns * tileSize;
        int worldHeight = numberOfTileRows * tileSize + uiSize;


        initializeTileMap();
        createObjects();

        createView(worldWidth, worldHeight);
    }

    private void createView(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);

        setView(view);
        size(screenWidth, screenHeight);
    }

    @Override
    public void update() {
    }

    private void createObjects() {
        player = new Player(this);
        int playerXposition = tileSize*numberOfTileColumns/2-tileSize/2;
        int playerYposition = tileSize*8 ;

        addGameObject(player, playerXposition, playerYposition);

    }

    private void initializeTileMap() {
        /* TILES */
        Sprite wallSprite = new Sprite("src/main/java/nl/han/ica/oopd/pacman/media/wall.png");
        TileType<WallTile> wallTileTileType = new TileType<>(WallTile.class, wallSprite);

        TileType[] tileTypes = {wallTileTileType};



        tileMap = new TileMap(tileSize, tileTypes, GridMap.getGridMap());
    }
}
