import java.util.*;

public class Main
{
    private static final int SIZE = 9;
    private static int[][] board;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Sudoku Puzzle Game!");

        while (true) {
            System.out.println("\nChoose difficulty level:\n1. Easy\n2. Medium\n3. Hard\nEnter your choice: ");
            int difficulty = scanner.nextInt();

            generatePuzzle(difficulty);
            long startTime = System.currentTimeMillis();

            while (!isSolved()) {
                displayBoard();
                System.out.println("Enter your solution (row col value) or 'q' to quit: ");

                String input = scanner.next();
                if (input.equalsIgnoreCase("q")) {
                    System.out.println("Thanks for playing!");
                    return;
                }

                int row = Integer.parseInt(input) - 1;
                int col = scanner.nextInt() - 1;
                int value = scanner.nextInt();

                if (isValidMove(row, col, value)) {
                    board[row][col] = value;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            }

            long timeTaken = (System.currentTimeMillis() - startTime) / 1000;
            System.out.println("Congratulations! You solved the Sudoku puzzle in " + timeTaken + " seconds!");
            System.out.println("Do you want to play again? (Y/N): ");
            if (scanner.next().equalsIgnoreCase("N")) {
                System.out.println("Thank you for playing Sudoku Puzzle Game!");
                break;
            }
        }
        scanner.close();
    }

    private static void generatePuzzle(int difficulty) {
        board = new int[SIZE][SIZE];
        int[][] solution = new int[SIZE][SIZE];
        Random rand = new Random();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                solution[i][j] = (i * 3 + i / 3 + j) % SIZE + 1;
            }
        }

        int clues = difficulty == 1 ? 40 : (difficulty == 2 ? 30 : 20);
        Set<String> used = new HashSet<>();
        while (used.size() < clues) {
            int r = rand.nextInt(SIZE);
            int c = rand.nextInt(SIZE);
            String key = r + "," + c;
            if (!used.contains(key)) {
                board[r][c] = solution[r][c];
                used.add(key);
            }
        }
    }

    private static void displayBoard() {
        System.out.println("Sudoku Puzzle:");
        for (int i = 0; i < SIZE; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("----------------------");
            }
            for (int j = 0; j < SIZE; j++) {
                if (j % 3 == 0 && j != 0) System.out.print(" | ");
                System.out.print((board[i][j] == 0 ? "_" : board[i][j]) + " ");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col, int value) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || board[row][col] != 0) return false;
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == value || board[i][col] == value) return false;
        }
        int boxRow = (row / 3) * 3, boxCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[boxRow + i][boxCol + j] == value) return false;
            }
        }
        return true;
    }

    private static boolean isSolved() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) return false;
            }
        }
        return true;
    }
}
