public class Position {
    private int x = 10;
    private int y = 10;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getX(){
        return x;
    }
    @Override
    public boolean equals(Object o) {
        if(o == null || o.getClass() != this.getClass()) return false;

        return (this == o) ||
                (this.x == ((Position) o).x && this.y == ((Position) o).y);
    }
}