package sudoku.backend;

/**
 * A single cell of a Sudoku grid.
 * This is a package-private class. Input is assumed to be correct -- error-checking is not provided.
 */
class Cell {

    private int value;
    private boolean locked;

    /**
     * Create a new cell. Default value set to 0.
     */
    Cell() {
        this.value = 0;
        this.locked = false;
    }

    void setValue(int value) {
        this.value = value;
    }

    int getValue() {
        return value;
    }

    void lock() {
        locked = true;
    }

    boolean isLocked() {
        return locked;
    }

}
