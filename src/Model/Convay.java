package Model;

/**
 * Created by KUBA on 2016-05-22.
 */
public class Convay extends Automaton{

    public Convay(int height, int width){
        super(height, width);
    }

    public void nextGeneration(){
        int temporary_board[][] = deepCopy(getBoard());

        for(int i = 0; i < getHeight(); i++){
            for(int j = 0; j <getWidth(); j++){
                int counter[] = neighboorCounter(i, j, 0, 1);
                if(checkLive(getBoard()[i][j])){
                    if(counter[1] < 2)
                        makeDead(temporary_board, i, j);
                    else if(counter[1] > 3)
                        makeDead(temporary_board, i, j);
                    }
                    else if(counter[1] == 3 && checkLive(getBoard()[i][j]) == false)
                        makeLive(temporary_board, i, j);
            }
        }

        setBoard(temporary_board);
    }
}

