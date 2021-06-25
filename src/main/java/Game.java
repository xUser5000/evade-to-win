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

    public Game (int width, int height) {
        this.width = width;
        this.height = height;
        init();
    }

    public void init () {
        coin = new Particle(
                new Vector(MathUtil.random(0, width), MathUtil.random(0, height)),
                Constants.DEFAULT_RADIUS,
                Constants.COLOR_YELLOW
        );
        player = new MoverParticle(
                new Vector(width/2, height/2),
                Constants.DEFAULT_RADIUS,
                Constants.COLOR_BLUE,
                new Vector(0, 0)
        );
        enemies = new ArrayList<>();
        mouse = new Vector(width/2, height/2);
        pointsCount = 0;
    }

    public void update () {
        // diff = mouse - player position
        Vector diff = VectorUtil.subtract(mouse, player.getPosition());
        if (VectorUtil.magnitude(diff) < player.getRadius()) {
            player.setVelocity(new Vector(0, 0));
        } else {
            player.setVelocity(VectorUtil.setMagnitude(diff, Constants.PLAYER_SPEED));
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
                Constants.DEFAULT_RADIUS,
                Constants.COLOR_YELLOW
        );

        // Add a new enemy
        enemies.add(
                new MoverParticle(
                    new Vector(MathUtil.random(0, width), MathUtil.random(0, height)),
                    Constants.DEFAULT_RADIUS,
                    Constants.COLOR_RED,
                    VectorUtil.setMagnitude(
                            new Vector(MathUtil.random(0, width), MathUtil.random(0, height)),
                            Constants.ENEMY_SPEED
                    )
                )
        );
    }

    public boolean hasPlayerReachedCoin () {
        return collision(player, coin);
    }

    public boolean hasPlayerHitEnemy () {
        for (Particle enemy: enemies) {
            if (collision(player, enemy)) return true;
        }
        return false;
    }

    private boolean collision (Particle a, Particle b) {
        return VectorUtil.distance(a.getPosition(), b.getPosition()) <= a.getRadius() + b.getRadius();
    }
}
