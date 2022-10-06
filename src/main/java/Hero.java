import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private Position position;
    private Screen screen;
    private Hero hero;

    public Hero(int x, int y) {
        position.setX(x);
        position.setY(y);
    }

    private void moveHero(Position position) {
        hero.setPosition(position);
    }

    public void draw(Screen screen) {
        this.screen.setCharacter(10, 10, TextCharacter.fromCharacter('X')[0]);
    }
    public void setPosition(Position p){
        position.setX(position.getX());
        position.setY(position.getY());
    }
    public Position moveUp(){
        return new Position(position.getX(), position.getY() - 1);
    }
    public Position moveDown(){
        return new Position(position.getX(), position.getY() + 1);
    }
    public Position moveLeft(){
        return new Position(position.getX() - 1, position.getY());
    }
    public Position moveRight(){
        return new Position(position.getX()+1, position.getY());
    }
}
