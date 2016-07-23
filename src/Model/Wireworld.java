package Model;

/**
 * Created by KUBA on 2016-05-28.
 */
public class Wireworld extends Automaton {
    public Wireworld(int height, int width){
        super(height, width);
    }

    public void nextGeneration(){
        int temporary_board[][] = deepCopy(getBoard());

        for(int i = 0; i < getHeight(); i++){
            for(int j = 0; j < getWidth(); j++){
                if(empty(getBoard()[i][j]))
                    makeEmpty(temporary_board, i, j);
                else if(electronHead(getBoard()[i][j]))
                    makeElectronTail(temporary_board, i, j);
                else if(electronTail(getBoard()[i][j]))
                    makeConductor(temporary_board, i, j);
                else if(conductor(getBoard()[i][j])){
                    int counter[] = neighboorCounter(i, j, 0, 1, 2, 3);
                    if(counter[1] == 1 || counter[1] == 2)
                        makeElectronHead(temporary_board, i, j);
                    else
                        makeConductor(temporary_board, i, j);
                }
            }
        }
        setBoard(temporary_board);
    }

    private boolean empty(int cell){
        if(cell == 0)
            return true;
        return false;
    }

    private boolean electronHead(int cell){
        if(cell == 1)
            return true;
        return false;
    }

    private boolean electronTail(int cell){
        if(cell == 2)
            return true;
        return false;
    }

    private boolean conductor(int cell){
        if(cell == 3)
            return true;
        return false;
    }

    private void makeEmpty(int board[][], int height, int width){
        board[height][width] = 0;
    }

    private void makeElectronHead(int board[][], int height, int width){
        board[height][width] = 1;
    }

    private void makeElectronTail(int board[][], int height, int width){
        board[height][width] = 2;
    }

    private void makeConductor(int board[][], int height, int width){
        board[height][width] = 3;
    }

}
