import java.util.*;

public class Main {
    private static final int SIZE = 9;  // The size of the Sudoku grid (9x9)
    private static int[][] board;       // Board to store the current state of the puzzle
    private static int[][] solution;    // Solution grid to hold the correct answers

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Sudoku Puzzle Game!\uD83E\uDDE9");

        // Main game loop
        while (true) {
            // Prompt user to select difficulty
            System.out.println("\nChoose difficulty level:\n1. Easy \uD83C\uDF40 \n2. Medium \uD83C\uDF1F \n3. Hard \uD83C\uDF4E \nEnter your choice: ");
            int difficulty = scanner.nextInt();

            // Generate puzzle based on selected difficulty
            generatePuzzle(difficulty);
            long startTime = System.currentTimeMillis();  // Start the timer

            // Game loop while puzzle is not solved
            while (!isSolved()) {
                displayBoard();  // Display the current state of the board
                System.out.println("Enter your solution (row col value)\uD83D\uDE80, 'h' for a hint\uD83D\uDD11, or 'q' to quit: ");

                String input = scanner.next();
                if (input.equalsIgnoreCase("q")) {
                    // Exit the game
                    System.out.println("Thanks for playing!\uD83D\uDE0A");
                    return;
                } else if (input.equalsIgnoreCase("h")) {
                    // Provide a hint
                    provideHint();
                    continue;
                }

                // Parse the user's input for row, column, and value
                int row = Integer.parseInt(input) - 1;
                int col = scanner.nextInt() - 1;
                int value = scanner.nextInt();

                // Validate the move and place the number on the board if valid
                if (isValidMove(row, col, value)) {
                    board[row][col] = value;
                } else {
                    // Notify the user if the move is invalid
                    System.out.println("Invalid move❌. Try again.");
                }
            }

            // Calculate and display the time taken to solve the puzzle
            long timeTaken = (System.currentTimeMillis() - startTime) / 1000;
            System.out.println("Congratulations!\uD83C\uDFC6 You solved the Sudoku puzzle in " + timeTaken + " seconds!⏰");

            // Ask the user if they want to play again
            System.out.println("Do you want to play again\uD83D\uDD04? (Y/N): ");
            if (scanner.next().equalsIgnoreCase("N")) {
                System.out.println("Thank you for playing Sudoku Puzzle Game!\uD83D\uDE0A");
                break;  // Exit the game
            }
        }
        scanner.close();
    }

    // Generate a new puzzle based on the difficulty level
    private static void generatePuzzle(int difficulty) {
        board = new int[SIZE][SIZE];  // Reset the board
        solution = new int[SIZE][SIZE];  // Reset the solution
        Random rand = new Random();

        // Fill the solution grid with valid numbers
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                solution[i][j] = (i * 3 + i / 3 + j) % SIZE + 1;
            }
        }

        // Determine the number of clues based on difficulty
        int clues = difficulty == 1 ? 40 : (difficulty == 2 ? 30 : 20);
        Set<String> used = new HashSet<>();
        while (used.size() < clues) {
            // Randomly place clues on the board
            int r = rand.nextInt(SIZE);
            int c = rand.nextInt(SIZE);
            String key = r + "," + c;
            if (!used.contains(key)) {
                board[r][c] = solution[r][c];
                used.add(key);  // Ensure that clues don't overlap
            }
        }
    }

    // Display the current Sudoku board with grid separators
    private static void displayBoard() {
        System.out.println("Sudoku Puzzle:");
        for (int i = 0; i < SIZE; i++) {
            // Add horizontal separator after every 3 rows
            if (i % 3 == 0 && i != 0) {
                System.out.println("----------------------");
            }
            for (int j = 0; j < SIZE; j++) {
                // Add vertical separator after every 3 columns
                if (j % 3 == 0 && j != 0) System.out.print(" | ");
                // Print either the number or an underscore for empty cells
                System.out.print((board[i][j] == 0 ? "_" : board[i][j]) + " ");
            }
            System.out.println();
        }
    }

    // Check if a move is valid based on Sudoku rules
    private static boolean isValidMove(int row, int col, int value) {
        // Check if the cell is already filled
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || board[row][col] != 0) return false;

        // Check the row and column for duplicate values
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == value || board[i][col] == value) return false;
        }

        // Check the 3x3 box for duplicate values
        int boxRow = (row / 3) * 3, boxCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[boxRow + i][boxCol + j] == value) return false;
            }
        }
        return true;
    }

    // Check if the puzzle has been solved (no empty cells)
    private static boolean isSolved() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) return false;  // If there are any empty cells
            }
        }
        return true;  // All cells are filled
    }

    // Provide a hint by filling one empty cell with the correct value
    private static void provideHint() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    // Fill the cell with the correct value from the solution
                    board[i][j] = solution[i][j];
                    System.out.println("Hint\uD83D\uDD11: Placed " + solution[i][j] + " at row " + (i + 1) + ", column " + (j + 1));
                    return;
                }
            }
        }
        System.out.println("No hints available. The puzzle is almost solved!");  // No empty cells left
    }
}

