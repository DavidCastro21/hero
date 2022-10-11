import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    public Game() {
        try {
            arena = new Arena(40, 50);
            TerminalSize terminalsize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalsize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen();           // screens must be started
            screen.doResizeIfNecessary();   // resize screen if necessary

        } catch (IOException e) {
            e.printStackTrace();
        }
        arena = new Arena(40, 20);
    }
    private Position position;
    private Screen screen;
    private int x = 10;
    private int y = 10;
    private Arena arena;
    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    private void processKey(com.googlecode.lanterna.input.KeyStroke key){
        System.out.println(key);
        switch (key.getKeyType()) {
            case ArrowUp    -> arena.moveHero(arena.moveUp());
            case ArrowDown  -> arena.moveHero(arena.moveDown());
            case ArrowLeft  -> arena.moveHero(arena.moveLeft());
            case ArrowRight -> arena.moveHero(arena.moveRight());
        }
    }

    public void run() {
        try {
            while(true) {
                draw();
                com.googlecode.lanterna.input.KeyStroke key = screen.readInput();
                processKey(key);
                if(arena.verifyMonsterCollisions()){
                    screen.close();
                    break;
                }

                if (key.getKeyType() == KeyType.Character && key.getCharacter() == ('q'))
                    screen.close();
                if (key.getKeyType() == KeyType.EOF)
                    break;

                arena.moveMonsters();
                if(arena.verifyMonsterCollisions()){
                    screen.close();
                    break;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
