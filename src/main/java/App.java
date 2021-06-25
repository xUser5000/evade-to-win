import processing.core.PApplet;
import vector.Vector;

public class App extends PApplet {

    static final int WIDTH = 1200;
    static final int HEIGHT = 800;

    Game game;
    Drawer drawer;

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        game = new Game(WIDTH, HEIGHT);
        drawer = new Drawer(this, game);
    }

    @Override
    public void draw() {
        background(255);
        game.setMouse(new Vector(mouseX, mouseY));
        game.update();
        drawer.draw();
    }

    public static void main(String[] args) {
        PApplet.main(App.class);
    }
}
