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
    }

    private void drawMouse () {
        context.noFill();
        context.stroke(10);
        context.strokeWeight(5);
        context.ellipse(
                game.getMouse().getX(),
                game.getMouse().getY(),
                50,
                50
        );
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

    private void stroke (int[] color) {
        context.stroke(color[0], color[1], color[2]);
    }
}
