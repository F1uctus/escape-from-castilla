package com.nntc.escapefromcastilla.maps.gen;

import com.crown.common.utils.Random;

public class MazeGenerator {
    private static int rows;
    private static int cols;

    public static Cell[][] generate(int rows, int cols) {
        MazeGenerator.rows = rows;
        MazeGenerator.cols = cols;
        Cell[][] grid = new Cell[rows][cols];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                grid[x][y] = Cell.wall;
            }
        }
        carve(grid, 1, 1);
        return grid;
    }

    private static void carve(Cell[][] grid, int startRow, int startCol) {
        grid[startRow][startCol] = Cell.path;

        // Clipping: if it is at edge of grid, don't carve any more, just return.
        if (startRow > rows - 2 || startRow < 1 || startCol > cols - 2 || startCol < 1) {
            return;
        }

        switch (Random.rnd.nextInt(4)) {
            case 0:
                if (hasUnvisited(grid, startRow, startCol)) {
                    if (startCol + 2 <= cols - 2 && grid[startRow][startCol + 2] != Cell.path) {
                        grid[startRow][startCol + 1] = Cell.path;
                        carve(grid, startRow, startCol + 2);
                    }
                    carve(grid, startRow, startCol);
                }
                break;
            case 1:
                if (hasUnvisited(grid, startRow, startCol)) {
                    if (startCol - 2 >= 0 && grid[startRow][startCol - 2] != Cell.path) {
                        grid[startRow][startCol - 1] = Cell.path;
                        carve(grid, startRow, startCol - 2);
                    }
                    carve(grid, startRow, startCol);
                }
                break;
            case 2:
                if (hasUnvisited(grid, startRow, startCol)) {
                    if (startRow + 2 <= rows - 2 && grid[startRow + 2][startCol] != Cell.path) {
                        grid[startRow + 1][startCol] = Cell.path;
                        carve(grid, startRow + 2, startCol);
                    }
                    carve(grid, startRow, startCol);
                }
                break;

            case 3:
                if (hasUnvisited(grid, startRow, startCol)) {
                    if (startRow - 2 >= 0 && grid[startRow - 2][startCol] != Cell.path) {
                        grid[startRow - 1][startCol] = Cell.path;
                        carve(grid, startRow - 2, startCol);
                    }
                    carve(grid, startRow, startCol);
                }
                break;
        }
    }

    private static boolean hasUnvisited(
        Cell[][] grid,
        int startRow,
        int startCol
    ) {
        if (startCol + 2 <= cols - 2 && grid[startRow][startCol + 2] == Cell.wall) {
            return true;
        }
        if (startCol - 2 >= 0 && grid[startRow][startCol - 2] == Cell.wall) {
            return true;
        }
        if (startRow + 2 <= rows - 2 && grid[startRow + 2][startCol] == Cell.wall) {
            return true;
        }
        return startRow - 2 >= 0 && grid[startRow - 2][startCol] == Cell.wall;
    }
}