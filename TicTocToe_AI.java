import java.util.Scanner;

public class TicTacToeAI {

    private static final char huPlayer = 'O'; 
    private static final char aiPlayer = 'X'; 
    private static char[] board = new char[9]; 
    private static int[][] winCombos = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, 
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, 
            {0, 4, 8}, {2, 4, 6} 
    };

    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        resetBoard();
        printBoard();
        while (true) {
            playerTurn();
            printBoard();
            if (checkWin(huPlayer)) {
                System.out.println("You Win!");
                break;
            }
            if (checkTie()) {
                System.out.println("Tie Game!");
                break;
            }
            aiTurn();
            printBoard();
            if (checkWin(aiPlayer)) {
                System.out.println("You Lost!");
                break;
            }
            if (checkTie()) {
                System.out.println("Tie Game!");
                break;
            }
        }
    }

    public static void resetBoard() {
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
    }

    public static void printBoard() {
        System.out.println("Board:");
        for (int i = 0; i < 9; i++) {
            System.out.print(board[i]);
            if (i % 3 == 2) {
                System.out.println();
            } else {
                System.out.print("|");
            }
        }
        System.out.println();
    }

    public static void playerTurn() {
        Scanner scanner = new Scanner(System.in);
        int square;
        while (true) {
            System.out.print("Enter a number (1-9) to make a move:");
            square = scanner.nextInt() - 1;
            if (square >= 0 && square < 9 && board[square] == ' ') {
                board[square] = huPlayer;
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    public static void aiTurn() {
        int bestMove = bestSpot();
        board[bestMove] = aiPlayer;
    }

    public static boolean checkWin(char player) {
        for (int[] combo : winCombos) {
            if (board[combo[0]] == player && board[combo[1]] == player && board[combo[2]] == player) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkTie() {
        for (char c : board) {
            if (c == ' ') {
                return false;
            }
        }
        return true;
    }

    public static int bestSpot() {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = aiPlayer;
                int score = minmax(board, false);
                board[i] = ' ';
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }

    public static int minmax(char[] newBoard, boolean isMaximizing) {
        if (checkWin(aiPlayer)) return 1;
        if (checkWin(huPlayer)) return -1;
        if (checkTie()) return 0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 9; i++) {
            if (newBoard[i] == ' ') {
                newBoard[i] = isMaximizing ? aiPlayer : huPlayer;
                int score = minmax(newBoard, !isMaximizing);
                newBoard[i] = ' ';
                bestScore = isMaximizing ? Math.max(bestScore, score) : Math.min(bestScore, score);
            }
        }
        return bestScore;
    }
}
