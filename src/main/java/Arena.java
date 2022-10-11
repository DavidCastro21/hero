import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Monster> monsters;
    public int getHeight() {return height;}

    public int getWidth() {return width;}
    public Position moveUp(){
        return new Position(hero.getPosition().getX(), hero.getPosition().getY() - 1);
    }
    public Position moveDown(){
        return new Position(hero.getPosition().getX(), hero.getPosition().getY() + 1);
    }
    public Position moveLeft(){
        return new Position(hero.getPosition().getX() - 1, hero.getPosition().getY());
    }
    public Position moveRight(){
        return new Position(hero.getPosition().getX()+1, hero.getPosition().getY());
    }

    public Arena(int width, int height) {
        hero = new Hero(10, 10);
        this.width = width;
        this.height = height;
        walls = createWalls();
        monsters = createMonsters();
    }
    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        screen.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');

        hero.draw(screen);

        for(Wall wall : walls)
            wall.draw(screen);

        for(Monster monster : monsters)
            monster.draw(screen);
    }
    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for(int i=0; i<5; i++){
            Monster newmonster = new Monster(random.nextInt(width-2) + 1, random.nextInt(height-2)+1);
            if(!monsters.contains(newmonster) && !newmonster.getPosition().equals(hero.getPosition()))
                monsters.add(newmonster);
        }
        return monsters;
    }
    public void moveMonsters(){
        for(Monster monster : monsters){
            monster.setPosition(monster.move(this));
        }
    }


    public boolean verifyMonsterCollisions(){
        for(Monster monster : monsters){
            if(monster.getPosition().equals(hero.getPosition())){
                System.out.println("Death.");
                return true;
            }
        }
        return false;
    }
    public void moveHero(Position position) {if (canHeroMove(position))hero.setPosition(position);}
    public boolean canHeroMove(Position pos) {
        return (pos.getX() >= 0 && pos.getX() < width) &&
                (pos.getY() >= 0 && pos.getY() < height) &&
                !walls.contains(new Wall(pos.getX(), pos.getY()));
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
    private class Hero extends Element {
        private Hero(int x, int y) {
            super(x, y);
        }

        public void draw(TextGraphics screen) {
            screen.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
            screen.enableModifiers(SGR.BOLD);
            screen.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "X");

        }
    }
}