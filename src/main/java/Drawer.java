import lombok.AllArgsConstructor;
import particles.Particle;
import processing.core.PApplet;

@AllArgsConstructor
public class Drawer {

    private PApplet context;
    private Game game;

    public void draw () {
        context.noStroke();
        drawParticle(game.getCoin());
        drawParticle(game.getPlayer());
        for (Particle enemy: game.getEnemies()) drawParticle(enemy);
        printPoints();
    }

    private void drawParticle (Particle particle) {
        fill(particle.getColor());
        context.noStroke();
        context.ellipse(
                particle.getPosition().getX(),
                particle.getPosition().getY(),
                particle.getRadius() * 2,
                particle.getRadius() * 2
        );
    }

    private void fill (int[] color) {
        context.fill(color[0], color[1], color[2]);
    }

    private void printPoints () {
        context.textSize(32);
        fill(Constants.COLOR_BLACK);
        context.text("Points: " + game.getPointsCount(), 10, 30);
    }
}
