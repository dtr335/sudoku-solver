package sudoku.backend;

/**
 * A representation of a 4x4 Sudoku grid. Provides methods for setting up the puzzle and solving it (via brute force).
 * This is a package-private class. Input is assumed to be correct -- error-checking is not provided.
 */
class Grid {

    final int PUZZLE_SIZE = 16;
    final int MAX_DIGIT = 4;

    private Cell[] grid;

    Grid() {
        grid = new Cell[PUZZLE_SIZE];
        for (int i=0; i<PUZZLE_SIZE; i++) {
            grid[i] = new Cell();
        }
    }

    /**
     * DEPRECATED
     * Used for setting up the grid before solving. A preset cell must be locked, or it will be changed during the solving phase.
     * @param position
     * @param value
     */
    void setAndLockCell(int position, int value) {
        grid[position].setValue(value);
        grid[position].lock();
    }

    /**
     * Used for setting up the grid before solving. Erase a previously set cell by entering 0 as its value.
     * @param position
     * @param value
     */
    void setCell(int position, int value) {
        grid[position].setValue(value);
    }

    /**
     * Retrieve the value at the given cell. Useful after the puzzle has been solved, for displaying to user.
     * @param position
     * @return
     */
    int getCellValue(int position) {
        return grid[position].getValue();
    }

    /**
     * Attempt to solve the puzzle.
     * @return true if a solution was found, false if the puzzle is unsolvable
     */
    boolean solve() {

        // lock all user pre-filled cells
        lockPresetCells();

        int pos = 0;
        boolean backtracking = false; // need to keep track of direction, so the solver knows which direction to move upon reaching a locked cell
        while (pos < PUZZLE_SIZE) {

            // check backtracking: if pos < 0, the puzzle is unsolvable
            if (pos < 0) {
                break;
            }

            // ignore locked cells
            if (grid[pos].isLocked()) {
                if (backtracking) {
                    pos--;
                } else {
                    pos++;
                }
                continue;
            }

            boolean numberFound = false;

            // try numbers in the cell, starting with its current value + 1
            for (int tryNumber = grid[pos].getValue()+1; tryNumber <= MAX_DIGIT; tryNumber++) {

                boolean clashFound = false;

                ////// CHECK ROW

                // find the row
                int row = pos / MAX_DIGIT;

                // iterate through row
                for (int j = 0; j < MAX_DIGIT; j++) {
                    int r = row * MAX_DIGIT + j;
                    if (r == pos) { // don't compare the current cell to itself
                        continue;
                    }
                    // compare the cells and check for a clash
                    if (grid[r].getValue() == tryNumber) {
                        // clash found, need to try next number
                        clashFound = true;
                        break;
                    }
                }

                // check results: if clash found, quit and try the next number. if no clash, move on to check the column
                if (clashFound) {
                    continue;
                }

                ////// CHECK COLUMN

                // find the column
                int col = pos % MAX_DIGIT;

                // iterate through column
                for (int j = 0; j < MAX_DIGIT; j++) {
                    int c = col + MAX_DIGIT * j;
                    if (c == pos) { // don't compare current cell to itself
                        continue;
                    }
                    // compare the cells and search for a clash
                    if (grid[c].getValue() == tryNumber) {
                        // clash found, need to try next number
                        clashFound = true;
                        break;
                    }
                }

                // check results: if clash found, quit and try the next number. if no clash, move on to check the box
                if (clashFound) {
                    continue;
                }

                ////// CHECK BOX

                // find the box
                // old calc -- fixed values
                // int box = 2 * (pos / 8) + (pos % 4) / 2;
                // new calc -- variable values
                int sqrt = (int) Math.sqrt(MAX_DIGIT);
                int box = sqrt * (pos / (MAX_DIGIT*sqrt)) + (pos % MAX_DIGIT) / sqrt;

                // iterate through box
                for (int j = 0; j < MAX_DIGIT; j++) {

                    //old calc -- fixed values
                    //int b = (box/2)*8 + (box%2)*2 + (j/2)*4 + j%2;
                    //new calc -- variable values
                    int b = (box/sqrt)*(MAX_DIGIT*sqrt) + (box % sqrt)*sqrt + (j/sqrt)*MAX_DIGIT + j % sqrt;


                    if (b == pos) { // don't compare current cell to itself
                        continue;
                    }
                    // compare the cells and search for a clash
                    if (grid[b].getValue() == tryNumber) {
                        // clash found, need to try next number
                        clashFound = true;
                        break;
                    }
                }

                // check results: if clash found, quit and try the next number. if no clash, this number is good, set it and move on to the next position
                if (clashFound) {
                    continue;
                } else {
                    grid[pos].setValue(tryNumber);
                    numberFound = true;
                    break;
                }
            }

            // at this point, one of two things could have occurred:
            // 1) a valid number was found for the cell. In this case, we need to increment the position and move on
            // 2) no valid numbers were found for the cell. In this case, we need to wipe the cell, decrement the position, and backtrack

            if (numberFound) {
                backtracking = false;
                pos++;
            } else {
                grid[pos].setValue(0);
                backtracking = true;
                pos--;
            }
        }

        // at this point, the puzzle is either completely solved, or deemed unsolvable
        if (pos > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Locks all nonzero cells
     */
    private void lockPresetCells() {
        for (Cell cell : grid) {
            if (cell.getValue() > 0) {
                cell.lock();
            }
        }
    }

/*
some math (for 4x4):

row:
row base = position / 4
row x = 4x+0, 4x+1, 4x+2, 4x+3

column:
column base = position % 4
col x = x+4*0, x+4*1, x+4*2, x+4*3

box:
math for box is tricky
box base = 2*(position/8)+(position%4)/2
once I know the base, I can calculate the 4 positions by:
(base/2)*8+(base%2)*2 + (j/2)*4+j%2
NOTE: parenthesis are important. order of operations matter
*/

}
