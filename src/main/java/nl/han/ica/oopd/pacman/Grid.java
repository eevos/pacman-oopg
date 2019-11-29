package nl.han.ica.oopd.pacman;

/** @author Mischa, Edwin
 * @version 1.1
 * @since 1.0
 *
 */

public class Grid {

    private int tileSize;
    private int baseSpeed;
    private int[][] gridMap = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 2, 2, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 2, 2, 0},
            {0, 2, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 2, 0},
            {0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0},
            {0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 2, 2, 2, 2, 2, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 3, 3, 9, 3, 3, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0},
            {0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0},
            {0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0},
            {0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0},
            {0, 2, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 2, 0},
            {0, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    public Grid(int tileSize, int baseSpeed) {
        this.tileSize = tileSize;
        this.baseSpeed = baseSpeed;
    }



    public int[][] getGridMap() {
        return gridMap;
    }

    public int getPostion(int gridPosition) {
        return gridPosition * tileSize;
    }

    public boolean canMoveInDirection(float xPos, float yPos, Direction direction) {

        if (direction.x == 0 && direction.y == 0) {
            return false;
        }

        int[] currentGridPosition = getCurrentGridPosition(xPos, yPos);
        int[] currentModuloGridPosition = getCurrentModuloGridPosition(xPos, yPos);

        int[] nextGridPosition = {currentGridPosition[0] + direction.x, currentGridPosition[1] + direction.y};
        int valueOfNextPosition = this.getValueOfPosition(nextGridPosition);


//        Kijkt of de volgende postie vrij is en dat er geen hoek is
        boolean canMovePartOne = valueOfNextPosition > 0
                && currentModuloGridPosition[0] * direction.y == 0
                && currentModuloGridPosition[1] * direction.x == 0;

//       Kijkt of er nog een restwaarde is
        boolean canMovePartTwo =
                (currentModuloGridPosition[0] * direction.x != 0)
                        || (currentModuloGridPosition[1] * direction.y != 0);
//        System.out.println("One : " + canMovePartOne + ", Two : " + canMovePartTwo + " - ");
        return canMovePartOne || canMovePartTwo;
    }


    public int gridPosition(float pos) {

        return (int) ((pos) / tileSize);
    }

    private int getValueOfPosition(int[] position) {

        return gridMap[position[1]][position[0]];
    }


    private int[] getCurrentGridPosition(float xPos, float yPos) {

        int xGridPosition = gridPosition(xPos);
        int yGridPosition = gridPosition(yPos);

        return new int[]{xGridPosition, yGridPosition};
    }


    private int[] getCurrentModuloGridPosition(float xPos, float yPos) {

        int xGridPosition = moduloGridPosition(xPos);
        int yGridPosition = moduloGridPosition(yPos);

        return new int[]{xGridPosition, yGridPosition};
    }

    /**
     * Geeft de restwaarde van de huidige gridcoördinaten terug ten opzichte van het grid.
     * <p>Geeft de modulo terug maar wanneer de modulo lager is dan (tilesize / 40 * baseSpeed),
     * dan wordt de restwaarde op 0 gezet. Omdat het systeem er moeite mee heeft: kiezen van richting wordt
     * dan niet goed uitgevoerd</p>
     * @param pos is de x of y van het object in de gridcoördinaten
     * @return moduloGridPosition
     * @since 1.0
     *
     */
    private int moduloGridPosition(float pos) {
        int moduloGridPosition = (int) ((pos) % tileSize);

        if (moduloGridPosition < (tileSize / 40 * baseSpeed)) {
            moduloGridPosition = 0;
        }

        return moduloGridPosition;
    }

}
