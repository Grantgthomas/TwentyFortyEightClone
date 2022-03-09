
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class TwentyFortyEight {
    //This instance variable represents the board. Use this to make changes.
    private int[][] board;
    //This variable keeps track of the current score.
    private int bestScore;
    private int score;
    private int boardWidth;
    //Constructor
    public TwentyFortyEight(int boardWidth){
        this.board = new int[boardWidth][boardWidth];
        this.score = score;
        this.boardWidth = boardWidth;
        placeRandom();
        // TODO
    }

    //This function resets the board to its initial state
    public void reset() {
        // TODO
     this.board = new int[boardWidth][boardWidth];
      this.score = 0;
     placeRandom();

    }

    //This function returns the total number of blank spaces on the board
    public int numBlanks() {

        // TODO
        int returnVal = 0;
        for(int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (board[i][j] == 0) {
                    returnVal++;
                }
            }
        }
        return returnVal;
    }

    //This function places a 2 at a random blank space on the board.
    //It does nothing if there are no blank spaces.
    public void placeRandom(){
        if(numBlanks()!=0) {
            ArrayList<String> freeSpace = new ArrayList<String>();
            int placeAt = 0;
            int row = 0;
            int col = 0;
            // Find all of the blanks on the board
            for (int i = 0; i < boardWidth; i++) {
                for (int j = 0; j < boardWidth; j++) {
                    if (board[i][j] == 0) {
                        freeSpace.add(i + "," + j);
                    }
                }
            }
            // Add their coordinates to a list
            // randomly choose one
            if (freeSpace.size() > 0) {
                placeAt = (int) (Math.random() * freeSpace.size() - 1);
                row = Integer.parseInt(freeSpace.get(placeAt).substring(0, 1));
                col = Integer.parseInt(freeSpace.get(placeAt).substring(2));
                board[row][col] = 2;
            } else {
                System.out.println("Game Over");
                reset();
            }
        }// TODO
    }

    //This function attempts to move a cell at coordinates fromRow,fromCol to
    //the cell toRow,toCol. Refer to the handout for movement rules.
    public boolean moveTo(int fromRow, int fromCol, int toRow, int toCol) {
        if(toRow-fromRow >1 || toCol-fromCol >1)
            return false;
      if( (fromRow >= 0 && fromRow < boardWidth) && (fromCol >= 0 && fromCol < boardWidth) && (toRow >= 0 && toRow < boardWidth) && (toCol >= 0 && toCol < boardWidth) ) {
          //check if all coordinates are in bounds
          if(board[fromRow][fromCol]!=0){
              //make sure a zero isn't being moved
              if(board[toRow][toCol] == 0){
                  board[toRow][toCol] = board[fromRow][fromCol];
                  board[fromRow][fromCol] = 0;
                  return true;
              } else if (board[fromRow][fromCol] == board[toRow][toCol]){
                  board[toRow][toCol] = board[toRow][toCol]*2;
                  board[fromRow][fromCol] = 0;
                    if(board[toRow][toCol] > score )
                        score = board[toRow][toCol];
                  return true;
              }else
                  return false;
          }else return false;
          // TODO
      }else return false;
        //return false;
    }

    //The following four functions move the board in a single direction.
    public boolean moveUp(){
        // TODO

        for(int i = boardWidth-1; i >= 0; i--){
            for(int j =0;j < boardWidth; j++){
                moveTo(i,j,i-1,j);
            }
        }
        return false;
    }
    public boolean moveDown() {
        // TODO

        for(int i = boardWidth-1; i >=0; i--){
            for(int j =0;j < boardWidth; j++){
                moveTo(i, j, i + 1, j);

            }
        }
        return false;
    }
    //doesn't move larger pieces sometimes
    public boolean moveRight() {
        // TODO

        for(int i = 0; i < boardWidth; i++){
            for(int j =boardWidth-1;j >= 0; j--){
                if(moveTo(i, j, i , j+1))
                    continue;
            }
        }
        return false;
    }


    public boolean moveLeft() {

        for(int i = 0; i < boardWidth; i++){
            for(int j =boardWidth-1;j >= 0; j--){
                if(moveTo(i, j, i , j-1))
                    continue;
            }
        }
        return false;
    }

    ////////////////////////////////////////////////////////////////////////
    // Do not edit the methods below, they are for testing and running the
    // program.
    ////////////////////////////////////////////////////////////////////////
    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] newBoard) {
        board = newBoard;
    }

    /**
     * The main will allow you to play the game.
     *
     * @param args
     */
//


    public static void main(String args[]) {
        TwentyFortyEight tfe = new TwentyFortyEight(4);
        Scanner s = new Scanner(System.in);
        int bestScore = 0;
        boolean resetFlag = false;
        System.out.println(tfe.numBlanks() );
      //  tfe.placeRandom();

        while(true) {
            System.out.println("Current score: " + tfe.getScore() + " | Best score: " + bestScore);

            tfe.showBoard();

            System.out.println("Enter up, down, left or right to move in that direction.");
            System.out.println("Enter reset to reset the game or quit to exit.");
            String line = s.nextLine();

            switch(line){
                case "up":
                    while(tfe.moveUp()){
                        tfe.moveUp();
                    }
                    break;
                case "down":
                    while(tfe.moveDown()){
                      tfe.moveDown();
                    }
                    break;
                case "left":
                    while(tfe.moveLeft()){
                        tfe.moveLeft();
                    }
                    break;
                case "right":
                    while(tfe.moveRight()){
                        tfe.moveRight();
                    }
                    break;
                case "reset":
                    tfe.reset();
                    resetFlag = true;
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Invalid move, Please try again!!!!\n");
                    continue;

            }

            bestScore = Math.max(bestScore, tfe.getScore());
            if(!resetFlag) {
             tfe.placeRandom();
                resetFlag = false;
            }
        }
    }



    public void showBoard() {
        for(int x = 0; x < boardWidth * 6 + 1; x++){
            System.out.print("-");
        }
        System.out.println();

        for(int x = 0; x < boardWidth; x++) {
            for(int y = 0; y < boardWidth; y++) {
                String square = (board[x][y] == 0)? "":board[x][y] + "";
                System.out.printf("|%5s", square);
            }
            System.out.println("|");

            for(int y = 0; y < boardWidth * 6 + 1; y++){
                System.out.print("-");
            }
            System.out.println();
        }
    }

    public int getScore() {
        return score;
    }

}