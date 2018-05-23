public class GridGeneration {
    private int[][] initialGrid = new int[3][3];
    private final int upperbound = 9;
    private final int lowerbound = 1;
    private int random;
    int[] playerGrid = null; //test.toPlayerGrid(temp1D);
    int[] finalGrid = null;

    public static void main(String[] args) {
        GridGeneration test = new GridGeneration();
        test.initialCreation();
        printGrid(test.initialGrid, 3);
        int[][] temp = test.generate9x9(test.initialGrid);
        printGrid(temp, 9);
        int[] temp1D = test.toSingleArray(temp);
        int[] playerGrid = test.toPlayerGrid(temp1D);
        System.out.println("\nPlayer Grid");
        printGrid1D(playerGrid);
    }

    /**
     * getFinalGrid1D
     * @return int[]
     */
//    public int[] getFinalGrid1D() {
//        return this.finalGrid1D;
//    }

    /**
     * getPlayerGrid
     * @return int[]
     */
    public int[] getPlayerGrid() {
        return this.playerGrid;
    }

    /**
     * toPlayerGrid method
     * @param fullGrid int[]
     * @return int[]
     */
    public int[] toPlayerGrid(int[] fullGrid) {
        int[] playerGrid = new int[81];
        for(int i = 0; i < playerGrid.length;i++) {
            if(((i % 6) != 0) && (i % 4) != 0) {
                playerGrid[i] = fullGrid[i];
            }
        }
        return playerGrid;
    }

    public int[] toSingleArray(int[][] tempGrid) {
        int[] finalGrid = new int[tempGrid.length * tempGrid.length];
        for(int row = 0; row < tempGrid.length;row ++) {
            for(int column = 0; column < tempGrid[row].length;column++) {
                finalGrid[(row * tempGrid.length) + column] = tempGrid[row][column];
            }
        }
//        System.out.println();
//        for(int i = 0; i < finalGrid.length; i ++) {
//            if (i % 9 == 0) {
//                System.out.println();
//            }
//            System.out.print(" " + finalGrid[i] + " ");
//        }
        return finalGrid;
    }

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

    private int[][] addToGrid ( int[][] gridInWork, int rowOffset, int columnOffset, int[][] subGrid){
        for (int row = 0; row < subGrid.length; row++) {
            for (int column = 0; column < subGrid[row].length; column++) {
                gridInWork[row + rowOffset][column + columnOffset] = subGrid[row][column];
            }
        }
        return gridInWork;
    }

    private int randomWithRange ( int min, int max){
        int range = Math.abs(max - min) + 1;
        return (int) (Math.random() * range) + (min <= max ? min : max);
    }

    private int[] getRow ( int[][] grid, int rowNumber){
        int[] currentRow = new int[3];
        for (int i = 0; i < grid.length; i++) {
            currentRow[i] = grid[i][rowNumber];
        }
        return currentRow;
    }

    private int[] getColumn ( int[][] grid, int columnNumber){
        int[] currentColumn = new int[3];
        for (int i = 0; i < grid[columnNumber].length; i++) {
            currentColumn[i] = grid[columnNumber][i];
        }
        return currentColumn;
    }

    public static void printGrid1D(int[] grid) {
        System.out.println();
        for(int i = 0; i < grid.length; i ++) {
            if (i % 9 == 0) {
                System.out.println();
            }
            System.out.print(" " + grid[i] + " ");
        }
    }

    public static void printGrid (int[][] grid, int size){
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
