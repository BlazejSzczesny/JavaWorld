package main;



public class Position implements Comparable<Position> {
    int X;
    int Y;

    public Position(int X, int Y) {

        this.X = X;
        this.Y = Y;
    }
    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    @Override
    public int compareTo(Position o) {
        if(X == o.getX() && Y == o.getY())
            return 1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return "Position{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }

}
