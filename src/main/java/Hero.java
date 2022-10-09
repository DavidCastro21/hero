import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;



public class Hero {
    private int x;
    private int y;
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

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
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
