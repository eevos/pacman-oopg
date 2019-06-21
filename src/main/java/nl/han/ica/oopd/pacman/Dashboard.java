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

    public int addPoints;

    @Override
    public void draw(PGraphics g) {
        float textX = x + width/4 -25;

        g.fill(50, 100, 50);
        g.rect(x, y, width, height);

        g.fill(0);
        g.textSize(28);
        g.text("Score", textX, y + 50);
        g.text(world.getScore(), textX , y + 80);
        g.text("Last crumb", textX, y + 110);
        g.text(addPoints, textX, y + 140);


    }
}
