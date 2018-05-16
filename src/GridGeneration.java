public class GridGeneration {
    private int[][] initialGrid = new int[3][3];
    private int upperbound = 9;
    private int lowerbound = 1;
    private int random;

    public static void main(String[] args) {
        GridGeneration test = new GridGeneration();
        test.initialCreation();
        test.printGrid(test.initialGrid, 3);
        test.generate9x9(test.initialGrid);
//        int[][] vertical1 = test.generate3x3("vertical", test.initialGrid);
//        System.out.println("\n\nVertical\n");
//        test.printGrid(vertical1);

//        System.out.println("\n\nHorizontal\n");
//        int[][] horizontal1 = test.generate3x3("horizontal", test.initialGrid);
//        test.printGrid(horizontal1);


//        System.out.println("\nRow 1");
//        int[] row1 = test.getRow(test.initialGrid, 0);
//        for(int i = 0; i < row1.length; i++) {
//            System.out.println(row1[i]);
//        }
//
//        System.out.println("\nRow 2");
//        int[] row2 = test.getRow(test.initialGrid, 1);
//        for(int i = 0; i < row2.length; i++) {
//            System.out.println(row2[i]);
//        }
//
//        System.out.println("\nRow 3");
//        int[] row3 = test.getRow(test.initialGrid, 2);
//        for(int i = 0; i < row3.length; i++) {
//            System.out.println(row3[i]);
//        }

//        System.out.println("\nColumn 1");
//        int[] column1 = test.getColumn(test.initialGrid, 0);
//        for(int i = 0; i < column1.length; i++) {
//            System.out.println(column1[i]);
//        }
//
//        System.out.println("\nColumn 2");
//        int[] column2 = test.getRow(test.initialGrid, 1);
//        for(int i = 0; i < column2.length; i++) {
//            System.out.println(column2[i]);
//        }
//
//        System.out.println("\nColumn 3");
//        int[] column3 = test.getColumn(test.initialGrid, 2);
//        for(int i = 0; i < column3.length; i++) {
//            System.out.println(column3[i]);
//        }

//        int upperbound = 9;
//        int lowerbound = 1;
//        int random = (int)(Math.random() * ((upperbound - lowerbound) + 1) + lowerbound);
//        System.out.println(random);
    }

    public void initialCreation() {
        for (int row = 0; row < initialGrid.length; row++) {
            for (int column = 0; column < initialGrid[row].length; column++) {
                do {
                    random = (int) (Math.random() * ((upperbound - lowerbound) + 1) + lowerbound);
//                    System.out.println("Within do " + random);
                } while (!checkRules(random));
                //cellGrid[row] = new Cell(random, row);
                initialGrid[row][column] = random;
//                System.out.println("Outside do " + random);

                //while (checkRules(random));
                //System.out.println("within my initalCreation method" + row);
            }
        }
    }

    public static int[][] createCell(int cellValue) {
        int[][] grid = new int[3][3];
        return grid;
    }

    public boolean checkRules(int cellValue) {
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

    public int[][] generate3x3(String direction, int[][] grid) {
        int[][] subGrid = new int[3][3];
        int[] row1;
        int[] row2;
        int[] row3;
        int[] column1;
        int[] column2;
        int[] column3;

        if(direction.equalsIgnoreCase("vertical")) {
            row1 = this.getRow(grid, 0);
            row2 = this.getRow(grid, 1);
            row3 = this.getRow(grid, 2);

            for(int count = 0; count < 3; count++) {
                for (int i = 0; i < 3; i++) {
                    if (count == 0) {
                        subGrid[i][count] = row2[i];
                    }
                    else if(count == 1)
                        subGrid[i][count] = row3[i];
                    else
                        subGrid[i][count] = row1[i];
                }
            }
        }
        else if(direction.equalsIgnoreCase("horizontal")) {
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
        int[][] subGrid1;
        int[][] subGrid2;
        int[][] subGrid3;// = new int[3][3];
        int[][] subGrid4;
        int[][] subGrid5;
        int[][] subGrid6;// = new int[3][3];
        int[][] subGrid7;
        int[][] subGrid8;

                fullGrid = this.addToGrid(fullGrid, 0, initialGrid);
                subGrid3 = generate3x3("vertical", this.initialGrid);
                fullGrid = this.addToGrid(fullGrid, 3, subGrid3);
                subGrid6 = generate3x3("vertical", subGrid3);
                fullGrid = this.addToGrid(fullGrid, 6, subGrid6);
                subGrid1 = generate3x3("horizontal", this.initialGrid);
                subGrid2 = generate3x3("horizontal", subGrid1);

                subGrid4 = generate3x3("horizontal", subGrid3);
                subGrid5 = generate3x3("horizontal", subGrid4);

                subGrid7 = generate3x3("horizontal", subGrid6);
                subGrid8 = generate3x3("horizontal", subGrid7);

                System.out.println("\n");
                this.printGrid(fullGrid, 9);
        return fullGrid;
    }

    private int[][] addToGrid(int[][] gridInWork, int index, int[][] subGrid) {
        for(int row = index; row < subGrid.length; row++) {
            for(int column = index; column < subGrid[row].length; column++) {
                if(index == 0) {
                    gridInWork[row][column] = subGrid[row][column];
                }
                else if(index == 3 || index == 6) {
                    gridInWork[row+index][column] = subGrid[row][column];
                }
                gridInWork[row][column+index] = subGrid[row][column];
            }
        }
        return gridInWork;
    }

    public int randomWithRange(int min, int max) {
        int range = Math.abs(max - min) + 1;
        return (int) (Math.random() * range) + (min <= max ? min : max);
    }

    public int[] getRow(int[][] grid, int rowNumber) {
        int[] currentRow = new int[3];
        for(int i = 0; i < grid.length; i ++) {
            currentRow[i] = grid[i][rowNumber];
        }
        return currentRow;
    }

    public int[] getColumn(int[][] grid, int columnNumber) {
        int[] currentColumn = new int[3];
        for(int i = 0; i < grid[columnNumber].length; i ++) {
            currentColumn[i] = grid[columnNumber][i];
        }
        return currentColumn;
    }

    public void printGrid(int[][] grid, int size) {
        int count = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if(size == 3) {
                    if(count == 3 || count == 6 || count == 9) {
                        System.out.println();
                    }
                }
                if(size == 9) {
                    if(count == 9 || count == 18 || count == 27 || count == 36 || count == 45 || count == 54 ||
                            count == 63 || count == 72 || count == 81) {
                        System.out.println();
                    }
                }
                count++;
                System.out.print(" " + grid[row][column] + " ");

            }
        }
    }

//    private class Cell {
//        private int cellValue;
//        private int uniqueID;
//
//        public Cell(int value, int id) {
//            this.cellValue = value;
//            this.uniqueID = id;
//        }
//
//        public void newCell(int value, int id) {
//            this.cellValue = value;
//            this.uniqueID = id;
//        }
//    }
}
