
public class Generation {
    private int[][] initialGrid = new int[3][3];
    private final int upperbound = 9;
    private final int lowerbound = 1;
    private int random;
    private int[][] finalGrid2D;
    private int[] finalGrid1D;
    private int[] playerGrid;

    public static void main(String [] args) {
        Generation grid = new Generation();
        grid.printGrid2D(grid.initialGrid,3);
        grid.printGrid1D(grid.finalGrid1D);
        grid.printGrid1D(grid.playerGrid);
    }

    /**
     * No-arg constructor will create my 9x9 grid in a 2d array as well as translate to a 1d array.
     * Will also translate it to the player grid as well(have x amount missing)
     */
    public Generation() {
        initialCreation();//create the initial 3x3 grid using random numbers.
        //generate 9x9 method will take the initial and create a 9x9 through alterations.
        finalGrid2D = generate9x9(initialGrid);
        //Take my full 2d array of my 9x9 grid and convert it into a 1d array.
        finalGrid1D = toSingleArray(finalGrid2D);
        //Take my 1d array and take out x amount of grid spots in order to give the user a challenge.
        //Using two random numbers and modulo in order to accomplish this.
        playerGrid = toPlayerGrid(finalGrid1D);
    }

    /**
     * getFinalGrid1D
     * @return int[]
     */
    public int[] getFinalGrid1D() {
        return this.finalGrid1D;
    }

    /**
     * getFinalGrid2D
     * @return int[][]
     */
    public int[][] getFinalGrid2D() {
        return this.finalGrid2D;
    }


    /**
     * getPlayerGrid
     * @return int[]
     */
    public int[] getPlayerGrid() {
        return this.playerGrid;
    }

    /**
     * toPlayerGrid method that will take the full grid and run through it setting x amount of
     * grid locations to be blank. This will create the grid that will be shown to the user.
     * @param fullGrid int[]
     * @return int[]
     */
    public int[] toPlayerGrid(int[] fullGrid) {
        int[] playerGrid = new int[81];
        for(int i = 0; i < playerGrid.length;i++) {
            if((((i % randomWithRange(1, 9)) != 0)) && (i % randomWithRange(1,9)) != 0) {
//                if(i != 1 && i != 9) {
                playerGrid[i] = fullGrid[i];
            }
        }
        return playerGrid;
    }

    /**
     * toSingleArray method that will take any 2d array and translate it to a 1d array. In my case
     * this will be used to take my full 2D array holding my 9x9 grid into a 1D holding the same.
     * @param tempGrid int[][]
     * @return int[]
     */
    public int[] toSingleArray(int[][] tempGrid) {
        int[] finalGrid = new int[tempGrid.length * tempGrid.length];
        for(int row = 0; row < tempGrid.length;row ++) {
            for(int column = 0; column < tempGrid[row].length;column++) {
                finalGrid[(row * tempGrid.length) + column] = tempGrid[row][column];
            }
        }
        return finalGrid;
    }

    /**
     * initialCreation method that will utilize random numbers in order to generate my first 3x3
     * grid of numbers. During the creation the checkRules method will be called in order to make
     * sure that the current random number is not already present within the 3x3 grid.
     */
    public void initialCreation() {
        for (int row = 0; row < initialGrid.length; row++) {
            for (int column = 0; column < initialGrid[row].length; column++) {
                do {
                    random = (int) (Math.random() * ((upperbound - lowerbound) + 1) + lowerbound);
                } while (!checkRules(random));
                initialGrid[row][column] = random;
            }
        }
    }

    /**
     * checkRules method that will take an integer for a cellValue and check it against the initial
     * grid to make sure that any new integers generate do not match any previous. Will return a
     * boolean, true if the integer is not found within the grid and false if it is found.
     * @param cellValue int
     * @return boolean
     */
    private boolean checkRules(int cellValue) {
        boolean followsRules = true;
        for (int row = 0; row < initialGrid.length; row++) {
            for (int column = 0; column < initialGrid[row].length; column++) {
                if (cellValue == initialGrid[row][column]) {
                    return false;
                } else
                    followsRules = true;
            }
        }
        return followsRules;
    }

    /**
     * generate3x3 method will generate a new 3x3 grid of numbers using minor alterations to the
     * grid passed in using the direction in order to know which direction to alter it.
     * @param direction String
     * @param grid int[][]
     * @return int[][]
     */
    private int[][] generate3x3(String direction, int[][] grid) {
        int[][] subGrid = new int[3][3];
        int[] row1;
        int[] row2;
        int[] row3;
        int[] column1;
        int[] column2;
        int[] column3;

        if (direction.equalsIgnoreCase("vertical")) {
            row1 = this.getRow(grid, 0);
            row2 = this.getRow(grid, 1);
            row3 = this.getRow(grid, 2);

            for (int count = 0; count < 3; count++) {
                for (int i = 0; i < 3; i++) {
                    if (count == 0) {
                        subGrid[i][count] = row2[i];
                    } else if (count == 1)
                        subGrid[i][count] = row3[i];
                    else
                        subGrid[i][count] = row1[i];
                }
            }
        } else if (direction.equalsIgnoreCase("horizontal")) {
            column1 = this.getColumn(grid, 0);
            column2 = this.getColumn(grid, 1);
            column3 = this.getColumn(grid, 2);

            for (int count = 0; count < 3; count++) {
                for (int i = 0; i < 3; i++) {
                    if (count == 0)
                        subGrid[count][i] = column3[i];
                    else if (count == 1)
                        subGrid[count][i] = column1[i];
                    else
                        subGrid[count][i] = column2[i];
                }
            }
        }
        return subGrid;
    }

    /**
     * generate9x9 method that will call my generate3x3 method in order to create 8 more subgrids
     * and then add all of said subgrids into one large 9x9 grid.
     * @param initialGrid int[][]
     * @return int[][]
     */
    public int[][] generate9x9(int[][] initialGrid) {
        int[][] fullGrid = new int[9][9];
        int[][] subGrid1 = generate3x3("horizontal", this.initialGrid);
        int[][] subGrid2 = generate3x3("horizontal", subGrid1);
        int[][] subGrid3 = generate3x3("vertical", this.initialGrid);
        int[][] subGrid4 = generate3x3("horizontal", subGrid3);
        int[][] subGrid5 = generate3x3("horizontal", subGrid4);
        int[][] subGrid6 = generate3x3("vertical", subGrid3);
        int[][] subGrid7 = generate3x3("horizontal", subGrid6);
        int[][] subGrid8 = generate3x3("horizontal", subGrid7);

        fullGrid = this.addToGrid(fullGrid, 0, 0, initialGrid);
        fullGrid = this.addToGrid(fullGrid, 3, 0, subGrid3);
        fullGrid = this.addToGrid(fullGrid, 6, 0, subGrid6);
        fullGrid = this.addToGrid(fullGrid, 0, 3, subGrid1);
        fullGrid = this.addToGrid(fullGrid, 0, 6, subGrid2);
        fullGrid = this.addToGrid(fullGrid, 3, 3, subGrid4);
        fullGrid = this.addToGrid(fullGrid, 3, 6 ,subGrid5);
        fullGrid = this.addToGrid(fullGrid, 6, 3, subGrid7);
        fullGrid = this.addToGrid(fullGrid, 6, 6 , subGrid8);

        return fullGrid;
    }

    /**
     * addToGrid method that will take in an in work grid, row offset, column offset and the sub
     * grid that you want to add to the in work grid. This method will iterate through the sub grid
     * setting the in work grid values to match using the offets to place them correctly.
     * @param gridInWork int[][]
     * @param rowOffset int
     * @param columnOffset int
     * @param subGrid int[][]
     * @return int[][]
     */
    private int[][] addToGrid ( int[][] gridInWork, int rowOffset, int columnOffset, int[][] subGrid){
        for (int row = 0; row < subGrid.length; row++) {
            for (int column = 0; column < subGrid[row].length; column++) {
                gridInWork[row + rowOffset][column + columnOffset] = subGrid[row][column];
            }
        }
        return gridInWork;
    }

    /**
     * randomWithRange will generate a random number using the passed in integers for the min and max of said
     * random number. Will return an int.
     * @param min int
     * @param max int
     * @return int
     */
    private int randomWithRange (int min, int max){
        int range = Math.abs(max - min) + 1;
        return (int) (Math.random() * range) + (min <= max ? min : max);
    }

    /**
     * getRow method that will create an int[] currentRow and dependent upon the grid and the desired row number
     * will save that row and return it as an int[].
     * @param grid int[][]
     * @param rowNumber int
     * @return int[]
     */
    private int[] getRow ( int[][] grid, int rowNumber){
        int[] currentRow = new int[3];
        for (int i = 0; i < grid.length; i++) {
            currentRow[i] = grid[i][rowNumber];
        }
        return currentRow;
    }

    /**
     * getColumn method that will create an int[] currentColumn and dependent upon the grid and the desired
     * column number will save that column and return it as an int[].
     * @param grid int[][]
     * @param columnNumber int
     * @return int[]
     */
    private int[] getColumn ( int[][] grid, int columnNumber){
        int[] currentColumn = new int[3];
        for (int i = 0; i < grid[columnNumber].length; i++) {
            currentColumn[i] = grid[columnNumber][i];
        }
        return currentColumn;
    }

    /**
     * printGrid1D will print out the 1 dimensional array that is passed into it.
     * @param grid int[]
     */
    public static void printGrid1D(int[] grid) {
        System.out.println();
        for(int i = 0; i < grid.length; i ++) {
            if (i % 9 == 0) {
                System.out.println();
            }
            System.out.print(" " + grid[i] + " ");
        }
    }

    /**
     * printGrid2D will print out the 2 dimensional array that is passed into it. Creating line breaks dependent
     * upon the size that is passed in. However only handles size 3 x 3 and 9 x 9 at this time.
     * @param grid int[][]
     * @param size int
     */
    public static void printGrid2D (int[][] grid, int size){
        System.out.println();
        int count = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (size == 3)
                    if (count % 3 == 0)
                        System.out.println();
                if (size == 9)
                    if (count % 9 == 0)
                        System.out.println();
                count++;
                System.out.print(" " + grid[row][column] + " ");
            }
        }
    }
}