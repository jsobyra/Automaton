package Model;

/**
 * Created by KUBA on 2016-05-29.
 */
public class Quadlife extends Automaton {

    public Quadlife(int height, int width){
        super(height, width);
    }

    public void nextGeneration(){
        int temporary_board[][] = deepCopy(getBoard());
        for(int i = 0; i < getHeight(); i++){
            for(int j = 0; j < getWidth(); j++){
                int counter[] = neighboorCounter(i, j, 0, 1, 2, 3, 4);
                int neighbor = sum(counter);
                if(checkLive(getBoard()[i][j])){
                    if(neighbor < 2 || neighbor > 3)
                        makeDead(temporary_board, i, j);
                }
                else if(neighbor == 3 && !checkLive(getBoard()[i][j])){
                    int color = color(counter);
                    if(color == 1)
                        makeLive1(temporary_board, i, j);
                    else if(color == 2)
                        makeLive2(temporary_board, i, j);
                    if(color == 3)
                        makeLive3(temporary_board, i, j);
                    if(color == 4)
                        makeLive4(temporary_board, i, j);
                }
            }
        }

        setBoard(temporary_board);
    }

    public void makeLive1(int[][] board, int height, int width){
        board[height][width] = 1;
    }

    public void makeLive2(int[][] board, int height, int width){
        board[height][width] = 2;
    }

    public void makeLive3(int[][] board, int height, int width){
        board[height][width] = 3;
    }

    public void makeLive4(int[][] board, int height, int width){
        board[height][width] = 4;
    }

    private boolean checkLive1(int[][] board, int height, int width){
        if(board[height][width] == 1)
            return true;
        return false;
    }

    private boolean checkLive2(int[][] board, int height, int width){
        if(board[height][width] == 2)
            return true;
        return false;
    }

    private boolean checkLive3(int[][] board, int height, int width){
        if(board[height][width] == 3)
            return true;
        return false;
    }

    private boolean checkLive4(int[][] board, int height, int width){
        if(board[height][width] == 4)
            return true;
        else
            return false;
    }

    public int sum(int board[]){
        int sum = 0;
        for(int i = 1; i < board.length; i++)
            sum += board[i];

        return sum;
    }

    public int color(int neighbor[]){             // IT IS WRONG AS FOR NOW !!!!
        int color = 0;
        int max = 0;

        for(int i = 1; i < neighbor.length; i++){
            int flag = 1;
            for(int j =1; j < neighbor.length; j++){
                if(j != i && neighbor[j] == neighbor[i]){
                    flag++;
                }
                else if(j != i){
                    color = j;
                }
            }
            if(flag == neighbor.length - 2)
                return color;
        }

        for(int i = 1; i < neighbor.length; i++){
            if(max < neighbor[i]){
                color = i;
                max = neighbor[i];
            }
        }

        return color;
    }

}
