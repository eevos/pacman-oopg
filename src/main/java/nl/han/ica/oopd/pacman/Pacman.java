package nl.han.ica.oopd.pacman;

import nl.han.ica.oopd.pacman.tiles.WallTile;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;

public class Pacman extends GameEngine {

    private MovableObject player;
    private MovableObject enemy1;
    private MovableObject enemy2;
    private int tileSize;
    private int baseSpeed;
    private int numberOfTileColumns;
    private int numberOfTileRows;
    private Grid grid = Grid.getInstance();


    public static void main(String[] args) {
        String[] processingArgs = {"nl.han.ica.oopd.world.Pacman"};

        Pacman mySketch = new Pacman();
        PApplet.runSketch(processingArgs, mySketch);
    }

    @Override
    public void setupGame() {

        tileSize = 40;
        baseSpeed = 4;
        grid.setProperties(tileSize, baseSpeed);


        numberOfTileRows = grid.getGridMap().length;
        numberOfTileColumns = grid.getGridMap()[0].length;

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
        player = new Player(this, baseSpeed);
        enemy1 = new Enemy(this, baseSpeed);
        enemy2 = new Enemy(this, baseSpeed);

        int playerXposition = grid.getPostion(9);
        int playerYposition = grid.getPostion(12);
        int enemyYposition = grid.getPostion(8);

        addGameObject(player, playerXposition, playerYposition);
        addGameObject(enemy1, playerXposition - tileSize, enemyYposition);
        addGameObject(enemy2, playerXposition + tileSize, enemyYposition);
    }

    private void initializeTileMap() {
        /* TILES */
        Sprite wallSprite = new Sprite("src/main/java/nl/han/ica/oopd/pacman/media/wall.png");
        TileType<WallTile> wallTileTileType = new TileType<>(WallTile.class, wallSprite);

        TileType[] tileTypes = {wallTileTileType};

        tileMap = new TileMap(tileSize, tileTypes, grid.getGridMap());
    }
}
