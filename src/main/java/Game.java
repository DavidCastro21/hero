import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class Game {
    private Position position;
    private Hero hero;
    private Screen screen;
    private int x = 10;
    private int y = 10;
    private Arena arena;

    public Game() {
        try {
            arena = new Arena(40, 50);
            TerminalSize terminalsize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalsize);
            Terminal terminal = terminalFactory.createTerminal();
            this.screen = new TerminalScreen(terminal);
            this.screen.setCursorPosition(null); // we don't need a cursor
            this.screen.startScreen();           // screens must be started
            this.screen.doResizeIfNecessary();   // resize screen if necessary

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        this.screen.clear();
        this.screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        this.screen.refresh();
    }

    private void processKey(KeyStroke key) throws IOException {
        arena.processKey(key);
    }


    public void run() {
        try {
            while (true) {
                draw();
                KeyStroke key = this.screen.readInput();
                processKey(key);
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == ('q'))
                    screen.close();
                if (key.getKeyType() == KeyType.EOF) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
