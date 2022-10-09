import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Arena(int width, int height) {
        hero = new Hero(10, 10);
        this.width = width;
        this.height = height;
        this.walls = createWalls();
    }
    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        screen.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(screen);
        for (Wall wall : walls) wall.draw(screen);
    }
    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }
    public boolean canHeroMove(Position position) {
        if ((position.getX() >= 0 && position.getX() < width) && (position.getY() >= 0 && position.getY() < height) && !walls.contains(new Wall(position.getX(), position.getY()))){
            return true;
        }
        else return false;
    }

    public void processKey(KeyStroke key) {
        if(key.getKeyType()== KeyType.ArrowUp){moveHero(hero.moveUp());}
        if(key.getKeyType()== KeyType.ArrowDown){moveHero(hero.moveDown());}
        if(key.getKeyType()== KeyType.ArrowLeft){moveHero(hero.moveLeft());}
        if(key.getKeyType()== KeyType.ArrowRight){moveHero(hero.moveRight());}
    }

    private List<Wall> createWalls(){
        List<Wall> walls = new ArrayList<>();

        for(int c=0; c < width; c++){
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height-1));
        }

        for(int r=0; r < height; r++){
            walls.add(new Wall(0, r));
            walls.add(new Wall(width-1, r));
        }

        return walls;
    }

}