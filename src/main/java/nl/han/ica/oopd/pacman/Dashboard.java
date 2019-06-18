package nl.han.ica.oopd.pacman;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Dashboard extends GameObject {

    private Pacman world;

    public Dashboard(Pacman world, float width, float height){
        this.world = world;
        this.width = width;
        this.height = height;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {
        g.fill(50, 100, 50);
        g.rect(x, y, width, height);

        g.fill(0);
        g.textSize(32);
        g.text("Score", x + width/4, y + 50);
        g.text(world.getScore(), x + width/4, y + 80);


    }
}
