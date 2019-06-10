package nl.han.ica.oopd.pacman;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;

public class Pacman extends GameEngine {

    private Player player;

    public static void main(String[] args) {
        String[] processingArgs = {"nl.han.ica.oopd.pacman.Pacman"};

        Pacman mySketch = new Pacman();
        PApplet.runSketch(processingArgs, mySketch);
    }

    @Override
    public void setupGame() {
        int worldWidth = 1024;
        int worldHeight = 768;

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
    }
}
