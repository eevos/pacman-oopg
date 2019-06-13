package nl.han.ica.oopd.pacman;

public class Grid {

    private int tileSize;


    public Grid(int tileSize) {
        this.tileSize = tileSize;
    }

    public int[] getCurrentGridPosition(float xPos, float yPos) {


        int xGridPosition = gridPosition(xPos);
        int yGridPosition = gridPosition(yPos);


        int[] currentGridPosition = {xGridPosition, yGridPosition};

        return currentGridPosition;

    }

    private int gridPosition(float pos) {

        int gridPosition = (int) ((pos + tileSize / 2) / tileSize);


        return gridPosition;
    }

    private int[] getCurrentModuloGridPosition(float xPos, float yPos) {


        int xGridPosition = moduloGridPosition(xPos);
        int yGridPosition = moduloGridPosition(yPos);

        int[] currentModuloGridPosition = {xGridPosition, yGridPosition};
        return currentModuloGridPosition;

    }

    private int moduloGridPosition(float pos) {
        int moduloGridPosition = (int) ((pos) % tileSize);

        if (moduloGridPosition < tileSize/10) {
            moduloGridPosition =0;
        }

        return moduloGridPosition;
    }

    public boolean canMoveInDirection(float xPos, float yPos, int[] direction) {

        int[] currentGridPosition = getCurrentGridPosition(xPos, yPos);
        int[] currentModuloGridPosition = getCurrentModuloGridPosition(xPos, yPos);

        int[] nextGridPosition = {currentGridPosition[0] + direction[0], currentGridPosition[1] + direction[1]};

        int valueOfNextPosition = GridMap.getValueOfPosition(nextGridPosition);


        System.out.println(valueOfNextPosition + "modX" + currentModuloGridPosition[0] + "modY" + currentModuloGridPosition[1]);
        System.out.println(currentModuloGridPosition[1]);

        boolean moduloBoolean = (valueOfNextPosition > 0 &&
                (currentModuloGridPosition[0] * direction[1] == 0  && currentModuloGridPosition[1] * direction[0] == 0)) ||
                (
                        (currentModuloGridPosition[0] * direction[0] != 0 && currentModuloGridPosition[1] * direction[1] == 0)
                        || (currentModuloGridPosition[0] * direction[0] == 0 && currentModuloGridPosition[1] * direction[1] != 0)
                );


        return moduloBoolean;

    }

//    public getX ()
//    {
//
//    }
}
