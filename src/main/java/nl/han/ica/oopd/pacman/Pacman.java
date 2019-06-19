package nl.han.ica.oopd.pacman;

import nl.han.ica.oopd.pacman.tiles.Breadcrumb2Tile;
import nl.han.ica.oopd.pacman.tiles.BreadcrumbTile;
import nl.han.ica.oopd.pacman.tiles.WallTile;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;

public class Pacman extends GameEngine {


    private int playerXPosition;
    private int playerYPosition;
    private int enemyYPosition;

    private Dashboard dashboard;

    private MovableObject player;
    private MovableObject enemy1;
    private MovableObject enemy2;
    private MovableObject enemy3;

    private MovableObject[] movableObjects = new MovableObject[4];


    private int score;

    private int uiSize;
    private int tileSize;
    private int worldWidth;
    private int worldHeight;
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

        playerXPosition = grid.getPostion(9);
        playerYPosition = grid.getPostion(12);
        enemyYPosition = grid.getPostion(8);

        numberOfTileRows = grid.getGridMap().length;
        numberOfTileColumns = grid.getGridMap()[0].length;

        uiSize = 200;

        worldWidth = numberOfTileColumns * tileSize  + uiSize;
        worldHeight = numberOfTileRows * tileSize;


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
        dashboard = new Dashboard(this, uiSize, worldHeight);
        enemy1 = new Enemy(this, baseSpeed);
        enemy2 = new Enemy(this, baseSpeed);
        enemy3 = new Enemy(this, baseSpeed);

        movableObjects[0] = player;
        movableObjects[1] = enemy1;
        movableObjects[2] = enemy2;
        movableObjects[3] = enemy3;

        addGameObject(dashboard , worldWidth - uiSize, 0);


        int i = -1;
        for (MovableObject movableObject : movableObjects) {
            if (movableObject instanceof Player) {
                addGameObject(movableObject, playerXPosition, playerYPosition);
            } else {
                addGameObject(movableObject, playerXPosition + tileSize * i, enemyYPosition);
                i++;
            }
        }

    }

    private void initializeTileMap() {
        /* TILES */
        Sprite wallSprite = new Sprite("src/main/java/nl/han/ica/oopd/pacman/media/wall.png");
        Sprite breadcrumbSprite = new Sprite("src/main/java/nl/han/ica/oopd/pacman/media/breadcrumb.png");
        Sprite breadcrumb2Sprite = new Sprite("src/main/java/nl/han/ica/oopd/pacman/media/breadcrumb2.png");

        TileType<WallTile> wallTileType = new TileType<>(WallTile.class, wallSprite);
        TileType<BreadcrumbTile> breadcrumbTileType = new TileType<>(BreadcrumbTile.class, breadcrumbSprite);
        TileType<Breadcrumb2Tile> breadcrumb2TileType = new TileType<>(Breadcrumb2Tile.class, breadcrumb2Sprite);

        TileType[] tileTypes = {wallTileType, breadcrumbTileType, breadcrumb2TileType};

        tileMap = new TileMap(tileSize, tileTypes, grid.getGridMap());
    }


    public void addPointsToScore(int crumbScore){

        score += crumbScore;
    }

    public int getScore() {
        return score;
    }



    protected void reset(){

        int i = -1;
        for (MovableObject movableObject : movableObjects) {
            System.out.println(movableObject);

            int xPos;
            int yPos;

            if (movableObject instanceof Player) {
                xPos = playerXPosition;
                yPos = playerYPosition;
            } else {
                xPos = playerXPosition + tileSize * i;
                yPos = enemyYPosition;
                i++;
            }
            movableObject.setX(xPos);
            movableObject.setY(yPos);

            movableObject.reset();
            score = 0;

        }

    }
}
