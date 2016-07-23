package Model;


/**
 * Created by KUBA on 2016-05-26.
 */
public class Langton extends Automaton{
    private Ant ant;

    public Langton(int height, int width){
        super(height, width);
        this.ant = new Ant(height/2, width/2, AntDirection.N);
    }

    public Ant getAnt() {
        return ant;
    }

    public void setAnt(Ant ant) {
        this.ant = ant;
    }

    public void nextGeneration(){

        if(checkLive(getBoard()[ant.getY()][ant.getX()])){
            makeDead(getBoard(), ant.getY(), ant.getX());
            if(ant.getDirection() == AntDirection.N){
                ant.setX(ant.getX() + 1);
                ant.setDirection(AntDirection.E);
            }
            else if(ant.getDirection() == AntDirection.E){
                ant.setY(ant.getY() + 1);
                ant.setDirection(AntDirection.S);
            }
            else if(ant.getDirection() == AntDirection.S){
                ant.setX(ant.getX() - 1);
                ant.setDirection(AntDirection.W);
            }
            else if(ant.getDirection() == AntDirection.W){
                ant.setY(ant.getY() - 1);
                ant.setDirection(AntDirection.N);
            }
        }
        else{
            makeLive(getBoard(), ant.getY(), ant.getX());
            if(ant.getDirection() == AntDirection.N){
                ant.setX(ant.getX() - 1);
                ant.setDirection(AntDirection.W);
            }
            else if(ant.getDirection() == AntDirection.E){
                ant.setY(ant.getY() - 1);
                ant.setDirection(AntDirection.N);
            }
            else if(ant.getDirection() == AntDirection.S){
                ant.setX(ant.getX() + 1);
                ant.setDirection(AntDirection.E);
            }
            else if(ant.getDirection() == AntDirection.W){
                ant.setY(ant.getY() + 1);
                ant.setDirection(AntDirection.S);
            }
        }
    }
}

