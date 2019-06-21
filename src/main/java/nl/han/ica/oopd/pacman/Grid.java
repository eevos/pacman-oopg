package nl.han.ica.oopd.pacman;

public class Grid {

    // static variable single_instance of type Singleton
    private static Grid single_instance = null;
    private int tileSize;
    private int baseSpeed;

    // private constructor restricted to this class itself
    private Grid()
    {
    }

    // static method to create instance of Grid class
    public static Grid getInstance()
    {
        if (single_instance == null) {
            single_instance = new Grid();
        }

        return single_instance;
    }



    private static int [][] gridMap = {
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
            {0, 0, 0, 0, 1, 0, 1, 1, 1, 9, 1, 1, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 3, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0},
            {0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0},
            {0, 0, 1, 0, 3, 0, 1, 0, 0, 0, 0, 0, 1, 0, 3, 0, 1, 0, 0},
            {0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0},
            {0, 2, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 2, 0},
            {0, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };


    public void setProperties(int tileSize, int baseSpeed){
        this.tileSize = tileSize;
        this.baseSpeed = baseSpeed;
    }

    public int[][] getGridMap() {
        return gridMap;
    }

    public int getValueOfPosition (int[] position)
    {

        return gridMap[position[1]][position[0]];
    }


    public int[] getCurrentGridPosition(float xPos, float yPos) {

        int xGridPosition = gridPosition(xPos);
        int yGridPosition = gridPosition(yPos);

        int[] currentGridPosition = {xGridPosition, yGridPosition};

        return currentGridPosition;
    }

    public int getPostion(int gridPosition){
        return gridPosition*tileSize;
    }


    public boolean canMoveInDirection(float xPos, float yPos, Direction direction) {

        if (direction.x == 0 && direction.y == 0) {
            return false;
        }

        int[] currentGridPosition = getCurrentGridPosition(xPos, yPos);
        int[] currentModuloGridPosition = getCurrentModuloGridPosition(xPos, yPos);

        int[] nextGridPosition = {currentGridPosition[0] + direction.x, currentGridPosition[1] + direction.y};
        int valueOfNextPosition = this.getValueOfPosition(nextGridPosition);


//        Kijkt of de volgende postie vrij is en het object niet midden in een tile zit


        boolean canMovePartOne = valueOfNextPosition > 0
                && currentModuloGridPosition[0] * direction.y == 0
                && currentModuloGridPosition[1] * direction.x == 0;

//       Kijkt of er nog een restwaarde is
        boolean canMovePartTwo =
                (currentModuloGridPosition[0] * direction.x != 0)
                || (currentModuloGridPosition[1] * direction.y != 0);



        boolean moduloBoolean =  canMovePartOne || canMovePartTwo;

        return moduloBoolean;
    }


    public int gridPosition(float pos) {

        int gridPosition = (int) ((pos ) / tileSize);
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

        if (moduloGridPosition < (tileSize/40*baseSpeed)) {
            moduloGridPosition =0;
        }

        return moduloGridPosition;
    }






}
