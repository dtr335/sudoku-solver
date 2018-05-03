package sudoku.frontend;

import sudoku.backend.Puzzle;

import java.util.Scanner;

/**
 * An interactive console app for solving a Sudoku puzzle.
 */
public class Frontend {

    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println("Welcome to Sudoku Solver.");
        System.out.println("Using brute force to solve a Sudoku puzzle.");
        System.out.println("===========================================");
        System.out.println();

        Scanner sc = new Scanner(System.in);

        // get puzzle size
        int maxDigit;
        do {
            System.out.print("Select a puzzle size (options: 4, 9): ");
            String input = sc.nextLine();
            try {
                maxDigit = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a 4 or a 9.");
                continue;
            }
            if (maxDigit == 4 || maxDigit == 9) { // valid input
                break;
            } else {
                System.out.println("Please enter a 4 or a 9.");
            }
        } while (true);

        Puzzle puzzle = new Puzzle(maxDigit);
        int maxPosition = maxDigit*maxDigit - 1;

        System.out.println();
        System.out.printf("Solving for a %dx%d puzzle.\n", maxDigit, maxDigit);
        System.out.println();

        System.out.println("To set up the puzzle:");
        System.out.printf("* Choose a cell to modify. Locations range from 0-%d, according to the following key:\n", maxPosition);
        displayLocations(puzzle);
        System.out.printf("* Choose a value for the cell. Values range from 1-%d.\n", maxDigit);
        System.out.println("* (Or, enter a 0 to erase a previously-set cell.)");
        System.out.println();
        System.out.println("To solve the puzzle, press ENTER at any time.");
        System.out.println();


        // start setting up puzzle
        do {
            // get position from user
            int position = -1;
            boolean finishedSetup = false;
            do {
                System.out.printf("Enter a cell to modify (0-%d): ", maxPosition);
                String input = sc.nextLine();
                if (input.equals("")) { // user hit enter, solve now
                    finishedSetup = true;
                    break;
                }
                try {
                    position = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter an integer.");
                    continue;
                }
                if (position >= 0 && position <= maxPosition) { // valid input
                    break;
                } else {
                    System.out.printf("Please enter a location from 0 to %d.\n", maxPosition);
                }
            } while (true);
            // get value from user
            int value = -1;
            while (!finishedSetup) { // don't need to do this part if user wants to solve now
                System.out.printf("Enter a value (0-%d): ", maxDigit);
                String input = sc.nextLine();
                if (input.equals("")) { // user hit enter, solve now
                    finishedSetup = true;
                    break;
                }
                try {
                    value = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter an integer.");
                    continue;
                }
                if (value >= 0 && value <= maxDigit) { // valid input
                    break;
                } else {
                    System.out.printf("Please enter a value from 0 to %d.\n", maxDigit);
                }
            }

            if (finishedSetup) {
                break;
            } else {
                puzzle.setPresetCell(position, value);
                System.out.println();
                System.out.println("Updated Puzzle:");
                displayPuzzle(puzzle);
                System.out.println();
                System.out.println("Key:");
                displayLocations(puzzle);
                System.out.println();
            }
        } while (true);

        // solve now
        boolean result = puzzle.solve();
        System.out.println();
        if (result) {
            System.out.println("The puzzle has been solved! Here is a solution:");
            displayPuzzle(puzzle);
        } else {
            System.out.println("This puzzle is unsolvable.");
        }
        System.out.println();

    }

    private static void displayPuzzle(Puzzle puzzle) {
        int maxDig = puzzle.getMaxDigit();
        int sqrt = (int) Math.sqrt(maxDig);
        for (int i=0; i<maxDig; i++) {
            if (i % sqrt == 0) {
                printRowSpacerPuzzle(sqrt);
            }
            StringBuilder sb = new StringBuilder("| ");
            for (int j = 0; j < maxDig; j++) {
                int pos = maxDig * i + j;
                int val = puzzle.getCellValue(pos);
                if (val == 0) {
                    sb.append("-");
                } else {
                    sb.append(val);
                }
                if (j % sqrt == sqrt-1) { // print out a pipe immediately following new column
                    sb.append(" |");
                }
                sb.append(" ");
            }
            System.out.println(sb);
        }
        printRowSpacerPuzzle(sqrt);


    }

    private static void displayLocations(Puzzle puzzle) {
        int maxDig = puzzle.getMaxDigit();
        int sqrt = (int) Math.sqrt(maxDig);
        for (int i = 0; i < maxDig; i++) {
            if (i % sqrt == 0) {
                printRowSpacerLocations(sqrt);
            }
            StringBuilder sb = new StringBuilder("| ");
            for (int j = 0; j < maxDig; j++) {
                int pos = maxDig*i + j;
                if (pos < 10) { // print out extra space for numbers < 10 (formatting)
                    sb.append(" ");
                }
                sb.append(pos);
                if (j % sqrt == sqrt-1) { // print out a pipe immediately following new column
                    sb.append(" |");
                }
                sb.append(" ");
            }
            System.out.println(sb);
        }
        printRowSpacerLocations(sqrt);
    }

    private static void printRowSpacerPuzzle(int columns) {
        if (columns == 2) System.out.println("+-----+-----+");
        else if (columns == 3) System.out.println("+-------+-------+-------+");
    }

    private static void printRowSpacerLocations(int columns) {
        if (columns == 2) System.out.println("+-------+-------+");
        else if (columns == 3) System.out.println("+----------+----------+----------+");
    }

    /* A probably-overkill method for printing row spacers
    private static void printSpacer(int columns) {
        StringBuilder sb = new StringBuilder("+");
        for (int i = 0; i < columns; i++) {
            int numDashes = 2 * columns + columns + 1;
            for (int j = 0; j < numDashes; j++) {
                sb.append("-");
            }
            sb.append("+");
        }
        System.out.println(sb);
    }
    */

}
