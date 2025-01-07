import java.util.*;
class Tic_Toc_Toe{
    public static void main(String args[]){
        char[][] board=new char[3][3];
        for(int ir=0;ir<3; ir++){
            for(int jc=0;jc<3;jc++){
                board[ir][jc]=' ';
            }
        }
        Scanner sc=new Scanner(System.in);

        char player ='X';
        boolean gameOver=false;
        while(!gameOver){
            printBoard(board);
            System.out.print("Player '"+player+"' Enter : ");
            int row=sc.nextInt();
            int col=sc.nextInt();
            if(board[row][col]==' '){
                board[row][col]=player;
                gameOver = haveWon(board, player);
                if(gameOver){
                    System.out.println("Player '"+player+"' has won...");
                }else{
                    if(player == 'X')
                        player = 'O';
                    else
                        player = 'X';
                }
            } else {
                System.out.println("Invalid move. Try again!");
            }
        }
        printBoard(board);
    }
    public static boolean haveWon(char[][]board, char player){
        for(int ir=0;ir<3;ir++){
            if(board[ir][0]==player && board[ir][1]==player && board[ir][2]==player ){
                return true;
            }
        }

        for(int jc=0;jc<3;jc++){
            if(board[0][jc]==player && board[1][jc]==player && board[2][jc]==player){
                return true;
            }
        }

        if(board[0][0]==player && board[1][1]==player && board[2][2]==player)
            return true;

        if(board[0][2]==player && board[1][1]==player && board[2][0]==player)
            return true;

        return false;
    }
    public static void printBoard(char[][] board){
        for(int ir=0;ir<3;ir++){
            for(int jc=0;jc<3;jc++){
                System.out.print(board[ir][jc]);
                if(jc<2)
                    System.out.print(" | ");
            }
            System.out.println();
            if(ir<2)
                System.out.println("--+---+--");
        }
    }
}
