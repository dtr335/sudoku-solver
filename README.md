# sudoku-solver
A console-based java program to solve 9x9 and 4x4 Sudoku puzzles. Solutions are found using a brute force algorithm.

Download the latest jar file [here](https://github.com/dtr335/sudoku-solver/raw/master/jar/solver.jar).

Example run:

```
===========================================
Welcome to Sudoku Solver.
Using brute force to solve a Sudoku puzzle.
===========================================

Select a puzzle size (options: 4, 9): 9
Solving for a 9x9 puzzle.

To set up the puzzle:
* Choose a cell to modify. Locations range from 0-80, according to the following key:
+----------+----------+----------+
|  0  1  2 |  3  4  5 |  6  7  8 | 
|  9 10 11 | 12 13 14 | 15 16 17 | 
| 18 19 20 | 21 22 23 | 24 25 26 | 
+----------+----------+----------+
| 27 28 29 | 30 31 32 | 33 34 35 | 
| 36 37 38 | 39 40 41 | 42 43 44 | 
| 45 46 47 | 48 49 50 | 51 52 53 | 
+----------+----------+----------+
| 54 55 56 | 57 58 59 | 60 61 62 | 
| 63 64 65 | 66 67 68 | 69 70 71 | 
| 72 73 74 | 75 76 77 | 78 79 80 | 
+----------+----------+----------+
* Choose a value for the cell. Values range from 1-9.
* (Or, enter a 0 to erase a previously-set cell.)

To solve the puzzle, press ENTER at any time.

Enter a cell to modify (0-80): 0
Enter a value (0-9): 9

Updated Puzzle:
+-------+-------+-------+
| 9 - - | - - - | - - - | 
| - - - | - - - | - - - | 
| - - - | - - - | - - - | 
+-------+-------+-------+
| - - - | - - - | - - - | 
| - - - | - - - | - - - | 
| - - - | - - - | - - - | 
+-------+-------+-------+
| - - - | - - - | - - - | 
| - - - | - - - | - - - | 
| - - - | - - - | - - - | 
+-------+-------+-------+

Key:
+----------+----------+----------+
|  0  1  2 |  3  4  5 |  6  7  8 | 
|  9 10 11 | 12 13 14 | 15 16 17 | 
| 18 19 20 | 21 22 23 | 24 25 26 | 
+----------+----------+----------+
| 27 28 29 | 30 31 32 | 33 34 35 | 
| 36 37 38 | 39 40 41 | 42 43 44 | 
| 45 46 47 | 48 49 50 | 51 52 53 | 
+----------+----------+----------+
| 54 55 56 | 57 58 59 | 60 61 62 | 
| 63 64 65 | 66 67 68 | 69 70 71 | 
| 72 73 74 | 75 76 77 | 78 79 80 | 
+----------+----------+----------+

Enter a cell to modify (0-80): 26
Enter a value (0-9): 9

Updated Puzzle:
+-------+-------+-------+
| 9 - - | - - - | - - - | 
| - - - | - - - | - - - | 
| - - - | - - - | - - 9 | 
+-------+-------+-------+
| - - - | - - - | - - - | 
| - - - | - - - | - - - | 
| - - - | - - - | - - - | 
+-------+-------+-------+
| - - - | - - - | - - - | 
| - - - | - - - | - - - | 
| - - - | - - - | - - - | 
+-------+-------+-------+

Key:
+----------+----------+----------+
|  0  1  2 |  3  4  5 |  6  7  8 | 
|  9 10 11 | 12 13 14 | 15 16 17 | 
| 18 19 20 | 21 22 23 | 24 25 26 | 
+----------+----------+----------+
| 27 28 29 | 30 31 32 | 33 34 35 | 
| 36 37 38 | 39 40 41 | 42 43 44 | 
| 45 46 47 | 48 49 50 | 51 52 53 | 
+----------+----------+----------+
| 54 55 56 | 57 58 59 | 60 61 62 | 
| 63 64 65 | 66 67 68 | 69 70 71 | 
| 72 73 74 | 75 76 77 | 78 79 80 | 
+----------+----------+----------+

Enter a cell to modify (0-80): 18
Enter a value (0-9): 7

Updated Puzzle:
+-------+-------+-------+
| 9 - - | - - - | - - - | 
| - - - | - - - | - - - | 
| 7 - - | - - - | - - 9 | 
+-------+-------+-------+
| - - - | - - - | - - - | 
| - - - | - - - | - - - | 
| - - - | - - - | - - - | 
+-------+-------+-------+
| - - - | - - - | - - - | 
| - - - | - - - | - - - | 
| - - - | - - - | - - - | 
+-------+-------+-------+

Key:
+----------+----------+----------+
|  0  1  2 |  3  4  5 |  6  7  8 | 
|  9 10 11 | 12 13 14 | 15 16 17 | 
| 18 19 20 | 21 22 23 | 24 25 26 | 
+----------+----------+----------+
| 27 28 29 | 30 31 32 | 33 34 35 | 
| 36 37 38 | 39 40 41 | 42 43 44 | 
| 45 46 47 | 48 49 50 | 51 52 53 | 
+----------+----------+----------+
| 54 55 56 | 57 58 59 | 60 61 62 | 
| 63 64 65 | 66 67 68 | 69 70 71 | 
| 72 73 74 | 75 76 77 | 78 79 80 | 
+----------+----------+----------+

Enter a cell to modify (0-80): 

The puzzle has been solved! Here is a solution:
+-------+-------+-------+
| 9 1 2 | 3 4 5 | 6 7 8 | 
| 3 4 6 | 7 8 9 | 1 2 5 | 
| 7 5 8 | 1 2 6 | 3 4 9 | 
+-------+-------+-------+
| 1 2 3 | 4 5 7 | 8 9 6 | 
| 4 6 5 | 2 9 8 | 7 1 3 | 
| 8 7 9 | 6 1 3 | 2 5 4 | 
+-------+-------+-------+
| 2 3 1 | 5 6 4 | 9 8 7 | 
| 5 8 7 | 9 3 1 | 4 6 2 | 
| 6 9 4 | 8 7 2 | 5 3 1 | 
+-------+-------+-------+
```
