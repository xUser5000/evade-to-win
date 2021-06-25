import lombok.Setter;
import lombok.Getter;
import vector.Vector;
import particles.MoverParticle;
import particles.Particle;
import vector.VectorUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Game {

    private int width, height;
    private Particle coin;
    private MoverParticle player;
    private List<MoverParticle> enemies;
    @Setter private Vector mouse;
    private int pointsCount;

    private static float DEFAULT_RADIUS = 25;
    private static float MAX_SPEED = 15;

    public Game (int width, int height) {
        this.width = width;
        this.height = height;
        init();
    }

    public void init () {
        coin = new Particle(
                new Vector(MathUtil.random(0, width), MathUtil.random(0, height)),
                DEFAULT_RADIUS,
                Constants.COLOR_YELLOW
        );
        player = new MoverParticle(
                new Vector(width/2, height/2),
                DEFAULT_RADIUS,
                Constants.COLOR_BLUE,
                new Vector(0, 0)
        );
        enemies = new ArrayList<>();
        mouse = new Vector(width/2, height/2);
        pointsCount = 0;
    }

    public void update () {
        Vector diff = VectorUtil.subtract(mouse, player.getPosition());
        if (VectorUtil.magnitude(diff) < player.getRadius()) player.setVelocity(new Vector(0, 0));
        else {
            player.setVelocity(VectorUtil.scale(
                    VectorUtil.normalize(diff),
                    MAX_SPEED
            ));
        }
        player.update();

        // Animate all the enemies
        for (MoverParticle enemy: enemies) {
            enemy.update();
            float x = enemy.getPosition().getX();
            float y = enemy.getPosition().getY();
            if (x > width) enemy.getPosition().setX(x % width);
            if (y > height) enemy.getPosition().setY(y % height);
        }

    }

    public void levelUp () {
        pointsCount++;
        coin = new Particle(
                new Vector(MathUtil.random(0, width), MathUtil.random(0, height)),
                DEFAULT_RADIUS,
                Constants.COLOR_YELLOW
        );

        // Add a new enemy
        enemies.add(
                new MoverParticle(
                    new Vector(MathUtil.random(0, width), MathUtil.random(0, height)),
                    DEFAULT_RADIUS,
                    Constants.COLOR_RED,
                    VectorUtil.scale(
                            VectorUtil.normalize(
                                    new Vector(MathUtil.random(0, width), MathUtil.random(0, height))
                            ),
                            MAX_SPEED
                    )
                )
        );

        // Increase the speed of the enemies
        for (MoverParticle enemy: enemies) {
            enemy.setVelocity(
                    VectorUtil.scale(
                            VectorUtil.normalize(enemy.getVelocity()),
                            MAX_SPEED
                    )
            );
        }
    }

    public boolean hasPlayerReachedCoin () {
        return collision(player, coin);
    }

    private boolean collision (Particle a, Particle b) {
        return VectorUtil.distance(a.getPosition(), b.getPosition()) <= a.getRadius() + b.getRadius();
    }
}
