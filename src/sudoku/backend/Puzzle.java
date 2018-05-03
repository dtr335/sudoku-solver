package sudoku.backend;

/**
 * Provides a backend interface for creating and solving a 4x4 or 9x9 Sudoku puzzle.
 */
public class Puzzle {

    private Grid grid;

    /**
     * Creates an empty Sudoku puzzle.
     * @param size Size of board. Values of 4 or 9 accepted. Other values default to 9.
     */
    public Puzzle(int size) {
        grid = new Grid(size);
    }

    /**
     * Allows the frontend to set user-filled preset cells, before solving the puzzle.
     * Invalid input (see parameters) will be IGNORED!
     * @param position cell's position on the board, ranging from 0 to (size of puzzle)-1. (15 for 4x4, 80 for 9x9)
     * @param value cell's value, ranging from 0 to max digit (4 or 9). A '0' value will reset a previously-filled cell.
     */
    public void setPresetCell(int position, int value) {
        if (position < 0 || position > grid.getPuzzleSize()-1
                || value < 0 || value > grid.getMaxDigit()) {
            return;
        }
        grid.setCell(position, value);
    }

    /**
     * Attempt to solve the puzzle.
     * @return true if a solution was found, false if the puzzle is unsolvable
     */
    public boolean solve() {
        return grid.solve();
    }

    /**
     * Allows the frontend to view the cell's current value. Useful after the puzzle has been solved, for displaying to user.
     * @param position position of cell on board
     * @return value at given position
     */
    public int getCellValue(int position) {
        return grid.getCellValue(position);
    }

    public int getMaxDigit() {
        return grid.getMaxDigit();
    }

    public int getPuzzleSize() {
        return grid.getPuzzleSize();
    }

}