package Model;

/**
 * Created by KUBA on 2016-05-26.
 */
public class Ant {
    private int x;
    private int y;
    private AntDirection direction;

    public Ant(int x, int y, AntDirection antDirection){
        this.x = x;
        this.y = y;
        this.direction = antDirection;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public AntDirection getDirection() {
        return direction;
    }

    public void setDirection(AntDirection direction) {
        this.direction = direction;
    }
}
