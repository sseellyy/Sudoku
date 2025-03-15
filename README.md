# Sudoku Puzzle Game Documentation
## 1. Project Description, Design Choices, and Challenges

### Project Overview:
 The project is a simple text-based Sudoku puzzle game written in Java. The goal of the game is to provide users with an interactive Sudoku experience, where they can select a difficulty level, solve the puzzle, and receive hints. The game tracks the time taken to solve the puzzle and provides feedback on whether a move is valid.

### Design Choices:
  * **Difficulty Levels:** The game has three difficulty levels (Easy, Medium, and Hard). The number of clues (pre-filled numbers) on the board is adjusted based on the selected difficulty, with easier puzzles having more clues and harder puzzles fewer.
  * **Interactive Gameplay:** The game runs in a continuous loop, prompting the user for inputs (row, column, and value) while displaying the current board state.
  * **Validation:** Moves are validated based on Sudoku rules, checking rows, columns, and 3x3 sub-grids for conflicts.
  * **Hints:** The game offers hints where the user can receive the correct number for an empty cell. This is done by revealing one empty cell at a time.
  * **Timer:** The time taken to solve the puzzle is tracked and displayed at the end of the game.
   
### Challenges Encountered:
  * **Puzzle Generation:** Generating a valid Sudoku puzzle while ensuring that the board adheres to Sudoku rules, and the difficulty level is respected, was challenging.
  * **User Input Validation:** Handling user input in a way that is both robust and user-friendly was a key consideration, especially given the various forms of invalid inputs (e.g., non-numeric inputs or out-of-bound values).


## 2. Algorithms and Data Structures

### Data Structures:
* **2D Arrays (int[][] board, int[][] solution):** Used to represent the Sudoku grid. The board array holds the user's current progress, while the solution array holds the correct solution.
* **HashSet (Set<String> used):** Used to ensure that clues are placed randomly without duplication. This prevents the same cell from being filled with multiple clues.

### Algorithms:
* **Puzzle Generation:**
  * The solution grid is initially filled with valid Sudoku numbers. A simple formula is used to assign numbers based on their row and column positions.
  * Random clues are then placed on the board based on the selected difficulty, ensuring that the clues do not overlap.
* **Move Validation:**
  * **Row and Column Check:** The algorithm checks whether the number already exists in the given row or column.
  * **3x3 Sub-grid Check:** The algorithm also checks whether the number appears in the 3x3 sub-grid of the specified cell.
* **Hint Generation:** A hint is provided by revealing the correct number from the solution array at the first empty cell.


## 3. Improvements Made

* **Initial Implementation:** The initial version did not have user feedback for invalid moves or the hint system. It also lacked a timer to measure how long the user took to solve the puzzle.

* **Enhancements:**
  1. **Input Validation:** Improved user input handling to ensure valid moves are made.
  2. **Timer:** Added functionality to track the time taken to solve the puzzle.
  3. **Hints:** Implemented a hint system to help players when they are stuck.
  4. **Difficulty Levels:** Introduced multiple difficulty levels to cater to players with varying skill levels.
 

## 4. Files Used for Input/Output

**No external files** are used for input or output in this implementation. The game relies entirely on the console for input (e.g., the user enters their moves) and output (e.g., displaying the current state of the board, providing feedback, and displaying time taken).


## 5. Additional Explanations

* **Grid Display:** The board is displayed in a user-friendly manner with separators for 3x3 sub-grids, making it easy for the user to follow along with their progress.
* **Hints:** The hint system provides feedback when the player asks for it, which helps players solve the puzzle if they get stuck. The game places one correct number in an empty cell at a time.
* **Exit Option:** Users can quit the game at any time by entering 'q', which will print a goodbye message and terminate the program.
* **Game Flow:** The game runs continuously, prompting the user for moves until the puzzle is solved or the player opts to quit. The program provides feedback on the player's moves and informs them if they are correct or incorrect.
