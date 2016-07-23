package Model;

import java.util.Arrays;

/**
 * Created by KUBA on 2016-05-26.
 */
public class Automaton {

    private int height;
    private int width;
    private int board[][];

    public Automaton(int height, int width){
        this.height = height;
        this.width = width;
        this.board = new int[height][width];
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setBoard(int[][] board) {
        this.board = deepCopy(board);
    }

    public void setCell(int width, int height, int value){
        board[height][width] = value;
    }

    public void displayBoard(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public int[][] deepCopy(int[][] current){                                        //we copy current array to another
        int temporaryBoard[][] = new int[height][width];
        for(int i = 0; i < height; i++)
            temporaryBoard[i] = Arrays.copyOf(current[i], getWidth());

        return temporaryBoard;
    }

    public void makeLive(int[][] board, int height, int width){
        board[height][width] = 1;
    }

    public void makeDead(int board[][], int height, int width){
        board[height][width] = 0;
    }

    public boolean checkLive(int cell){
        if(cell != 0){
            return true;
        }
        return false;
    }

    public int[] neighboorCounter(int height, int width, int ... state){
        int counter[] = new int[state.length];

        for(int i = height - 1; i <= height + 1; i++){
            for(int j = width - 1; j <= width + 1; j++){
                if(0 <= i && i < getHeight() && 0 <= j && j < getWidth()){
                    for(int k = 0; k < state.length; k++){
                        if(getBoard()[i][j] == state[k] && (i != height || j != width)){
                            counter[k]++;
                        }

                    }

                }
            }
        }

        return counter;
    }
}
