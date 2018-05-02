package sudoku.frontend;

import sudoku.backend.Puzzle;

import java.util.Scanner;

/**
 * Provides a frontend interface to run a console-based Sudoku solver
 */
public class Frontend {

    public static void main(String[] args) {

        Puzzle puzzle = new Puzzle();

        System.out.println("===============================================");
        System.out.println("Welcome to Sudoku Solver.");
        System.out.println("Using brute force to solve a 4x4 Sudoku puzzle.");
        System.out.println("===============================================");
        System.out.println();

        System.out.println("To set up the puzzle:");
        System.out.println("* Choose a cell to modify. Locations range from 0-15, according to the following key:");
        displayLocations();
        System.out.println("* Choose a value for the cell. Values range from 1-4. Alternatively, entering a 0 resets a cell, erasing its current value.");
        System.out.println();
        System.out.println("To solve the puzzle, press ENTER at any time.");
        System.out.println();

        Scanner sc = new Scanner(System.in);

        // start setting up puzzle
        while (true) {

            // get position from user
            int position = -1;
            boolean finishedSetup = false;
            while (true) {
                System.out.print("Enter a cell to modify (0-15): ");
                String input = sc.nextLine();
                if (input.equals("")) {
                    finishedSetup = true;
                    break;
                }
                try {
                    position = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter an integer.");
                    continue;
                }
                if (position < 0 || position > 15) {
                    System.out.println("Please enter a location from 0 to 15.");
                    continue;
                }
                break;
            }
            // get value from user
            int value = -1;
            while (!finishedSetup) { // don't need to do this part if user wants to solve now
                System.out.print("Enter a value (0-4): ");
                String input = sc.nextLine();
                if (input.equals("")) {
                    finishedSetup = true;
                    break;
                }
                try {
                    value = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter an integer.");
                    continue;
                }
                if (value < 0 || value > 4) {
                    System.out.println("Please enter a value from 0 to 4.");
                    continue;
                }
                break;
            }

            // check to see if user wants to continue setting up or solve
            if (!finishedSetup) {
                puzzle.setPresetCell(position, value); // this method could return false ... but, already error-checked earlier
                System.out.println();
                System.out.println("Updated Puzzle:");
                displayPuzzle(puzzle);
                System.out.println();
                System.out.println("Key:");
                displayLocations();
                System.out.println();
                continue;
            }

            break;
        }

        // solve now
        boolean result = puzzle.solve();
        System.out.println();
        if (result) {
            System.out.println("The puzzle has been solved! Here is a solution:");
            displayPuzzle(puzzle);
        } else {
            System.out.println("This puzzle is unsolvable!");
        }
        System.out.println();

    }

    private static void displayPuzzle(Puzzle puzzle) {
        for (int i=0; i<4; i++) {
            if (i % 2 == 0) {
                System.out.println("+-----+-----+");
            }
            System.out.print("| ");
            for (int j=0; j<4; j++) {
                int pos = 4*i + j;

                if (puzzle.getCellValue(pos) == 0) {
                    System.out.print("-");
                } else {
                    System.out.print(puzzle.getCellValue(pos));
                }

                if (j % 2 == 1) {
                    System.out.print(" | ");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println("+-----+-----+");
    }

    private static void displayLocations() {
        for (int i=0; i<4; i++) {
            if (i % 2 == 0) {
                System.out.println("+-------+-------+");
            }
            System.out.print("| ");
            for (int j=0; j<4; j++) {
                int pos = 4*i + j;
                if (pos < 10) {
                    System.out.print(" " + pos);
                } else {
                    System.out.print(pos);
                }
                if (j % 2 == 1) {
                    System.out.print(" | ");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println("+-------+-------+");
    }

}
