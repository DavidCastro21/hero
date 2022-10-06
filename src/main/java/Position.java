public class Position {
    private Position position;
    private Hero hero;
    private int x = 10;
    private int y = 10;

    public Position(int x, int y){

    }
    private void moveHero(){
        hero.setPosition(position);
    }
    public void setY(int y){
        this.y = this.y;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x = this.x;
    }
    public int getX(){
        return x;
    }
}
